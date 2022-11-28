package forms.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static utils.Constants.PATH_TO_CREDENTIALS;
import static utils.JSONJacksonProcessorUtils.getValueFromJSONMap;

public class WelcomePage extends Form {
    private final ITextBox loginField = getElementFactory()
            .getTextBox(By.xpath("//input[@name='login']"), "Login field");
    private final IButton enterBtn = getElementFactory()
            .getButton(By.xpath("//button[contains(@class,'signInButton')]"), "Sign in button");

    public WelcomePage() {
        super(By.xpath("//div[@id='index_login']"), "Welcome page");
    }

    public void enterLoginAndSignIn() {
        loginField.clearAndType(getValueFromJSONMap(PATH_TO_CREDENTIALS, "login"));
        enterBtn.clickAndWait();
    }
}
