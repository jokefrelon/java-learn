# Java-IO💻 Part 1

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

### 1.4 前三 part 测试

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

## 2 FileOutputStream 向📄内写入内容

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

## 3 FileInputStream 读取📄内容

FileInputStream类和FileOutputStream类有许多的共同点,都需要传一个File类的对象(new 对象时要保证Path✔无误,要不然很容易造成空指针异常) 还有就是在使用完资源以后要及时释放掉资源,还有要注意的是异常の处理,如果没有处理好异常也会导致我们无法释放掉资源,导致程序占用过多的系统资源,使系统卡慢

### 3.1❌read() 入门级读取

~~~java
package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readFile {
	private static final int LIMSize = 8192;

	public static void main(String[] args) throws IOException {
		File dir = new File("c:\\tempfile\\fops.java");

		FileInputStream fipts = new FileInputStream(dir);

		int byt;
		while ((byt = fipts.read()) != -1) {
			System.out.println(byt);
		}

		fipts.close();
	}
}        
~~~

日常开发中,基本上用不上 PASS ❌

### 3.2❌read() 初级读取

~~~java
package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readFile {
	private static final int LIMSize = 8192;

	public static void main(String[] args) throws IOException {
		File dir = new File("c:\\tempfile\\fops.java");
		byte[] buf = new byte[2];

		FileInputStream fip = new FileInputStream(dir);

		int len = fip.read(buf);
		System.out.println(len + "~~~" + new String(buf));

		fip.close();
	}
}            
~~~

日常开发中,基本上用不上 PASS ❌

### 3.3 ✔ read() 进阶级读取

~~~java
package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readFile {
	private static final int LIMSize = 8192;

	public static void main(String[] args) throws IOException {
		File dir = new File("c:\\tempfile\\fops.java");
				FileInputStream fips = new FileInputStream(dir);
		byte[] by = new byte[LIMSize];

		int length = 0;

		while ((length = fips.read(by)) != -1) {
			System.out.println(new String(by, 0, length));
		}
		fips.close();

	}
}
~~~

日常开发中可以使用,✔建议根据需要改变缓冲区的大小,一般建议设置成 :8192

### 3.4 ✔ read()高级读取

~~~java
package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readFileAdvantage {
	public static void main(String[] args) throws IOException {
		File dir = new File("c:\\tempfile\\fops.java");
		FileInputStream fips = new FileInputStream(dir);
		byte [] byt = new byte [fips.available()];
		fips.read(byt);
		System.out.println(new String(byt));
		fips.close();
	}
}
~~~

✔这种是我比较喜欢的一种方法,简洁好用,也不用考虑缓冲区大小😀,而且一般File对象没有问题也就不会出错啊(相对于前面几个),这是重点✔,圈起来!下次会考!!!

但是!!但是!!但是! 这种方法只是比较适合处理比较小的文件,如果处理好几个G的文件,当场挂..所以呢,还是 进阶 和 高级 一起用

## 4 FileOutputStream和FileInputStream一起读写文件

### 4.1 低级写法

~~~java
package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IO_CopyFile {

	public static void main(String[] args) throws IOException {
		File dir = new File("C:\\tempfile\\fops.java");
		FileInputStream fis = new FileInputStream(dir);

		File dir1 = new File("C:\\tempfile\\fops.txt");
		FileOutputStream fos = new FileOutputStream(dir1);
		int len = 0;
		while ((len = fis.read()) != -1) {
			fos.write(len);
		}
		fis.close();
		fos.close();
	}
}
~~~

不推荐这种写法,因为效率比较低,读取一个写入一个,太浪费时间

### 4.2 高级写法

~~~java
package Java_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import APICourceCode.dateAPI;

public class copyFileByBuf {
	public static void main(String[] args) throws IOException {
		dateAPI date = new dateAPI();
		date.getDate();
		File dir = new File("d:\\tempfile\\idea.exe");
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(dir);
			fos = new FileOutputStream("d:\\tempfile\\idea.wilu");

			byte[] buf = new byte[10240];

			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
		} catch (IOException e) {
			throw new IOException();
		} finally {
			fis.close();
			fos.close();
			date.getDate();
			System.out.println("Copy Over");
		}

	}
}

~~~

我无聊就试了试,这个**byte** 数组の长度对**IO** の性能影响有多大,就把长度从10,102,1024,2048,4096,8192,10240 都试了个遍,得出以下答案:

| 时间\byte长度 |    10    |   102    |   1024   |   2048   |   4096   |   8192   |  10240   |  20480   |
| :-----------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
|     Start     | 19:38:27 | 19:53:42 | 19:54:52 | 19:55:50 | 19:56:25 | 19:57:05 | 19:57:40 | 19:58:16 |
|     Stop      | 19:44:33 | 19:54:19 | 19:54:57 | 19:55:54 | 19:56:28 | 19:57:08 | 19:57:42 | 19:58:18 |
|     time      |   366s   |   37s    |    5s    |    4s    |    3s    |    3s    |    2s    |    2s    |

