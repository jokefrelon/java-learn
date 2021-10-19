package top.jokeme.demo2;

public class aMan {
    public static void main(String[] args) {
        ILikeGolang golang = new ILikeGolang();

        CustomInvocationHandler handler = new CustomInvocationHandler();

        handler.setTarget(golang);

        golang proxy = (golang) handler.creaetProxy();

        proxy.GolangYes();
        proxy.GolangFrameWork(3);
        handler.betterGolangFrameWork(3);
        proxy.GolangFirst();
    }
}
