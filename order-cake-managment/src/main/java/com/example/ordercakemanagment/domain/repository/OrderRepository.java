package com.example.ordercakemanagment.domain.repository;


import com.example.ordercakemanagment.domain.model.Order;
import com.example.ordercakemanagment.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
