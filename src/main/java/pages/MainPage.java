package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private String URL = "https://qa-scooter.praktikum-services.ru";

    // Локаторы
    private By faqBlock = By.className("Home_FAQ__3uVm4");

    private By appCookieMsg = By.className("App_CookieConsent__1yUIN");

    private By appCookieBtn = By.className("App_CookieButton__3cvqF");

    private By page= By.className("Home_FirstPart__3g6vG");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public WebElement homePage() {
        return driver.findElement(page);
    }

    public void openPage(){
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(homePage()));
        // Убираем сообщение о куках
        try {
            WebElement cookieElement = driver.findElement(appCookieMsg);
            if (cookieElement.isDisplayed()){
                driver.findElement(appCookieBtn).click();
            }
        } catch (NoSuchElementException e) {
            // Если сообщение о куках не найдено, продолжаем
        }
    }



    //Поиск блока с вопросом по вопросу
    public WebElement findQuestionElement(String question) {
        By questionXpath = By.xpath("//div[contains(@class, 'accordion__button') and contains(text(), '" + question + "')]");
        return driver.findElement(questionXpath);
    }
    //Раскрытие блока с вопросом
    public void clickQuestion(String question) {
        WebElement questionElement = findQuestionElement(question);
        questionElement.click();
    }
    //получение элемента с ответом на вопрос
    public WebElement answerElement(String question) {
        return driver.findElement(By.xpath("//div[contains(@class,'accordion__button') and contains(text(),'" + question + "')]/parent::*//following-sibling::div[contains(@class,'accordion__panel')]"));
    }

    public WebElement findAnswerByQuestion(String question) {
        try {
            // Скроллим до блока FAQ
            WebElement faqBlockElement = driver.findElement(faqBlock);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", faqBlockElement);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOf(faqBlockElement));

            // Находим вопрос
            WebElement questionElement = findQuestionElement(question);
            wait.until(ExpectedConditions.visibilityOf(questionElement));

            // Кликаем по вопросу
            clickQuestion(question);

            // Ждем и возвращаем ответ
            wait.until(ExpectedConditions.visibilityOf(answerElement(question)));
            return answerElement(question);

        } catch (NoSuchElementException e) {
            throw new RuntimeException("Вопрос '" + question + "' не найден или не удалось найти ответ", e);
        }
    }
}
