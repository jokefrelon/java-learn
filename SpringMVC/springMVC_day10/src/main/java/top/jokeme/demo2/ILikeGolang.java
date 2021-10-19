package top.jokeme.demo2;

public class ILikeGolang implements golang{
    @Override
    public void GolangYes() {
        System.out.println("Go 是一种非常棒的语言");
    }

    @Override
    public void GolangFirst() {
        System.out.println("我在开发中会优先考虑Go语言");
    }

    @Override
    public int GolangFrameWork(int a) {
//        System.out.println("Go的框架灰常的多------------------");
        int ss = a*2;
        System.out.println("当前没有被修改的返回值为："+ ss+" -@@@@@@@@@@@@@@@@@@@@\n");
        return ss;
    }
}
