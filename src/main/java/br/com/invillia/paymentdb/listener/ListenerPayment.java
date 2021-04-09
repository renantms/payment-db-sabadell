package br.com.invillia.paymentdb.listener;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ListenerPayment {

    private PaymentService paymentService;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ListenerPayment(PaymentService paymentService, RabbitTemplate rabbitTemplate){
        this.paymentService = paymentService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @KafkaListener(topics = "payment3", groupId = "teste")
    public void listen(PaymentDto paymentDto){
        log.info("Saving payment - Kafka, paymentDto={}", paymentDto);
        try{
            paymentService.savePayment(paymentDto);
            log.info("Save successful - Kafka, paymentDto={}", paymentDto);
        }catch(RuntimeException e){
            log.error("Payment not saved - Kafka, paymentDto={}", paymentDto);
            log.info("Allocating message to RabbitMQ, paymentDto={}", paymentDto);
            rabbitTemplate.convertAndSend("paymentExchange", "payment", paymentDto);
        }
    }

}
