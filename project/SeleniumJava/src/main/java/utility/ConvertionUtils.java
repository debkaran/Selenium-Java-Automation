package utility;

import java.util.Hashtable;
import java.util.List;

public class ConvertionUtils {

	public static Object[][] convertListTo2DArray(List<List<String>> list) {

		return list.stream().map(innerList -> innerList.toArray(new Object[0])).toArray(Object[][]::new);
	}

	public static Hashtable<String, String> convertListToHashtable(List<List<String>> list) {

		Hashtable<String, String> hashtable = list.stream().filter(row -> row.size() >= 2).collect(Hashtable::new,
				(table, row) -> table.put(row.get(0), row.get(1)), Hashtable::putAll);
		System.out.println(hashtable);

		return hashtable;
	}

	public static Object[][] convertHashtableToObjectArray(Hashtable<String, String> table) {

		return table.entrySet().stream().map(entry -> new Object[] { entry.getKey(), entry.getValue() })
				.toArray(Object[][]::new);
	}

	public static long calculateTwoNumber(String operator, int num1, int num2) {

		long result = 0;
		switch (operator) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "/":
			result = num1 / num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		default:
			System.out.println("Invalid Operator!!!");
		}
		return result;
	}
}
