package project1_Bank_Management_System_FileHandling;

import java.util.List;

public  class ValidationUtil {
    
    private boolean valid;
    private String message;
    public ValidationUtil(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }
    public boolean isValid() {
        return valid;
    }
    public String getMessage() {
        return message;
    }    

    public static ValidationUtil ValidationLogic(String accountnumber){
           
            if((accountnumber.startsWith("-")))
                 return new ValidationUtil(false,"Account No. Can't be Negative");
                
            if((accountnumber.startsWith("0")))
                return new ValidationUtil(false,"First Digit of Account No can't be Zero");

            if(!(accountnumber.matches("^\\d+$")))
                return new ValidationUtil(false,"Account No. can contain only digits");

            if(accountnumber.length()<9 || accountnumber.length()>18)
                return new ValidationUtil(false,"Length Of Account Number is Wrong ");
                
            return new ValidationUtil(true,"Valid");
    }

    public static boolean uniqueAccountNo(String accountnumber,List <Account> list){
            for (Account x: list) {
                if (x.getAccountnum().equals(accountnumber)) 
                    return false;
            }
            return true;
    }

    public static boolean isHolderNameValid(String holdername){
        if(!(holdername.matches("[a-zA-Z\' -]+")) || holdername.length()<2|| holdername.length()>50)
            return false;
        return true; // like else
    }
    
    public static boolean isHolderAgeValid(int age){
        if(age<1)
            return false;
        return true;
    }
    public static boolean isHolderBalanceValid(double balance){
        if(balance<0)
            return false;
        return true;
    }

    public static boolean isAccountEmpty(List <Account> accountlist){
        if (accountlist.isEmpty()) 
            return false;
        return true;
    }

    public static Account isAccountNumExist(String accountnumber,List <Account> accountlist){
         for (Account account : accountlist) {
            if(account.getAccountnum().equals(accountnumber)) // Made method Account type to access particular account oustide this class
                return account;
        }
        return null;
    }

     public static boolean hasBalance(Account account){   // checks is Account Balance Zero?
        if (account.getBalance()==0) 
            return false;
        return true;
    }
}

