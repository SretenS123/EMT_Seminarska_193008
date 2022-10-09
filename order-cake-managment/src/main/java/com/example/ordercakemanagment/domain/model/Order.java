package com.example.ordercakemanagment.domain.model;


import com.example.ordercakemanagment.domain.valueobjects.Cake;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderOn;


    @Enumerated(value = EnumType.STRING)
    private OrderState orderState;

    @Column(name="order_currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemsList = new HashSet<>() ;

    private Order() {
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderOn=now;
        this.currency=currency;

    }

    public Money total()
    {
        return orderItemsList.stream().map(OrderItem::subtotal).reduce(new Money(currency,0),Money::add);
    }

    public OrderItem addItem(@NotNull Cake cake, int qty)
    {
        Objects.requireNonNull(cake,"product must not be null");
        var item = new OrderItem(cake.getId(),cake.getPrice(),qty);
        orderItemsList.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId)
    {
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        orderItemsList.removeIf(v->v.getId().equals(orderItemId));
    }
}
