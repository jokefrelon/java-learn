package Java_IO;

import java.io.File;
import java.io.IOException;

public class fileClass {
	public static void main(String[] args) throws IOException {
		
		File se = new File("D:\\Frelon.txt");
		File we = new File("D:\\","sda.py");
		File dir = new File("D:\\KW"+File.separator);
		File sdCard = new File (dir,"a.java");
		sdCard.createNewFile();
	}
}
