package Utils;

import cn.hutool.db.ActiveEntity;
import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.ds.pooled.PooledConnection;
import cn.hutool.db.ds.pooled.PooledDataSource;
import cn.hutool.db.handler.EntityListHandler;
import cn.hutool.db.sql.Condition;
import cn.hutool.db.sql.SqlExecutor;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class HT_jdbc {




    private    Db Global_db;

    /*这里的group是resources目录下db.setting文件中类似【test2】 【dev】这种名称  */
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
     * 将Entity集合转成Map 或者Map列表
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
     * 将String（表+字段明：字段值值） 转化成  Entity
     *
     * @param tablename  表名
     * @param key_values 列明：列值
     ** @return Entity   根据表名、列明:列值的形式封装成一个Entity对象
     */
    private Entity  StringtoEntity(String tablename,String... key_values){

        Entity  inner_en = new Entity();
        inner_en=Entity.create(tablename);
        if(key_values != null && key_values.length > 0) {
            for (String inner_term:key_values) {
                //把查询条件拆分成多个Entity添加进去
                inner_en.set(inner_term.split(":")[0],inner_term.split(":")[1]);

            }
        }
        return inner_en;
    }





    /**
     * 根据传入的sql与想要返回的类型进行返回
     *
     * @param sql  查询语句
     * @param return_type  想要返回的类型
     ** @return T   根据返回的结果是一条还是多条自动判断是否要转成对应的list集合
     */
    public  <T> T  select_get_value(String sql,Type return_type) throws SQLException {

        if (return_type == Type.Json) {
            return  EntityToJson( Global_db.query(sql));
        }
        else if (return_type == Type.Map){
            return EntityToMap(Global_db.query(sql));
        }


        return null;
    }


    /**
     * 根据传入的表名、过滤的字段条件与想要返回的类型进行返回
     *
     * @param tablename   想要查询的表
     * @param return_type      想要返回的类型
     * @param term    查询时带的条件,形式为“字段：值”，“字段：值”,可以带多个条件;不传入的话就是不带条件查询
     ** @return T    根据返回的结果是一条还是多条自动判断是否要转成对应的list集合
     */

    public  <T> T  find_get_value(String tablename,Type return_type,String... term) throws SQLException {

       Entity  inner_en = new Entity();
        inner_en=Entity.create(tablename);
        if(term != null && term.length > 0) {


            for (String inner_term:term) {
                //把查询条件拆分成多个Entity添加进去
                inner_en.set(inner_term.split(":")[0],inner_term.split(":")[1]);

            }
        }


        if (return_type == Type.Json) {
            return  EntityToJson( Global_db.findAll(inner_en));
        }
        else if (return_type == Type.Map){
            return EntityToMap(Global_db.findAll(inner_en));
        }


        return null;


    }

    /**
     * 根据传入的表名、插入的字段:字段值 进行插入操作。并返回成功插入的行数
     *
     * @param tablename    想要插入的表
     * @param key_values   插入时带的条件,形式为“字段：值”，“字段：值”,可以带多个插入字段
     ** @return T    返回成功插入的行数
     */
    public int insert(String tablename,String... key_values) throws SQLException {

        return Global_db.insert(StringtoEntity(tablename,key_values));

    }


    /**
     * 根据传入的表名、插入的字段:字段值 进行插入操作。并返回成功插入的行数
     *
     * @param tablename    想要插入的表
     * @param key_values   插入时带的条件,形式为“字段：值”，“字段：值”,可以带多个插入字段
     ** @return T    返回成功插入后的数据对应的主键的值
     */
    public Long insert_GeneratedKey(String tablename,String... key_values) throws SQLException {

        return Global_db.insertForGeneratedKey(StringtoEntity(tablename,key_values));

    }


    /**
     * 根据传入insert语句。并返回成功插入的行数或者是对应的主键id
     *ps：如果该表有主键，返回的就是对应的主键id，如果没有主键返回的就是成功的行数
     ** @param sql    想要执行的sql语句
     * @return T    返回成功插入的数据对应的主键的值
     */
    public Long insert_GeneratedKey_sql(String sql) throws SQLException {

        return Global_db.executeForGeneratedKey(sql);

    }

    /**
     * 根据传入非查询语句（新增、修改、删除）。并返回执行成功的行数
     ** @param sql    想要执行的sql语句
     * @return    返回成功插入的行数
     */
    public int excute_sql(String sql) throws SQLException {

        return Global_db.execute(sql);

    }




    /**
     * 根据删除的表名、删除时带入的查询条件的字段:字段值 进行插入操作。并返回成功删除的行数
     *
     * @param tablename    想要删除的表
     * @param key_values   删除时带的条件,形式为“字段：值”，“字段：值”,可以带多个查询字段
     ** @return     返回成功删除的行数
     */
    public int  del(String tablename,String... key_values) throws SQLException {

        return Global_db.del(StringtoEntity(tablename,key_values));

    }

    /**
     * 根据要更新的的表名、更新时带入的查询条件的字段:字段值 进行插入操作。并返回成功更新的行数
     *
     * @param tablename    想要删除的表
     * @param files        想要更新的字段，格式为   “字段:字段值”
     * @param key_values   更新时带的条件,形式为“字段：值”，“字段：值”,可以带多个查询字段
     ** @return     返回成功更新的行数
     */

    public int  update(String tablename, String[] files, String... key_values) throws SQLException {

        return Global_db.update(StringtoEntity(tablename,files),StringtoEntity(tablename,key_values));

    }

   //重载上面的方法，支持更新的字段 的类型可以是列表可以是集合
    public int  update(String tablename, Collection<String> files, String... key_values) throws SQLException {

        String[]  newfiles=new String[files.size()];
        files.toArray(newfiles);
        return Global_db.update(StringtoEntity(tablename,newfiles),StringtoEntity(tablename,key_values));

    }









   public static void main(String[] args) throws SQLException {
        HT_jdbc tl = new HT_jdbc("test2");




        List<JSON> df = tl.find_get_value("devices",Type.Json,"model:TC20","bindStatus:1");
        System.out.println(df.size());
        System.out.println(df.get(0).toString());


        //可以根据返回类型是list还是json来判断是多条还是一条结果
       System.out.println(tl.find_get_value("linkuserdoctor", Type.Json, "doctorId:1010", "userid:111513").getClass());
       List<JSON> df1= tl.select_get_value("select * from im_message_room_videotape where channelId  in  (select channelId from im_message_room where orderNumber='PO2021081713593331739')  group by channelId;", Type.Json);
       for (JSON js1:df1
            ) {
           System.out.println(js1.toString());
       }

       //System.out.println(tl.insert("department", "departmentName:test6"));
       //System.out.println(tl.insert("department", "departmentName:test7"));
     // System.out.println(Global_db.executeBatch("delete from department where departmentName='test6'"));


      //System.out.println(tl.del("department","departmentName:test8"));

       //String[]  files={"name:test9","id:88"};
       //System.out.println(tl.update("test12",files,"name:test8"));

       //System.out.println(tl.excute_sql("update department set departmentName='test51' where departmentname='test5'"));
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

    }


}
