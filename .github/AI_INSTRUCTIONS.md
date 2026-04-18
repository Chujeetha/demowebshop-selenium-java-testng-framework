# AI Instructions & Patterns

This document outlines the architectural patterns and specific conventions established in this project. All AI agents and code generators modifying this repository MUST adhere to these patterns.

## Architectural Patterns

1. **Page Object Model (POM) with PageFactory**:
   - Use `@FindBy` annotations for web elements.
   - Initialize elements using `PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeout), this);` in page constructors to ensure dynamic waiting and minimize `StaleElementReferenceException`.
   - Separate state and behavior. Pages should return new Page instances when navigating.

2. **Test Orchestration (TestNG)**:
   - Use TestNG for execution orchestration.
   - Rely on `BaseTest` or base configurations for driver setup and teardown.
   - Use `@DataProvider` for parameterizing tests from external sources (e.g., JSON payload generation).

3. **Driver Management**:
   - Utilize `WebDriverManager` from Bonigarcia.
   - Driver scoping must remain thread-safe to avoid static crossover in parallel execution.

4. **Telemetry and Reporting**:
   - **ExtentReports 5**: Log meaningful checkpoints (`ExtentTestManager` or similar context logging). Incorporate Screenshots upon `@AfterMethod` test failure contexts into the report.
   - **SLF4J + Logback**: Perform structured logging for all significant events. Avoid `System.out.println`. 

5. **Assertions**:
   - Use declarative TestNG or AssertJ assertions to validate logic points explicitly (e.g., verifying checkout total).

When extending this framework, apply these exact standards to maintain structural integrity.
