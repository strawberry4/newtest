package Utils;

//多线程的wss类。实现了runnable的接口。
import Utils.WssClient;
import java.net.URI;
import java.util.List;

import org.java_websocket.WebSocket;

public class ThreadWss extends WssClient implements Runnable {
    /*
      private  String serverUri;*/
    private  List<String> users =null;
    private  String lottedid;
    private  URI url;
    public ThreadWss(URI serverUri) {
        super(serverUri);
        /*this.serverUri=user;
        this.user=user;
        this.lottedid=lottedid;*/
        this.url=serverUri;
    }

//这个构造函数主要是为了根据传入的用户列表，来创建多个链接。
    //所以需要把URI给拆分成3部分：1、固定的域名  2、用户名  3、lott简称
    public ThreadWss(URI serverUri, List<String> users,String lottedid) {
        super(serverUri);
        /*this.serverUri=user;*/

        this.lottedid=lottedid;
        this.url=serverUri;
        this.users=users;
    }

    public void connecttion(String user,String  lottedid) {
        try {
            //  WssClient myClient = new WssClient(new URL(url1));
            String newrul=url.toString()+"/"+user+"/"+lottedid;

            WssClient myClient = new WssClient(new URI(newrul));
            myClient.connecturl();
//            myClient.connect();
            //  System.out.println(myClient.getReadyState());

            // 判断是否连接成功，未成功后面发送消息时会报错
            while (!myClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                //System.out.println(myClient.getReadyState());
               // System.out.println("连接中···请稍后");
                Thread.sleep(1000);
            }
            myClient.send("MyClient");
          //  System.out.println(myClient.getReadyState());
            //System.out.println("发送成功");
            System.out.println("线程" + Thread.currentThread().getId() + user.toString() + "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connecttion() {//当没有传入用户名与lottid的时候，直接使用该方法进行链接
        try {
            //  WssClient myClient = new WssClient(new URL(url1));

            WssClient myClient = new WssClient(url);
            myClient.connecturl();
//            myClient.connect();
            //  System.out.println(myClient.getReadyState());

            // 判断是否连接成功，未成功后面发送消息时会报错
            while (!myClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                //System.out.println(myClient.getReadyState());
                System.out.println("连接中···请稍后");
                Thread.sleep(1000);
            }
            myClient.send("MyClient");
            System.out.println(myClient.getReadyState());
            //System.out.println("发送成功");
            System.out.println("线程" + Thread.currentThread().getId() + "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void run() {
      //  String url1 = "wss://dm.cgame88.com/socketServer/"+user+"/cqssc";
      //  String url1 = serverUri+"/"+user+"/"+lottedid;
        if (users != null) {
            for (String user : users) {//根据传入的用户列表users，按照每个用户一条i链接的方式来创建链接
                connecttion(user, this.lottedid);
            }
        }else   connecttion();

    }

}
