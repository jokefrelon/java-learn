我的Hadoop学习笔记.所学资料来自网络,贡献于开源
Frelon - Lee
发现了一句非常厉害的 **List<Map<String,String>>** 转 JSON语句

~~~java
String jsonData = list.toString().replace("{", "{\"").replace("}", "\"}").replace("=","\":\"").replace(", ", "\",\"").replace("}\",\"{", "},{");
~~~

