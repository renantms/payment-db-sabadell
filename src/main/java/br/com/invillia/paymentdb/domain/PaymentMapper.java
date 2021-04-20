package br.com.invillia.paymentdb.domain;

import br.com.invillia.paymentdb.domain.request.PaymentRequest;
import br.com.invillia.paymentdb.domain.response.PaymentResponse;
import br.com.invillia.paymentdb.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper( PaymentMapper.class );

    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    PaymentResponse paymentToPaymentResponse(Payment payment);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    Payment paymentResponseToPayment(PaymentResponse payment);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    PaymentRequest paymentToPaymentRequest(Payment payment);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    Payment paymentRequestToPayment(PaymentRequest paymentRequest);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    PaymentRequest paymentResponseToPaymentRequest(PaymentResponse paymentResponse);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    PaymentResponse paymentRequestToPaymentResponse(PaymentRequest paymentRequest);
}
