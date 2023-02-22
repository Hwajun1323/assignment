package com.example.assignment.controller;

import com.example.assignment.model.Order;
import com.example.assignment.model.OrderDto;
import com.example.assignment.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void createOrderWithValidRequest() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setEmail("janet.weaver@reqres.in");
        orderDto.setProductId("1234");

        String orderId = "abcd1234";
        when(orderService.createOrder(orderDto)).thenReturn(orderId);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(orderId));
    }

    @Test
    public void createOrderWithInvalidRequest() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setEmail("janet.weaver@reqres.in");

        String errorMessage = "Invalid request";
        when(orderService.createOrder(orderDto)).thenThrow(new IllegalArgumentException(errorMessage));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));
    }

    @Test
    public void getAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("1234", "george.bluth@reqres.in", "George", "Bluth", "7777"));
        orders.add(new Order("1111", "george.bluth@reqres.in", "George", "Bluth", "1111"));
        orders.add(new Order("4321", "janet.weaver@reqres.in", "Janet", "Weaver", "1234"));

        when(orderService.getOrders()).thenReturn(orders);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].email").value("george.bluth@reqres.in"))
                .andExpect(jsonPath("$[0].firstName").value("George"))
                .andExpect(jsonPath("$[0].lastName").value("Bluth"))
                .andExpect(jsonPath("$[0].productId").value("7777"));
    }
}