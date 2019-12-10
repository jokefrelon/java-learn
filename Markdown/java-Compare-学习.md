# **equals() && contains()**

Java中常见的比较有**equals**和**hashcode**

今天在学习的时候就遇到了一个头疼的问题(菜鸟级的我有大大疑问)

在比较两个对象内的元素时,无论是使用**equals**还是**hashcode**都始终得不到我想要的结果.遂百度,记此文

那么这两种方法到底有什么区别呢?

equals()方法是根类Object的默认方法,查看Object中equals()的默认实现:
~~~java
public boolean equals(Object obj) {
    return (this == obj);
    }
~~~

可以看出没有重写过的equals()方法和==是一样的，都是比较两个对象引用指向的内存地址是否一样判断两个对象是否相等。

也就是说,基本上每次都要**Overwrite**这个方法

 **hashCode**是**JDK**根据对象的地址或者字符串或者数字计算该对象的哈希码值的方法。 

**hashcode** 和 **equals** 两者必须同时重写。 

