package com.github.boyvita.services.catalog.model;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "catalog", name = "item")
@ToString(of = {"item_id"})
@EqualsAndHashCode(of = {"item_id"})
public class Item implements Serializable  {
    @Id
    @Column(name="item_id")
    private Long itemId;

    @Column(name="quantity")
    private Long quantity;

    @Column(name="order_id")
    private Long orderId;

    @Column(name="product_id")
    private Long productId;

    public Item() {

    }

    public Item(Long itemQuantity, Long orderId, Long productId) {
        this.quantity = itemQuantity;
        this.orderId = orderId;
        this.productId = productId;
    }


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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
