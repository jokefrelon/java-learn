package sourceCode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class learnSet {
public static void main(String[] args) {
	Set doli = new HashSet();
	doli.add("Dog");
	doli.add("Cat");
	doli.add("Cat");
	for (Iterator itr = doli.iterator(); itr.hasNext();) {
		Object object = (Object) itr.next();
		System.out.println(object);
	}
}
}
