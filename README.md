# 🛒 DemoBlaze Selenium-Java Automation Framework

## 📌 Overview

This project is a **Selenium + Java Test Automation Framework** built using:

* Selenium WebDriver
* TestNG
* Page Object Model (POM)
* WebDriverManager
* Extent Reports
* Excel Data-Driven Testing

---

## 🚀 Features

* ✅ Page Object Model (POM) design
* ✅ Data-driven testing using Excel
* ✅ Screenshot capture on failure
* ✅ Extent HTML reporting
* ✅ Retry mechanism for failed tests
* ✅ Config-driven execution
* ✅ Parallel execution support
* ✅ Clean and reusable framework structure

---

## Project Workflow

<img width="4656" height="1637" alt="Project12Workflow drawio" src="https://github.com/user-attachments/assets/fb1b7fef-35bf-4889-9e70-1678112ac74b" />

---

## 📂 Project Structure

```text
DemoBlaze/
│
├── src/main/java
│   ├── base
│   │   └── BasePage.java              # Common reusable WebDriver methods (click, type, wait, etc.)
│   │
│   ├── pages
│   │   ├── AuthPage.java              # Handles login & signup actions
│   │   ├── CartPage.java              # Handles cart operations
│   │   ├── HomePage.java              # Handles navigation
│   │   ├── OrderPage.java             # Handles order placement & confirmation
│   │   └── ProductPage.java           # Handles product browsing & selection
│   │
│   └── utils
│       ├── ConfigReader.java          # Reads config.properties values
│       ├── ExcelUtil.java             # Reads test data from Excel
│       ├── ExtentManager.java         # Configures Extent Reports
│       └── ScreenshotUtil.java        # Captures screenshots on failure
│
├── src/test/java
│   ├── base
│   │   └── BaseTest.java              # WebDriver setup & teardown (ThreadLocal for parallel)
│   │
│   ├── listeners
│   │   ├── RetryListener.java         # Attaches retry logic to tests
│   │   └── TestListener.java          # Captures screenshots & logs in Extent report
│   │
│   ├── tests
│   │   ├── AuthTest.java              # Login & signup test cases
│   │   ├── CartTest.java              # Cart operations test cases
│   │   ├── FormValidationTest.java    # Form validation scenarios
│   │   ├── OrderTest.java             # Order placement tests
│   │   └── ProductTest.java           # Product browsing tests
│   │
│   └── utils
│       └── RetryAnalyzer.java         # Retry failed tests automatically
│
├── src/test/resources
│   ├── testdata
│   │   └── LoginData.xlsx             # Excel file for login data provider
│   └── config.properties             # Config (browser, baseUrl, headless, credentials)
│
├── reports/                          # Extent report output
├── screenshots/                      # Screenshots captured on failure
├── target/                           # Maven build output
├── test-output/                      # TestNG default reports
│
├── pom.xml                           # Maven dependencies
└── testng.xml                        # Test suite configuration
```

---

## 🧪 Test Modules Covered

### 1. User Authentication

* Verify successful sign-up with a unique username and password via modal
* Verify successful login and confirm username is displayed in navbar
* Verify login failure with invalid credentials and validate alert message
* Verify logout removes the username from the navbar

---

### 2. Product Browse by Category

* Verify Phones category displays phone products
* Verify Laptops category displays laptop products
* Select a product and verify name, price, and description
* Verify Home navigation reloads full product listing

---

### 3. Shopping Cart

* Add a product and verify it appears in cart with correct name and price
* Add two products and verify both are present
* Delete a product and verify it is removed
* Verify total price updates correctly after deletion

---

### 4. Order Placement

* Place an order with valid details and verify confirmation alert
* Verify order ID is present in confirmation message
* Attempt order with missing details and validate alert behavior

---

### 5. Form Validations

* Verify sign-up with existing username shows appropriate alert
* Verify login with empty fields triggers validation behavior
* Verify modal input fields accept and retain entered values

---

## ⚙️ Configuration

### 📄 `config.properties`

```properties
browser=chrome
baseUrl=https://www.demoblaze.com
timeout=10
headless=true
username=uour_username
password=your_password
```

---

## ▶️ How to Run Tests

### Using Maven

```bash
mvn test
```

### Using TestNG

Run:

```text
testng.xml
```

---

## 📊 Reporting

* 📄 Extent Report:
  `reports/ExtentReport.html`

* 📸 Screenshots (on failure):
  `screenshots/`

---

## 👩‍💻 Author

Vaishnavi

---
## 🏁 Conclusion
This project delivers a robust Selenium-based automation framework covering all critical user flows of the DemoBlaze application. It follows best practices with a scalable, maintainable design and reliable test execution.
