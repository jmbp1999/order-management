package com.zerobeta.ordermanagement.controller;

import com.zerobeta.ordermanagement.dto.OrderRequest;
//import com.zerobeta.ordermanagement.model.Order;
import com.zerobeta.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place")
    public OrderRequest placeOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        return orderService.placeOrder(orderRequest);
    }
}
