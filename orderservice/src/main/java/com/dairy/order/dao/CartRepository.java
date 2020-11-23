package com.dairy.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dairy.order.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	@Modifying
	@Query("delete from Cart where product_id=?1")
	public int deleteByPrductId(Long id);

}
