package com.example.interior.dto;

import com.example.interior.models.OrderItem;

public class UserOrderItemDTO {
    private Long itemId;
    private String productName;
    private String productImage;
    private int quantity;
    private double price;
    private String deliveryStatus; 
  

    public UserOrderItemDTO(OrderItem orderItem) {
        this.itemId = orderItem.getId();
        this.productName = orderItem.getProduct() != null ? orderItem.getProduct().getName() : "Unknown Product";
        this.productImage = orderItem.getProduct() != null ? orderItem.getProduct().getImageUrl() : "";
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.deliveryStatus = orderItem.getDeliveryStatus(); 
        
    }
    
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }


	public Long getItemId() {
		return itemId;
	}


	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

    
    // Getters & Setters
}
