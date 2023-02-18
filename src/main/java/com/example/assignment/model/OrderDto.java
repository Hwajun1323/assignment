package com.example.assignment.model;

import lombok.Data;

@Data
public class OrderDto {
    private String email;
    private String firstName;
    private String lastName;
    private String productId;
}
