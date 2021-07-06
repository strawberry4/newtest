package Manager;

import io.restassured.response.Response;

import com.GlobalVar;
import com.annotation.GET;
import com.annotation.Param;
import com.annotation.PathVariable;
import com.annotation.SERVER;



/**
 * @Author: Wang Hualin
 * @Date: 2018/1/18
 */
@SERVER(GlobalVar.DOUBAN_MOVIE_SERVER)
public interface MovieApi {

    /**
     * @param start
     */
    @GET(path = "/top250", description = "豆瓣电影 top 250")
    Response top250(@Param("start") Integer start,
                    @Param("count") Integer count);

    @GET(path = " /celebrity/{}", description = "影人条目信息")
    Response celebrity(@PathVariable Integer id);


}
