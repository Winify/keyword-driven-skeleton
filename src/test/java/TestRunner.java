import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.TestCase;
import utils.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestRunner extends TestUtils {

    @BeforeMethod
    public void setUp() {
        initializeDriver();
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }

    @Test(dataProvider = "readTestCaseFile")
    public void printTestCases(TestCase test) {
        test.run();
    }

    @DataProvider(name = "readTestCaseFile")
    public Object[][] readTestCaseFile() {
        final File testFile = loadTestFile("login_to_probono.xlsx");

        List<TestCase> testCaseList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(testFile)) {
            XSSFWorkbook testWorkbook = new XSSFWorkbook(fis);

            int testCaseNumber = testWorkbook.getNumberOfSheets();

            for (int i = 0; i < testCaseNumber; i++) {
                XSSFSheet testCaseSheet = testWorkbook.getSheetAt(i);

                String testName = testCaseSheet.getRow(0).getCell(0).getStringCellValue();
                TestCase testCase = new TestCase(testName);

                int actionNumber = testCaseSheet.getPhysicalNumberOfRows();

                for (int j = 1; j < actionNumber; j++) {
                    XSSFRow actionRow = testCaseSheet.getRow(j);

                    testCase.addAction(actionRow);
                }

                testCaseList.add(testCase);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertTestCaseListToMatrix(testCaseList);
    }

    private File loadTestFile(String fileName) {
        return new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
    }

    private Object[][] convertTestCaseListToMatrix(List list) {
        Object[][] data = new Object[list.size()][1];

        for (int i = 0; i < list.size(); i++)
            data[i][0] = list.get(i);

        return data;
    }

}
