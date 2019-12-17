package Java_IO;

import java.io.File;
import java.io.IOException;

public class fileClass {
	public static void main(String[] args) throws IOException {
		
		File se = new File("D:\\Frelon.txt");
		File we = new File("D:\\","sda.py");
		System.out.println(se+"~~~~"+we);
		
		File dir = new File("D:\\KW"+File.separator);
		File sdCard = new File (dir,"a.java");
		if(sdCard.createNewFile()) {
			System.out.println("Create File Successfully ! "+sdCard);
		}else {
			System.out.println("Filed!");
		}
	}
}
