package org.example.stepdefs;

import io.cucumber.java.ParameterType;

/**
 * Custom Parameter Types for Cucumber
 *
 * This class demonstrates how to create custom parameter types
 * that can be used in step definitions to convert string values
 * from feature files into custom Java objects.
 */
public class ParameterTypes {

    /**
     * Custom parameter type for Money
     * Matches patterns like: $50.00, $25.50, $100
     *
     * Usage in feature file:
     * When I add $50.00 and $25.50
     *
     * This will automatically convert to Money objects in step definition:
     * @When("I add {money} and {money}")
     * public void i_add_money(Money amount1, Money amount2) { ... }
     */
    @ParameterType("\\$([0-9]+\\.?[0-9]*)")
    public CalculatorSteps.Money money(String amount) {
        double value = Double.parseDouble(amount);
        System.out.println("🔄 Converting string '" + amount + "' to Money object: $" + value);
        return new CalculatorSteps.Money(value);
    }
}

