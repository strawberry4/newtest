package API;

import Utils.Https.Httpsconnect;
import Utils.Https.ThreadDown;
import Utils.Https.X509TrustUtiil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.*;

public class Downloadfile {
    public static void main(String[] args) throws Exception {
      /* String  downfileurl="https://down4new.cgame88.com/download/android/ryv8.7.0.3.apk";

        HttpsURLConnection connection=   new  Httpsconnect().downloadconnect(downfileurl);


        String filename="E:\\Download\\appfile\\app1.apk";
        // 读文件流
        DataInputStream in = new DataInputStream(connection.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();
        in.close();
    }*/

        //安卓
     String downfileurl="https://down4new.cgame88.com/download/android/ryv8.7.0.3.apk";
    // String downfileurl="https://logdown.ryiwin188.com/download/android/ry4v8.7.0.4.apk";
   // String  downfileurl="https://down.qingcdn22.com/ry/ryv8.7.2.apk";  //安卓旧的下载方式
      //ios
      //String downfileurl="https://logdown.ryiwin188.com/download/ios/ry4v8.7.0.4.ipa";
        for (int i=10;i<=10;i++) {
            //安卓的存放地址
            String localfile="E:\\Download\\apkfile\\app"+i+".apk";

            //ios的存放地址
           // String localfile="E:\\Download\\ipafile\\app"+i+".ipa";
            Thread thread = new Thread(new ThreadDown(downfileurl,localfile,i));
            thread.start();
            System.out.println("第"+i+"个线程启动成功");

        }
    }
}