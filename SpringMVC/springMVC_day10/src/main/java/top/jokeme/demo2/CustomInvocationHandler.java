package top.jokeme.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CustomInvocationHandler implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object creaetProxy(){
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target,args);
    }

    int betterGolangFrameWork (int a){
       golang go =  (golang) target;
       int re = go.GolangFrameWork(a);
//       System.out.println("当前GolangFrameWork 的返回值是 ：" + re );
        System.out.println("现在我们对当前GolangFrameWork做了一些优化，它的最新返回值是："+ (re-1) );
       return re-1;
    }
}
