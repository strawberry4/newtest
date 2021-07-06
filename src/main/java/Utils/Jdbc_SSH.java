package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


public class Jdbc_SSH {
    public static Session session;
    public static Connection getConnection() {
        JSch jSch = new JSch();
        try {
            /**
             * SSH_USER：服务器用户名
             * SSH_HOST：服务器地址
             * SSH_PORT：服务器端口
             * SSH_PASSWORD：服务器登录密码
             */
            String SSH_USER= PropertiesUtils.getProperty("SSH_host_name");
            String SSH_HOST= PropertiesUtils.getProperty("SSH_host_ip");
            int SSH_PORT= Integer.valueOf(PropertiesUtils.getProperty("SSH_host_port"));
            String SSH_PASSWORD= PropertiesUtils.getProperty("SSH_host_password");

            session = jSch.getSession(SSH_USER, SSH_HOST, SSH_PORT);
            session.setPassword(SSH_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            int timeout = 6000;
            session.setTimeout(timeout); // 设置timeout时间

            session.connect();
            System.out.println("服务器版本信息: {}"+session.getServerVersion());

            /**
             * 正向代理
             * LOCALHOST_HOST（可缺省参数，默认：127.0.0.1）：绑定的地址
             * LOCALHOST_PORT：本地未被占用的端口
             * MYSQL_HOST：数据库地址
             * MYSQL_PORT： 数据库端口
             */
          /*  String SSH_USER=PropertiesUtils.getProperty("SSH_host_name");
            String LOCALHOST_HOST=PropertiesUtils.getProperty("SSH_host_ip");
            int LOCALHOST_PORT=Integer.valueOf(PropertiesUtils.getProperty("SSH_host_port"));
            String SSH_PASSWORD=PropertiesUtils.getProperty("SSH_host_password");*/
            /**
             *  55554对应的是本地服务器转发的端口
             * 链接成功的话，这里返回的就是55554
             * 目标服务器服务提供对外访问的端口3306
             */

          session.setPortForwardingL(3306,SSH_HOST,3306);
            //System.out.println(num);
            System.out.println("Connected");
        } catch (JSchException e) {
            System.out.println("JSch get session failure"+e);
        }
        Connection conn = null;
        try {
           // URL： jdbc:mysql://127.0.0.1:3300/test  **这里的地址和 LOCALHOST_HOST 一致

           // conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+localPort+"/"+db+"?characterEncoding=utf8&useSSL=false",name,password);
            //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hs3_76"+"?&autoReconnect=true&failOverReadOnly=false","webrepl","I1oyzwHs3WEYlB9i");
           // Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs3_76?serverTimezone=UTC","webrepl","I1oyzwHs3WEYlB9i");
            System.out.println(conn+  "is  connect");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("get connection failure:"+e);
        }
        return conn;
    }

    public static void closeSSH(){
        session.disconnect();
    }
    @Test
    public void testa(){
        getConnection();

        System.out.println("----------------------");

        closeSSH();





    
    }

    @Test
    public void testb(){
         JDBCConnectionFactory jc=new JDBCConnectionFactory();
         System.out.println(jc.jdbcConnection());

    }
}
