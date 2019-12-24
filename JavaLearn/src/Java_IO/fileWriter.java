package Java_IO;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class fileWriter {
	public static void main(String[] args) throws IOException {
//		FileOutputStream fos = new FileOutputStream("D:\\tempfile\\fos.c");
//		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
		
		FileReader fr = new FileReader("D:\\tempfile\\fos.java");
		FileWriter fw = new FileWriter("D:\\tempfile\\fos.c");
		
		int in;
		while((in=fr.read())!=-1) {
			fw.write((char)in);
		}
		fr.close();
		System.out.println(fw.getEncoding());
		fw.close();
//		osw.close();
		System.out.println("Copy over");
	}
}
