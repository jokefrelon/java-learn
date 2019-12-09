package sourceCode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class listTest {
	public static void clean(ArrayList<person> jsr) {
		List<person> dot = new ArrayList<person>();

		for (ListIterator<person> lis = jsr.listIterator(); lis.hasNext();) {
			person pop = lis.next();
			if (!dot.contains(pop)) {
				dot.add(pop);
			}
		}
		jsr.clear();
		jsr.addAll(dot);
	}

	public static void main(String[] args) {
		ArrayList<person> jsr = new ArrayList<person>();
		jsr.add(new person(20, "frelon"));
		jsr.add(new person(21, "frelon"));
		jsr.add(new person(23, "frelon"));
		jsr.add(new person(21, "frelon"));
		clean(jsr);
		System.out.println(jsr);
	}

}
