package com.github.boyvita.services.accounting.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class RabbitConfig {


	@Value("${rabbit.rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbit.rabbitmq.accountingQueue}")
	private String accountingQueue;

	@Value("${rabbit.rabbitmq.catalogQueue}")
	private String catalogQueue;

	@Value("${rabbit.rabbitmq.confirmationKey}")
	private String confirmationKey;

	@Value("${rabbit.rabbitmq.cancellingKey}")
	private String cancellingKey;

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Queue accountingQueue() {
		return new Queue(accountingQueue, false);
	}

	@Bean
	Queue catalogQueue() {
		return new Queue(catalogQueue, false);
	}

	@Bean
	Binding confirmationBinding(Queue accountingQueue, DirectExchange exchange) {
		return BindingBuilder.bind(accountingQueue).to(exchange).with(confirmationKey);
	}

	@Bean
	Binding cancellingCatalogBinding(Queue catalogQueue, DirectExchange exchange) {
		return BindingBuilder.bind(catalogQueue).to(exchange).with(cancellingKey);
	}

	@Bean
	Binding cancellingAccountingBinding(Queue accountingQueue, DirectExchange exchange) {
		return BindingBuilder.bind(accountingQueue).to(exchange).with(cancellingKey);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
