package Java_IO;

import java.io.File;
import java.io.FileFilter;

public class fileFilterByName implements FileFilter {
	private String str;

	public fileFilterByName(String str) {
		super();
		this.str = str;
	}

	public fileFilterByName() {
		super();
	}

	@Override
	public boolean accept(File arg0) {
		return arg0.getName().endsWith(this.str);
	}

}