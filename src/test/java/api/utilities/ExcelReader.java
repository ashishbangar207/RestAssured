package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;


public class ExcelReader {

    @DataProvider(name = "testdata")
    public Object[][] getTestData() throws EncryptedDocumentException, IOException {
        return getData("TestData"); // Use correct sheet name
    }

    public Object[][] getData(String sheetName) throws EncryptedDocumentException, IOException {
        File file = new File("C:\\Users\\ashish bangar\\eclipse-workspace\\Selenium Automation Framework\\TestRestAssured\\TestData\\LoginTestData.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getLastRowNum();  // Last row index (zero-based)
        int totalCols = sheet.getRow(0).getLastCellNum(); // Total columns

        DataFormatter formatter = new DataFormatter();
        Object[][] testData = new Object[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {  // Skipping header (start from row 1)
            for (int j = 0; j < totalCols; j++) {
                testData[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                System.out.print(testData[i - 1][j] + "  "); // Printing Data
            }
            System.out.println("");
        }
        workbook.close();
        return testData;
    }

    public static void main(String[] args) throws EncryptedDocumentException, IOException {
        ExcelReader reader = new ExcelReader();
        reader.getData("TestData"); // Test Data Reading
    }

}
