package action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@FunctionalInterface
public interface PerformAction {
    void act(WebDriver driver, WebElement element, String value);
}