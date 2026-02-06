package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    public OrderPage(WebDriver driver){

        this.driver = driver;
    }
    //Кнопка заказать верхняя
    private By upOrdBtn = By.xpath(".//button[@class='Button_Button__ra12g']");
    private By dwnOrdBtn = By.xpath(".//button[contains(@class,'Button_Button__ra12g')]");
    // Имя
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    // Фамилия
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Адрес: куда привезти заказ
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Селектор со списком станций метро
    private By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    // Телефон: на него позвонит курьер
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private By orderNextButton = By.xpath(".//button[text()='Далее']");

    // Когда привезти самокат
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Дата календаря
    private By dateCal = By.xpath("//div[contains(@class,'react-datepicker__day--selected')]");
    // Поле Срок аренды
    private By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder']");
    // Комментарий для курьера
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка Заказать
    private By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons__1xGrp')]/button[text()='Заказать']");

    // Кнопка подтверждения заказа
    private By orderConfirmButton = By.xpath(".//button[text()='Да']");

    //Кнопка Посмотреть статус
    private By checkStatus = By.xpath("//button[text()='Посмотреть статус']");

    public WebElement orderForm() {

        return driver.findElement(By.className("Order_Form__17u6u"));
    }
    
    public void waitOrderFormLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(orderForm()));
    }

    //Нажимаем верхнюю кнопку заказать если её видно
    public void clickUpOrdBtn(){
        if (driver.findElement(upOrdBtn).isDisplayed()){
            driver.findElement(upOrdBtn).click();
        }
    }
    //Нажимаем скроллим до нижней кнопки и жмем заказать
    public void clickDwnOrdBtn(){
        WebElement parent = driver.findElement(By.className("Home_RoadMap__2tal_"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", parent);

        WebElement button = parent.findElement(dwnOrdBtn);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dwnOrdBtn));
        button.click();
    }


    // Метод для заполнения поля * Имя
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    // Метод для заполнения поля * Фамилия
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    // Метод для заполнения поля * Адрес: куда привезти заказ
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    // Метод для заполнения поля * Станция метро
    public void setSubway(String subway) {
        driver.findElement(subwayField).click();
        driver.findElement(By.xpath(".//div[text()='"+subway+"']")).click();
    }
    // Метод для заполнения поля * Телефон: на него позвонит курьер
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    // Метод для перехода ко второй странице создания заказа
    public void clickOrderNextButton() {
        driver.findElement(orderNextButton).click();
    }
    // Метод для заполнения поля * Когда привезти самокат
    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateCal).click();
    }
    // Метод для заполнения поля Срок аренды
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[text()='"+rentalPeriod+"']")).click();
    }
    // Метод для заполнения поля Цвет самоката
    public void setColor(String color) {
        driver.findElement(By.xpath(".//label[text()='"+color+"']")).click();
    }
    // Метод для заполнения поля Комментарий для курьера
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    // Метод для перехода к подтверждению заказа
    public void clickOrderCreateButton() {
        driver.findElement(orderCreateButton).click();
    }
    // Метод для подтверждения заказа
    public void clickOrderConfirmButton() {
        driver.findElement(orderConfirmButton).click();
    }
    //Метод для нахождения кнопки Посмотреть статус
    public String trackOrderInfoMsg(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(checkStatus));
        return driver.findElement(checkStatus).getText();
    }

}
