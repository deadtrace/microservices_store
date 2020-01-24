package com.github.boyvita.services.accounting.controller;

import com.github.boyvita.services.accounting.exception.NoEntityException;
import com.github.boyvita.services.accounting.model.Client;
import com.github.boyvita.services.accounting.model.Order;
import com.github.boyvita.services.accounting.repo.ClientRepository;
import com.github.boyvita.services.accounting.repo.OrderRepository;
import org.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.*;

import static com.github.boyvita.services.accounting.util.JsonReader.readJsonArrayFromUrl;


@RestControllerAdvice
@RequestMapping
@ComponentScan(basePackages = "com.github.boyvita.services.accounting")
@EnableJpaRepositories(basePackages = "com.github.boyvita.services.accounting.repo")
@EntityScan(basePackages = "com.github.boyvita.services.accounting.model")
public class AccountingController {

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;

    @Autowired
    private RabbitMQReceiver rabbitMQReceiver;

    @Autowired
    public AccountingController(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }


    @GetMapping("/order")
    public List<Order> listOrder() {
        return orderRepository.findAll();
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable("id") Order order) {
        return order;
    }

    @PostMapping("/order")
    public Order addOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping("/order")
    public Order updateOrder(@RequestBody Order order) throws NoEntityException {
        Long id = order.getOrderId();
        Order orderFromDb = orderRepository.findById(id).orElseThrow(() -> new NoEntityException(id));
        BeanUtils.copyProperties(order, orderFromDb, "id");
        return orderRepository.save(orderFromDb);

    }

    @GetMapping("/client")
    public List<Client> listClient() {
        return clientRepository.findAll();
    }

    @GetMapping("/client/{id}")
    public Client getClient(@PathVariable("id") Client client) {
        return client;
    }

    @PostMapping("/client")
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/client")
    public Client updateClient(@RequestBody Client client) throws NoEntityException {
        Long id = client.getClientId();
        Client clientFromDb = clientRepository.findById(id).orElseThrow(() -> new NoEntityException(id));
        BeanUtils.copyProperties(client, clientFromDb, "id");
        return clientRepository.save(clientFromDb);

    }

    @DeleteMapping("/client/{id}")
    public Client deleteClient(@PathVariable("id") Client client) {
        clientRepository.delete(client);
        return client;
    }

    @GetMapping("/order/{id}/items")
    public ResponseEntity<Object> getOrderItems(@PathVariable("id") Order order) throws IOException {
        Long orderId = order.getOrderId();
        String requestUrl = "http://localhost:8762/catalog/item";
        JSONArray res = new JSONArray();
        JSONArray json = readJsonArrayFromUrl(requestUrl);
        System.out.println(json);
        for (int i = 0; i < json.length(); i++) {
            if (Objects.equals(orderId, json.getJSONObject(i).getLong("orderId"))) {
                res.put(json.getJSONObject(i));
            }
        }

        System.out.println(res.toString());
        return new ResponseEntity<>(res.toList(), HttpStatus.OK);
    }


    @GetMapping("/client/{id}/items")
    public ResponseEntity<Object> getClientItems(@PathVariable("id") Client client) throws IOException {

        String requestUrl = "http://localhost:8762/catalog/item";
        JSONArray res = new JSONArray();

        JSONArray json = readJsonArrayFromUrl(requestUrl);

        System.out.println(json);


        for (Order ord : orderRepository.findAll()) {
            if (Objects.equals(ord.getClientId(), client.getClientId())) {
                for (int i = 0; i < json.length(); i++) {
                    if (Objects.equals(ord.getOrderId(), json.getJSONObject(i).getLong("orderId"))) {
                        res.put(json.getJSONObject(i));
                    }
                }
            }
        }

        System.out.println(res.toString());
        return new ResponseEntity<>(res.toList(), HttpStatus.OK);
    }


}
