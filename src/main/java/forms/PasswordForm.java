package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.JSONJacksonProcessorUtils;

import static utils.Constants.PATH_TO_CREDENTIALS;

public class PasswordForm extends Form {

    private final ITextBox passwordField = getElementFactory()
            .getTextBox(By.xpath("//input[@name='password']"), "Password field");
    private final IButton continueBtn = getElementFactory()
            .getButton(By.xpath("//span[contains(@class,'vkuiButton__in')]"), "Continue button");

    public PasswordForm() {
        super(By.xpath("//h3[contains(@class,'EnterPasswordNoUserInfo')]"), "Password form");
    }

    public void enterPasswordAndProceed() {
        passwordField.clearAndType(JSONJacksonProcessorUtils.getValueFromJSONMap(PATH_TO_CREDENTIALS, "password"));
        continueBtn.clickAndWait();
    }
}
