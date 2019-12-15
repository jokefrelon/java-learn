package CollectionSourceCode;

import java.util.Comparator;

public class compareByLength implements Comparator<String> {
	@Override
	public int compare(String str1,String str2) {
		int temp = str2.length() - str1.length();
		return temp==0?str2.compareTo(str1):temp;
	}
}
