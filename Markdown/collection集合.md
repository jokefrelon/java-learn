# **collection**集合

~~~properties
collection--|
		   |--list:有序,有索引,允许重复元素
		   |--set:不允许重复元素,元素存取无序
~~~



## 1.共性功能

a.添加

~~~java
Boolean add (Obj obj);//添加单个对象
Boolean addAll (Collection c);//添加一组的collection的对象
~~~

b.删除

~~~Java
void clear();//清空collection内所有对象/元素
Boolean remove (Object o);//删除collection里面某个对象
Boolean removeAll (Collection c);//删除一组的collection
~~~

c.获取长度

~~~java
int size();
~~~

d.判断

~~~java
Boolean isEmpty();
Boolean contains(Object o);
Boolean containsAll(Collection c)
~~~

e.集合转数组

~~~java
toArray();
toArray([]);
~~~

f.迭代器

~~~java
Iterator iterator();
~~~



## 2. **List**集合 

### 2.1 **list**集合的具体子类

~~~properties
List----
	|-- Vector:数组数据结构,长度可变,线程同步<多线程安全>,但是速度比较慢(现在已经不常用-被ArrayList替代),长度按数组长度的100%延长
	|-- ArrayList:数组结构,长度可变,线程不同步<多线程不安全>.查,速度快,增 删 改速度慢,长度按数组长度的50%延长
	|-- LinkedList:链表结构,线程不同步<多线程不安全>,增,删,改,速度快,查 速度慢
~~~


### 2.2 List常见方法


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
subList()
~~~

### 2.3  迭代器使用注意

~~~properties
Exception in thread "main" java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(Unknown Source)
	at java.util.ArrayList$Itr.next(Unknown Source)
~~~

遇到这种在迭代器里还增加元素的时候,**JVM**会报错,这时候就需要用**ListIterator**



### 2.4 犯错实记

~~~java
package sourceCode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class listTest {
	public static void clean(ArrayList<person> jsr) {
		List<person> dot = new ArrayList<person>();

		for (ListIterator<person> lis = jsr.listIterator(); lis.hasNext();) {
			person pop = lis.next();
			if (!dot.contains(pop)) {
				dot.add(pop);
			}
		}
		jsr.clear();
		jsr.addAll(dot);
	}

	public static void main(String[] args) {
		ArrayList<person> jsr = new ArrayList<person>();
		jsr.add(new person(20, "frelon"));
		jsr.add(new person(21, "frelon"));
		jsr.add(new person(23, "frelon"));
		jsr.add(new person(21, "frelon"));
		clean(jsr);
		System.out.println(jsr);
	}

}
~~~

~~~java
package sourceCode;

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
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		person other = (person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public boolean equals(person obj) {
		if (this==obj) {
			return true;
		}
			return this.name.equals(obj.name)&&this.age==obj.age;
	}
}

~~~

上面这个例子,是我用了两天时间得出**Java**面向接口编程的一个实例

为什么说用了两天时间呢?

因为我一直都不太明白Java面向对象编程的具体表现,所以在写代码的时候也比较呆板,再者就是我还没有搞明白接口里面的类有没有被子类所实现

~~~Java
ArrayList<person> dot = new ArrayList<person>();
~~~

这就是我犯错的一段代码,我总是用**List**的子类 **ArrayList**来**new**新对象,而我**Overwrite**的方法已经被**ArrayList**实现,使用无论我怎么调用都还是无法调用到我自己 **Overwrite**后的代码

~~~java
List<person> dot = new ArrayList<person>();
~~~

这就是导致我卡了两天的细节,我用**List** **new**对象以后代码完美执行了

这就是因为没有掌握面向对象编程的思想,犯下的一个低级错误

[重写 equals和hashcode](java-Compare-学习.md)



### 2.5 LinkedList

##### a.特有方法

|   addFirst()    |  removeFirst()   |  getFirst()   |
| :-------------: | :--------------: | :-----------: |
|  #添加到第一个  |   #删除第一个    |  #获取第一个  |
|  **addlast()**  | **removeLast()** | **getLast()** |
| #添加到最后一个 |  #删除最后一个   | #获取最后一个 |

