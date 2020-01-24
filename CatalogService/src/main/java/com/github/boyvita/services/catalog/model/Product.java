package com.github.boyvita.services.catalog.model;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "catalog", name = "product")
@ToString(of = {"product_id"})
@EqualsAndHashCode(of = {"product_id"})
public class Product implements Serializable {
    @Id
    @Column(name="product_id")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Long quantity;

    public Product() {
    }

    public Product(String name, Double cost, String description, Long quantity) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.quantity = quantity;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    public void SetQuantity(Long quantity) {
    	this.quantity = quantity;
    }
}
