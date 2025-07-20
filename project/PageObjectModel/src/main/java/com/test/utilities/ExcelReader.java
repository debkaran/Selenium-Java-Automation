package com.test.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static final String FILE_NAME = "src/test/resources/excel/testdata.xlsx";

	public static void main(String[] args) {

		System.out.println(readExcel(FILE_NAME, "AddCustomerTest"));
	}

	/**
	 * Reads the content of an Excel sheet and returns it as a list of rows, where
	 * each row is a list of string values.
	 *
	 * @param fileName  the full path to the Excel file (should be in .xlsx format)
	 * @param sheetName the name of the sheet to read data from
	 * @return a List<List<String>> where each inner list represents a row of cell
	 *         values from the sheet
	 * @throws RuntimeException if the file is not found or cannot be read (wrapped
	 *                          from IOException)
	 */
	public static List<List<String>> readExcel(String fileName, String sheetName) {
		List<List<String>> data = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(fileName); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);

			for (Row row : sheet) {
				List<String> rowData = new ArrayList<>();
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case STRING:
						rowData.add(cell.getStringCellValue());
						break;
					case NUMERIC:
						rowData.add(String.valueOf(cell.getNumericCellValue()));
						break;
					case BOOLEAN:
						rowData.add(String.valueOf(cell.getBooleanCellValue()));
						break;
					case FORMULA:
						rowData.add(cell.getCellFormula());
						break;
					default:
						rowData.add("");
						break;
					}
				}
				data.add(rowData);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
