package utils;

import aquality.selenium.core.logging.Logger;
import io.restassured.response.Response;

import java.util.Map;

import static utils.Constants.PATH_TO_VK_METHODS;
import static utils.JSONJacksonProcessorUtils.getValueFromJSONMap;
import static utils.ReqResUtils.*;

public class ActionsVKUtils {

    public static Response postTextOnAWall(String message) {
        Logger.getInstance().info("Creating a post with text on the wall");
        Map<String, String> parameters = getMapWithBasicParams();
        parameters.put("message", message);
        return getResponseAfterPost(getValueFromJSONMap(PATH_TO_VK_METHODS, "post"), parameters);
    }

    public static Response getUploadServer() {
        Logger.getInstance().info("Getting URL of the server to upload");
        Map<String, String> parameters = getMapWithBasicParams();
        return getResponseAfterGet(getValueFromJSONMap(PATH_TO_VK_METHODS, "getUploadingServer"), parameters);
    }

    public static Response saveUploadedPhoto(Integer server, String photo, String hash) {
        Logger.getInstance().info("Saving the photo after uploading");
        Map<String, String> parameters = getMapWithBasicParams();
        parameters.put("server", Integer.toString(server));
        parameters.put("photo", photo);
        parameters.put("hash", hash);
        return getResponseAfterPost(getValueFromJSONMap(PATH_TO_VK_METHODS, "saveUploadedPhoto"), parameters);
    }

    public static void editPostWithMedia(String owner_id, Integer post_id, String message, String type, Integer mediaId) {
        Logger.getInstance().info("Editing a post with media");
        Map<String, String> parameters = getMapWithBasicParams();
        parameters.put("owner_id", owner_id);
        parameters.put("post_id", Integer.toString(post_id));
        parameters.put("message", message);
        parameters.put("attachments", String.format("%s%s_%d", type, owner_id, mediaId));
        getResponseAfterPost(getValueFromJSONMap(PATH_TO_VK_METHODS, "editPost"), parameters);
    }

    public static Response writeCommentToPost(String owner_id, Integer post_id, String message) {
        Logger.getInstance().info("Writing commentary for the post");
        Map<String, String> parameters = getMapWithBasicParams();
        parameters.put("owner_id", owner_id);
        parameters.put("post_id", Integer.toString(post_id));
        parameters.put("message", message);
        return getResponseAfterPost(getValueFromJSONMap(PATH_TO_VK_METHODS, "writeComment"), parameters);
    }

    public static Response getLikers(String contentType, String owner_id, Integer item_id) {
        Logger.getInstance().info("Getting list of users, who liked the post");
        Map<String, String> parameters = getMapWithBasicParams();
        parameters.put("owner_id", owner_id);
        parameters.put("item_id", Integer.toString(item_id));
        parameters.put("type", contentType);
        return getResponseAfterPost(getValueFromJSONMap(PATH_TO_VK_METHODS, "getListOfUsersLikedPost"), parameters);
    }

    public static void deletePost(String owner_id, Integer post_id) {
        Logger.getInstance().info("Deleting the post");
        Map<String, String> parameters = getMapWithBasicParams();
        parameters.put("owner_id", owner_id);
        parameters.put("post_id", Integer.toString(post_id));
        getResponseAfterPost(getValueFromJSONMap(PATH_TO_VK_METHODS, "deletePost"), parameters);
    }
}
