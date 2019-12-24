package Java_IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Buffered_IO {
	public static void main(String[] args) throws IOException {
		buff();
	}
	public static void buff() throws IOException {
		FileReader fr = new FileReader("D:\\tempfile\\fos.java");
		BufferedReader br = new BufferedReader(fr);
		
		FileWriter fw = new FileWriter("D:\\tempfile\\copyed_fos.wuli");
		BufferedWriter bw = new BufferedWriter(fw);
		
		char [] chara = new char[2048];
		
		int in ;
		while((in=fr.read(chara))!= -1) {
			bw.write(chara,0,in);
		}
		br.close();
		bw.close();
		System.out.println("Copy over");
	}
}
