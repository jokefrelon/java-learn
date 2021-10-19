package top.jokeme.demo1;

public class Shopper {
    public static void main(String[] args) {

        //  真实角色
        Saler saler = new Saler();

        //  代理角色
        ProxyInvocationHandler handler = new ProxyInvocationHandler();

        //  将我们自己new的角色传给ProxyInvocationHandler来处理
        handler.setCarSale(saler);

        //  调用前面 "生成代理" 的方法
        CarSale proxy = (CarSale) handler.getProxy();

        proxy.Sale();
    }
}
