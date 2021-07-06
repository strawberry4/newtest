package Utils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;


import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

/**
 * Yaml文件的读取工具类
 * 加载yaml文件的常用几种方式，返回的类型之所以是object是因为yaml文件内容结构不同，返回的数据类型也不同
 * public Object load(String yaml)
 * public Object load(InputStream io)
 * public Object load(Reader io)
 * 同理对应的还有几种loadAll方法，返回的是一个Object的迭代对象，一般用来解析,形式如下的yaml文件
 * sample1:
 *     r: 10
 * ---
 * sample2:
 *     other: haha
 *
 * 其中的每一个Object就是每一个yaml片段解析出来的对象。
 * loadAll解析兼容load解析，但是反过来就不行.
 ** 下面所有的方法中
 *      * @param path 这里的path对应的是Resources文件夹的相对路径，
 *      *             例如Resources下有文件Y1.yml。这里对应的就是Y1.yml
 *      *             如果是Resources/date下有文件Y1.yml,这里对应的就是date/Y1.yml
 * */


public class YamlUtil {
    //定义一个yaml驱动的属性
    private final Yaml yaml= new Yaml();

    /**
     * 直接打印出给定yaml文件的内容
     * @param path 这里的path对应的是Resources文件夹的相对路径，
     *             例如Resources下有文件Y1.yml。这里对应的就是Y1.yml
     *             如果是Resources/date下有文件Y1.yml,这里对应的就是date/Y1.yml
     *
     */
     public  void printyaml(String path) throws IOException {
        String ym1=this.getClass().getClassLoader().getResource(path).getFile();
        Iterable<Object> ret = yaml.loadAll(this.getClass().getClassLoader()
                .getResourceAsStream(ym1));
        for (Object o : ret) {
            System.out.println(o);
        }
    }

    /**
     * 该方法方法：测试用例类读取自己独属的测试数据文件路径，获得路径后可以再根据文件中的数据类型去指定不同的读取方法
     * @param classzz 传入一个当前指定的类
     * @return 根据传入的类的路径（包名+类名）返回对应路径下的yaml文件路径（带有.yml后缀）
     */
    public String return_yml_path(Class classzz){
         String path;
         path=classzz.getCanonicalName()
                 .replace(".","/")+".yml";
         return path;

    }


    /**
     * 返回一个map类型的数据结构，对应的yaml文件的数据结构为：
     *name: redis222
     *age:
     *    -  dev.bar.com
     *    -  foo.bar.com
     * 第一个是正常的键值对，第二个的值是一个列表
     * @param path
     * @return
     */
    public  Map<String, Object> readto_map(String path){
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);
        Map<String, Object> obj = yaml.load(inputStream);
        return obj;
    }

    @Test
    public void tt() throws IOException {
       // System.out.println(readto_map("E:\\自动化相关\\AutoForInterface\\src\\main\\resources\\Web4Mysql.yml"));
       // printyaml("Web4Mysql.yml");
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("Web4Mysql.yml");
        Map<String, Object> obj = yaml.load(inputStream);
        System.out.println(obj);
      //Map<String, Object> mysqlinfo=
    }

    /**
     * 读取yaml文件的内容，返回一个object的对象，根据内容的类型自己转型
     * @param path
     * @return
     */
    public Object readto_Obj(String path)  {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);
        return yaml.load(inputStream);
    }

    /**
     * 读取yaml文件的内容，返回一个object的迭代对象，根据内容的类型自己转型,对应上面的最后一种数据格式
     * @param path
     * @return
     */
    public Iterable<Object> readto_Iter(String path)  {
        String ym1=this.getClass().getClassLoader().getResource(path).getFile();
        //System.out.println(ym1);
        Iterable<Object> ret = yaml.loadAll(this.getClass().getClassLoader()
                .getResourceAsStream(ym1));;
              return ret;
    }

    /**
     * Yaml转List
     * @param path
     * @return
     */
    public static List<Object> readtoList(String path) {
        Yaml yaml= new Yaml();
        List<Object> list= (List<Object>) yaml.load(path);
        return list;
    }



    /**
     * 可以读取本地文件也可以读取网络文件
     * @param path 如果是网络文件需要以http开头，而不是file开头，
     * @return  返回一个输入流
     * @throws IOException
     */
    public InputStream loadStream(String path) throws IOException {
        if (path.startsWith("http")) {
            URL url = new URL(path);
            return url.openStream();
        } else if (path.startsWith("/")) {
            return new FileInputStream(path);
        } else {
            //return this.getClass().getClassLoader().getResourceAsStream(path);
            return this.getClass().getClassLoader().getResourceAsStream(path);
        }
    }

    public  <T> T loadConf(String path, Class<T> clz) throws IOException {
        try (InputStream inputStream = loadStream(path)) {
            Yaml yaml = new Yaml();
            return yaml.loadAs(inputStream, clz);
        }
    }

    public static <T> void dumpConf(String save, T obj) throws IOException {
        Yaml yaml = new Yaml();
        yaml.dump(obj, new BufferedWriter(new FileWriter(save)));
    }

}
