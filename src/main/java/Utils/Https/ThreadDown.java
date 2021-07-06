package Utils.Https;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.net.ssl.HttpsURLConnection;

import org.joda.time.Instant;
import org.joda.time.Chronology;
public class ThreadDown  implements Runnable {
    private String  url;
    private  String localurl;
    private int num;  //用来存储第几个下载包
    public ThreadDown(String url,String localurl,int num) {
        this.url=url;
        this.localurl=localurl;
        this.num=num;
    }

    @Override
    public void run() {
        HttpsURLConnection connection= null;
        try {
            connection = new Httpsconnect().downloadconnect(url);

        } catch (Exception e) {
            e.printStackTrace();
        }


      //  String filename="E:\\Download\\appfile\\app1.apk";
        // 读文件流
        DataInputStream in = null;
        try {
            long t1= Instant.now().getMillis();
            in = new DataInputStream(connection.getInputStream());
            DataOutputStream out = new DataOutputStream(new FileOutputStream(localurl));
            byte[] buffer = new byte[20480000];
            int count = 0;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
                out.flush();  //刷新文件。不过好像没有用
            }
          /*  out.write(buffer, 0, 20479999);
            out.flush();  //刷新文件。不过好像没有用*/

            long t2=Instant.now().getMillis();
            double time=(double)(t2-t1)/(1000*60);
            System.out.println("第"+num+"个包下载完成"+"----耗时"+time+"分");
            out.close();
            in.close();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("第"+num+"下载失败");
        }


    }
}
