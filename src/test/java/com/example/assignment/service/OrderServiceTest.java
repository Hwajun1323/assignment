package com.example.assignment.service;

import com.example.assignment.model.Order;
import com.example.assignment.model.OrderDto;
import com.example.assignment.model.User;
import com.example.assignment.model.UserListResponse;
import com.example.assignment.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.MockitoJUnitRunner;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @MockBean
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    @After
    public void cleanup() {
        orderRepository.deleteAll();
    }

    @Test
    public void createOrderWithValidOrderDto(){

    }

    @Test
    public void testCreateOrderWithSameProductId(){

    }

    @Test
    public void testCreateOrderWithEmailDoesNotExist() {
        OrderDto orderDto = new OrderDto();
        orderDto.setEmail("test@example.com");
        orderDto.setProductId("123");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(orderDto));
        assertEquals("Email does not exist", exception.getMessage());
    }

    @Test
    public void getAllOrdersCorrectly(){
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("1234", "george.bluth@reqres.in", "George", "Bluth", "7777"));
        orders.add(new Order("1111", "george.bluth@reqres.in", "George", "Bluth", "1111"));
        orders.add(new Order("4321", "janet.weaver@reqres.in", "Janet", "Weaver", "1234"));
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getOrders();

        assertEquals(3, result.size());
        assertEquals("george.bluth@reqres.in", result.get(0).getEmail());
        assertEquals("George", result.get(0).getFirstName());
        assertEquals("Bluth", result.get(0).getLastName());
        assertEquals("7777", result.get(0).getProductId());
    }
}