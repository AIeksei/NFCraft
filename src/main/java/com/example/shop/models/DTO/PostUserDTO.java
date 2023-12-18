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
public class PostUserDTO {
    private  String addres;
    private  String father_name;
    private  String first_name;
    private  String phone;
    private  String sur_name;
    private  Long login_id;
}
