package com.github.boyvita.services.payment.controller;

import com.netflix.discovery.EurekaClient;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequestMapping
@ComponentScan(basePackages = "com.github.boyvita.services")
public class PaymentController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @PostMapping("/confirm")
    public Long payOrderById(@RequestBody Long orderId) {
        rabbitMQSender.sendConfirmation(orderId);
        return orderId;
    }

    @PostMapping("/cancel")
    public Long cancelOrderById(@RequestBody Long orderId) {
        rabbitMQSender.sendCancelling(orderId);
        return orderId;
    }


}
