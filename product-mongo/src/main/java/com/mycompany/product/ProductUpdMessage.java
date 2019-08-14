package com.mycompany.product;

public class ProductUpdMessage {

	private Product product;
	private String action;

	public ProductUpdMessage(final Product product, final String action) {
		this.product = product;
		this.action = action;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getAction() {
		return action;
	}

	public void setAction(final String action) {
		this.action = action;
	}
}
