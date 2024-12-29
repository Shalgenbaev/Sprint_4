import Pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class WebTest {
    private static final String BROWSER_NAME = "chrome";
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By QUESTIONS_BLOCK = By.className("Home_FourPart__1uthg");
    private static final By FIRST_QUESTION = By.id("accordion__heading-0");
    private static final By FIRST_ANSWER = By.id("accordion__panel-0");
    private static final String FIRST_EXPECTED_ANSWER = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";

    private static final By SEVENTH_QUESTION = By.id("accordion__heading-7");

    private static final String ERROR_MESSAGE = "Текст в строке не соответствует ожиданиям";
    public static final int DEFAULT_TIMEOUT = 3;

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = getDriver(BROWSER_NAME);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public static WebDriver getDriver(String browserName) {
        if (browserName.equals("chrome")) {
            return new ChromeDriver();
        } else if (browserName.equals("firefox")){
            return new FirefoxDriver();
        }else {
            throw new RuntimeException("Нераспознанный браузер" + browserName);
        }
    }

    @Test
    public void testArrowPress() {

        driver.get(PAGE_URL);

        WebElement lastStringText = driver.findElement(QUESTIONS_BLOCK);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lastStringText);

        driver.findElement(FIRST_QUESTION).click();
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(driver.findElement(FIRST_ANSWER)));

        assertEquals(ERROR_MESSAGE,
                FIRST_EXPECTED_ANSWER,driver.findElement(FIRST_ANSWER).getText());

    }


    @Test
    public void testFaqAnswer() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.scrollToQuestionsBlock();
        mainPage.clickQuestion();
        mainPage.verifyAnswerText();
    }

    @Test
    public void testFaqAnswer_fluentApi() {
        MainPage mainPage = new MainPage(driver);
        mainPage
                .openMainPage()
                .scrollToQuestionsBlock()
                .clickQuestion()
                .verifyAnswerText();
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}