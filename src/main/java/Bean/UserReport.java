package Bean;

import java.util.List;

public class UserReport {
    private int status;

    private List<UserReportContent> content ;

    private String url;

    private String needSetSafe;

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setContent(List<UserReportContent> content){
        this.content = content;
    }
    public List<UserReportContent> getContent(){
        return this.content;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setNeedSetSafe(String needSetSafe){
        this.needSetSafe = needSetSafe;
    }
    public String getNeedSetSafe(){
        return this.needSetSafe;
    }
}
