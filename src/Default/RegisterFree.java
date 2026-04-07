package Default;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterFree extends LandingPage {

    private static final Duration BUTTON_WAIT_TIMEOUT = Duration.ofSeconds(5);
    private static final int CONTINUE_CLICK_COUNT = 3;

    private static final String[] TRY_FOR_FREE_SELECTORS = {
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'try for free')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'try for free')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'get started')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'get started')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'start free')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'start free')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'register free')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'register free')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign up')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign up')]",
        "a[href*='signup']",
        "a[href*='register']",
        "button[class*='try' i]",
        "button[class*='start' i]",
        "button[class*='register' i]"
    };

    private static final String[] CONTINUE_BUTTON_SELECTORS = {
        "//button[normalize-space()='Continue']",
        "//a[normalize-space()='Continue']",
        "//*[self::a or self::button or self::span][normalize-space()='Continue']",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'continue')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'continue')]",
        "button[class*='continue' i]",
        "button[id*='continue' i]"
    };

    private static final String[] SKIP_BUTTON_SELECTORS = {
        "//button[normalize-space()='Skip']",
        "//a[normalize-space()='Skip']",
        "//*[self::a or self::button or self::span][normalize-space()='Skip']",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'skip')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'skip')]",
        "button[class*='skip' i]",
        "button[id*='skip' i]"
    };

    private static final String[] CREATE_PROFILE_BUTTON_SELECTORS = {
        "//button[normalize-space()='Create my profile']",
        "//a[normalize-space()='Create my profile']",
        "//*[self::a or self::button or self::span][normalize-space()='Create my profile']",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'create my profile')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'create my profile')]",
        "button[class*='profile' i]",
        "button[id*='profile' i]"
    };

    public static void main(String[] args) {
        try {
            setupBrowser();
            openLandingPage();
            handleBrowserNotification();
            acceptCookieBanner();
            RegisterFree page = new RegisterFree();
            page.clickTryForFree();
            // page.clickSkipButton();
            page.clickContinueButtons();
            page.clickCreateMyProfile();
        } finally {
            // keep browser open for review
        }
    }

    void clickTryForFree() {
        for (String selector : TRY_FOR_FREE_SELECTORS) {
            try {
                WebElement button = findDisplayedNow(selector);
                if (button == null) {
                    button = findElementIfPresent(selector, BUTTON_WAIT_TIMEOUT);
                }
                if (button != null) {
                    scrollIntoView(button);
                    clickElement(button);
                    System.out.println("Clicked Try for free button");
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        WebElement fallbackButton = findTryForFreeFallback();
        if (fallbackButton != null) {
            scrollIntoView(fallbackButton);
            clickElement(fallbackButton);
            System.out.println("Clicked Try for free button");
            return;
        }

        throw new RuntimeException("'Try for free' button not found");
    }

    void clickContinueButtons() {
        for (int i = 0; i < CONTINUE_CLICK_COUNT; i++) {
            WebElement continueButton = findContinueButton();
            if (continueButton == null) {
                throw new RuntimeException("'Continue' button not found on modal");
            }

            scrollIntoView(continueButton);
            clickElement(continueButton);
            System.out.println("Clicked Continue button " + (i + 1));
        }
    }

    void clickCreateMyProfile() {
        WebElement createProfileButton = findButton(CREATE_PROFILE_BUTTON_SELECTORS);
        if (createProfileButton == null) {
            throw new RuntimeException("'Create my profile' button not found on modal");
        }

        scrollIntoView(createProfileButton);
        clickElement(createProfileButton);
        System.out.println("Clicked Create my profile button");
    }

    void clickSkipButton() {
        WebElement skipButton = findButton(SKIP_BUTTON_SELECTORS);
        if (skipButton == null) {
            throw new RuntimeException("'Skip' button not found on modal");
        }

        scrollIntoView(skipButton);
        clickElement(skipButton);
        System.out.println("Clicked Skip button");
    }

    WebElement findContinueButton() {
        return findButton(CONTINUE_BUTTON_SELECTORS);
    }

    WebElement findElementIfPresent(String selector, Duration timeout) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, timeout);
            By locator = selector.startsWith("//") ? By.xpath(selector) : By.cssSelector(selector);
            return shortWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            return null;
        }
    }

    WebElement findTryForFreeFallback() {
        String[] tags = { "button", "a" };

        for (String tag : tags) {
            try {
                for (WebElement element : driver.findElements(By.tagName(tag))) {
                    if (element == null || !element.isDisplayed()) {
                        continue;
                    }

                    String text = element.getText();
                    if (text == null) {
                        continue;
                    }

                    String normalizedText = text.trim().toLowerCase();
                    if (normalizedText.contains("try for free")
                        || normalizedText.contains("get started")
                        || normalizedText.contains("start free")
                        || normalizedText.contains("register")
                        || normalizedText.contains("sign up")
                        || normalizedText.contains("free")) {
                        return element;
                    }
                }
            } catch (Exception ignored) {
            }
        }

        return null;
    }

    WebElement findButton(String[] selectors) {
        for (String selector : selectors) {
            try {
                WebElement button = findDisplayedNow(selector);
                if (button == null) {
                    button = findElementIfPresent(selector, BUTTON_WAIT_TIMEOUT);
                }
                if (button != null && button.isDisplayed()) {
                    return button;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
