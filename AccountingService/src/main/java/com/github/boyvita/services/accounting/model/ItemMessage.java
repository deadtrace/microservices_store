package com.github.boyvita.services.accounting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public final class CustomMessage implements Serializable {

    private final long orderId;
    private final int quantity;
    private final long productId;

    public CustomMessage(@JsonProperty("orderId") long orderId,
                         @JsonProperty("quantity") int quantity,
                         @JsonProperty("productId") long productId) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public long productId() {
        return productId;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "orderId='" + orderId + '\'' +
                ", quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }
}
