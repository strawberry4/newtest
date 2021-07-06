package Utils;

import Manager.EntityforMyinterface;
import groovy.json.JsonOutput;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.w3c.dom.ls.LSOutput;

public class TestDynamicProxy {
    private  Object sub;
    public  TestDynamicProxy(Object object){
        this.sub=object;
    }
  /*
    public  <K> K create(Class classzz){

     return (K) Proxy.newProxyInstance(classzz.getClassLoader(), classzz.getInterfaces(),new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我已经进入你的体内");
                System.out.println("this is dynamicproxy for"+classzz.getName());
              //return classzz.newInstance();
                return  new EntityforMyinterface();
                //  return  null;

            }
        });
    }*/
      public   <T> T gettest(Class clazz){
      ClassLoader classLoader= clazz.getClassLoader();
      Class[]  classes= new Class[]{clazz};
      InvocationHandler handler=new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              //1、代理对象 2、代理方法  3、（代理执行时指挥者）执行方法时传递的参数
              System.out.println("我已经进入你的体内");
              System.out.println(method.getDeclaringClass());
              System.out.println("this is dynamicproxy for  "+proxy.getClass().getName());
              method.invoke(sub,args);
             return new EntityforMyinterface();
          }
      };
      T proxy;
      proxy = (T) Proxy.newProxyInstance(classLoader,classes,handler);
      return proxy;
  }
}
