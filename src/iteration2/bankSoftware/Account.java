package iteration2.bankSoftware;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2017554
 */
public abstract class Account {
    protected String SortCode = "00-00-00";
    protected int AccountNo = 0;
    protected double Balance = 0;
    protected String NameOfBank = "TSB";
    protected double Rate = 1.2;
    protected LocalDate LastReportedDate;
    protected int Transactions = 0;
    
    public void create(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate){
        SortCode = inSortCode;
        AccountNo = inAccountNo;
        Balance = inBalance;
        NameOfBank = inNameOfBank;
        Rate = inRate;
    }
    public void Edit(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate){
        SortCode = inSortCode;
        AccountNo = inAccountNo;
        Balance = inBalance;
        NameOfBank = inNameOfBank;
        Rate = inRate;
    }
    public void edit(double param){
        
    }
    public String getSortCode(){
        return SortCode;
    }
    public void Display(JTextArea src){
        src.append("\nSort Code: " + SortCode + "\nAccount Number: " + Integer.toString(AccountNo) + "\nBalance: £"+Balance + "\nBank Name: "+ NameOfBank);
    }
    public void deposit(double amount){
        Balance = Balance + amount;
    }
    public void withdraw(double amount){
        Balance = Balance-amount;
    }
    public void transfer(Account toAccount, double amount){
        this.withdraw(amount);
        toAccount.deposit(amount);
        
    }
    public void saveToFile(FileWriter aWriter){
        try{
            String string;
            string = SortCode + "\n" + Integer.toString(AccountNo) + "\n" +Balance + "\n"+ NameOfBank + "\n" + Rate;
            aWriter.write(string+System.getProperty("line.separator"));
            aWriter.write("##"+System.getProperty("line.separator"));
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
    public void LoadFromFile(BufferedReader bin){
        String Record;
        
        try{
            Record = new String();
            while((Record=bin.readLine()) !=null){             
                SortCode = Record;
                Record=bin.readLine();
                AccountNo = Integer.valueOf(Record);
                Record=bin.readLine();
                Balance = Double.parseDouble(Record);
                Record=bin.readLine();
                NameOfBank = Record; 
                Record=bin.readLine();
                Rate = Double.parseDouble(Record);
                
            }
        }
        catch(IOException ioe){
            System.out.println("Could not load");
        }
    }
    public String getType(){
        return "Account";
    }
    public double getSpecialVal(){
        return 0;
    }
    public int getAccountNo(){
        return AccountNo;
    }
    public abstract void endMonth();
    public void printStatement(){
        System.out.println("Transactions: " + Transactions +"\nBalance: " + Balance);
    }
    public double getBalance(){
        return Balance;
    }
    public Account(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate, Date inLastReportedDate){
        LastReportedDate = LocalDate.now();
        SortCode = inSortCode;
        AccountNo = inAccountNo;
        Balance = inBalance;
        NameOfBank = inNameOfBank;
        Rate = inRate;
    }
    public Account(){
        LastReportedDate = LocalDate.now();
    }
}
