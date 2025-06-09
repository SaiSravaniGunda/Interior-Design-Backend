package com.example.interior.models;

import jakarta.persistence.*;

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String description;

    @OneToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private User vendor; // Only vendors can own shops

    // Default Constructor
    public Shop() {
    }

    // Parameterized Constructor
    public Shop(String name, String address, String description, User vendor) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.vendor = vendor;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }
}
