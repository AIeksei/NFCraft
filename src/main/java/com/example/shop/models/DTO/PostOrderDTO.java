package com.example.shop.models.DTO;

import com.example.shop.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PostOrderDTO {
    private List<PostCartDTO> cartRequest;
    private  Long user_id;
    private Float price;

}
