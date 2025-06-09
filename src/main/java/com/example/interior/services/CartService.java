package com.example.interior.services;

import com.example.interior.dto.CartDTO;
import com.example.interior.dto.CartItemDTO;
import com.example.interior.models.*;
import com.example.interior.repositories.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves the cart for a given user. If no cart exists, a new cart is created.
     */
    @Transactional
    public CartDTO getCartByUser(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
            .orElseGet(() -> {
                User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
                Cart newCart = new Cart(user);
                return cartRepository.save(newCart);
            });

        return convertToDTO(cart);
    }

    /**
     * Adds a product to the cart. If the product exists, updates the quantity.
     */
//    @Transactional
//    public CartDTO addToCart(Long userId, Long productId, int quantity) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> cartRepository.save(new Cart(user)));
//        Shop shop = product.getShop();
//        
//        Long shopId=shop.getId();
//
//        Optional<CartItem> existingItem = cart.getCartItems().stream()
//                .filter(item -> item.getProduct().getId().equals(productId))
//                .findFirst();
//
//        if (existingItem.isPresent()) {
//            CartItem cartItem = existingItem.get();
//            cartItem.setQuantity(cartItem.getQuantity() + quantity);
//            cartItemRepository.save(cartItem);
//        } else {
//            CartItem newItem = new CartItem(cart, product, quantity, shopId);
//            cartItemRepository.save(newItem);
//        }
//
//        return convertToDTO(cart);
//    }
//    @Transactional
//    public CartDTO addToCart(Long userId, Long productId, int quantity) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        // 👉 Explicitly check and create cart if not present
//        Cart cart = cartRepository.findByUserId(userId).orElse(null);
//        if (cart == null) {
//            cart = new Cart();         // make sure this sets user correctly
//            cart.setUser(user);        // manually assign user
//            cart = cartRepository.save(cart); // save and retrieve
//        }
//
//        Shop shop = product.getShop();
//        Long shopId = shop.getId();
//
//        Optional<CartItem> existingItem = cart.getCartItems().stream()
//                .filter(item -> item.getProduct().getId().equals(productId))
//                .findFirst();
//
//        if (existingItem.isPresent()) {
//            CartItem cartItem = existingItem.get();
//            cartItem.setQuantity(cartItem.getQuantity() + quantity);
//            cartItemRepository.save(cartItem);
//        } else {
//            CartItem newItem = new CartItem(cart, product, quantity, shopId);
//            cartItemRepository.save(newItem);
//        }
//        System.out.print(cart);
//
//        return convertToDTO(cart);
//    }
    @Transactional
    public CartDTO addToCart(Long userId, Long productId, int quantity) {
        // Retrieve user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User found: " + user.toString());  // Using toString()

        // Retrieve product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println("Product found: " + product.toString());  // Using toString()

        // Check and create cart if not present
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
            System.out.println("New cart created: " + cart.toString());  // Using toString()
        } else {
            System.out.println("Existing cart found: " + cart.toString());  // Using toString()
        }

        // Get shop information
        Shop shop = product.getShop();
        Long shopId = shop.getId();
        System.out.println("Product shop found: " + shop.toString());  // Using toString()

        // Check if item already exists in cart
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
            System.out.println("Updated existing cart item: " + cartItem.toString());  // Using toString()
        } else {
            CartItem newItem = new CartItem(cart, product, quantity, shopId);
            cartItemRepository.save(newItem);
            System.out.println("Added new cart item: " + newItem.toString());  // Using toString()
        }

        // Final cart state
        System.out.println("Updated cart: " + cart.toString());  // Using toString()

        return convertToDTO(cart);
    }


    
    



    /**
     * Removes a product from the cart.
     */
    @Transactional
    public CartDTO removeFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItem> itemsToRemove = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .collect(Collectors.toList());

        if (itemsToRemove.isEmpty()) {
            throw new RuntimeException("Product not found in cart");
        }

        // Remove items from the cart entity
        cart.getCartItems().removeAll(itemsToRemove);

        // Delete from the database
        cartItemRepository.deleteAll(itemsToRemove);

        // **Save the updated cart**
        cartRepository.save(cart);

        return convertToDTO(cart);
    }


    /**
     * Clears the cart for a given user.
     */
//    @Transactional
//    public void clearCart(Long userId) {
//        Cart cart = cartRepository.findByUserId(userId)
//                .orElseThrow(() -> new RuntimeException("Cart not found"));
//        cartItemRepository.deleteAll(cart.getCartItems());
//    }

    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // ✅ Delete all cart items
        cartItemRepository.deleteAll(cart.getCartItems());

        // ✅ Clear the cart's item list and save the cart to reflect changes
        cart.getCartItems().clear();
        cartRepository.save(cart); // Ensure it's updated in the DB
    }

    /**
     * Converts a Cart entity to a CartDTO.
     */
    private CartDTO convertToDTO(Cart cart) {
        List<CartItemDTO> cartItemDTOs = cart.getCartItems().stream()
            .map(item -> new CartItemDTO(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getProduct().getPrice(),
                item.getProduct().getImageUrl() ,
                item.getShopId()
                
            ))
            .collect(Collectors.toList());

        return new CartDTO(cart.getId(), cart.getUser().getId(), cartItemDTOs);
    }

}
