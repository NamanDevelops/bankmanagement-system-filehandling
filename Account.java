package project1_Bank_Management_System_FileHandling;

public class Account {
    private String holdername;
    private int holderage;
    private String accountnum;
    private double balance;

    public String getHoldername() {
        return holdername;
    }
    public void setHoldername(String holdername) {
        this.holdername = holdername;
    }
    public int getHolderage() {
        return holderage;
    }
    public void setHolderage(int holderage) {
        this.holderage = holderage;
    }
    public String getAccountnum() {
        return accountnum;
    }
    
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public Account(String holdername, int holderage, String accountnum, double balance) {
        this.holdername = holdername;
        this.holderage = holderage;
        this.accountnum = accountnum;
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "Account [holdername=" + holdername + ", holderage=" + holderage + ", accountnum=" + accountnum
                + ", balance=" + balance+"rs]";
    }
}
