package com.example.shop.controllers;
import com.example.shop.models.DTO.PostOrderDTO;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.repositories.LoginRepository;
import com.example.shop.repositories.OrderRepository;
import com.example.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/myorders/{userId}")
    public ResponseEntity<?> userOrders(@PathVariable Long userId) {
        return ResponseEntity.ok( orderRepository.allOrders( loginRepository.findByLogin(userId)));
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> userOrderDetails(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderRepository.orderDetails(orderId));
    }
    @PostMapping("/order/add")
    public ResponseEntity<String> UserAdd(@RequestBody PostOrderDTO postOrderDTO)
    {
        try {
            orderService.saveOrder(postOrderDTO.getCartRequest(),postOrderDTO.getPrice(), loginRepository.findByLogin(postOrderDTO.getUser_id()));
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(7);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/allOrders")
    public ResponseEntity<List<ProductDto>> getAllOrders() {
        List<ProductDto> orders = orderRepository.allOrder();
        if (orders == null || orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}

