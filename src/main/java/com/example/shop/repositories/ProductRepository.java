package com.example.shop.repositories;
import com.example.shop.models.DTO.ProductDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shop.models.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT \n" +
            "products.name as productname,\n" +
            "price as productprice,\n" +
            "quant as productquant,\n" +
            "info as producinfo,\n" +
            "category.name as categoryname,\n" +
            "products.id as productId, \n" +
            "ROUND(AVG(Review.star),2)  as avgRewiewStar, \n" +
            "MAX(CASE WHEN product_image.count = 1 THEN product_image.url END) AS product_imageurl\n" +
            "FROM products\n" +
            "LEFT join category on products.category_id = category.id\n" +
            "LEFT join review on review.product_id = products.id\n" +
            "LEFT join product_image on product_image.product_id = products.id\n" +
            "group by products.id, products.name, products.price, products.quant, products.info, category.name\n",
            nativeQuery = true)
    List <ProductDto> listProducts();

    @Query(value = "SELECT \n" +
            "products.name as productname,\n" +
            "price as productprice,\n" +
            "quant as productquant,\n" +
            "info as producinfo,\n" +
            "products.id as productId,\n" +
            "product_image.url as product_imageurl,\n" +
            "product_image.count as product_imagecount\n" +
            "FROM products\n" +
            "left join product_image on product_image.product_id = products.id\n" +
            "WHERE products.id = ?1 \n" , nativeQuery = true)
    List <ProductDto> cardProduct(@Param("id") Long newID);
    @Query(value = "SELECT \n" +
            "ROUND(AVG(Review.star),2)  as avgRewiewStar \n" +
            "FROM products\n" +
            "join review on review.product_id = products.id\n" +
            "WHERE products.id = ?1 \n" , nativeQuery = true)
    List <ProductDto> awgReviewStar(@Param("id") Long newID);

    @Query(value = "SELECT \n" +
            "review.star as rewiewstar,\n" +
            "review.date AS date,\n" +
            "review.text AS text,\n" +
            "user.first_name AS username,\n" +
            "user.sur_name AS usersur\n" +
            "FROM review\n" +
            "left join products on review.product_id = products.id\n" +
            "left join user ON review.user_id = user.id\n" +
            "WHERE products.id = ?1 \n" , nativeQuery = true)
    List <ProductDto> ReviewStar(@Param("id") Long newID);

    @Query(value = "SELECT * from products WHERE id=?1", nativeQuery = true)
    Product findByProductId(@Param("id") Long id);

    @Modifying // для внесения изменений в бд
    @Transactional
    @Query(value = "insert into products(info,name,price,quant,category_id) values (?1,?2,?3,?4,?5)", nativeQuery = true)
    void saveProduct(@Param("info") String info, @Param("name") String name, @Param("price") Float price,
                    @Param("quant") Long quant,@Param("category_id") Long category_id);

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Review\n" +
//    "WHERE Review.product_id\n" +
//    "IN (SELECT products.id FROM products\n" +
//    "WHERE products.name = :productName)\n")
//    void deleteProductAndReviewsByProductName(@Param("productName")String productName);
//

  //  @Modifying
  //  @Query("DELETE FROM Review WHERE id = ?1")
 //   void deleteProductById(@Param("id") Long id);

//    @Transactional
//    @Modifying
//    @Query("UPDATE Product p " +
//            "SET p.info = ?info, " +
//            "    p.name = ?name, " +
//            "    p.price = ?price, " +
//            "    p.quant = ?quant, " +
//            "    p.category.id = ?categoryId " +
//            "WHERE p.id = ?productId")
//    void updateProductInfo(
//            @Param("info") String info,
//            @Param("name") String name,
//            @Param("price") Float price,
//            @Param("quantity") Integer quant,
//            @Param("categoryId") Long categoryId,
//            @Param("productId") Long productId
//    );
}
