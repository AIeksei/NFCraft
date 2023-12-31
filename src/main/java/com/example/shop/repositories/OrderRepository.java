package com.example.shop.repositories;
import com.example.shop.models.DTO.BoughtDTO;
import com.example.shop.models.DTO.BoughtOrderDTO;
import com.example.shop.models.DTO.OrderDto;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.models.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

        @Query(value = " SELECT * \n" +
                "        FROM orders \n" +
                "        WHERE user_id = ?1", nativeQuery = true)
        Iterable <OrderDto> allOrders(@Param("userID") Long userID);

        @Query(value = " SELECT p.id as product_id, p.name, b.quant, u.login_id as user_id," +
                "MAX(CASE WHEN i.count = 1 THEN i.url END) AS url\n" +
                "        FROM orders o\n" +
                "        LEFT JOIN buy_product b ON o.id = b.product_order_id\n" +
                "        LEFT JOIN products p ON p.id = b.product_id\n" +
                "        LEFT JOIN user u ON o.user_id = u.id\n" +
                "        LEFT JOIN product_image i ON i.product_id = p.id\n" +
                "        WHERE o.id = ?1"+
                "        GROUP BY product_id,p.name,b.quant" , nativeQuery = true)
        List<BoughtDTO> orderDetails(@Param("orderID") Long orderID);

        @Modifying
        @Transactional
        @Query(value = "insert into orders (date,price,user_id)values (?1,?2,?3)", nativeQuery = true)
        void saveOrders(@Param("date") LocalDate date,
                        @Param("price") Float price, @Param("user_id") Long user_id);
        @Query(value = "SELECT LAST_INSERT_ID();", nativeQuery = true)
        Long getLastOrderId();
        @Modifying
        @Transactional
        @Query(value = "insert into buy_product (quant,product_id,product_order_id)values (?1,?2,?3)", nativeQuery = true)
        void saveBuyProduct(@Param("quant") Integer quant,
                        @Param("product_id") Long product_id, @Param("product_order_id") Long product_order_id);

        @Query(value = " SELECT "+
                " o.date as orderDate, "+
                " o.price as orderPrice, "+
                " u.first_name as first_name, "+
                " u.last_name as last_name, "+
                " u.phone as phone, "+
                " u.addres as userAddres "+
                "        FROM orders o\n" +
                "        LEFT JOIN user u ON o.user_id = u.id\n" , nativeQuery = true)
        List<ProductDto> allOrder();
}
