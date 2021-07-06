package API;
/*
只开启一个链接。主要测试是否能正常链接
*/

import Utils.WssClient;
import java.net.URI;

import org.java_websocket.WebSocket;

public class WebSocketTest {
    public static void main(String[] args) {
        String url1 = "wss://dm.cgame88.com/socketServer/tencen313/cqssc";
        //String url1="ws://127.0.0.1:8443/v1";
        try {
            WssClient myClient = new WssClient(new URI(url1));
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
            System.out.println("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
