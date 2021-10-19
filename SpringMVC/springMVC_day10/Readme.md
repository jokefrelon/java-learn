#  动态代理
在学习之前先了解一下动态代理的特点

- 动态代理类肯定是动态生成的，不是我们事先写好的
- 动态代理分为两大类：1 基于接口 2 基于类
> 1. 基于接口                 --- JDK动态代理（咱们学习这个） 
> 2. 基于类                   --- cglib 
> 3. 基于Java字节码           --- javasist 

需要了解的两个类：`Proxy` `InvocationHandler` 

```java
public interface CarSale {
    public void Sale();
}
```

```java
public class Saler implements CarSale{
    @Override
    public void Sale() {
        System.out.println("I sale car");
    }
}
```

```java
public class ProxyInvocationHandler implements InvocationHandler {

    //  被代理的接口
    private CarSale carSale;

    public void setCarSale(CarSale carSale) {
        this.carSale = carSale;
    }

    //    生成代理
    public Object getProxy(){
        System.out.println("exec newProxyInstance");
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),carSale.getClass().getInterfaces() ,this);
    }

    //  处理生成的代理实例，并返回。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("exec invoke");

        //  动态代理的本质就是使用反射机制
        return method.invoke(carSale,args);
    }
}
```

```java
public class Shopper {
    public static void main(String[] args) {

        //  真实角色
        Saler saler = new Saler();

        //  代理角色
        ProxyInvocationHandler handler = new ProxyInvocationHandler();

        //  将我们自己new的角色传给ProxyInvocationHandler来处理
        handler.setCarSale(saler);

        //  调用前面 "生成代理" 的方法 。注意：这里需要用接口接收。
        CarSale proxy = (CarSale) handler.getProxy();

        proxy.Sale();
    }
}
```


这里重难点就是 `ProxyInvocationHandler` 和 `Shopper`

因为 **ProxyInvocationHandler** 需要implements **InvocationHandler**接口，所以需要 **@Override** `invoke` 方法
这里也没有啥需要注意的，咱先这样写起来。返回值就当作固定模式写。

至于 `GetProxy()` 这个就是咱们需要重点了解的了，通过 **Proxy**类来生成代理，**newProxyInstance()** 的三个参数分别是

(ClassLoader loader, @NotNull Class<?>[] interfaces, @NotNull reflect.InvocationHandler h)

说人话就是

(接口的ClassLoader,接口的接口类型,一个InvocationHandler实现类)


---

然后 **Shopper** 这边，先需要 **new** 一个需要代理的接口的实体类，肯定也需要 **new** 一个 `ProxyInvocationHandler` 来帮我们生成代理对象。
接着把那个实体类接口传给 `ProxyInvocationHandler` ，告诉它我们需要啥样的代理。然后就是运行我们自己写的 `getProxy()` 方法
来生成代理。这里会返回一个 **Object** 类型的实体类，需要我们自己强转一下。

这样就做到了动态生成代理了。可能有点绕，但是知道了内部原理就好了，以后慢慢学。

[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P19 动态代理详解](https://www.bilibili.com/video/BV1WE411d7Dv?p=19)