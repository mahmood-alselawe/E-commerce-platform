package com.takarub.ecommerce.model;

import com.takarub.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//By using @EntityListeners(AuditingEntityListener.class),
// you enable automatic population and updating of timestamp
// fields when entities are created or modified.
// This helps in keeping track of when records were created and last updated without
// requiring manual intervention.
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {

    // its like what is represent to customer
    // id for this order and refrence
    // total amount how much should is bay
    // paymentMethod way that want to pay
    // customerId id for this customer
    // orderLines the deteales about the product that he is requseted like quanttiy
    //productId , productName , price ,quantity

    @Id
    @GeneratedValue
    private Integer id;

    private String reference;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    // because i now that customer is String
    private String customerId;

    // is the relationShip between Order and Products
    // one Order can take many products
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @CreatedDate
    @Column(updatable = false , nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;



}
