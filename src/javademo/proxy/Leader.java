package javademo.proxy;

public class Leader implements IPerson{
    IPerson iPerson;

    public Leader(IPerson iPerson) {
        this.iPerson = iPerson;
    }

    @Override
    public void say(String what) {
        System.out.println("Leader say --");
        iPerson.say(what);
    }

    @Override
    public void walk(String where) {
        iPerson.walk(where);
    }
}