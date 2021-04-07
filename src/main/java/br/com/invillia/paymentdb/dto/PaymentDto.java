package br.com.invillia.paymentdb.dto;

import br.com.invillia.paymentdb.model.Payment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {

    private String name;

    private BigDecimal value;

}
