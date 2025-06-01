package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {

	private static final String FILE_NAME = "seleniumgrid.xlsx";

	public static void main(String[] args) {
		writeExcel();
		readExcel(FILE_NAME);
	}

	// Method to write data into Excel file
	public static void writeExcel() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Demo");

		// Header row
//		Row header = sheet.createRow(0);
//		header.createCell(0).setCellValue("ID");
//		header.createCell(1).setCellValue("Name");
//		header.createCell(2).setCellValue("Status");
//		header.createCell(3).setCellValue("Age");
//		header.createCell(4).setCellValue("Gender");
//
//		// Data rows
//		Row row1 = sheet.createRow(1);
//		row1.createCell(0).setCellValue("1");
//		row1.createCell(1).setCellValue("Raman");
//		row1.createCell(2).setCellValue("Pass");
//		row1.createCell(3).setCellValue("12");
//		row1.createCell(4).setCellValue("Male");
//
//		Row row2 = sheet.createRow(2);
//		row2.createCell(0).setCellValue("2");
//		row2.createCell(1).setCellValue("Rahul");
//		row2.createCell(2).setCellValue("Fail");
//		row2.createCell(3).setCellValue("90");
//		row2.createCell(4).setCellValue("Female");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Username");
		header.createCell(1).setCellValue("Password");
		header.createCell(2).setCellValue("Browser");

		// Data rows
		Row row1 = sheet.createRow(1);
		row1.createCell(0).setCellValue("Raman");
		row1.createCell(1).setCellValue("Pass");
		row1.createCell(2).setCellValue("Chrome");

		Row row2 = sheet.createRow(2);
		row2.createCell(0).setCellValue("Rahul");
		row2.createCell(1).setCellValue("Fail");
		row2.createCell(2).setCellValue("Safari");

		Row row3 = sheet.createRow(3);
		row3.createCell(0).setCellValue("Ram");
		row3.createCell(1).setCellValue("Fail");
		row3.createCell(2).setCellValue("Chrome");

		Row row4 = sheet.createRow(4);
		row4.createCell(0).setCellValue("Rajes");
		row4.createCell(1).setCellValue("Fail");
		row4.createCell(2).setCellValue("Safari");

		Row row5 = sheet.createRow(5);
		row5.createCell(0).setCellValue("Rakesh");
		row5.createCell(1).setCellValue("Fail");
		row5.createCell(2).setCellValue("Chrome");

		Row row6 = sheet.createRow(6);
		row6.createCell(0).setCellValue("Ramandip");
		row6.createCell(1).setCellValue("Fail");
		row6.createCell(2).setCellValue("Chrome");

		Row row7 = sheet.createRow(7);
		row7.createCell(0).setCellValue("Raaj");
		row7.createCell(1).setCellValue("Fail");
		row7.createCell(2).setCellValue("Chrome");

		Row row8 = sheet.createRow(8);
		row8.createCell(0).setCellValue("Ramprasad");
		row8.createCell(1).setCellValue("Fail");
		row8.createCell(2).setCellValue("Chrome");

		Row row9 = sheet.createRow(9);
		row9.createCell(0).setCellValue("Ramu");
		row9.createCell(1).setCellValue("Fail");
		row9.createCell(2).setCellValue("Chrome");

		Row row10 = sheet.createRow(10);
		row10.createCell(0).setCellValue("Raju");
		row10.createCell(1).setCellValue("Fail");
		row10.createCell(2).setCellValue("Chrome");

		try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
			workbook.write(fos);
			System.out.println("Data written to Excel file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Method to read data from Excel file
	public static List<List<String>> readExcel(String fileName) {
		List<List<String>> data = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(fileName); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);

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
