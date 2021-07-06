package Utils;
import java.sql.*;
import java.util.Map;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


import static Utils.EnDecryptUtil.parseStrToMd5L32;

//测试web4的数据库
public class JDBCWeb4connect {



        public static void main1(String[] args){

         /*   // 驱动程序名
            String driver = "com.mysql.jdbc.Driver";

            // URL指向要访问的数据库名scutcs
            String url = "jdbc:mysql://154.209.252.193:3306/hs3?createDatabaseIfNotExist=true&autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false";

            // MySQL配置时的用户名
            String user = "mvjukck0MTwmqCaVlDAwzg==";

            // MySQL配置时的密码
            String password = "+ZsixgnttfPpuuyz60AttgfmHZLJssXNqlws5OFFxpo=";

            try {
                // 加载驱动程序
                Class.forName(driver);

                // 连续数据库
                Connection conn = DriverManager.getConnection(url, user, password);

                if(!conn.isClosed())
                    System.out.println("Succeeded connecting to the Database!");

                // statement用来执行SQL语句
                Statement statement = conn.createStatement();

                // 要执行的SQL语句
                String sql = "select * from t_user";

                // 结果集
                ResultSet rs = statement.executeQuery(sql);

                System.out.println("-----------------");
                System.out.println("执行结果如下所示:");
                System.out.println("-----------------");

                System.out.println("-----------------");

                String name = null;

               *//* while(rs.next()) {

                    // 选择sname这列数据
                    name = rs.getString("sname");

                    // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
                    // 然后使用GB2312字符集解码指定的字节数组
                    name = new String(name.getBytes("ISO-8859-1"),"GB2312");

                    // 输出结果
                    System.out.println(rs.getString("sno") + "\t" + name);
                }*//*

                rs.close();
                conn.close();

            } catch(ClassNotFoundException e) {


                System.out.println("Sorry,can`t find the Driver!");
                e.printStackTrace();


            } catch(SQLException e) {


                e.printStackTrace();


            } catch(Exception e) {


                e.printStackTrace();


            }*/
        }


    public static void main(String[] args) throws SQLException {
            YamlUtil  yu=new YamlUtil();
            //读取配置文件，以map的结果返回读取到的内容
            Map<String, Object> Sql_resource=yu.readto_map("Web4Mysql.yml");


        String user = Sql_resource.get("ssh_user").toString();//SSH连接用户名 "root"
        String password = Sql_resource.get("ssh_password").toString();//SSH连接密码  "5c6o@a*yCGzucSNNt!SR"
        String host = Sql_resource.get("ssh_host").toString();//SSH服务器的外网ip "154.209.252.193"
        int port = Integer.parseInt(Sql_resource.get("ssh_port").toString()) ;//SSH访问端口 22888
        //JSch jsch = new JSch();

        int lport = 3306 ;//本地端口3306
        //String rhost = "10.10.10.10";//远程MySQL服务器
        String rhost = Sql_resource.get("rhost").toString();//远程MySQL服务器的内网ip "10.58.27.240"
        int rport = Integer.parseInt(Sql_resource.get("rport").toString());//远程MySQL服务端口

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
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

            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs3", "webrepl", "I1oyzwHs3WEYlB9i");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs3?createDatabaseIfNotExist=true&autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false", "webrepl", "I1oyzwHs3WEYlB9i");
            st = conn.createStatement();


           //将查询的结果打印出来。注；如果是查询的话调用的是executequery，如果是更新或者是插入的话，调用的是execute
            /*String sql = "SELECT * FROM t_account_change_type ";
            rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println(rs.getString(1));*/

           //批量插入md5加密的手机号码

            /*for (Integer i=31;i<99;i++){
                String phone="130000000"+i;
                //System.out.println(phone);
                String web4_user="testcp10"+ i;
               String md5_phone= parseStrToMd5L32(phone.toString());
                String update_user_sql ="update   t_user  set verifyPhone= '"+md5_phone+"' where account='"+web4_user+"'";
                rs = st.execute(update_user_sql);
            }
            System.out.println("执行成功");*/

            /*String phone="'45696b170b6e349cad659bcb9bf61d87'";
            String web4_user="'testcp1030'";
            String update_user_sql ="update   t_user  set verifyPhone="+phone+" where account="+web4_user;
            rs = st.execute(update_user_sql);
            System.out.println(rs);
            */
            System.out.println(st.execute("select count(*) from t_user"));



        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
           // if(rs!=null) rs.close();
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        }


    }





}



