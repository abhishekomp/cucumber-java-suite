# Cucumber Java Suite - JUnit 5 Reference Project

> **A comprehensive starter template for Behavior-Driven Development (BDD) using Cucumber with JUnit 5**
>
> **Created by:** Abhishek Omprakash

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://adoptium.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.14.0-brightgreen.svg)](https://cucumber.io/)
[![JUnit 5](https://img.shields.io/badge/JUnit-5.10.1-green.svg)](https://junit.org/junit5/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 📋 Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
- [Project Components](#project-components)
  - [Feature Files](#feature-files)
  - [Step Definitions](#step-definitions)
  - [Hooks](#hooks)
  - [Custom Parameter Types](#custom-parameter-types)
  - [Test Runner Configuration](#test-runner-configuration)
- [Important Concepts](#important-concepts)
- [Test Reports](#test-reports)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

This project serves as a **complete reference implementation** for teams adopting Behavior-Driven Development (BDD) with **Cucumber** and **JUnit 5**. It demonstrates modern testing practices, clean code organization, and includes real-world examples of all major Cucumber features.

### What Makes This Project Special?

- ✅ **Modern JUnit 5 Platform Suite API** - No legacy JUnit 4 dependencies
- ✅ **Clean Architecture** - Organized packages for hooks, step definitions, and runners
- ✅ **Comprehensive Examples** - Background, Scenario Outline, Data Tables, Custom Parameters
- ✅ **Production-Ready Hooks** - Before/After scenario and step hooks with tag filtering
- ✅ **Multiple Report Formats** - HTML, JSON, and Console output
- ✅ **Configuration Management** - Using `cucumber.properties` for centralized settings
- ✅ **Well-Documented** - Extensive comments and documentation throughout

---

## 🌟 Key Features

### 1. **Background Steps**
Common setup steps that run before each scenario (e.g., system initialization, database cleanup).

### 2. **Scenario Outline with Examples**
Data-driven testing with multiple test data sets in a single scenario.

### 3. **Data Tables (asMaps)**
Handle complex test data using tables converted to `List<Map<String, String>>`.

### 4. **Custom Parameter Types**
Define custom parameter types (e.g., `Money`) for type-safe step definitions.

### 5. **Comprehensive Hooks**
- `@Before` / `@After` for scenario-level setup/teardown
- `@BeforeStep` / `@AfterStep` for step-level actions
- Tag-based hooks (e.g., `@Before("@smoke")`)
- Failure analysis and reporting

### 6. **Tag-Based Filtering**
Run specific subsets of tests using tags like `@smoke`, `@regression`.

---

## 🛠 Technologies

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 17 | Programming language (LTS version) |
| **Maven** | 3.6+ | Build automation and dependency management |
| **Cucumber** | 7.14.0 | BDD framework for writing executable specifications |
| **JUnit Jupiter** | 5.10.1 | Test framework for assertions and test execution |
| **JUnit Platform Suite** | 1.10.1 | Test suite orchestration with `@Suite` annotation |
| **Maven Surefire** | 3.2.5 | Test execution plugin for Maven |

---

## 📁 Project Structure

```
cucumber-java-suite/
├── pom.xml                                    # Maven configuration
├── README.md                                  # This file
├── src/
│   ├── main/
│   │   └── java/                              # Application code (if any)
│   └── test/
│       ├── java/
│       │   └── org/example/
│       │       ├── hooks/                     # ⭐ Cucumber hooks (separate package)
│       │       │   └── Hooks.java             # Before/After scenario and step hooks
│       │       ├── runner/
│       │       │   └── CucumberSuiteTest.java # JUnit 5 test runner
│       │       └── stepdefs/                  # Step definitions
│       │           ├── CalculatorSteps.java   # Calculator feature steps
│       │           ├── NotificationSteps.java # Notification feature steps
│       │           ├── UserManagementSteps.java # User management steps
│       │           ├── UserManagementContext.java # Shared context
│       │           └── ParameterTypes.java    # Custom parameter types
│       └── resources/
│           ├── cucumber.properties            # Cucumber configuration
│           └── features/                      # Gherkin feature files
│               ├── calculator.feature         # Calculator scenarios
│               ├── message.feature            # Notification scenarios
│               └── user_management.feature    # User management scenarios
└── target/
    └── cucumber-reports/                      # Generated test reports
        ├── cucumber.html                      # HTML report
        └── cucumber.json                      # JSON report
```

---

## ✅ Prerequisites

Ensure you have the following installed on your system:

- **Java Development Kit (JDK) 17** or higher
  ```bash
  java -version
  ```

- **Apache Maven 3.6+**
  ```bash
  mvn -version
  ```

- **IDE** (Recommended):
  - IntelliJ IDEA with Cucumber plugin
  - VS Code with Cucumber (Gherkin) extension
  - Eclipse with Cucumber Eclipse Plugin

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/cucumber-java-suite.git
cd cucumber-java-suite
```

### 2. Install Dependencies

```bash
mvn clean install
```

This downloads all required dependencies defined in `pom.xml`.

### 3. Verify Setup

Run a quick test to ensure everything is working:

```bash
mvn test
```

You should see all tests passing with colorful console output! 🎉

---

## ▶️ Running Tests

### Run All Tests

```bash
mvn test
```

### Run Only Smoke Tests

```bash
mvn test -Dcucumber.filter.tags=@smoke
```

### Run Only Regression Tests

```bash
mvn test -Dcucumber.filter.tags=@regression
```

### Run Multiple Tags (OR condition)

```bash
mvn test -Dcucumber.filter.tags="@smoke or @regression"
```

### Exclude Specific Tags

```bash
mvn test -Dcucumber.filter.tags="not @wip"
```

### Run from IDE

- **IntelliJ IDEA**: Right-click on `CucumberSuiteTest.java` → Run
- **Run specific feature**: Right-click on any `.feature` file → Run

---

## 📚 Project Components

### Feature Files

Located in `src/test/resources/features/`

#### 1. **calculator.feature**
Demonstrates:
- **Background** steps that run before each scenario
- **Scenario Outline** with multiple examples
- **Custom Parameter Types** (Money)

```gherkin
Background: Calculator is ready
  Given a calculator is initialized

Scenario Outline: Perform addition with different numbers
  When I add <num1> and <num2>
  Then the result should be <expected>

  Examples:
    | num1 | num2 | expected |
    | 5    | 3    | 8        |
    | 10   | 20   | 30       |
```

#### 2. **user_management.feature**
Demonstrates:
- **Background** for common setup
- **Data Tables** using `asMaps()`
- **Tag-based filtering** with `@smoke`

```gherkin
@smoke
Scenario: Create multiple users from data table
  When I create the following users:
    | username | email             | role  | active |
    | user1    | user1@example.com | admin | true   |
    | user2    | user2@example.com | user  | true   |
  Then all users should be created successfully
```

#### 3. **message.feature**
Simple notification feature demonstrating basic Given-When-Then structure.

---

### Step Definitions

Located in `src/test/java/org/example/stepdefs/`

Step definitions map Gherkin steps to Java methods:

```java
@When("I create the following users:")
public void i_create_the_following_users(DataTable dataTable) {
    // Convert DataTable to List of Maps using asMaps()
    List<Map<String, String>> users = dataTable.asMaps();
    
    for (Map<String, String> user : users) {
        String username = user.get("username");
        String email = user.get("email");
        String role = user.get("role");
        // ... create user logic
    }
}
```

---

### Hooks

Located in `src/test/java/org/example/hooks/` ⭐

#### Why Hooks Are in a Separate Package

**Best Practice:** Keeping hooks in a separate package from step definitions provides:

✅ **Clear Separation of Concerns** - Hooks handle cross-cutting concerns (setup/teardown)
✅ **Better Organization** - Easier to locate and maintain
✅ **Reusability** - Can be shared across multiple test suites
✅ **Professional Structure** - Industry-standard approach

#### Types of Hooks Implemented

##### 1. Before Scenario Hook
```java
@Before
public void beforeScenario(Scenario scenario) {
    System.out.println("▶ Starting Scenario: " + scenario.getName());
}
```

##### 2. Tag-Specific Hooks
```java
@Before("@smoke")
public void beforeSmokeTest(Scenario scenario) {
    System.out.println("🔥 [SMOKE TEST] Running critical smoke test");
}
```

##### 3. After Scenario Hook
```java
@After
public void afterScenario(Scenario scenario) {
    if (scenario.isFailed()) {
        System.out.println("❌ FAILED - Error details logged");
        // Take screenshot, log details, etc.
    }
}
```

##### 4. Step-Level Hooks (Optional)
```java
@BeforeStep
public void beforeStep(Scenario scenario) {
    // Executes before each step
}

@AfterStep
public void afterStep(Scenario scenario) {
    // Useful for taking screenshots after each step
}
```

---

### Custom Parameter Types

Located in `src/test/java/org/example/stepdefs/ParameterTypes.java`

Define custom types for automatic conversion in step definitions:

```java
@ParameterType("\\$([0-9]+\\.?[0-9]*)")
public Money money(String amount) {
    return new Money(Double.parseDouble(amount));
}
```

Usage in feature file:
```gherkin
When I add $50.00 and $25.50
Then the total should be $75.50
```

---

### Test Runner Configuration

Located in `src/test/java/org/example/runner/CucumberSuiteTest.java`

#### ⭐ Understanding the Glue Property (CRITICAL)

```java
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example")
public class CucumberSuiteTest {}
```

#### The Glue Property Explained

**What is "Glue"?**
The `GLUE_PROPERTY_NAME` tells Cucumber where to find your step definitions, hooks, and parameter types.

**Why `"org.example"` instead of `"org.example.stepdefs"`?**

When you set:
```java
value = "org.example"
```

Cucumber scans **ALL sub-packages** under `org.example`:
- ✅ `org.example.stepdefs` → Step definitions
- ✅ `org.example.hooks` → Hooks
- ✅ `org.example.runner` → (ignored, not Cucumber-related)

**If you used `"org.example.stepdefs"`:**
- ✅ Step definitions found
- ❌ Hooks NOT found (because they're in `org.example.hooks`)
- ❌ Tests would run without hooks executing!

#### Multiple Glue Paths (Alternative Approach)

You can also specify multiple glue paths:

```java
@ConfigurationParameter(
    key = GLUE_PROPERTY_NAME, 
    value = "org.example.stepdefs, org.example.hooks"
)
```

**Recommendation:** Use the parent package (`org.example`) for simplicity and flexibility.

---

## 🔑 Important Concepts

### 1. Background vs @Before Hook

| Feature | Background | @Before Hook |
|---------|-----------|--------------|
| **Defined in** | Feature file (Gherkin) | Java code |
| **Visibility** | Part of test specification | Technical implementation |
| **Purpose** | Business-readable setup steps | Technical setup (DB, config, etc.) |
| **When to use** | When setup is part of the story | When setup is technical concern |

**Example Background:**
```gherkin
Background: User is logged in
  Given the user management system is initialized
  And the database is clean
```

**Example @Before Hook:**
```java
@Before
public void setupTestEnvironment() {
    // Initialize database connection
    // Set up test data
    // Configure system properties
}
```

### 2. Scenario vs Scenario Outline

**Scenario** - Single test case:
```gherkin
Scenario: Create a user
  When I create a user "john"
  Then the user "john" exists
```

**Scenario Outline** - Data-driven test:
```gherkin
Scenario Outline: Create users
  When I create a user "<username>"
  Then the user "<username>" exists

  Examples:
    | username |
    | john     |
    | jane     |
    | bob      |
```

### 3. DataTable.asMaps() Explained

Converts a data table to `List<Map<String, String>>`:

**Feature File:**
```gherkin
| username | email             | role  |
| user1    | user1@example.com | admin |
| user2    | user2@example.com | user  |
```

**Java Code:**
```java
List<Map<String, String>> users = dataTable.asMaps();
// users.get(0) → {"username": "user1", "email": "user1@example.com", "role": "admin"}
// users.get(1) → {"username": "user2", "email": "user2@example.com", "role": "user"}
```

---

## 📊 Test Reports

After running tests, view generated reports:

### 1. HTML Report (Visual)
```bash
open target/cucumber-reports/cucumber.html
```

Features:
- ✅ Beautiful, interactive UI
- ✅ Passed/Failed scenarios with details
- ✅ Step-by-step execution
- ✅ Screenshots (if implemented)

### 2. JSON Report (CI/CD Integration)
```
target/cucumber-reports/cucumber.json
```

Use with:
- Jenkins Cucumber Reports Plugin
- GitLab CI
- CircleCI
- Custom reporting tools

### 3. Console Output (Real-time)

Enhanced with hooks showing:
```
================================================================================
▶ Starting Scenario: Create multiple users from data table
  Tags: [@smoke]
================================================================================
🔥 [SMOKE TEST] Running critical smoke test
✓ User management system initialized
✓ Database cleaned
✓ Logged in as administrator
Creating 3 users from data table:
  ✓ user1 (user1@example.com) - Role: admin, Active: true
  ✓ user2 (user2@example.com) - Role: user, Active: true
  ✓ user3 (user3@example.com) - Role: moderator, Active: false
✓ All users created successfully
--------------------------------------------------------------------------------
✓ Finished Scenario: Create multiple users from data table
  Status: PASSED
================================================================================
```

---

## 🎯 Best Practices

### 1. **Feature File Organization**
```
features/
├── smoke/              # Critical smoke tests
│   └── login.feature
├── regression/         # Full regression suite
│   └── user_crud.feature
└── wip/                # Work in progress
    └── new_feature.feature
```

### 2. **Step Definition Naming**
- Use descriptive method names
- Follow Gherkin step text closely
- Keep methods focused (single responsibility)

### 3. **Using Tags Effectively**
```gherkin
@smoke @login
Scenario: Successful login

@regression @user-management
Scenario: Create user with valid data

@wip @ignore
Scenario: Feature under development
```

### 4. **Avoiding Code Duplication**
- Use shared context classes (e.g., `UserManagementContext`)
- Create reusable step definitions
- Leverage Background for common setup

### 5. **Hook Ordering**
```java
@After(order = 1)   // Runs last
public void cleanupData() { }

@After(order = 10)  // Runs first
public void takeScreenshot() { }
```

Higher order number = executes first

---

## 🔧 Troubleshooting

### Issue: Hooks Not Executing

**Symptom:** Step definitions work, but hooks are not running.

**Solution:** Check your glue path in `CucumberSuiteTest.java`:

```java
// ❌ WRONG - Only scans stepdefs package
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example.stepdefs")

// ✅ CORRECT - Scans all sub-packages
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example")
```

### Issue: Undefined Steps

**Symptom:** Cucumber can't find step definitions.

**Solution:**
1. Verify glue path includes the step definitions package
2. Check method annotations (`@Given`, `@When`, `@Then`)
3. Ensure parameter types match (e.g., `{string}` → `String`)

### Issue: DataTable Not Working

**Symptom:** Error when using data tables.

**Solution:**
```java
// Import the correct DataTable class
import io.cucumber.datatable.DataTable;

// Use asMaps() method
List<Map<String, String>> data = dataTable.asMaps();
```

### Issue: Maven Compilation Errors

**Solution:**
```bash
mvn clean compile
mvn clean test-compile
```

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Commit your changes**
   ```bash
   git commit -m 'Add amazing feature'
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```
5. **Open a Pull Request**

### Contribution Guidelines

- Follow existing code style and structure
- Add tests for new features
- Update documentation (README, JavaDocs)
- Ensure all tests pass before submitting

---

## 📝 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- **Cucumber Team** - For the amazing BDD framework
- **JUnit Team** - For the robust testing platform
- **Community Contributors** - For feedback and improvements

---

## 📞 Contact & Support

**Creator:** Abhishek Omprakash

For questions, issues, or suggestions:

- **GitHub Issues:** [Create an issue](https://github.com/yourusername/cucumber-java-suite/issues)
- **Documentation:** [Cucumber Official Docs](https://cucumber.io/docs/cucumber/)
- **JUnit 5 Guide:** [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

---

## 🚀 Quick Reference Commands

```bash
# Run all tests
mvn test

# Run smoke tests only
mvn test -Dcucumber.filter.tags=@smoke

# Run regression tests only
mvn test -Dcucumber.filter.tags=@regression

# Clean and rebuild
mvn clean install

# Generate reports
mvn test
open target/cucumber-reports/cucumber.html

# Check for undefined steps (dry run)
mvn test -Dcucumber.execution.dry-run=true
```

---

## 📖 Additional Resources

### Learning Resources
- [Cucumber School](https://school.cucumber.io/) - Free BDD training
- [Cucumber Gherkin Reference](https://cucumber.io/docs/gherkin/reference/)
- [JUnit 5 Platform Suite](https://junit.org/junit5/docs/current/user-guide/#junit-platform-suite-engine)

### Related Projects
- [Cucumber-JVM Examples](https://github.com/cucumber/cucumber-jvm)
- [Serenity BDD](https://serenity-bdd.github.io/)
- [RestAssured](https://rest-assured.io/) - For API testing

---

<div align="center">

**⭐ If this project helped you, please give it a star! ⭐**

Made with ❤️ by [Abhishek Omprakash](https://github.com/yourusername)

</div>