测试所使用的文件大小为: 584935KB 大约是571MB

可以看出byte数组的长度也是越大越好的,只要你内存足够大,磁盘性能足够强,理论上是可以做到一秒内复制任何文件的,可是!! 科技日益发达的今天电脑内存和磁盘还依然是一个瓶颈,有待我们突破啊,So 为了适应各种大小的文件,还是选择大小为**8192** 或者**10240** 也是可以的



## 5 Buffer缓冲区

buffer原理就是调用一片内存,作为缓冲区,然后再为FileInput/OutputStream使用,原理和上面的相似

### 5.1 使用示例

~~~java
package Java_IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class buffer {
	public static void main(String[] args) throws IOException {
				copyfile();
	}
	public static void copyfile() throws IOException {
		File dir  = new File("d:\\LinuxSoftware_VariedOS\\Python-3.8.0.tgz");
		
		FileInputStream pis = new FileInputStream(dir);
		FileOutputStream pos = new FileOutputStream("d:\\tempfile\\Python.wilu");
		
		BufferedInputStream bufpis = new BufferedInputStream(pis);
		BufferedOutputStream bufpos = new BufferedOutputStream(pos);
		
		byte [] buf = new byte[8192];
		int len = 0;
		while((len=bufpis.read(buf))!=-1) {
			bufpos.write(buf,0,len);
		}
		bufpis.close();
		bufpos.close();
		System.out.println("Copy over");
	}  
}
~~~



## 6 字符读写

### 6.1 使用 I/O-Stream-Reader/Writer

由于中文一个字符占两字节,所以我们不能像读写英文那样,直接操作数据了,

~~~java
package Java_IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StreamReader {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("D:\\tempfile\\fpos.java");
		FileOutputStream fos = new FileOutputStream("D:\\tempfile\\pofs.c",true);
		
		InputStreamReader isr = new InputStreamReader(fis);
		OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
		
		int in ;
		while((in = isr.read()) != -1) {
			osw.write(in);
		}
		
		isr.close();
		osw.close();
		System.out.println("Copy over");
	}
}
~~~

中文处理时比较的麻烦,因为各种电脑支持的编码可能不一样,就导致乱码问题,这时候我们在**new** 对象的时候就需要指定我们所需要的编码方式,今天就因为编码就倒腾了半天的时间⏰

所以为了保险起见,在创建 **FileOutputStream** 的时候还是加上 **UTF-8** 好一点

### 6.2 FileReader/FileWriter类

**FileReader/FileWriter** 其实就是简化了**FileOutputStream** ＆ **FileOutputStream** の 使用过程,但是缺点( •̀ ω •́ )y 非常明显,就是不支持设置编码方式,默认的是 **ISO-8859-1 or US-ASCII** ,非常容易导致乱码 ~%?…,# *'☆&℃$︿★?

处理乱码也不难,就是不用这个类 🤣

way 1 

~~~java
BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (filePath,true),"UTF-8"));
~~~

虽然这也是一种解决办法吧,但是如果一点要用的话那就只能用下面这种方法了

way 2

~~~java
Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream ("String"), "UTF-8"));
~~~

让**FileWriter** 继承 **Write** の 编码方式,不过也不好用



#### 6.2.1 FileReader /  FileWrite 初级写法

~~~java
package Java_IO;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class fileWriter {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("D:\\tempfile\\pofs.c");
		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
		
		FileReader fr = new FileReader("D:\\tempfile\\fpos.java");
		FileWriter fw = new FileWriter("D:\\tempfile\\pofs.c");
		
		int in;
		while((in=fr.read())!=-1) {
			fw.write((char)in);
		}
		fr.close();
		System.out.println(fw.getEncoding());
		fw.close();
		osw.close();
		System.out.println("Copy over");
	}
}
~~~






#### 6.2.2 FileReader /  FileWrite 高级写法
~~~java
package Java_IO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO_SimplifyChinese {
	public static void main(String[] args) throws IOException {
		copyChineseChar();
	}
	public static void copyChineseChar() throws IOException {
		FileReader fr = new FileReader("D:\\LinuxSoftware_VariedOS\\jdk-8u231-linux-x64.tar.gz");
		FileWriter fw = new FileWriter("D:\\tempfile\\jdk8.wuli");
		
		char [] chara = new char[2048];
		int len ;
		while ((len=fr.read(chara))!= -1) {
			fw.write(chara,0,len);
		}
		fr.close();
		fw.close();
		System.out.println("copy over");
	}
}
~~~



### 6.3 BufferedReader / BufferedWriter



















































































## Final	写在最后:

在经过学习后,我发现字节流和字符流都用于复制文件,但字符流的出现主要是为了解决与字符相关的问题,所以不适合复制文件(编码原因),字符流复制文件时可能会出现莫名其妙的问题导致复制后的文件破损无法使用,但字节流就不存在这种问题所以得出

~~~properties
复制文件时使用字节流:复制文本文件时使用字符流
~~~

