Java学习笔记📒
					～Frelon - Lee～

### 最近在网上发现了一句非常厉害的 **List<Map<String,String>>** 转 **JSON** 语句

~~~java
String jsonData = list.toString().replace("{", "{\"").replace("}", "\"}")
  .replace("=","\":\"").replace(", ", "\",\"").replace("}\",\"{", "},{");
~~~

### 来对比一下：

~~~json
没有转化前：
[
{User_Name=Tom_smilo, User_Mod=Unkonwn, User_IDE=132564897, User_ID=2}, 
  
{User_Name=Asimai, User_Mod=Unkonwn, User_IDE=968598489, User_ID=5}, 
  
{User_Name=jojy_bell, User_Mod=Unkonwn, User_IDE=1237181248, User_ID=1}
]
~~~

~~~json
转化后：
[
{"User_Name":"Tom_smilo","User_Mod":"Unkonwn","User_IDE":"132564897","User_ID":"2"},

{"User_Name":"Asimai","User_Mod":"Unkonwn","User_IDE":"968598489","User_ID":"5"},

{"User_Name":"jojy_bell","User_Mod":"Unkonwn","User_IDE":"1237181248","User_ID":"1"}
]
~~~

总结就是：

|    {  |  >>    |   	{"   |
| :--: | :--: | :--: |
| **=** | **>>**   |   **":"**   |
| **,**  | **>>** | **","**  |
|   **}","**   |   **>>**   | **"},{** |
|    **}]**		  |  **>>**	| **"}]** |

于是我自己 也 写了一个，发现很简单

~~~java
String jsonData = list.toString().replace("{","{\"").replace("=","\":\""). replace(",","\",\"").replace("}\",\" {","\"},{").replace("}]","\"}]");
~~~





