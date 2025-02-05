package com.website.e_commerce.portfolioproject.image;

import java.util.Objects;

public class ProjectImageDto {

    Long id;
    String fileName;
    String downloadUrl;

    @Override
    public String toString() {
        return "ProjectImageDto{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectImageDto that = (ProjectImageDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(fileName, that.fileName)) return false;
        return Objects.equals(downloadUrl, that.downloadUrl);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (downloadUrl != null ? downloadUrl.hashCode() : 0);
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

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public ProjectImageDto(Long id, String fileName, String downloadUrl) {
        this.id = id;
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
    }

    public ProjectImageDto() {
    }
}
