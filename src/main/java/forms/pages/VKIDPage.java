package forms.pages;

import aquality.selenium.forms.Form;
import forms.PasswordForm;
import org.openqa.selenium.By;

public class VKIDPage extends Form {
    public VKIDPage() {
        super(By.xpath("//div[contains(@class,'AuthRoot__contentIn')]"), "VK ID page");
    }

    public PasswordForm getPasswordForm() {
        return new PasswordForm();
    }
}
