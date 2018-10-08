package utils;

import action.Action;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.ArrayList;
import java.util.List;

public class TestCase {

    private String name;
    private List<Action> actionList;

    public TestCase(String testName) {
        this.name = testName;
        this.actionList = new ArrayList<>();
    }

    public void addAction(XSSFRow actionRow) {
        actionList.add(new Action(
                actionRow.getCell(1),
                actionRow.getCell(2),
                actionRow.getCell(3),
                actionRow.getCell(4)
        ));
    }

    public void run() {
        for (Action action : actionList) action.perform();
    }
}
