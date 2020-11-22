package com.dairy.order.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dairy.order.OrderserviceApplication;
import com.dairy.order.dao.CartRepository;
import com.dairy.order.dao.OrderRepository;
import com.dairy.order.model.Cart;
import com.dairy.order.model.OrderDetails;
import com.dairy.order.model.Product;
import com.dairy.order.restclient.ProductClient;
import java.time.LocalDateTime;
@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderserviceApplication.class);
	
	@Autowired
	ProductClient productClient;
	
	@Autowired
	CartRepository cartRepository;

	@Autowired
	OrderRepository orderRepository;
	@Override
	public void addToCart(String pId) {
		LOGGER.debug("service class add to cart" + pId);
		if (productClient.getProduct(pId) != null) {
			Cart cart = new Cart();
			cart.setProductId(Long.valueOf(pId));
			cartRepository.save(cart);
		}
		LOGGER.debug("service class add to cart----not found " + pId);
	}

	@Override
	public List<Product> getCartItems() {
		LOGGER.debug("service class get all cartitems");
		
		List<Cart> list = cartRepository.findAll();
		List<Product> cartItems = new ArrayList<Product>();

		while (!list.isEmpty()) {
			String pId = String.valueOf(list.get(0).getProductId());
			LOGGER.debug("service class get all cartitems pId fromm cart table" + pId);
			Product product = productClient.getProduct(pId);
			if (product != null) {
				cartItems.add(product);
			}
			list.remove(0);
		}
		return cartItems;
	}

	@Override
	@Transactional
	public void removeItem(Long id) {
		cartRepository.deleteByPrductId(id);

	}

	@Override
	public OrderDetails generateOrder() {
		OrderDetails orderDetails=new OrderDetails();
		double total=0;
		LOGGER.debug("service class generate order");
		List<Cart> list = cartRepository.findAll();
		while (!list.isEmpty()) {
			String pId = String.valueOf(list.get(0).getProductId());
			LOGGER.debug("service class get all GEN ORDER" + pId);
			Product product = productClient.getProduct(pId);
			if (product != null) {
				total+=product.getPrice();
			}
			list.remove(0);
		}
		orderDetails.setOrderAmount(total);
		orderDetails.setDateOfOrder(LocalDateTime.now());
		return orderRepository.save(orderDetails);
	}
	
}
