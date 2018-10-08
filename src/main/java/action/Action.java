package action;

import org.apache.poi.xssf.usermodel.XSSFCell;
import utils.TestUtils;

public class Action extends TestUtils {

    private String keyword;

    String by;
    String selector;
    String value;

    public Action(XSSFCell keyword, XSSFCell by, XSSFCell selector, XSSFCell value) {
        this.keyword = String.valueOf(keyword);
        this.by = String.valueOf(by);
        this.selector = String.valueOf(selector);
        this.value = String.valueOf(value).replace(".0", "");
    }

    public void perform() {
        try {
            ActionType.fromKeyword(keyword).act(driver, this);
        } catch (ActionNotSupported exception) {
            exception.printStackTrace();
        }
    }
}
