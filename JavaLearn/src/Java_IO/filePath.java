package Java_IO;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class filePath {
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\KW\\a.txt");
		String str = file.getAbsolutePath();
		String str1 = file.getPath();
		System.out.println(str + "~~~~~" + str1);

		Long lon = file.lastModified();
		Date date = new Date(lon);
		String mod_time = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(date);
		System.out.println(lon + " >>>>> " + mod_time);
		
		boolean boof = file.createNewFile();
		boolean boo  = file.canExecute();
		boolean booa = file.canRead();
		boolean bood = file.canWrite();
		boolean booh = file.exists();
		boolean booj = file.isAbsolute();
		boolean book = file.isDirectory();
		boolean bool = file.isFile();
		boolean booz = file.isHidden();
		System.out.println("~~~canExecute() "+boo+" ~~~canRead() "+booa);
		System.out.println("~~~canWrite()  "+bood+"~~~createNewFile()"+boof);
		System.out.println("~~~exists()  "+booh);
		System.out.println("~~~isAbsolute()  "+booj+"~~~isDirectory()"+book);
		System.out.println("~~~isFile()  "+bool+"~~~isHidden()"+booz);
	}
}
