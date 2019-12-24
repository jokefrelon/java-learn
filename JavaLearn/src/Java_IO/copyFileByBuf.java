package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import APICourceCode.dateAPI;

public class copyFileByBuf {
	public static void main(String[] args) throws IOException {
		dateAPI date = new dateAPI();
		date.getDate();
		File dir = new File("D:\\LinuxSoftware_VariedOS\\jdk-8u231-linux-x64.tar.gz");
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(dir);
			fos = new FileOutputStream("D:\\tempfile\\jdk8.wuli");

			byte[] buf = new byte[8192];

			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
		} catch (IOException e) {
			throw new IOException();
		} finally {
			fis.close();
			fos.close();
			date.getDate();
			System.out.println("Copy Over");
		}

	}
}
