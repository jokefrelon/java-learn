package Java_IO;

import java.io.File;

public class FileFilter {
	public static void main(String[] args) {
		File dir = new File("C:\\");
		
		String[] str = dir.list(new fileNameFilterByName());
		for (String name : str) {
			System.out.println(name);
		}
		System.out.println("~~~~~~~~~~");
		File[] str1  =  dir.listFiles(new fileFilterByName());
		for (File strr : str1) {
			System.out.println(strr);
		}
	}
}
