package com.dairy.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dairy.order.model.OrderDetails;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {

}
