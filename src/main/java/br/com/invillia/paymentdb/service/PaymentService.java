package br.com.invillia.paymentdb.service;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.dto.PaymentMapper;
import br.com.invillia.paymentdb.model.Payment;
import br.com.invillia.paymentdb.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentDto> getPayment(String name, int page, int size) {
        Page<Payment> paymentPage;

        Pageable pageable = PageRequest.of(page, size);

        if(name != null){
            paymentPage = paymentRepository.findByName(name, pageable);
        } else{
            paymentPage = paymentRepository.findAll(pageable);
        }

        if(paymentPage.isEmpty()){
            return List.of();
        }

        return paymentPage.map(PaymentMapper.INSTANCE::paymentToPaymentDto).toList();
    }

    public void savePayment(PaymentDto paymentDto){
        try{
            paymentRepository.save(PaymentMapper.INSTANCE.paymentDtoToPayment(paymentDto));
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
