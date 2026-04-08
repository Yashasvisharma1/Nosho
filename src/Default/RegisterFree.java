package Default;

import java.time.Duration;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterFree extends LandingPage {

    private static final Duration WAIT_TIME = Duration.ofSeconds(8);
    private static final int CONTINUE_CLICK_COUNT = 3;

    private static final String PROFILE_IMAGE_PATH =
        "C:\\Users\\Techglock\\OneDrive\\Pictures\\Screenshots\\Screenshot (10).png";
    private static final String HEADER_IMAGE_PATH =
        "C:\\Users\\Techglock\\Downloads\\1773055212395-nosho.png";

    private static final String[] TRY_FOR_FREE_SELECTORS = {
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'try for free')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'try for free')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'get started')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'get started')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'register free')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'register free')]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign up')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign up')]",
        "a[href*='signup']",
        "a[href*='register']"
    };

    private static final String[] CONTINUE_BUTTON_SELECTORS = {
        "//button[normalize-space()='Continue']",
        "//a[normalize-space()='Continue']",
        "//*[self::a or self::button or self::span][normalize-space()='Continue']",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'continue')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'continue')]"
    };

    private static final String[] CREATE_PROFILE_BUTTON_SELECTORS = {
        "//button[normalize-space()='Create my profile']",
        "//a[normalize-space()='Create my profile']",
        "//*[self::a or self::button or self::span][normalize-space()='Create my profile']",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'create my profile')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'create my profile')]"
    };

    private static final String[] ADD_PROFILE_IMAGE_SELECTORS = {
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'add a profile image')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'add a profile image')]",
        "//*[self::a or self::button or self::span][contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'add a profile image')]",
        "//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'profile image')]",
        "label[for]",
        "button[class*='image' i]",
        "[class*='image' i]"
    };

    private static final String[] ADD_HEADER_IMAGE_SELECTORS = {
        "//*[self::button or self::a or self::div or self::span][contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'add header image')]",
        "//*[self::button or self::a or self::div or self::span][contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'add your header image')]",
        "//div[contains(@class,'MuiBox-root') and contains(@class,'css-oaopx4')]",
        "//div[contains(@class,'MuiBox-root') and .//*[name()='svg']]"
    };
    private static final String COLOR_SECTION_XPATH =
        "//div[contains(@class,'MuiBox-root') and .//p[normalize-space()='Colour']]";
    private static final String COLOR_CAROUSEL_XPATH =
        COLOR_SECTION_XPATH + "//div[contains(@class,'swiper') and contains(@class,'CompanyColorList')]";
    private static final String COLOR_CHIP_XPATH =
        COLOR_SECTION_XPATH
            + "//div[contains(@class,'swiper-slide')]"
            + "//div[contains(@class,'css-h6kn6g') or contains(@class,'css-1az15yq')]";
    private static final String[] BRIGHT_COLOR_CHIP_SELECTORS = {
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-rhkeia')]]",
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-1jct9y0')]]",
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-1n9payi')]]",
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-1bpszts')]]",
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-1q0azom')]]",
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-13e2clg')]]",
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-r1gtb6')]]",
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-8gmvhm')]]",
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-4c9leu')]]",
        "//div[contains(@class,'css-1az15yq')][.//div[contains(@class,'css-wosxr1')]]"
    };

    private static final String FILE_INPUT_SELECTOR = "input[type='file']";
    private static final String UPLOAD_PROGRESS_BOX_XPATH =
        "//div[contains(@class,'MuiBox-root') and .//p[contains(normalize-space(.),'Uploading')]]";
    private static final String UPLOAD_PROGRESS_100_XPATH =
        "//div[contains(@class,'MuiBox-root') and .//p[normalize-space()='100%'] and .//p[contains(normalize-space(.),'Uploading')]]";

    private static final String TICK_BUTTON_XPATH =
        "//button[contains(@class,'MuiIconButton-root') and .//*[name()='path' and contains(@d,'M369 209')]]";

    public static void main(String[] args) {
        try {
            setupBrowser();
            openLandingPage();
            handleBrowserNotification();
            acceptCookieBanner();

            RegisterFree page = new RegisterFree();
            page.clickTryForFree();
            page.clickContinueButtons();
            page.clickCreateMyProfile();
            page.uploadProfileImage();
            page.clickTickButton();
            page.waitUntilUploadProgressCompletes();
            page.uploadHeaderImage();
            page.waitUntilHeaderUploadProgressCompletes();
            page.scrollColourCarousel();
            page.clickAnyBrightColour();

            System.out.println("Register free flow completed successfully.");

        } catch (Exception e) {
            System.out.println("Flow failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Keep browser open for review
        }
    }

    void clickTryForFree() {
        WebElement button = findFirstVisibleElement(TRY_FOR_FREE_SELECTORS);
        if (button == null) {
            throw new RuntimeException("'Try for free' button not found");
        }

        click(button);
        System.out.println("Clicked Try for free button");
    }

    void clickContinueButtons() {
        for (int i = 1; i <= CONTINUE_CLICK_COUNT; i++) {
            WebElement button = findFirstVisibleElement(CONTINUE_BUTTON_SELECTORS);
            if (button == null) {
                throw new RuntimeException("'Continue' button not found at step " + i);
            }

            click(button);
            System.out.println("Clicked Continue button " + i);
        }
    }

    void clickCreateMyProfile() {
        WebElement button = findFirstVisibleElement(CREATE_PROFILE_BUTTON_SELECTORS);
        if (button == null) {
            throw new RuntimeException("'Create my profile' button not found");
        }

        click(button);
        System.out.println("Clicked Create my profile button");
    }

    void uploadProfileImage() {
        validateProfileImageFile();

        WebElement addImageButton = findFirstVisibleElement(ADD_PROFILE_IMAGE_SELECTORS);
        if (addImageButton == null) {
            throw new RuntimeException("'Add a profile image' button not found");
        }

        click(addImageButton);
        System.out.println("Clicked Add a profile image");

        WebElement fileInput = waitForElement(FILE_INPUT_SELECTOR);
        if (fileInput == null) {
            throw new RuntimeException("File input not found");
        }

        uploadImageToAvailableInput(fileInput);

        waitForFileUploadValue();
        System.out.println("Profile image uploaded successfully");
    }

    void clickTickButton() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);

            WebElement tickButton = shortWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(TICK_BUTTON_XPATH))
            );

            click(tickButton);
            System.out.println("Clicked tick button successfully");

        } catch (Exception e) {
            throw new RuntimeException("Tick button not found or not clickable", e);
        }
    }

    void clickAddHeaderImage() {
        WebElement addHeaderImageButton = findFirstVisibleElement(ADD_HEADER_IMAGE_SELECTORS);
        if (addHeaderImageButton == null) {
            throw new RuntimeException("'Add header image' button not found");
        }

        click(addHeaderImageButton);
        System.out.println("Clicked Add header image");
    }

    void uploadHeaderImage() {
        validateHeaderImageFile();
        int fileInputCountBeforeClick = driver.findElements(By.cssSelector(FILE_INPUT_SELECTOR)).size();
        clickAddHeaderImage();

        WebElement fileInput = waitForHeaderFileInput(fileInputCountBeforeClick);
        if (fileInput == null) {
            throw new RuntimeException("Header image file input not found");
        }

        uploadImageToAvailableInput(fileInput, HEADER_IMAGE_PATH);
        waitForFileUploadValue();
        System.out.println("Header image uploaded successfully");
        clickTickButton();
    }

    void scrollColourCarousel() {
        try {
            WebElement carousel = waitForElement(COLOR_CAROUSEL_XPATH);
            if (carousel == null) {
                throw new RuntimeException("Colour carousel not found");
            }

            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'instant', block:'center'});" +
                "arguments[0].scrollLeft = arguments[0].scrollWidth;",
                carousel
            );
            System.out.println("Scrolled colour carousel");
        } catch (Exception e) {
            throw new RuntimeException("Unable to scroll colour carousel", e);
        }
    }

    void clickAnyBrightColour() {
        WebElement brightColourOption = findFirstVisibleElement(BRIGHT_COLOR_CHIP_SELECTORS);
        if (brightColourOption != null) {
            click(brightColourOption);
            System.out.println("Clicked a bright colour option");
            return;
        }

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
            java.util.List<WebElement> colourOptions = shortWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(COLOR_CHIP_XPATH))
            );

            for (WebElement colourOption : colourOptions) {
                try {
                    if (colourOption != null && colourOption.isDisplayed()) {
                        click(colourOption);
                        System.out.println("Clicked a colour option");
                        return;
                    }
                } catch (Exception ignored) {
                }
            }
        } catch (Exception ignored) {
        }

        throw new RuntimeException("No selectable bright colour option found");
    }

    WebElement findFirstVisibleElement(String[] selectors) {
        for (String selector : selectors) {
            try {
                WebElement element = waitForElement(selector);
                if (element != null && element.isDisplayed()) {
                    return element;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    WebElement waitForElement(String selector) {
        try {
            By locator = getLocator(selector);
            WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
            return shortWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            return null;
        }
    }

    By getLocator(String selector) {
        if (selector.startsWith("//")) {
            return By.xpath(selector);
        }
        return By.cssSelector(selector);
    }

    void click(WebElement element) {
        try {
            scrollIntoView(element);
            new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.visibilityOf(element));
            element.click();
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception ex) {
                throw new RuntimeException("Unable to click element", ex);
            }
        }
    }

    void validateProfileImageFile() {
        if (!Files.exists(Path.of(PROFILE_IMAGE_PATH))) {
            throw new RuntimeException("Profile image file not found: " + PROFILE_IMAGE_PATH);
        }
    }

    void validateHeaderImageFile() {
        if (!Files.exists(Path.of(HEADER_IMAGE_PATH))) {
            throw new RuntimeException("Header image file not found: " + HEADER_IMAGE_PATH);
        }
    }

    WebElement waitForHeaderFileInput(int fileInputCountBeforeClick) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
            return shortWait.until(d -> {
                java.util.List<WebElement> inputs = d.findElements(By.cssSelector(FILE_INPUT_SELECTOR));
                if (inputs.isEmpty()) {
                    return null;
                }

                if (inputs.size() > fileInputCountBeforeClick) {
                    return inputs.get(inputs.size() - 1);
                }

                return inputs.get(inputs.size() - 1);
            });
        } catch (Exception e) {
            return waitForElement(FILE_INPUT_SELECTOR);
        }
    }

    void makeFileInputVisible(WebElement fileInput) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.display='block';" +
                "arguments[0].style.visibility='visible';" +
                "arguments[0].style.opacity='1';" +
                "arguments[0].removeAttribute('hidden');",
                fileInput
            );
        } catch (Exception ignored) {
        }
    }

    void waitForFileUploadValue() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(d -> {
                for (WebElement currentInput : d.findElements(By.cssSelector(FILE_INPUT_SELECTOR))) {
                    try {
                        @SuppressWarnings("deprecation")
                        String value = currentInput.getAttribute("value");
                        if (value != null && !value.trim().isEmpty()) {
                            return true;
                        }
                    } catch (Exception ignored) {
                    }
                }
                for (WebElement image : d.findElements(By.tagName("img"))) {
                    try {
                        if (image != null && image.isDisplayed()) {
                            @SuppressWarnings("deprecation")
                            String src = image.getAttribute("src");
                            if (src != null && !src.trim().isEmpty() && !src.startsWith("data:,")) {
                                return true;
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
                return false;
            });
        } catch (Exception e) {
            throw new RuntimeException("Image file was not attached to upload input");
        }
    }

    void uploadImageToAvailableInput(WebElement firstInput) {
        uploadImageToAvailableInput(firstInput, PROFILE_IMAGE_PATH);
    }

    void uploadImageToAvailableInput(WebElement firstInput, String imagePath) {
        try {
            makeFileInputVisible(firstInput);
            firstInput.sendKeys(imagePath);
        } catch (Exception ignored) {
        }

        for (WebElement input : driver.findElements(By.cssSelector(FILE_INPUT_SELECTOR))) {
            try {
                makeFileInputVisible(input);
                input.sendKeys(imagePath);
                return;
            } catch (Exception ignored) {
            }
        }
    }

    void waitUntilUploadProgressCompletes() {
        try {
            WebDriverWait uploadWait = new WebDriverWait(driver, Duration.ofSeconds(20));

            boolean progressAppeared = false;
            try {
                uploadWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UPLOAD_PROGRESS_BOX_XPATH)));
                progressAppeared = true;
                System.out.println("Upload progress appeared near Add profile image");
            } catch (Exception ignored) {
            }

            if (!progressAppeared) {
                System.out.println("Upload progress was not visible, continuing");
                return;
            }

            try {
                uploadWait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(UPLOAD_PROGRESS_100_XPATH)),
                    ExpectedConditions.invisibilityOfElementLocated(By.xpath(UPLOAD_PROGRESS_BOX_XPATH))
                ));
            } catch (Exception ignored) {
            }

            uploadWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UPLOAD_PROGRESS_BOX_XPATH)));
            System.out.println("Upload progress completed and disappeared");
        } catch (Exception e) {
            throw new RuntimeException("Upload progress did not complete before timeout", e);
        }
    }

    void waitUntilHeaderUploadProgressCompletes() {
        try {
            WebDriverWait uploadWait = new WebDriverWait(driver, Duration.ofSeconds(20));

            boolean progressAppeared = false;
            try {
                uploadWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UPLOAD_PROGRESS_BOX_XPATH)));
                progressAppeared = true;
                System.out.println("Header upload progress appeared near Add your header image");
            } catch (Exception ignored) {
            }

            if (!progressAppeared) {
                System.out.println("Header upload progress was not visible, continuing");
                return;
            }

            try {
                uploadWait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(UPLOAD_PROGRESS_100_XPATH)),
                    ExpectedConditions.invisibilityOfElementLocated(By.xpath(UPLOAD_PROGRESS_BOX_XPATH))
                ));
            } catch (Exception ignored) {
            }

            uploadWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UPLOAD_PROGRESS_BOX_XPATH)));
            System.out.println("Header upload progress completed and disappeared");
        } catch (Exception e) {
            throw new RuntimeException("Header upload progress did not complete before timeout", e);
        }
    }
}
