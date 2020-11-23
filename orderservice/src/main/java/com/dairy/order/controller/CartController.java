package com.dairy.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dairy.order.OrderserviceApplication;
import com.dairy.order.exception.CartEmptyException;
import com.dairy.order.exception.ProductNotFoundException;
import com.dairy.order.model.OrderDetails;
import com.dairy.order.model.Product;
import com.dairy.order.restclient.AuthClient;
import com.dairy.order.restclient.AuthResponse;
import com.dairy.order.service.OrderService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CartController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderserviceApplication.class);
	@Autowired
	OrderService orderService;

	@Autowired
	private AuthClient authclient;

	@PutMapping("/addtocart")
	public void addToCart(@RequestBody String pId) {
		LOGGER.debug("Started addtocart " + pId);
		orderService.addToCart(pId);
		LOGGER.debug("end addtocart ");

	}

	@GetMapping("/cart")
	public ResponseEntity<?> getCartItems(@RequestHeader(name = "Authorization") String token) {
		LOGGER.debug("Started getcartitems all");
		AuthResponse authResponse = authclient.verifyToken(token);
		LOGGER.debug("token verification sucess");
		if (authResponse.isValid()) {
			List<Product> list = orderService.getCartItems();
			if (list != null)
				return new ResponseEntity<>(list, HttpStatus.OK);
			else
				throw new CartEmptyException();
		} else {
			return new ResponseEntity<>("You are not LoggedIn", HttpStatus.FORBIDDEN);
		}

	}

	@DeleteMapping("/remove/{id}")
	public void removeFromCart(@PathVariable String id) throws ProductNotFoundException {
		orderService.removeItem(Long.valueOf(id));
	}

	@GetMapping("/ordernow")
	public OrderDetails orderNow() throws CartEmptyException {
		return orderService.generateOrder();
	}
}
