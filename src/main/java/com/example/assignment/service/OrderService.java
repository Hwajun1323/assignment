package com.example.assignment.service;

import com.example.assignment.model.Order;
import com.example.assignment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public String createOrder(String email, String productId, String firstName, String lastName) {
        Order order = new Order();
        order.setEmail(email);
        order.setProductId(productId);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        orderRepository.save(order);
        return order.getOrderId();
    }
}