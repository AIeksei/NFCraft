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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void saveOrder(List<PostCartDTO> postCartDTO,Float price, Long user_id) {
        for(int i = 0; i < postCartDTO.size(); i++){
           if( productRepository.countProducts(postCartDTO.get(i).getId()) < postCartDTO.get(i).getCount()){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Количество товара превышает доступное количество");
           };
        }
        orderRepository.saveOrders(LocalDate.now(), price, user_id);
        Long orderId = orderRepository.getLastOrderId();
        for (int i = 0; i < postCartDTO.size(); i++) {
            productRepository.changeCount(productRepository.countProducts(postCartDTO.get(i).getId()) - postCartDTO.get(i).getCount(), postCartDTO.get(i).getId());
            orderRepository.saveBuyProduct(postCartDTO.get(i).getCount(),postCartDTO.get(i).getId(),orderId);
        }
    }
}
