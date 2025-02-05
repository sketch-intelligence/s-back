package com.website.e_commerce.profilepicture;

import com.website.e_commerce.user.model.entity.UserEntity;
import jakarta.persistence.*;

import java.sql.Blob;
import java.util.Objects;

// Entity Class
@Entity
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    private Blob image;
    private String downloadUrl;
    @OneToOne(mappedBy = "profileImage")
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Override
    public String toString() {
        return "ProfileImage{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", image=" + image +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public final boolean equals(Object o) {


        if (this == o) return true;
        if (!(o instanceof ProfileImage that)) return false;

        return Objects.equals(getId(), that.getId()) && Objects.equals(getFileName(), that.getFileName()) && Objects.equals(getFileType(), that.getFileType()) && Objects.equals(getImage(), that.getImage()) && Objects.equals(getDownloadUrl(), that.getDownloadUrl()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getFileName());
        result = 31 * result + Objects.hashCode(getFileType());
        result = 31 * result + Objects.hashCode(getImage());
        result = 31 * result + Objects.hashCode(getDownloadUrl());
        result = 31 * result + Objects.hashCode(getUser());
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProfileImage(Long id, String fileName, String fileType, Blob image, String downloadUrl, UserEntity user) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.image = image;
        this.downloadUrl = downloadUrl;
        this.user = user;
    }

    public ProfileImage() {
    }
}