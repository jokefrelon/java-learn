package Java_IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class buffer {
	public static void main(String[] args) throws IOException {
				copyfile();
	}
	public static void copyfile() throws IOException {
		File dir  = new File("d:\\LinuxSoftware_VariedOS\\Python-3.8.0.tgz");
		
		FileInputStream pis = new FileInputStream(dir);
		FileOutputStream pos = new FileOutputStream("d:\\tempfile\\Python.wilu");
		
		BufferedInputStream bufpis = new BufferedInputStream(pis);
		BufferedOutputStream bufpos = new BufferedOutputStream(pos);
		
		byte [] buf = new byte[8192];
		int len = 0;
		while((len=bufpis.read(buf))!=-1) {
			bufpos.write(buf,0,len);
		}
		bufpis.close();
		bufpos.close();
		System.out.println("Copy over");
	}  
}
