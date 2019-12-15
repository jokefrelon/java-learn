# Collection-Map

~~~properties
Map--|双列集合,一次存一对键值对,必须保证唯一性
	 --|HashMap:哈希表,不同步,允许空的Key,Value
	 --|TreeMap:二叉树,线程不同步,可以对Map集合的键🥰排序
	 --|Hashtable:哈希表,线程同步效率低下,不允许空的Key,Value
~~~

## 1. Map集合共性功能

|   添加   | V put(K,V) / putAll(Map<K,V> map)  |
| :------: | :--------------------------------: |
| **删除** |   **V remove(K) / void clear()**   |
| **判断** | **Boolean  containsKey / Value()** |
| **获取** |     **V get(K) / int size()**      |

注意:如果对同一个键多次存储会出现值被覆盖的现象



## 2 Map 集合取出键值对方法

### 2.1 迭代器取出法

~~~java
package sourceCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class collMap {
	public static void main(String[] args) {
		Map<Integer,String> se = new HashMap<Integer,String>();
		se.put(1, "Now");
		se.put(5, "aset");
		se.put(6, "kEREd");
//		System.out.println(se);
		Set<Integer> keySet = se.keySet();
		for (Iterator<Integer> iter = keySet.iterator(); iter.hasNext();) {
			Integer ssr = (Integer) iter.next();
			System.out.println(se.get(ssr));
		}
	}
}
~~~

### 2.2 Map.Entry

~~~Java
		Set<Map.Entry<Integer, String>> Solo = se.entrySet();
		for (Iterator<Map.Entry<Integer, String>> itera = Solo.iterator(); itera.hasNext();) {
			Map.Entry<Integer, String> me = itera.next();
			Integer er =me.getKey();
			String  sun =me.getValue();
			System.out.println(er+"::::"+sun);			
		}
~~~

这里解释以下**Map.Entry** 其实,**Entry**就是**Map**接口内的一个接口

它随着**Map**的加载而加载

~~~java
interface Map{
	public static interface Entry {
		
	}
}
class xxx implements Map.Entry{

}
~~~

### 2.3 Values方法取出键值对

~~~java
Collection<String> value = se.values();
for (Iterator<String> uuid = value.iterator(); uuid.hasNext();) {
	String dock =uuid.next();
	System.out.println(dock);
}
~~~

<hr>

### 2.4 三种方法结果如下:

~~~properties
Now
aset
kEREd
​~~~~~~~~~~~
1::::Now
5::::aset
6::::kEREd
​~~~~~~~~~~~
Now
aset
kEREd
~~~

### 2.5 关于Map.Entry方法

其实**Map.Entry**可以直接写为**Entry** 方法,因为我在敲代码的时候偶然发现,不敲 **Map.** 程序依然可以正常运行

百度得:

~~~properties
Entry是Map中的一个静态内部类，用来表示Map中的每个键值对。除非使用了静态导入:import static java.util.Map.*
除了使用静态导入外，还可以直接导入这个类，因为它是public的。即:import java.util.Map.Entry。
导入后也可以在当前空间直接使用Entry。
~~~

