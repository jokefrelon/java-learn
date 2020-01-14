# Java 注解（Annotation）

Java注解其实并不难，从名字上你都知道注解是帮助Java代码执行的，它的形式跟接口很类似，不过前面多了一个 @ 符号

## 1 Java元注解

- @Retention

  Retention 保留期，决定注解的生命时常，取值如下

  |   SOURCE    |         只在源码阶段保留         |
  | :---------: | :------------------------------: |
  |  **CLASS**  |   **只被保留到编译进行的时候**   |
  | **RUNTIME** | **注解可以保留到程序运行的时候** |

  eg：该注解被保留到运行时

  ~~~java
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Annotation {
  }
  ~~~

  <hr>

- @Documented

  它的作用是能够将注解中的元素包含到 Javadoc 中去


  <hr>

- @Target

  Target 指定了注解运用的地方

  | **ANNOTATION_TYPE** |        **给一个注解进行注解**        |
  | :-----------------: | :----------------------------------: |
  |   **CONSTRUCTOR**   |        **给构造方法进行注解**        |
  |      **FIELD**      |          **给属性进行注解**          |
  | **LOCAL_VARIABLE**  |        **给局部变量进行注解**        |
  |     **METHOD**      |          **给方法进行注解**          |
  |     **PACKAGE**     |           **给包进行注解**           |
  |    **PARAMETER**    |      **给方法内的参数进行注解**      |
  |      **TYPE**       | **给一个类型进行注解，比如类、接口** |
  <hr>

- @Inherited

  一个类被 @Inherited 注解过的注解进行注解的话，如果它的子类没有被任何注解应用的话，那么这个子类就继承了该类的注解。

  <hr>

- @Repeatable

是可重复的意思

## 2 Java内置注解
- @Deprecated
这个元素是用来标记过时的元素
- @Override
子类要复写父类中的方法
- @SuppressWarnings
忽略编辑器的警告⚠
- @SafeVarargs
参数安全类型注解。它的目的是提醒开发者不要用参数做一些不安全的操作,
它的存在会阻止编译器产生 unchecked 这样的警告。
- @FunctionalInterface
函数式接口注解，函数式接口可以很容易转换为 Lambda 表达式

## 3 注解与反射。

注解通过反射获取。首先可以通过 Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解
~~~java
public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {}
~~~

然后通过 getAnnotation() 方法来获取 Annotation 对象。

~~~java
public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {}
~~~

或者是 getAnnotations() 方法。
~~~java
public Annotation[] getAnnotations() {}
~~~

前一种方法返回指定类型的注解，后一种方法返回注解到这个元素上的所有注解。

如果获取到的 Annotation 如果不为 null，则就可以调用它们的属性方法了

## 4 注解应用实例

**JUnit**

~~~java
public class JUnitTest {
    @Test
    public void solo(){
        System.out.println("Awesome Junit");
    }
}
~~~

