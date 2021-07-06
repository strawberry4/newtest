package Utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

public class PropertiesUtils {
    public static String getProperty(String proName) {
        String proValue = "";
        Properties prop = new Properties();
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbcnormal.properties");
            //Thread.currentThread().getContextClassLoader().getResourceAsStream("common.properties");
            //new BufferedInputStream(new FileInputStream("classpath:common.properties"));
            prop.load(new InputStreamReader(in, "utf-8"));
            proValue = (prop.getProperty(proName));
            return proValue;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Set<String> getallnames(String proName) {
        Set<String> names=new HashSet<>();
        try{
            Properties properties=new Properties();
            //直接写src 类路径下的文件名
            InputStream input=Thread.currentThread().getContextClassLoader().getResourceAsStream(proName);
            properties.load(input);

            //把key值转换成set 的形式，遍历set
            names=properties.stringPropertyNames();
            Iterator<String> iterator=names.iterator();
            while(iterator.hasNext()){
                String key=iterator.next();
                String value=properties.getProperty(key);
                System.out.println(key+"="+value);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        return names;
    }






    @Test
    public  void test11(){
        //InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("src/main/java/Utils/PropertiesUtils.java");
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbcnormal.properties");
        System.out.println(in);
        Set<String> names=new HashSet<>();
        names= getallnames("jdbcnormal.properties");
        Iterator<String> iterator=names.iterator();
        while(iterator.hasNext()){
                System.out.println(iterator.next());
        }
    }


}
