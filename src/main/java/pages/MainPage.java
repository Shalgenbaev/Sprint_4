package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MainPage {
    private static final int DEFAULT_TIMEOUT = 5;
    // Локатор для закрытия всплывающего окна с куками
    public static final By CLOSE_COOKIE = By.className("App_CookieButton__3cvqF");
    // Верхняя кнопка Заказать
    protected static final By TOP_ORDER_BUTTON = By.xpath(".//div[starts-with(@class, 'Header')]/button[text()='Заказать']");
    // Нижняя кнопка заказа
    protected static final By BOTTOM_ORDER_BUTTON = By.xpath("//div[contains(@class, 'Home_FinishButton__1_cWm')]//button[text()='Заказать']");


    // Блок вопросов FAQ
    private static final By QUESTIONS_BLOCK = By.className("Home_FourPart__1uthg");
    // Вопросы блока FAQ
    private static final By[] QUESTIONS = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    };
    // Ответы блока FAQ
    private static final By[] ANSWERS = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };
    // Ожидаемые ответы
    public static final String[] EXPECTED_ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    private static final String ERROR_MESSAGE = "Текст в строке не соответствует ожиданиям";

    private WebDriver driver; // Объект WebDriver для управления браузером

    // Конструктор класса MainPage, принимающий объект WebDriver
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для прокрутки к блоку вопросов
    public MainPage scrollToQuestionsBlock() {
        WebElement questionsBlock = driver.findElement(QUESTIONS_BLOCK);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionsBlock);
        return this;
    }

    // Метод для клика на вопрос по индексу
    public MainPage clickQuestion(int index) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            WebElement questionElement = wait.until(ExpectedConditions.elementToBeClickable(QUESTIONS[index]));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", questionElement);
        } catch (Exception e) {
            System.out.println("Ошибка при клике на вопрос: " + e.getMessage());
        }
        return this;
    }

    // Метод для проверки текста ответа на вопрос по индексу
    public MainPage verifyAnswerText(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(ANSWERS[index]));

        String actualAnswer = driver.findElement(ANSWERS[index]).getText();
        assertEquals(ERROR_MESSAGE, EXPECTED_ANSWERS[index], actualAnswer);
        return this;
    }
}
