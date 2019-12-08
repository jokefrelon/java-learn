# **collection**集合

## 1.共性功能

#### a.添加



~~~java
Boolean add (Obj obj);
~~~

添加单个对象



~~~Java
Boolean addAll (Collection c);
~~~

添加一组的**collection**的对象



#### b.删除



~~~Java
void clear();
~~~

清空**collection**内所有对象/元素

​	

~~~java
Boolean remove (Object o);
~~~

删除**collection**里面某个对象



~~~java
Boolean removeAll (Collection c);
~~~

删除一组的**collection**



#### c.获取长度



~~~java
int size();
~~~



#### d.判断

~~~java
Boolean isEmpty();
~~~

~~~java
Boolean contains(Object o);
~~~

~~~java
Boolean containsAll(Collection c)
~~~



#### e.集合转数组

~~~java
toArray();
~~~

~~~java
toArray([]);
~~~



#### f.迭代器

~~~java
Iterator iterator();
~~~

取出集合元素的



#### g.代码示例

~~~java
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Test {
	public static void main(String[] args) {
		
		Collection<Integer> coll2 = new ArrayList<Integer>();
		coll2.add(10000001);
		coll2.add(20000002);
		
		Collection<Integer> coll = new ArrayList<Integer>();
		coll.add(12);
		coll.add(23);
		coll.add(85);
		coll.add(45);
		System.out.println(coll);
		coll.addAll(coll2);
		System.out.println(coll);
		boolean se = coll.isEmpty();
		System.out.println("collection is empty ? "+ se);
		coll.remove(12);
		System.out.println(coll);
		coll.removeAll(coll2);
		System.out.println(coll);
		Iterator <Integer> it = coll.iterator();
		while (it.hasNext())
			System.out.print(" coll has "+it.next()+" ////");	
	}
	
}

~~~



## 2. **List**集合 && **set**集合

~~~log
collection--
		  |--list:有序,有索引,允许重复元素
		  |--set:不允许重复元素
~~~

### list常见方法

#### 1.增:

~~~java
add (index,element);
~~~



#### 2.删:

~~~java
remove (index);
~~~



#### 3.改:

~~~java
set (index,new-element);
~~~



#### 4.查:

~~~java
indexOf (element);
element get(index);
~~~

 

#### 5.代码示例

~~~java
package hadoop_download;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

		for (Iterator iet = list.iterator(); iet.hasNext();) {
			Object obj = iet.next();
			if (obj.equals(98)) {
				list.set(list.indexOf(obj), 97);
				list.add(23);
			}

		}
		System.out.println(list);

	}
}

~~~

~~~properties
Exception in thread "main" java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(Unknown Source)
	at java.util.ArrayList$Itr.next(Unknown Source)
	at hadoop_download.download.ssr(download.java:37)
	at hadoop_download.download.main(download.java:10)
~~~

遇到这种在迭代器里还增加元素的时候,**JVM**会报错,这时候就需要用**ListIterator**



将

~~~java
		for (Iterator iet = list.iterator(); iet.hasNext();) {
			Object obj = iet.next();
			if (obj.equals(98)) {
				list.set(list.indexOf(obj), 97);
				list.add(23);
			}

		}
~~~

改为

~~~Java
		for (ListIterator iet = list.listIterator(); iet.hasNext();) {
			Object obj = iet.next();
			if (obj.equals(98)) {
				iet.add(23);
			}

		}
~~~



### 3.**list**集合的具体子类

~~~properties
list----
	|-- Vector:数组数据结构,长度可变,线程同步<多线程安全>,但是速度比较慢(现在已经不常用-被ArrayList替代)
	|-- ArrayList:数组结构,长度可变,线程不同步<多线程不安全>.查,速度快,增 删 改速度慢
	|-- LinkedList:链表结构,线程不同步<多线程不安全>,增,删,改,速度快,查 速度慢
~~~







### 4.List类练习

~~~java
package hadoop_download;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class listTest {
	public static void main(String[] args) {
		List jsr = new ArrayList();
		jsr.add(new person(20, "frelon"));
		for (Iterator it = jsr.iterator(); it.hasNext();) {
			person object =  (person) it.next();
			System.out.println(object);
		}
	}
}

~~~

~~~java
package hadoop_download;

public class person {
	private int age;
	private String name;

	public person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "person [age=" + age + ", name=" + name + "]";
	}

}
~~~
<p style="color:#ee1148">在日常开发中还是建议尽量不要<Strong>overwrite</Strong> <b>toString</b> 直接调用 <b>getXXX</b></p>







