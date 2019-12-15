package CollectionSourceCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collections_utils {
	public static void main(String[] args) {
		List<String> se = new ArrayList<String>();
		se.add("fdw");
		se.add("jokeme");
		se.add("dfipo");
		se.add("daase");
		Collections.sort(se);
		System.out.println(se);
		
		Collections.reverse(se);
		System.out.println(se);
		
		Collections.sort(se, Collections.reverseOrder(new compareByLength()));
		System.out.println(se);
	}
}
