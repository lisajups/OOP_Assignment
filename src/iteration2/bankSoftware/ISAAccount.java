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
public class ISAAccount extends Account{
    private double depositLimitYearly = 3250;
    private double depositedThisYear = 0;
    public ISAAccount(){
        
    }
    public void endMonth(){
        if (LocalDate.now() == LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())){
            this.printStatement();
            Transactions = 0;
            LastReportedDate = LocalDate.now();
        }
    }
    public String getType(){
        return "ISA Account";
    }
    public double getSpecialVal(){
        return depositLimitYearly;
    }
    public void edit(double inDepositLim){
        depositLimitYearly = inDepositLim;
    }
    
    public ISAAccount(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate, Date inLastReportedDate){
        super(inSortCode, inAccountNo, inBalance, inNameOfBank, inRate, inLastReportedDate);
    }
    public void create(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate){
        super.create(inSortCode, inAccountNo, inBalance, inNameOfBank, inRate);
    }
    public void Display(JTextArea src){
        src.append("\nAccount Type: ISA Account\nDeposit Limit: £" + depositLimitYearly);
        super.Display(src);
    }
    public void withdraw(double amount){
        if (Balance-amount >= 0){
            super.withdraw(amount);
        }
    }
    public void deposit(double amount){
        if (depositedThisYear+amount <= depositLimitYearly){
            super.deposit(amount);
            depositedThisYear = depositedThisYear + amount;
        }
        
    }
    public void saveToFile(FileWriter aWriter){
        try{
            String string;
            string = "ISA\n" + depositLimitYearly;
            aWriter.write(string+System.getProperty("line.separator"));
            super.saveToFile(aWriter);
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
}
