package com.galaxyfreedom.introduction.profile.repository;

import com.galaxyfreedom.introduction.profile.entity.Profile;
import com.galaxyfreedom.introduction.security.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    @EntityGraph(attributePaths = "user")
    Optional<Profile> findFirstByIsPrimaryOrderByCreatedAtDesc(Boolean isPrimary);
    boolean existsByUser(UserEntity user);

    Optional<Profile> findByUser(UserEntity user);

    @Query("SELECT p.id FROM Profile p WHERE p.isPrimary = :primary ORDER BY p.createdAt DESC")
    Optional<UUID> findIdByPrimary(@Param("primary") Boolean primary);

}
