package br.com.invillia.paymentdb.control;

import br.com.invillia.paymentdb.controller.PaymentControl;
import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PaymentControlTest {

    private PaymentControl paymentControl;

    @Mock
    private PaymentService paymentService;

    private PaymentDto paymentDtoTest;

    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);

        this.paymentControl = new PaymentControl(paymentService);

        this.paymentDtoTest = new PaymentDto();
        paymentDtoTest.setName("Renan");
        paymentDtoTest.setValue(new BigDecimal("300"));
    }

    private void configureGet(String name, Optional<PaymentDto> paymentDto){
        Mockito.when(paymentService.getPayment(name)).thenReturn(paymentDto);
    }

    private Optional<PaymentDto> configureGetAndReturn(String name, Optional<PaymentDto> paymentDto){
        configureGet(name, paymentDto);
        return paymentControl.getPayment(name);
    }

    private void configurePost(PaymentDto paymentDto, Optional<PaymentDto> paymentDtoOptional) {
        Mockito.when(paymentService.postPayment(paymentDto)).thenReturn(paymentDtoOptional);
    }

    private Optional<PaymentDto> configurePostAndReturn(PaymentDto paymentDto, Optional<PaymentDto> paymentDtoOptional) {
        configurePost(paymentDto, paymentDtoOptional);
        return paymentControl.postPayment(paymentDto);
    }

    @Test
    void getWillReturnPayment(){
        Optional<PaymentDto> paymentDtoOptional = configureGetAndReturn("Renan", Optional.of(paymentDtoTest));
        assertTrue(paymentDtoTest.equals(paymentDtoOptional.get()));
    }

    @Test
    void getWillNotReturnPayment(){
        Optional<PaymentDto> paymentDtoOptional = configureGetAndReturn("Scolari", Optional.empty());
        assertFalse(paymentDtoOptional.isPresent());
    }

    @Test
    void postWillCreatePayment(){
        Optional<PaymentDto> paymentDtoOptional = configurePostAndReturn(paymentDtoTest, Optional.of(paymentDtoTest));
        assertTrue(paymentDtoTest.equals(paymentDtoOptional.get()));
    }

    @Test
    void postWillNotCreatePayment(){
        Optional<PaymentDto> paymentDtoOptional = configurePostAndReturn(paymentDtoTest, Optional.empty());
        assertFalse(paymentDtoOptional.isPresent());
    }

}
