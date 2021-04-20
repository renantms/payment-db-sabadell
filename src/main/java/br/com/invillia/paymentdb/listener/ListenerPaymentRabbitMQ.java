package br.com.invillia.paymentdb.listener;

import br.com.invillia.paymentdb.domain.request.PaymentRequest;
import br.com.invillia.paymentdb.exception.SaveFailedException;
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

    @RabbitListener(queues = "paymentQueue")
    public void savePayment(PaymentRequest paymentRequest) throws Exception {
        log.info("Saving payment - RabbitMQ, paymentRequest={}", paymentRequest);
        try{
            paymentService.savePayment(paymentRequest);
            log.info("Save successful - RabbitMQ, paymentRequest={}", paymentRequest);
        }catch(SaveFailedException e){
            log.error("Payment not saved - RabbitMQ, paymentRequest={}", paymentRequest);
            throw new SaveFailedException(paymentRequest);
        }
    }

}
