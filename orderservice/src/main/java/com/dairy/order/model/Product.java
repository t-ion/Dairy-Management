package com.dairy.order.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="YYYY-MM-dd")
	private LocalDateTime dateOfLaunch;
}
