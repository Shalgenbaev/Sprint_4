package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OrderStatus {

    // Верхняя кнопка Заказать
    private final By TOP_ORDER_BUTTON = By.xpath(".//div[starts-with(@class, 'Header')]/button[text()='Заказать']");
    // Нижняя кнопка заказа
    private final By BOTTOM_ORDER_BUTTON = By.xpath("//div[contains(@class, 'Home_FinishButton__1_cWm')]//button[text()='Заказать']");
    // Поле ввода имени
    private static final By NAME_FIELD = By.xpath(".//input[contains(@placeholder,'Имя')]");
    // Поле ввода фамилии
    private static final By SURNAME_FIELD = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    // Поле ввода адреса
    private final By ADDRESS_FIELD = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    // Поле выбора станции метро
    private final By SUBWAY_STATION_FIELD = By.xpath(".//input[contains(@placeholder,'метро')]");
    // Поле ввода телефона
    private final By PHONE_NUMBER_FIELD = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    // Кнопка далее
    private final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");
    // Кнопка перехода на предыдущий шаг заполнения данных при заказе
    private final By BACK_BUTTON = By.xpath(".//button[text()='Назад']");
    // Поле ввода даты
    private final By DATE_FIELD = By.xpath(".//input[contains(@placeholder,'Когда')]");
    // Поле для выбора периода аренды
    private final By RENTAL_PERIOD_FIELD = By.xpath(".//span[@class='Dropdown-arrow']");
    // Поле ввода комментария для курьера
    private final By COMMENT_FOR_COURIER_FIELD = By.xpath(".//input[contains(@placeholder,'Комментарий для курьера')]");

    private final By ORDER_BUTTON = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Заказать']");
    // Кнопка подтверждения заказа
    private final By ACCEPT_ORDER_BUTTON = By.xpath(".//button[text()='Да']");
    // Кнопка перехода к статусу заказа
    private final By SHOW_STATUS_BUTTON = By.xpath(".//button[text()='Посмотреть статус']");


    private WebDriver driver;

    // Конструктор, принимающий объект WebDriver
    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для клика по кнопке заказа
    public void clickOrderButton(int isTopButton) {
        if (isTopButton == 0) {
            driver.findElement(TOP_ORDER_BUTTON).click();
        } else if (isTopButton == 1) {
            WebElement bottomOrderButton = driver.findElement(BOTTOM_ORDER_BUTTON);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bottomOrderButton);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(bottomOrderButton));
            bottomOrderButton.click();
        }
    }


    // Метод ввода имени, принимающий имя как параметр
    public void inputName(String name) {
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    // Метод ввода фамилии, принимающий фамилию как параметр
    public void inputSurname(String surname) {
        driver.findElement(SURNAME_FIELD).sendKeys(surname);
    }

    // Метод ввода адреса, принимающий адрес как параметр
    public void inputAddress(String address) {
        driver.findElement(ADDRESS_FIELD).sendKeys(address);
    }

    // Метод выбора станции метро
    public void inputSubwayStation(String subwayStation) {
        driver.findElement(SUBWAY_STATION_FIELD).click();
        // API необходимо, если вы хотите кликнуть по конкретной станции
        // Например, выполнить поиск по названию метро и кликнуть по нему
        driver.findElement(By.xpath("//div[contains(text(), '" + subwayStation + "')]")).click();
    }

    // Метод ввода номера телефона, принимающий номер как параметр
    public void inputPhoneNumber(String phoneNumber) {
        driver.findElement(PHONE_NUMBER_FIELD).sendKeys(phoneNumber);
    }

    // Метод нажатия кнопки Далее
    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    // Метод ввода даты, принимающий дату как параметр
    public void inputDate(String date) {
        driver.findElement(DATE_FIELD).sendKeys(date);
    }

    // Метод выбора периода аренды, принимающий его как параметр
    public void chooseRentalPeriod(String rentalPeriod) {
        driver.findElement(RENTAL_PERIOD_FIELD).click();
        // Здесь подразумевается, что период аренды имеет уникальный текст для выбора
        driver.findElement(By.xpath("//div[contains(text(), '" + rentalPeriod + "')]")).click();
    }

    // Метод выбора чекбокса цвета, принимающий цвет как параметр
    public void chooseColorCheckboxes(String color) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By colorCheckbox = By.xpath("//input[@id='" + color + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(colorCheckbox));
        driver.findElement(colorCheckbox).click();
    }

    // Метод добавления комментария, принимающий комментарий как параметр
    public void inputCommentForCourier(String comment) {
        driver.findElement(COMMENT_FOR_COURIER_FIELD).sendKeys(comment);
    }

    // Метод оформления заказа
    public void clickBottomOrderButton() {
        driver.findElement(ORDER_BUTTON).click();
        driver.findElement(ACCEPT_ORDER_BUTTON).click();
    }

    // Метод просмотра статуса заказа
    public void clickShowStatusButton() {
        driver.findElement(SHOW_STATUS_BUTTON).click();
    }
}
