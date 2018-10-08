package selector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@FunctionalInterface
interface ElementFinder {
    WebElement getElement(WebDriver d, String s);
}