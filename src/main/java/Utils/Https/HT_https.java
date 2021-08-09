package Utils.Https;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class HT_https {
    public static void main(String[] args) {
        String url="https://app-test2.gyenno.com/service_app_im/user_center/v3_1/user/signIn";
        Map<String,Object> contents =new HashMap<>();
        contents.put("cc","+86");
        contents.put("password","e10adc3949ba59abbe56e057f20f883e");
        contents.put("signInChannel","1");
        contents.put("mobile","13148780002");
        contents.put("role","ROLE_DOCTOR");
        String body="{\n" +
                "\t\"cc\": \"+86\",\n" +
                "\t\"password\": \"e10adc3949ba59abbe56e057f20f883e\",\n" +
                "\t\"signInChannel\": \"1\",\n" +
                "\t\"mobile\": \"13148780002\",\n" +
                "\t\"role\": \"ROLE_DOCTOR\"\n" +
                "}";

        HttpRequest res=  HttpRequest.post(url).header("content-type", "application/json" ).header("cookie","JSESSIONID=86F4C29D789E86DDEA35B941FFA1E6EF").body(body);
        System.out.println(res.execute().body());

        //不设置请求头情况就使用：HttpUtil
        //System.out.println(HttpUtil.post(url,contents));
    }

    @Test
    public void ttet() throws MalformedURLException {
       /* given().when()

                .post("https://app-test2.gyenno.com/service_app_im/user_center/v3_1/user/signIn").header()
                header("content-type", "application/json" ).header("cookie","JSESSIONID=86F4C29D789E86DDEA35B941FFA1E6EF").body(body).

                then()

                .log().all().statusCode(200)

                .extract().path("data.user_id");*/
        String body="{\n" +
                "\t\"cc\": \"+86\",\n" +
                "\t\"password\": \"e10adc3949ba59abbe56e057f20f883e\",\n" +
                "\t\"signInChannel\": \"1\",\n" +
                "\t\"mobile\": \"13148780002\",\n" +
                "\t\"role\": \"ROLE_DOCTOR\"\n" +
                "}";

        URL url = new URL("https://app-test2.gyenno.com/service_app_im/user_center/v3_1/user/signIn");
        useRelaxedHTTPSValidation();
        Response response = given().log().all().
                header("content-type", "application/json").
                header("cookie", "JSESSIONID=86F4C29D789E86DDEA35B941FFA1E6EF").

                body(body).
                then().
                statusCode(200).
                when().
                post(url);
        response.getBody().prettyPrint();





    }

}
