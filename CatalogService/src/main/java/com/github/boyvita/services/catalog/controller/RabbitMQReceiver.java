package com.github.boyvita.services.catalog.controller;

import com.github.boyvita.services.catalog.exception.NoEntityException;
import com.github.boyvita.services.catalog.model.Item;
import com.github.boyvita.services.catalog.model.PaymentInfo;
import com.github.boyvita.services.catalog.model.Product;
import com.github.boyvita.services.catalog.repo.ItemRepository;
import com.github.boyvita.services.catalog.repo.ProductRepository;
import javafx.util.Pair;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@ComponentScan(basePackages = "com.github.boyvita.services.catalog")
public class RabbitMQReceiver {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RabbitTemplate amqpTemplateReceiver;

    @RabbitListener(queues = "${rabbit.rabbitmq.catalogQueue}")
    public void receivePaymentInfo(PaymentInfo paymentInfo) throws NoEntityException {
        Long orderId = paymentInfo.getOrderId();
        System.out.println(orderId.toString());
        for (Item it: itemRepository.findAll()){
            if (it.getOrderId() == orderId) {
                Long productId = it.getOrderId();
                Product product = productRepository.findById(productId).orElseThrow(() -> new NoEntityException(productId));
                product.setQuantity(product.getQuantity() + it.getItemQuantity());
                productRepository.save(product);
            }
        }
        System.out.println("canceled order" + orderId.toString());
    }
}