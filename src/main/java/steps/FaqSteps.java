package steps;

import Pages.MainPage;
import org.openqa.selenium.WebElement;


public class FaqSteps {
    private final MainPage mainPage;

    public FaqSteps(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public String getAnswer(WebElement questionAnswerBlock) {
        return questionAnswerBlock.getText();
    }


}