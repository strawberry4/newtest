package API;

import Utils.YamlUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.*;
import java.util.Map;

public class gyennodb {




    public static void main(String[] args) throws SQLException, JSchException {
        YamlUtil  yu=new YamlUtil();
        //读取配置文件，以map的结果返回读取到的内容
        Map<String, Object> Sql_resource=yu.readto_map("gyennosql.yml");


        String user = Sql_resource.get("ssh_user").toString();//SSH连接用户名
        String password = Sql_resource.get("ssh_password").toString();//SSH连接密码
        String host = Sql_resource.get("ssh_host").toString();//SSH服务器的外网ip
        int port = Integer.parseInt(Sql_resource.get("ssh_port").toString()) ;//SSH访问端口


        int lport = 3306 ;//本地端口3306
        String rhost = Sql_resource.get("rhost").toString();//远程MySQL服务器的ip
        int rport = Integer.parseInt(Sql_resource.get("rport").toString());//远程MySQL服务端口
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        try {


            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Connection conn = null;
        boolean rs;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");


           // conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spoon_database?autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false", "gyenno_read", "Geobrksorcg5yu5inwurcGqh");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spoon_database?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC", "gyenno_read", "Geobrksorcg5yu5inwurcGqh");
            st = conn.createStatement();

            if(st !=null) {
                System.out.println("okookokokok");
                ResultSet re=st.executeQuery("SELECT username FROM usersinfo  where uPhone='13288888888';");
                while (re.next())
                    System.out.println(re.getString(1));
            }


        } catch (Exception e1) {
            //e1.printStackTrace();
        } finally {
            // if(rs!=null) rs.close();
            if(st!=null) st.close();
            if(conn!=null) conn.close();
            session.setTimeout(2);

        }


    }



}
