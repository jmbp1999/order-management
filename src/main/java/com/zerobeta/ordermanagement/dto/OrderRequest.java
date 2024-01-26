package com.zerobeta.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class OrderRequest {
    private String itemName;

    private Integer quantity;

    private String shippingAddress;
}
