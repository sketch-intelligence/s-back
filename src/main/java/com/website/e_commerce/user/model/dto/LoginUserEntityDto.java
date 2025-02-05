package com.website.e_commerce.user.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;


public class LoginUserEntityDto {
    private String email;
    private String password;

    @Override
    public String toString() {
        return "LoginUserEntityDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginUserEntityDto that = (LoginUserEntityDto) o;

        if (!Objects.equals(email, that.email)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginUserEntityDto() {
    }

    public LoginUserEntityDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
