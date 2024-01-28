package com.microservices.stockservice.consumer;

import com.microservices.stockservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event received => %s", orderEvent.toString()));

        // save order event data in database
    }
}
