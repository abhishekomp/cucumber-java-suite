Feature: Notification Feature

  Scenario: Successful notification scenario
    When I send a notification with "Test message" to "alice@example.com"
    Then the response should contain "Test message"