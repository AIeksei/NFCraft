package com.example.shop.repositories;
import com.example.shop.models.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Modifying // для внесения изменений в бд
    @Transactional
    @Query(value = "insert into Category(name) values (?1)", nativeQuery = true)
    void saveCategory(@Param("name") String name);

//@Modifying
//@Transactional
//@Query(value = "UPDATE Product p " +
//        "SET p.category.id = (SELECT MIN(c.id) " +
//        "FROM Category c " +
//        "WHERE c.id <> ?1) " +
//        "WHERE p.category.id = ?1")
//void updateProductCategoryAndDeleteCategory(@Param("categoryId") Long categoryId);
//
//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM Category c " +
//            "WHERE c.id = ?1")
//    void deleteCategoryById(@Param("categoryId") Long categoryId);

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Review WHERE Review.product_id IN (SELECT products.id FROM products  WHERE products.name = :productName)")
//    void deleteProductAndReviewsByProductName(@Param("productName")String productName);

}
