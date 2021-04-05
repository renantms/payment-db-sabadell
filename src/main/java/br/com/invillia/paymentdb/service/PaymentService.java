package br.com.invillia.paymentdb.service;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.model.Payment;
import br.com.invillia.paymentdb.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Optional<PaymentDto> getPayment(String name) {
        Optional<Payment> paymentOptional = paymentRepository.findByName(name);

        if(paymentOptional.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(new PaymentDto(paymentOptional.get()));
    }

    public Optional<PaymentDto> postPayment(PaymentDto paymentDto) {
        return Optional.of(new PaymentDto(paymentRepository.save(paymentDto.convert())));
    }
}
