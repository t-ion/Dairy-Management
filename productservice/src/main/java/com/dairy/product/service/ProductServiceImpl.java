package com.dairy.product.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.product.ProductserviceApplication;
import com.dairy.product.dao.ProductRepo;
import com.dairy.product.exception.ProductNotFoundException;
import com.dairy.product.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductserviceApplication.class);

	@Autowired
	ProductRepo productRepo;

	@Override
	public Product addProduct(Product product) {
		product.setDateModified(LocalDateTime.now());
		return productRepo.save(product);
	}

	@Override
	public Product getProduct(Long id) {
		Product pr=productRepo.findById(id).orElseThrow(()->{throw new ProductNotFoundException();});
		LOGGER.debug("Service class get product");
		return pr;
	}

	@Override
	public List<Product> getAllProducts() {
		LOGGER.debug("Service class get all products");
		return productRepo.findAll();
	}

	@Override
	public void modifyProduct(Product product) {
		Product newProduct = productRepo.findById(product.getId()).orElseThrow(()->{throw new ProductNotFoundException();});
		newProduct.setActive(product.isActive());
		newProduct.setCategory(product.getCategory());
		newProduct.setDateModified(LocalDateTime.now());
		newProduct.setPName(product.getPName());
		newProduct.setPrice(product.getPrice());
		productRepo.save(newProduct);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepo.findById(id).orElseThrow(()->{throw new ProductNotFoundException();});
		productRepo.deleteById(id);
		
	}


}
