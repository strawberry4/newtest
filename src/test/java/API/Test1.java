package API;


import Manager.*;
import Utils.DateUtil;
import Utils.ProxyUtils;
import Utils.TestDynamicProxy;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.EasyExcel;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.annotation.GET;


import static Utils.EnDecryptUtil.parseStrToMd5L32;

public class Test1 {
 /*   MovieApi movieApi = ProxyUtils.create(MovieApi.class);
    @Test
   // @GET(path = "/top250", description = "&#x8c46;&#x74e3;&#x7535;&#x5f71; top 250")
    Response test1(Integer start, Integer count) {

        return null;
    }
    @org.junit.Test
    @GET(path = "/top250", description = "&#x8c46;&#x74e3;&#x7535;&#x5f71; top 250")
    public void testCelebrity() throws Exception {
        Response response = movieApi.celebrity(1031931);
    }*/
    @Test
    public  void proxytest1(){

       /* Myinterface dynamicProxy = new EntityforMyinterface();
        Myinterface dynamicProxy2 = new Entity2forMyinterface();
        System.out.println(dynamicProxy.getClass());
        //Myinterface myinterface= TestDynamicProxy.gettest(dynamicProxy.getClass());
        //myinterface.sellMobilePhone("ttt");
        TestDynamicProxy testDynamicProxy=new TestDynamicProxy(dynamicProxy2);
        Myinterface ad=testDynamicProxy.gettest(Myinterface.class);
        ad.sellMobilePhone("ttttt");*/




        // li.add(1);
        //System.out.println(li.size());

    }


    @Test
    public  void proxytest2(){
      Myinterface myinterface = (Myinterface) ProxyFactory.getstore(Myinterface.class);
           //myinterface.sellMobilePhone();
       myinterface.sellMobilePhone();


       // System.out.println("uuuuuuuuuuuuu"+myinterface.toString());


    }
    @Test
    public  void Footest(){
        FooProxy fooProxy1= new FooProxy();
        fooProxy1.setTarget(new FooImpl2());
        Foo f = (Foo) fooProxy1.poxy();
        System.out.println("f is"+f);
        f.doAction();

        String aa=f.seesomething("named");
        System.out.println("----------------");
        System.out.println(aa);

        /*System.out.println("after f is"+f);
        fooProxy1.setTarget(new FooImpl2());
        f.doAction();*/
        List l1=new ArrayList();
        System.out.println(l1.getClass());

    }

    @Test
    void  datetest(){


        Date date = new Date();//取时间
       /* Calendar calendar  =   Calendar.getInstance();

        calendar.setTime(date); //需要将date数据转移到Calender对象中操作
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-3);
        //calendar.add(calendar.DATE, -62);//把日期往后增加n天.正数往后推,负数往前移动
        //date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        System.out.println(calendar.getTime());
        System.out.println(date.toString());*/
        long diff=15*24*60*60*1000;
       long time = date.getTime()-diff;
        System.out.println(new Date(time).toString());
        System.out.println(date.getTime());
        System.out.println(time);
        DateUtil du =new DateUtil();
        Calendar calendar  =   Calendar.getInstance();
        calendar.add(calendar.DATE, -62);
        date=calendar.getTime();
        System.out.println(date);

        String phone="130000000";
        //System.out.println(phone);
        String web4_user="testcp10";
        String md5_phone= parseStrToMd5L32(phone.toString());
        String update_user_sql ="update   t_user  set verifyPhone= '"+phone+"' where account='"+web4_user+"'";
        System.out.println(update_user_sql);
    }

