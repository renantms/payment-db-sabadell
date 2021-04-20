package br.com.invillia.paymentdb.domain.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {

    private String name;

    private BigDecimal value;

}
