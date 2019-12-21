package Java_IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class IO_Test1 {
	public static void main(String[] args) {
		File dir = new File("c:\\tempfile");

		List<String> list = new ArrayList<String>();

		fileFilterByName filter = new fileFilterByName(".txt");

		getFile(dir, filter, list);

		for (String ser : list) {
			System.out.println(ser);
		}
	}

	private static void getFile(File file, fileFilterByName filter, List<String> list) {
		File[] fileName = file.listFiles();
		for (File se : fileName) {
			if (se.isDirectory()) {
				getFile(se, filter, list);
			} else {
				if (filter.accept(se)) {
					list.add(se.getPath());
				}
			}
		}
	}
}
