package javademo.proxy.demo2;

public class People implements IAction {
    private String name = "小明";

    @Override
    public void eat(String food) {
        System.out.println(name + "吃" + food);
    }
}


