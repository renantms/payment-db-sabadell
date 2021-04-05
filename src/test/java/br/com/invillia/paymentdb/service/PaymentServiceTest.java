package br.com.invillia.paymentdb.service;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.model.Payment;
import br.com.invillia.paymentdb.repository.PaymentRepository;
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
public class PaymentServiceTest {

    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private PaymentDto paymentDtoTest;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.paymentService = new PaymentService(paymentRepository);

        this.paymentDtoTest = new PaymentDto();
        paymentDtoTest.setName("Renan");
        paymentDtoTest.setValue(new BigDecimal("300"));
    }

    private void configureGet(String name, Optional<Payment> payment) {
        Mockito.when(paymentRepository.findByName(name)).thenReturn(payment);
    }

    private Optional<PaymentDto> configureGetAndReturn(String name, Optional<Payment> payment) {
        configureGet(name, payment);
        return paymentService.getPayment(name);
    }


    @Test
    void getWillReturnPayment() {
        Optional<PaymentDto> paymentDtoOptional = configureGetAndReturn("Renan", Optional.of(paymentDtoTest.convert()));
        assertTrue(paymentDtoTest.equals(paymentDtoOptional.get()));
    }

    @Test
    void getWillNotReturnPayment() {
        Optional<PaymentDto> paymentDtoOptional = configureGetAndReturn("Scolari", Optional.empty());
        assertFalse(paymentDtoOptional.isPresent());
    }
}
