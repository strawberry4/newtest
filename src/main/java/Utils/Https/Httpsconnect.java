package Utils.Https;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.*;

public class Httpsconnect {

    public static HttpsURLConnection downloadconnect(String fileUrl) throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        sslcontext.init(null, new TrustManager[]{new X509TrustUtiil()}, new java.security.SecureRandom());
        URL url = new URL(fileUrl);
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslsession) {
                System.out.println("WARNING: Hostname is not matched for cert.");
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
        urlCon.setConnectTimeout(60000);
        urlCon.setReadTimeout(60000);

        // 设置字符编码
       // urlCon.setRequestProperty("Charset", "UTF-8");
        // 设定请求的方法，默认是GET（如果服务器要求的是POST会返回405。所以这里要有所区分。）
        //urlCon.setRequestMethod("GET");

        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("文件读取失败");
        }
        return urlCon;
    }
}
