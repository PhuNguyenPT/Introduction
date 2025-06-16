package com.galaxyfreedom.introduction.profile.mapper;

import com.galaxyfreedom.introduction.profile.entity.Experience;
import com.galaxyfreedom.introduction.profile.model.ExperienceDetailsModel;
import com.galaxyfreedom.introduction.profile.model.ExperienceModel;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ExperienceMapper {
    public static ExperienceModel toModel(Experience experience) {
        return (experience != null) ? new ExperienceModel(
                experience.getId(),
                experience.getTitle(),
                experience.getCompany(),
                experience.getDescription(),
                experience.getFormattedDuration()
        ) : null;
    }

    public static Set<ExperienceModel> toCollectionModel(Set<Experience> experiences) {
        return experiences.stream()
                .map(ExperienceMapper::toModel)
                .collect(Collectors.toSet());
    }

    public static ExperienceDetailsModel toDetailsModel(Experience experience) {
        return (experience != null) ? new ExperienceDetailsModel(
                experience.getId(),
                experience.getTitle(),
                experience.getCompany(),
                experience.getDescription(),
                experience.getStartDate(),
                experience.getEndDate()
        ) : null;
    }
}
