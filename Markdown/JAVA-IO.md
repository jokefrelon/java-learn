# Java-IO

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
		File dir = new File("D:\\KW"+File.separator);
		File sdCard = new File (dir,"a.java");
		sdCard.createNewFile();
	}
}
~~~



