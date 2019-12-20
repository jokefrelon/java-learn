package Java_IO;

import java.io.File;

public class visitFile {
	public static void main(String[] args) {
		File alean = new File("c:\\");
		if (alean.exists()) {

			String[] dd = alean.list();
			for (String ss : dd) {
				System.out.println(ss);

			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
			File[] fo = alean.listFiles();
			for (File fs : fo) {
				if(fs.getName().contains("Logs")) {
					System.out.println(fs+" ~~~ "+fs.getName());
				}
				
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
			Long sis = alean.getFreeSpace();
			System.out.println(alean + " " + sis / Math.pow(1024, 3) + "Gb" + " free!");
		} else {

			System.out.println("No this file");
		}

	}
}
