package Default;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

    protected static final String URL = "https://nosho.vercel.app";
  

    protected static final String[] COOKIE_ACCEPT_SELECTORS = {
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'accept all')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'accept')]",
        "button[id*='accept' i]",
        "button[class*='accept-all' i]",
        "button[class*='accept' i]",
        "[class*='cookie'] button",
        "[class*='consent'] button",
        "[id*='cookie'] button"
    };

  
    protected static final String[] LOGIN_BTN_SELECTORS = {
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'login')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'login')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign in')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign in')]",
        "a[href*='login']",
        "a[href*='signin']",
        "button[class*='login' i]",
        "button[id*='login' i]"
    };

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    protected static final Duration DEFAULT_WAIT_TIMEOUT = Duration.ofSeconds(15);
    protected static final Duration FAST_WAIT_TIMEOUT = Duration.ofSeconds(3);

    public static void main(String[] args) {
        try {
            setupBrowser();
            openLandingPage();
            handleBrowserNotification();
            acceptCookieBanner();
          

            System.out.println("Land on Landing page successfully.");
            System.out.println("Current URL: " + driver.getCurrentUrl());

            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("\nFlow failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Intentionally left open for inspection.
        }
    }

    protected static void setupBrowser() {
        log("STEP 0", "Launching Chrome...");

        try {
            Class<?> wdm = Class.forName("io.github.bonigarcia.wdm.WebDriverManager");
            Object instance = wdm.getMethod("chromedriver").invoke(null);
            instance.getClass().getMethod("setup").invoke(instance);
        } catch (Exception e) {
            System.out.println("[INFO] WebDriverManager not found; using ChromeDriver from PATH.");
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 1);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT);

        log("STEP 0", "Browser launched.");
    }

    protected static void openLandingPage() {
        log("STEP 1", "Navigating to " + URL);
        driver.get(URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        log("STEP 1", "Landing page loaded. Title: " + driver.getTitle());
    }

    protected static void handleBrowserNotification() {
        log("STEP 2", "Handling browser notification permission...");

        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            log("STEP 2", "Alert-style notification accepted.");
            return;
        } catch (Exception ignored) {
        }

        try {
            WebElement allowButton = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'allow')]")));
            clickElement(allowButton);
            log("STEP 2", "In-page notification button clicked.");
        } catch (Exception ignored) {
            log("STEP 2", "Notification already handled by browser preferences.");
        }
    }

    protected static void acceptCookieBanner() {
        log("STEP 3", "Looking for cookie consent banner...");

        for (String selector : COOKIE_ACCEPT_SELECTORS) {
            try {
                WebElement button = findDisplayedNow(selector);
                if (button == null) {
                    button = findClickable(selector, FAST_WAIT_TIMEOUT);
                }
                if (button != null && button.isDisplayed()) {
                    scrollIntoView(button);
                    clickElement(button);
                    log("STEP 3", "Cookie banner accepted using selector: " + selector);
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        log("STEP 3", "No cookie banner found; continuing.");
    }

    protected static WebElement findDisplayedNow(String selector) {
        try {
            By locator = toLocator(selector);
            for (WebElement element : driver.findElements(locator)) {
                if (element != null && element.isDisplayed()) {
                    return element;
                }
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    
    protected static WebElement findClickable(String selector, Duration timeout) {
        WebDriverWait shortWait = new WebDriverWait(driver, timeout);
        By locator = toLocator(selector);
        return shortWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected static boolean isDisplayed(By locator, Duration timeout) {
        try {
            return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(locator))
                .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected static void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected static void scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'instant', block:'center'});", element);
        } catch (Exception ignored) {
        }
    }

    protected static void log(String step, String message) {
        System.out.println("[" + step + "] " + message);
    }

    protected static By toLocator(String selector) {
        return selector.startsWith("//") ? By.xpath(selector) : By.cssSelector(selector);
    }

    public static void clickLoginButton() {
        log("STEP 4", "Looking for login button on landing page...");

        for (String selector : LOGIN_BTN_SELECTORS) {
            try {
                WebElement button = findClickable(selector, FAST_WAIT_TIMEOUT);
                if (button != null && button.isDisplayed()) {
                    scrollIntoView(button);
                    clickElement(button);
                    log("STEP 4", "Login button clicked using selector: " + selector);
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        throw new RuntimeException("'Login' button not found on landing page");
    }
}
