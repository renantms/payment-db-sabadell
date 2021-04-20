package br.com.invillia.paymentdb.domain.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponse {

    private String name;

    private BigDecimal value;

}
