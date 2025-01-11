import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.time.Duration;

public class DriverRule {
    private static final String BROWSER_NAME = System.getProperty("browser", "chrome"); // Используем свойство системы для определения браузера
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final int DEFAULT_TIMEOUT = 10;

    private WebDriver driver; // Поле для хранения драйвера

    // Метод для инициализации WebDriver
    @Before
    public void setUp() {
        // Переключение между браузерами на основе переменной BROWSER_NAME
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

        driver.get(PAGE_URL); // Открываем целевую страницу
        closeCookiePopup(); // Закрываем всплывающее окно с куками
    }

    // Метод для закрытия всплывающего окна с куками
    public void closeCookiePopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(MainPage.CLOSE_COOKIE)).click();
    }

    // Метод для получения экземпляра WebDriver
    public WebDriver getDriver() {
        return driver;
    }

    // Метод для завершения работы с WebDriver
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Завершение работы драйвера
            driver = null; // Очистка ссылки на драйвер после завершения
        }
    }
}
