package com.example.assignment.controller;

import com.example.assignment.model.OrderDto;
import com.example.assignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
        String orderId = orderService.createOrder(orderDto.getEmail(), orderDto.getProductId(), orderDto.getFirstName(), orderDto.getLastName());
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }
}