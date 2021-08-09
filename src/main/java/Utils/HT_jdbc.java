package Utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.db.ActiveEntity;
import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.handler.EntityListHandler;
import cn.hutool.db.sql.Condition;
import cn.hutool.db.sql.SqlExecutor;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HT_jdbc {




    private    Db Global_db;


     HT_jdbc (String group){

         Global_db = Db.use(group);
    }





    /*定义一个查询结果类型返回的类型  */
    public enum Type {
        Json, Map
    }


   // public    Db Global_db = Db.use("test2");

    public static void main1(String[] args) throws SQLException {
      //  DataSource ds = DSFactory.get("mysql"); // 加载 Mysql 数据库
       Db db = Db.use("test2");
   //   db.use("mysql").findAll("devices");
        System.out.println(db);
        ActiveEntity ac = new ActiveEntity(db,"linkuserdoctor1");
       // System.out.println(ac.getTableName());
        System.out.println(db.findAll("linkuserdoctor").size());    //这里的find调用完后会关闭connect  （AbstractDb的find方法中使用完成后会进行关闭）
       // List<Entity> entityList1 =db.findAll("linkuserdoctor");
       // List<Entity> entityList2 =db.findAll("usertip");
       // System.out.println(entityList1.size() +  "next "  + entityList2.size());
        System.out.println(db.findAll(Entity.create("linkuserdoctor").set("id", "1751111111111")));   //带条件查询，如果查询不到返回一个空的list

        //模糊匹配查询  Contains="%value%"    EndWith=="%value"   StartWith== "value%"
        System.out.println(db.findLike("linkuserdoctor", "userid", "866", Condition.LikeType.Contains));

        //直接传入sql查询
        List<Entity> entityList3 = db.query("select * from linkuserdoctor where id = ?", 1751);
        System.out.println(entityList3);
        System.out.println(entityList3.get(0).get("userid"));
        HashMap <String,Object>  map34 = entityList3.get(0);

        map34.forEach((k,v)-> System.out.println("Item :"+k+" Count:"+v));

         JSON     js = JSONUtil.parse(map34);
        System.out.println(js.toString());



    }

   /* @Test
    public  void test3342() throws SQLException {
        System.out.println(Global_db.findAll(Entity.create("linkuserdoctor").set("doctorId", "1010").set("userid","111513")).size());


    }*/



    /**
     * 将Entity集合转成json或者json列表
     *
     * @param <T>  Entity集合
     ** @return T
     */
    private <T>T  EntityToJson(List<Entity> ens){
        if(ens == null || ens.isEmpty() || ens.size()==0) {
            throw new NullPointerException("查询结果为空");

        }
        else if (ens.size()==1){
            return  (T)JSONUtil.parse(ens.get(0));
        }else{
            List<JSON> L_js = new ArrayList<>();
            for (Entity en: ens) {
                L_js.add(JSONUtil.parse(en));
            }
            return (T)L_js;
        }

    }

    /**
     * 将Entity集合转成json或者json列表
     *
     * @param <T>  Entity集合
     ** @return T
     */
    private <T>T  EntityToMap(List<Entity> ens){
        if(ens == null || ens.isEmpty() || ens.size()==0) {
            throw new NullPointerException("查询结果为空");

        }
        else if (ens.size()==1){
            return  (T)ens.get(0);
        }else{
            List< HashMap<String,Object>> L_js = new ArrayList<>();
            for (Entity en: ens) {
                L_js.add(en);
            }
            return (T)L_js;
        }

    }


    /**
     * 根据传入的sql与想要返回的类型进行返回
     *
     * @param sql  查询语句
     * @param type  想要返回的类型
     ** @return T
     */
    public  <T> T  select_get_value(String sql,Type type) throws SQLException {

        if (type == Type.Json) {
            return  EntityToJson( Global_db.query(sql));
        }
        else if (type == Type.Map){
            return EntityToMap(Global_db.query(sql));
        }


        return null;
    }


    /**
     * 根据传入的表明、过滤的字段条件与想要返回的类型进行返回
     *
     * @param tablename   想要查询的表
     * @param type      想要返回的类型
     * @param term    查询时带的条件,形式为“字段：值”，“字段：值”,可以带多个条件
     ** @return T
     */

    public  <T> T  find_get_value(String tablename,Type type,String... term) throws SQLException {

       Entity  inner_en = new Entity();
        if(term == null || term.length==0) {
            inner_en=Entity.create(tablename);

        }else{
            for (String inner_term:term) {

                inner_en=Entity.create(tablename).set(inner_term.split(":")[0],inner_term.split(":")[1]);
            }
        }


        if (type == Type.Json) {
            return  EntityToJson( Global_db.findAll(inner_en));
        }
        else if (type == Type.Map){
            return EntityToMap(Global_db.findAll(inner_en));
        }


        return null;


    }

/*
    public  <T> T  select_get_value(String tablename,Type type,String... term) throws SQLException {

        if (type == Type.Json) {
            return  EntityToJson( Global_db.query(sql));
        }
        else if (type == Type.Map){
            return EntityToMap(Global_db.query(sql));
        }


        return null;
    }
*/







   public static void main(String[] args) throws SQLException {
        HT_jdbc tl = new HT_jdbc("test2");
        List<JSON> df = tl.find_get_value("linkuserdoctor",Type.Json,"doctorId:1010");
        System.out.println(df.size());
        System.out.println(df.get(0).toString());


        //可以根据返回类型是list还是json来判断是多条还是一条结果
       System.out.println(tl.find_get_value("linkuserdoctor", Type.Json, "doctorId:1010", "userid:111513").getClass());


   }


    public  void testst99(){
        DataSource ds = DSFactory.get("test2");
        Connection conn = null;

        try {
            conn = ds.getConnection();
           /* // 执行非查询语句，返回影响的行数
            int count = SqlExecutor.execute(conn, "UPDATE " + TABLE_NAME + " set field1 = ? where id = ?", 0, 0);
            log.info("影响行数：{}", count);
            // 执行非查询语句，返回自增的键，如果有多个自增键，只返回第一个
            Long generatedKey = SqlExecutor.executeForGeneratedKey(conn, "UPDATE " + TABLE_NAME + " set field1 = ? where id = ?", 0, 0);
            log.info("主键：{}", generatedKey);*/
            /* 执行查询语句，返回实体列表，一个Entity对象表示一行的数据，Entity对象是一个继承自HashMap的对象，存储的key为字段名，value为字段值 */
            List<Entity> entityList = SqlExecutor.query(conn, "select * from linkuserdoctor",new EntityListHandler() );
            System.out.println(entityList.size());
            System.out.println(entityList.get(1).toString());
        } catch (SQLException e) {

        } finally {
            DbUtil.close(conn);
        }



        DataSource ds1 = DSFactory.get("dev");
        Connection conn1 = null;

        try {
            conn1 = ds1.getConnection();
           /* // 执行非查询语句，返回影响的行数
            int count = SqlExecutor.execute(conn, "UPDATE " + TABLE_NAME + " set field1 = ? where id = ?", 0, 0);
            log.info("影响行数：{}", count);
            // 执行非查询语句，返回自增的键，如果有多个自增键，只返回第一个
            Long generatedKey = SqlExecutor.executeForGeneratedKey(conn, "UPDATE " + TABLE_NAME + " set field1 = ? where id = ?", 0, 0);
            log.info("主键：{}", generatedKey);*/
            /* 执行查询语句，返回实体列表，一个Entity对象表示一行的数据，Entity对象是一个继承自HashMap的对象，存储的key为字段名，value为字段值 */
            List<Entity> entityList = SqlExecutor.query(conn1, "select * from linkuserdoctor",new EntityListHandler() );
            System.out.println(entityList.size());
            System.out.println(entityList.get(1).toString());
        } catch (SQLException e) {

        } finally {
            DbUtil.close(conn1);
        }
    }


}
