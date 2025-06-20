package com.galaxyfreedom.introduction.security.controller;

import com.galaxyfreedom.introduction.profile.entity.Profile;
import com.galaxyfreedom.introduction.profile.service.ProfileService;
import com.galaxyfreedom.introduction.security.entity.UserEntity;
import com.galaxyfreedom.introduction.security.service.UserEntityService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UserEntityService userEntityService;
    private final ProfileService profileService;

    public AuthController(UserEntityService userEntityService, ProfileService profileService) {
        this.userEntityService = userEntityService;
        this.profileService = profileService;
    }

    /**
     * Display the login page
     */
    @GetMapping("/login")
    public String showLoginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "expired", required = false) String expired,
            Model model) {

        // Add attributes for displaying messages in the template
        if (error != null) {
            model.addAttribute("loginError", true);
            model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
        }

        if (logout != null) {
            model.addAttribute("logoutSuccess", true);
            model.addAttribute("logoutMessage", "You have been successfully logged out.");
        }

        if (expired != null) {
            model.addAttribute("sessionExpired", true);
            model.addAttribute("expiredMessage", "Your session has expired. Please log in again.");
        }

        return "security/login";
    }

    /**
     * Handle successful logout redirect
     */
    @GetMapping("/logout-success")
    public String logoutSuccess(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("logout", "true");
        return "redirect:/login";
    }

    /**
     * Handle login success - redirect to appropriate page based on user role or previous page
     */
    @GetMapping("/login-success")
    public String loginSuccess(HttpServletRequest request) {
        // Get the user's intended destination before login
        String targetUrl = (String) request.getSession().getAttribute("targetUrl");

        if (targetUrl != null) {
            request.getSession().removeAttribute("targetUrl");
            return "redirect:" + targetUrl;
        }

        // Default redirect after successful login
        return "redirect:/dashboard";
    }

    /**
     * Handle access denied
     */
    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("errorMessage", "You don't have permission to access this resource.");
        return "error/access-denied";
    }

    /**
     * Handle login failure - this is called when authentication fails
     */
    @PostMapping("/login-failure")
    public String loginFailure(
            @RequestParam(value = "username", required = false) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("error", "true");
        redirectAttributes.addFlashAttribute("username", username);

        return "redirect:/login";
    }

    /**
     * Check if user is authenticated - useful for AJAX calls
     */
    @GetMapping("/auth/status")
    public String checkAuthStatus(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getName().equals("anonymousUser")) {
            model.addAttribute("authenticated", true);
            model.addAttribute("username", authentication.getName());
            log.info("Authorities {}", authentication.getAuthorities());
            model.addAttribute("authorities", authentication.getAuthorities());
        } else {
            model.addAttribute("authenticated", false);
        }

        return "security/fragments/auth-status"; // Return a fragment template
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal User user) {
        // Get the current user
        UserEntity currentUser = userEntityService.findByUsername(user.getUsername());

        // Get the user's profile
        Profile userProfile = profileService.getByUser(currentUser);

        // Add required attributes to the model that the template expects
        model.addAttribute("pageTitle", "Dashboard - " + currentUser.getUsername());
        model.addAttribute("user", currentUser);
        model.addAttribute("profile", userProfile);

        return "security/dashboard"; // Make sure you have templates/dashboard.html
    }

    @GetMapping("/user/roles")
    public String userRoles(Model model, @AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("authenticated", true);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("authorities", user.getAuthorities());
        } else {
            model.addAttribute("authenticated", false);
        }

        // ✅ Return the full fragment template (not just ::auth-status)
        return "security/fragments/auth-status";
    }
}
