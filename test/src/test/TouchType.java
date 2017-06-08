package test;

import java.util.List;

public class TouchType {

	public static void main(String[] args) {
		List<String> lines = FileUtils.readLines("touch.txt");
		for (String line : lines) {
			System.out.println(line);
			FileUtils.readFromConsole("");
		}
	}
}
