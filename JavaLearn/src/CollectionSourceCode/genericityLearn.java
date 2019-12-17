package CollectionSourceCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class genericityLearn {
	public static void main(String[] args) {
		List<person> human = new ArrayList<person>();
		human.add(new person(19, "frelon"));
		human.add(new person(27, "lion"));
		human.add(new person(31, "dalian"));
		printColl(human);
		
		Set<person> doxeu = new HashSet<person>();
		doxeu.add(new person(19,"frelon"));
		doxeu.add(new person(23,"jinjin"));
		printColl(doxeu);
	}

	public static void printColl(Collection<?> coll) {
		for (Object obj:coll){
			System.out.println(obj);
		}
	}
}