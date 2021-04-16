package br.com.invillia.paymentdb.exception;

import br.com.invillia.paymentdb.dto.PaymentDto;

public class SaveFailedException extends RuntimeException {

    public SaveFailedException(PaymentDto paymentDto){
        super("Payment: " + paymentDto.toString() + " not saved");
    }

}
