# 代理模式
```java
public interface SaleCar {
    public void Sale();
}
```

```java
public class Saler implements SaleCar {
    @Override
    public void Sale() {
        System.out.println("I sale car");
    }
}
```

```java
public class ProxyCompany implements SaleCar {

    private Saler saler;

    public ProxyCompany(Saler saler) {
        this.saler = saler;
    }

    public ProxyCompany() {
    }

    @Override
    public void Sale() {
        saler.Sale();
    }

    public Saler getSaler() {
        return saler;
    }

    public void setSaler(Saler saler) {
        this.saler = saler;
    }

}
```

```java
package top.jokeme.demo;

public class Shopper {
    public static void main(String[] args) {
        ProxyCompany company = new ProxyCompany(new Saler());
        company.Sale();
        company.getSaler().Sale();

    }
}
```

以上就是一个简单的代理模式，

interface：**SalaCar** 是卖家和代理公司的共同目的。

class：Saler & ProxyCompany 都需要实现 **SalaCar** 接口的`Sale()`方法。以达到共同的目的：卖车。
咱们目前这个公司还是比较良心的，没有手续费，最终都是卖家和买家直接交易，买家也可以轻松获取到卖家的信息。

calss：Shopper 只需要 `new ProxyCompany` 找他们然后提供一个卖家信息，让代理公司联系卖家。感觉这个买家可能是脑子有点不太好使，为什么不自己去联系呢！

---

```java
public interface SaleCar {
    public void Sale();
}
```

```java
public class Saler implements SaleCar {
    @Override
    public void Sale() {
        System.out.println("I sale car");
    }
}
```


```java
public class ProxyCompany implements SaleCar {
    Saler saler = new Saler();
    @Override
    public void Sale() {
        saler.Sale();
        System.out.println("ProxyCompany sala the car");
    }
}
```

```java
public class Shopper {
    public static void main(String[] args) {
        ProxyCompany company = new ProxyCompany();
        company.Sale();
    }
}
```
下面这个才是真正的代理公司，它在中间帮卖家卖车，买家也不需要提供任何卖家的信息就可以买到车了。非常合理。


那么问题来了，这种静态代理是很方便，但是如果两个人同时租房子，就需要在 **ProxyCompany** 再 `new` 一个房东，这种方法也不是好方法

因为耦合性太高了，需求变了，相应的就需要大量改动代码，所以我们现在需要的是动态代理。

好像我上面的这种由代理来new 对象的做法不是静态代理。F**K

静态代理是：代理：private xxx类 xxx变量名。然后还是需要传进来一个实体类来调用这些方法，就像最开始的那个例子。

## 重点：

说实话，代理的真谛就是：有一个类，但是这个类的某一些方法并不是我想要的，我就要写一个方法来接收这个类，并且完成我需要的操作以后，在加一些操作，比如说
我原来的操作是：给库存商品数量减一，但是我现在需要把这个操作写入日志里面，我就在代理的那一层，完成库存减一的操作以后，再加一个写日志的操作。然后我以后直接调用代理就可以了。所以说，**没有什么问题是加一层解决不了的**







[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P17 静态代理模式 ](https://www.bilibili.com/video/BV1WE411d7Dv?p=17)


