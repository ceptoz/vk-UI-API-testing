package forms.pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import forms.CommentForm;
import forms.ImageViewForm;
import forms.PostForm;
import org.openqa.selenium.By;

public class ProfilePage extends Form {

    private final ILabel profileName = getElementFactory()
            .getLabel(By.xpath("//h2[contains(@class,'OwnerPageName')]"), "String with the name");

    public ProfilePage() {
        super(By.xpath("//div[contains(@class,'ProfileHeader__in')]"), "Profile page");
    }

    public PostForm getPostForm(String postLocatorIds) {
        return new PostForm(postLocatorIds);
    }

    public String getProfileNameText() {
        return profileName.getText();
    }

    public ImageViewForm getImageViewForm() {
        return new ImageViewForm();
    }

    public CommentForm getCommentForm(String commentLocatorIds) {return new CommentForm(commentLocatorIds);}
}
