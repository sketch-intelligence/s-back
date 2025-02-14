package com.website.e_commerce.portfolioproject;

import com.website.e_commerce.portfolioproject.image.ProjectImageDto;
import com.website.e_commerce.user.model.dto.UserEntityDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PortfolioProjectDto {
    private Long id;
    private UserEntityDto architect;

    private String title;

    private String Description;

    private List<ProjectImageDto> projectImage= new ArrayList<>();


    public List<ProjectImageDto> getProjectImage() {
        return projectImage == null ? new ArrayList<>() : projectImage;
    }

    @Override
    public String toString() {
        return "PortfolioProjectDto{" +
                "id=" + id +
                ", architect=" + architect +
                ", title='" + title + '\'' +
                ", Description='" + Description + '\'' +
                ", projectImage=" + projectImage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortfolioProjectDto that = (PortfolioProjectDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(architect, that.architect)) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(Description, that.Description)) return false;
        return Objects.equals(projectImage, that.projectImage);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (architect != null ? architect.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (Description != null ? Description.hashCode() : 0);
        result = 31 * result + (projectImage != null ? projectImage.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntityDto getArchitect() {
        return architect;
    }

    public void setArchitect(UserEntityDto architect) {
        this.architect = architect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



    public void setProjectImage(List<ProjectImageDto> projectImage) {
        this.projectImage = projectImage;
    }

    public PortfolioProjectDto() {
    }

    public PortfolioProjectDto(Long id, UserEntityDto architect, String title, String description, List<ProjectImageDto> projectImage) {
        this.id = id;
        this.architect = architect;
        this.title = title;
        Description = description;
        this.projectImage = projectImage;
    }
}
