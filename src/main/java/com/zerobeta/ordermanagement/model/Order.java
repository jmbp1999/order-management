package com.zerobeta.ordermanagement.model;

import com.zerobeta.ordermanagement.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="order_table")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="itemName",nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private UUID orderReferenceNumber;

    @Column(nullable = false)
    private Timestamp placementTimestamp;


    @Column(nullable = false)
    private long userId;

}
