package com.dairy.product.model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pName;
	private String category;
	private boolean isActive;
	private double price;
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="YYYY-MM-dd")
	private LocalDateTime dateModified;
}
