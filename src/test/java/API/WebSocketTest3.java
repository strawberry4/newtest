package API;

import Utils.ThreadWss;
import Utils.YamlUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;



/*
多线程链接。主要测试能承受多少个链接数
*/
public class WebSocketTest3 {
    public static void main(String[] args) throws URISyntaxException {


        /*System.out.println("组数="+groups+1);
        System.out.println("总数="+users.size());*/
                 //String url="wss://dm.cgame88.com/socketServer";
        String url="wss://dm.ryiwin188.com/socketServer";
        String lottedid="cqssc";
      /*  for (int i = 0; i < groups; i++) {//分几个线程
            int user_index=
            String user=users.get(i);
            URI final_url =new URI(url+"/"+user+"/"+lottedid);

            ThreadWss ts =new ThreadWss(final_url);
            Thread t = new Thread(m1);

        }*/


        //准备数据源
        YamlUtil yu=new YamlUtil();
        ArrayList<String> users  = (ArrayList<String> )yu.readto_Obj("user.yml");
        int  valuesnum=10;//定义每个线程处理10个用户的前提下，需要几个线程组
        int groups = users.size()/valuesnum;     //直接按照10人一组来取整（如果小于10人的下面会加1）
        if  (users.size() <valuesnum)          groups ++;  //如果不够10人，直接就是1组
        if (users.size() % valuesnum != 0)   groups ++;  //如果比10大，但是又不能被10整除的，最后要多加1组


       // Integer count = 2;

        //根据用户数确定需要多少个线程
      //  List<List<String>> lists = splitList(userList, count);
        /*1、先根据上面画好的组数来划分有几组用户，每一组就是一个线程
        2、每组线程启动后（调用start方法），就开始各自创建链接。
        3、每组线程创建多个用户，是要看每个用户组有多少个用户（这个上面定义死的是10个）
        4、所以外层的循环次数就是对应的组数，这里就是取groups的数值。
        */
        int j=0;  //定义下方内存for循环的起止坐标。第一次就是groupusers的首位，内存循环，每循环一次，就往后移动一个切片大小的位置。
        for (int i=1;i <= groups;i++) {//每循环一次，就产生一个线程，同时该线程就以下方groupusers里的每个用户为参数来创建链接
            
            List<String>  groupusers=new ArrayList<String>();  //开始填充每组的用户数据i，且外层循环每次结束后，该列表的内容要清空
            // TODO: 2020/11/9  

            if (groups==1)  { groupusers.addAll(users);} //这种情况就是只有一组，所有直接把users整个装入groupusers即可。
            else {//这种情况就是不止一个分组，就需要按照最大的组用户数来切片

                for (; j < valuesnum*i; j++) {
                    if(j< users.size()){
                    groupusers.add(users.get(j));}
                    else break;

                }

            }


          //  URI final_url =new URI(url+"/"+groupusers+"/"+lottedid);
              URI final_url =new URI(url);
         //   ThreadWss ts=new ThreadWss(final_url);
              ThreadWss ts=new ThreadWss(final_url,groupusers,lottedid);
            Thread thread=new Thread(ts );
            //启动线程
            thread.start();


        }




    }
}
