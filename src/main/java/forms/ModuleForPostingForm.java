package forms;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static utils.Constants.USER_ID_ATTRIBUTE;

public class ModuleForPostingForm extends Form {

    private final ILabel postingModule = getElementFactory()
            .getLabel(By.xpath("//div[contains(@id,'submit_post_box')]"), "Label with user id");

    public ModuleForPostingForm() {
        super(By.xpath("//div[contains(@class,'post_field_wrap')]"), "Module for posting form");
    }

    public String getUserId() {
        return postingModule.getAttribute(USER_ID_ATTRIBUTE);
    }
}
