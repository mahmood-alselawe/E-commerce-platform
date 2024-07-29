package com.takarub.ecommerce.services;

import com.takarub.ecommerce.dto.OrderLineRequest;
import com.takarub.ecommerce.dto.OrderLineResponse;
import com.takarub.ecommerce.mapper.OrderLineMapper;
import com.takarub.ecommerce.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineServices {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {

        var order = mapper.toOrderLine(request);
        return orderLineRepository.save(order).getId();

    }

    public List<OrderLineResponse> findByOrderID(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
