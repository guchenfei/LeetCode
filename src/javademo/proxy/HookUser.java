package javademo.proxy;

public class HookUser {
    IPerson iPerson ;
    public HookUser(){
        iPerson = new Sales();
    }
    public void doWhat(){
        iPerson.say("自定义say");
    }
}
