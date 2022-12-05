package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordForm extends Form {

    private final ITextBox passwordField = getElementFactory()
            .getTextBox(By.xpath("//input[@name='password']"), "Password field");
    private final IButton continueBtn = getElementFactory()
            .getButton(By.xpath("//span[contains(@class,'vkuiButton__in')]"), "Continue button");

    public PasswordForm() {
        super(By.xpath("//h3[contains(@class,'EnterPasswordNoUserInfo')]"), "Password form");
    }

    public void enterPasswordAndProceed() {
        passwordField.clearAndType((String) AqualityServices.get(ISettingsFile.class).getValue("/password"));
        continueBtn.clickAndWait();
    }
}
