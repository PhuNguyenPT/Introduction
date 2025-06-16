package com.galaxyfreedom.introduction.profile.enums;

public enum ProjectType {
    WEB_APPLICATION("Web Application"),
    MOBILE_APPLICATION("Mobile Application"),
    DATA_SCIENCE("Data Science/ML"),
    UI_UX_DESIGN("UI/UX Design"),
    API_BACKEND("API/Backend"),
    DESKTOP_APPLICATION("Desktop Application"),
    GAME("Game Development"),
    ROBOTICS("Robotics"),
    THEORETICAL_COMPUTER_SCIENCE("Theoretical Computer Science"),
    OTHER("Other");

    private final String displayName;

    ProjectType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
