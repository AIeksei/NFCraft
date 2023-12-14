package com.example.shop.controllers;
import com.example.shop.models.Order;
import com.example.shop.models.User;
import com.example.shop.repositories.OrderRepository;
import com.example.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @GetMapping("/myorders")
    public String HomePage(User user, Model model){
        ArrayList<Order> res = OrderService.getOrdersByUserId(user.getId());
        if(res.isEmpty()){
            model.addAttribute("Empty", "Заказов нет...");
        }
        model.addAttribute("orders", res );
        return "ordersPage";
    }

//    @GetMapping("/myorders")
//    public ResponseEntity<?> showPostsForUser(@PathVariable Long id) {
//        return ResponseEntity.ok( productService.findByIdProducts(id));
//    }
}
