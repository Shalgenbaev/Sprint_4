import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverRule {
    private static final String BROWSER_NAME = "chrome";
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By CLOSE_COOKIE = By.className("App_CookieButton__3cvqF");
    private static final int DEFAULT_TIMEOUT = 3;

    private WebDriver driver; // Поле для хранения драйвера

    // Метод для инициализации WebDriver
    public void setUp() {
        if (BROWSER_NAME.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (BROWSER_NAME.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Нераспознанный браузер: " + BROWSER_NAME);
        }
        initialize(); // Инициализируем страницу
    }

    // Метод для инициализации страницы
    private void initialize() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
        driver.get(PAGE_URL);
        driver.findElement(CLOSE_COOKIE).click(); // Закрываем окно cookies
    }

    // Метод для получения экземпляра WebDriver
    public WebDriver getDriver() {
        return driver;
    }

    // Метод для завершения работы с WebDriver
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Завершение работы драйвера
            driver = null; // Очистка ссылки на драйвер после завершения
        }
    }
}
