package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private String URL = "https://qa-scooter.praktikum-services.ru";


    //открыть страницу
    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public void openPage(){
        driver.get(URL);
        // Убираем сообщение о куках
        if (driver.findElement(By.className("App_CookieConsent__1yUIN")).isDisplayed()){
            driver.findElement(By.className("App_CookieButton__3cvqF")).click();
        }
    }
    public WebElement findAnswerByQuestion(String question) {
        try {
            // Скроллим до блока FAQ, чтобы он был виден
            WebElement faqBlock = driver.findElement(By.className("Home_FAQ__3uVm4"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", faqBlock);

            // Ждем немного, чтобы элемент полностью отобразился
            Thread.sleep(500);

            // Находим вопрос в аккордеоне
            WebElement questionElement = driver.findElement(By.xpath("//div[contains(@class, 'accordion__button') and contains(text(), '" + question + "')]"));

            // находим родительский элемент вопроса
            WebElement parentElement = questionElement.findElement(By.xpath("./parent::div"));

            // Ждем, пока элемент станет кликабельным
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(questionElement));

            // Кликаем по вопросу, чтобы он открылся
            questionElement.click();
            // Ждем пока элемент с ответом отобразится
            Thread.sleep(500);

            // Находим соответствующий ответ (следующий sibling)
            WebElement answerElement = parentElement.findElement(By.xpath("./following-sibling::div[contains(@class, 'accordion__panel')]"));

            return answerElement;
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Вопрос '" + question + "' не найден или не удалось найти ответ", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Ошибка при ожидании", e);
        }
    }


}
