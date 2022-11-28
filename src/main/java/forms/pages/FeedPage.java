package forms.pages;

import aquality.selenium.forms.Form;
import forms.LeftSideMenuForm;
import forms.ModuleForPostingForm;
import org.openqa.selenium.By;

public class FeedPage extends Form {

    public FeedPage() {
        super(By.xpath("//div[@id='feed_rmenu']"), "Feed page");
    }

    public LeftSideMenuForm getLeftSideMenuForm() {
        return new LeftSideMenuForm();
    }

    public ModuleForPostingForm getModuleForPostingForm() {
        return new ModuleForPostingForm();
    }
}
