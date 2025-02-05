package com.website.e_commerce.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


public class UserEntityLoginResponse {

    private String  token;
    private long expiresAt;

    @Override
    public String toString() {
        return "UserEntityLoginResponse{" +
                "token='" + token + '\'' +
                ", expiresAt=" + expiresAt +
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
