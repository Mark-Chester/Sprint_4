import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrdSteps;
import java.util.ArrayList;
import java.util.List;



@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String browser;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;
    private OrdSteps ordSteps;

    public OrderTest(String browser, String firstName, String lastName, String address,
                     String metroStation, String phone, String date, String rentalPeriod,
                     String color, String comment) {
        super(browser);
        this.browser = browser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "{0}: Заказ на {1} {2}")
    public static Object[][] getDateSetForOrder() {
        List<String> browsers = List.of("chrome", "firefox");

        List<Object[]> result = new ArrayList<>();

        // Основной набор данных для заказа
        Object[][] baseData = {
                {"Иван", "Иванов", "г. Москва, ул. Пушкина, д.10", "Комсомольская", "89151234567", "01.01.2050", "сутки", "чёрный жемчуг", "Не звонить в дверь"},
                {"Ирина", "Авдеева", "проспект Маяковского 6", "Комсомольская", "+79657654321", "10.10.2030", "двое суток", "серая безысходность", "Привезите чистый самокат"},
        };

        for (String browser : browsers) {
            for (Object[] orderData : baseData) {
                // Создаем новый массив с добавлением браузера в начало
                Object[] testData = new Object[orderData.length + 1];
                testData[0] = browser;
                System.arraycopy(orderData, 0, testData, 1, orderData.length);
                result.add(testData);
            }
        }

        return result.toArray(new Object[0][]);
    }
    @Before
    public void setUp(){
        ordSteps = new OrdSteps(driver);
    }
    @Test
    public void positiveOrderTest(){
        // Открыли сайт
        mainPage.openPage();
        // клик кнопки заказать
        orderPage.clickOrdBtn();
        // ввод параметров в поля
        ordSteps.fillForWhoOrdForm(firstName,lastName,address,metroStation,phone);
        orderPage.clickOrderNextButton();//переходим на следующую страницу
        ordSteps.fillAboutOrderForm(date,rentalPeriod,color,comment);
        orderPage.clickOrderCreateButton();//создаем заказ
        orderPage.clickOrderConfirmButton();//подтверждаем заказ
        orderPage.TrackOrderInfoMsg();//Проверяем что появилось сообщение с кнопкой Посмотреть статус
    }

}
