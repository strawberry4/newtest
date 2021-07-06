package Utils;

//三方的wss客户端
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WssClient  extends WebSocketClient {
    public WssClient(URI serverUri) {
        super(serverUri);
    }
    @Override
    public void onOpen(ServerHandshake arg0) {

        //System.out.println("握手成功"); //打印消息太多。暂时屏蔽掉
    }

    @Override
    public void onMessage(String arg0) {
        System.out.println("收到消息：" + arg0);
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {

        System.out.println("连接关闭"+arg1);
    }

    @Override
    public void onError(Exception arg0) {
        System.out.println("发生错误");
    }

   // public  void connecturl(WsClient client){
   public  void connecturl(){
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance( "TLS" );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslContext.init(null, new TrustManager[] {
                    new X509TrustManager() {

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            System.out.println("checkClientTrusted1");
                        }

                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                                throws CertificateException {
                            System.out.println("checkClientTrusted2");
                        }

                        public void checkServerTrusted(X509Certificate[] certs,
                                                       String authType) {
                            System.out.println("checkServerTrusted1");
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                                throws CertificateException {
                            System.out.println("checkServerTrusted2");
                        }
                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            }, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        SSLSocketFactory factory = sslContext.getSocketFactory();

        try {
            this.setSocket( factory.createSocket() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connect();
    }



}
