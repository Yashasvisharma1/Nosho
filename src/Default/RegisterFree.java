package Default;

import java.time.Duration;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterFree extends LandingPage {

    private static final Duration WAIT_TIME = Duration.ofSeconds(5);
    private static final Duration FILE_UPLOAD_WAIT_TIME = Duration.ofSeconds(5);
    private static final Duration UPLOAD_PROGRESS_WAIT_TIME = Duration.ofSeconds(20);
    private static final long CLICK_DELAY_MILLIS = 2000;
    private static final int CONTINUE_CLICK_COUNT = 3;

    private static final String PROFILE_IMAGE_PATH =
        "C:\\Users\\Techglock\\Downloads\\profile.jpg";
    private static final String HEADER_IMAGE_PATH =
        "C:\\Users\\Techglock\\Downloads\\header.jpg";
    private static final String COMPANY_NAME = "Nosho Labs";
    private static final String ABOUT_TEXT = "We help clients build modern brand and booking experiences.";
    private static final String LOCATION_NAME = "J";
    
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
    @SuppressWarnings("unused")
	private static final String[] FULL_HEADER_IMAGE_SELECTORS = {
        "button.MuiButtonBase-root.MuiButton-root.css-1nu21xj[type='button']",
        "//button[@type='button' and contains(@class,'MuiButtonBase-root') and contains(@class,'MuiButton-root') and contains(@class,'css-1nu21xj')]",
        "//button[contains(@class,'MuiButton-root') and .//*[name()='path' and contains(@d,'M16 3H22V9H20V5H16V3')]]",
        "//button[.//*[name()='svg'] and .//*[name()='path' and contains(@d,'M20 19V15H22V21H16V19H20')]]"
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
    private static final String[] NEXT_BUTTON_SELECTORS = {
        "//button[normalize-space()='Next']",
        "//a[normalize-space()='Next']",
        "//*[self::button or self::a or self::span][normalize-space()='Next']",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'next')]",
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'next')]"
    };
    private static final String[] COMPANY_NAME_SELECTORS = {
        "input[name='companyName']",
        "input[id='companyName']",
        "input[name='company_name']",
        "input[id='company_name']",
        "input[placeholder*='company name' i]",
        "//label[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'company name')]/following::input[1]"
    };
    private static final String[] BUSINESS_TYPE_SELECTORS = {
        "div.MuiInputBase-root.MuiOutlinedInput-root.MuiSelect-root.css-6j12it",
        "//*[@id=\"demo-simple-select\"]",
        "div#demo-simple-select[role='combobox']",
        "div.MuiInputBase-root.MuiSelect-root div#demo-simple-select",
        "div.MuiInputBase-root.MuiSelect-root div[role='combobox']",
        "div.MuiSelect-select.MuiOutlinedInput-input[role='combobox']",
        "//div[@id='demo-simple-select' and @role='combobox']",
        "//div[@role='combobox' and contains(@class,'MuiSelect-select') and normalize-space()='Please select']",
        "select[name*='business' i]",
        "select[id*='business' i]",
        "[role='combobox'][aria-label*='business' i]",
        "[role='button'][aria-label*='business' i]",
        "[class*='select' i][aria-haspopup='listbox']",
        "[class*='select' i][role='button']",
        "[class*='dropdown' i]",
        "[class*='MuiSelect-select']",
        "input[placeholder*='business type' i]",
        "//label[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'business type')]/following::*[@role='combobox' or @role='button' or self::select or self::input][1]",
        "//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'business type')]/following::*[@role='combobox' or @role='button' or self::select or self::input][1]"
    };
    private static final String[] BUSINESS_TYPE_TRIGGER_SELECTORS = {
        "div.MuiInputBase-root.MuiOutlinedInput-root.MuiSelect-root.css-6j12it",
        "//*[@id=\"demo-simple-select\"]",
        "div#demo-simple-select[role='combobox']",
        "div.MuiInputBase-root.MuiSelect-root div#demo-simple-select",
        "div.MuiInputBase-root.MuiSelect-root div[role='combobox']",
        "//div[@id='demo-simple-select' and @role='combobox']",
        "//div[@role='combobox' and @aria-haspopup='listbox' and contains(@class,'MuiSelect-select')]",
        "//label[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'business type')]/following::*[name()='svg'][1]",
        "//label[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'business type')]/following::*[contains(@class,'arrow') or contains(@class,'icon')][1]",
        "//label[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'business type')]/following::div[@role='button' or @role='combobox'][1]",
        "//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'business type')]/following::*[name()='svg'][1]",
        "//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'business type')]/following::div[@role='button' or @role='combobox'][1]"
    };
    private static final String[] DROPDOWN_OPTION_SELECTORS = {
        "[role='option']",
        "[role='listbox'] li",
        ".MuiMenu-paper li",
        ".MuiPopover-root li",
        "[class*='option' i]",
        "[class*='autocomplete' i] li",
        "li"
    };
    private static final String BUSINESS_TYPE_OPTION_XPATH =
        "//ul[@role='listbox']//li[@role='option'"
            + " and not(@aria-disabled='true')"
            + " and normalize-space()!='Please select']";
    private static final String BUSINESS_TYPE_LISTBOX_XPATH =
        "//ul[contains(@class,'MuiMenu-list') and @role='listbox']";
    private static final String BUSINESS_TYPE_MENU_XPATH = "/html/body/div[2]/div[3]/ul";
    private static final String BUSINESS_TYPE_FIELD_XPATH = "//*[@id=\"demo-simple-select\"]";
    private static final String BUSINESS_TYPE_PLACEHOLDER_TEXT = "Please select";
    private static final String BUSINESS_TYPE_TARGET_OPTION_XPATH =
        "//ul[@role='listbox']//li[@role='option'"
            + " and @data-value='Hair Salon'"
            + " and not(@aria-disabled='true')]";
    private static final String[] ABOUT_SELECTORS = {
        "textarea[name='about']",
        "textarea[id='about']",
        "textarea[placeholder*='about' i]",
        "//label[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'about')]/following::textarea[1]"
    };
    private static final String[] LOCATION_SELECTORS = {
        "input[name='company_address']",
        "input[id='_r_1i_']",
        "input[placeholder*='enter your location' i]",
        "//input[@name='company_address']"
    };
    private static final String LOCATION_DROPDOWN_OPTION_XPATH =
        "//div[contains(@class,'MuiBox-root') and contains(@class,'css-1poh9y9')]"
            + "//div[contains(@class,'css-1mzw8re')]";

    private static final String FILE_INPUT_SELECTOR = "input[type='file']";
    private static final String UPLOAD_PROGRESS_BOX_XPATH =
        "//div[contains(@class,'MuiBox-root') and .//p[contains(normalize-space(.),'Uploading')]]";
    private static final String UPLOAD_PROGRESS_100_XPATH =
        "//div[contains(@class,'MuiBox-root') and .//p[normalize-space()='100%'] and .//p[contains(normalize-space(.),'Uploading')]]";

    private static final String TICK_BUTTON_XPATH =
        "//button[contains(@class,'MuiIconButton-root') and .//*[name()='path' and contains(@d,'M369 209')]]";
    private static final String PROFILE_UPLOAD_LABEL = "Add profile image";
    private static final String HEADER_UPLOAD_LABEL = "Add your header image";

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
            page.clickTickButton();
            page.waitUntilHeaderUploadProgressCompletes();
            page.handleHeaderImageLayoutAndColourStep();
            page.clickNextButton();
            page.fillCompanyDetails();

            System.out.println("Register free flow completed successfully.");

        } catch (Exception e) {
            System.out.println("Flow failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Keep browser open for review
        }
    }

    void clickTryForFree() {
        clickRequiredElement(TRY_FOR_FREE_SELECTORS, "'Try for free' button not found");
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
            sleep(CLICK_DELAY_MILLIS);
        }
    }

    void clickCreateMyProfile() {
        clickRequiredElement(CREATE_PROFILE_BUTTON_SELECTORS, "'Create my profile' button not found");
        System.out.println("Clicked Create my profile button");
    }

    void uploadProfileImage() {
        validateImageFile(PROFILE_IMAGE_PATH, "Profile image file not found: ");
        clickRequiredElement(ADD_PROFILE_IMAGE_SELECTORS, "'Add a profile image' button not found");
        System.out.println("Clicked Add a profile image");
        uploadImage(waitForElementOrThrow(FILE_INPUT_SELECTOR, "File input not found"), PROFILE_IMAGE_PATH);
        System.out.println("Profile image uploaded successfully");
        sleep(CLICK_DELAY_MILLIS);
    }

    void clickTickButton() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);

            WebElement tickButton = shortWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(TICK_BUTTON_XPATH))
            );

            click(tickButton);
            System.out.println("Clicked tick button successfully");
            sleep(CLICK_DELAY_MILLIS);

            

        } catch (Exception e) {
            throw new RuntimeException("Tick button not found or not clickable", e);
        }
    }

    void clickAddHeaderImage() {
        clickRequiredElement(ADD_HEADER_IMAGE_SELECTORS, "'Add header image' button not found");
        System.out.println("Clicked Add header image");
        sleep(CLICK_DELAY_MILLIS);
    }

    void uploadHeaderImage() {
        validateImageFile(HEADER_IMAGE_PATH, "Header image file not found: ");
        int fileInputCountBeforeClick = driver.findElements(By.cssSelector(FILE_INPUT_SELECTOR)).size();
        clickAddHeaderImage();
        uploadImage(waitForHeaderFileInput(fileInputCountBeforeClick), HEADER_IMAGE_PATH);
        System.out.println("Header image uploaded successfully");
        sleep(CLICK_DELAY_MILLIS);

    }

    void handleHeaderImageLayoutAndColourStep() {
        if (isColourSelectionDisabled()) {
            System.out.println("Colour field is disabled for full header image, skipping colour selection");
            sleep(CLICK_DELAY_MILLIS);

            return;
            
        }

        scrollColourCarousel();
        sleep(CLICK_DELAY_MILLIS);

        clickAnyBrightColour();
        sleep(CLICK_DELAY_MILLIS);

    }

