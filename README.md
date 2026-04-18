# Demo Webshop - QA Automation Assessment (SDET 5+ YoE)

An enterprise-grade, End-to-End Test Automation execution framework built with Java, Selenium WebDriver, TestNG, and ExtentReports. Employs advanced implementation paradigms like explicit PageFactory, thread-safe asynchronous Driver binding, and declarative assertions.

## Technology Topography
* **Java** (SDK 11 or 17 recommended)
* **Maven** (Dependency & Build Management)
* **Selenium WebDriver** (4.18.x - UI Interaction)
* **TestNG** (7.9.x - Test Orchestration)
* **WebDriverManager** (Automated Driver binary management)
* **ExtentReports 5** (Telemetry generation & HTML Reports)
* **SLF4J + Logback** (Structured asynchronous logging)
* **JSON/Jackson** (Describing Data-Driven Payloads)

## What is Working / Implemented Features
* **E2E Purchase Flow Automation**: Completely automated workflows replicating user registration, login, product discovery, add to cart, and checkout processes on the Demo Webshop.
* **Granular Telemetry**: Automated capture of 11 distinct execution checkpoints.
* **Data-Driven Configuration**: Tests orchestrate around data payloads supplied via JSON files.
* **Cross-Browser Verification**: Tests configured for concurrent Chrome and Edge validation.
* **Failure Artifacts**: Auto-capturing of DOM screenshots upon assertion failures integrated directly into ExtentReports.

## Setup & Execution Context
1. **Download/Extract** the repository directory.
2. Ensure you have **Java JDK** and **Maven** installed locally.
3. Access default environmental values securely bound within `src/main/resources/application.properties`.
4. Trigger the automated test orchestration via CLI:

```bash
mvn clean test
```

## Reviewing the Artifact Telemetry
* **Console Streaming:** Real-time execution context mutation events are strictly parsed identically to stdout via SLF4J.
* **Rich HTML Reports:** Once completion halts, direct your browser to `target/ExtentReport.html`. Complete with encoded execution state visualization upon fault.

## Execution Framework Tenets
* **Strict PageFactory Utilization:** Implements `@FindBy` tied natively alongside `AjaxElementLocatorFactory` bypassing traditional brittle state loops.
* **Component Layer Modularity:** Driver mapping encapsulated fully into decoupled singleton execution handlers resolving parallelization leaks.

## Contributing
When expanding upon this framework, please ensure that you follow the architectural patterns dictated in `.github/AI_INSTRUCTIONS.md`. Any new page classes must utilize `PageFactory` and ensure thread-safety. Pull Requests must clear execution gates before merge.
