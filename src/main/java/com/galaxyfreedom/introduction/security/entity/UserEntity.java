package com.galaxyfreedom.introduction.security.entity;

import com.galaxyfreedom.introduction.audit.Auditable;
import com.galaxyfreedom.introduction.security.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "users", schema = "security")
public class UserEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonExpired = true;

    @Column(nullable = false)
    private boolean credentialsNonExpired = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_roles",
            schema = "security",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role") // Column name for each enum value
    private Set<Role> roles = new HashSet<>();

    // Default constructor for JPA
    public UserEntity() {}

    public UserEntity(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Convert to Spring Security User
    public User toSpringSecurityUser() {
        Set<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet());

        return new User(
                this.username,
                this.password,
                this.enabled,
                this.accountNonExpired,
                this.credentialsNonExpired,
                this.accountNonLocked,
                authorities
        );
    }

    // Static factory method from UserDetails (which could be User or other implementation)
    public static UserEntity fromSpringSecurityUser(UserDetails userDetails, Set<Role> roles) {
        UserEntity entity = new UserEntity();
        entity.username = userDetails.getUsername();
        entity.password = userDetails.getPassword();
        entity.enabled = userDetails.isEnabled();
        entity.accountNonExpired = userDetails.isAccountNonExpired();
        entity.credentialsNonExpired = userDetails.isCredentialsNonExpired();
        entity.accountNonLocked = userDetails.isAccountNonLocked();
        entity.roles = roles;
        return entity;
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public boolean isAccountNonExpired() { return accountNonExpired; }
    public void setAccountNonExpired(boolean accountNonExpired) { this.accountNonExpired = accountNonExpired; }

    public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) { this.credentialsNonExpired = credentialsNonExpired; }

    public boolean isAccountNonLocked() { return accountNonLocked; }
    public void setAccountNonLocked(boolean accountNonLocked) { this.accountNonLocked = accountNonLocked; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}