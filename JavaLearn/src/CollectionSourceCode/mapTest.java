package CollectionSourceCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class mapTest {
	public static void main(String[] args) {
		Map<student,sex> we = new HashMap<student,sex>(); 
		we.put(new student("Helean",23), new sex("female"));
		we.put(new student("Lora",21), new sex("female"));
		we.put(new student("Lora",21), new sex("male"));
		we.put(new student("Kingting",29), new sex("male"));
//		Set<student> key = we.keySet();
//		for (Iterator<student> itera = key.iterator(); itera.hasNext();) {
//			student fun = itera.next();
//			System.out.println(fun+"~~~~~~~~~~~~~~"+we.get(fun));
//			
//		}
		for (student stu:we.keySet()) {
			System.out.println(stu+"~~~~~~~~~~~~~"+we.get(stu));
		}
	}
}
