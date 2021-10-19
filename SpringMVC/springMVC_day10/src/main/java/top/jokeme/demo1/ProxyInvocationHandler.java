package top.jokeme.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {

    //  被代理的接口
    private CarSale carSale;

    public void setCarSale(CarSale carSale) {
        this.carSale = carSale;
    }

    //    生成代理
    public Object getProxy(){
        System.out.println("exec newProxyInstance");
        return Proxy.newProxyInstance(this.carSale.getClass().getClassLoader(),carSale.getClass().getInterfaces() ,this);
    }

    //  处理生成的代理实例，并返回。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("exec invoke");

        //  动态代理的本质就是使用反射机制
        return method.invoke(carSale,args);
    }
}
