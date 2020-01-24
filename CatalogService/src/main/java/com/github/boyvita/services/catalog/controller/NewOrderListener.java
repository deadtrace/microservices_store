package com.github.boyvita.services.catalog.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@ComponentScan(basePackages = "com.github.boyvita.services.catalog")
public class NewOrderListener {

//    @Autowired
//    private RabbitTemplate amqpTemplateReceiver;
//
//    @Value("${rabbit.rabbitmq.queueAccount}")
//    private String queue;
//
//    @Value("${rabbit.rabbitmq.routingKeyAccount}")
//    private String routingkey;
//
//    @Autowired
//    private ItemRepository itemRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @RabbitListener(queues = "${rabbit.rabbitmq.queueCatalog}")
//    public void receiveMessage(ItemMessage itemMessage) {
//        System.out.println(itemMessage.toString());
//    }
}
