package com.example.shop.repositories;
import com.example.shop.models.Category;
import com.example.shop.models.DTO.CategoryDTO;
import com.example.shop.models.DTO.ProductDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Modifying // для внесения изменений в бд
    @Transactional
    @Query(value = "insert into Category(name) values (?1)", nativeQuery = true)
    void saveCategory(@Param("name") String name);

//    @Modifying
//    @Transactional
//    @Query(value = "START TRANSACTION; " +
//            "UPDATE Products p " +
//            "SET p.category_id = (SELECT MIN(c.id) " +
//            "                     FROM Category c " +
//            "                     WHERE c.id <> ?1) " +
//            "WHERE p.category_id = ?1; " +
//            "DELETE FROM Category WHERE id = ?1; " +
//            "COMMIT;", nativeQuery = true)
//    void updateAndDeleteCategory(@Param("category_id") Long category_id);
    @Procedure(name = "UpdateAndDeleteCategory")
    void updateAndDeleteCategory(@Param("category_id_param") Long categoryId);

    @Query(value = "SELECT " +
            "category.name as categoryName, \n" +
            "category.id as categoryId \n" +
            "FROM category\n",
            nativeQuery = true)
    List<ProductDto> listCategory();

    @Query(value = "SELECT " +
            "products.name as productname, " +
            "products.price as productprice, " +
            "products.quant as productquant, " +
            "products.info as producinfo, " +
            "category.name as categoryname, " +
            "products.id as productId, " +
            "category.id as categoryId, " +
            "ROUND(AVG(Review.star), 2) as avgRewiewStar, " +
            "MAX(CASE WHEN product_image.count = 1 THEN product_image.url END) AS product_imageurl " +
            "FROM products " +
            "LEFT JOIN category ON products.category_id = category.id " +
            "LEFT JOIN review ON review.product_id = products.id " +
            "LEFT JOIN product_image ON product_image.product_id = products.id " +
            "WHERE products.is_deleted <> 1 " +
            "AND category.id = ?1 " +
            "GROUP BY products.id, products.name, products.price, products.quant, products.info, category.name", nativeQuery = true)
    List<ProductDto> listProductsByCategory(@Param("categoryId") Long categoryId);

}
