package com.github.boyvita.services.catalog.model;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "catalog", name = "product")
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
public class Product implements Serializable {
    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;

    public Product(String name, Double cost, String description, Integer quantity) {
        this.name = name;
        this.cost = cost;
        this.description = description;
	this.quantity = quantity;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getQuantity() {
    	return quantity;
    }

    public void SetQuantity(Integer quantity) {
    	this.quantity = quantity;
    }
}
