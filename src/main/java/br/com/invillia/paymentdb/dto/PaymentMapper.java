package br.com.invillia.paymentdb.dto;

import br.com.invillia.paymentdb.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper( PaymentMapper.class );

    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    PaymentDto paymentToPaymentDto(Payment payment);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    Payment paymentDtoToPayment(PaymentDto payment);
}
