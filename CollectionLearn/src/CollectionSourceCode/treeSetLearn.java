package CollectionSourceCode;

import java.util.Set;
import java.util.TreeSet;

public class treeSetLearn {
	public static void main(String[] args) {
		Set ssf = new TreeSet();
		ssf.add(new person(12, "hfh"));
		ssf.add(new person(19, "frelon"));
		ssf.add(new person(13, "jokoe"));
		System.out.println(ssf);

	}
}
