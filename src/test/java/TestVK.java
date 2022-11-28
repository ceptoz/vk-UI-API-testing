import com.github.romankh3.image.comparison.model.ImageComparisonState;
import forms.pages.FeedPage;
import forms.pages.ProfilePage;
import forms.pages.VKIDPage;
import forms.pages.WelcomePage;
import io.restassured.response.Response;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static utils.ActionsVKUtils.*;
import static utils.Constants.*;
import static utils.ImageProcessingUtils.areImagesEqual;
import static utils.ImageProcessingUtils.downloadVIAImageIO;
import static utils.RandomProviderUtils.createRandomString;
import static utils.ReqResUtils.*;

public class TestVK extends BaseTest{
    @Test
    public void performTestVK() {
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "Welcome page is not open");

        welcomePage.enterLoginAndSignIn();
        VKIDPage vkidPage = new VKIDPage();
        Assert.assertTrue(vkidPage.state().waitForDisplayed(), "Auth page is not open");

        vkidPage.getPasswordForm().enterPasswordAndProceed();
        FeedPage feedPage = new FeedPage();
        Assert.assertTrue(feedPage.state().waitForDisplayed(), "VK feed page is not open");

        String userId = feedPage.getModuleForPostingForm().getUserId();
        feedPage.getLeftSideMenuForm().clickProfileBtn();
        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.state().waitForDisplayed(), "Profile page is not open");

        String firstMessageForPost = createRandomString();
        Response actualResponse = postTextOnAWall(firstMessageForPost);
        Integer postId = getInnerObjectFromJSONResponse(actualResponse, WRAPPER, WallPostResModel.class).getPost_id();
        String postLocatorIds = String.format("%s_%d", userId, postId);

        Assert.assertTrue(profilePage.getPostForm(postLocatorIds).state().waitForDisplayed(), "Post is not displayed");
        Assert.assertEquals(
                profilePage.getPostForm(postLocatorIds).getPostAuthor(postLocatorIds),
                profilePage.getProfileNameText(),
                "Post author is not equal to the page owner");
        Assert.assertEquals(
                profilePage.getPostForm(postLocatorIds).getPostText(postLocatorIds),
                firstMessageForPost, "Post text is not equal to the created value");

        actualResponse = getUploadServer();
        String uploadServerURL = getInnerObjectFromJSONResponse(actualResponse, WRAPPER, ServerForUploadingResModel.class).getUpload_url();

        actualResponse = sendMultipartFormat(uploadServerURL, "photo", PATH_TO_PICTURE);
        PhotoUploadedResModel photoUploadedRes = getObjectFromStringJSON(extractDataFromHTMLResponse(actualResponse), PhotoUploadedResModel.class);

        actualResponse = saveUploadedPhoto(photoUploadedRes.getServer(), photoUploadedRes.getPhoto(), photoUploadedRes.getHash());
        Integer photoId = getListOfInnerJSONObjects(actualResponse, WRAPPER, PhotoSavedRModel.class).get(0).getId();

        String secondMessageForPost = createRandomString();
        editPostWithMedia(userId, postId, secondMessageForPost, "photo", photoId);

        Assert.assertNotEquals(
                profilePage.getPostForm(postLocatorIds).getPostText(postLocatorIds),
                firstMessageForPost, "Text of the post is equal to the created value");

        profilePage.getPostForm(postLocatorIds).clickImage(postLocatorIds);
        downloadVIAImageIO(profilePage.getImageViewForm().getImageSrc(), PATH_FOR_DOWNLOADED_PIC);
        profilePage.getImageViewForm().clickCloseBtn();
        Assert.assertEquals(ImageComparisonState.MATCH, areImagesEqual(PATH_TO_PICTURE, PATH_FOR_DOWNLOADED_PIC), "There is a big difference between two uploaded and appeared photos");

        String commentMessage = createRandomString();
        actualResponse = writeCommentToPost(userId, postId, commentMessage);
        Integer commentId = getInnerObjectFromJSONResponse(actualResponse, WRAPPER, CommentResModel.class).getComment_id();

        if(profilePage.getCommentForm(postLocatorIds).getShowNextCommentsBtn(postLocatorIds).state().isExist()) {
            profilePage.getCommentForm(postLocatorIds).clickShowNextCommentsBtn(postLocatorIds);
        }

        String commentLocatorIds = String.format("%s_%d", userId, commentId);
        Assert.assertTrue(profilePage.getCommentForm(commentLocatorIds).state().waitForDisplayed(), "Comment is not displayed");
        Assert.assertEquals(
                profilePage.getCommentForm(commentLocatorIds).getCommentAuthor(commentLocatorIds),
                profilePage.getProfileNameText(),
                "Comment author is not equal to the page owner");
        Assert.assertEquals(
                profilePage.getCommentForm(commentLocatorIds).getCommentText(commentLocatorIds),
                commentMessage, "Comment text is not equal to the created value");

        profilePage.getPostForm(postLocatorIds).clickLikeBtn(postLocatorIds);
        actualResponse = getLikers("post", userId, postId);

        List<Integer> listOfLikers = getInnerObjectFromJSONResponse(actualResponse, WRAPPER, WhoLikedResModel.class).getItems();
        Assert.assertTrue(listOfLikers.contains(Integer.parseInt(userId)), "User like was not found");

        deletePost(userId, postId);
        Assert.assertTrue(profilePage.getPostForm(postLocatorIds).state().waitForNotDisplayed(), "Post is not deleted");
    }
}
