package com.example.interior.dto;

import com.example.interior.models.OrderItem;

public class OrderItemResponse {
    private Long id;
    private Long orderId;
    private String deliveryStatus;
    private double price;
    private int quantity;

    private Long productId;
    private String productName;
    private String productImage;

    private String customerName;
    private String shippingAddress;

    public OrderItemResponse(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.orderId = orderItem.getOrder() != null ? orderItem.getOrder().getId() : null;
        this.deliveryStatus = orderItem.getDeliveryStatus();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();

        if (orderItem.getProduct() != null) {
            this.productId = orderItem.getProduct().getId();
            this.productName = orderItem.getProduct().getName();
            this.productImage = orderItem.getProduct().getImageUrl();
        }

        if (orderItem.getOrder() != null && orderItem.getOrder().getUser() != null) {
            this.customerName = orderItem.getOrder().getUser().getName();
            this.shippingAddress = orderItem.getOrder().getAddress();
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


}
