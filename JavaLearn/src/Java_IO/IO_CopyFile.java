package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IO_CopyFile {

	public static void main(String[] args) throws IOException {
		File dir = new File("C:\\tempfile\\fops.java");
		FileInputStream fis = new FileInputStream(dir);

		File dir1 = new File("C:\\tempfile\\fops.py");
		FileOutputStream fos = new FileOutputStream(dir1);
		int len = 0;
		while ((len = fis.read()) != -1) {
			fos.write(len);
		}
		fis.close();
		fos.close();
	}
}
