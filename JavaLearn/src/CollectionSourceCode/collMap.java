package CollectionSourceCode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class collMap {
	public static void main(String[] args) {
		Map<Integer,String> se = new HashMap<Integer,String>();
		se.put(1, "Now");
		se.put(5, "aset");
		se.put(6, "kEREd");
		Set<Integer> keySet = se.keySet();
		for (Iterator<Integer> iter = keySet.iterator(); iter.hasNext();) {
			Integer ssr = (Integer) iter.next();
			System.out.println(ssr+"~~~~~"+se.get(ssr));
		}
		System.out.println("~~~~~~~~~~~");
		
		Set<Entry<Integer, String>> Solo = se.entrySet();
		for (Iterator<Entry<Integer, String>> itera = Solo.iterator(); itera.hasNext();) {
			Entry<Integer, String> me = itera.next();
			Integer er =me.getKey();
			String  sun =me.getValue();
			System.out.println(er+"::::"+sun);			
		}
		for(Entry<Integer, String> obj:Solo) {
			System.out.println(obj.getKey()+"~~~~~"+obj.getValue());
		}
		System.out.println("~~~~~~~~~~~");
		Collection<String> value = se.values();
		for (Iterator<String> uuid = value.iterator(); uuid.hasNext();) {
			String dock =uuid.next();
			System.out.println(dock);
		}
	}
}
