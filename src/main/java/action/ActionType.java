package action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selector.SelectorType;

enum ActionType {
    GOTOURL((driver, element, url) -> driver.get(url)),
    CLICK((driver, element, value) -> element.click()),
    SETTEXT((driver, element, value) -> element.sendKeys(value)),
    ASSERT((driver, element, value) -> element.isDisplayed()),
    INVALID;

    PerformAction performAction;

    ActionType(PerformAction performAction) {
        this.performAction = performAction;
    }

    ActionType() {
        this.performAction = null;
    }

    public static ActionType fromKeyword(String keyword) throws ActionNotSupported {
        for (ActionType type : values()) {
            if (keyword.toUpperCase().equals(type.name())) {
                return type;
            }
        }

        throw new ActionNotSupported("Not supported keyword action: " + keyword);
    }

    public void act(WebDriver driver, Action action) {
        WebElement element = SelectorType.getWebElementFromSelector(driver, action.by, action.selector);
        performAction.act(driver, element, action.value);
    }
}