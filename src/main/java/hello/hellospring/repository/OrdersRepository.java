package hello.hellospring.repository;

import hello.hellospring.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByOrderUuid(String orderUuid);
}
