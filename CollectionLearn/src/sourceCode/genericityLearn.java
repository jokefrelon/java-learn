package sourceCode;

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
		printCollection(human);
		
		Set<person> doxeu = new HashSet<person>();
		doxeu.add(new person(19,"frelon"));
		doxeu.add(new person(23,"jinjin"));
		printCollection(doxeu);
	}
	public static void printCollection(Collection<?> coll) {
		for (Object obj:coll){
			System.out.println(obj);
		}
	}
}