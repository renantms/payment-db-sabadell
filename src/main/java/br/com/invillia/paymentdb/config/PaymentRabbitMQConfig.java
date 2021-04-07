package br.com.invillia.paymentdb.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRabbitMQConfig {

    @Bean
    Queue createQueue(){
        return QueueBuilder.durable("payment")
                .withArgument("x-dead-letter-exchange", "deadLetterPaymentExchange")
                .withArgument("x-dead-letter-routing-key", "deadLetter").build();
    }

    @Bean
    Queue createDeadLetterQueue(){
        return QueueBuilder.durable("deadLetterPayment").build();
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("paymentExchange");
    }

    @Bean
    TopicExchange deadLetterExchange(){
        return new TopicExchange("deadLetterPaymentExchange");
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(createQueue()).to(exchange()).with("payment");
    }

    @Bean
    Binding DLQBinding(){
        return BindingBuilder.bind(createDeadLetterQueue()).to(deadLetterExchange()).with("deadLetter");
    }

    @Bean
    MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }


}
