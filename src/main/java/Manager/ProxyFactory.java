package Manager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {


    public static  Object getstore(Class<?>  clazz){
        ClassLoader classLoader = clazz.getClassLoader();
        Class[]  classes = new Class[]{clazz};
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("clazz.getDeclaringClass()");
                System.out.println(method.getName());
                //Object jj=clazz.newInstance();

                return new Intuio();
               /* if(clazz.getDeclaringClass().equals(Myinterface.class))
                {
                    System.out.println("this is Entity2forMyinterface");
                   // method.invoke(classes,args);
                    return new Entity2forMyinterface();
                }else{
                    System.out.println("this is EntityforMyinterface");
                    return  new EntityforMyinterface();
                }
*/
            }
        };

        Object proxy =  Proxy.newProxyInstance(classLoader,classes,handler);
        System.out.println(proxy.getClass());

        return proxy;
    }
}
