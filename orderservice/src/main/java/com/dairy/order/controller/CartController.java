package com.dairy.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dairy.order.model.OrderDetails;
import com.dairy.order.model.Product;
import com.dairy.order.service.OrderService;

@RestController
public class CartController {
	
	@Autowired
	OrderService orderService;

	@PutMapping("/addtocart")
	public void addToCart(@RequestBody String pId) {
		orderService.addToCart(pId);
	}
	
	@GetMapping("/cart")
	public List<Product> getCartItems(){
		return orderService.getCartItems();
	}
	@DeleteMapping("/remove/{id}")
	public void removeFromCart(@PathVariable String id) {
		orderService.removeItem(Long.valueOf(id));
	}
	@GetMapping("/ordernow")
	public OrderDetails orderNow() {
		return orderService.generateOrder();
	}
}
