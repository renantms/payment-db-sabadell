package br.com.invillia.paymentdb.listener;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.repository.PaymentRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListenerPaymentRabbitMQ {

    private PaymentRepository paymentRepository;

    @Autowired
    public ListenerPaymentRabbitMQ(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @RabbitListener(queues = "payment")
    public void savePayment(PaymentDto paymentDto) throws Exception{
        paymentRepository.save(paymentDto.convert());
    }

}
