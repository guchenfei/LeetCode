package javademo.proxy.demo1;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.UUID;

/**
 * 通过这种代理方式,可以很轻松做到动态创建接口实现类.
 */
public class TestProxyClazz {
    interface TestInterface {
        String test01();

        String test02(String st, String st2);
    }

    static class TestProxy implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            switch (method.getName()) {
                case "test01":
                    return "invoke==>" + UUID.randomUUID().toString();
                case "test02":
                    return "invoke==>" + Arrays.asList(args);
            }
            return null;
        }
    }

    static class TestFactory {
        public static <T> T newInstance(Class<T> clazz) {
            InvocationHandler h = new TestProxy();
            return (T) Proxy.newProxyInstance(TestFactory.class.getClassLoader(), new Class[]{clazz}, h);
        }
    }

    @Test
    public void test01() {
        InvocationHandler h = new TestProxy();
        TestInterface obj = (TestInterface) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{TestInterface.class}, h);
        String res = obj.test01();
        System.out.println(res);
    }

    @Test
    public void test02() {
        TestInterface t = TestFactory.newInstance(TestInterface.class);
        String res = t.test02("111", "222");
        System.out.println(res);
    }
}
