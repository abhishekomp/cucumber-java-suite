package org.example.stepdefs;

import java.util.*;

/**
 * Shared context object for user management scenarios.
 * This simulates a simple in-memory user management system.
 */
public class UserManagementContext {

    private final Map<String, User> users = new HashMap<>();
    private boolean systemInitialized = false;
    private boolean adminLoggedIn = false;
    private String lastCreatedUsername;

    public void initializeSystem() {
        this.systemInitialized = true;
    }

    public void clearDatabase() {
        users.clear();
    }

    public void loginAsAdmin() {
        if (!systemInitialized) {
            throw new IllegalStateException("System must be initialized before login");
        }
        this.adminLoggedIn = true;
    }

    public void createUser(String username, String email, String role) {
        if (!adminLoggedIn) {
            throw new IllegalStateException("Must be logged in as admin to create users");
        }

        User user = new User(username, email, role);
        users.put(username, user);
        lastCreatedUsername = username;
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public String getUserStatus(String username) {
        User user = users.get(username);
        return user != null ? user.getStatus() : null;
    }

    public String getUserRole(String username) {
        User user = users.get(username);
        return user != null ? user.getRole() : null;
    }

    public String getAccessLevel(String username) {
        User user = users.get(username);
        if (user == null) return null;

        // Map roles to access levels
        return switch (user.getRole()) {
            case "admin" -> "full";
            case "moderator" -> "elevated";
            case "user" -> "limited";
            case "guest" -> "readonly";
            default -> "none";
        };
    }

    public void setUserActiveStatus(String username, boolean active) {
        User user = users.get(username);
        if (user != null) {
            user.setActive(active);
        }
    }

    public String getLastCreatedUsername() {
        return lastCreatedUsername;
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public int getUserCount() {
        return users.size();
    }

    // Inner class to represent a User
    static class User {
        private final String username;
        private final String email;
        private final String role;
        private String status = "active";
        private boolean active = true;

        public User(String username, String email, String role) {
            this.username = username;
            this.email = email;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getRole() {
            return role;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @Override
        public String toString() {
            return String.format("User{username='%s', email='%s', role='%s', active=%s}",
                username, email, role, active);
        }
    }
}

