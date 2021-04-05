package br.com.invillia.paymentdb.dto;

import br.com.invillia.paymentdb.model.Payment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDto that = (PaymentDto) o;
        return name.equals(that.name) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }


}
