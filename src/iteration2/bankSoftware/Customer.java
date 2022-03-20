package iteration2.bankSoftware;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
public class Customer {
    private String firstname = "";
    private String surname = "";
    private IAddressBank homeaddress;
    private String DOB = "";
    private String CustomerSince;
    private Account custAccount;
    private int numAccounts = 0;
    private ArrayList<Account> custAccounts;
    
    public String getFirstName(){
        return firstname;
    }
    public String getDOB(){
        return DOB;
    }
    public String getSurname(){
        return surname;
    }
    public Account getAccount(){
        return custAccount;
    }
    public void Edit(String strFirstname, String strSurname, String inDOB, int inNumAccounts){
        surname = strSurname;
        firstname = strFirstname;
        DOB = inDOB;
        numAccounts = inNumAccounts;
    }
    public void EditAddress(String strName, String strHouse_name, int houseNum, String strStreet, String strArea, String strTown, String strPostcode, String strCountry){
        this.getAddress().Edit(strName, strHouse_name, houseNum, strStreet, strArea, strTown, strPostcode, strCountry);
    }
    public void EditAccount(String inSortCode, int inAccountNo, double inBalance, String inNameOfBank, double inRate){
        this.custAccount.Edit(inSortCode, inAccountNo, inBalance, inNameOfBank, inRate);
    }
    public void Display(JTextArea src){
        src.append("\n---\nName: " + firstname + " " + surname + "\nDOB: " + DOB + "\nCustomer Since: " + CustomerSince);
        this.getAddress().Display(src);
        for (int i = 0; i < numAccounts; i++){
            this.custAccounts.get(i).Display(src);
        }
        
    }
    public Boolean hasAccount(String inSortCode, int inAccountNum){
        boolean found = false;
        for (int i = 0; i<custAccounts.size(); i++){
            if ((custAccounts.get(i).getAccountNo()==inAccountNum) && (custAccounts.get(i).getSortCode().equals(inSortCode))){
                found = true;
            }
        }
        
        return found;
    }
    public Account getAccount(String inSortCode, int inAccountNum){
        Account account = null;
        for (int i = 0; i<custAccounts.size(); i++){
            if ((custAccounts.get(i).getAccountNo()==inAccountNum) && (custAccounts.get(i).getSortCode().equals(inSortCode))){
                account = custAccounts.get(i);
            }
        }
        return account;
    }
    
    
    public Boolean checkDOB(String givenDOB){
        if (givenDOB == DOB){
            return true;
        }
        else{
            return false;
        }
    }
    public void saveToFile(){
        FileWriter aWriter;
        try{
            aWriter = new FileWriter("customers.txt", true);
            String string;
            string = firstname + "\n" + surname + "\n" + DOB + "\n" + numAccounts;
            aWriter.write(string+System.getProperty("line.separator"));
            this.getAddress().SaveToFile(aWriter);
            this.custAccount.saveToFile(aWriter);
            aWriter.flush();
            aWriter.close();
            aWriter = null;
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
    public void LoadFromFile(){
        FileReader reader;
        try{
            reader = new FileReader("customers.txt");
            BufferedReader bin = new BufferedReader(reader);
            String Record;
            Record = new String();
            while((Record=bin.readLine()) !=null){
                firstname = Record;
                Record=bin.readLine();
                surname = Record;
                Record=bin.readLine();
                DOB = Record;    
                this.getAddress().LoadFromFile(bin);
                this.custAccount.LoadFromFile(bin);
            }
        }
        catch(IOException ioe){
            System.out.println("Could not load");
        }
    }
    public IAddressBank getAddress(){
        return homeaddress;
    }
    public Customer(){
        homeaddress = new IAddressBank();
        custAccounts = new ArrayList<Account>();
        this.Edit(firstname, surname, DOB, numAccounts);
    }
    public void LoadAccounts(BufferedReader bin){
            Double Balance, Rate, AccountSpecificVal;
            String Record;
            Record = new String();
            String SortCode,NameOfBank,accountType;
            Integer AccountNo;
            try{
                for(int i = 0; i<numAccounts; i++){
                    Record=bin.readLine();
                    if (Record.equals("Current Account")){
                        accountType = Record;                    
                    }
                    else if (Record.equals("ISA")){
                        accountType = Record;
                    }
                    else{
                        accountType = Record;
                    }
                    Record=bin.readLine();
                    AccountSpecificVal = Double.parseDouble(Record);
                    Record=bin.readLine();
                    SortCode = Record;
                    Record=bin.readLine();
                    AccountNo = Integer.valueOf(Record);
                    Record=bin.readLine();
                    Balance = Double.parseDouble(Record);
                    Record=bin.readLine();
                    NameOfBank = Record; 
                    Record=bin.readLine();
                    Rate = Double.parseDouble(Record);
                    Record=bin.readLine();
                    this.AddAccount(accountType, true);
                    this.getAcconut().edit(AccountSpecificVal);
                    this.EditAccount(SortCode, AccountNo, Balance, NameOfBank, Rate);
                }
            }
            catch(IOException ioe){}
    }
    public void AddAccount(String type, Boolean fromFile){
        if ("Current Account".equals(type)){
            
            custAccount = new CurrentAccount();
            custAccounts.add(custAccount);
            if (!fromFile){
                numAccounts++;
            }
            
        }
        else if ("ISA".equals(type)){
            
            custAccount = new ISAAccount();
            custAccounts.add(custAccount);
            if (!fromFile){
                numAccounts++;
            }
            
        }
        else if ("Savings".equals(type)){
            
            custAccount = new SavingsAccount();
            custAccounts.add(custAccount);
            if (!fromFile){
                numAccounts++;
            }
            
        }
        
    }
    public Account getAcconut(){
        return custAccount;
    }
    public ArrayList<Account> getAccounts(){
        return custAccounts;
    }
}
