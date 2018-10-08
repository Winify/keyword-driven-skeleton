package selector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public enum SelectorType {
    ID((driver, selector) -> element(driver, By.id(selector))),
    CSS((driver, selector) -> element(driver, By.cssSelector(selector))),
    INVALID((driver, selector) -> null);

    ElementFinder elementFinder;

    SelectorType(ElementFinder elementFinder) {
        this.elementFinder = elementFinder;
    }

    public static WebElement getWebElementFromSelector(WebDriver driver, String by, String selector) {
        return SelectorType.fromBy(by).getElement(driver, selector);
    }

    private WebElement getElement(WebDriver driver, String selector) {
        return elementFinder.getElement(driver, selector);
    }

    private static SelectorType fromBy(String by) {
        for (SelectorType type : values()) {
            if (by.toUpperCase().equals(type.name())) {
                return type;
            }
        }

        return INVALID;
    }

    private static WebElement element(WebDriver driver, By by) {
        return driver.findElements(by).stream().filter(WebElement::isDisplayed).findFirst().orElse(null);
    }
}
