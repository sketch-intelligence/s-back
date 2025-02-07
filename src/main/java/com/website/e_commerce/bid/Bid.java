package com.website.e_commerce.bid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.website.e_commerce.userproject.UserProject;
import com.website.e_commerce.user.model.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore // Prevents infinite recursion
    private UserProject userProject;

    @ManyToOne
    @JoinColumn(name = "architect_id", nullable = false)
    private UserEntity architect;

    private BigDecimal price;
    private Integer expectedDuration;
    private String description;

    public Bid() {}

    public Bid(UserProject userProject, UserEntity architect, BigDecimal price, Integer expectedDuration, String description) {
        this.userProject = userProject;
        this.architect = architect;
        this.price = price;
        this.expectedDuration = expectedDuration;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UserProject getUserProject() { return userProject; }
    public void setUserProject(UserProject userProject) { this.userProject = userProject; }

    public UserEntity getArchitect() { return architect; }
    public void setArchitect(UserEntity architect) { this.architect = architect; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getExpectedDuration() { return expectedDuration; }
    public void setExpectedDuration(Integer expectedDuration) { this.expectedDuration = expectedDuration; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
