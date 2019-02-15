package com.daybelge.billing.product;

import com.daybelge.billing.currency.MoneyAmount;

public class Product {
	private String name;
	private ProductType type;
	private MoneyAmount price;
	
	public Product(String name, ProductType type, MoneyAmount price) {
		setName(name);
		setType(type);
		setPrice(price);
	}

	public static Product grocery(String name, MoneyAmount price) {
		return new Product(name, ProductType.GROCERY, price);
	}
	
	public static Product book(String name, MoneyAmount price) {
		return new Product(name, ProductType.BOOK, price);
	}
	
	public static Product cloth(String name, MoneyAmount price) {
		return new Product(name, ProductType.CLOTHING, price);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("Product name cannot be null.");
		}
		this.name = name;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		if(type == null) {
			throw new IllegalArgumentException("Product type cannot be null.");
		}
		this.type = type;
	}
	public MoneyAmount getPrice() {
		return price;
	}
	public void setPrice(MoneyAmount price) {
		if(price == null) {
			throw new IllegalArgumentException("Product price cannot be null.");
		}
		this.price = price;
	}

}
