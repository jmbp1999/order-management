package com.zerobeta.ordermanagement.controller;

import com.zerobeta.ordermanagement.dto.OrderRequest;
import com.zerobeta.ordermanagement.model.Order;
import com.zerobeta.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/history")
    public Page<Order> getOrderHistoryWithPagination(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println("Pagination");
        try {
            return orderService.getOrderHistoryWithPagination(pageNo, pageSize);

        } catch (Exception e) {
            return Page.empty();
        }
    }

    @PostMapping("/place")
    public Order placeOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        return orderService.placeOrder(orderRequest);
    }
    @PutMapping("/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId)  {
        return orderService.cancelOrder(orderId);

    }

}
