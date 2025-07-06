package org.example.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificationSteps {
    private String lastMessage = null;

    @When("I send a notification with {string} to {string}")
    public void i_send_a_notification_with_to(String message, String recipient) {
        // Simulate sending a notification
        //System.out.printf("Sending '%s' to %s%n", message, recipient);
        // %n is a platform-independent newline character
        // Change the above line to use System.out.format
        System.out.format("Sending '%s' to %s%n", message, recipient);
        lastMessage = message;
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String expectedMessage) {
        assertTrue(lastMessage != null && lastMessage.contains(expectedMessage),
            "Actual lastMessage was: " + lastMessage);
    }
}