package com.website.e_commerce.portfolioproject;

import com.website.e_commerce.portfolioproject.image.ProjectImage;
import com.website.e_commerce.user.model.entity.UserEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class PortfolioProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "architect_id")
    private UserEntity architect;
    private String title;

    private String Description;
    @OneToMany(mappedBy = "portfolioProject")
    private List<ProjectImage> projectImage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PortfolioProject{" +
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

        PortfolioProject that = (PortfolioProject) o;

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

    public UserEntity getArchitect() {
        return architect;
    }

    public void setArchitect(UserEntity architect) {
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

    public List<ProjectImage> getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(List<ProjectImage> projectImage) {
        this.projectImage = projectImage;
    }

    public PortfolioProject(Long id, UserEntity architect, String title, String description, List<ProjectImage> projectImage) {
        this.id = id;
        this.architect = architect;
        this.title = title;
        Description = description;
        this.projectImage = projectImage;
    }

    public PortfolioProject() {
    }
}
