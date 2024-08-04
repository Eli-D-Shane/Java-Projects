import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    public LocalDate curdate;
    public LocalTime curtime;
    public String transactionType;
    public float initialBalance;
    public float finalBalance;
    
    public Transaction(LocalDate curdate, LocalTime curtime, String transactionType, float initialBalance,float finalBalance) {
        this.curdate = curdate;
        this.curtime = curtime;
        this.transactionType = transactionType;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }
}

public class ATM{
    public static int Pin = 0;
    public static int choice;
    public static float balance = 0;
    public static List<Transaction> transactions = new ArrayList<>();

    public static void balanceEnquiry(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Pin number: ");
        int enteredPin = sc.nextInt();
        if(enteredPin == Pin){
            System.out.println("Account Balance: "+balance);
        }
        else{
            System.out.println("Wrong Pin entered! ");
        }
    }
    public static void cashDeposit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Pin number: ");
        int enteredPin = sc.nextInt();
        if(enteredPin == Pin){
            System.out.println("Please enter the amount you would like to deposit: ");
            float amount = sc.nextFloat();
            balance += amount;
            String transactionType = "Credit";
            Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(), transactionType, balance - amount, balance);
            transactions.add(transaction);
        }
        else{
            System.out.println("Wrong Pin entered! ");
        }
    }
    public static void cashWithdrawal(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Pin number: ");
        int enteredPin = sc.nextInt();
        if(enteredPin == Pin){
            System.out.println("Please enter the amount you would like to withdraw: ");
            float amount = sc.nextFloat();
            if(amount > balance){
                System.out.println("Insufficient Funds, cannot withdraw");
                return;
            }
            else{
                balance-=amount;
                String transactionType = "Debit";
                Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(), transactionType, balance + amount, balance);
                transactions.add(transaction);
            }
        }
        else{
            System.out.println("Wrong Pin entered! ");
        }
        
    }
    public static void pinChange(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Pin number: ");
        int enterPin = sc.nextInt();
        if(enterPin == Pin){
            System.out.println("Please enter your new Pin number: ");
            Pin = sc.nextInt();
            System.out.println("Please confirm your new Pin number: ");
            int newPin = sc.nextInt();
            if(newPin == Pin){
                System.out.println("Pin changed successfully");
            }
            else{
                System.out.println("Incorrect new pin confirmation");
                Pin = enterPin;
            }
        }
        else{
            System.out.println("Incorrect Pin number: ");
        }
    }

    public static void transactionHistory(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Pin number: ");
        int enteredPin = sc.nextInt();
        if(enteredPin == Pin){
            System.out.println("Transaction Date\tTransaction Time\tType of Transaction\tInitial Balance\t\tFinal Balance");
            for (Transaction transaction : transactions) {
                System.out.println(transaction.curdate+"\t\t"+transaction.curtime+"\t\t"+transaction.transactionType+"\t\t"+transaction.initialBalance+"\t\t"+transaction.finalBalance);
            }
        }
        else{
            System.out.println("Wrong Pin entered! ");
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        for (;;) {
            if(Pin == 0){
                for(;;){
                    System.out.println("Please set your Pin number: ");
                    Pin = sc.nextInt();
                    System.out.println("Please confirm your pin:");
                    int some_pin = sc.nextInt();
                    if(Pin == some_pin){
                        System.out.println("Pin set successfully!");
                        break;
                    }
                    else{
                        System.out.println("Wrong pin! Please set it again!");
                    }
                }
            }
            else{
                for(;;){
                    System.out.println("1. Balance Enquiry \n 2. Cash Withdrawal \n 3. Cash Deposit \n 4. PIN Change \n 5. Transaction History\n 6. Exit");
                    choice = sc.nextInt();
                    if(choice == 6)
                        break;
                    switch(choice){
                        case 1: balanceEnquiry(); break;
                        case 2: cashWithdrawal(); break;
                        case 3: cashDeposit(); break;
                        case 4: pinChange(); break;
                        case 5: transactionHistory(); break;
                        default : System.out.println("Wrong choice!");
                    }
                }
                if(choice == 6)
                    break;
            }
        }
    }
}