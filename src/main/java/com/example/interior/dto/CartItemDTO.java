package com.example.interior.dto;

public class CartItemDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private String image_url; 
    private Long shopId;

    public CartItemDTO(Long productId, String productName, int quantity, double price, String image_url,Long shopId) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.image_url = image_url; 
        this.shopId = shopId;
    }

    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getImage_url() { return image_url; } 

    public void setImage_url(String image_url) { this.image_url = image_url; }
    
    public Long getShopId() { return shopId; }
}
