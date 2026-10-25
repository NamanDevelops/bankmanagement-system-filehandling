package project1_Bank_Management_System_FileHandling;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Bank {
    
    Scanner scanner=new Scanner(System.in);
    List <Account> list=new ArrayList<>(); //holds the User account details
    
    // Helper Method For Reading accountnum
    public String readAccountNum(String message){
        System.out.println(message);
            return scanner.nextLine();
    }
    

    
    public void createAccount(){
        String accountnumber=readAccountNum("Enter Account Number :");
        ValidationUtil result=ValidationUtil.ValidationLogic(accountnumber);    //Validation1
        if(!result.isValid()){  // Runs when validation fail
            System.out.println(result.getMessage());
            return;
        }
    if(! ValidationUtil.uniqueAccountNo(accountnumber,list)){
        System.out.println("This Already Exists, Account no Should be Unique always");    //Validation2
        return;
       }

       System.out.println("Enter Account holder Name :");
       String holdername=scanner.nextLine();
       if(!ValidationUtil.isHolderNameValid(holdername)){
            System.out.println("Invalid Input, Please enter a valid Name"); //validation
            return;
       }

       System.out.println("Enter Account holder Age :");
       int holderage=0;   
       try {
          holderage=scanner.nextInt();
          scanner.nextLine();     
       } catch (InputMismatchException e) {
        System.out.println("Invalid Input, Age must be an integer");
        scanner.nextLine();
        return;
       }
       if(!ValidationUtil.isHolderAgeValid(holderage)){
        System.out.println("Age can't be 0 or negative");       //validation
        return;
       }

        System.out.println("Enter Initial Balance in rs (No Unit):");
        double holderbalance = 0;
        try {
            holderbalance = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input, Balance must be a number");
            scanner.nextLine();
            return;
        }
        if(!ValidationUtil.isHolderBalanceValid(holderbalance)){
        System.out.println("Balance can't be negative");       //validation
        return;
       }

       Account account=new Account(holdername, holderage, accountnumber, holderbalance);
       list.add(account);
   
       AccountFileHandler.saveAllAccounts(list);
       System.out.println("\nAccount Created Successfully");
    }


    public void viewAllAccount(){
        if(!ValidationUtil.isAccountEmpty(list)){
            System.out.println("No Account Created till Now");
            return;
        }
        System.out.println("Deatails of All Account Holder Is Mentioned Below :");
        for (Account account : list) {
            System.out.println(account);
        }
    }


    public void searchAccount(){
         if(!ValidationUtil.isAccountEmpty(list)){
            System.out.println("No Account Created till Now, So can't Search ");
            return;
        }

        String accountnumber=readAccountNum("Enter Account Number :");
        ValidationUtil result=ValidationUtil.ValidationLogic(accountnumber);    //Validation
        if(!result.isValid()){  // Runs when validation fail
            System.out.println(result.getMessage());
            return;
        }
        Account isExist=ValidationUtil.isAccountNumExist(accountnumber, list);
       if(isExist!=null){
        System.out.println(isExist);    // isExist=account
       }
       else{
        System.out.println("\nThis Account Number Doesn't Exist");
        return;
       }
    }


    public void depositMoney(){

         if(!ValidationUtil.isAccountEmpty(list)){
            System.out.println("No Account Created till Now, So can't Deposit Money ");
            return;
        }
    
        String accountnumber=readAccountNum("Enter Account Number :");
        ValidationUtil result=ValidationUtil.ValidationLogic(accountnumber);    //Validation1
        if(!result.isValid()){  // Runs when validation fail
            System.out.println(result.getMessage());
            return;
        }
        // Checking Entered AccNo Exists Or Not 
        Account isExist=ValidationUtil.isAccountNumExist(accountnumber, list);
       if(isExist!=null){

            System.out.println("Enter Amount For Deposit :");
            double amount = 0;
            try {   
                amount = scanner.nextDouble();
                 if(amount==0){
                                System.out.println("Invalid Input, Zero rs can't be deposit ");
                                return ;
                            }
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, amount must be a number");
                scanner.nextLine();
                return;
            }
            if(!ValidationUtil.isHolderBalanceValid(amount)){  // IK Should Use Balance in message but i played little to avoid write another method to validate +,- 
            System.out.println("Amount can't be negative");       //validation
            return;
            }
            isExist.setBalance(isExist.getBalance()+amount);
            System.out.println(amount +"rs Deposited Succesfully");
            AccountFileHandler.saveAllAccounts(list);
       }
       else{
        System.out.println("\nThis Account Number Doesn't Exist");
        return;
       }
    }

    public void withdrawMoney(){

         if(!ValidationUtil.isAccountEmpty(list)){
            System.out.println("No Account Created till Now, So can't Withdraw Money ");
            return;
        }

         String accountnumber=readAccountNum("Enter Account Number :");
        ValidationUtil result=ValidationUtil.ValidationLogic(accountnumber);    //Validation1
        if(!result.isValid()){  // Runs when validation fail
            System.out.println(result.getMessage());
            return;
        }
        // Checking Entered AccNo Exists Or Not 
        Account isExist=ValidationUtil.isAccountNumExist(accountnumber, list);
       if(isExist!=null){
    
             if(!ValidationUtil.hasBalance(isExist)){
                        System.out.println("'Error!, Withdrawing Money Not Possible', This Account has Zero Balance");
                        return;
                        }
            System.out.println("Enter Amount For Withdrawl :");
            double amount = 0;
            try {
                amount = scanner.nextDouble();
                 if(amount==0){
                                System.out.println("Invalid Input, Zero rs can't be Withdraw ");
                                return ;
                            }
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, amount must be a number");
                scanner.nextLine();
                return;
            }
            if(!ValidationUtil.isHolderBalanceValid(amount)){
            System.out.println("Amount can't be negative");       //validation
            return;
            } 
            // Checking Does this Account has Sufficient Balance to Withdraw
                if(isExist.getBalance()>amount || isExist.getBalance()==amount){
                        isExist.setBalance(isExist.getBalance()-amount);
                        System.out.println(amount +"rs Withdrawn Succesfully"); 
                        AccountFileHandler.saveAllAccounts(list);
                }
                else{
                     System.out.println("'Withdrawl Failed!', This Account has Insufficient Balance to Withdraw Money");
                            return;
                }
       }
       else{
        System.out.println("\nThis Account Number Doesn't Exist");
        return;
       }
    }



    public void transferMoney(){
        if(!ValidationUtil.isAccountEmpty(list)){
            System.out.println("No Account Created till Now, So can't transfer Money");
            return;
        }
        if(list.size()==1){
                System.out.println("'Error!, Transfer Money Not Possible', Only 1 Account is Created till Now");
                return ;
            }
        
        String senderaccountnumber=readAccountNum("Enter Sender's Account Number :");
        ValidationUtil senderresult=ValidationUtil.ValidationLogic(senderaccountnumber);    //Validation1
        if(!senderresult.isValid()){  // Runs when validation fail
            System.out.println(senderresult.getMessage());
            return;
        }
        // Checking Entered Sender's AccNo Exists Or Not 
        Account senderisExist=ValidationUtil.isAccountNumExist(senderaccountnumber, list);
        if(senderisExist!=null){
                // Checking Does the Sender has any(not zero) Balance to Transfer
                        if(!ValidationUtil.hasBalance(senderisExist)){
                        System.out.println("'Error!, Transfer Money Not Possible', Sender's Account has Zero Balance");
                        return;
                        }
               
                String receiveraccountnumber=readAccountNum("Enter Receiver's Account Number :");
                ValidationUtil receiverresult=ValidationUtil.ValidationLogic(receiveraccountnumber);    //Validation1
                if(!receiverresult.isValid()){  // Runs when validation fail
                    System.out.println(receiverresult.getMessage());
                    return;
                }
                // Sender & Receiver Can't be Same
                if(senderaccountnumber.equals(receiveraccountnumber)){
                    System.out.println("'Error!', Sender and Receiver Can't belongs to the same Account Number");
                    return ;
                }
                // Checking Entered Receiver's AccNo Exists Or Not 
                Account receiverisExist=ValidationUtil.isAccountNumExist(receiveraccountnumber, list);
                if(receiverisExist!=null){
                        System.out.println("Enter Amount that You want to Trasfer in rs (No Unit)  :");
                        double amount = 0;
                        try {
                            amount = scanner.nextDouble();
                            if(amount==0){
                                System.out.println("Invalid Input, Zero rs can't be transfer ");
                                return ;
                            }
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input, amount must be a number");
                            scanner.nextLine();
                            return;
                        }
                        if(!ValidationUtil.isHolderBalanceValid(amount)){
                        System.out.println("Invalid Input, Amount can't be negative");       //validation
                        return;
                        }
                       
                        // Checking Does the Sender has Sufficient Balance to Transfer
                        if(senderisExist.getBalance()>amount || senderisExist.getBalance()==amount){
                            receiverisExist.setBalance(receiverisExist.getBalance()+amount);// Credeted to receiver
                            senderisExist.setBalance(senderisExist.getBalance()-amount);// Withdrawn from sender
                            System.out.println("'Transaction Successful', "+amount+"rs Transferred");
                            AccountFileHandler.saveAllAccounts(list);
                        }
                        else{
                            System.out.println("'Transaction Failed!', Sender has Insufficient Balance to Transfer");
                            return;
                        }

                }
                else{
                    System.out.println("\nThis Receiver's Account Number Doesn't Exist");
                    return;
                }
                }
        else{
            System.out.println("\nThis Sender's Account Number Doesn't Exist");
            return;
        }

    }


    public void deleteAccount(){
         if(!ValidationUtil.isAccountEmpty(list)){
            System.out.println("No Account Created till Now, So nothing is deletable ");
            return;
        }

        String accountnumber=readAccountNum("Enter Account Number :");
        ValidationUtil result=ValidationUtil.ValidationLogic(accountnumber);    //Validation1
        if(!result.isValid()){  // Runs when validation fail
            System.out.println(result.getMessage());
            return;
        }
        // Checking Entered AccNo Exists Or Not 
        Account isExist=ValidationUtil.isAccountNumExist(accountnumber, list);
       if(isExist!=null){
            if(ValidationUtil.hasBalance(isExist)){
                        System.out.println("'Error!, Can't delete account with remaining Balance");
                        return;
                        }
            list.remove(isExist);
            System.out.println("Account Deleted Successfully");
            AccountFileHandler.saveAllAccounts(list);
       }
       else{
        System.out.println("\nThis Account Number Doesn't Exist");
        return;
       }
    }
}
