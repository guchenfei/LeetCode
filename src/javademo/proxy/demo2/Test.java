package javademo.proxy.demo2;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
//        System.out.println("-----正常操作-----");
        //normal();
        System.out.println("-----hook代理-----");
        hookInterface();
//        System.out.println("-----hook属性-----");
        //hookAttr();
    }


    /**
     * 正常操作
     */
    public static void normal() {
        People people = new People();
        people.eat("梨子");
    }

    /**
     * hook方法
     */
    public static void hookInterface() {
        People people = new People();
        IAction iAction = (IAction) Proxy.newProxyInstance(
                IAction.class.getClassLoader(),
                new Class[]{IAction.class},
                new DynamicProxyHandler(people));
        // 虽然传递的是"梨子"，但hook过程被修改了
        iAction.eat("梨子");
    }

    /**
     * hook属性
     */
    public static void hookAttr() {
        try {
            System.out.println(" ==== hook属性，狸猫换太子,小明变小强");
            People people = new People();
            Field field = People.class.getDeclaredField("name");
            field.setAccessible(true);
            field.set(people, "小强");
            people.eat("梨子");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
