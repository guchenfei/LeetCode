package javademo.proxy;

public class Worker implements IPerson {
    @Override
    public void say(String what) {
        System.out.println("Worker Say. " + what);
    }

    @Override
    public void walk(String where) {
        System.out.println("Worker walk. " + where);
    }
}
