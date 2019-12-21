package Java_IO;

import java.io.File;
import java.nio.file.NoSuchFileException;

public class deleteFiles {
	public static void main(String[] args) throws NoSuchFileException {
		File dir = new File("c:\\files");
		removeFiles(dir);
	}
	public static void removeFiles(File se) throws NoSuchFileException {
		File [] wow = se.listFiles();
		for(File wo : wow) {
			if(!wo.exists()) {
				throw new NoSuchFileException(null);
			}
			if(wo.isDirectory()) {
				removeFiles(wo);
			}else {
				Boolean esc = wo.delete();
				System.out.println("delete the file "+wo+" "+esc);
			}
		}
		Boolean esc = se.delete();
		System.out.println("delete the file "+se+" "+esc);
	}
}
