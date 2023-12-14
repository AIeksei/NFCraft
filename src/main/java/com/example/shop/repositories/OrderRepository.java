package com.example.shop.repositories;

import com.example.shop.models.DTO.ProductDto;
import com.example.shop.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>{

        @Query(value = "SELECT date as orderdate\n" +
                        "FROM order\n" +
                        "join user on user.id = order.user_id\n", nativeQuery = true)
        Iterable <ProductDto> listProducts();



}
