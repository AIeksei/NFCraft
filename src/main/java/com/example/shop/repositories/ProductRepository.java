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
            "GROUP BY products.id, products.name, products.price, products.quant, products.info, category.name", nativeQuery = true)
    List<ProductDto> listProducts();

    @Query(value = "SELECT " +
            "products.quant as productquant " +
            "FROM products " +
            "WHERE products.is_deleted <> 1 and products.id = ?1", nativeQuery = true)
    Long countProducts(@Param("product_id") Long product_id);

    @Query(value = "SELECT \n" +
            "products.name as productname,\n" +
            "price as productprice,\n" +
            "quant as productquant,\n" +
            "info as producinfo,\n" +
            "products.id as productId,\n" +
            "product_image.url as product_imageurl,\n" +
            "product_image.count as product_imagecount,\n" +
            "category.id as categoryId \n" +
            "FROM products\n" +
            "LEFT join category on products.category_id = category.id\n" +
            "left join product_image on product_image.product_id = products.id\n" +
            "WHERE products.id = ?1 and products.is_deleted <> 1 \n" , nativeQuery = true)
    List <ProductDto> cardProduct(@Param("id") Long newID);

    @Query(value = "SELECT \n" +
            "count as count, \n" +
            "url as url \n" +
            "FROM product_image\n"+
            "where product_id = ?1 \n", nativeQuery = true)
    List <ProductDto> allImage(@Param("id") Long productId);

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

    @Modifying
    @Transactional
    @Query(value = "insert into products(info,name,price,quant,category_id,is_deleted) values (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void saveProduct(@Param("info") String info, @Param("name") String name, @Param("price") Float price,
                    @Param("quant") Long quant,@Param("category_id") Long category_id,@Param("is_deleted") Boolean is_deleted);

  @Modifying
  @Transactional
  @Query("UPDATE Product p SET p.isDeleted = true WHERE p.id = ?1")
  void updateProductDeletedStatus(@Param("productId") long productId);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.quant = ?1 WHERE p.id = ?2")
    void changeCount(@Param("quant") Long quant, @Param("product_id") Long product_id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p " +
            "SET p.info = ?1, " +
            "    p.name = ?2, " +
            "    p.price = ?3, " +
            "    p.quant = ?4, " +
            "    p.category.id = ?5 " +
            "WHERE p.id = ?6")
    void updateProductInfo(
            @Param("info") String info,
            @Param("name") String name,
            @Param("price") Float price,
            @Param("quantity") Long quant,
            @Param("categoryId") Long categoryId,
            @Param("productId") Long productId
    );

    @Modifying
    @Transactional
    @Query(value = "insert into product_image (count,url,product_id) values (?1,?2,?3)", nativeQuery = true)
    void saveImage(@Param("count") Integer count, @Param("url") String url, @Param("product_id") Long product_id);
}
