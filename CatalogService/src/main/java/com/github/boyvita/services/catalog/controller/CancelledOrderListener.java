package com.github.boyvita.services.catalog.controller;

import com.github.boyvita.services.accounting.exception.NoEntityException;
import com.github.boyvita.services.catalog.model.Item;
import com.github.boyvita.services.catalog.model.Product;
import com.github.boyvita.services.catalog.repo.ItemRepository;
import com.github.boyvita.services.catalog.repo.ProductRepository; 
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@ComponentScan(basePackages = "com.github.boyvita.services.catalog")
public class CancelledOrderListener {

    private final RabbitTemplate amqpTemplateReceiver;

    @Value("${rabbit.rabbitmq.queueAccount}")
    private String queue;

    @Value("${rabbit.rabbitmq.routingKeyAccount}")
    private String routingkey;

    @Autowired
    private ItemRepository itemRepository;
    private ProductRepository productRepository;

    public RabbitMQReceiver(RabbitTemplate amqpTemplateReceiver ) {
        this.amqpTemplateReceiver = amqpTemplateReceiver;
    }

    @RabbitListener(queues = "${rabbit.rabbitmq.queueAccount}")
    public void receivePaidOrder(Long orderId) throws NoEntityException {

    }


}
