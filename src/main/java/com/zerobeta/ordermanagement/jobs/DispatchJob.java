package com.zerobeta.ordermanagement.jobs;

import com.zerobeta.ordermanagement.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DispatchJob {

    private final OrderService orderService;

    public DispatchJob(OrderService orderService) {
        this.orderService = orderService;
    }

//    @Scheduled(cron = "0 0 * * * *") // Run hourly
    @Scheduled(cron = "0 * * * * *") // Run every minute
    public void dispatchOrders() {
        System.out.println("Dispatching Orders..");
        orderService.updateNewOrdersToDispatchedAsync();
    }
}