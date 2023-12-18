package com.example.shop.services;

import com.example.shop.models.DTO.PostCartDTO;
import com.example.shop.models.DTO.PostOrderDTO;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.models.Order;
import com.example.shop.models.Product;
import com.example.shop.repositories.OrderRepository;
import com.example.shop.repositories.ProductRepository;
import com.example.shop.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final ProductRepository productRepository;

    public static ArrayList<Order> getOrdersByUserId(Long id) {
        return null;
    }

    public void saveOrder(List<PostCartDTO> postCartDTO, Long user_id) {
        orderRepository.saveOrders(LocalDate.now(), 0.0F, user_id);
        Long orderId = orderRepository.getLastOrderId();
        for (int i = 0; i < postCartDTO.size(); i++) {
            orderRepository.saveBuyProduct(postCartDTO.get(i).getCount(),postCartDTO.get(i).getId(),orderId);
        }
    }
}
