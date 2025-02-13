package com.website.e_commerce.userproject;

import com.website.e_commerce.portfolioproject.image.ProjectImage;
import com.website.e_commerce.user.model.entity.UserEntity;
import jakarta.persistence.*;
import com.website.e_commerce.bid.Bid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
@Entity
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "architect_id")
    private UserEntity architect;
    private String title;
    private String description;
    @OneToMany
    private List<ProjectImage> projectImage;

    @OneToMany(mappedBy = "userProject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids;

//    editing bids
    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }


    private BigDecimal budget = BigDecimal.ZERO;


    @Column(name = "dead_line")
    private LocalDate deadLine;




    @Override
    public String toString() {
        return "UserProject{" +
                "id=" + id +
                ", user=" + architect +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", projectImage=" + projectImage +
                ", budget=" + budget +
                ", deadLine=" + deadLine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProject that = (UserProject) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(architect, that.architect)) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(projectImage, that.projectImage)) return false;
        if (!Objects.equals(budget, that.budget)) return false;
        return Objects.equals(deadLine, that.deadLine);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (architect != null ? architect.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (projectImage != null ? projectImage.hashCode() : 0);
        result = 31 * result + (budget != null ? budget.hashCode() : 0);
        result = 31 * result + (deadLine != null ? deadLine.hashCode() : 0);
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProjectImage> getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(List<ProjectImage> projectImage) {
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

    public UserProject() {
    }

    public UserProject(Long id, UserEntity architect, String title, String description, List<ProjectImage> projectImage, BigDecimal budget, LocalDate deadLine) {
        this.id = id;
        this.architect = architect;
        this.title = title;
        this.description = description;
        this.projectImage = projectImage;
        this.budget = budget;
        this.deadLine = deadLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
