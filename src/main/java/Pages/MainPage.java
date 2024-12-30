package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static Pages.FaqBlock.getQuestionsBlock;
import static Pages.FaqBlock.getQuestion;
import static Pages.FaqBlock.getAnswer;
import static Pages.FaqBlock.getExpectedAnswer;
import static Pages.FaqBlock.getErrorMessage;

public class MainPage {
    private static final int DEFAULT_TIMEOUT = 5;
    private WebDriver driver; // Объект WebDriver для управления браузером

    // Конструктор класса MainPage, принимающий объект WebDriver
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для прокрутки к блоку вопросов
    public MainPage scrollToQuestionsBlock() {
        WebElement questionsBlock = driver.findElement(getQuestionsBlock());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionsBlock);
        return this;
    }

    // Метод для клика на вопрос по индексу
    public MainPage clickQuestion(int index) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            WebElement questionElement = wait.until(ExpectedConditions.elementToBeClickable(getQuestion(index)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", questionElement);
        } catch (Exception e) {
            System.out.println("Ошибка при клике на вопрос: " + e.getMessage());
        }
        return this;
    }

    // Метод для проверки текста ответа на вопрос по индексу
    public MainPage verifyAnswerText(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getAnswer(index)));

        String actualAnswer = driver.findElement(getAnswer(index)).getText();
        assertEquals(getErrorMessage(), getExpectedAnswer(index), actualAnswer);
        return this;
    }
}
