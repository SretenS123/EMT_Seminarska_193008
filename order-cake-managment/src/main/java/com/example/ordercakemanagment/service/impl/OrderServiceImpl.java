package com.example.ordercakemanagment.service.impl;

import com.example.ordercakemanagment.domain.exceptions.OrderIdNotExistException;
import com.example.ordercakemanagment.domain.exceptions.OrderItemIdNotExistException;
import com.example.ordercakemanagment.domain.model.Order;
import com.example.ordercakemanagment.domain.model.OrderId;
import com.example.ordercakemanagment.domain.model.OrderItemId;
import com.example.ordercakemanagment.domain.repository.OrderRepository;
import com.example.ordercakemanagment.service.OrderService;
import com.example.ordercakemanagment.service.forms.OrderForm;
import com.example.ordercakemanagment.service.forms.OrderItemForm;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.domain.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;





    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderItemsList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getCakeId().getId(),item.getQuantity())));
        return newOrder.getId();

    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(orderItemForm.getCake(),orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getCake().getId().getId(),orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getItems().forEach(item->order.addItem(item.getCake(),item.getQuantity()));
        return order;
    }

}
