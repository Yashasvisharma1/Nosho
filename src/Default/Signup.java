package Default;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Signup extends LandingPage {

    private static final Duration FORM_WAIT_TIMEOUT = Duration.ofSeconds(3);
    private static final Duration SCREEN_WAIT_TIMEOUT = Duration.ofSeconds(8);

    private static final String INVALID_USERNAME = "ab@";
    private static final String INVALID_FULL_NAME = "A1";
    private static final String INVALID_EMAIL = "wrongemail";
    private static final String INVALID_PHONE = "98AB7654";
    private static final String INVALID_PASSWORD = "admin123";

    private static final String VALID_USERNAME = "anilsharma";
    private static final String VALID_FULL_NAME = "Anil";
    private static final String VALID_EMAIL = "anil@gmail.com";
    private static final String VALID_PHONE = "9987456735";
    private static final String VALID_PASSWORD = "Admin@123";

    private static final String[] REGISTER_FREE_SELECTORS = {
        "//button[normalize-space()='Register free']",
        "//a[normalize-space()='Register free']",
        "//*[self::a or self::button or self::span][normalize-space()='Register free']",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'register free')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'register free')]",
        "a[href*='signup']",
        "a[href*='register']",
        "button[class*='register' i]",
        "button[id*='register' i]"
    };

    private static final String[] USERNAME_SELECTORS = {
        "input[name='username']",
        "input[id='username']",
        "input[placeholder*='username' i]"
    };

    private static final String[] FULL_NAME_SELECTORS = {
        "input[name='fullName']",
        "input[name='fullname']",
        "input[id='fullName']",
        "input[id='fullname']",
        "input[placeholder*='full name' i]"
    };

    private static final String[] EMAIL_SELECTORS = {
        "input[type='email']",
        "input[name='email']",
        "input[id='email']",
        "input[placeholder*='email' i]"
    };

    private static final String[] COUNTRY_CODE_SELECTORS = {
        "select[name*='country' i]",
        "select[id*='country' i]",
        "select[name*='code' i]",
        "select[id*='code' i]",
        "[role='combobox'][aria-label*='country' i]",
        "[role='combobox'][aria-label*='code' i]",
        "select"
    };

    private static final String[] PHONE_SELECTORS = {
        "input[type='tel']",
        "input[name='phone']",
        "input[name='mobile']",
        "input[id='phone']",
        "input[id='mobile']",
        "input[placeholder*='phone' i]",
        "input[placeholder*='mobile' i]"
    };

    private static final String[] PASSWORD_SELECTORS = {
        "input[type='password'][name='password']",
        "input[type='password'][id='password']",
        "input[name='password']",
        "input[id='password']",
        "input[type='password']"
    };

    private static final String[] CREATE_ACCOUNT_BUTTON_SELECTORS = {
        "//button[normalize-space()='Create account']",
        "//a[normalize-space()='Create account']",
        "//*[self::button or self::a or self::span][normalize-space()='Create account']",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'create account')]",
        "button[class*='create' i]",
        "button[id*='create' i]",
        "button[type='submit']",
        "input[type='submit']"
    };

    public static void main(String[] args) {
        Signup test = new Signup();

        try {
            setupBrowser();
            openLandingPage();
            handleBrowserNotification();
            acceptCookieBanner();
            test.navigateToSignupFromLanding();
            test.tc_signup_invalid_then_valid_create_account();
        } finally {
            // keep browser open for review
        }
    }

    void tc_signup_invalid_then_valid_create_account() {
        System.out.println(">> TC_SIGNUP_E2E - Invalid all fields check and valid all fields check");

        try {
            navigateToSignup();

            fillSignupForm(
                INVALID_USERNAME,
                INVALID_FULL_NAME,
                INVALID_EMAIL,
                INVALID_PHONE,
                INVALID_PASSWORD
            );
            clickCreateAccountButton();
            assertSignupInvalidState();
            assertTrue(isSignupSubmissionRejected(),
                "Signup should fail for invalid credentials");
            System.out.println("   PASS - Invalid all fields check passed");

            fillSignupForm(
                VALID_USERNAME,
                VALID_FULL_NAME,
                VALID_EMAIL,
                VALID_PHONE,
                VALID_PASSWORD
            );
            assertSignupFormValues(
                VALID_USERNAME,
                VALID_FULL_NAME,
                VALID_EMAIL,
                VALID_PHONE,
                VALID_PASSWORD
            );
            clickCreateAccountButton();
            System.out.println("   PASS - Valid all fields check passed and Create account clicked");
        } catch (Exception e) {
            System.out.println("   FAIL - " + e.getMessage());
            e.printStackTrace();
        }
    }

    void navigateToSignupFromLanding() {
        if (!(driver.getCurrentUrl().contains("signup") || driver.getCurrentUrl().contains("register"))) {
            if (!driver.getCurrentUrl().contains("login")) {
                LandingPage.clickLoginButton();
                waitForLoginScreen();
            }
            clickRegisterFree();
        }
        waitForSignupScreen();
    }

    void clickRegisterFree() {
        waitForLoginScreen();

        for (String selector : REGISTER_FREE_SELECTORS) {
            try {
                WebElement element = LandingPage.findDisplayedNow(selector);
                if (element == null) {
                    element = findElementBySelector(selector, SCREEN_WAIT_TIMEOUT);
                }

                if (element != null && element.isDisplayed()) {
                    if ("span".equalsIgnoreCase(element.getTagName())) {
                        try {
                            WebElement parentLink = element.findElement(By.xpath("./ancestor::a[1]"));
                            scrollIntoView(parentLink);
                            clickElement(parentLink);
                        } catch (Exception e1) {
                            WebElement parentButton = element.findElement(By.xpath("./ancestor::button[1]"));
                            scrollIntoView(parentButton);
                            clickElement(parentButton);
                        }
                    } else {
                        scrollIntoView(element);
                        clickElement(element);
                    }
                    waitForSignupScreen();
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        throw new RuntimeException("'Register free' not found on login page");
    }

    void waitForLoginScreen() {
        new WebDriverWait(driver, SCREEN_WAIT_TIMEOUT).until(ExpectedConditions.or(
            ExpectedConditions.urlContains("login"),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='email']")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']"))
        ));
    }

    void waitForSignupScreen() {
        new WebDriverWait(driver, SCREEN_WAIT_TIMEOUT).until(ExpectedConditions.or(
            ExpectedConditions.urlContains("signup"),
            ExpectedConditions.urlContains("register"),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='username']")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']"))
        ));
    }

    void navigateToSignup() {
        if (!(driver.getCurrentUrl().contains("signup") || driver.getCurrentUrl().contains("register"))) {
            navigateToSignupFromLanding();
        } else {
            waitForSignupScreen();
        }
    }

    void fillSignupForm(String username, String fullName, String email, String phone, String password)
        throws InterruptedException {
        setFieldValue(findFirstVisible(USERNAME_SELECTORS), username);
        setFieldValue(findFirstVisible(FULL_NAME_SELECTORS), fullName);
        setFieldValue(findFirstVisible(EMAIL_SELECTORS), email);
        selectCountryCode("+91");
        setFieldValue(findFirstVisible(PHONE_SELECTORS), phone);
        setFieldValue(findFirstVisible(PASSWORD_SELECTORS), password);
    }

    @SuppressWarnings("deprecation")
    void assertSignupFormValues(String username, String fullName, String email, String phone, String password) {
        assertEquals(findFirstVisible(USERNAME_SELECTORS).getAttribute("value"), username,
            "Username should accept correct credentials");
        assertEquals(findFirstVisible(FULL_NAME_SELECTORS).getAttribute("value"), fullName,
            "Full name should accept correct credentials");
        assertEquals(findFirstVisible(EMAIL_SELECTORS).getAttribute("value"), email,
            "Email should accept correct credentials");
        assertEquals(normalizeDigits(findFirstVisible(PHONE_SELECTORS).getAttribute("value")), normalizeDigits(phone),
            "Phone should accept correct credentials");
        assertEquals(findFirstVisible(PASSWORD_SELECTORS).getAttribute("value"), password,
            "Password should accept correct credentials");
    }

    void assertSignupInvalidState() {
        assertTrue(isValidationStatePresent(findFirstVisible(USERNAME_SELECTORS)),
            "Username should show validation for invalid value");
        assertTrue(isValidationStatePresent(findFirstVisible(FULL_NAME_SELECTORS)),
            "Full name should show validation for invalid value");
        assertTrue(isValidationStatePresent(findFirstVisible(EMAIL_SELECTORS)),
            "Email should show validation for invalid value");
        assertTrue(isValidationStatePresent(findFirstVisible(PHONE_SELECTORS)),
            "Phone should show validation for invalid value");
        assertTrue(isValidationStatePresent(findFirstVisible(PASSWORD_SELECTORS)),
            "Password should show validation for invalid value");
    }

    void selectCountryCode(String code) {
        for (String selector : COUNTRY_CODE_SELECTORS) {
            try {
                WebElement countryCode = findFirstVisible(new String[] { selector });
                scrollIntoView(countryCode);
                clickElement(countryCode);

                if ("select".equalsIgnoreCase(countryCode.getTagName())) {
                    countryCode.sendKeys(Keys.ARROW_DOWN);
                    countryCode.sendKeys(Keys.ENTER);
                } else {
                    countryCode.sendKeys(code);
                    countryCode.sendKeys(Keys.ENTER);
                }
                return;
            } catch (Exception ignored) {
            }
        }
    }

    void clickCreateAccountButton() {
        WebElement button = findFirstVisible(CREATE_ACCOUNT_BUTTON_SELECTORS);
        scrollIntoView(button);
        clickElement(button);
    }

    boolean isSignupSubmissionRejected() {
        boolean stillOnSignup = driver.getCurrentUrl().contains("signup")
            || driver.getCurrentUrl().contains("register");

        boolean validationVisible = isElementPresent(By.cssSelector(
            "[class*='error'], [class*='alert'], [role='alert'], .invalid-feedback, :invalid"
        ));

        return stillOnSignup && validationVisible;
    }

    @SuppressWarnings("deprecation")
    boolean isValidationStatePresent(WebElement field) {
        String ariaInvalid = field.getAttribute("aria-invalid");
        if ("true".equalsIgnoreCase(ariaInvalid)) {
            return true;
        }

        Object validity = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "if(arguments[0].checkValidity){return arguments[0].checkValidity();} return true;", field);
        if (validity instanceof Boolean && !((Boolean) validity)) {
            return true;
        }

        return isElementPresent(By.cssSelector(
            "[class*='error'], [class*='alert'], [role='alert'], .invalid-feedback, :invalid"));
    }

    @SuppressWarnings("deprecation")
    void setFieldValue(WebElement field, String value) throws InterruptedException {
        scrollIntoView(field);
        clickElement(field);
        fastSetFieldValue(field, value);

        String actualValue = field.getAttribute("value");
        if (actualValue == null || !normalizeForCompare(actualValue).equals(normalizeForCompare(value))) {
            forceClear(field);
            field.sendKeys(value);
            actualValue = field.getAttribute("value");
        }

        if (actualValue == null || !normalizeForCompare(actualValue).equals(normalizeForCompare(value))) {
            throw new RuntimeException("Field value mismatch. Expected: " + value + " but got: " + actualValue);
        }

        field.sendKeys(Keys.TAB);
        Thread.sleep(100);
    }

    void fastSetFieldValue(WebElement field, String value) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].focus();" +
                "var setter = Object.getOwnPropertyDescriptor(arguments[0].__proto__, 'value');" +
                "if (setter && setter.set) {" +
                "  setter.set.call(arguments[0], '');" +
                "  setter.set.call(arguments[0], arguments[1]);" +
                "} else {" +
                "  arguments[0].value='';" +
                "  arguments[0].value=arguments[1];" +
                "}" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                field, value
            );
        } catch (Exception ignored) {
        }
    }

    WebElement findFirstVisible(String[] selectors) {
        WebDriverWait shortWait = new WebDriverWait(driver, FORM_WAIT_TIMEOUT);

        for (String selector : selectors) {
            try {
                WebElement element = LandingPage.findDisplayedNow(selector);
                if (element != null) {
                    return element;
                }

                By locator = selector.startsWith("//") ? By.xpath(selector) : By.cssSelector(selector);
                element = shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                if (element != null && element.isDisplayed()) {
                    return element;
                }
            } catch (Exception ignored) {
            }
        }

        throw new RuntimeException("No visible element found for selectors");
    }

    WebElement findElementBySelector(String selector, Duration timeout) {
        WebDriverWait shortWait = new WebDriverWait(driver, timeout);
        By locator = selector.startsWith("//") ? By.xpath(selector) : By.cssSelector(selector);
        return shortWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    void forceClear(WebElement element) {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
    }

    String normalizeForCompare(String value) {
        return value == null ? "" : value.trim();
    }

    String normalizeDigits(String value) {
        return value == null ? "" : value.replaceAll("\\D", "");
    }

    void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    void assertEquals(String actual, String expected, String message) {
        if (actual == null || !actual.equals(expected)) {
            throw new AssertionError(message + " | Expected: " + expected + " but got: " + actual);
        }
    }

    boolean isElementPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
