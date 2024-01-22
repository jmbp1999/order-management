package com.zerobeta.ordermanagement.service;

import com.zerobeta.ordermanagement.dto.OrderRequest;
import com.zerobeta.ordermanagement.model.Order;
import com.zerobeta.ordermanagement.enums.OrderStatus;
import com.zerobeta.ordermanagement.repository.OrderRepository;
import com.zerobeta.ordermanagement.securityConfig.IAuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final IAuthenticationFacade authenticationFacade;

    public Order placeOrder(OrderRequest orderRequest) throws Exception {
        try {

            long userId= authenticationFacade.getUserId();
            System.out.println("Serve");
            System.out.println(userId);
            Order order = Order.builder()
                    .itemName(orderRequest.getItemName())
                    .quantity(orderRequest.getQuantity())
                    .shippingAddress(orderRequest.getShippingAddress())
                    .status(OrderStatus.NEW)
                    .orderReferenceNumber(UUID.randomUUID())
                    .placementTimestamp(new Timestamp(System.currentTimeMillis()))
                    .userId(userId)
                    .build();
            return orderRepository.save(order);

        } catch (Exception e) {
            throw new Exception("Error placing order");
        }
    }

    public Page<Order> getOrderHistoryWithPagination(int pageNo, int pageSize) {
        try {
            long userId = authenticationFacade.getUserId();
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
            return orderRepository.findByUserId(userId, pageRequest);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching order history with pagination", e);
        }
    }


    public String cancelOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            if (order.getUserId() == authenticationFacade.getUserId()) {
                if (OrderStatus.NEW.equals(order.getStatus())) {
                    order.setStatus(OrderStatus.CANCELLED);
                    orderRepository.save(order);
                    return "Order successfully canceled.";
                } else if (OrderStatus.CANCELLED.equals(order.getStatus())) {
                    return "Order is already canceled.";
                } else {
                    return "Cannot cancel order. Order is not in NEW status.";
                }
            } else {
                return "Unauthorized to cancel order. User ID mismatch.";
            }
        } else {
            return "Order not found with ID: " + orderId;
        }
    }

}
