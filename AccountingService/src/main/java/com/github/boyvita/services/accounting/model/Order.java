package com.github.boyvita.services.accounting.model;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "accounting", name = "order")
@ToString(of = {"order_id"})
@EqualsAndHashCode(of = {"order_id"})
public class Order implements Serializable {
    @Id
    @Column(name="order_id")
    private Long orderId;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @Column(name = "client_id")
    private Long clientId;

    public Order(Long clientId) {
        this.clientId = clientId;
        this.orderStatus = OrderStatus.COLLECTING;
    }

    public Order() {
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
