package com.microservices.orderservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name.order}")
    private String orderQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key.order}")
    private String orderRoutingKey;

    @Value("${rabbitmq.queue.name.email}")
    private String emailQueueName;

    @Value("${rabbitmq.routing.key.email}")
    private String emailRoutingKey;

    /*
    * spring bean for order queue
    * */
    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueueName);
    }

    /*
     * spring bean for email queue
     * */
    @Bean
    public Queue emailQueue() {
        return new Queue(emailQueueName);
    }

    /*
    * spring bean for exchange
    * */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /*
    * spring bean for binding order between exchange and queue using routing key
    * */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(orderQueue()).to(exchange()).with(orderRoutingKey);
    }

    /*
     * spring bean for binding email between exchange and queue using routing key
     * */
    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(exchange()).with(emailRoutingKey);
    }

    /*
    * message converter
    * */
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    /*
    * configure RabbitTemplate
    * */
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
