package com.example.shop.models;


import jakarta.persistence.*;
@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String password;

    @Column
    private Long number;

    @Override
    public String toString() {
        return (number + " " + id);
    }
}
