package br.com.invillia.paymentdb.dto;

import br.com.invillia.paymentdb.model.Payment;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class PaymentDto {

    private String name;

    private BigDecimal value;

    public PaymentDto(){}

    public PaymentDto(Payment payment) {
        this.name = payment.getName();
        this.value = payment.getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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
