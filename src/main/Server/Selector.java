package Server;

import java.io.IOException;
import java.util.*;

public class Selector {
	public static String randomElement;
	public static List<String> value;
	public static Random rand = new Random();
	public String filename;

//below gives me hashmap with keys:values
	public HashMap<String, List<String>> selectDictionary(List<String> list) throws IOException {
		String regexUnderscoreStart = "_";
		List<String> valueList = new ArrayList<>();
		HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

		String[] splitString = list.toArray(new String[0]);

		int currentMatch = 0;

		for (int currentString = splitString.length - 1; currentString > 0; currentString--) {

			if (splitString[currentString].contains(regexUnderscoreStart)) {
				currentMatch = currentString;

				List<String> keyList = new ArrayList<>(valueList);

				if (currentString != splitString.length - 1) {
					keyList.removeIf(item -> item == null || "".equals(item));
					hashMap.put(splitString[currentString], keyList);
				}

				valueList.clear();

			} else {
				if (currentMatch > currentString) {
					valueList.add(splitString[currentString]);

				}
			}

		}
		return hashMap;
	}

	public String selectCategory(HashMap<String, List<String>> hashMap, int userOption) throws IOException {
		String categoryName = "";
		List<String> categories = new ArrayList<String>();
		for (String key : hashMap.keySet()) {
			categories.add(key);
		}
		if (userOption - 1 < categories.size()) {
			categoryName = categories.get(userOption - 1);
		}
		value = hashMap.get(categoryName);
		randomElement = value.get(rand.nextInt(value.size()));

		return randomElement;


	}
}
