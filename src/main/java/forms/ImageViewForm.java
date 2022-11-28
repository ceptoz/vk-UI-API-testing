package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ImageViewForm extends Form {
    private final ILink image = getElementFactory()
            .getLink(By.xpath("//div[@id='pv_photo']//child::img"), "Image");
    private final IButton closeBtn = getElementFactory()
            .getButton(By.xpath("//div[contains(@class,'pv_close_btn')]"), "Closing button");

    public ImageViewForm() {
        super(By.xpath("//div[contains(@class,'pv_photo_wrap')]"), "Photo show form");
    }

    public String getImageSrc() {
        return image.getAttribute("src");
    }

    public void clickCloseBtn() {
        closeBtn.click();
    }
}