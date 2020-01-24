package com.github.boyvita.services.payment.controller;

import com.github.boyvita.services.payment.model.PaymentInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${rabbit.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${rabbit.rabbitmq.cancellingKey}")
	private String cancellingKey;

	@Value("${rabbit.rabbitmq.confirmationKey}")
	private String confirmationKey;


	public void sendConfirmation(Long orderId) {
		rabbitTemplate.convertAndSend(exchange, confirmationKey, new PaymentInfo(new Boolean(true), orderId));
	}
	public void sendCancelling(Long orderId) {
		rabbitTemplate.convertAndSend(exchange, cancellingKey, new PaymentInfo(new Boolean(false), orderId));
	}
}