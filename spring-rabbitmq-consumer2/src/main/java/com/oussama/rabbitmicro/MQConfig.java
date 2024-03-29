package com.oussama.rabbitmicro;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String QUEUE2 = "2ite_micro_message_queue2";
    public static final String EXCHANGE2 = "2ite_micro_message_exchange";
    public static final String ROUTING_KEY2 = "message_routingKey2";

    @Bean
    public Queue queue1() {
        return  new Queue(QUEUE2);
    }
    
  
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE2);
    }

    @Bean
    public Binding binding1(Queue queue2, TopicExchange exchange2) {
        return BindingBuilder
                .bind(queue2)
                .to(exchange2)
                .with(ROUTING_KEY2);
    }
    
  

    @Bean
    public MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }

}
