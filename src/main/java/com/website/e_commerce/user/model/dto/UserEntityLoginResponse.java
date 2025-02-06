package com.website.e_commerce.user.model.dto;

import com.website.e_commerce.user.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


public class UserEntityLoginResponse {

    private String  token;
    private long expiresAt;
    private UserEntity user;


    @Override
    public String toString() {
        return "UserEntityLoginResponse{" +
                "token='" + token + '\'' +
                ", expiresAt=" + expiresAt +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntityLoginResponse that = (UserEntityLoginResponse) o;

        if (expiresAt != that.expiresAt) return false;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (int) (expiresAt ^ (expiresAt >>> 32));
        return result;
    }
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public UserEntityLoginResponse() {
    }

    public UserEntityLoginResponse(String token, long expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }
}
