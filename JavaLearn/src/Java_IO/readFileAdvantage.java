package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readFileAdvantage {
	public static void main(String[] args) throws IOException {
		File dir = new File("c:\\tempfile\\fops.java");
		FileInputStream fips = new FileInputStream(dir);
		byte [] byt = new byte [fips.available()];
		fips.read(byt);
		System.out.println(new String(byt));
		fips.close();
	}
}
