package com.website.e_commerce.user.model.dto;

import com.website.e_commerce.user.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserEntityLoginResponse {
    private String message;
    private Map<String, Object> data;

    public UserEntityLoginResponse() {
        this.data = new HashMap<>();
    }

    public UserEntityLoginResponse(String message, String token, long expiresAt, UserEntity user) {
        this.message = message;
        this.data = new HashMap<>();
        this.data.put("token", token);
        this.data.put("expiresAt", expiresAt);
        this.data.put("user", user);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserEntityLoginResponse{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntityLoginResponse that = (UserEntityLoginResponse) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, data);
    }
}
