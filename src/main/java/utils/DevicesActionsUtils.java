package utils;

import aquality.selenium.elements.actions.JsActions;
import aquality.selenium.elements.interfaces.IElement;

public class DevicesActionsUtils {
    public static void pointAndClick(IElement element) {
        JsActions mouseActions = new JsActions(element, "");
        mouseActions.click();
        element.click();
    }
}
