import Pages.MainPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver; // Замените на используемый вами драйвер (если необходимо)

import java.util.Arrays;
import java.util.Collection;

import static Pages.FaqBlock.*;

@RunWith(Parameterized.class) // Указываем, что тест будет параметризован
public class MainPageTest {

    private static DriverRule driverRule; // Статический экземпляр DriverRule
    private static WebDriver driver; // Статический экземпляр WebDriver
    private int questionIndex; // Индекс вопроса для тестирования
    private String expectedAnswer; // Ожидаемый ответ

    // Конструктор, который принимает параметры
    public MainPageTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    // Определяем параметры для теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, getExpectedAnswer(0) },
                { 1, getExpectedAnswer(1) },
                { 2, getExpectedAnswer(2) },
                { 3, getExpectedAnswer(3) },
                { 4, getExpectedAnswer(4) },
                { 5, getExpectedAnswer(5) },
                { 6, getExpectedAnswer(6) },
                { 7, getExpectedAnswer(7) }
        });
    }

    @BeforeClass
    public static void setUpClass() {
        driverRule = new DriverRule(); // Инициализация DriverRule
        driverRule.setUp(); // Инициализация драйвера
        driver = driverRule.getDriver(); // Получаем WebDriver
    }

    @AfterClass
    public static void tearDownClass() {
        driverRule.tearDown(); // Завершение работы драйвера
    }

    @Test
    public void testFaqAnswer() {
        MainPage mainPage = new MainPage(driver); // Используем общий экземпляр WebDriver
        mainPage.scrollToQuestionsBlock();
        mainPage.clickQuestion(questionIndex);
        mainPage.verifyAnswerText(questionIndex);
    }
}
