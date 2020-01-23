package com.github.boyvita.services.catalog.model;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "catalog", name = "item")
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
public class Item implements Serializable  {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="quantity")
    private Integer itemQuantity;

    @Column(name="order_id")
    private Long orderId;

    @Column(name="product_id")
    private Long productId;

    public Item(Long productId, Long orderId) {
        this.productId = productId;
	this.orderId = orderId;
        this.itemQuantity = 1;
    }

    public Item() {
        this.itemQuantity = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
