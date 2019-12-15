package CollectionSourceCode;

import java.util.Set;
import java.util.TreeSet;

public class diySavePerson {
	public static void main(String[] args) {
//		Set t1 = new TreeSet(new diyCompare());
//		t1.add(new person(12,"frelon"));
//		t1.add(new person(29,"lion"));
//		t1.add(new person(29,"aion"));
//		t1.add(new person(19,"zoco"));
//		for(Object se : t1) {
//			System.out.println(se);
//		}
		Set<String> set1 = new TreeSet<String>( new diyCompare()); 
		set1.add("weAreFun");
		set1.add("togeUs");
		set1.add("gethgghxsawrUs");
		set1.add("zxcsdfsdff");
		set1.add("ssr");
		for (Object se : set1) {
			System.out.println(se);
		}
	}
}
