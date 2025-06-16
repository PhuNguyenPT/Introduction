package com.galaxyfreedom.introduction.profile.repository;

import com.galaxyfreedom.introduction.profile.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface InterestRepository extends JpaRepository<Interest, UUID> {
    Set<Interest>  findInterestsByProfile_Id(UUID profileId);
}
