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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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
    public void run(String... args) {
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

        Set<String> technologies1 = new HashSet<>(Arrays.asList(
                "Java", "Spring Framework", "HTMX", "Docker", "PostgresSQL"
        ));
        Project project1 = Project.builder()
                .title("Self introduction Web App")
                .description("Full Stack Web Application using Java, Spring Framework, HTMX, Docker, PostgresSQL")
                .detailedDescription("Self learning Full Stack Web App Development using Java, Spring Framework, HTMX, HATEOAS, Docker, PostgresSQL")
                .projectUrl("https://github.com/PhuNguyenPT/Introduction")
                .githubUrl("https://github.com/PhuNguyenPT/Introduction")
                .demoUrl("https://galaxyfreedom.com")
                .projectType(ProjectType.WEB_APPLICATION)
                .status(Status.IN_PROGRESS)
                .startDate(LocalDate.of(2024, 3, 1))
                .current(true)
                .technologies(technologies1)
                .isFeatured(false)
                .imageUrl("https://shourai.io/wp-content/uploads/2020/07/kindpng_1272110.png")
                .displayOrder(0)
                .profile(profile)
                .build();


        Set<String> technologies2 = new HashSet<>(Arrays.asList("ROS", "Python", ".NET", "Unity"));
        Project project2 = Project.builder()
                .title("Robotics")
                .description("Controlling robot arm ur10e and gripper rg2")
                .detailedDescription("Controlling robot arm ur10e and gripper rg2 using ROS, Python, .NET, Unity")
                .projectUrl("https://github.com/PhuNguyenPT/ur10e_rg2")
                .githubUrl("https://github.com/PhuNguyenPT/ur10e_rg2")
                .projectType(ProjectType.ROBOTICS)
                .status(Status.ON_HOLD)
                .startDate(LocalDate.of(2023, 10, 1))
                .current(true)
                .technologies(technologies2)
                .isFeatured(false)
                .imageUrl("https://public.blenderkit.com/thumbnails/assets/ac065292a3e44b3584fdb7389c8847f4/files/thumbnail_48c12b75-8f12-4277-9e47-e36c386c364e.png.2048x2048_q85.png")
                .displayOrder(4)
                .profile(profile)
                .build();


        Set<String> technologies3 = new HashSet<>(Arrays.asList("Pronela", "C", "Perl"));
        Project project3 = Project.builder()
                .title("Concurrency Modelling")
                .description("Modelling a simple concurrency in simple airport tower controller")
                .detailedDescription("Modelling a simple concurrency in simple airport tower controller using Pronela, C, Perl")
                .projectUrl("https://github.com/PhuNguyenPT/Air_Traffic_Control")
                .githubUrl("https://github.com/PhuNguyenPT/Air_Traffic_Control")
                .projectType(ProjectType.THEORETICAL_COMPUTER_SCIENCE)
                .technologies(technologies3)
                .startDate(LocalDate.of(2023, 10, 1))
                .endDate(LocalDate.of(2024, 3, 1))
                .status(Status.COMPLETED)
                .imageUrl("https://files.realpython.com/media/An-Overview-of-Concurrency-in-Python_Watermarked.c54c399ccb32.jpg")
                .displayOrder(3)
                .profile(profile)
                .build();

        Set<String> technologies4 = new HashSet<>(Arrays.asList("SQL", "Snowflake", "Python"));

        Project project4 = Project.builder()
                .title("Business Intelligence")
                .description("Data warehouse and Prediction using SQLServer, SQL, Snowflake, Python")
                .detailedDescription("Data warehouse Design and Data Prediction using SQLServer, SQL, Snowflake, Python")
                .projectUrl("https://github.com/PhuNguyenPT/Current-Topic-in-CS")
                .githubUrl("https://github.com/PhuNguyenPT/Current-Topic-in-CS")
                .projectType(ProjectType.DATA_SCIENCE)
                .technologies(technologies4)
                .startDate(LocalDate.of(2023, 10, 1))
                .endDate(LocalDate.of(2024, 3, 1))
                .status(Status.COMPLETED)
                .imageUrl("https://rockwalltechnologies.com/assets/img/bi.png")
                .displayOrder(1)
                .profile(profile)
                .build();

        Set<String> technologies5 = new HashSet<>(Arrays.asList("Java", "JavaFX"));

        Project project5 = Project.builder()
                .title("Game Schnappt Hubi")
                .description("German Board game Schnappt Hubi using JavaFX, Java")
                .detailedDescription("German Board game Schnappt Hubi using JavaFX, Java, Design Character art model")
                .projectUrl("https://github.com/PhuNguyenPT/Schnappt_Hubi")
                .githubUrl("https://github.com/PhuNguyenPT/Schnappt_Hubi")
                .projectType(ProjectType.GAME)
                .technologies(technologies5)
                .startDate(LocalDate.of(2023, 10, 1))
                .endDate(LocalDate.of(2024, 3, 1))
                .status(Status.COMPLETED)
                .imageUrl("https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MP_107209306/fee_786_587_png")
                .displayOrder(2)
                .profile(profile)
                .build();

        Set<String> technologies6 = new HashSet<>(Arrays.asList("Java", "Spring Framework", "PostgreSQL", "VueJS", "Docker"));

        Project project6 = Project.builder()
                .title("E-commerce Web App")
                .description("Small Web App project using Java, Spring Framework, Docker, PostgreSQL, VueJS")
                .detailedDescription("Small RESTful Web App App project using Java Spring Framework, Docker, PostgreSQL, VueJS")
                .projectUrl("https://github.com/PhuNguyenPT/Shopping_Cart_API")
                .githubUrl("https://github.com/PhuNguyenPT/Shopping_Cart_API")
                .projectType(ProjectType.WEB_APPLICATION)
                .technologies(technologies6)
                .startDate(LocalDate.of(2024, 3, 1))
                .endDate(LocalDate.of(2024, 6, 30))
                .status(Status.ON_HOLD)
                .imageUrl("https://shourai.io/wp-content/uploads/2020/07/kindpng_1272110.png")
                .displayOrder(5)
                .profile(profile)
                .build();


        Set<Project> projects = new HashSet<>(Arrays.asList(project1, project2, project3, project4, project5, project6));
        profile.setProjects(projects);

        // Skills
        Profile finalProfile1 = profile;
        Set<Skill> skills = Stream.of(
                "JavaScript", "Java", "Spring Framework", "HTMX", "Thymeleaf",
                "MongoDB", "PostgreSQL", "SQLServer", "Security", "Docker", "Git", "Cloud Hosting"
        )
                .map(s -> {
                    Skill skill = new Skill(s);
                    skill.setProfile(finalProfile1);
                    return skill;
                })
                .collect(Collectors.toCollection(HashSet::new));
        profile.setSkills(skills);

        // --- FIX FOR INTERESTS ---
        Profile finalProfile = profile;
        Set<Interest> interests = Stream.of(
                        "Programming", "Software Development", "Music", "MMA",
                        "Dancing", "Gaming", "Watching Movie", "Sight Seeing"
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