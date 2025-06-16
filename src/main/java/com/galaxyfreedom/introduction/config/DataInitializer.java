package com.galaxyfreedom.introduction.config;

import com.galaxyfreedom.introduction.profile.entity.*;
import com.galaxyfreedom.introduction.profile.enums.ProjectType;
import com.galaxyfreedom.introduction.profile.enums.Status;
import com.galaxyfreedom.introduction.profile.service.ProfileService;
import com.galaxyfreedom.introduction.security.enums.Role;
import com.galaxyfreedom.introduction.security.entity.UserEntity;
import com.galaxyfreedom.introduction.security.service.UserEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final ProfileService profileService;
    private final UserEntityService userEntityService;

    public DataInitializer(ProfileService profileService, UserEntityService userEntityService) {
        this.profileService = profileService;
        this.userEntityService = userEntityService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if user already exists to avoid duplicates on restart
        UserEntity userEntity;
        try {
            userEntity = userEntityService.findByUsername("user");
            log.info("User 'user' already exists, skipping user creation");
        } catch (Exception e) {
            // User doesn't exist, create it
            userEntity = userEntityService.createUser(
                    "user", "password", Set.of(Role.USER, Role.ADMIN)
            );
            log.info("Created user: {}", userEntity.getUsername());
        }

        // Check if profile already exists for this user
        if (profileService.existsByUser(userEntity)) {
            log.info("Profile already exists for user: {}", userEntity.getUsername());
            return;
        }

        // Create sample profile data linked to the user
        Profile profile = new Profile("Phu", "Full Stack Developer & Tech Enthusiast",
                "Passionate about creating innovative web solutions and building scalable applications.");

        // Link the profile to the user
        profile.setUser(userEntity);
        profile.setEmail("phu.npt01@email.com");
        profile.setPhone("(+84) 938 213 228");
        profile.setLocation("Ho Chi Minh City, Vietnam");
        profile.setLinkedinUrl("https://www.linkedin.com/in/phu-nguyen-phuoc-thien-51416a351");
        profile.setGithubUrl("https://github.com/PhuNguyenPT");
        profile.setPortfolioUrl("https://galaxyfreedom.com");
        profile.setPrimary(true);

        Project project1 = new Project("Self introduction Web App", "Full Stack Web Application using Java, Spring Framework, HTMX, " +
                "Docker, PostgresSQL", ProjectType.WEB_APPLICATION);
        project1.setDetailedDescription("Self learning Full Stack Web App Development using Java, Spring Framework, HTMX, " +
                "HATEOAS, Docker, PostgresSQL");
        project1.setStartDate(LocalDate.of(2024, 3, 1));
        project1.setEndDate(LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        project1.setStatus(Status.IN_PROGRESS);
        project1.setProfile(profile);

        Project project2 = new Project("Robotics", "Controlling robot arm ur10e and gripper rg2",
                ProjectType.ROBOTICS);
        project2.setStartDate(LocalDate.of(2023, 10, 1));
        project2.setEndDate(LocalDate.of(2024, 3, 1));
        project2.setStatus(Status.COMPLETED);
        project2.setProfile(profile);

        Project project3 = new Project("Concurrency Modelling", "Modelling a simple concurrency in simple airport tower controller",
                ProjectType.THEORETICAL_COMPUTER_SCIENCE);
        project3.setStartDate(LocalDate.of(2023, 10, 1));
        project3.setEndDate(LocalDate.of(2024, 3, 1));
        project3.setStatus(Status.COMPLETED);
        project3.setProfile(profile);

        Set<Project> projects = new HashSet<>(Arrays.asList(project1, project2, project3));
        profile.setProjects(projects);

        // Skills
        Profile finalProfile1 = profile;
        Set<Skill> skills = Stream.of(
                "JavaScript", "Java", "Spring Framework", "HTMX", "Thymeleaf",
                "MongoDB", "PostgreSQL", "Docker", "Git", "Cloud Hosting"
        )
                .map(s -> {
                    Skill skill = new Skill(s);
                    skill.setProfile(finalProfile1);
                    return skill;
                })
                .collect(Collectors.toSet());
        profile.setSkills(skills);

        // --- FIX FOR INTERESTS ---
        Profile finalProfile = profile;
        Set<Interest> interests = Stream.of(
                        "Reading Tech Blogs", "Music Production", "MMA",
                        "Dancing", "Gaming", "Watching Movie"
                )
                .map(s -> {
                    Interest interest = new Interest(s);
                    interest.setProfile(finalProfile); // <-- Set the profile on each Interest object
                    return interest;
                })
                .collect(Collectors.toCollection(HashSet::new));
        profile.setInterests(interests);

        // Save profile first (this will now cascade and save interests correctly)
        profile = profileService.saveProfile(profile);

        // Create experiences (already correctly handled)
        Experience exp1 = new Experience(
                "Web Developer",
                "Vietnamese-German University",
                "Leading development of web applications using Spring Boot and VueJS",
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 6, 30) // Current position
        );
        exp1.setProfile(profile);

        Experience exp2 = new Experience(
                "Full Stack Developer",
                "Vietnamese-German University",
                "Self-learning development of full stack web applications",
                LocalDate.of(2024, 7, 1),
                null
        );
        exp2.setProfile(profile);

        profile.setExperiences(new HashSet<>(Arrays.asList(exp1, exp2)));
        // Saving profile again will update the experiences if they are managed,
        // or cascade persistence if they are new and not yet managed.
        profileService.saveProfile(profile);
        log.info("Profile created {}", profile.getId());
    }
}