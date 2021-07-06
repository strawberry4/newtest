package Manager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FooProxy {
    private Object target;
    public FooProxy (Object target){
        this.target=target;
    }

    public FooProxy() {

    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public InvocationHandler handler = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /* Class clazz = method.getDeclaringClass();
            System.out.println("clazz is "+clazz);
         if (clazz==Foo.class){
             System.out.println("dddddddddd");
             return new FooImpl2();
         }else {
             System.out.println("yyyyyyyyyyy");
               return method.invoke(target,args);
         }*/
            // return  new FooImpl2(); //这种返回的就是空

            return  method.invoke(target,args);

            /*method.invoke(target,args);
            return  null;
            //这种情况下返回的对象是空，但是方法可以执行
            */
        }
    };

    public  Object  poxy(){
        return  Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),handler);
    }
}
