package com.dairy.order.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.dairy.order.model.Product;

@FeignClient("PRODUCT-SERVICE")
public interface ProductClient {

	@GetMapping("/productapi/getproduct")
	Product getProduct(String pId);
}
