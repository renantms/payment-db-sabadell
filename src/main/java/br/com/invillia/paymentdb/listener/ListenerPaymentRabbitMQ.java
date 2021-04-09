package br.com.invillia.paymentdb.listener;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ListenerPaymentRabbitMQ {

    private PaymentService paymentService;

    @Autowired
    public ListenerPaymentRabbitMQ(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @RabbitListener(queues = "payment")
    public void savePayment(PaymentDto paymentDto) throws Exception {
        log.info("Saving payment - RabbitMQ, paymentDto={}", paymentDto);
        try{
            paymentService.savePayment(paymentDto);
            log.info("Save successful - RabbitMQ, paymentDto={}", paymentDto);
        }catch(RuntimeException e){
            log.error("Payment not saved - RabbitMQ, paymentDto={}", paymentDto);
            throw new RuntimeException();
        }
    }

}
