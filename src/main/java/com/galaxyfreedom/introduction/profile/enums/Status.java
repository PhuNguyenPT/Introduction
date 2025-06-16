package com.galaxyfreedom.introduction.profile.enums;

public enum Status {
    PLANNING("Planning"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    MAINTENANCE("Maintenance"),
    ARCHIVED("Archived");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

