package com.zerobeta.ordermanagement.repository;

import com.zerobeta.ordermanagement.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserId(long userId, PageRequest pageRequest);
    @Modifying
    @Query("UPDATE Order o SET o.status = 'DISPATCHED' WHERE o.status = 'NEW'")
    void updateNewOrdersToDispatched();


    Order findByOrderReferenceNumber(String orderReferenceNumber);
}
