package com.takarub.ecommerce.repository;

import com.takarub.ecommerce.dto.OrderLineResponse;
import com.takarub.ecommerce.orderline.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
