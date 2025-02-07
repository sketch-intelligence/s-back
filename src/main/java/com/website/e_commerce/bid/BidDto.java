package com.website.e_commerce.bid;

import java.math.BigDecimal;

public class BidDto {
    private Long id;
    private Long projectId;
    private BigDecimal price;
    private Integer expectedDuration;
    private String description;

    public BidDto() {}

    public BidDto(Long id, Long projectId, BigDecimal price, Integer expectedDuration, String description) {
        this.id = id;
        this.projectId = projectId;
        this.price = price;
        this.expectedDuration = expectedDuration;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getExpectedDuration() { return expectedDuration; }
    public void setExpectedDuration(Integer expectedDuration) { this.expectedDuration = expectedDuration; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
