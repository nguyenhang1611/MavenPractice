package scripts.coreactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	public String getCellValue(String filePath, String fileName, String sheetName, int row, int col)
			throws IOException {
		try {
			// Create a object of File class to open xlsx file
			File file = new File(filePath + "\\" + fileName);

			// Create an object of FileInputStream class to read excel file
			FileInputStream inputStream = new FileInputStream(file);

			Workbook wbWorkbook = null;

			// Find the file extension by spliting file name in substring and
			// getting only extension name
			String fileExtensionName = fileName.substring(fileName.indexOf("."));

			if (fileExtensionName.equals(".xlsx")) {
				wbWorkbook = new XSSFWorkbook(inputStream);
			} else if (fileExtensionName.equals(".xls")) {
				wbWorkbook = new HSSFWorkbook(inputStream);
			}
			// Read sheet inside the workbook by its name
			Sheet sSheet = wbWorkbook.getSheet(sheetName);
			// Read cell inside the sheet by its row and column
			Cell cell = wbWorkbook.getSheet(sheetName).getRow(row).getCell(col);

			//Cell cell = sSheet.getRow(row).getCell(col);
			return cell.getStringCellValue();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * getRowCount getRowCount method
	 * 
	 * @author HangNT
	 * @since 2017/07/13
	 * @param filePath
	 * @param fileName
	 * @param Sheet
	 * @return
	 */
	public int getRowCount(String filePath, String fileName, String Sheet) {
		try {
			File file = new File(filePath + "\\" + fileName);
			FileInputStream inputStream = new FileInputStream(file);
			Workbook workbook = null;

			// Find the file extension by spliting file name in substring and
			// getting only extension name
			String fileExtensionName = fileName.substring(fileName.indexOf("."));

			// Check condition if the file is xlsx file
			if (fileExtensionName.equals(".xlsx")) {
				// If it is xlsx file then create object of XSSFWorkbook class
				workbook = new XSSFWorkbook(inputStream);
			}
			// Check condition if the file is xls file
			else if (fileExtensionName.equals(".xls")) {
				// If it is xls file then create object of XSSFWorkbook class
				workbook = new HSSFWorkbook(inputStream);
			}
			return workbook.getSheet(Sheet).getLastRowNum();
		} catch (Exception e) {
			return 0;
		}
	}

	public static String cellToString(Cell cell) {
		int type;
		Object result = null;
		type = cell.getCellType();

		switch (type) {
		case Cell.CELL_TYPE_NUMERIC: // numeric value in Excel
		case Cell.CELL_TYPE_FORMULA: // precomputed value based on formula
			result = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING: // String Value in Excel
			result = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			result = "";
		case Cell.CELL_TYPE_BOOLEAN: // boolean value
			result = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_ERROR:
		default:
			throw new RuntimeException("There is no support for this type of cell");
		}
		return result.toString();
	}
}
