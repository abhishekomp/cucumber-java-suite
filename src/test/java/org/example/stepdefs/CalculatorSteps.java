package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorSteps {

    private Calculator calculator;
    private double result;

    @Given("a calculator is initialized")
    public void a_calculator_is_initialized() {
        calculator = new Calculator();
        System.out.println("✓ Calculator initialized");
    }

    @When("I add {int} and {int}")
    public void i_add_and(Integer num1, Integer num2) {
        result = calculator.add(num1, num2);
        System.out.format("✓ Added %d + %d = %.0f%n", num1, num2, result);
    }

    @When("I multiply the result by {int}")
    public void i_multiply_the_result_by(Integer multiplier) {
        result = calculator.multiply(result, multiplier);
        System.out.format("✓ Multiplied result by %d = %.0f%n", multiplier, result);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(Integer expected) {
        assertEquals(expected, (int) result, "Result mismatch");
        System.out.format("✓ Result verified: %.0f%n", result);
    }

    // Steps using custom parameter type (Money)
    @When("I add {money} and {money}")
    public void i_add_money_and_money(Money amount1, Money amount2) {
        result = amount1.getAmount() + amount2.getAmount();
        System.out.format("✓ Added %s + %s = $%.2f%n", amount1, amount2, result);
    }

    @Then("the total should be {money}")
    public void the_total_should_be(Money expected) {
        assertEquals(expected.getAmount(), result, 0.01, "Money amount mismatch");
        System.out.format("✓ Total verified: $%.2f%n", result);
    }

    // Simple Calculator class
    static class Calculator {
        public double add(double a, double b) {
            return a + b;
        }

        public double multiply(double a, double b) {
            return a * b;
        }
    }

    // Custom Money class for parameterized type example
    public static class Money {
        private final double amount;

        public Money(double amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return String.format("$%.2f", amount);
        }
    }
}

