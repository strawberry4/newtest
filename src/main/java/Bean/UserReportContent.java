package Bean;

import java.util.List;

public class UserReportContent {
    private String id;

    private String createTime;

    private String statDate;

    private int type;

    private String account;

    private double actualAmount;

    private double winAmount;

    private double rebateAmount;

    private double subRebateAmount;

    private int activityAmount;

    private double profitAmount;

    private int rechargeAmount;

    private int withdrawAmount;

    private double rechargeWithdrawDiff;

    private double balanceDiff;

    private double balance;

    private int dividend;

    private int balanceRectification;

    private int rechargeAmount2;

    private String test;

    private List<UserReportDetail> details ;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
    public String getCreateTime(){
        return this.createTime;
    }
    public void setStatDate(String statDate){
        this.statDate = statDate;
    }
    public String getStatDate(){
        return this.statDate;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setAccount(String account){
        this.account = account;
    }
    public String getAccount(){
        return this.account;
    }
    public void setActualAmount(double actualAmount){
        this.actualAmount = actualAmount;
    }
    public double getActualAmount(){
        return this.actualAmount;
    }
    public void setWinAmount(double winAmount){
        this.winAmount = winAmount;
    }
    public double getWinAmount(){
        return this.winAmount;
    }
    public void setRebateAmount(double rebateAmount){
        this.rebateAmount = rebateAmount;
    }
    public double getRebateAmount(){
        return this.rebateAmount;
    }
    public void setSubRebateAmount(double subRebateAmount){
        this.subRebateAmount = subRebateAmount;
    }
    public double getSubRebateAmount(){
        return this.subRebateAmount;
    }
    public void setActivityAmount(int activityAmount){
        this.activityAmount = activityAmount;
    }
    public int getActivityAmount(){
        return this.activityAmount;
    }
    public void setProfitAmount(double profitAmount){
        this.profitAmount = profitAmount;
    }
    public double getProfitAmount(){
        return this.profitAmount;
    }
    public void setRechargeAmount(int rechargeAmount){
        this.rechargeAmount = rechargeAmount;
    }
    public int getRechargeAmount(){
        return this.rechargeAmount;
    }
    public void setWithdrawAmount(int withdrawAmount){
        this.withdrawAmount = withdrawAmount;
    }
    public int getWithdrawAmount(){
        return this.withdrawAmount;
    }
    public void setRechargeWithdrawDiff(double rechargeWithdrawDiff){
        this.rechargeWithdrawDiff = rechargeWithdrawDiff;
    }
    public double getRechargeWithdrawDiff(){
        return this.rechargeWithdrawDiff;
    }
    public void setBalanceDiff(double balanceDiff){
        this.balanceDiff = balanceDiff;
    }
    public double getBalanceDiff(){
        return this.balanceDiff;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public double getBalance(){
        return this.balance;
    }
    public void setDividend(int dividend){
        this.dividend = dividend;
    }
    public int getDividend(){
        return this.dividend;
    }
    public void setBalanceRectification(int balanceRectification){
        this.balanceRectification = balanceRectification;
    }
    public int getBalanceRectification(){
        return this.balanceRectification;
    }
    public void setRechargeAmount2(int rechargeAmount2){
        this.rechargeAmount2 = rechargeAmount2;
    }
    public int getRechargeAmount2(){
        return this.rechargeAmount2;
    }
    public void setTest(String test){
        this.test = test;
    }
    public String getTest(){
        return this.test;
    }
    public void setDetails(List<UserReportDetail> details){
        this.details = details;
    }
    public List<UserReportDetail> getDetails(){
        return this.details;
    }
}
