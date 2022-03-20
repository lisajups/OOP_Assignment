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
public class CurrentAccount extends Account{
    private double Overdraft = 100;
    private double AvailableBalance;
    private String conditions;
    public CurrentAccount(){
        
      
    }
    public void endMonth(){
        if (LocalDate.now() == LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())){
            this.printStatement();
            Transactions = 0;
            LastReportedDate = LocalDate.now();
        }
    }
    public String getType(){
        return "Current Account";
    }
    public double getSpecialVal(){
        return Overdraft;
    }
    public CurrentAccount(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate, Date inLastReportedDate){
        super(inSortCode, inAccountNo, inBalance, inNameOfBank, inRate, inLastReportedDate);
    }
    public void create(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate){
        super.create(inSortCode, inAccountNo, inBalance, inNameOfBank, inRate);
    }
    public void Display(JTextArea src){
        src.append("\nAccount Type: Current Account\nOverdraft: £" + Overdraft);
        super.Display(src);
    }
    public void withdraw(double amount){
        super.withdraw(amount);
        if (Balance <= -Overdraft){
            super.withdraw(25.00);
        }
    }
    public void edit(double inOverdraft){
        Overdraft = inOverdraft;
    }
    public void depositMonthlyInterest(){
        
    }
    public void saveToFile(FileWriter aWriter){
        try{
            String string;
            string = "Current Account\n" + (Overdraft);
            aWriter.write(string+System.getProperty("line.separator"));
            super.saveToFile(aWriter);
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
}
