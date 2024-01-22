package com.zerobeta.ordermanagement.repository;

import com.zerobeta.ordermanagement.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserId(long userId, PageRequest pageRequest);
}
