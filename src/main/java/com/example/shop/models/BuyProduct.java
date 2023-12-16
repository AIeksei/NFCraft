package com.example.shop.models;
import jakarta.persistence.*;
@Entity
@Table(name = "buyProduct")
public class BuyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "product_order_id")
    private Order productOrder;

    @Column
    private Integer quant;

    public BuyProduct() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return productOrder;
    }

    public void setOrder(Order productOrder) {
        this.productOrder = productOrder;
    }


    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }
}

