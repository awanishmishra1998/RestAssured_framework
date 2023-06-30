package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public Sheet readExcel(String filePath, String fileName, String sheetName) throws IOException {

        File file = new File(filePath + "\\" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook testWorkbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if (fileExtensionName.equals(".xlsx")) {
            testWorkbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            testWorkbook = new HSSFWorkbook(inputStream);
        }

        Sheet testSheet = testWorkbook.getSheet(sheetName);
        return testSheet;

    }
}
