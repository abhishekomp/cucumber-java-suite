Feature: User Management
  As a system administrator
  I want to manage user accounts
  So that I can control access to the system

  Background: System setup
    Given the user management system is initialized
    And the database is clean

  Scenario: Create a single user
    Given I am logged in as an administrator
    When I create a user with username "john_doe" and email "john@example.com"
    Then the user "john_doe" should exist in the system
    And the user should have status "active"

  Scenario Outline: Create users with different roles
    Given I am logged in as an administrator
    When I create a user with username "<username>" and role "<role>"
    Then the user "<username>" should have role "<role>"
    And the user should have access level "<access_level>"

    Examples:
      | username    | role       | access_level |
      | alice_admin | admin      | full         |
      | bob_user    | user       | limited      |
      | charlie_mod | moderator  | elevated     |
      | diana_guest | guest      | readonly     |

  @smoke
  Scenario: Create multiple users from data table
    Given I am logged in as an administrator
    When I create the following users:
      | username  | email              | role      | active |
      | user1     | user1@example.com  | admin     | true   |
      | user2     | user2@example.com  | user      | true   |
      | user3     | user3@example.com  | moderator | false  |
    Then all users should be created successfully
    And there should be 3 users in the system