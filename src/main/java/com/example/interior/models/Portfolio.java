package com.example.interior.models;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "designer_id", nullable = false, unique = true)
    private User designer; 

    private String skills;
    private int experience; // in years

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Project> projects;

    // Constructors
    public Portfolio() {}

    public Portfolio(User designer, String skills, int experience) {
        this.designer = designer;
        this.skills = skills;
        this.experience = experience;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public User getDesigner() { return designer; }
    public void setDesigner(User designer) { this.designer = designer; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }
}
