package com.dairy.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dairy.product.ProductserviceApplication;
import com.dairy.product.exception.ProductNotFoundException;
import com.dairy.product.model.Product;
import com.dairy.product.service.ProductService;

@RestController
public class ProductRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductserviceApplication.class);
	@Autowired
	ProductService productService;

	@PostMapping("/addproduct")
	public Product addProduct(@RequestBody Product product) {
		LOGGER.debug("controller class add product");
		return productService.addProduct(product);

	}
	@PostMapping("/getproduct")
	public Product viewProduct(@RequestBody String id) throws ProductNotFoundException{
		Product p=productService.getProduct(Long.valueOf(id));
		LOGGER.debug("controller class view product");
		return p;
	}
	@GetMapping("/allproducts")
	public List<Product> viewAllProducts(){
		LOGGER.debug("request for all products");
		return productService.getAllProducts();
	}
	@PutMapping("/edit")
	public void modifyProduct(@RequestBody Product product) throws ProductNotFoundException{
		LOGGER.debug("Request for modify product");
		productService.modifyProduct(product);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable @Valid Long id) throws ProductNotFoundException{
		productService.deleteProduct(Long.valueOf(id));
	}
}
