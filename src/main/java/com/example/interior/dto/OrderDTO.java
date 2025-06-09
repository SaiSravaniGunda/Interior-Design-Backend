package com.example.interior.dto;

import java.util.Date;

//OrderDTO.java


import java.util.List;

public class OrderDTO {
 private Long userId;
 private List<OrderItemsDTO> items;
 private Date orderDate;
 private String status;

 public OrderDTO() {
	 this.status = "PENDING"; // DEFAULT VALUE
 }

 public OrderDTO(Long userId, List<OrderItemsDTO> items) {
     this.userId = userId;
     this.items = items;
 }

 public Long getUserId() {
     return userId;
 }

 public void setUserId(Long userId) {
     this.userId = userId;
 }
 public Date getOrderDate() {
     
	return orderDate;
 }

 public void setOrderDate(Date orderDate) {
     this.orderDate = orderDate;
 }
 public List<OrderItemsDTO> getItems() {
     return items;
 }

 public void setItems(List<OrderItemsDTO> items) {
     this.items = items;
 }
 public String getStatus() {
     return status;
 }

 public void setStatus(String status) {
     this.status = status;
 }
}
