package com.zerobeta.ordermanagement.dto;

import lombok.Getter;

@Getter
public class OrderRequest {
    private String itemName;

    private Integer quantity;

    private String shippingAddress;
}
