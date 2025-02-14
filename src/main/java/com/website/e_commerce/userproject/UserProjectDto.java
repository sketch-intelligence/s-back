package com.website.e_commerce.userproject;

import com.website.e_commerce.portfolioproject.image.ProjectImageDto;
import com.website.e_commerce.user.model.dto.UserEntityDto;
import com.website.e_commerce.bid.BidDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;




import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class UserProjectDto {
   private Long id;
   private Long architectId;
   private String title;
   private String description;
    private String userName; // Make sure this is present in your DTO

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private List<ProjectImageDto> projectImage;
   private BigDecimal budget;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deadLine;

//   editing bids
private List<BidDto> bidDtos;

    public List<BidDto> getBidDtos() {
        return bidDtos;
    }

    public void setBidDtos(List<BidDto> bidDtos) {
        this.bidDtos = bidDtos;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProjectDto that = (UserProjectDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(architectId, that.architectId)) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(projectImage, that.projectImage)) return false;
        if (!Objects.equals(budget, that.budget)) return false;
        return Objects.equals(deadLine, that.deadLine);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (architectId != null ? architectId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (projectImage != null ? projectImage.hashCode() : 0);
        result = 31 * result + (budget != null ? budget.hashCode() : 0);
        result = 31 * result + (deadLine != null ? deadLine.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArchitectId() {
        return architectId;
    }

    public void setArchitectId(Long architectId) {
        this.architectId = architectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProjectImageDto> getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(List<ProjectImageDto> projectImage) {
        this.projectImage = projectImage;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }


    private String status; // New attribute

    private int publishedSince; // New attribute

    public enum Status {
        CANCELED, COMPLETED
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPublishedSince() {
        return publishedSince;
    }

    public void setPublishedSince(int publishedSince) {
        this.publishedSince = publishedSince;
    }

    @Override
    public String toString() {
        return "UserProject{" +
                "id=" + id +
                ", architect=" + architectId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", projectImage=" + projectImage +
                ", budget=" + budget +
                ", deadLine=" + deadLine +
                ", status=" + status +
                ", publishedSince=" + publishedSince +
                '}';
    }
    public UserProjectDto(Long id, Long architectId, String title, String description, List<ProjectImageDto> projectImage, BigDecimal budget, LocalDate deadLine, String userName, String status, int publishedSince) {
        this.id = id;
        this.architectId = architectId;
        this.title = title;
        this.description = description;
        this.projectImage = projectImage;
        this.budget = budget;
        this.deadLine = deadLine;
        this.userName = userName;
        this.status = status;
        this.publishedSince = publishedSince;
    }
    public UserProjectDto() {
    }
}
