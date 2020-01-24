package com.github.boyvita.services.accounting.controller;    

import com.github.boyvita.services.accounting.model.ItemMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;    
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.beans.factory.annotation.Value;    
import org.springframework.stereotype.Service; 

@Service
public class NewOrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbit.rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbit.rabbitmq.routingKeyAccount}")
    private String routingKey;

    public void send(Long orderId, Long quantity, Long productId) {
        ItemMessage message = new ItemMessage(orderId, quantity, productId);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
