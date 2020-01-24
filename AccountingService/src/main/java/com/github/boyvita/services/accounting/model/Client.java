package com.github.boyvita.services.accounting.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "accounting", name = "client")
@ToString(of = {"client_id"})
@EqualsAndHashCode(of = {"client_id"})
public class Client implements Serializable {
    @Id
    @Column(name="client_id")
    private Long clientId;

    @Column(name = "name")
    private String name;

    public Client() {
    }
    public Client(String name) {
        this.name = name;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
