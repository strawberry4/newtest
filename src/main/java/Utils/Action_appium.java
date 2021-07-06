package Utils;

/**
 * 定义appium中常用的几种操作类型。不过目前用不上，因为可以直接定义在yaml文件中
 */
public enum Action_appium {
    Chick("Chick"),  //点击
    Send_keys("Send_keys"), //输入字符并点击
    input("Send"),//发送字符
    Slide("Slide"); //滑动
    private final String actName;

    private Action_appium(String actName) {
        this.actName = actName;

    }

    public String getActName() {
        return this.actName;
    }
}
