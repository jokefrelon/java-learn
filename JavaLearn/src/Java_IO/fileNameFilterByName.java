package Java_IO;

import java.io.File;
import java.io.FilenameFilter;

public class fileNameFilterByName implements FilenameFilter {
	private String se="";
	
	public fileNameFilterByName(String se) {
		super();
		this.se = se;
	}
	public fileNameFilterByName() {
		super();
	}
	
	@Override
	public boolean accept(File arg0, String arg1) {
		
		return arg1.endsWith(se);
	}

}
