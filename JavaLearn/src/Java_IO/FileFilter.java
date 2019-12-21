package Java_IO;

import java.io.File;

public class FileFilter {
	public static void main(String[] args) {
		File dir = new File("D:\\hadoop-2.8.5");
		
		String[] str = dir.list(new fileNameFilterByName("e"));
		for (String name : str) {
			System.out.println(name);
		}
		
		File[] str1  =  dir.listFiles(new fileFilterByName());
		for (File strr : str1) {
			System.out.println(strr);
		}
	}
}
