package br.com.invillia.paymentdb.repository;

import br.com.invillia.paymentdb.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Page<Payment> findByName(String name, Pageable pageable);

}
