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

~~~java
package Java_IO;

import java.io.File;
import java.nio.file.NoSuchFileException;

public class deleteFiles {
	public static void main(String[] args) throws NoSuchFileException {
		File dir = new File("D:\\KWS");
		removeFiles(dir);
	}
	public static void removeFiles(File se) throws NoSuchFileException {
		File [] wow = se.listFiles();
		for(File wo : wow) {
			if(!wo.exists()) {
				throw new NoSuchFileException(null);
			}
			if(wo.isDirectory()) {
				removeFiles(wo);
			}else {
				Boolean esc = wo.delete();
				System.out.println("delete the file "+wo+" "+esc);
			}
		}
		Boolean esc = se.delete();
		System.out.println("delete the file "+se+" "+esc);
	}
}
~~~

递归删除很简单,只是需要注意,这个删除是不经过回收站的,会被直接删除,不可找回

还有就是需要判断该 📂有没有权限访问,要不然会报 **java.lang.NullPointerException** 异常,甚至还需要我们自己来抛一些 **IO** 异常

### 1.4 向📄内写入内容

~~~java
package Java_IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class writeIntoFile {
	private static final String Line_separator = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException {
		File dir = new File("c:\\tempfile");
		if(!dir.exists()) {
			dir.mkdir();
		}
		String str ="i Love Java";
		FileOutputStream fops = null;
		try {
			fops = new FileOutputStream("c:\\tempfile\\fops.java",true);
			String ssr = Line_separator+"Hello World"+Line_separator;
			fops.write(ssr.getBytes());
			fops.write(str.getBytes());
			fops.write(ssr.getBytes());
			System.out.println("写入成功");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fops!=null) {}
			fops.close();
		}
	}
}
~~~

写入内容很容易,只需要调用一下**write()**方法即可,但是难点就是处理各种**Exception** ,我们需要考虑到各种各样的异常

比如 FileNotFoundException,,,NullPointerException等等等, 因为一旦出现了这些问题我们的代码可能就占用了相关的系统资源,但是程序执行失败后,我们并没有释放该资源,如此循环往复就会导致系统的卡慢,甚至死机重启,这里我们经常使用**try catch finally** 方法来解决,需要执行的代码放在**try** 里面,在**finally** 里面放 **close()**方法,保证我们的代码在申请完资源以后,无论是否报错,都可以被释放掉,还有一点就是如果我们使用 **try catch finally** 那我们一定不能在**try**里面 **new** 对象,因为到时候**finally** 关闭资源时会**找不到对象** 🤣的

还有**write()** 方法是会覆盖掉文件内部的内容的,如果我们需要在当前内容上继续添加内容,那就需要在创建对象时加一个**true** 表示续写该文件,代码如下

~~~java
FileOutputStream fops = new FileOutputStream("c:\\tempfile\\fops.java",true);
~~~

### 1.5 前4 part练习

需求:

~~~
获取某个📂内所有文件的集合
获取该📂内某个📂内的.txt文件,并存储到集合中打印🖨出来
~~~

~~~java
package Java_IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class IO_Test1 {
	public static void main(String[] args) {
		File dir = new File("c:\\tempfile");

		List<String> list = new ArrayList<String>();

		fileFilterByName filter = new fileFilterByName(".txt");

		getFile(dir, filter, list);

		for (String ser : list) {
			System.out.println(ser);
		}
	}

	private static void getFile(File file, fileFilterByName filter, List<String> list) {
		File[] fileName = file.listFiles();
		for (File se : fileName) {
			if (se.isDirectory()) {
				getFile(se, filter, list);
			} else {
				if (filter.accept(se)) {
					list.add(se.getPath());
				}
			}
		}
	}
}
​~~~~~~~~~~~~~~~~~~~~
package Java_IO;

import java.io.File;
import java.io.FileFilter;

public class fileFilterByName implements FileFilter {
	private String str;
    
	public fileFilterByName(String str) {
		super();
		this.str = str;
	}
    
	public fileFilterByName() {
		super();
	}
	
	
	@Override
	public boolean accept(File arg0) {
		return arg0.getName().endsWith(this.str);
	}

}
~~~

给自己点个赞👍,我真厉害🥰