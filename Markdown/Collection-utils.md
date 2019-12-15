# Collection框架的工具类

## 1. Collections

### 1.1.1  sort 按照自然顺序排序

~~~java
package sourceCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collections_utils {
	public static void main(String[] args) {
		List<String> se = new ArrayList<String>();
		se.add("fdw");
		se.add("jokeme");
		se.add("dfipo");
		se.add("daase");
		Collections.sort(se);
		System.out.println(se);
			}
}
​~~~~~~~~~~~~~~~~~~~~~
[daase, dfipo, fdw, jokeme]
~~~

用法很简单: **Collections.sort(List)** 

### 1.1.2   reverse倒序排列自然排序的List

~~~java
Collections.reverse(se);
System.out.println(se);
​~~~~~~~~~~~~~~~~
[jokeme, fdw, dfipo, daase]
~~~

用法也很简单: **Collections.reverse(List)**

如果单独使用时,其效果和 **reverseOrder()** 作用一样

### 1.1.3  自定义排序方法

~~~java
Collections.reverseOrder(Comparator<T> cmp())
~~~

自定义排序方法时就需要使用 **reverseOrder()** 并且需要自定义一个比较方法 👋 传进去

~~~java
Collections.sort(se, Collections.reverseOrder(new compareByLength()));
System.out.println(se);
​~~~~~~~~~~~~~~~
[fdw, daase, dfipo, jokeme]
​~~~~~~~~~~~~~~~
package sourceCode;

import java.util.Comparator;

public class compareByLength implements Comparator<String> {
	@Override
	public int compare(String str1,String str2) {
		int temp = str2.length() - str1.length();
		return temp==0?str2.compareTo(str1):temp;
	}
}
~~~



**上面我们可以看出 reverseOrder() 就是万金油,不传参就是倒序排列自然排序的 List , 而传参就可以自定义我们需要的比较方法 <  注意reverseOrder要联合sort使用,只有reverse可以单独使用 > **

## 2 将非同步集合转为同步集合

 