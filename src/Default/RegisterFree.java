package Default;

import java.time.Duration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

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
    private static final String ABOUT_TEXT = "This is sample about text added for register free form testing.";
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
        "div.ql-editor[contenteditable='true'][data-placeholder*='About' i]",
        "div[contenteditable='true'][data-placeholder*='About your business' i]",
        "//label[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'about')]/following::textarea[1]",
        "//label[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'about')]/following::*[@contenteditable='true'][1]"
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

    private static final String[] MONDAY_SWITCH_SELECTORS = {
        "//p[normalize-space()='Monday']/following::input[@role='switch'][1]",
        "//span[normalize-space()='Monday']/following::input[@role='switch'][1]",
        "//div[.//p[normalize-space()='Monday']]//input[@role='switch' and @type='checkbox']",
        "//div[.//span[normalize-space()='Monday']]//input[@role='switch' and @type='checkbox']",
        "//label[.//*[normalize-space()='Monday']]//input[@role='switch']",
        "//div[.//*[normalize-space()='Monday']]//input[@role='switch' and @type='checkbox']"
    };

    private static final String[][] EXTRA_DAY_SWITCHES = {
        {
            "Tuesday",
            "//p[normalize-space()='Tuesday']/following::input[@role='switch'][1]",
            "//span[normalize-space()='Tuesday']/following::input[@role='switch'][1]",
            "//div[.//p[normalize-space()='Tuesday']]//input[@role='switch' and @type='checkbox']",
            "//div[.//span[normalize-space()='Tuesday']]//input[@role='switch' and @type='checkbox']"
        },
        {
            "Wednesday",
            "//p[normalize-space()='Wednesday']/following::input[@role='switch'][1]",
            "//span[normalize-space()='Wednesday']/following::input[@role='switch'][1]",
            "//div[.//p[normalize-space()='Wednesday']]//input[@role='switch' and @type='checkbox']",
            "//div[.//span[normalize-space()='Wednesday']]//input[@role='switch' and @type='checkbox']"
        },
        {
            "Thursday",
            "//p[normalize-space()='Thursday']/following::input[@role='switch'][1]",
            "//span[normalize-space()='Thursday']/following::input[@role='switch'][1]",
            "//div[.//p[normalize-space()='Thursday']]//input[@role='switch' and @type='checkbox']",
            "//div[.//span[normalize-space()='Thursday']]//input[@role='switch' and @type='checkbox']"
        },
        {
            "Friday",
            "//p[normalize-space()='Friday']/following::input[@role='switch'][1]",
            "//span[normalize-space()='Friday']/following::input[@role='switch'][1]",
            "//div[.//p[normalize-space()='Friday']]//input[@role='switch' and @type='checkbox']",
            "//div[.//span[normalize-space()='Friday']]//input[@role='switch' and @type='checkbox']"
        },
        {
            "Saturday",
            "//p[normalize-space()='Saturday']/following::input[@role='switch'][1]",
            "//span[normalize-space()='Saturday']/following::input[@role='switch'][1]",
            "//div[.//p[normalize-space()='Saturday']]//input[@role='switch' and @type='checkbox']",
            "//div[.//span[normalize-space()='Saturday']]//input[@role='switch' and @type='checkbox']"
        },
        {
            "Sunday",
            "//p[normalize-space()='Sunday']/following::input[@role='switch'][1]",
            "//span[normalize-space()='Sunday']/following::input[@role='switch'][1]",
            "//div[.//p[normalize-space()='Sunday']]//input[@role='switch' and @type='checkbox']",
            "//div[.//span[normalize-space()='Sunday']]//input[@role='switch' and @type='checkbox']"
        }
    };

    private static final String VALID_GOOGLE_REVIEW_URL = "https://share.google/osUvsqTwEaAh0QlrF";
    private static final String[] GOOGLE_REVIEW_INPUT_SELECTORS = {
        "input[name='google_maps_url']",
        "input[placeholder*='Google business link url' i]",
        "input[id='_r_2t_']",
        "//p[normalize-space()='Google reviews']/following::input[@name='google_maps_url'][1]",
        "//p[contains(normalize-space(.),'Google reviews')]/following::input[1]"
    };
    private static final String[] GOOGLE_REVIEW_ERROR_SELECTORS = {
        "//input[@name='google_maps_url']/ancestor::div[contains(@class,'MuiFormControl-root')][1]/following-sibling::p",
        "//input[@name='google_maps_url']/following::p[contains(@class,'Mui-error')][1]",
        "//p[contains(@class,'Mui-error')]"
    };
    private static final String[] LINKS_DROPDOWN_SELECTORS = {
        "//p[normalize-space()='Links']/following::div[@role='combobox'][1]",
        "//p[normalize-space()='Links']/following::*[@id='demo-simple-select'][1]",
        "//div[contains(@class,'MuiBox-root') and .//p[normalize-space()='Links']]//div[@role='combobox'][1]"
    };
    private static final String LINKS_LISTBOX_XPATH =
        "//ul[contains(@class,'MuiMenu-list') and @role='listbox']";
    private static final String LINKS_OPTION_XPATH =
        "//ul[@role='listbox']//li[@role='option' and @data-value='External link']";
    private static final String[] LINK_TITLE_INPUT_SELECTORS = {
        "input[name='links.0.title']",
        "input[name='title']",
        "input[placeholder*='title' i]",
        "//p[normalize-space()='Links']/following::input[contains(@placeholder,'Title')][1]",
        "//p[normalize-space()='Links']/following::input[1]"
    };
    private static final String[] LINK_URL_INPUT_SELECTORS = {
        "input[name='links.0.url']",
        "input[placeholder='URL']",
        "input[name='url']",
        "input[name='link']",
        "input[placeholder*='url' i]",
        "input[placeholder*='link' i]",
        "//p[normalize-space()='Links']/following::input[contains(@placeholder,'URL') or contains(@placeholder,'url')][1]",
        "//p[normalize-space()='Links']/following::input[2]"
    };
    private static final String[] LINK_URL_ERROR_SELECTORS = {
        "//input[@name='links.0.url']/ancestor::div[contains(@class,'MuiFormControl-root')][1]/following-sibling::p[contains(@class,'Mui-error')]",
        "//input[@name='links.0.url']/following::p[contains(@class,'Mui-error')][1]",
        "//input[@name='url']/ancestor::div[contains(@class,'MuiFormControl-root')][1]/following-sibling::p[contains(@class,'Mui-error')]",
        "//input[@name='link']/ancestor::div[contains(@class,'MuiFormControl-root')][1]/following-sibling::p[contains(@class,'Mui-error')]",
        "//input[contains(@placeholder,'URL') or contains(@placeholder,'url')]/following::p[contains(@class,'Mui-error')][1]"
    };
    private static final String[] LINK_URL_VALID_INDICATOR_SELECTORS = {
        "//input[@name='links.0.url']/following::*[local-name()='svg'][1]",
        "//*[local-name()='svg' and .//*[local-name()='linearGradient']]",
        "//input[@name='url']/following::*[local-name()='svg'][1]",
        "//input[@name='link']/following::*[local-name()='svg'][1]"
    };
    private static final String[] LINK_URL_INVALID_ICON_SELECTORS = {
        "//input[@name='links.0.url']/following::*[local-name()='svg'][1]",
        "//*[local-name()='svg' and .//*[local-name()='path' and contains(@d,'M256 48')]]",
        "//input[@name='url']/following::*[local-name()='svg'][1]",
        "//input[@name='link']/following::*[local-name()='svg'][1]"
    };
    private static final String VALID_LINK_URL = "https://nosho.vercel.app/build-your-profile";
    private static final String[] NOTIFICATION_EMAIL_INPUT_SELECTORS = {
        "input[name='email']",
        "input[name='notification_email']",
        "input[type='email']",
        "input[placeholder*='email' i]",
        "//p[contains(normalize-space(.),'notification')]/following::input[contains(@placeholder,'email') or @type='email'][1]",
        "//p[contains(normalize-space(.),'notification')]/following::input[1]"
    };
    private static final String[] NOTIFICATION_EMAIL_ERROR_SELECTORS = {
        "//input[@type='email']/ancestor::div[contains(@class,'MuiFormControl-root')][1]/following-sibling::p[contains(@class,'Mui-error')]",
        "//input[contains(@placeholder,'email') or contains(@placeholder,'Email')]/following::p[contains(@class,'Mui-error')][1]",
        "//p[contains(@class,'Mui-error')]"
    };
    private static final String[] SAVE_BUTTON_SELECTORS = {
        "//button[normalize-space()='Save']",
        "//p[normalize-space()='Notifications']/following::button[normalize-space()='Save'][1]",
        "//p[contains(normalize-space(.),'notification')]/following::button[normalize-space()='Save'][1]",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'save')]"
    };
    private static final String SIGNUP_INVALID_USERNAME = "ab@";
    private static final String SIGNUP_INVALID_FULL_NAME = "A1";
    private static final String SIGNUP_INVALID_EMAIL = "wrongemail";
    private static final String SIGNUP_INVALID_PHONE = "+91 7654";
    private static final String SIGNUP_INVALID_PASSWORD = "admin123";
    private static final String SIGNUP_VALID_USERNAME = "manualtest";
    private static final String SIGNUP_VALID_FULL_NAME = "Anil";
    private static final String SIGNUP_VALID_EMAIL = "manualtest@gmail.com";
    private static final String SIGNUP_VALID_PHONE = "+998 74 567 35";
    private static final String SIGNUP_VALID_PASSWORD = "Admin@123";
    private static final String SIGNUP_NEW_USER_PREFIX = "newuserrr";
    private static final String[] SIGNUP_USERNAME_SELECTORS = {
        "input[name='username']",
        "input[id='username']",
        "input[placeholder*='username' i]"
    };
    private static final String[] SIGNUP_FULL_NAME_SELECTORS = {
        "input[name='fullName']",
        "input[name='fullname']",
        "input[id='fullName']",
        "input[id='fullname']",
        "input[placeholder*='full name' i]"
    };
    private static final String[] SIGNUP_EMAIL_SELECTORS = {
        "input[name='email']",
        "input[id='email']",
        "input[placeholder*='email' i]",
        "input[type='email']"
    };
    private static final String[] SIGNUP_COUNTRY_CODE_SELECTORS = {
        "select[name*='country' i]",
        "select[id*='country' i]",
        "select[name*='code' i]",
        "select[id*='code' i]",
        "[role='combobox'][aria-label*='country' i]",
        "[role='combobox'][aria-label*='code' i]",
        "select"
    };
    private static final String[] SIGNUP_PHONE_SELECTORS = {
        "input[type='tel']",
        "input[name='phone']",
        "input[name='mobile']",
        "input[id='phone']",
        "input[id='mobile']",
        "input[placeholder*='phone' i]",
        "input[placeholder*='mobile' i]"
    };
    private static final String[] SIGNUP_PASSWORD_SELECTORS = {
        "input[type='password'][name='password']",
        "input[type='password'][id='password']",
        "input[name='password']",
        "input[id='password']",
        "input[type='password']"
    };
    private static final String[] SIGNUP_CREATE_ACCOUNT_BUTTON_SELECTORS = {
        "//button[normalize-space()='Create account']",
        "//a[normalize-space()='Create account']",
        "//*[self::button or self::a or self::span][normalize-space()='Create account']",
        "//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'create account')]",
        "button[class*='create' i]",
        "button[id*='create' i]",
        "button[type='submit']",
        "input[type='submit']"
    };

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
            page.enableMondayAndTwoRandomSwitches();
            page.testGoogleReviewField();
            page.selectLinksDropdownOption();
            page.testSelectedLinkFields();
            page.clickNextButton();
            page.testNotificationEmailField();
            page.runSignupFromUsernameField();

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

    void enableMondayAndTwoRandomSwitches() {
        WebElement mondaySwitch = findMondaySwitch();
        if (mondaySwitch == null) {
            throw new RuntimeException("Monday switch not found");
        }

        ensureSwitchOn(mondaySwitch, "Monday");
        enableTwoRandomAdditionalDaySwitches();
    }

    void testGoogleReviewField() {
        WebElement googleReviewField = waitForAnyVisibleElement(GOOGLE_REVIEW_INPUT_SELECTORS);
        if (googleReviewField == null) {
            throw new RuntimeException("Google review input field not found");
        }

        scrollIntoViewCenter(googleReviewField);
        sleep(1000);

        String invalidUrl = buildRandomInvalidGoogleReviewValue();
        setFieldValue(googleReviewField, invalidUrl);
        System.out.println("Entered invalid Google review test value: " + invalidUrl);
        sleep(CLICK_DELAY_MILLIS);
        boolean invalidRejected = hasGoogleReviewValidationError(googleReviewField);
        System.out.println("Invalid Google review URL rejected: " + invalidRejected);

        clearTextField(googleReviewField, "Google review field");
        sleep(2000);

        String validUrl = buildRandomValidGoogleReviewValue();
        setFieldValue(googleReviewField, validUrl);
        System.out.println("Entered valid Google review test value: " + validUrl);
        sleep(CLICK_DELAY_MILLIS);
        boolean validAccepted = !hasGoogleReviewValidationError(googleReviewField);
        System.out.println("Valid Google review URL accepted: " + validAccepted);
    }

    String buildRandomInvalidGoogleReviewValue() {
        return "not-a-google-review-link-" + UUID.randomUUID().toString().substring(0, 8);
    }

    String buildRandomValidGoogleReviewValue() {
        return VALID_GOOGLE_REVIEW_URL;
    }

    void clearTextField(WebElement field, String fieldLabel) {
        try {
            scrollIntoViewCenter(field);
            click(field);
        } catch (Exception ignored) {
        }

        try {
            field.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        } catch (Exception ignored) {
        }

        try {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = '';" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                field
            );
        } catch (Exception ignored) {
        }

        waitForFieldToBeEmpty(field, fieldLabel);
        System.out.println("Cleared " + fieldLabel);
    }

    @SuppressWarnings("deprecation")
	void waitForFieldToBeEmpty(WebElement field, String fieldLabel) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
            shortWait.until(d -> {
                try {
                    String value = String.valueOf(field.getAttribute("value"));
                    return value == null || value.trim().isEmpty();
                } catch (Exception e) {
                    return false;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(fieldLabel + " did not clear before continuing", e);
        }
    }

    boolean hasGoogleReviewValidationError(WebElement field) {
        try {
            @SuppressWarnings("deprecation")
			String ariaInvalid = String.valueOf(field.getAttribute("aria-invalid"));
            if ("true".equalsIgnoreCase(ariaInvalid)) {
                return true;
            }
        } catch (Exception ignored) {
        }

        for (String selector : GOOGLE_REVIEW_ERROR_SELECTORS) {
            try {
                WebElement error = waitForElement(selector);
                if (error != null && error.isDisplayed()) {
                    String text = error.getText();
                    if (text != null && !text.trim().isEmpty()) {
                        return true;
                    }
                }
            } catch (Exception ignored) {
            }
        }

        return false;
    }

    void selectLinksDropdownOption() {
        WebElement linksDropdown = waitForAnyVisibleElement(LINKS_DROPDOWN_SELECTORS);
        if (linksDropdown == null) {
            throw new RuntimeException("Links dropdown field not found");
        }

        scrollIntoViewCenter(linksDropdown);
        click(linksDropdown);
        sleep(CLICK_DELAY_MILLIS);

        WebElement option = waitForLinksDropdownOption();
        if (option == null) {
            throw new RuntimeException("No selectable option found in Links dropdown");
        }

        String optionText = option.getText();
        click(option);
        sleep(CLICK_DELAY_MILLIS);
        System.out.println("Selected Links dropdown option: " + optionText);
    }

    void testSelectedLinkFields() {
        WebElement titleField = waitForAnyVisibleElement(LINK_TITLE_INPUT_SELECTORS);
        if (titleField == null) {
            throw new RuntimeException("Link title field not found");
        }

        WebElement urlField = waitForAnyVisibleElement(LINK_URL_INPUT_SELECTORS);
        if (urlField == null) {
            throw new RuntimeException("Link URL field not found");
        }

        setFieldValue(titleField, "Link " + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("Entered value in link Title field");
        sleep(1000);

        String invalidUrl = buildRandomInvalidGoogleReviewValue();
        setFieldValue(urlField, invalidUrl);
        System.out.println("Entered invalid URL in link field: " + invalidUrl);
        sleep(CLICK_DELAY_MILLIS);
        System.out.println("Invalid URL state shown: " + isLinkUrlInvalid(urlField));

        clearTextField(urlField, "Link URL field");
        sleep(2000);

        setFieldValue(urlField, VALID_LINK_URL);
        System.out.println("Entered valid URL in link field: " + VALID_LINK_URL);
        
        if (!waitForValidLinkIndicator()) {
            throw new RuntimeException("Valid link URL indicator did not appear");
        }

        System.out.println("Valid link URL accepted");
    }

    WebElement waitForLinksDropdownOption() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
            return shortWait.until(d -> {
                try {
                    WebElement listbox = d.findElement(By.xpath(LINKS_LISTBOX_XPATH));
                    if (listbox == null || !listbox.isDisplayed()) {
                        return null;
                    }

                    for (WebElement option : listbox.findElements(By.xpath(".//li[@role='option']"))) {
                        String text = option.getText();
                        String normalizedText = text == null ? "" : text.trim().replaceAll("\\s+", " ");
                        if (option.isDisplayed()
                            && option.isEnabled()
                            && !normalizedText.isEmpty()
                            && !normalizedText.equalsIgnoreCase("Add a link")
                            && !normalizedText.equalsIgnoreCase("Add link")) {
                            return option;
                        }
                    }

                    for (WebElement option : d.findElements(By.xpath(LINKS_OPTION_XPATH))) {
                        String text = option == null ? "" : option.getText();
                        String normalizedText = text == null ? "" : text.trim().replaceAll("\\s+", " ");
                        if (option != null
                            && option.isDisplayed()
                            && option.isEnabled()
                            && !normalizedText.isEmpty()
                            && !normalizedText.equalsIgnoreCase("Add a link")
                            && !normalizedText.equalsIgnoreCase("Add link")) {
                            return option;
                        }
                    }
                } catch (Exception ignored) {
                }
                return null;
            });
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("deprecation")
	boolean isLinkUrlInvalid(WebElement field) {
        if (hasLinkUrlValidationError()) {
            return true;
        }

        try {
            String ariaInvalid = String.valueOf(field.getAttribute("aria-invalid"));
            if ("true".equalsIgnoreCase(ariaInvalid)) {
                return true;
            }
        } catch (Exception ignored) {
        }

        return isAnyVisibleElementPresent(LINK_URL_INVALID_ICON_SELECTORS)
            && !isAnyVisibleElementPresent(LINK_URL_VALID_INDICATOR_SELECTORS);
    }

    boolean hasLinkUrlValidationError() {
        for (String selector : LINK_URL_ERROR_SELECTORS) {
            try {
                WebElement error = waitForElement(selector);
                if (error != null && error.isDisplayed()) {
                    String text = error.getText();
                    if (text != null && !text.trim().isEmpty()) {
                        return true;
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    boolean waitForValidLinkIndicator() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
            return shortWait.until(d ->
                isAnyVisibleElementPresent(LINK_URL_VALID_INDICATOR_SELECTORS)
                    && !hasLinkUrlValidationError()
            );
        } catch (Exception e) {
            return false;
        }
    }

    boolean isAnyVisibleElementPresent(String[] selectors) {
        for (String selector : selectors) {
            try {
                WebElement element = waitForElement(selector);
                if (element != null && element.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    void testNotificationEmailField() {
        WebElement emailField = waitForAnyVisibleElement(NOTIFICATION_EMAIL_INPUT_SELECTORS);
        if (emailField == null) {
            throw new RuntimeException("Notification email field not found");
        }

        scrollIntoViewCenter(emailField);
        String invalidEmail = "invalid-email-" + UUID.randomUUID().toString().substring(0, 6);
        setFieldValue(emailField, invalidEmail);
        System.out.println("Entered invalid notification email: " + invalidEmail);
        sleep(CLICK_DELAY_MILLIS);
        clickSaveButton();
        sleep(1000);
        System.out.println("Invalid notification email shown: " + hasNotificationEmailValidationError(emailField));

        clearTextField(emailField, "Notification email field");

        String validEmail = buildRandomValidEmail();
        setFieldValue(emailField, validEmail);
        System.out.println("Entered valid notification email: " + validEmail);
        sleep(1000);
        clickSaveButton();
        sleep(1000);
        System.out.println("Valid notification email accepted: " + !hasNotificationEmailValidationError(emailField));
    }

    void clickSaveButton() {
        clickRequiredElement(SAVE_BUTTON_SELECTORS, "'Save' button not found");
        System.out.println("Clicked Save button");
    }

    void runSignupFromUsernameField() {
        Signup signup = new Signup();
        System.out.println(">> TC_SIGNUP_FROM_USERNAME - Starting directly from username field");

        try {
            WebElement signupForm = waitForSignupFormContainer();

            fillSignupFormFromCurrentScreen(
                signupForm,
                SIGNUP_INVALID_USERNAME,
                SIGNUP_INVALID_FULL_NAME,
                SIGNUP_INVALID_EMAIL,
                SIGNUP_INVALID_PHONE,
                SIGNUP_INVALID_PASSWORD
            );
            clickSignupCreateAccountButton(signupForm);
            boolean invalidStateShown = isCurrentSignupInvalidState(signup, signupForm);
            boolean invalidRejected = signup.isSignupSubmissionRejected();
            System.out.println("   Invalid signup validation shown: " + invalidStateShown);
            System.out.println("   Invalid signup rejected: " + invalidRejected);

            clearSignupFormFromCurrentScreen(signupForm);
            fillSignupFormFromCurrentScreen(
                signupForm,
                SIGNUP_VALID_USERNAME,
                SIGNUP_VALID_FULL_NAME,
                SIGNUP_VALID_EMAIL,
                SIGNUP_VALID_PHONE,
                SIGNUP_VALID_PASSWORD
            );
            clickSignupCreateAccountButton(signupForm);
            boolean duplicateUserShown = signup.waitForDuplicateUserError();
            boolean existingUserRejected = signup.isSignupSubmissionRejected();
            System.out.println("   Existing user/username duplicate shown: " + duplicateUserShown);
            System.out.println("   Existing user submission rejected: " + existingUserRejected);

            String uniqueSuffix = String.valueOf(System.currentTimeMillis());
            String freshUsername = SIGNUP_NEW_USER_PREFIX + uniqueSuffix;
            String freshEmail = SIGNUP_NEW_USER_PREFIX + uniqueSuffix + "@mailinator.com";

            clearSignupFormFromCurrentScreen(signupForm);
            fillSignupFormFromCurrentScreen(
                signupForm,
                freshUsername,
                SIGNUP_VALID_FULL_NAME,
                freshEmail,
                SIGNUP_VALID_PHONE,
                SIGNUP_VALID_PASSWORD
            );
            clickSignupCreateAccountButton(signupForm);
            signup.waitForVerificationModal();
            signup.assertTrue(signup.isVerificationModalVisible(),
                "Verification modal should appear after entering fresh valid credentials");
            System.out.println("   PASS - Cleared fields, entered fresh credentials, and opened verification modal");

            signup.tc_signup_verify_invalid_then_valid_otp_in_same_modal();
        } catch (Exception e) {
            throw new RuntimeException("Signup from username field failed", e);
        }
    }

    WebElement waitForSignupFormContainer() {
        WebElement usernameField = waitForAnyVisibleElement(SIGNUP_USERNAME_SELECTORS);
        if (usernameField == null) {
            throw new RuntimeException("Signup username field not found");
        }

        try {
            WebElement form = usernameField.findElement(By.xpath("./ancestor::form[1]"));
            if (form != null) {
                return form;
            }
        } catch (Exception ignored) {
        }

        try {
            WebElement container = usernameField.findElement(
                By.xpath("./ancestor::*[.//input[@type='password']][1]")
            );
            if (container != null) {
                return container;
            }
        } catch (Exception ignored) {
        }

        throw new RuntimeException("Signup form container not found from username field");
    }

    void fillSignupFormFromCurrentScreen(
        WebElement signupForm,
        String username,
        String fullName,
        String email,
        String phone,
        String password
    ) {
        setFieldValue(findSignupField(signupForm, SIGNUP_USERNAME_SELECTORS), username);
        setFieldValue(findSignupField(signupForm, SIGNUP_FULL_NAME_SELECTORS), fullName);
        setFieldValue(findSignupField(signupForm, SIGNUP_EMAIL_SELECTORS), email);
        selectSignupCountryCodeIfPresent(signupForm, "+91");
        setFieldValue(findSignupField(signupForm, SIGNUP_PHONE_SELECTORS), phone);
        setFieldValue(findSignupField(signupForm, SIGNUP_PASSWORD_SELECTORS), password);
    }

    void clearSignupFormFromCurrentScreen(WebElement signupForm) {
        clearTextField(findSignupField(signupForm, SIGNUP_USERNAME_SELECTORS), "Signup username field");
        clearTextField(findSignupField(signupForm, SIGNUP_FULL_NAME_SELECTORS), "Signup full name field");
        clearTextField(findSignupField(signupForm, SIGNUP_EMAIL_SELECTORS), "Signup email field");
        clearTextField(findSignupField(signupForm, SIGNUP_PHONE_SELECTORS), "Signup phone field");
        clearTextField(findSignupField(signupForm, SIGNUP_PASSWORD_SELECTORS), "Signup password field");
    }

    void assertCurrentSignupInvalidState(Signup signup, WebElement signupForm) {
        signup.assertTrue(signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_USERNAME_SELECTORS)),
            "Username should show validation for invalid value");
        signup.assertTrue(signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_FULL_NAME_SELECTORS)),
            "Full name should show validation for invalid value");
        signup.assertTrue(signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_EMAIL_SELECTORS)),
            "Email should show validation for invalid value");
        signup.assertTrue(signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_PHONE_SELECTORS)),
            "Phone should show validation for invalid value");
        signup.assertTrue(signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_PASSWORD_SELECTORS)),
            "Password should show validation for invalid value");
    }

    boolean isCurrentSignupInvalidState(Signup signup, WebElement signupForm) {
        return signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_USERNAME_SELECTORS))
            || signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_FULL_NAME_SELECTORS))
            || signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_EMAIL_SELECTORS))
            || signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_PHONE_SELECTORS))
            || signup.isValidationStatePresent(findSignupField(signupForm, SIGNUP_PASSWORD_SELECTORS));
    }

    void clickSignupCreateAccountButton(WebElement signupForm) {
        WebElement button = findSignupField(signupForm, SIGNUP_CREATE_ACCOUNT_BUTTON_SELECTORS);
        click(button);
    }

    void selectSignupCountryCodeIfPresent(WebElement signupForm, String code) {
        WebElement countryCode = tryFindSignupField(signupForm, SIGNUP_COUNTRY_CODE_SELECTORS);
        if (countryCode == null) {
            System.out.println("Signup country code field not found, continuing with phone field directly");
            return;
        }

        scrollIntoViewCenter(countryCode);
        click(countryCode);
        sleep(500);

        try {
            countryCode.sendKeys(code);
            countryCode.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            try {
                countryCode.sendKeys(Keys.ARROW_DOWN);
                countryCode.sendKeys(Keys.ENTER);
            } catch (Exception ignored) {
            }
        }
    }

    WebElement findSignupField(WebElement signupForm, String[] selectors) {
        WebElement field = tryFindSignupField(signupForm, selectors);
        if (field != null) {
            return field;
        }

        throw new RuntimeException("Signup field not found for provided selectors");
    }

    WebElement tryFindSignupField(WebElement signupForm, String[] selectors) {
        for (String selector : selectors) {
            try {
                By locator = getLocator(selector);
                java.util.List<WebElement> elements = signupForm.findElements(locator);
                for (WebElement element : elements) {
                    if (element != null && element.isDisplayed() && element.isEnabled()) {
                        return element;
                    }
                }
            } catch (Exception ignored) {
            }
        }

        return null;
    }

    String buildRandomValidEmail() {
        return "nosho+" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    @SuppressWarnings("deprecation")
	boolean hasNotificationEmailValidationError(WebElement field) {
        try {
            String ariaInvalid = String.valueOf(field.getAttribute("aria-invalid"));
            if ("true".equalsIgnoreCase(ariaInvalid)) {
                return true;
            }
        } catch (Exception ignored) {
        }

        for (String selector : NOTIFICATION_EMAIL_ERROR_SELECTORS) {
            try {
                WebElement error = waitForElement(selector);
                if (error != null && error.isDisplayed()) {
                    String text = error.getText();
                    if (text != null && !text.trim().isEmpty()) {
                        return true;
                    }
                }
            } catch (Exception ignored) {
            }
        }

        return false;
    }

    void scrollIntoViewCenter(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                element
            );
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings("deprecation")
    boolean isSwitchEnabled(WebElement input) {
        try {
            String checked = String.valueOf(input.getAttribute("checked"));
            String ariaChecked = String.valueOf(input.getAttribute("aria-checked"));

            return "true".equalsIgnoreCase(checked)
                || "true".equalsIgnoreCase(ariaChecked)
                || input.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("deprecation")
	boolean isSwitchOn(WebElement input) {
        try {
            String checked = input.getAttribute("checked");
            String ariaChecked = input.getAttribute("aria-checked");

            return input.isSelected()
                || "true".equalsIgnoreCase(checked)
                || "true".equalsIgnoreCase(ariaChecked);
        } catch (Exception e) {
            return false;
        }
    }

    boolean waitUntilSwitchEnabled(WebElement input) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
            return wait.until(d -> isSwitchEnabled(input));
        } catch (Exception e) {
            return false;
        }
    }

    void clickSwitchReliably(WebElement element) {
        try {
            scrollIntoViewCenter(element);
            new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            return;
        } catch (Exception ignored) {
        }

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            throw new RuntimeException("Unable to click switch", e);
        }
    }

    void clickSwitchWithJavaScript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].dispatchEvent(new MouseEvent('click', {bubbles:true}));",
            element
        );
    }

    void forceEnableSwitchInput(WebElement input) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].checked = true;" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new MouseEvent('click', { bubbles: true }));",
            input
        );
    }

    void enableTwoRandomAdditionalDaySwitches() {
        ArrayList<String[]> candidateDays = new ArrayList<>();
        Collections.addAll(candidateDays, EXTRA_DAY_SWITCHES);
        Collections.shuffle(candidateDays);

        int enabledCount = 0;
        for (String[] dayConfig : candidateDays) {
            WebElement daySwitch = findFirstAvailableSwitch(dayConfig);
            if (daySwitch == null) {
                continue;
            }

            ensureSwitchOn(daySwitch, dayConfig[0]);
            enabledCount++;
            if (enabledCount == 2) {
                return;
            }
        }

        if (enabledCount < 2) {
            throw new RuntimeException("Could not enable two additional random day switches");
        }
    }

    void ensureSwitchOn(WebElement daySwitch, String dayName) {
        ensureSwitchOn(daySwitch, daySwitch, dayName);
    }

    void ensureSwitchOn(WebElement stateElement, WebElement clickTarget, String dayName) {
        if (isSwitchOn(stateElement)) {
            System.out.println(dayName + " switch already enabled");
            return;
        }

        click(clickTarget);

        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        boolean turnedOn = wait.until(d -> isSwitchOn(stateElement));

        if (!turnedOn) {
            throw new RuntimeException(dayName + " switch did not turn on");
        }

        System.out.println("Enabled " + dayName + " switch");
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

    WebElement findMondaySwitch() {
        for (String selector : MONDAY_SWITCH_SELECTORS) {
            try {
                By locator = getLocator(selector);
                WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
                java.util.List<WebElement> elements =
                    shortWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                for (WebElement element : elements) {
                    try {
                        if (element != null) {
                            return element;
                        }
                    } catch (Exception ignored) {
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    WebElement findFirstAvailableSwitch(String[] dayConfig) {
        for (int i = 1; i < dayConfig.length; i++) {
            try {
                By locator = getLocator(dayConfig[i]);
                WebDriverWait shortWait = new WebDriverWait(driver, WAIT_TIME);
                java.util.List<WebElement> elements =
                    shortWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                for (WebElement element : elements) {
                    try {
                        if (element != null) {
                            return element;
                        }
                    } catch (Exception ignored) {
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return null;
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

    @SuppressWarnings("deprecation")
    void setFieldValue(WebElement field, String value) {
        try {
            scrollIntoView(field);
            click(field);
        } catch (Exception ignored) {
        }

        String contentEditable = "";
        try {
            contentEditable = field.getAttribute("contenteditable");
        } catch (Exception ignored) {
        }
        boolean isContentEditable = "true".equalsIgnoreCase(contentEditable);

        if (isContentEditable) {
            try {
                field.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            } catch (Exception ignored) {
            }

            try {
                field.sendKeys(value);
                return;
            } catch (Exception ignored) {
            }

            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].innerHTML = '<p>' + arguments[1] + '</p>';" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                field, value
            );
            return;
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

    @SuppressWarnings("deprecation")
    void waitForFileUploadValue() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, FILE_UPLOAD_WAIT_TIME);
            shortWait.until(d -> {
                for (WebElement currentInput : d.findElements(By.cssSelector(FILE_INPUT_SELECTOR))) {
                    try {
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
