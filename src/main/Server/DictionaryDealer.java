
package Server;

import java.util.HashMap;
import java.util.List;

public class DictionaryDealer {

	public String dashString(String word) {
		int wordLength = word.length();
		return new String(new char[wordLength]).replace('\0', '_');
	}

	public String takeCategories(HashMap<String, List<String>> hashMap) {
		int counter = 1;
		String categories = "";
		for (String key : hashMap.keySet()) {
			categories = categories + counter + key + "\n";
			counter++;
		}
		return categories;
	}

}
