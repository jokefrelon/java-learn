package CollectionSourceCode;

import java.util.Comparator;

public class diyCompare implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
//
//		person p1 = (person) arg0;
//		person p2 = (person) arg1;
//
//		int temp = p1.getName().compareTo(p2.getName());
//		int temp2 = p1.getAge() - p2.getAge();
//
//		return temp == 0 ? temp2 : temp;
		String str1 = (String)arg0;
		String str2 = (String)arg1;
		int temp1=str1.length()-str2.length();
		int sss = str1.hashCode();
		int ssd = str2.hashCode();
		return temp1==0?sss-ssd:temp1;
	}

}
