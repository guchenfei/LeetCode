package javademo.proxy.demo1;

public class Sales implements IPerson
{
    @Override
    public void say(String what) {
        System.out.println("我是商人");
    }

    @Override
    public void walk(String where) {
        System.out.println("商人走路");
    }
}
