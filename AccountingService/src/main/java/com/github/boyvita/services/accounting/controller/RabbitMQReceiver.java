package com.github.boyvita.services.accounting.controller;

import com.github.boyvita.services.accounting.exception.NoEntityException;
import com.github.boyvita.services.accounting.model.Order;
import com.github.boyvita.services.accounting.model.OrderStatus;
import com.github.boyvita.services.accounting.model.PaymentInfo;
import com.github.boyvita.services.accounting.repo.OrderRepository;
import javafx.util.Pair;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@ComponentScan(basePackages = "com.github.boyvita.services.accounting")
public class RabbitMQReceiver {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate amqpTemplateReceiver;

    @RabbitListener(queues = "${rabbit.rabbitmq.accountingQueue}")
    public void receivePaymentInfo(PaymentInfo paymentInfo) throws NoEntityException {
        Boolean isConfirmed = paymentInfo.getConfirmed();
        Long orderId = paymentInfo.getOrderId();
        if (isConfirmed) {
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityException(orderId));
            order.setOrderStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }
        else {
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityException(orderId));
            order.setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
        }
        System.out.println("canceled order" + orderId.toString());
    }
}