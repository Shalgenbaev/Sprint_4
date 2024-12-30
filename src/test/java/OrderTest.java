import Pages.OrderStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;


@RunWith(Parameterized.class)
public class OrderTest {

    private static DriverRule driverRule; // Статический экземпляр DriverRule
    private WebDriver driver; // Экземпляр WebDriver для каждого теста
    private OrderStatus orderStatus; // Экземпляр страницы заказа

    // Параметры теста
    private final int indexButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String dateOrder;
    private final String period;
    private final String color;
    private final String comment;

    // Конструктор для параметризованного теста
    public OrderTest(int indexButton, String name, String surname, String address, String metro,
                     String phone, String dateOrder, String period, String color, String comment) {
        this.indexButton = indexButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Оформление заказа: " +
            "Способ вызова: {0}; " +
            "Имя: {1}; " +
            "Фамилия: {2}; " +
            "Адрес: {3}; " +
            "Метро: {4}; " +
            "Телефон: {5}; " +
            "Когда нужен: {6}; " +
            "Срок аренды: {7}; " +
            "Цвет: {8}; " +
            "Комментарий: {9}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {0, "Иван", "Иванов", "пр. Победы 1", "Выставочная", "+79225553555", "10.03.2023", "трое суток", "grey", "Проверка 1"},
                {1, "Петр", "Петров", "пр. Мира 2", "Беговая", "+79058111111", "20.03.2023", "сутки", "black", "Проверка 2"}
        };
    }

    @Before
    public void setUp() {
        driverRule = new DriverRule(); // Инициализация DriverRule
        driverRule.setUp(); // Инициализация драйвера
        driver = driverRule.getDriver(); // Получаем WebDriver для каждого теста
        orderStatus = new OrderStatus(driver); // Инициализация страницы заказа
    }

    @After
    public void tearDown() {
        driverRule.tearDown(); // Завершение работы драйвера после каждого теста
    }

    @Test
    public void orderTest() throws InterruptedException {
        // Выбор кнопки на основе параметра
        orderStatus.clickOrderButton(indexButton);

        // Заполнение данных
        orderStatus.inputName(name);
        orderStatus.inputSurname(surname);
        orderStatus.inputAddress(address);
        orderStatus.inputSubwayStation(metro);
        orderStatus.inputPhoneNumber(phone);
        orderStatus.clickNextButton();
        orderStatus.inputDate(dateOrder);
        orderStatus.chooseRentalPeriod(period);
        orderStatus.chooseColorCheckboxes(color);
        orderStatus.inputCommentForCourier(comment);
        orderStatus.clickBottomOrderButton();
        orderStatus.clickShowStatusButton();

    }
}
