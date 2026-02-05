package steps;
import Pages.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrdSteps {

    private WebDriver driver;
    public OrdSteps(WebDriver driver){
        this.driver = driver;
    }

    //ввод параметров в поля(поиск поля,активация и ввод данных для заказа)
    public void fillForWhoOrdForm(String name,String surname,String address, String subway,String phoneNumber) {
        OrderPage orderPage = new OrderPage(driver);
        // Заполняем поля формы, если форма прогрузилась
        driver.findElement(By.className("Order_Form__17u6u")).isDisplayed();
        orderPage.setName(name); // Имя
        orderPage.setSurname(surname); //фамилия
        orderPage.setAddress(address);// адрес
        orderPage.setSubway(subway);//название метро
        orderPage.setPhoneNumber(phoneNumber);//номер телефона
        // Добавьте задержку или проверку, если нужно
        // Thread.sleep(1000);

    }
    public void fillAboutOrderForm(String date,String rentalPeriod,String color,String comment){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.setDate(date);//дата
        orderPage.setRentalPeriod(rentalPeriod);//срок аренды
        orderPage.setColor(color);//цвет самоката
        orderPage.setComment(comment);//комментарий
    }

}