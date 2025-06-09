package com.example.interior.services;

import com.example.interior.dto.OrderItemResponse;
import com.example.interior.models.OrderItem;

import com.example.interior.repositories.OrderItemRepository;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    
   
   

    
//    public List<OrderItem> getVendorOrders(Long vendorId) {
//        // Fetch vendor details
//        User vendor = userRepository.findById(vendorId).orElse(null);
//        if (vendor == null) {
//            throw new RuntimeException("Vendor not found");
//        }
//
//        // Get all order items where the product's shop is owned by this vendor
//        return orderItemRepository.findAll().stream()
//                .filter(orderItem -> orderItem.getProduct().getShop().getVendor().getId().equals(vendorId))
//                .collect(Collectors.toList());
//    }
    
//    public List<OrderItem> getVendorOrders(Long vendorId) {
//        return orderItemRepository.findByVendorId(vendorId);
//    }
    public List<OrderItemResponse> getVendorOrders(Long vendorId) {
        return orderItemRepository.findByVendorId(vendorId);
    }

    public String updateOrderItemStatus(Long orderItemId, String status) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElse(null);
        if (orderItem != null) {
            orderItem.setDeliveryStatus(status);
            orderItemRepository.save(orderItem);
            return "Order item status updated successfully!";
        }
        return "Order item not found!";
    }
}
