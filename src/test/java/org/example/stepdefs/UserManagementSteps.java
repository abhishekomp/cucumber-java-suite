package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagementSteps {

    private final UserManagementContext context = new UserManagementContext();

    // Background steps
    @Given("the user management system is initialized")
    public void the_user_management_system_is_initialized() {
        context.initializeSystem();
        System.out.println("✓ User management system initialized");
    }

    @Given("the database is clean")
    public void the_database_is_clean() {
        context.clearDatabase();
        System.out.println("✓ Database cleaned");
    }

    // Regular scenario steps
    @Given("I am logged in as an administrator")
    public void i_am_logged_in_as_an_administrator() {
        context.loginAsAdmin();
        System.out.println("✓ Logged in as administrator");
    }

    @When("I create a user with username {string} and email {string}")
    public void i_create_a_user_with_username_and_email(String username, String email) {
        context.createUser(username, email, "user");
        System.out.format("✓ Created user: %s (%s)%n", username, email);
    }

    @Then("the user {string} should exist in the system")
    public void the_user_should_exist_in_the_system(String username) {
        assertTrue(context.userExists(username),
            "User " + username + " should exist but was not found");
        System.out.println("✓ User exists: " + username);
    }

    @Then("the user should have status {string}")
    public void the_user_should_have_status(String expectedStatus) {
        String actualStatus = context.getUserStatus(context.getLastCreatedUsername());
        assertEquals(expectedStatus, actualStatus, "User status mismatch");
        System.out.println("✓ User status verified: " + expectedStatus);
    }

    // Scenario Outline steps
    @When("I create a user with username {string} and role {string}")
    public void i_create_a_user_with_username_and_role(String username, String role) {
        context.createUser(username, username + "@example.com", role);
        System.out.format("✓ Created user: %s with role: %s%n", username, role);
    }

    @Then("the user {string} should have role {string}")
    public void the_user_should_have_role(String username, String expectedRole) {
        String actualRole = context.getUserRole(username);
        assertEquals(expectedRole, actualRole,
            "Expected role: " + expectedRole + " but got: " + actualRole);
        System.out.format("✓ User %s has role: %s%n", username, expectedRole);
    }

    @Then("the user should have access level {string}")
    public void the_user_should_have_access_level(String expectedAccessLevel) {
        String actualAccessLevel = context.getAccessLevel(context.getLastCreatedUsername());
        assertEquals(expectedAccessLevel, actualAccessLevel, "Access level mismatch");
        System.out.println("✓ Access level verified: " + expectedAccessLevel);
    }

    // Data Table steps - using asMaps()
    @When("I create the following users:")
    public void i_create_the_following_users(DataTable dataTable) {
        // Convert DataTable to List of Maps
        List<Map<String, String>> users = dataTable.asMaps();

        System.out.println("Creating " + users.size() + " users from data table:");

        for (Map<String, String> user : users) {
            String username = user.get("username");
            String email = user.get("email");
            String role = user.get("role");
            boolean active = Boolean.parseBoolean(user.get("active"));

            context.createUser(username, email, role);
            context.setUserActiveStatus(username, active);

            System.out.format("  ✓ %s (%s) - Role: %s, Active: %s%n",
                username, email, role, active);
        }
    }

    @Then("all users should be created successfully")
    public void all_users_should_be_created_successfully() {
        assertTrue(context.getAllUsers().size() > 0,
            "Expected users to be created but found none");
        System.out.println("✓ All users created successfully");
    }

    @Then("there should be {int} users in the system")
    public void there_should_be_users_in_the_system(Integer expectedCount) {
        int actualCount = context.getUserCount();
        assertEquals(expectedCount, actualCount,
            "Expected " + expectedCount + " users but found " + actualCount);
        System.out.format("✓ User count verified: %d users%n", actualCount);
    }
}

