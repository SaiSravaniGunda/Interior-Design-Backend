package com.example.interior.models;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "project_requests")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProjectRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore
    @JsonManagedReference
    private User user;  // Reference to the user (client)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "designer_id", nullable = false)
//    @JsonIgnore
    @JsonManagedReference
    private User designer;  // Reference to the designer

    @Column(nullable = false)
    private String name;  // Name of the project

    @Column(nullable = false)
    private String specialization;  // Specialization required

    @Column(nullable = false)
    private double budget;  // Budget for the project

    @Column(nullable = false)
    private String theme;  // Theme of the project

    @Column(nullable = false)
    private String status = "Pending";  // Status of the project request, default is "Pending"

    // Default constructor
    public ProjectRequest() {}

    // Constructor
    public ProjectRequest(User user, User designer, String name, String specialization, double budget, String theme) {
        this.user = user;
        this.designer = designer;
        this.name = name;
        this.specialization = specialization;
        this.budget = budget;
        this.theme = theme;
        this.status = "Pending";  
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getDesigner() {
        return designer;
    }

    public void setDesigner(User designer) {
        this.designer = designer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProjectRequest{" +
                "name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", budget=" + budget +
                ", theme='" + theme + '\'' +
                ", designer=" + designer +
                ", user=" + user +
                ", status='" + status + '\'' +
                '}';
    }
}
