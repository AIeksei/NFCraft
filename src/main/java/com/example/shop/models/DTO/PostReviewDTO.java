package com.example.shop.models.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PostReviewDTO {
    private Integer star;
    private String text;
    private Long product_id;
    private Long user_id;

}
