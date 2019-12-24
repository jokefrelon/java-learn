package Java_IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StreamReader {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("D:\\\\LinuxSoftware_VariedOS\\\\jdk-8u231-linux-x64.tar.gz");
		FileOutputStream fos = new FileOutputStream("D:\\\\tempfile\\\\jdk8.wuli",true);
		
		InputStreamReader isr = new InputStreamReader(fis);
		OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
		
		int in ;
		while((in = isr.read()) != -1) {
			osw.write(in);
		}
		
		isr.close();
		osw.close();
		System.out.println("Copy over");
	}
}
