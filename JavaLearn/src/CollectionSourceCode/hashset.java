package CollectionSourceCode;

import java.util.HashSet;
import java.util.Set;

public class hashset {
	public static void main(String[] args) {
		Set doThat = new HashSet();
		
		doThat.add(new person(13,"coco"));
		doThat.add(new person(22,"peon"));
		doThat.add(new person(23,"frelon"));
		doThat.add(new person(23,"frelon"));
		
		System.out.println(doThat);
	}
}
