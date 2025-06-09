package com.example.interior.dto;

import java.util.List;

public class OrderResponseDTO {
    private Long orderId;
    private Long shopId;
    private Double totalAmount;
    private String status;
    private List<OrderItemDTO> orderItems;

    public OrderResponseDTO() {}

    public OrderResponseDTO(Long orderId, Long shopId, Double totalAmount, String status, List<OrderItemDTO> orderItems) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderItems = orderItems;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
