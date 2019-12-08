package hadoop_download;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class listTest {
	public static void removeTheSame(ArrayList<person> jsr) {
		ArrayList<person> bak = new ArrayList<person>();
		for (ListIterator<person> it = jsr.listIterator(); it.hasNext();) {
			person pe = it.next();
			if (jsr.contains(pe)) {
				
			}
		}
	}

	public static void main(String[] args) {
		ArrayList<person> jsr = new ArrayList<person>();
		jsr.add(new person(20, "frelon"));
		jsr.add(new person(21, "frelon"));
		jsr.add(new person(22, "frelon"));
		jsr.add(new person(23, "frelon"));
		jsr.add(new person(20, "frelon"));
		jsr.add(new person(21, "frelon"));
		jsr.add(new person(22, "frelon"));
		jsr.add(new person(23, "frelon"));
		removeTheSame(jsr);
		for (Iterator<person> it = jsr.iterator(); it.hasNext();) {
			person object = (person) it.next();
			System.out.println(object);
		}
	}
	
}