    @Test
    void test3() {
       /* String[]  tablse = {"diagnosticassessmentscore","doctor_follow_up_visit","doctor_user_binding","doctoruser","el_doctor_copying","followupplans","im_contacts","inquiry_info","linkpayorderinfo","linkuserdoctor","order_medicine","orderrefund","prescription","prescription_time","prodromalphaseoperationlog","push_scale_info","pushsendmessage","remark","remind","report_summary","serviceuserinfo","user_doctor_free_reminder","user_project_service_receive","user_service_intention","userlookarticleinfo","videodiagnosereservation"};
        for (int i = 0; i < tablse.length ; i++) {
            System.out.println("IF role=1 THEN \n" +
                    "select count(*) into @old_total from "+tablse[i]+"_old;\n" +
                    "select count(*) into @new_total from "+tablse[i]+";\n" +
                    "select count(*) into @old_id_total from "+tablse[i]+"_old where  userid=uid;\n" +
                    "select count(*) into @new_id_total from "+tablse[i]+" where  userid=uid;\n" +
                    "select count(*) into @cancel_id_total from "+tablse[i]+" where userid=cancel_id;\n" +
                    "INSERT into compareSummary(table_name,old_total,new_total,old_id_total,new_id_total,cancel_id_total) VALUES ('"+tablse[i]+"',@old_total,@new_total,@old_id_total,@new_id_total,@cancel_id_total );\n" +
                    "\n" +
                    "else\n" +
                    "\n" +
                    "select count(*) into @old_total from "+tablse[i]+"_old;\n" +
                    "select count(*) into @new_total from "+tablse[i]+";\n" +
                    "select count(*) into @old_id_total from "+tablse[i]+"_old where  doctorid=uid;\n" +
                    "select count(*) into @new_id_total from "+tablse[i]+" where  doctorid=uid;\n" +
                    "select count(*) into @cancel_id_total from "+tablse[i]+" where  doctorid=cancel_id;\n" +
                    "INSERT into compareSummary(table_name,old_total,new_total,old_id_total,new_id_total,cancel_id_total) VALUES ('"+tablse[i]+"',@old_total,@new_total,@old_id_total,@new_id_total,@cancel_id_total );\n" +
                    "\n" +
                    "\n" +
                    "end  IF;");
            System.out.println();

        }*/

      /*  String[]  tables1 = {"coupondata","device_fog_configs","device_fog_datas","device_fog_datas_handled","device_smes_datas","device_smes_features","devicedatafiles","devicedatas","devices","devices_bind","diagnosticassessmentresult","diagnosticassessmentstandard","diarydoses","diarydoses_effect","diarydoses_temp","diaryliferecords","diarysleeprecords","diarysports","diaryswitchingperiods","evaluationuser","labeluser","linkuserlikearticle","linkuserlikearticletypes","medicinenames","medicinespecs","my_prescription","nim_questionnaire_outlines","patient_info","patientconsultation","patientdiagnoserecord","patientdiseasetypes","patientmedicalhistory","self_feeling","smes_abnormal_result","smes_no_sleep_data","spoondata","tremor_baseline","tremor_baseline_standard","tremor_daily_summary","tremorstatistics","user_report_time_record","user_service_report_time","usersinfo","usersinfo_history","userspoonbindinfo","userspoonbindinfodetails","usersquiz","userusedrugrecord","voice_sharing_activities","weixin_formid_save","weixin_user_openid","wx_community_user_activity","biko","exception_smes_data_comment"};

        for (int i = 0; i <tables1.length ; i++) {
            System.out.println("IF role=1 THEN \n" +
                    "select count(*) into @old_total from "+tables1[i]+"_old;\n" +
                    "select count(*) into @new_total from "+tables1[i]+";\n" +
                    "select count(*) into @old_id_total from "+tables1[i]+"_old where  userId=uid;\n" +
                    "select count(*) into @new_id_total from "+tables1[i]+" where  userId=uid;\n" +
                    "select count(*) into @cancel_id_total from "+tables1[i]+" where userId=cancel_id;\n" +
                    "INSERT into compareSummary(table_name,old_total,new_total,old_id_total,new_id_total,cancel_id_total) VALUES ('"+tables1[i]+"',@old_total,@new_total,@old_id_total,@new_id_total,@cancel_id_total );\n" +
                    "\n" +

                    "end  IF;");

        }*/

        String[] tables1 = {"doctor", "doctor_service", "doctor_synopsis", "doctor_visit_time", "doctorvideodiagnose", "el_doctor_info", "label", "linkageinfor", "linkdoctorarticle", "linkdoctordiseasetypes", "nim_diagnose_settings", "serviceinfoset", "template_content"};
        for (int i = 0; i < tables1.length; i++) {
            System.out.println("IF role=1 THEN \n" +
                    "select count(*) into @old_total from " + tables1[i] + "_old;\n" +
                    "select count(*) into @new_total from " + tables1[i] + ";\n" +
                    "select count(*) into @old_id_total from " + tables1[i] + "_old where  doctorId=uid;\n" +
                    "select count(*) into @new_id_total from " + tables1[i] + " where  doctorId=uid;\n" +
                    "select count(*) into @cancel_id_total from " + tables1[i] + " where doctorId=cancel_id;\n" +
                    "INSERT into compareSummary(table_name,old_total,new_total,old_id_total,new_id_total,cancel_id_total) VALUES ('" + tables1[i] + "',@old_total,@new_total,@old_id_total,@new_id_total,@cancel_id_total );\n" +
                    "\n" +
                    "end  IF;");


        }


    }


    @Test
    void test4(){
        String table[] = {"applogger","clientewallet","clientewalletrecord","coupondata","device_fog_configs","device_fog_datas","device_fog_datas_handled","device_smes_datas","device_smes_features","devicedatafiles","devicedatas","devices","devices_bind","diagnosticassessmentresult","diagnosticassessmentscore","diagnosticassessmentstandard","diarydoses","diarydoses_effect","diarydoses_temp","diaryliferecords","diarysleeprecords","diarysports","diaryswitchingperiods","dingding_message","doctor","doctor_follow_up_visit","doctor_service","doctor_synopsis","doctor_user_binding","doctor_visit_time","doctoruser","doctorvideodiagnose","el_doctor_copying","el_doctor_info","evaluationuser","followupplans","im_contacts","im_message_room_members","immessagetemplate","inquiry_info","label","labeluser","linkageinfor","linkdoctorarticle","linkdoctordiseasetypes","linkpayorderinfo","linkuserdoctor","linkuserlikearticle","linkuserlikearticletypes","medicinenames","medicinespecs","message","my_prescription","netslog","nim_diagnose_settings","nim_questionnaire_outlines","order_medicine","orderrefund","patient_info","patientconsultation","patientdiagnoserecord","patientdiseasetypes","patientmedicalhistory","prescription","prescription_time","prodromalphaseoperationlog","push_scale_info","pushsendmessage","remark","remind","report_summary","searchhistory","self_feeling","serviceinfoset","serviceuserinfo","sharerecords","smes_abnormal_result","smes_no_sleep_data","spoondata","template_content","tremor_baseline","tremor_baseline_standard","tremor_daily_summary","tremorstatistics","user_doctor_free_reminder","user_message_state","user_project_service_receive","user_report_time_record","user_service_intention","user_service_report_time","userlookarticleinfo","usersinfo","usersinfo_history","userspoonbindinfo","userspoonbindinfodetails","usersquiz","usertip","usertiptasks","userusedrugrecord","videodiagnosereservation","voice_sharing_activities","weixin_formid_save","weixin_user_openid","wx_community_user_activity"};

    }


    @Test
    void iotest(){
        //String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        System.out.println(File.separator);
        String e="3232";
        File  file = new File(this.getClass().getResource("").getFile());
        System.out.println(file.getAbsolutePath());
     //   EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }


}
