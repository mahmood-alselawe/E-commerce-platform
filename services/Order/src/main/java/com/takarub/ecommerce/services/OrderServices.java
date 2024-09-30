package com.takarub.ecommerce.services;

import com.takarub.ecommerce.client.CustomerClient;
import com.takarub.ecommerce.client.PaymentClient;
import com.takarub.ecommerce.client.ProductClient;
import com.takarub.ecommerce.client.dto.PaymentRequest;
import com.takarub.ecommerce.dto.OrderLineRequest;
import com.takarub.ecommerce.dto.OrderRequest;
import com.takarub.ecommerce.dto.OrderResponse;
import com.takarub.ecommerce.exception.BusinessException;
import com.takarub.ecommerce.kafka.OrderProducer;
import com.takarub.ecommerce.kafka.dto.OrderConfirmation;
import com.takarub.ecommerce.mapper.OrderMapper;
import com.takarub.ecommerce.product.PurchaseRequest;
import com.takarub.ecommerce.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServices {

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderRepository orderRepository;

    private final OrderMapper mapper;

    private final OrderLineServices orderLineServices;

    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;


    public Integer createOrder(OrderRequest request, String token) {


        // before we create order we need a few checks
        //1- checks if we have customer or not --> by open-feign-clients
        // to check you need comunecate with customer-ms

        var customer = this.customerClient.findCustomerById(request.customerId(),token)
                .orElseThrow(() -> new BusinessException
                        ("can not create order:: No Customer exists with customer id: " + request.customerId()));

        System.out.println(customer);


        //2- purchase the products and this need to make request from product-ms
        var purchaseProducts =this.productClient.purchaseProducts(request.products(),token);
        System.out.println("this token for productClient : " + token);

        //3- persist order
        var order = this.orderRepository.save(mapper.toOrder(request));
        System.out.println(order);
        //4-persist order-line
        for (PurchaseRequest purchaseRequest: request.products()) {
                orderLineServices.saveOrderLine(
                        new OrderLineRequest(
                                null,
                                order.getId(),
                                purchaseRequest.productId(),
                                purchaseRequest.quantity()
                        )
                );

        }

        //5- toDo starts payments process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest,token);


        //6- todo send order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(mapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()->new
                        EntityNotFoundException(String.format("No order found with provided id:  %d ",orderId)));
    }
}
