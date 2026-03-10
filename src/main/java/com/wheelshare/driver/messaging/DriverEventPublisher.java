package com.wheelshare.driver.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.wheelshare.driver.config.RabbitMQConfig;
import com.wheelshare.driver.event.DriverEvent;

import lombok.RequiredArgsConstructor;

@Component
public class DriverEventPublisher {
	private final RabbitTemplate rabbitTemplate;

	public DriverEventPublisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void publish(DriverEvent event) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.DRIVER_EXCHANGE,
				event.getType(),
				event);
	}

}
