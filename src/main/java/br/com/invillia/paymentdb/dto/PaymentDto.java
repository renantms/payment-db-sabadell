package br.com.invillia.paymentdb.dto;

import br.com.invillia.paymentdb.model.Payment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {

    private String name;

    private BigDecimal value;

    public PaymentDto(){}

    public PaymentDto(Payment payment) {
        this.name = payment.getName();
        this.value = payment.getValue();
    }


    public Payment convert() {
        Payment payment = new Payment();
        payment.setName(this.name);
        payment.setValue(this.value);
        return payment;
    }

}
