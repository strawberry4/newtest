package API;

import java.sql.*;

public class JdbcNormal {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        boolean rs;
        Statement st = null;
        conn = DriverManager.getConnection("jdbc:mysql://t.gyenno.com:33306/information_schema?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC", "develop", "bmdy3htunvtwqjMwadaDf1oz");
        st = conn.createStatement();
        if(st !=null) {

            String[] table  ={"applogger","clientewallet","clientewalletrecord","coupondata","device_fog_configs","device_fog_datas","device_fog_datas_handled","device_smes_datas","device_smes_features","devicedatafiles","devicedatas","devices","devices_bind","diagnosticassessmentresult","diagnosticassessmentscore","diagnosticassessmentstandard","diarydoses","diarydoses_effect","diarydoses_temp","diaryliferecords","diarysleeprecords","diarysports","diaryswitchingperiods","dingding_message","doctor","doctor_follow_up_visit","doctor_service","doctor_synopsis","doctor_user_binding","doctor_visit_time","doctoruser","doctorvideodiagnose","el_doctor_copying","el_doctor_info","evaluationuser","followupplans","im_contacts","im_message_room_members","immessagetemplate","inquiry_info","label","labeluser","linkageinfor","linkdoctorarticle","linkdoctordiseasetypes","linkpayorderinfo","linkuserdoctor","linkuserlikearticle","linkuserlikearticletypes","medicinenames","medicinespecs","message","my_prescription","netslog","nim_diagnose_settings","nim_questionnaire_outlines","order_medicine","orderrefund","patient_info","patientconsultation","patientdiagnoserecord","patientdiseasetypes","patientmedicalhistory","prescription","prescription_time","prodromalphaseoperationlog","push_scale_info","pushsendmessage","remark","remind","report_summary","searchhistory","self_feeling","serviceinfoset","serviceuserinfo","sharerecords","smes_abnormal_result","smes_no_sleep_data","spoondata","template_content","tremor_baseline","tremor_baseline_standard","tremor_daily_summary","tremorstatistics","user_doctor_free_reminder","user_message_state","user_project_service_receive","user_report_time_record","user_service_intention","user_service_report_time","userlookarticleinfo","usersinfo","usersinfo_history","userspoonbindinfo","userspoonbindinfodetails","usersquiz","usertip","usertiptasks","userusedrugrecord","videodiagnosereservation","voice_sharing_activities","weixin_formid_save","weixin_user_openid","wx_community_user_activity"};
           // String[] table  ={"applogger","clientewallet"};
            for (int i = 0; i < table.length; i++) {

                StringBuilder ids =new StringBuilder();
                ResultSet re=st.executeQuery("select COLUMN_NAME from columns   where table_schema = 'spoon_database'  and extra !='on update CURRENT_TIMESTAMP'  and  table_name ='"+table[i]+"'");

                while (re.next())
                    ids.append(re.getString(1)).append(",");

                System.out.println("INSERT into filterSummary \n" +
                        "\tSELECT \n" +
                        "\tA.id,"+"'"+table[i]+"'"+",count(*) \n" +
                        "FROM\n" +
                        "\t(\n" +
                        "\tSELECT * FROM "+table[i]+" \n" +
                        "\t\n" +
                        "\tUNION ALL \n" +
                        "\tSELECT * FROM "+table[i]+"_old\n" +
                        "\t) A GROUP BY\n" +
                        ids +
                        "\n" +
                        "HAVING    count(*)   !=2 ;\n");
                System.out.println();

            }


        }

        conn.close();
    }
}
