package project1_Bank_Management_System_FileHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Bank bank=new Bank();
        boolean running=true;
        AccountFileHandler.loadAccounts(bank.list);
        while (running) {
            System.out.println("\n*********************************");
            System.out.println("Banking Management System");
            System.out.println("*********************************");
            System.out.println("1: Create Account");
            System.out.println("2: View All Account");
            System.out.println("3: Search Account");
            System.out.println("4: Deposit Money");
            System.out.println("5: Withdraw Money");
            System.out.println("6: Transfer Money");
            System.out.println("7: Delete Account");
            System.out.println("8: Exit");
            System.out.println("--------------------------------");
            System.out.println("Enter Your Choice:");
            int choice=0;
            try {
                choice=scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Only Integer choice is allowed");
                scanner.nextLine();
            }
            
            switch (choice) {
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    bank.viewAllAccount();
                    break;
                case 3:
                    bank.searchAccount();
                    break;
                case 4:
                    bank.depositMoney();
                    break;
                case 5:
                    bank.withdrawMoney();
                    break;
                case 6:
                    bank.transferMoney();
                    break;
                case 7:
                    bank.deleteAccount();
                    break;
                case 8:
                    System.out.println("--------------------------------");
                    System.out.println("Thank You For Using My Project");
                    System.out.println("--------------------------------");
                    running=false;
                    break;

                default:
                    System.out.println("Invalid Choice, Please Enter any of Given Choices");
            }
        }
        scanner.close();
    }
}

