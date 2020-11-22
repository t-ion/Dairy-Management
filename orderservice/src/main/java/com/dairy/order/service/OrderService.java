package com.dairy.order.service;

import java.util.List;

import com.dairy.order.model.OrderDetails;
import com.dairy.order.model.Product;

public interface OrderService {

	public void addToCart(String pId);
	public List<Product> getCartItems();
	public void removeItem(Long id);
	public OrderDetails generateOrder();
}
