package com.github.boyvita.services.accounting.controller;    

import com.github.boyvita.services.accounting.model.ItemMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate;    
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.beans.factory.annotation.Value;    
import org.springframework.stereotype.Service; 

@Service
public class NewOrderSender {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(long orderId, int quantity, long productId) {
        final var message = new ItemMessage(orderId, quantity, productId);
        rabbitTemplate.convertAndSend(MessagingApplication.EXCHANGE_NAME, MessagingApplication.ROUTING_KEY, message);
    }
}
