import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelWriter {

    public static Object[] toPutIntoExcel = Functions.assignTabling();

    public static void main(String[] args) throws IOException, InvalidFormatException {

        List<String> contentNames = (List<String>) toPutIntoExcel[0];
        Map<String, String> round1 = (Map<String, String>) toPutIntoExcel[1];
        Map<String, String> round2 = (Map<String, String>) toPutIntoExcel[2];
        Map<String, String> round3 = (Map<String, String>) toPutIntoExcel[3];

        // Create a Workbook
        Workbook workbook = new HSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("TableSeating");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);


        // Create cells
        Cell cell = headerRow.createCell(0);
        cell.setCellValue("Name");
        cell.setCellStyle(headerCellStyle);

        for (int i = 1; i <= 3; i++) {
            cell = headerRow.createCell(i);
            cell.setCellValue("Round " + Integer.toString(i));
            cell.setCellStyle(headerCellStyle);
        }


        // Create Other rows and cells with employees data
        int rowNum = 1;
        for (String name : round1.keySet()) {

            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(name);

            row.createCell(1)
                    .setCellValue(round1.get(name));

            row.createCell(2)
                    .setCellValue(round2.get(name));

            row.createCell(3)
                    .setCellValue(round3.get(name));
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xls");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
}