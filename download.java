package hadoop_download;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class download {
	public static void main(String[] args) {
		List ddos = new ArrayList();
		ssr(ddos);
	}

	public static void ssr(List list) {
		list.add(2121);
		list.add(2111);
		list.add(2123);
		list.add(2143);
		System.out.println(list);

		list.add(1, 999999);
		System.out.println(list);

		list.remove(1);
		System.out.println(list);

		list.set(2, 98);
		System.out.println(list);

		System.out.println(list.get(1));
		System.out.println(list.indexOf(2111));

		Iterator it = list.iterator();
		while (it.hasNext())
			System.out.println(it.next());

//		for (Iterator iet = list.iterator(); iet.hasNext();) {
//			Object obj = iet.next();
//			if (obj.equals(98)) {
//				list.set(list.indexOf(obj), 97);
//				list.add(23);
//			}

//		}
		for (ListIterator iet = list.listIterator(); iet.hasNext();) {
			Object obj = iet.next();
			if (obj.equals(98)) {
				iet.add(23);
			}

		}
		System.out.println(list);

	}
}
