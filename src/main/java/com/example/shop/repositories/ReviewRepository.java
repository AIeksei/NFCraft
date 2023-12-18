package com.example.shop.repositories;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.models.Login;
import com.example.shop.models.Product;
import com.example.shop.models.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Modifying // для внесения изменений в бд
    @Transactional
    @Query(value = "insert into Review(date,text,star,product_id,user_id)" +
            " values (:#{#review.date},:#{#review.text},:#{#review.star}," +
            ":#{#review.product_id},:#{#review.user_id})", nativeQuery = true)
    void saveReview(Review review);

    @Modifying
    @Transactional
    @Query(value = "insert into Review (date,star,text,product_id,user_id)values (?1,?2,?3,?4,?5)", nativeQuery = true)
    void saveReview2(@Param("date") LocalDate date,@Param("star") Integer star,@Param("text") String text,@Param("product_id") Long product_id,@Param("user_id") Long user_id);


    @Query(value = "SELECT \n" +
            "review.star as rewiewstar,\n" +
            "review.date AS date,\n" +
            "review.text AS text,\n" +
            "user.first_name AS username,\n" +
            "user.sur_name AS usersur\n" +
            "FROM review\n" +
            "left join user ON review.user_id = user.id", nativeQuery = true)
    List<ProductDto> ReviewAllStar();




    @Modifying
    @Query("DELETE FROM Review WHERE id = ?1")
    void deleteReviewById(@Param("id") Long id);
}
