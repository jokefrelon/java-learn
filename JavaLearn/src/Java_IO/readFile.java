package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readFile {
	private static final int LIMSize = 8192;

	public static void main(String[] args) throws IOException {
		File dir = new File("c:\\tempfile\\fops.java");
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		FileInputStream fipts = new FileInputStream(dir);

		int byt;
		while ((byt = fipts.read()) != -1) {
			System.out.println(byt);
		}

		fipts.close();
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		byte[] buf = new byte[2];

		FileInputStream fip = new FileInputStream(dir);

		int len = fip.read(buf);
		System.out.println(len + "~~~" + new String(buf));

		fip.close();
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		FileInputStream fips = new FileInputStream(dir);
		byte[] by = new byte[LIMSize];

		int length = 0;

		while ((length = fips.read(by)) != -1) {
			System.out.println(new String(by, 0, length));
		}
		fips.close();

	}
}
