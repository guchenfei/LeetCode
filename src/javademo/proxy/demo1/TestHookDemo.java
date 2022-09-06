package javademo.proxy.demo1;

import java.lang.reflect.*;

public class TestHookDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<HookUser> hookUserClass = (Class<HookUser>) Class.forName("HookUser");
        Constructor<HookUser> constructor = hookUserClass.getConstructor();
        HookUser userInstance = constructor.newInstance();

        userInstance.doWhat();

        System.out.println("----------代理之前--------");
//        Constructor<?>[] constructors = hookUserClass.getDeclaredConstructors();
//        for (Constructor<?> constructor1 : constructors) {
//            constructor1.setAccessible(true);
//            Class<?>[] parameterTypes = constructor1.getParameterTypes();
//            System.out.println("tag" + parameterTypes.length);
//        }
//
//        Method[] methods = hookUserClass.getDeclaredMethods();
        Field[] declaredFields = hookUserClass.getDeclaredFields();
//        Annotation[] declaredAnnotation = hookUserClass.getDeclaredAnnotations();


        //动态代理相关类
        final Leader leader = new Leader(new Worker());
       IPerson iPersonImp = (IPerson)Proxy.newProxyInstance(Leader.class.getClassLoader(), new Class[]{IPerson.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                 return method.invoke(leader,args);
            }
        });

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.getName().equals("iPerson")){
                declaredField.set(userInstance,iPersonImp);
            }
        }

        userInstance.doWhat();
    }
}
