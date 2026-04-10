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
    private static final String INVALID_PHONE = "+91 7654";
    private static final String INVALID_PASSWORD = "admin123";

    private static final String VALID_USERNAME = "manualtest";
    private static final String VALID_FULL_NAME = "Anil";
    private static final String VALID_EMAIL = "manualtest@gmail.com";
    private static final String VALID_PHONE = "+998 74 567 35";
    private static final String VALID_PASSWORD = "Admin@123";
    private static final String NEW_USER_PREFIX = "newuserrr";
    private static final String VALID_OTP_CODE = "111111";
    private static final String INVALID_OTP_CODE = "123456";
    private static final String[] EXISTING_USER_ERROR_TEXTS = {
        "already exist",
        "already exists",
        "already registered",
        "already taken",
        "email already",
        "username already",
        "user already",
        "account already",
        "email is taken",
        "username is taken"
    };
    private static final String[] REGISTRATION_SUCCESS_TEXTS = {
        "registered successfully",
        "registration successful",
        "account created",
        "signup successful",
        "sign up successful",
        "verified successfully",
        "otp verified",
        "welcome",
        "dashboard",
        "profile"
    };
    private static final String VERIFICATION_TEXT = "verification";

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

    private static final String[] VERIFICATION_MODAL_SELECTORS = {
        "[role='dialog']",
        "[class*='modal' i]",
        "[class*='dialog' i]",
        "[class*='verification' i]"
    };

    private static final String[] OTP_INPUT_SELECTORS = {
        "input[name*='otp' i]",
        "input[id*='otp' i]",
        "input[autocomplete='one-time-code']",
        "input[maxlength='1']",
        "input[inputmode='numeric']"
    };

    private static final String[] VERIFY_BUTTON_SELECTORS = {
        "//button[normalize-space()='Verify']",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'verify')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'continue')]",
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
            test.tc_signup_invalid_then_existing_user_create_account();
        } finally {
            // keep browser open for review
        }
    }

    void tc_signup_invalid_then_existing_user_create_account() {
        System.out.println(">> TC_SIGNUP_E2E - Invalid fields and existing user validation");

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
            assertTrue(waitForDuplicateUserError(),
                "Signup should show an 'already exist' error for existing email or username");
            assertTrue(isSignupSubmissionRejected(),
                "Signup should stay on the signup page when email or username already exists");
            System.out.println("   PASS - Existing email/username shows error and signup does not proceed");

            String uniqueSuffix = String.valueOf(System.currentTimeMillis());
            String freshUsername = NEW_USER_PREFIX + uniqueSuffix;
            String freshEmail = NEW_USER_PREFIX + uniqueSuffix + "@mailinator.com";

            clearSignupForm();
            fillSignupForm(
                freshUsername,
                VALID_FULL_NAME,
                freshEmail,
                VALID_PHONE,
                VALID_PASSWORD
            );
            assertSignupFormValues(
                freshUsername,
                VALID_FULL_NAME,
                freshEmail,
                VALID_PHONE,
                VALID_PASSWORD
            );
            clickCreateAccountButton();
            waitForVerificationModal();
            assertTrue(isVerificationModalVisible(),
                "Verification modal should appear after entering fresh valid credentials");
            System.out.println("   PASS - Cleared fields, entered fresh credentials, and opened verification modal");

            tc_signup_verify_invalid_then_valid_otp_in_same_modal();
        } catch (Exception e) {
            System.out.println("   FAIL - " + e.getMessage());
            e.printStackTrace();
        }
    }

    void tc_signup_verify_invalid_then_valid_otp_in_same_modal() {
        System.out.println(">> TC_SIGNUP_OTP - Verification modal checks invalid OTP first, then valid OTP");

        try {
            assertTrue(isVerificationModalVisible(),
                "Verification modal should already be open before OTP validation starts");
            assertTrue(hasExpectedOtpInputs(),
                "Verification modal should show OTP input fields");

            enterOtp(INVALID_OTP_CODE);
            clickVerifyButton();
            assertTrue(isOtpVerificationRejected(),
                "App should reject invalid OTP and stay on verification modal");
            System.out.println("   PASS - Verification modal opened and invalid OTP was rejected");

            clearOtpInputs();
            enterOtp(VALID_OTP_CODE);
            clickVerifyButton();
            assertTrue(isVerificationSuccessful(),
                "App should accept valid OTP " + VALID_OTP_CODE + " and proceed forward");
            System.out.println("   PASS - Invalid OTP cleared, valid OTP accepted, and user registered successfully");
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

    void openFreshSignupPage() {
        openLandingPage();
        handleBrowserNotification();
        acceptCookieBanner();
        navigateToSignupFromLanding();
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

    void clearSignupForm() {
        clearFieldIfPresent(USERNAME_SELECTORS);
        clearFieldIfPresent(FULL_NAME_SELECTORS);
        clearFieldIfPresent(EMAIL_SELECTORS);
        clearFieldIfPresent(PHONE_SELECTORS);
        clearFieldIfPresent(PASSWORD_SELECTORS);
    }

    void clearFieldIfPresent(String[] selectors) {
        try {
            WebElement field = findFirstVisible(selectors);
            forceClear(field);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings("deprecation")
    void assertSignupFormValues(String username, String fullName, String email, String phone, String password) {
        assertEqualsIgnoringUsernameCase(findFirstVisible(USERNAME_SELECTORS).getAttribute("value"), username,
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

    void waitForVerificationModal() {
        WebDriverWait shortWait = new WebDriverWait(driver, SCREEN_WAIT_TIMEOUT);
        shortWait.until(d -> isVerificationModalVisible() || hasExpectedOtpInputs());
    }

    boolean waitForDuplicateUserError() {
        try {
            new WebDriverWait(driver, SCREEN_WAIT_TIMEOUT).until(d -> isDuplicateUserErrorShown());
            return true;
        } catch (Exception e) {
            return isDuplicateUserErrorShown();
        }
    }

    boolean isSignupSubmissionRejected() {
        boolean stillOnSignup = driver.getCurrentUrl().contains("signup")
            || driver.getCurrentUrl().contains("register");

        boolean validationVisible = isElementPresent(By.cssSelector(
            "[class*='error'], [class*='alert'], [role='alert'], .invalid-feedback, :invalid"
        )) || isDuplicateUserErrorShown();

        return stillOnSignup && validationVisible;
    }

    boolean isDuplicateUserErrorShown() {
        String normalizedPageText = getPageTextLower();
        for (String errorText : EXISTING_USER_ERROR_TEXTS) {
            if (normalizedPageText.contains(errorText)) {
                return true;
            }
        }

        return false;
    }

    boolean isVerificationModalVisible() {
        for (String selector : VERIFICATION_MODAL_SELECTORS) {
            try {
                By locator = selector.startsWith("//") ? By.xpath(selector) : By.cssSelector(selector);
                for (WebElement element : driver.findElements(locator)) {
                    if (element != null && element.isDisplayed()) {
                        String modalText = element.getText();
                        if (modalText != null && modalText.toLowerCase().contains(VERIFICATION_TEXT)) {
                            return true;
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }

        String normalizedPageText = getPageTextLower();
        return normalizedPageText.contains(VERIFICATION_TEXT) && hasExpectedOtpInputs();
    }

    boolean hasExpectedOtpInputs() {
        return findOtpInputs().size() >= VALID_OTP_CODE.length();
    }

    java.util.List<WebElement> findOtpInputs() {
        java.util.LinkedHashSet<WebElement> inputs = new java.util.LinkedHashSet<>();

        for (String selector : OTP_INPUT_SELECTORS) {
            try {
                By locator = selector.startsWith("//") ? By.xpath(selector) : By.cssSelector(selector);
                for (WebElement element : driver.findElements(locator)) {
                    if (element != null && element.isDisplayed()) {
                        inputs.add(element);
                    }
                }
            } catch (Exception ignored) {
            }
        }

        return new java.util.ArrayList<>(inputs);
    }

    @SuppressWarnings("deprecation")
	void enterOtp(String otp) throws InterruptedException {
        java.util.List<WebElement> otpInputs = findOtpInputs();
        if (otpInputs.size() < otp.length()) {
            throw new RuntimeException("Expected " + otp.length() + " OTP inputs but found " + otpInputs.size());
        }

        for (int i = 0; i < otp.length(); i++) {
            WebElement input = otpInputs.get(i);
            String expectedDigit = String.valueOf(otp.charAt(i));

            try {
                scrollIntoView(input);
                clickElement(input);
            } catch (Exception ignored) {
            }

            try {
                forceClear(input);
            } catch (Exception ignored) {
            }

            try {
                input.sendKeys(expectedDigit);
            } catch (Exception ignored) {
                fastSetFieldValue(input, expectedDigit);
            }

            String actualDigit = normalizeForCompare(input.getAttribute("value"));
            if (!actualDigit.equals(expectedDigit)) {
                fastSetFieldValue(input, expectedDigit);
                actualDigit = normalizeForCompare(input.getAttribute("value"));
            }

            if (!actualDigit.equals(expectedDigit)) {
                throw new RuntimeException(
                    "OTP digit mismatch at position " + (i + 1) + ". Expected: " + expectedDigit + " but got: " + actualDigit
                );
            }
            Thread.sleep(300);
        }
    }

    void clickVerifyButton() {
        WebElement button = findFirstVisible(VERIFY_BUTTON_SELECTORS);
        scrollIntoView(button);
        clickElement(button);
        waitAfterVerifyClick();
    }

    void waitAfterVerifyClick() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    boolean isVerificationSuccessful() {
        new WebDriverWait(driver, SCREEN_WAIT_TIMEOUT).until(d ->
            !isVerificationModalVisible()
                || !(driver.getCurrentUrl().contains("signup") || driver.getCurrentUrl().contains("register"))
                || isRegistrationSuccessVisible());

        boolean movedForward = !(driver.getCurrentUrl().contains("signup")
            || driver.getCurrentUrl().contains("register"));

        boolean modalClosed = !isVerificationModalVisible();

        return movedForward || modalClosed || isRegistrationSuccessVisible();
    }

    boolean isOtpVerificationRejected() {
        try {
            new WebDriverWait(driver, SCREEN_WAIT_TIMEOUT).until(d ->
                isVerificationModalVisible() || isOtpErrorVisible());
        } catch (Exception ignored) {
        }

        return isVerificationModalVisible() || isOtpErrorVisible();
    }

    boolean isOtpErrorVisible() {
        String normalizedPageText = getPageTextLower();
        return normalizedPageText.contains("invalid otp")
            || normalizedPageText.contains("incorrect otp")
            || normalizedPageText.contains("wrong otp")
            || normalizedPageText.contains("invalid code")
            || normalizedPageText.contains("incorrect code")
            || normalizedPageText.contains("wrong code")
            || normalizedPageText.contains("otp not valid");
    }

    boolean isRegistrationSuccessVisible() {
        String normalizedPageText = getPageTextLower();
        for (String successText : REGISTRATION_SUCCESS_TEXTS) {
            if (normalizedPageText.contains(successText)) {
                return true;
            }
        }
        return false;
    }

    String getPageTextLower() {
        try {
            String pageText = driver.findElement(By.tagName("body")).getText();
            return pageText == null ? "" : pageText.toLowerCase();
        } catch (Exception e) {
            return "";
        }
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
        try {
            clickElement(field);
        } catch (Exception ignored) {
        }
        fastSetFieldValue(field, value);

        String actualValue = field.getAttribute("value");
        if (!fieldValueMatches(field, actualValue, value)) {
            try {
                forceClear(field);
                field.sendKeys(value);
            } catch (Exception ignored) {
                fastSetFieldValue(field, value);
            }
            actualValue = field.getAttribute("value");
        }

        if (!fieldValueMatches(field, actualValue, value)) {
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
        By locator = LandingPage.toLocator(selector);
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

    boolean fieldValueMatches(WebElement field, String actualValue, String expectedValue) {
        String actual = normalizeForCompare(actualValue);
        String expected = normalizeForCompare(expectedValue);

        if (isUsernameField(field)) {
            return actual.equalsIgnoreCase(expected);
        }

        return actual.equals(expected);
    }

    @SuppressWarnings("deprecation")
	boolean isUsernameField(WebElement field) {
        try {
            String name = normalizeForCompare(field.getAttribute("name")).toLowerCase();
            String id = normalizeForCompare(field.getAttribute("id")).toLowerCase();
            String placeholder = normalizeForCompare(field.getAttribute("placeholder")).toLowerCase();
            return name.contains("username") || id.contains("username") || placeholder.contains("username");
        } catch (Exception e) {
            return false;
        }
    }

    void clearOtpInputs() {
        java.util.List<WebElement> otpInputs = findOtpInputs();
        for (WebElement input : otpInputs) {
            try {
                forceClear(input);
                fastSetFieldValue(input, "");
            } catch (Exception ignored) {
            }
        }
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

    void assertEqualsIgnoringUsernameCase(String actual, String expected, String message) {
        String normalizedActual = normalizeForCompare(actual);
        String normalizedExpected = normalizeForCompare(expected);
        if (!normalizedActual.equalsIgnoreCase(normalizedExpected)) {
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