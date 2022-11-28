package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CommentForm extends Form {
    public final String commentLocator = "//div[@id='post%s']";
    private final String commentAuthorLocator = String
            .format("%s//following::a[contains(@class,'author')]", commentLocator);
    private final String commentTextLocator = String
            .format("%s//following::div[contains(@class,'wall_reply_text')]", commentLocator);
    private final String showNextCommentsBtnLocator = String
            .format("%s//following::span[contains(@class,'js-replies_next_label')]", commentLocator);

    public CommentForm(String commentLocatorIds) {
        super(By.xpath(String.format("//div[@data-post-id='%s']", commentLocatorIds)), "Commentary");
    }

    public String getCommentAuthor(String commentLocatorIds) {
        return getElementFactory().getLink(By.xpath(String.format(commentAuthorLocator, commentLocatorIds)), "Entry author").getText();
    }

    public String getCommentText(String commentLocatorIds) {
        return getElementFactory().getLabel(By.xpath(String.format(commentTextLocator, commentLocatorIds)), "Comment text").getText();
    }

    public IButton getShowNextCommentsBtn(String commentLocatorIds) {
        return getElementFactory().getButton(By.xpath(String.format(showNextCommentsBtnLocator, commentLocatorIds)), "Button to reveal hidden comments");
    }

    public void clickShowNextCommentsBtn(String commentLocatorIds) {
        getShowNextCommentsBtn(commentLocatorIds).clickAndWait();
    }
}
