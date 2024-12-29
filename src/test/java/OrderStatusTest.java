import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class OrderStatusTest {
    private static final String BROWSER_NAME = "chrome";
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final int DEFAULT_TIMEOUT = 3;
    private static final By CLOSE_COOKIE = By.className("App_CookieButton__3cvqF");
    private static final By ORDER_NUMBER_INPUT_FIELD = By.cssSelector(".Input_Input__1iN_Z.Header_Input__xIoUq");
    private static final String ORDER_NUMBER = "12345";
    private static final By ORDER_STATUS_BUTTON = By.className("Header_Link__1TAG7");
    private static final String GO_BUTTON = ".Button_Button__ra12g.Header_Button__28dPO";
    private static final By NOT_FOUND_IMAGE = By.cssSelector("img[alt='Not found']"); // так же .//img[@alt='Not found']

    private WebDriver driver;

    public static WebDriver getDriver(String browserName) {
        if (browserName.equals("chrome")) {
            return new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            return new FirefoxDriver();
        } else {
            throw new RuntimeException("Нераспознанный браузер" + browserName);
        }
    }

    @Before
    public void setUp() {
        driver = getDriver(BROWSER_NAME);
        driver.get(PAGE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
        driver.findElement(CLOSE_COOKIE).click();
    }

    @Test
    public void testOrderNumber() {

        clickOnStatus();

        enterOederId();

        clickOnGo();

        checkErrorMessage();

    }

    private void checkErrorMessage() {
        assertTrue(driver.findElement(NOT_FOUND_IMAGE).isDisplayed());
    }

    private void clickOnGo() {
        driver.findElement(By.cssSelector(GO_BUTTON)).click();
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(driver.findElement(NOT_FOUND_IMAGE)));
    }

    private void enterOederId() {
        driver.findElement(ORDER_NUMBER_INPUT_FIELD).sendKeys(ORDER_NUMBER);
    }

    private void clickOnStatus() {
        driver.findElement(ORDER_STATUS_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(driver.findElement(ORDER_NUMBER_INPUT_FIELD)));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
