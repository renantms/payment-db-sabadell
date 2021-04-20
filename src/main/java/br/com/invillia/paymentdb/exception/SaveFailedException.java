package br.com.invillia.paymentdb.exception;

import br.com.invillia.paymentdb.domain.request.PaymentRequest;

public class SaveFailedException extends RuntimeException {

    public SaveFailedException(PaymentRequest paymentRequest){
        super("Payment: " + paymentRequest.toString() + " not saved");
    }

}
