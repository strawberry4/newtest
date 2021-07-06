package Utils;

import java.util.Map;
import java.util.Set;

public class MathUtil {

    /**
     * 给定一个map，将其对应的键，组装成一个String数组，并返回
     * @param map
     * @param <V>
     * @return
     */
    public static <V>  String[] get_map_key(Map<String,V> map){
        Set<String> set= map.keySet();
       int length= set.size();
        String[] array =new String[length];
        return set.toArray(array);

    }
}
