package com.galaxyfreedom.introduction.security.service;

import com.galaxyfreedom.introduction.security.enums.Role;
import com.galaxyfreedom.introduction.security.entity.UserEntity;
import com.galaxyfreedom.introduction.security.repository.UserEntityRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserEntityService {

    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntityService(UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(String username, String rawPassword, Set<Role> roles) {
        // Use Spring Security's User.builder() for validation and construction
        UserDetails springUser = User.builder()
                .username(username)
                .password(rawPassword)
                .passwordEncoder(passwordEncoder::encode)
                .authorities(roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toSet()))
                .build();

        // Convert to entity and save
        UserEntity entity = UserEntity.fromSpringSecurityUser(springUser, roles);
        return userRepository.save(entity);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }
}