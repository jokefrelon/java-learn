package sourceCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class listTest {
	public static void clean(ArrayList<person> jsr) {
		ArrayList<person> bak = new ArrayList<person>();
		ArrayList<person> base = new ArrayList<person>();
		
		for (ListIterator<person> its = jsr.listIterator(); its.hasNext();) {
			person se = its.next();
			if(bak.isEmpty()) {
				bak.add(se);
				base.add(se);
			}else {
				for (ListIterator<person> baks = bak.listIterator(); baks.hasNext();) {
					person pers = baks.next();
					if(se.toString()!=pers.toString()) {
						base.add(se);
					}	break;
				}
				bak.clear();
				bak.addAll(base);
			}
		}
		jsr.clear();
		jsr.addAll(bak);
	}

	public static void main(String[] args) {
		ArrayList<person> jsr = new ArrayList<person>();
		jsr.add(new person(20, "frelon"));
		jsr.add(new person(21, "frelon"));
		jsr.add(new person(23, "frelon"));
		jsr.add(new person(21, "frelon"));
		clean(jsr);
		for (Iterator<person> it = jsr.iterator(); it.hasNext();) {
			person object = (person) it.next();
			System.out.println(object);
		}
	}

}