//    // Switches the uploaded header into the full-image layout when that control is available.
//    boolean clickFullHeaderImageIfPresent() {
//        WebElement fullHeaderImageButton = findFirstVisibleElement(FULL_HEADER_IMAGE_SELECTORS);
//        if (fullHeaderImageButton == null) {
//            System.out.println("Full/small header image button not visible, continuing");
//            return false;
//        }
//
//        click(fullHeaderImageButton);
//        System.out.println("Clicked full header image button");
//        sleep(CLICK_DELAY_MILLIS);
//        return true;
//    }

    @SuppressWarnings("deprecation")
	boolean isColourSelectionDisabled() {
        try {
            WebElement colourSection = waitForElement(COLOR_SECTION_XPATH);
            if (colourSection == null) {
                return true;
            }

            String className = String.valueOf(colourSection.getAttribute("class")).toLowerCase();
            String ariaDisabled = String.valueOf(colourSection.getAttribute("aria-disabled")).toLowerCase();
            if (className.contains("disabled") || "true".equals(ariaDisabled)) {
                return true;
            }

            java.util.List<WebElement> colourOptions = driver.findElements(By.xpath(COLOR_CHIP_XPATH));
            for (WebElement colourOption : colourOptions) {
                try {
                    if (colourOption != null && colourOption.isDisplayed() && colourOption.isEnabled()) {
                        return false;
                    }
                } catch (Exception ignored) {
                }
            }

            return true;
        } catch (Exception e) {
            return true;
        }
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
            sleep(CLICK_DELAY_MILLIS);

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
                        sleep(CLICK_DELAY_MILLIS);

                        return;
                    }
                } catch (Exception ignored) {
                }
            }
        } catch (Exception ignored) {
        }

        throw new RuntimeException("No selectable bright colour option found");
    }

    void clickNextButton() {
        clickRequiredElement(NEXT_BUTTON_SELECTORS, "'Next' button not found");
        System.out.println("Clicked Next button");
        sleep(CLICK_DELAY_MILLIS);

    }

    void fillCompanyDetails() {
        fillCompanyName();
        selectBusinessType();
        fillAboutIfVisible();
        selectLocation();
        System.out.println("Completed Company details section");
    }

    void fillCompanyName() {
        if (COMPANY_NAME.length() < 2 || COMPANY_NAME.length() > 50) {
            throw new RuntimeException("Company name must be between 2 and 50 characters");
        }

        WebElement companyNameField = waitForAnyVisibleElement(COMPANY_NAME_SELECTORS);
        if (companyNameField == null) {
            throw new RuntimeException("Company name field not found");
        }

        setFieldValue(companyNameField, COMPANY_NAME);
        System.out.println("Filled Company name");
        sleep(CLICK_DELAY_MILLIS);
    }

    void selectBusinessType() {
        WebElement businessTypeField = waitForAnyVisibleElement(new String[] {
            "div.MuiInputBase-root.MuiOutlinedInput-root.MuiSelect-root.css-6j12it"
        });
        if (businessTypeField != null) {
            click(businessTypeField);
            sleep(500);
        }

        WebElement businessTypeCombobox = waitForElement(BUSINESS_TYPE_FIELD_XPATH);
        if (businessTypeCombobox == null || !businessTypeCombobox.isDisplayed()) {
            businessTypeCombobox = waitForAnyVisibleElement(BUSINESS_TYPE_TRIGGER_SELECTORS);
        }
        if (businessTypeCombobox == null) {
            businessTypeCombobox = waitForAnyVisibleElement(BUSINESS_TYPE_SELECTORS);
        }
        if (businessTypeCombobox == null) {
            throw new RuntimeException("Business type field not found");
        }

        click(businessTypeCombobox);
        sleep(CLICK_DELAY_MILLIS);

        WebElement option = waitForAnyBusinessTypeOption();
        if (option != null) {
            click(option);
            System.out.println("Selected Business type");
            sleep(CLICK_DELAY_MILLIS);
            return;
        }

        selectBusinessTypeWithKeyboard(businessTypeCombobox);
    }

    void fillAboutIfVisible() {
        WebElement aboutField = findFirstVisibleElement(ABOUT_SELECTORS);
        if (aboutField == null) {
            System.out.println("About field not visible, continuing");
            return;
        }

        setFieldValue(aboutField, ABOUT_TEXT);
        System.out.println("Filled About section");
        sleep(CLICK_DELAY_MILLIS);
    }

    void selectLocation() {
        WebElement locationField = waitForAnyVisibleElement(LOCATION_SELECTORS);
        if (locationField == null) {
            throw new RuntimeException("Location field not found");
        }

        setFieldValue(locationField, LOCATION_NAME);
        sleep(CLICK_DELAY_MILLIS);

        WebElement locationOption = waitForLocationDropdownOption();
        if (locationOption == null) {
            throw new RuntimeException("Location dropdown option not found");
        }

        click(locationOption);
        System.out.println("Selected Location");
        sleep(CLICK_DELAY_MILLIS);
    }

    @SuppressWarnings("deprecation")
    WebElement waitForAnyBusinessTypeOption() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
            return shortWait.until(d -> {
                try {
                    WebElement listbox;
                    try {
                        listbox = d.findElement(By.xpath(BUSINESS_TYPE_MENU_XPATH));
                    } catch (Exception ignored) {
                        listbox = d.findElement(By.xpath(BUSINESS_TYPE_LISTBOX_XPATH));
                    }
                    if (listbox == null || !listbox.isDisplayed()) {
                        return null;
                    }

                    for (WebElement option : listbox.findElements(By.xpath(".//li[@role='option']"))) {
                        try {
                            if (option == null || !option.isDisplayed() || !option.isEnabled()) {
                                continue;
                            }

                            String text = option.getText();
                            String ariaDisabled = String.valueOf(option.getAttribute("aria-disabled")).toLowerCase();
                            if (text != null
                                && !text.trim().isEmpty()
                                && !text.trim().equalsIgnoreCase(BUSINESS_TYPE_PLACEHOLDER_TEXT)
                                && !"true".equals(ariaDisabled)) {
                                return option;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                } catch (Exception ignored) {
                    return null;
                }

                for (WebElement option : d.findElements(By.xpath(BUSINESS_TYPE_TARGET_OPTION_XPATH))) {
                    try {
                        if (option != null && option.isDisplayed() && option.isEnabled()) {
                            return option;
                        }
                    } catch (Exception ignored) {
                    }
                }

                for (WebElement option : d.findElements(By.xpath(BUSINESS_TYPE_OPTION_XPATH))) {
                    try {
                        if (option != null && option.isDisplayed() && option.isEnabled()) {
                            return option;
                        }
                    } catch (Exception ignored) {
                    }
                }

                for (String selector : DROPDOWN_OPTION_SELECTORS) {
                    try {
                        By locator = getLocator(selector);
                        for (WebElement option : d.findElements(locator)) {
                            if (option == null || !option.isDisplayed()) {
                                continue;
                            }

                            String text = option.getText();
                            if (text == null) {
                                continue;
                            }

                            String normalizedText = text.trim().toLowerCase();
                            String ariaDisabled = String.valueOf(option.getAttribute("aria-disabled")).toLowerCase();
                            if (!normalizedText.isEmpty()
                                && !normalizedText.contains("please select")
                                && !"true".equals(ariaDisabled)) {
                                return option;
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
                return null;
            });
        } catch (Exception e) {
            return null;
        }
    }

    WebElement waitForLocationDropdownOption() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
            return shortWait.until(d -> {
                for (WebElement option : d.findElements(By.xpath(LOCATION_DROPDOWN_OPTION_XPATH))) {
                    try {
                        if (option != null && option.isDisplayed()) {
                            String text = option.getText();
                            if (text != null && !text.trim().isEmpty()) {
                                return option;
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
                return null;
            });
        } catch (Exception e) {
            return null;
        }
    }

    void selectBusinessTypeWithKeyboard(WebElement businessTypeField) {
        try {
            click(businessTypeField);
        } catch (Exception ignored) {
        }

        try {
            businessTypeField.sendKeys(Keys.ARROW_DOWN);
            sleep(500);
            businessTypeField.sendKeys(Keys.ENTER);
            System.out.println("Selected Business type with keyboard");
            sleep(CLICK_DELAY_MILLIS);
            return;
        } catch (Exception ignored) {
        }

        try {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].focus();" +
                "arguments[0].dispatchEvent(new KeyboardEvent('keydown', {key:'ArrowDown', bubbles:true}));" +
                "arguments[0].dispatchEvent(new KeyboardEvent('keydown', {key:'Enter', bubbles:true}));",
                businessTypeField
            );
            System.out.println("Selected Business type with JavaScript keyboard fallback");
            sleep(CLICK_DELAY_MILLIS);
        } catch (Exception e) {
            throw new RuntimeException("Business type dropdown option not found");
        }
    }

    void clickRequiredElement(String[] selectors, String errorMessage) {
        WebElement element = findFirstVisibleElement(selectors);
        if (element == null) {
            throw new RuntimeException(errorMessage);
        }
        click(element);
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

    WebElement waitForAnyVisibleElement(String[] selectors) {
        for (String selector : selectors) {
            try {
                By locator = getLocator(selector);
                WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
                java.util.List<WebElement> elements =
                    shortWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                for (WebElement element : elements) {
                    if (element != null && element.isDisplayed()) {
                        return element;
                    }
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

    WebElement waitForElementOrThrow(String selector, String errorMessage) {
        WebElement element = waitForElement(selector);
        if (element == null) {
            throw new RuntimeException(errorMessage);
        }
        return element;
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

    void validateImageFile(String imagePath, String errorPrefix) {
        if (!Files.exists(Path.of(imagePath))) {
            throw new RuntimeException(errorPrefix + imagePath);
        }
    }

    void setFieldValue(WebElement field, String value) {
        try {
            scrollIntoView(field);
            click(field);
        } catch (Exception ignored) {
        }

        try {
            field.clear();
        } catch (Exception ignored) {
        }

        try {
            field.sendKeys(value);
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                field, value
            );
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
            return waitForElementOrThrow(FILE_INPUT_SELECTOR, "Header image file input not found");
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
            WebDriverWait shortWait = new WebDriverWait(driver, FILE_UPLOAD_WAIT_TIME);
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

    void uploadImage(WebElement fileInput, String imagePath) {
        uploadImageToAvailableInput(fileInput, imagePath);
        waitForFileUploadValue();
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
        waitForUploadProgressCompletion(PROFILE_UPLOAD_LABEL);
    }

    void waitUntilHeaderUploadProgressCompletes() {
        waitForUploadProgressCompletion(HEADER_UPLOAD_LABEL);
    }

    void waitForUploadProgressCompletion(String uploadLabel) {
        try {
            WebDriverWait uploadWait = new WebDriverWait(driver, UPLOAD_PROGRESS_WAIT_TIME);

            boolean progressAppeared = false;
            try {
                uploadWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UPLOAD_PROGRESS_BOX_XPATH)));
                progressAppeared = true;
                System.out.println(uploadLabel + " progress appeared");
            } catch (Exception ignored) {
            }

            if (!progressAppeared) {
                System.out.println(uploadLabel + " progress was not visible, continuing");
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
            System.out.println(uploadLabel + " progress completed and disappeared");
        } catch (Exception e) {
            throw new RuntimeException(uploadLabel + " progress did not complete before timeout", e);
        }
    }

    void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sleep interrupted", e);
        }
    }
}
