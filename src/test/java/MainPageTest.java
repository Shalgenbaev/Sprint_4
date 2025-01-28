import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MainPageTest {

    private DriverRule driverRule; // Экземпляр DriverRule
    private MainPage mainPage; // Объект главной страницы
    private int questionIndex; // Индекс вопроса для тестирования
    private String expectedAnswer; // Ожидаемый ответ
    private WebDriver driver; // Экземпляр WebDriver для тестов

    // Конструктор, который принимает параметры
    public MainPageTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    // Определяем параметры для теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, MainPage.EXPECTED_ANSWERS[0]},
                {1, MainPage.EXPECTED_ANSWERS[1]},
                {2, MainPage.EXPECTED_ANSWERS[2]},
                {3, MainPage.EXPECTED_ANSWERS[3]},
                {4, MainPage.EXPECTED_ANSWERS[4]},
                {5, MainPage.EXPECTED_ANSWERS[5]},
                {6, MainPage.EXPECTED_ANSWERS[6]},
                {7, MainPage.EXPECTED_ANSWERS[7]}
        });
    }

    @Before
    public void setUp() {
        driverRule = new DriverRule(); // Инициализация DriverRule
        driverRule.setUp(); // Инициализация драйвера
        driver = driverRule.getDriver(); // Получаем WebDriver для каждого теста
        mainPage = new MainPage(driver); // Инициализация главной страницы
    }

    @Test
    public void testFaqAnswer() {
        // Прокручиваем к блоку вопросов
        mainPage.scrollToQuestionsBlock();
        // Кликаем на вопрос по индексу
        mainPage.clickQuestion(questionIndex);
        // Проверяем ответ на вопрос
        mainPage.verifyAnswerText(questionIndex);
    }

    @After
    public void tearDown() {
        driverRule.tearDown(); // Завершение работы драйвера после каждого теста
    }
}
