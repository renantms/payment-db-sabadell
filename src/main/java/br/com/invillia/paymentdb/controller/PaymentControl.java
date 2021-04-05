package br.com.invillia.paymentdb.controller;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentControl {

    private PaymentService paymentService;

    @Autowired
    public PaymentControl(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/v1/{name}")
    public Optional<PaymentDto> getPayment(@PathVariable String name){
        return paymentService.getPayment(name);
    }

    @PostMapping("/v1")
    public Optional<PaymentDto> postPayment(PaymentDto paymentDto) {
        return paymentService.postPayment(paymentDto);
    }
}
