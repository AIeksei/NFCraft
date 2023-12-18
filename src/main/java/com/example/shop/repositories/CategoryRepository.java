package com.example.shop.repositories;
import com.example.shop.models.Category;
import com.example.shop.models.DTO.CategoryDTO;
import com.example.shop.models.DTO.ProductDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Modifying // для внесения изменений в бд
    @Transactional
    @Query(value = "insert into Category(name) values (?1)", nativeQuery = true)
    void saveCategory(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "START TRANSACTION; " +
            "UPDATE Products p " +
            "SET p.category_id = (SELECT MIN(c.id) " +
            "                     FROM Category c " +
            "                     WHERE c.id <> ?1) " +
            "WHERE p.category_id = ?1; " +
            "DELETE FROM Category WHERE id = ?1; " +
            "COMMIT;", nativeQuery = true)
    void updateAndDeleteCategory(@Param("category_id") Long category_id);
    @Query(value = "SELECT " +
            "category.name as categoryName, \n" +
            "category.id as categoryId \n" +
            "FROM category\n",
            nativeQuery = true)
    List<ProductDto> listCategory();

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Review WHERE Review.product_id IN (SELECT products.id FROM products  WHERE products.name = :productName)")
//    void deleteProductAndReviewsByProductName(@Param("productName")String productName);

}
