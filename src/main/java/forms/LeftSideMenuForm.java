package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static utils.DevicesActionsUtils.pointAndClick;

public class LeftSideMenuForm extends Form {

    private final IButton profileBtn = getElementFactory()
            .getButton(By.xpath("//li[@id='l_pr']//a"), "'My page' button");

    public LeftSideMenuForm() {
        super(By.xpath("//div[@id='side_bar']"), "Side menu");
    }

    public void clickProfileBtn() {
        pointAndClick(profileBtn);
    }
}
