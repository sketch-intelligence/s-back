package com.website.e_commerce.user.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.website.e_commerce.annotation.Password;
import com.website.e_commerce.profilepicture.ProfileImage;
import com.website.e_commerce.role.RoleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * The {@code CustomerEntity} class represents a customer in the e-commerce application.
 * This entity is mapped to a database table and includes fields such as first name,
 * last name, email, and password, with appropriate validation constraints.
 *
 * <p>The class is annotated with JPA annotations to define the entity and its fields.
 * Additionally, it implements the {@link UserDetails} interface, making it compatible
 * with Spring Security for authentication and authorization purposes.</p>
 *
 * <p>Lombok annotations are used to reduce boilerplate code by automatically generating
 * getters, setters, constructors, and other methods. The {@code @Builder} annotation
 * facilitates the builder pattern for creating instances of this class.</p>
 *
 * <p>Fields:</p>
 * <ul>
 *   <li><strong>id:</strong> The primary key for the customer entity, generated using a sequence strategy.</li>
 *   <li><strong>firstName:</strong> The customer's first name, which is mandatory and cannot be blank.</li>
 *   <li><strong>lastName:</strong> The customer's last name, which is also mandatory and cannot be blank.</li>
 *   <li><strong>email:</strong> The customer's email address, which must be unique, well-formed, and non-blank.</li>
 *   <li><strong>password:</strong> The customer's password, validated by custom constraints defined by the {@code @Password} annotation.</li>
 * </ul>
 *
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAuthorities()}: Returns the authorities granted to the user. Currently returns an empty list.</li>
 *   <li>{@link #getPassword()}: Returns the password used to authenticate the user.</li>
 *   <li>{@link #getUsername()}: Returns the email address, which serves as the username for authentication.</li>
 *   <li>{@link #isAccountNonExpired()}: Indicates whether the user's account has expired. Always returns {@code true}.</li>
 *   <li>{@link #isAccountNonLocked()}: Indicates whether the user is locked or unlocked. Currently always returns {@code true}.</li>
 *   <li>{@link #isCredentialsNonExpired()}: Indicates whether the user's credentials (password) have expired. Returns {@code false} by default.</li>
 *   <li>{@link #isEnabled()}: Indicates whether the user is enabled or disabled. Returns {@code false} by default.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * CustomerEntity customer = CustomerEntity.builder()
 *     .firstName("John")
 *     .lastName("Doe")
 *     .email("john.doe@example.com")
 *     .password("SecurePassword123!")
 *     .build();
 * }</pre>
 *
 * @see UserDetails
 * @see jakarta.persistence.Entity
 * @see lombok.Builder
 * @see com.website.e_commerce.annotation.Password
 */


@Entity
@Table(name = "user")
@Builder
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name" , nullable = false )
    @NotBlank(message = "name is a mandatory")
    private String name;
    @NotBlank(message = "email is a mandatory")
    @Column(name = "email" , unique = true , nullable = false)
    @Email(message = "must be a well-formed email address")
    private String email;
    @NotBlank(message = "password is a mandatory")
    @Password
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();
    @OneToOne
    private ProfileImage profileImage;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                .toList();
    }

    @Override
    public @NonNull String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //Todo make lock feature in the future
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserEntity() {
    }

    public UserEntity(Long id, String name, String email, String password, Set<RoleEntity> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(password, that.password)) return false;
        return Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

}
