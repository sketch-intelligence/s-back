package com.website.e_commerce.portfolioproject.image;

import com.website.e_commerce.portfolioproject.PortfolioProject;
import jakarta.persistence.*;

import java.sql.Blob;
import java.util.Objects;

@Entity
public class ProjectImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    private Blob image;
    private String downloadUrl;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private PortfolioProject portfolioProject;

    public ProjectImage(Long id, String fileName, String fileType, Blob image, String downloadUrl, PortfolioProject portfolioProject) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.image = image;
        this.downloadUrl = downloadUrl;
        this.portfolioProject = portfolioProject;
    }

    public ProjectImage() {
    }

    @Override
    public String toString() {
        return "ProjectImage{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", image=" + image +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", portfolioProject=" + portfolioProject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectImage that = (ProjectImage) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(fileName, that.fileName)) return false;
        if (!Objects.equals(fileType, that.fileType)) return false;
        if (!Objects.equals(image, that.image)) return false;
        if (!Objects.equals(downloadUrl, that.downloadUrl)) return false;
        return Objects.equals(portfolioProject, that.portfolioProject);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (downloadUrl != null ? downloadUrl.hashCode() : 0);
        result = 31 * result + (portfolioProject != null ? portfolioProject.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public PortfolioProject getPortfolioProject() {
        return portfolioProject;
    }

    public void setPortfolioProject(PortfolioProject portfolioProject) {
        this.portfolioProject = portfolioProject;
    }
}

