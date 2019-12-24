package Java_IO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO_SimplifyChinese {
	public static void main(String[] args) throws IOException {
		copyChineseChar();
	}
	public static void copyChineseChar() throws IOException {
		FileReader fr = new FileReader("D:\\LinuxSoftware_VariedOS\\jdk-8u231-linux-x64.tar.gz");
		FileWriter fw = new FileWriter("D:\\tempfile\\jdk8.wuli");
		
		char [] chara = new char[2048];
		int len ;
		while ((len=fr.read(chara))!= -1) {
			fw.write(chara,0,len);
		}
		fr.close();
		fw.close();
		System.out.println("copy over");
	}
}
