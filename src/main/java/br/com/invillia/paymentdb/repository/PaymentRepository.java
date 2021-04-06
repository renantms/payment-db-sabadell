package br.com.invillia.paymentdb.repository;

import br.com.invillia.paymentdb.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByName(String name);

}
