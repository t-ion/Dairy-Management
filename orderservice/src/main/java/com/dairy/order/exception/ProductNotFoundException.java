package com.dairy.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found in cart")
public class ProductNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
