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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PaymentControlTest {

    private PaymentControl paymentControl;

    @Mock
    private PaymentService paymentService;

    private List<PaymentDto> paymentDtoTest;

    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);

        this.paymentControl = new PaymentControl(paymentService);

        this.paymentDtoTest = new ArrayList<>();
        paymentDtoTest.add(new PaymentDto());
        paymentDtoTest.get(0).setName("Renan");
        paymentDtoTest.get(0).setValue(new BigDecimal("300"));
    }

    private void configureGet(String name, List<PaymentDto> payment) {
        Mockito.when(paymentService.getPayment(name)).thenReturn(payment.stream()
                .filter((value -> value.getName().equals(name))).collect(Collectors.toList()));
    }

    private List<PaymentDto> configureGetAndReturn(String name, List<PaymentDto> payment) {
        configureGet(name, payment);
        return paymentService.getPayment(name);
    }


    @Test
    void getWillReturnPayment() {
        List<PaymentDto> paymentDtoOptional = configureGetAndReturn("Renan", paymentDtoTest);
        assertTrue(paymentDtoTest.equals(paymentDtoOptional));
    }

    @Test
    void getWillNotReturnPayment() {
        List<PaymentDto> paymentDtoOptional = configureGetAndReturn("Scolari", paymentDtoTest);
        assertTrue(paymentDtoOptional.isEmpty());
    }


}
