package com.micro.OrderService.repository;

import com.micro.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
