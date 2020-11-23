package com.dairy.product.model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {
	/**
	 * The Foo class is a silly example to illustrate documentation 
	 * comments.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pName;
	private String category;
	private boolean isActive;
	private double price;
	private LocalDateTime dateModified;
}
