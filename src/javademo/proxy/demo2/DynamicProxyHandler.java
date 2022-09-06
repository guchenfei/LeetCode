package javademo.proxy.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类，实现InvocationHandler接口，构造函数传入被代理的对象
 */
public class DynamicProxyHandler implements InvocationHandler {
    // 被代理的对象
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 执行代理对象原来的方法之前，加入任何自己想要的动作
        System.out.println("proxied = " + proxied.equals(proxy));
        System.out.println("==== 代理方法接收的参数是：" + args[0]);
        args[0] = "苹果";
        System.out.println(" ==== 改为：" + args[0]);
        // 执行代理对象原来的方法
        return method.invoke(proxied, args);
    }
}


