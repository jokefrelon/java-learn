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

## 3. set集合

~~~properties
set--:不允许重复元素,方法和collection相同.set集合只能用Iterator取出元素
	|--hashset:不保证存入和取出的顺序一致,不允许重复,效率高 
	|--treeset:
~~~



















