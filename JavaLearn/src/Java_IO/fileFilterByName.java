package Java_IO;

import java.io.File;
import java.io.FileFilter;

public class fileFilterByName implements FileFilter {

	@Override
	public boolean accept(File arg0) {
		
		return arg0.isDirectory();
	}

}
