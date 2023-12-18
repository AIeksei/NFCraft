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
public class PostProductDTO {
    private  String info;
    private  String name;
    private  Float price;
    private  Long quant;
    private  Long category_id;
    private  String category_name;
}
