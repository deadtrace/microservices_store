package com.github.boyvita.services.catalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PaymentInfo implements Serializable {
    @JsonProperty("is_confirmed")
    private Boolean isConfirmed;

    @JsonProperty("order_id")
    private Long orderId;

    public PaymentInfo(@JsonProperty("is_confirmed") Boolean isConfirmed, @JsonProperty("order_id") Long orderId) {
        this.isConfirmed = isConfirmed;
        this.orderId = orderId;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}


