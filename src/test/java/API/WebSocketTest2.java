package API;
import java.net.URI;
//废弃的测试类

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class WebSocketTest2 {




    @ClientEndpoint
    public static class WebSocketTest {


        private String deviceId;

        private Session session;

        public WebSocketTest () {

        }

        public WebSocketTest (String deviceId) {
            this.deviceId = deviceId;
        }

        protected boolean start() {
            WebSocketContainer Container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://dm.cgame88.com/web4im2/cqssc";
            System.out.println("Connecting to " + uri);
            try {
                session = Container.connectToServer(WebSocketTest.class, URI.create(uri));
                System.out.println("count: " + deviceId);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("创建失败");
                return false;
            }
            return true;
        }

    }


    public static void main(String[] args) {
        for (int i = 1; i< 10; i++) {
            WebSocketTest wSocketTest = new WebSocketTest(String.valueOf(i));
            if (!wSocketTest.start()) {
                System.out.println("测试结束！");
                break;
            }
        }
    }


}
