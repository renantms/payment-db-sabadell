package br.com.invillia.paymentdb.control;

import br.com.invillia.paymentdb.controller.PaymentControl;
import br.com.invillia.paymentdb.domain.PaymentMapper;
import br.com.invillia.paymentdb.domain.request.PaymentRequest;
import br.com.invillia.paymentdb.domain.response.PaymentResponse;
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

    private List<PaymentRequest> paymentRequests;

    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);

        this.paymentControl = new PaymentControl(paymentService);

        this.paymentRequests = new ArrayList<>();
        paymentRequests.add(new PaymentRequest());
        paymentRequests.get(0).setName("Renan");
        paymentRequests.get(0).setValue(new BigDecimal("300"));
    }

    private void configureGet(String name, List<PaymentResponse> payment) {
        Mockito.when(paymentService.getPayment(name, 0, 5)).thenReturn(payment.stream()
                .filter((value -> value.getName().equals(name))).collect(Collectors.toList()));
    }

    private List<PaymentResponse> configureGetAndReturn(String name, List<PaymentRequest> payment) {
        List<PaymentResponse> paymentResponses = payment.stream().map(PaymentMapper.INSTANCE::paymentRequestToPaymentResponse).collect(Collectors.toList());

        configureGet(name, paymentResponses);
        return paymentControl.getPayment(name, 0, 5);
    }


    @Test
    void getWillReturnPayment() {
        List<PaymentResponse> paymentResponses = configureGetAndReturn("Renan", paymentRequests);

        List<PaymentResponse> payments = paymentRequests.stream().map(PaymentMapper.INSTANCE::paymentRequestToPaymentResponse).collect(Collectors.toList());

        assertTrue(payments.equals(paymentResponses));
    }

    @Test
    void getWillNotReturnPayment() {
        List<PaymentResponse> paymentResponses = configureGetAndReturn("Scolari", paymentRequests);
        assertTrue(paymentResponses.isEmpty());
    }


}
