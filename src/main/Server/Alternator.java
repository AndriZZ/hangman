package Server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Alternator {
	public List<String> readFileContent(String filename) throws IOException {

		String content = new String(Files.readAllBytes(Paths.get(filename)));
		Alternator alternator = new Alternator();
		List<String> strings = alternator.turnFileIntoArrayOfStrings(content);
		return strings;
	}

	public List<String> turnFileIntoArrayOfStrings(String content) {
		List<String> supply = new ArrayList<String>();
		int index = 0;
		int tempInt = 0;
		int startIndex = 0;
		int lastIndex = content.length() - 1;
		while (true) {
			if (content.charAt(index) == '\n') {
				tempInt++;
				String temp = new String();
				for (int i = 0; i < index - startIndex; i++) {
					temp += content.charAt(startIndex + i);
				}
				startIndex = index;
				supply.add(tempInt - 1, temp);
			}
			if (index == lastIndex) {
				tempInt++;
				String temp = new String();
				for (int i = 0; i < index - startIndex + 1; i++) {
					temp += content.charAt(startIndex + i);
				}
				supply.add(tempInt - 1, temp);
				break;
			}
			index++;
		}
		return supply;
	}

	public String makeStringSpreadbySpaces(String string) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {

			result.append(string.charAt(i));
			if (i != string.length() - 1) {
				result.append(" ");
			}
		}
		return result.toString();
	}
}
