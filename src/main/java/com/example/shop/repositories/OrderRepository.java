package com.example.shop.repositories;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, Long>{

        @Query(value = " SELECT * \n" +
                "        FROM orders \n" +
                "        WHERE user_id = 1?", nativeQuery = true)
        Iterable <ProductDto> allOrders(@Param("userID") Long userID);

        @Query(value = " SELECT  p.name, b.quant, i.url\n" +
                "        FROM orders o\n" +
                "        JOIN buy_product b ON o.id = b.product_order_id\n" +
                "        JOIN products p ON p.id = b.product_id\n" +
                "        JOIN product_image i ON i.product_id = p.id\n" +
                "        WHERE o.id = ?1 and user_id = ?2 and i.count = 1 values (?1,?2)", nativeQuery = true)
        Iterable <ProductDto> orderDetails(@Param("orderID") Long orderID,@Param("userID") Long userID);

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
