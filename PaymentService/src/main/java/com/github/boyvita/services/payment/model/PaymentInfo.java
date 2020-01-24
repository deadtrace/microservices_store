package com.github.boyvita.services.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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


