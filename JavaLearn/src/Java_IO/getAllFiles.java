package Java_IO;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class getAllFiles {

	public static void main(String[] args) throws IOException {
		File dir = new File("D:\\github");
		getAllFile(dir);
	}

	public static void getAllFile(File se) throws IOException {
		File[] dir1 = se.listFiles();
		if(!se.exists()) {
			throw new NoSuchFileException("No such Path");
		}
		for (File der : dir1) {
			if (der.isDirectory()) {
				getAllFile(der);
			} else {
				System.out.println("Visited " + der);
			}
		}
	}
}
