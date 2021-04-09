package br.com.invillia.paymentdb.listener;

import br.com.invillia.paymentdb.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ListenerPaymentDLQRabbitMQ {

    @RabbitListener(queues = "paymentDLQ")
    public void sendToDLQ(PaymentDto paymentDto){
        log.warn("Sending message to DLQ, paymentDto={}", paymentDto);
    }

}
