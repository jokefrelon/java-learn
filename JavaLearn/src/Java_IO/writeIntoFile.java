package Java_IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class writeIntoFile {
	private static final String Line_separator = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException {
		File dir = new File("c:\\tempfile");
		if(!dir.exists()) {
			dir.mkdir();
		}
		String str ="i Love Java"+Line_separator;
		String ssr = Line_separator+"Hello World"+Line_separator;
		FileOutputStream fops = null;

		try {
			fops = new FileOutputStream("c:\\tempfile\\fops.java",true);
			
			fops.write(ssr.getBytes());
			fops.write(str.getBytes());
			fops.write(89);
			
			System.out.println("–¥»Î≥…π¶");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fops!=null) {}
			fops.close();
		}
	}
}
