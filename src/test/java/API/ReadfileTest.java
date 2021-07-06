package API;

import Utils.YamlUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadfileTest {
    public static void main(String[] args) {
        YamlUtil yu=new YamlUtil();
        ArrayList<String> user  = (ArrayList<String> )yu.readto_Obj("user.yml");

        System.out.println(user.size());
        System.out.println(user.get(1));


    }
}
