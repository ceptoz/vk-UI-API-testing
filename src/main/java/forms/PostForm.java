package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PostForm extends Form {
    public final String postLocator = "//div[@id='post%s']";
    private final String postAuthorLocator = String
            .format("%s//following::a[contains(@class,'author')]", postLocator);
    private final String postTextLocator = String
            .format("%s//following::div[contains(@class,'wall_post_text')]", postLocator);
    private final String postImageLocator = String
            .format("%s//following::a[contains(@class,'image_cover')]", postLocator);
    private final String likeBtnLocator = String
        .format("%s//child::span[contains(@class,'like_button_icon')]", postLocator);

    public PostForm(String postLocatorIds) {
        super(By.xpath(String.format("//div[@data-post-id='%s']", postLocatorIds)), "Commentary");
    }

    public String getPostAuthor(String postLocatorIds) {
        return getElementFactory().getLink(By.xpath(String.format(postAuthorLocator, postLocatorIds)), "Entry author").getText();
    }

    public String getPostText(String postLocatorIds) {
        return getElementFactory().getLabel(By.xpath(String.format(postTextLocator, postLocatorIds)), "Post text").getText();
    }

    public void clickImage(String entryLocatorIds) {
        getElementFactory().getLink(By.xpath(String.format(postImageLocator, entryLocatorIds)), "Posted image").clickAndWait();
    }

    public void clickLikeBtn(String entryLocatorIds) {
        getElementFactory().getLink(By.xpath(String.format(likeBtnLocator, entryLocatorIds)), "Like button").click();
    }
}