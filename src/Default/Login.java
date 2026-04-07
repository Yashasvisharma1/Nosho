package Default;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login extends LandingPage {

    private static final String USERNAME = "yashasvi.sharma@techglock.com";
    private static final String PASSWORD = "Admin@123";
    private static final String[] EMAIL_SELECTORS = {
        "input[type='email']",
        "input[name='email']",
        "input[id='email']",
        "input[placeholder*='email' i]",
        "input[placeholder*='username' i]"
    };

    private static final String[] PASSWORD_SELECTORS = {
        "input[type='password']",
        "input[name='password']",
        "input[id='password']"
    };

    private static final String[] SUBMIT_SELECTORS = {
        "button[type='submit']",
        "input[type='submit']",
        "button[id*='login' i]",
        "button[class*='login' i]",
        "button[class*='submit' i]"
    };
    private int passed = 0;
    private int failed = 0;

    public static void main(String[] args) {
        Login test = new Login();

        try {
            LandingPage.setupBrowser();
            LandingPage.openLandingPage();
            LandingPage.handleBrowserNotification();
            LandingPage.acceptCookieBanner();
          

            test.test_TC01_PageLoads();
            test.test_TC03_LoginWithBlankCredentials();
            test.test_TC04_LoginWithInvalidPassword();
            test.test_TC05_LoginWithInvalidEmail();
            test.test_TC02_LoginWithValidCredentials();
        } finally {
            test.printSummary();
        }
    }

    void test_TC01_PageLoads() {
        String tcId = "TC01";
        System.out.println(":arrow_forward: " + tcId + " - Login page loads");

        try {
            navigateToLogin();

            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='email']")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']"))
            ));

            String title = driver.getTitle();
            assertNotEmpty(title, "Page title should not be empty");

            System.out.println("  Page title: " + title);
            pass(tcId, "Page loaded successfully.");
        } catch (Exception e) {
            fail(tcId, e.getMessage());
        }
    }
   

    void test_TC03_LoginWithBlankCredentials() {
        String tcId = "TC03";
        System.out.println(":arrow_forward: " + tcId + " - Blank credentials");

        try {
            navigateToLogin();
            clearCredentials();
            clickSubmit();

            Thread.sleep(1200);

            String currentUrl = driver.getCurrentUrl();
            boolean staysOnLogin = currentUrl.contains("login");

            boolean errorShown = isElementPresent(By.cssSelector(
                "[class*='error'], [class*='alert'], [role='alert'], .invalid-feedback, :invalid"
            ));

            assertTrue(staysOnLogin || errorShown,
                "App should show an error or stay on login page for blank credentials");

            pass(tcId, "Blank credential validation works. Error shown: " + errorShown);
        } catch (Exception e) {
            fail(tcId, e.getMessage());
        }
    }
    void test_TC04_LoginWithInvalidPassword() {
        String tcId = "TC04";
        System.out.println(":arrow_forward: " + tcId + " - Invalid password");

        try {
            navigateToLogin();
            fillCredentials(USERNAME, "WrongPassword@999");
            clickSubmit();

            Thread.sleep(1500);

            String currentUrl = driver.getCurrentUrl();
            boolean staysOnLogin = currentUrl.contains("login");

            boolean errorShown = isElementPresent(By.cssSelector(
                "[class*='error'], [class*='alert'], [role='alert'], .toast, .notification"
            ));

            assertTrue(staysOnLogin || errorShown, "App should reject wrong password");

            pass(tcId, "Invalid password rejected. Error shown: " + errorShown);
        } catch (Exception e) {
            fail(tcId, e.getMessage());
        }
    }

    void test_TC05_LoginWithInvalidEmail() {
        String tcId = "TC05";
        System.out.println(":arrow_forward: " + tcId + " - Invalid email format");

        try {
            navigateToLogin();
            fillCredentials("not-an-email", PASSWORD);
            clickSubmit();

            Thread.sleep(1500);

            String currentUrl = driver.getCurrentUrl();
            boolean staysOnLogin = currentUrl.contains("login");

            boolean errorShown = isElementPresent(By.cssSelector(
                "[class*='error'], [class*='alert'], [role='alert'], :invalid"
            ));

            assertTrue(staysOnLogin || errorShown, "App should reject invalid email format");

            pass(tcId, "Invalid email format handled. Error shown: " + errorShown);
        } catch (Exception e) {
            fail(tcId, e.getMessage());
        }
    }

    void test_TC02_LoginWithValidCredentials() {
        String tcId = "TC02";
        System.out.println(":arrow_forward: " + tcId + " - Valid login");

        try {
            navigateToLogin();
            fillCredentials(USERNAME, PASSWORD);
            clickSubmit();

            wait.until(ExpectedConditions.or(
                ExpectedConditions.not(ExpectedConditions.urlContains("login")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                    "[class*='dashboard'], [class*='home'], [class*='navbar'], nav, header"
                ))
            ));

            String currentUrl = driver.getCurrentUrl();
            System.out.println("  Redirected to: " + currentUrl);

            assertFalse(currentUrl.contains("login"),
                "URL should not contain 'login' after successful login");

            pass(tcId, "Login successful. Redirected to: " + currentUrl);
        } catch (Exception e) {
            fail(tcId, e.getMessage());
        }
    }

    void navigateToLogin() {
        if (!driver.getCurrentUrl().contains("login")) {
            LandingPage.clickLoginButton();
        }

        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("login"),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='email']")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']"))
        ));
    }

	@SuppressWarnings("deprecation")
    void fillCredentials(String email, String password) throws InterruptedException {
        clearCredentials();

        WebElement emailField = findFirstVisible(EMAIL_SELECTORS);
        forceClear(emailField);
        slowType(emailField, email);

        String actualEmail = emailField.getAttribute("value");
        if (!actualEmail.equals(email)) {
            forceClear(emailField);
            slowType(emailField, email);

            actualEmail = emailField.getAttribute("value");
            if (!actualEmail.equals(email)) {
                throw new RuntimeException(
                    "Email field mismatch. Expected: " + email + " but found: " + actualEmail
                );
            }
        }

        WebElement passwordField = findFirstVisible(PASSWORD_SELECTORS);
        forceClear(passwordField);
        slowType(passwordField, password);

        String actualPassword = passwordField.getAttribute("value");
        if (!actualPassword.equals(password)) {
            forceClear(passwordField);
            slowType(passwordField, password);

            actualPassword = passwordField.getAttribute("value");
            if (!actualPassword.equals(password)) {
                throw new RuntimeException(
                    "Password field mismatch. Expected: " + password + " but found: " + actualPassword
                );
            }
        }
    }

    void clearCredentials() {
        try {
            WebElement emailField = findFirstVisible(EMAIL_SELECTORS);
            forceClear(emailField);
        } catch (Exception ignored) {
        }

        try {
            WebElement passwordField = findFirstVisible(PASSWORD_SELECTORS);
            forceClear(passwordField);
        } catch (Exception ignored) {
        }
    }

    void clickSubmit() {
        WebElement btn = findFirstVisible(SUBMIT_SELECTORS);
        scrollIntoView(btn);
        clickElement(btn);
    }

    WebElement findFirstVisible(String[] selectors) {
        for (String selector : selectors) {
            try {
                WebElement el = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector))
                );
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {
            }
        }
        throw new RuntimeException("No visible element found for selectors: " + String.join(", ", selectors));
    }

    void forceClear(WebElement element) {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
    }

    void slowType(WebElement element, String text) throws InterruptedException {
        for (char ch : text.toCharArray()) {
            element.sendKeys(String.valueOf(ch));
            Thread.sleep(50);
        }
    }

    boolean isElementPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("ASSERT FAILED: " + message);
        }
    }

    void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError("ASSERT FAILED: " + message);
        }
    }

    void assertNotEmpty(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new AssertionError("ASSERT FAILED: " + message);
        }
    }

    void pass(String tcId, String msg) {
        passed++;
        System.out.println("  PASS [" + tcId + "] " + msg + "\n");
    }

    void fail(String tcId, String msg) {
        failed++;
        System.out.println("  FAIL [" + tcId + "] " + msg + "\n");
    }

    void printSummary() {
        System.out.println("======================================");
        System.out.println("TEST SUMMARY");
        System.out.println("Total  : " + (passed + failed));
        System.out.println("Passed : " + passed);
        System.out.println("Failed : " + failed);
        System.out.println("======================================");
    }
}
