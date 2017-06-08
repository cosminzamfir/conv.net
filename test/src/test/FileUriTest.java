package test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class FileUriTest {

	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("file:///h/hadoop");
		System.out.println(uri.getAuthority());
		//		File file = new File(uri);
		//		System.out.println(file);
	}
}
