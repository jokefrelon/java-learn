# Java-IO💻

Java Input-Output 主要是用于文件的存储和读取,相关操作一般都会放在Java.io包中

## 1 File类

file类的方法是我们操作文件必不可少的,是必须要学习的类

### 1.1 使用示例:

~~~java
package Java_IO;

import java.io.File;
import java.io.IOException;

public class fileClass {
	public static void main(String[] args) throws IOException {
		
		File se = new File("D:\\Frelon.txt");
		File we = new File("D:\\","sda.py");
		System.out.println(se+"~~~~"+we);
		
		File dir = new File("D:\\KW"+File.separator);
		File sdCard = new File (dir,"a.java");
		if(sdCard.createNewFile()) {
			System.out.println("Create File Successfully ! "+sdCard);
		}else {
			System.out.println("Filed!");
		}
	}
}
~~~



### 1.2 **File**类常见方法

#### 1.2.1 获取文件路径

~~~java
package Java_IO;

import java.io.File;

public class filePath {
	public static void main(String[] args) {
		File file = new File("a.sh");
		String str = file.getAbsolutePath();
		String str1 = file.getPath();
		System.out.println(str+"~~~~~"+str1);
	}
}
​~~~~~~~~~~~~~~~~~~
D:\github\Hadoop_build\JavaLearn\a.sh~~~~~a.sh
~~~

getPath()只是获取相对路径<程序在哪运行,路径就是那里>

getAbsolutePath()表示获取绝对路径

#### 1.2.2 获取文件修改时间

~~~java
package Java_IO;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class filePath {
	public static void main(String[] args) {
		File file = new File(".classpath");
		Long lon = file.lastModified();
		Date date = new Date(lon);
		String mod_time = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG).format(date);
		System.out.println(lon+" >>>>> "+mod_time);
	}
}
~~~

~~~log
1575876750643 >>>>> 2019年12月9日 下午03时32分30秒
~~~

该方法并不困难,稍稍有难度的是如何把获取到的 **Long** 类型的数字转化为具体的时间

~~~java
Date date = new Date(1575876750643l);
DateFormat time = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
String str = time.format(date);
System.out.println(str);
~~~



#### 1.2.3 常见操作

~~~Java
boolean boof = file.createNewFile();     
boolean boo  = file.canExecute();        
boolean booa = file.canRead();           
boolean bood = file.canWrite();          
boolean booh = file.exists();            
boolean booj = file.isAbsolute();        
boolean book = file.isDirectory();       
boolean bool = file.isFile();            
boolean booz = file.isHidden();          
~~~

认识即可,没有难度

#### 1.2.4 获取文件夹内容

当操作文件夹时,肯定要知道这个文件夹里面有什么东西,我们才好操作,常用的方法有两种 **list()** , **listFile()** 但是建议用后者,因为**list()** 有的它也有,他有的方法**list()**没有

~~~Java
package Java_IO;

import java.io.File;

public class visitFile {
	public static void main(String[] args) {
		File alean = new File("c:\\");
		if (alean.exists()) {
			String[] dd = alean.list();
			for (String ss : dd) {
				System.out.println(ss);
			}
System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
			File[] fo = alean.listFiles();
			for (File fs : fo) {
				if(fs.getName().contains("Logs")) {
					System.out.println(fs+" ~~~ "+fs.getName());
				}
			}
System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
			Long sis = alean.getFreeSpace();
			System.out.println(alean + " " + sis / Math.pow(1024, 3) + "Gb" + " free!");
		} else {
			System.out.println("No this file");
		}
	}
}
~~~



#### 1.2.5 文件过滤器

~~~Java
File[] str1  =  dir.listFiles(new fileFilterByName());
for (File strr : str1) {
	System.out.println(strr);
}
​~~~~
package Java_IO;

import java.io.File;
import java.io.FileFilter;

public class fileFilterByName implements FileFilter {
	@Override
	public boolean accept(File arg0) {
		return arg0.isDirectory();
	}
}
~~~

它可以过滤出该📂内的📂,还可以过滤出📂内的📄,只需要把 **fileFilterByName** の **accept()** 方法改一下即可

~~~java 
public boolean accept(File arg0) {
	return arg0.isDirectory();
//	return arg0.isFile();
//  return arg0.isFile();
//  return arg0.getName().endSWith(".java")
}
~~~



##### 1.2.5.1 文件名过滤器

~~~Java
package Java_IO;

import java.io.File;
import java.io.FilenameFilter;

public class filterByName implements FilenameFilter {
	private String se="";
	
	public filterByName(String se) {
		super();
		this.se = se;
	}
	public filterByName() {
		super();
	}
	
	@Override
	public boolean accept(File arg0, String arg1) {
		
		return arg1.endsWith(se);
	}

}
​~~~~~~~~~~~~~~~~~~~~~~
package Java_IO;

import java.io.File;

public class FileFilter {
	public static void main(String[] args) {
		File file = new File("D:\\hadoop-2.8.5");
		String[] str = file.list(new filterByName(".txt"));
		for (String name : str) {
			System.out.println(name);
		}
	}
}

~~~

这种过滤器不常用,因为需要一个过滤器实现**FileNameFilter**, 实际coding 我们可以使用**FileFilter** 替代,它不仅仅可以过滤文件夹📂还可以过滤文件📃

### 1.3 递归 获取📂内的内容

~~~java
package Java_IO;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class getAllFiles {

	public static void main(String[] args) throws IOException {
		File dir = new File("D:\\github");
		getAllFile(dir);
	}

	public static void getAllFile(File se) throws IOException {
		File[] dir1 = se.listFiles();
		if(!se.exists()) {
			throw new NoSuchFileException("No such Path");
		}
		for (File der : dir1) {
			if (der.isDirectory()) {
				getAllFile(der);
			} else {
				System.out.println("Visited " + der);
			}
		}
	}
}
~~~

什么是递归?

其实 递归就是在方法内部调用该方法,该方法不断地进栈,在使用递归时,一定要有判断条件,否则一直递归下去会导致内存溢出

~~~java
public void met(){
	void show();
}

public void show(){
	void met();
}
~~~

这就是一种递归,但是它是错误❌的递归,两个方法相互调用会导致栈内存溢出,



~~~java
public void show(){
	void show();
}
~~~

这也是一种错误❌的递归,方法内部无线调用自己✌会导致栈内存溢出

#### 1.3.1 使用递归删除📂





