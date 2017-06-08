package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static List<String> readLines(String fileName, String... prefixes) {
		BufferedReader reader = null;
		try {
			List<String> res = new ArrayList<String>();
			reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (prefixes != null && prefixes.length > 0) {
					for (String prefix : prefixes) {
						if (line.toLowerCase().startsWith(prefix.toLowerCase())) {
							res.add(line);
							continue;
						}
					}
				} else {
					res.add(line);
				}
			}
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ignore) {
			}
		}
	}

	public static String readFromConsole(String message) {
		if (message != null) {
			System.out.print(message);
		}

		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			return bufferRead.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T randomElem(List<T> l) {
		if (l.isEmpty()) {
			System.out.println("No more words!");
			System.exit(0);
		}
		return l.get((int) (Math.random() * l.size()));
	}
}
