package iteration2.bankSoftware;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
public class SavingsAccount extends Account{
    private double withdrawalLimit = 200;
    private double withdrawn = 0;
    
    public SavingsAccount(){
        
    }
    public void endMonth(){
        if (LocalDate.now() == LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())){
            this.printStatement();
            Transactions = 0;
            LastReportedDate = LocalDate.now();
        }
    }
    public String getType(){
        return "Savings Account";
    }
    public double getSpecialVal(){
        return withdrawalLimit;
    }
    public void edit(double withdrawalLLim){
        withdrawalLimit = withdrawalLLim;
    }
    public SavingsAccount(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate, Date inLastReportedDate){
        super(inSortCode, inAccountNo, inBalance, inNameOfBank, inRate, inLastReportedDate);
    }
    public void create(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate){
        super.create(inSortCode, inAccountNo, inBalance, inNameOfBank, inRate);
    }
    public void Display(JTextArea src){
        src.append("\nAccount Type: Savings Account\nWithdrawal Limit: £" + withdrawalLimit);
        super.Display(src);
    }
    public void withdraw(double amount){
        if (amount+withdrawn <= withdrawalLimit){
            super.withdraw(amount);
            withdrawn = withdrawn+amount;
        }
    }
    public void saveToFile(FileWriter aWriter){
        try{
            String string;
            string = "Savings\n" + (withdrawalLimit);
            aWriter.write(string+System.getProperty("line.separator"));
            super.saveToFile(aWriter);
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
}
