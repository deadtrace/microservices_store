package com.github.boyvita.services.catalog.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public final class ItemMessage implements Serializable {

    private final Long orderId;
    private final Long quantity;
    private final Long productId;

    public ItemMessage(@JsonProperty("orderId") Long orderId,
                       @JsonProperty("quantity") Long quantity,
                       @JsonProperty("productId") Long productId) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ItemMessage{" +
                "orderId='" + orderId + '\'' +
                ", quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }
}
