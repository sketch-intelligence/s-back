package com.website.e_commerce.postimage;

import lombok.Data;

import java.util.Objects;

public class PostImageDto {
    private Long id;
    private String fileName;
    private String downloadUrl;

    public PostImageDto(Long id, String fileName, String downloadUrl) {
        this.id = id;
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
    }

    public PostImageDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostImageDto that = (PostImageDto) o;

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

    @Override
    public String toString() {
        return "PostImageDto{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }

}
