package br.com.invillia.paymentdb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {

    private String name;

    private BigDecimal value;

}
