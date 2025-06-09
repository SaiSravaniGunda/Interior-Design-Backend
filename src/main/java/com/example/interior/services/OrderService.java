package com.example.interior.services;

import com.example.interior.dto.UserOrderResponseDTO;
import com.example.interior.models.Order;
import com.example.interior.models.OrderItem;
import com.example.interior.models.Product;
import com.example.interior.models.User;
import com.example.interior.repositories.OrderRepository;
import com.example.interior.repositories.ProductRepository;
import com.example.interior.repositories.UserRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository; 
    
    @Autowired
    private ProductRepository productRepository;

    public Order placeOrder(Map<String, Object> payload) {
        Long userId = Long.valueOf(payload.get("userId").toString());
        Map<String, Object> orderData = (Map<String, Object>) payload.get("order");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setAddress(orderData.get("address").toString());
        order.setPaymentStatus(orderData.get("paymentStatus").toString());
        order.setDeliveryStatus(orderData.get("deliveryStatus").toString());

        List<OrderItem> orderItems = ((List<Map<String, Object>>) orderData.get("orderItems")).stream()
                .map(itemData -> {
                    OrderItem item = new OrderItem();
                    Long productId = Long.valueOf(itemData.get("productId").toString());
                    int quantity = Integer.parseInt(itemData.get("quantity").toString());

                    Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new RuntimeException("Product not found"));

                    // Check if enough stock is available
                    if (product.getQuantity() < quantity) {
                        throw new RuntimeException("Insufficient stock for product: " + product.getName());
                    }

                    // Decrease stock
                    product.setQuantity(product.getQuantity() - quantity);
                    productRepository.save(product); // Save updated stock

                    item.setProduct(product);
                    item.setQuantity(quantity);
                    item.setPrice(Double.parseDouble(itemData.get("price").toString()));
                    item.setDeliveryStatus("PENDING"); // Default status
                    item.setOrder(order); // Associate item with order
                    
                    return item;
                })
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }



    @Transactional(readOnly = true)
    public List<UserOrderResponseDTO> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(UserOrderResponseDTO::new)
                .collect(Collectors.toList());
    }
}
