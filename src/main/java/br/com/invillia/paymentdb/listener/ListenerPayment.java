package br.com.invillia.paymentdb.listener;

import br.com.invillia.paymentdb.domain.request.PaymentRequest;
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
    public void listen(PaymentRequest paymentRequest){
        log.info("Saving payment - Kafka, paymentRequest={}", paymentRequest);
        try{
            paymentService.savePayment(paymentRequest);
            log.info("Save successful - Kafka, paymentRequest={}", paymentRequest);
        }catch(RuntimeException e){
            log.error("Payment not saved - Kafka, paymentRequest={}", paymentRequest);
            log.info("Allocating message to RabbitMQ, paymentRequest={}", paymentRequest);
            rabbitTemplate.convertAndSend("paymentExchange", "payment", paymentRequest);
        }
    }

}
