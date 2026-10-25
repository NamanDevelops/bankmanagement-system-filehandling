package project1_Bank_Management_System_FileHandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AccountFileHandler {
    // Helper method for Saving all Accounts
    public static void saveAllAccounts(List <Account> list){
        try (BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\projectBackup\\project1_Bank_Management_System_FileHandling\\accounts.txt",false))) {
        for (Account account : list) {
            bw.write(account.toString());
            bw.newLine();
        }
       } catch (IOException e) {
        System.out.println("Error Saving accounts to file: "+e.getMessage());
       }
    } 

    // Helper Method to Load all Accounts to this program from accounts.txt

    public static void loadAccounts(List <Account> list){
        try (BufferedReader br=new BufferedReader(new FileReader("D:\\projectBackup\\project1_Bank_Management_System_FileHandling\\accounts.txt"))) {
           String line;
            while ((line=br.readLine())!=null) {
                // Remove "Account [" and "]" and "rs"
                line = line.replace("Account [", "").replace("]", "").replace("rs",""); //holdername=Naman, holderage=20, accountnum=123456789, balance=25.0
                // Split by comma
                String[] parts = line.split(", ");// part[0]=holdername=Naman and part[1]=holderage=20 so on.....
                 String holdername=parts[0].split("=")[1]; //Splitting by "=" gives ["holdername", "Naman"] and Index [1] picks the actual value "Naman".
                 int holderage=Integer.parseInt(parts[1].split("=")[1]);
                 String accountnum=parts[2].split("=")[1];
                 double balance=Double.parseDouble(parts[3].split("=")[1]);
                 // Creting an Account object to pass these recovered data into the Account attributes.
                 Account account=new Account(holdername, holderage, accountnum, balance);
                 list.add(account);

           }
        } 
        catch (IOException e) {
        System.out.println("Error loading accounts: " + e.getMessage()); // At the time of read/write file its recommended to handle exception(if file doesn't exist,...)
        } 
        catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
