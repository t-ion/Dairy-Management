package com.dairy.product.service;

import java.util.List;

import com.dairy.product.model.Product;

public interface ProductService {
	Product addProduct(Product product);
	Product getProduct(Long id);
	public List<Product> getAllProducts();
	public void modifyProduct(Product product);
	public void deleteProduct(Long id);
}
