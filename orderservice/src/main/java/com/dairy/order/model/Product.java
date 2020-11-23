package com.dairy.order.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private Long id;
	private String pName;
	private String category;
	private boolean isActive;
	private double price;
	private LocalDateTime dateModified;
}
