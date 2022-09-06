package javademo.proxy.demo1;

public class HookUser {
    IPerson iPerson ;
    public HookUser(){
        iPerson = new Sales();
    }
    public void doWhat(){
        iPerson.say("自定义say");
    }
}
