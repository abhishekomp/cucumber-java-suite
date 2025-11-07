package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

/**
 * Cucumber Hooks - Run before/after scenarios and steps.
 * Useful for setup, teardown, logging, and screenshots.
 */
public class Hooks {

    /**
     * Runs before every scenario
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("▶ Starting Scenario: " + scenario.getName());
        System.out.println("  Tags: " + scenario.getSourceTagNames());
        System.out.println("=".repeat(80));
    }

    /**
     * Runs before scenarios tagged with @smoke
     */
    @Before("@smoke")
    public void beforeSmokeTest(Scenario scenario) {
        System.out.println("🔥 [SMOKE TEST] Running critical smoke test");
    }

    /**
     * Runs before scenarios tagged with @regression
     */
    @Before("@regression")
    public void beforeRegressionTest(Scenario scenario) {
        System.out.println("🔄 [REGRESSION TEST] Running regression test suite");
    }

    /**
     * Runs after every scenario
     */
    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("-".repeat(80));
        System.out.println("✓ Finished Scenario: " + scenario.getName());
        System.out.println("  Status: " + scenario.getStatus());

        if (scenario.isFailed()) {
            System.out.println("  ❌ FAILED - Error details logged");
            // In real scenarios, you might:
            // - Take a screenshot
            // - Log error details
            // - Attach files to the report
        }
        System.out.println("=".repeat(80) + "\n");
    }

    /**
     * Runs before each step (optional - can be verbose)
     */
    @BeforeStep
    public void beforeStep(Scenario scenario) {
        // Uncomment to see step-level logging
        // System.out.println("  → Executing step...");
    }

    /**
     * Runs after each step (optional - useful for screenshots)
     */
    @AfterStep
    public void afterStep(Scenario scenario) {
        // Uncomment to see step-level logging
        // System.out.println("  ✓ Step completed");

        // In real scenarios with UI testing, you might:
        // if (scenario.isFailed()) {
        //     byte[] screenshot = takeScreenshot();
        //     scenario.attach(screenshot, "image/png", "failure-screenshot");
        // }
    }

    /**
     * Runs after failed scenarios - useful for cleanup or error reporting
     */
    @After(order = 10) // Higher order runs first
    public void afterFailedScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("  🔍 Performing failure analysis...");
            System.out.println("  📝 Scenario URI: " + scenario.getUri());
            System.out.println("  📍 Line: " + scenario.getLine());

            // You could:
            // - Send notification
            // - Log to monitoring system
            // - Clean up test data
        }
    }
}
