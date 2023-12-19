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

        @Query(value = " SELECT p.id as product_id, p.name, b.quant, " +
                "MAX(CASE WHEN i.count = 1 THEN i.url END) AS url\n" +
                "        FROM orders o\n" +
                "        LEFT JOIN buy_product b ON o.id = b.product_order_id\n" +
                "        LEFT JOIN products p ON p.id = b.product_id\n" +
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

//        START TRANSACTION;
//-- Блокировка строк для чтения и обновления
//        SELECT * FROM products WHERE id = 1 AND quant >= 1 FOR UPDATE;
//-- Если товар есть, создаем заказ
//        INSERT INTO orders (date, price, user_id)
//        VALUES (
//                CASE
//                        WHEN (SELECT COUNT(*) FROM products WHERE id = 1 AND quant >= 1) > 0 THEN NOW()
//        ELSE '1000-01-01 00:00:00'
//        END,
//                12,
//                1
//                );
//
//        SET @product_order_id = LAST_INSERT_ID(); -- Получаем ID последней вставленной записи в orders
//
//-- Создаем покупку товара в этом заказе
//        INSERT INTO buy_product (quant, product_id, product_order_id)
//        SELECT 1, 1, @product_order_id
//        WHERE (SELECT COUNT(*) FROM products WHERE id = 1 AND quant >= 1) > 0;
//-- Уменьшаем количество товара в таблице products
//        UPDATE products
//        SET quant = quant - 1
//        WHERE id = 1 AND quant >= 1;
//
//        COMMIT;


}
