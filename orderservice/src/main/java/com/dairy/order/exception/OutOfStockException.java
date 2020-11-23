package com.dairy.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Product is out of stock")
public class OutOfStockException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
