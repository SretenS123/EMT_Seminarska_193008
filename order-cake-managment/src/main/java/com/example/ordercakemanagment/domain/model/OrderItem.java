package com.example.ordercakemanagment.domain.model;

import com.example.ordercakemanagment.domain.valueobjects.CakeId;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {


    @Embedded
    private Money itemPrice;



    @Column(nullable = false)
    private int quantity;


    @Embedded
    @AttributeOverride(name="id",column = @Column(name ="cake_id",nullable = false))
    private CakeId cakeId;


    public OrderItem(@NonNull CakeId cakeId, @NonNull Money itemPrice, int qty)
    {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.cakeId=cakeId;
        this.itemPrice=itemPrice;
        this.quantity=qty;
    }

    public OrderItem() {

    }

    public Money subtotal()
    {
        return itemPrice.multiply(quantity);
    }

}
