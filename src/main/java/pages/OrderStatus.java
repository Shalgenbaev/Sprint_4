package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderStatus {

    // Поля для локаторов
    private static final By NAME_FIELD = By.xpath(".//input[contains(@placeholder,'Имя')]");
    private static final By SURNAME_FIELD = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    private static final By ADDRESS_FIELD = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    private static final By SUBWAY_STATION_FIELD = By.xpath(".//input[contains(@placeholder,'метро')]");
    private static final By PHONE_NUMBER_FIELD = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    private static final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");
    private static final By BACK_BUTTON = By.xpath(".//button[text()='Назад']");
    private static final By DATE_FIELD = By.xpath(".//input[contains(@placeholder,'Когда')]");
    private static final By RENTAL_PERIOD_FIELD = By.xpath(".//span[@class='Dropdown-arrow']");
    private static final By COMMENT_FOR_COURIER_FIELD = By.xpath(".//input[contains(@placeholder,'Комментарий для курьера')]");
    private static final By ORDER_BUTTON = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Заказать']");
    private static final By ACCEPT_ORDER_BUTTON = By.xpath(".//button[text()='Да']");
    private static final By SHOW_STATUS_BUTTON = By.xpath(".//button[text()='Посмотреть статус']");

    // Локатор для выбора станции метро по имени
    private static final String SUBWAY_STATION_OPTION_XPATH = "//div[contains(text(), '%s')]"; // формат для выбора станции

    // Локатор для выбора периода аренды
    private static final String RENTAL_PERIOD_OPTION_XPATH = "//div[contains(text(), '%s')]"; // формат для выбора периода аренды

    // Локатор для выбранного цвета (например, для чекбоксов)
    private static final String COLOR_CHECKBOX_XPATH = "//input[@id='%s']"; // формат для чекбоксов

    private WebDriver driver; // WebDriver для взаимодействия с браузером

    // Конструктор, принимающий объект WebDriver
    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для клика по кнопке заказа
    public void clickOrderButton(int isTopButton) {
        if (isTopButton == 0) {
            driver.findElement(MainPage.TOP_ORDER_BUTTON).click();
        } else if (isTopButton == 1) {
            WebElement bottomOrderButton = driver.findElement(MainPage.BOTTOM_ORDER_BUTTON);
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
        // Клик по конкретной станции метро
        driver.findElement(By.xpath(String.format(SUBWAY_STATION_OPTION_XPATH, subwayStation))).click();
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
        driver.findElement(By.xpath(String.format(RENTAL_PERIOD_OPTION_XPATH, rentalPeriod))).click();
    }

    // Метод выбора чекбокса цвета, принимающий цвет как параметр
    public void chooseColorCheckboxes(String color) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By colorCheckbox = By.xpath(String.format(COLOR_CHECKBOX_XPATH, color)); // Используем форматированный локатор
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
