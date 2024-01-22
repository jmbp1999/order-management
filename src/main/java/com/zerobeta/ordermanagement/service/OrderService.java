package com.zerobeta.ordermanagement.service;

import com.zerobeta.ordermanagement.dto.OrderRequest;
//import com.zerobeta.ordermanagement.model.Order;
import com.zerobeta.ordermanagement.model.User;
//import com.zerobeta.ordermanagement.repository.OrderRepository;
import com.zerobeta.ordermanagement.repository.UserRepository;
import com.zerobeta.ordermanagement.securityConfig.UserInfoDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

//    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    public OrderRequest placeOrder(OrderRequest orderRequest) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails ud = (UserInfoDetails) authentication.getPrincipal();
        try {

            User user = userRepository.findById(ud.getUserId())
                    .orElseThrow(() -> new Exception("User not found with ID: " + ud.getUserId()));

return orderRequest;
        } catch (Exception e) {
            throw new Exception("Error placing order", e);
        }
    }

}
