package br.com.invillia.paymentdb.service;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.dto.PaymentMapper;
import br.com.invillia.paymentdb.model.Payment;
import br.com.invillia.paymentdb.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentDto> getPayment(String name) {
        List<Payment> paymentList = paymentRepository.findByName(name);

        return paymentList.stream().map(PaymentMapper.INSTANCE::paymentToPaymentDto).collect(Collectors.toList());
    }

}
