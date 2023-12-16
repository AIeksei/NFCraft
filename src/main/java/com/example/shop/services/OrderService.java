package com.example.shop.services;

import com.example.shop.models.DTO.ProductDto;
import com.example.shop.models.Order;
import com.example.shop.repositories.OrderRepository;
import com.example.shop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

        @Autowired
        private final OrderRepository  orderRepository;

    public static ArrayList<Order> getOrdersByUserId(Long id) {
        return null;
    }

}
