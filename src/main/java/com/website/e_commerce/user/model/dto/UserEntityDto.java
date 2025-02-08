package com.website.e_commerce.user.model.dto;

import java.util.Objects;

public class UserEntityDto {
    private Long id;
    private String name; // Keep the existing field name

    @Override
    public String toString() {
        return "UserEntityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntityDto that = (UserEntityDto) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { // Keep method name consistent
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntityDto() {
    }

    // ✅ Fix: Ensure this constructor matches what PostService expects
    public UserEntityDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
