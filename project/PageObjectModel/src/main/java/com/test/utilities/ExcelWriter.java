package com.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	private static final String FILE_NAME = "src/test/resources/com/test/excel/testdata.xlsx";
//	private static final String SHEET_NAME = "LoginTest";
	private static final String SHEET_NAME = "CreateAccountTest";

	public static void main(String[] args) {

//		Object[][] arrayData = { { "debkaransinghania@gmail.com", "pas$M0rd@9339" } };
//		String[] headers = { "username", "password" };

		Object[][] arrayData = { { "Raman" } };
		String[] headers = { "accountname" };

		try {
			writeArrayToExcel(FILE_NAME, arrayData, headers, SHEET_NAME);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Writes a 2D array to Excel file using WorkbookFactory
	 * 
	 * @param filePath  The path where the Excel file will be created
	 * @param data      2D array of data
	 * @param headers   Array of column headers
	 * @param sheetName Name of the sheet
	 * @throws IOException if file cannot be written
	 */
	public static void writeArrayToExcel(String filePath, Object[][] data, String[] headers, String sheetName)
			throws IOException {

		Workbook workbook;
		if (!new File(filePath).exists()) {
			workbook = WorkbookFactory.create(isXlsxFormat(filePath));
		} else {
			FileInputStream fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		}
		try {
			Sheet sheet;
			if (workbook.getSheet(sheetName) == null) {
				sheet = workbook.createSheet(sheetName != null ? sheetName : "Sheet1");
			} else {
				sheet = workbook.getSheet(sheetName);
			}

			// Create header row if headers provided
			int startRow = 0;
			if (headers != null && headers.length > 0) {
				Row headerRow = sheet.createRow(0);
				CellStyle headerStyle = createHeaderStyle(workbook);

				for (int i = 0; i < headers.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(headers[i]);
					cell.setCellStyle(headerStyle);
				}
				startRow = 1;
			}

			// Write data rows
			for (int rowIdx = 0; rowIdx < data.length; rowIdx++) {
				Row row = sheet.createRow(startRow + rowIdx);
				Object[] rowData = data[rowIdx];

				for (int colIdx = 0; colIdx < rowData.length; colIdx++) {
					Cell cell = row.createCell(colIdx);
					setCellValue(cell, rowData[colIdx]);
				}
			}

			// Auto-size columns
			int maxCols = headers != null ? headers.length : (data.length > 0 ? data[0].length : 0);
			for (int i = 0; i < maxCols; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write to file
			try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
				workbook.write(outputStream);
				System.out.println("Excel data written successfully!");
			}

		} finally {
			workbook.close();
		}
	}

	/**
	 * Helper method to determine if format should be XLSX
	 */
	private static boolean isXlsxFormat(String filePath) {
		return filePath.toLowerCase().endsWith(".xlsx");
	}

	/**
	 * Creates and returns a CellStyle for Excel headers with a bold font, light
	 * blue background, and thin borders on all sides.
	 *
	 * @param workbook the Workbook instance to which the style will be applied
	 * @return a configured CellStyle for Excel header cells
	 */
	private static CellStyle createHeaderStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		return style;
	}

	/**
	 * Sets cell value based on the object type
	 */
	private static void setCellValue(Cell cell, Object value) {
		if (value == null) {
			cell.setCellValue("");
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof Number) {
			cell.setCellValue(((Number) value).doubleValue());
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof java.util.Date) {
			cell.setCellValue((java.util.Date) value);
		} else {
			cell.setCellValue(value.toString());
		}
	}
}
