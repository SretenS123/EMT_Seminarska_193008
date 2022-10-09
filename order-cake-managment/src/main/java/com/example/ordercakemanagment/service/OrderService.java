package com.example.ordercakemanagment.service;

import com.example.ordercakemanagment.domain.exceptions.OrderIdNotExistException;
import com.example.ordercakemanagment.domain.exceptions.OrderItemIdNotExistException;
import com.example.ordercakemanagment.domain.model.Order;
import com.example.ordercakemanagment.domain.model.OrderId;
import com.example.ordercakemanagment.domain.model.OrderItemId;
import com.example.ordercakemanagment.service.forms.OrderForm;
import com.example.ordercakemanagment.service.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderId placeOrder(OrderForm orderForm);

    List<Order> findAll();

    Optional<Order> findById(OrderId id);

    void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistException;


}
