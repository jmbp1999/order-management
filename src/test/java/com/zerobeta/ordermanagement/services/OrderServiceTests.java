package com.zerobeta.ordermanagement.services;

import com.zerobeta.ordermanagement.dto.OrderRequest;
import com.zerobeta.ordermanagement.enums.OrderStatus;
import com.zerobeta.ordermanagement.model.Order;
import com.zerobeta.ordermanagement.repository.OrderRepository;
import com.zerobeta.ordermanagement.securityConfig.IAuthenticationFacade;
import com.zerobeta.ordermanagement.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private IAuthenticationFacade authenticationFacade;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        // You can mock authenticationFacade.getUserId() to return a specific user ID.
        when(authenticationFacade.getUserId()).thenReturn(1L);
    }

    @Test
    void placeOrder_ValidOrderRequest_ReturnsOrderReference() {
        // Arrange
        OrderRequest orderRequest = new OrderRequest("Item", 2, "Address");
        Order savedOrder = new Order();
        savedOrder.setOrderReferenceNumber(UUID.randomUUID().toString()); // Set the order reference
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // Act
        String result = null;
        try {
            result = orderService.placeOrder(orderRequest);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        // Assert
        assertNotNull(result);
        assertEquals(savedOrder.getOrderReferenceNumber(), result);
    }

    @Test
    void getOrderHistoryWithPagination_ValidInputs_ReturnsPageOfOrders() {
        // Arrange
        int pageNo = 0;
        int pageSize = 10;
        List<Order> orders = new ArrayList<>();
        // Add some dummy orders to the list
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            orders.add(order);
        }
        Page<Order> page = new PageImpl<>(orders);
        when(orderRepository.findByUserId(any(Long.class), any())).thenReturn(page);

        // Act
        Page<Order> result = orderService.getOrderHistoryWithPagination(pageNo, pageSize);

        // Assert
        assertNotNull(result);
        assertEquals(page, result);
    }

    @Test
    void cancelOrder_ValidOrderReference_ReturnsSuccessMessage() {
        // Arrange
        String orderReferenceNumber = UUID.randomUUID().toString();
        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setUserId(1L);
        when(orderRepository.findByOrderReferenceNumber(orderReferenceNumber)).thenReturn(order);

        // Act
        String result = orderService.cancelOrder(orderReferenceNumber);

        // Assert
        assertEquals("Order successfully canceled.", result);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    // Add more test methods for other scenarios (e.g., invalid inputs, unauthorized cancellation, order not found, etc.).
}
