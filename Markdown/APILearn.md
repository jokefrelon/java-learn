# 时间类API

在日常学习生活中,我们经常遇到时间相关的问题,现在虽然Date类已经不是主流了,但任然有许多方法要学习

## 1.1DateFormat

### 1.1.1 使用示例

~~~java
package APICourceCode;

import java.text.DateFormat;
import java.util.Date;

public class dateAPI {
	public static void main(String[] args) {
		Date date = new Date();
		DateFormat ses = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		String sesa = ses.format(date);
		System.out.println(sesa);
		
	}
}
~~~

其常见的**Style**如下

```properties
FULL:2019年12月16日 星期一 下午06时38分17秒 CST
LONG:2019年12月16日 下午06时39分20秒
MEDIUM:2019-12-16 18:39:41 (默认方式)
SHORT:19-12-16 下午6:40
```

如果需要自定义的格式,就需要按照下面的表格自定义,自定义以后,系统自带的**Style**将不生效

| 字母 |           意义            |
| :--: | :-----------------------: |
|  y   |            年             |
|  M   |           月份            |
|  w   |       年份中的周数        |
|  W   |       月份中的周数        |
|  d   |       月份中的天数        |
|  D   |       年份中的天数        |
|  F   |       月份中的星期        |
|  E   |       星期中的天数        |
|  a   |      am/pm表示上下午      |
|  H   |   一天中的小时数(0-23)    |
|  h   | am/pm中的一天小时数(1-12) |
|  m   |      小时中的分钟数       |
|  s   |       分钟中的秒数        |
|  S   |          毫秒数           |
|  z   |        PST;GMT时区        |

~~~java
package APICourceCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateAPI {
	public static void main(String[] args) {
		Date date = new Date();
		DateFormat ses = DateFormat.getDateTimeInstance();
		ses = new SimpleDateFormat("yyyy/MM/dd HH-mm-ss-SS");
		String sesa = ses.format(date);
		System.out.println(sesa);
		
	}
}
~~~

### 1.1.2总结:

**DateFormat**方法其实很简单,

①首先**new**一个**Date**类对象,就不用管了

②调用**DateFormat**方法对象(不需要new),生成自定义的**Style**

③使用字符串接收**DateFormat**对象的**Format**方法,需要传**Date**类的对象

## 1.2 DateFormat反解析

### 1.2.1使用示例:

~~~java
package APICourceCode;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class ReverseDateFormat {
	public static void main(String[] args) throws ParseException {
		String str = "2019-07-02";
		DateFormat se = DateFormat.getDateInstance();
		Date date =se.parse(str);
		System.out.println(date);
	}
}
~~~

## 2 Calendar

### 2.1 使用示例:

~~~java
package APICourceCode;

import java.util.Calendar;

public class CalenderL {
	public static void main(String[] args) {
		Calendar se = Calendar.getInstance();
		
		se.add(Calendar.YEAR, 1);
		
		int year = se.get(Calendar.YEAR);
		int month = se.get(Calendar.MONTH)+1;
		int day = se.get(Calendar.DAY_OF_MONTH);
		int hours = se.get(Calendar.HOUR_OF_DAY);
		int minute = se.get(Calendar.MINUTE);
		int sec = se.get(Calendar.SECOND);
		System.out.println(year + "-" + month + "-" + day + "-" + hours + "-" + minute + "-" + sec);
	}
}
----------
2020-12-16-20-16-53
~~~



### 2.2 常见的**Calendar**参数

~~~properties
java.util.GregorianCalendar[
time=1576496255445,
firstDayOfWeek=1,
YEAR=2019,
MONTH=11,
WEEK_OF_YEAR=51,
WEEK_OF_MONTH=3,
DAY_OF_MONTH=16,
DAY_OF_YEAR=350,
DAY_OF_WEEK=2,
DAY_OF_WEEK_IN_MONTH=3,
HOUR=7,
HOUR_OF_DAY=19,
MINUTE=37,
SECOND=35,
MILLISECOND=445]
~~~

### 2.3 日期的偏移

~~~Java
se.add(Calendar.MONTH, 1);
~~~

个人认为作用不是很大













