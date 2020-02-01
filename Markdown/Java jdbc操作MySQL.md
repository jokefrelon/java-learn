# Java jdbc操作MySQL

## 1 . Maven导包

~~~xml
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.48</version>
</dependency>
	<groupId>org.junit.jupiter</groupId>
	<artifactId>junit-jupiter</artifactId>
	<version>RELEASE</version>
    <scope>compile</scope>
</dependency>
~~~

填在pom文件里面即可,无难度

## 2 . 用代码连接数据库

~~~java
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fre{
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String uri = "jdbc:mysql://127.0.0.1:3306/solo";
    public static final String user = "root";
    public static final String passcode = "123456";
    final String sqlScript = "select * from b3_solo_article";
    Connection connection= null;

    public ResultSet StartCollection() throws SQLException {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(uri,user,passcode);
            if(!connection.isClosed())
                System.out.println("connection successfully");
            Statement statement = connection.createStatement();
            ResultSet replyString = statement.executeQuery(sqlScript);
            return replyString;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toTheJson(List<Map> list){
        return list.toString().replace("{", "{\"").replace("}", "\"}").replace("=","\":\"").replace(", ", "\",\"").replace("}\",\"{", "},{").replace("{", "{\"").replace("}", "\"}").replace("=","\":\"").replace(", ", "\",\"").replace("}\",\"{", "},{").replace("\"\"","\"");
    }

    public void close(){
        try{

            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   @Test
   public void Solomon() throws SQLException, IOException {
       Map<String,Object> outer = new HashMap<String, Object>();
       List<Map> list = new ArrayList<Map>();
       Fre solo = new Fre();
       ResultSet so  = solo.StartCollection();
       while(so.next()){
           Map<String,Object>  map = new HashMap<String, Object>();
           map.put("User_ID",so.getString(1));
           map.put("User_Name",so.getString(2));
           map.put("User_Mod",so.getString(3));
           map.put("User_IDE",so.getString(4));
           list.add(map);
       }
       String Solo = toJson(list);
       System.out.println(Solo);
       FileOutputStream fos = new FileOutputStream("solo.json",true);
       OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
       osw.write(Solo);
       System.out.println("Write Over");
       osw.close();
   		}
    }
}
~~~

## 3 . 代码详解

~~~Java
public static final String driver = "com.mysql.jdbc.Driver";
public static final String uri = "jdbc:mysql://127.0.0.1:3306/solo";
public static final String user = "root";
public static final String passcode = "123456";
final String sqlScript = "select * from b3_solo_article";
Connection connection= null;
~~~

在定义这些常量的时候,最好不要直接定义 **String** ,而是 **public static final String** ,怎么安全,怎么来

而且对象尽量不要在方法里面 **new** ,如果发生异常就不能释放系统资源了

----------
~~~java
public ResultSet StartCollection() throws SQLException {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(uri,user,passcode);
            if(!connection.isClosed())
                System.out.println("connection successfully");
            Statement statement = connection.createStatement();
            ResultSet replyString = statement.executeQuery(sqlScript);
            return replyString;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
}
~~~

如果从安全角度来考虑的话,那么我们不应该用 **Statement** 而是用 **PreparedStatement** ,我在自己电脑上也不怕什么 **sql**注入,所以没有用

---------

~~~java
public static String toTheJson(List<Map> list){
        return list.toString().replace("{", "{\"").replace("}", "\"}").replace("=","\":\"").replace(", ", "\",\"").replace("}\",\"{", "},{").replace("{", "{\"").replace("}", "\"}").replace("=","\":\"").replace(", ", "\",\"").replace("}\",\"{", "},{").replace("\"\"","\"");
    }
~~~

主要是我没有导 **json** 相关的库,就这样简化 **List<Map<String,String>>**   >>>   **json** 主要就是字符串的替换,还有转义可能有点懵😵,小心即可

----

~~~java
public void close(){
        try{

            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
~~~

使用完资源以后肯定也要释放的,没有什么好说的

---

~~~java
@Test
public void Solomon() throws SQLException, IOException {
       Map<String,Object> outer = new HashMap<String, Object>();
       List<Map> list = new ArrayList<Map>();
       Fre solo = new Fre();
       ResultSet so  = solo.StartCollection();
       while(so.next()){
           Map<String,Object>  map = new HashMap<String, Object>();
           map.put("User_ID",so.getString(1));
           map.put("User_Name",so.getString(2));
           map.put("User_Mod",so.getString(3));
           map.put("User_IDE",so.getString(4));
           list.add(map);
       }
       String Solo = toTheJson(list);
       System.out.println(Solo);
       FileOutputStream fos = new FileOutputStream("solo.json",true);
       OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
       osw.write(Solo);
       System.out.println("Write Over");
       osw.close();
   		}
}
~~~

写完先测试一下