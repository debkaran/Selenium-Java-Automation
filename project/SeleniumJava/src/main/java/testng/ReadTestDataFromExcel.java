package testng;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ExcelReadWrite;

public class ReadTestDataFromExcel {

//	@Test(dataProvider = "getData")
//	public void doTest(String username, String password, String isCorrect) {
//
//		System.out.println("Username : " + username + ", Password : " + password + ", Status : " + isCorrect);
//	}

	@Test(dataProvider = "getData")
	public void doTest(Hashtable<String, String> table) {

		System.out.println(table.get("ID") + "----" + table.get("Name") + "----" + table.get("Status") + "----"
				+ table.get("Age") + "----" + table.get("Gender"));
	}

	@DataProvider
	public Object[][] getData() {

		String filePath = "sample.xlsx";
		Object[][] excelData = null;
		Hashtable<String, String> table = null;

		List<List<String>> list = ExcelReadWrite.readExcel(filePath);

		System.out.println(list);
		System.out.println(list.get(0).size());

		excelData = new Object[list.size() - 1][1];

		for (int i = 1; i < list.size(); i++) {
			table = new Hashtable<>();

			List<String> row = list.get(i);

			for (int j = 0; j < list.get(0).size(); j++) {
				table.put(list.get(0).get(j), row.get(j));
			}

			excelData[i - 1][0] = table;
		}

		// excelData =
		// ConvertionUtils.convertListTo2DArray(ExcelReadWrite.readExcel(filePath));

		return excelData;
	}
}