**JDK1.6**以后推荐使用下列方法替代,这样在遇到空值时就不会报错而是返回**null**

|  offerFirst()   |  pollFirst()   |  peekFirst()   |
| :-------------: | :------------: | :------------: |
|  #添加到第一个  |  #删除第一个   |  #获取第一个   |
| **offerLast()** | **pollLast()** | **peekLast()** |
| #添加到最后一个 | #删除最后一个  | #获取最后一个  |

##### b.实战演练

实现  先进后出

~~~java
package sourceCode;

import java.util.LinkedList;

public class linkedlist_test {
	public static void main(String[] args) {
		test loser = new test();
		loser.setKail("hello");
		loser.setKail("CNN");
		loser.setKail("Love China");
		while (loser.Kailisempty()) {
			System.out.println(loser.getKail());
		}
	}
}

class test {
	private LinkedList<Object> kail;

	public Object getKail() {
		return kail.pollLast();
	}

	public Boolean Kailisempty() {
		if (kail.isEmpty()) {
			return false;
		} else if (!kail.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "test [kail=" + kail + "]";
	}

	public void setKail(Object obj) {
		this.kail.addLast(obj);
	}

	public test() {
		kail = new LinkedList<Object>();
	}
}
~~~

## 3. Set集合

~~~properties
Set--:不允许重复元素,方法和collection相同.set集合只能用Iterator取出元素
	|--Hashset:不保证存入和取出的顺序一致,不允许重复,效率高 
			 |--LinkedHashSet:有序的HashSet表,不允许重复	
	|--Treeset:可以对元素进行排序,其排序方式需要元素具备比较功能,且实现CompareTo()方法,且不允许存储相同元素的方法是依据compareto()返回值是否为0,其数据结构为二叉树结构,
~~~



### 3.1HashSet

**HashSet**主要就是靠**hashcode()**表来确定元素排列的,在使用时必须要重写**hashCode()**方法,而**hashCode()**方法内部又调用到了 **equals()**方法来解决**Hash冲突**,所以在使用时必须要**Overwrite**这两个方法

### 3.1.1 HashSet使用实例

~~~Java
package sourceCode;

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

person类同上,这里仅写出 Overwrite 部分
	@Override
	public boolean equals(Object obj) {
		if (this==obj) {
			return true;
		}	
			person se = (person) obj;
			return this.name.equals(se.name)&&this.age==se.age;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
~~~

### 3.2 TreeSet

#### 3.2.1Comparable接口的学习

~~~properties
Exception in thread "main" java.lang.ClassCastException: sourceCode.person cannot be cast to java.lang.Comparable
	at java.util.TreeMap.compare(Unknown Source)
	at java.util.TreeMap.put(Unknown Source)
	at java.util.TreeSet.add(Unknown Source)
~~~

这个异常对于学习TreeSet的我是一脸懵逼啊,啥啥啥啊,就报了个异常,再看看我这代码没有错啊,编辑器也没有报错啊!

~~~java
package sourceCode;

import java.util.Set;
import java.util.TreeSet;

public class treeSetLearn {
	public static void main(String[] args) {
		Set ssf = new TreeSet();
		ssf.add(new person(12, "hfh"));
		ssf.add(new person(19, "frelon"));
		ssf.add(new person(13, "jokoe"));

	}
}
~~~

遂百度,知,需要**person** 类 **implements** **comparable** 接口并 **Overwrite** 该接口的 **CompareTo()** 方法

~~~java
package sourceCode;

public class person implements Comparable {
​~~~
    略
​~~~
    方法一:只能按年龄排序,忽略了姓名
	public int compareTo(Object osi) {
		person seao = (person) osi;
		if(this.age>seao.age) {
			return 1;
		}
		if(this.age<seao.age)
			return -1;
		return 0;
	}
    
​~~~
    方法二:完美方案
    public int compareTo(Object osi){
        int temp = this.age-seao.age;
        return temp==0?this.name.compareTo(osi.name):temp;
    }
~~~

按照方法二改完以后,成功按照年龄(从小到大)排序,并且同姓名,同年龄算一个人,不存进去









