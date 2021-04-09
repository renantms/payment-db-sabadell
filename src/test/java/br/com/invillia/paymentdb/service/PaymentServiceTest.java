package br.com.invillia.paymentdb.service;

import br.com.invillia.paymentdb.dto.PaymentDto;
import br.com.invillia.paymentdb.dto.PaymentMapper;
import br.com.invillia.paymentdb.model.Payment;
import br.com.invillia.paymentdb.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PaymentServiceTest {

    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private List<PaymentDto> paymentDtoTest;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.paymentService = new PaymentService(paymentRepository);

        this.paymentDtoTest = new ArrayList<>();
        paymentDtoTest.add(new PaymentDto());

        paymentDtoTest.get(0).setName("Renan");
        paymentDtoTest.get(0).setValue(new BigDecimal("300"));
    }

    private void configureGet(String name, List<Payment> payment) {
        Mockito.when(paymentRepository.findByName(name, PageRequest.of(0, 5))).thenReturn(new PageImpl<>(payment.stream()
                                                        .filter((value -> value.getName().equals(name))).collect(Collectors.toList())));
    }

    private List<PaymentDto> configureGetAndReturn(String name, List<Payment> payment) {
        configureGet(name, payment);
        return paymentService.getPayment(name, 0, 5);
    }


    @Test
    void getWillReturnPayment() {
        List<PaymentDto> paymentDtoList = configureGetAndReturn("Renan", paymentDtoTest.stream().map(PaymentMapper.INSTANCE::paymentDtoToPayment).collect(Collectors.toList()));
        assertTrue(paymentDtoTest.equals(paymentDtoList));
    }

    @Test
    void getWillNotReturnPayment() {
        List<PaymentDto> paymentDtoList = configureGetAndReturn("Scolari", paymentDtoTest.stream().map(PaymentMapper.INSTANCE::paymentDtoToPayment).collect(Collectors.toList()));
        assertTrue(paymentDtoList.isEmpty());
    }
}
