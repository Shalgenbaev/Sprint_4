import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;


import java.time.Duration;

public class DriverRule {
    private static final String BROWSER_NAME = System.getProperty("browser", "chrome");
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    private WebDriver driver;

    @Before
    public void setUp() {
        switch (BROWSER_NAME.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Нераспознанный браузер: " + BROWSER_NAME);
        }

        driver.get(PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.closeCookiePopup(); // Закрываем всплывающее окно с куками через MainPage
    }

    public WebDriver getDriver() {
        return driver;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
