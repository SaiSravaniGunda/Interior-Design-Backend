package com.example.interior.dto;

import com.example.interior.models.Order;

import java.util.List;
import java.util.stream.Collectors;

public class UserOrderResponseDTO {
    private Long orderId;
    private String paymentStatus;
    private String address;
    
    private List<UserOrderItemDTO> orderItems;

    public UserOrderResponseDTO(Order order) {
        this.orderId = order.getId();
        this.paymentStatus=order.getPaymentStatus();
        this.address = order.getAddress();
        
        this.orderItems = order.getOrderItems().stream()
                .map(UserOrderItemDTO::new)
                .collect(Collectors.toList());
    }

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<UserOrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<UserOrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
    
    

    // Getters & Setters
}
