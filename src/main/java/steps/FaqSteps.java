package steps;

import pages.MainPage;
import org.openqa.selenium.WebElement;


public class FaqSteps {
    private final MainPage mainPage;

    public FaqSteps(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public String getAnswer(String question) {
        WebElement questionAnswerBlock = mainPage.findAnswerByQuestion(question);
        return questionAnswerBlock.getText();
    }


}