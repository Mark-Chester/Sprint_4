package steps;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.OrderPage;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class OrdSteps {

    private WebDriver driver;
    public OrdSteps(WebDriver driver){
        this.driver = driver;
    }

    //ввод параметров в поля(поиск поля,активация и ввод данных для заказа)
    public void fillForWhoOrdForm(String name,String surname,String address, String subway,String phoneNumber) {
        OrderPage orderPage = new OrderPage(driver);
        // Заполняем поля формы, если форма прогрузилась
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(orderPage.orderForm()));
        orderPage.setName(name); // Имя
        orderPage.setSurname(surname); //фамилия
        orderPage.setAddress(address);// адрес
        orderPage.setSubway(subway);//название метро
        orderPage.setPhoneNumber(phoneNumber);//номер телефона
    }

    public void fillAboutOrderForm(String date,String rentalPeriod,String color,String comment){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.setDate(date);//дата
        orderPage.setRentalPeriod(rentalPeriod);//срок аренды
        orderPage.setColor(color);//цвет самоката
        orderPage.setComment(comment);//комментарий
    }

}