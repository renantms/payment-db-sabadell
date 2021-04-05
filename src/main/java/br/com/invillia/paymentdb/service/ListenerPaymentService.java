package br.com.invillia.paymentdb.service;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerPaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public ListenerPaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @KafkaListener(topics = "payment")
    public void listen(PaymentDto paymentDto){
        paymentRepository.save(paymentDto.convert());
    }

}
