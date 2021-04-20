package br.com.invillia.paymentdb.service;

import br.com.invillia.paymentdb.domain.request.PaymentRequest;
import br.com.invillia.paymentdb.domain.response.PaymentResponse;
import br.com.invillia.paymentdb.domain.PaymentMapper;
import br.com.invillia.paymentdb.exception.SaveFailedException;
import br.com.invillia.paymentdb.model.Payment;
import br.com.invillia.paymentdb.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentResponse> getPayment(String name, int page, int size) {
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

        return paymentPage.map(PaymentMapper.INSTANCE::paymentToPaymentResponse).toList();
    }

    public void savePayment(PaymentRequest paymentRequest){
        try{
            paymentRepository.save(PaymentMapper.INSTANCE.paymentRequestToPayment(paymentRequest));
        } catch(Exception e){
            throw new SaveFailedException(paymentRequest);
        }
    }

}
