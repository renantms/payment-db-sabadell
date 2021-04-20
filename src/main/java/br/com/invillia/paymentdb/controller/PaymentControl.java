package br.com.invillia.paymentdb.controller;

import br.com.invillia.paymentdb.domain.response.PaymentResponse;
import br.com.invillia.paymentdb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentControl {

    private PaymentService paymentService;

    @Autowired
    public PaymentControl(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentResponse> getPayment(@RequestParam(required = false) String name,
                                            @RequestParam(required = false, defaultValue = "0") int page,
                                            @RequestParam(required = false, defaultValue = "5") int size){
        return paymentService.getPayment(name, page, size);
    }
}
