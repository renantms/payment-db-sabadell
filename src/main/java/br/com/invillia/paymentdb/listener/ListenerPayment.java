package br.com.invillia.paymentdb.listener;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.dto.PaymentMapper;
import br.com.invillia.paymentdb.repository.PaymentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerPayment {

    private PaymentRepository paymentRepository;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ListenerPayment(PaymentRepository paymentRepository, RabbitTemplate rabbitTemplate){
        this.paymentRepository = paymentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @KafkaListener(topics = "payment3", groupId = "teste")
    public void listen(PaymentDto paymentDto){
        try{
            paymentRepository.save(PaymentMapper.INSTANCE.paymentDtoToPayment(paymentDto));
        } catch(Exception e){
            rabbitTemplate.convertAndSend("paymentExchange", "payment", paymentDto);
        }

    }

}
