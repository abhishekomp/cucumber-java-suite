Feature: Calculator Operations
  As a user
  I want to perform mathematical operations
  So that I can calculate results

  Background: Calculator is ready
    Given a calculator is initialized

  @regression
  Scenario Outline: Perform addition with different numbers
    When I add <num1> and <num2>
    Then the result should be <expected>

    Examples:
      | num1 | num2 | expected |
      | 5    | 3    | 8        |
      | 10   | 20   | 30       |
      | -5   | 5    | 0        |
      | 100  | 200  | 300      |

  Scenario: Perform multiple operations
    When I add 10 and 5
    And I multiply the result by 2
    Then the result should be 30

  # Example with Parameterized Type (Custom Parameter Type)
  Scenario: Calculate with currency
    When I add $50.00 and $25.50
    Then the total should be $75.50

