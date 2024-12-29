package Pages;

import org.openqa.selenium.By;
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
    private static final int DEFAULT_TIMEOUT = 5; // Временной таймаут для ожидания

    private WebDriver driver; // Объект WebDriver для управления браузером

    // Конструктор класса MainPage, принимающий объект WebDriver
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для прокрутки к блоку вопросов
    public MainPage scrollToQuestionsBlock() {
        // Находим элемент вопроса на странице
        WebElement questionsBlock = driver.findElement(getQuestionsBlock());
        // Прокручиваем к элементу, чтобы он стал видимым
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionsBlock);
        return this; // Возвращаем текущий объект для последующей цепочки вызовов
    }

    // Метод для клика на вопрос по индексу
    public MainPage clickQuestion(int index) {
        // Кликаем на элемент вопроса по переданному индексу
        driver.findElement(getQuestion(index)).click();
        return this; // Возвращаем текущий объект для последующей цепочки вызовов
    }

    // Метод для проверки текста ответа на вопрос по индексу
    public MainPage verifyAnswerText(int index) {
        // Ожидаем, пока элемент с ответом станет видимым на странице
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getAnswer(index)));

        // Получаем текст фактического ответа
        String actualAnswer = driver.findElement(getAnswer(index)).getText();
        // Сравниваем фактический ответ с ожидаемым ответом
        assertEquals(getErrorMessage(), getExpectedAnswer(index), actualAnswer);
        return this; // Возвращаем текущий объект для последующей цепочки вызовов
    }
}
