package com.dairy.order.service;

import java.time.LocalDateTime;
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
import com.dairy.order.exception.CartEmptyException;
import com.dairy.order.exception.OutOfStockException;
import com.dairy.order.exception.ProductNotFoundException;
import com.dairy.order.model.Cart;
import com.dairy.order.model.OrderDetails;
import com.dairy.order.model.Product;
import com.dairy.order.restclient.ProductClient;
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
	public void addToCart(String pId){
		LOGGER.debug("service class add to cart" + pId);
			Product p=productClient.getProduct(pId);
			if(!p.isActive())
				throw new OutOfStockException();
			Cart cart = new Cart();
			cart.setProductId(Long.valueOf(pId));
			cartRepository.save(cart);
		
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
		if(cartItems.isEmpty())
			throw new CartEmptyException();
		return cartItems;
	}

	@Override
	@Transactional
	public void removeItem(Long id) throws ProductNotFoundException{
		if(cartRepository.deleteByPrductId(id)==0)
			throw new ProductNotFoundException();

	}

	@Override
	public OrderDetails generateOrder() {
		OrderDetails orderDetails=new OrderDetails();
		double total=0;
		LOGGER.debug("service class generate order");
		List<Cart> list = cartRepository.findAll();
		if(list.isEmpty())
			throw new CartEmptyException();
		
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
		cartRepository.deleteAll();
		return orderRepository.save(orderDetails);
	}
	
}
