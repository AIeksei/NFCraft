package com.example.shop.repositories;
import com.example.shop.models.DTO.ProductDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository {

    @Modifying // для внесения изменений в бд
    @Transactional
    @Query(value = "insert into Review(text,star,product_id,user_id) values (?1,?2,?3,?4)", nativeQuery = true)
    void saveReview(@Param("text") String text, @Param("star") Float star, @Param("product_id") Long product,
                    @Param("user_id") Long user_id);

    @Query(value = "SELECT \n" +
            "review.star as rewiewstar,\n" +
            "review.date AS date,\n" +
            "review.text AS text,\n" +
            "review_image.url AS reviewurl,\n" +
            "user.first_name AS username,\n" +
            "user.sur_name AS usersur\n" +
            "FROM review\n" +
            "join review_image ON review_image.id = review.id\n" +
            "join user ON review.user_id = user.id", nativeQuery = true)
    List<ProductDto> ReviewAllStar();

    @Transactional
    @Modifying
    @Query("DELETE FROM review "+
           "WHERE id = :1?")
    void deleteReviewByID(@Param("id") Long id);
}
