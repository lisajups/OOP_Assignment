package iteration2.bankSoftware;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextArea;
/**
 *
 * @author 2017554
 */
public class Branch {
    private String WorkingHours = "9:00-17:00";
    private String SortCode = "00-00-00";
    private String Manager = "TBA";
    private IAddressBank Address;
    private String BranchFileName = "branches.txt";
    
    public Branch(String corpConstructor){
        Address = new IAddressBank();
        this.LoadFromFile();
    }
    public Branch(){
        Address = new IAddressBank();
        this.Edit(WorkingHours, SortCode, Manager);
    }

    public void EditAddress(String strName, String strHouse_name, int houseNum, String strStreet, String strArea, String strTown, String strPostcode, String strCountry){
        Address.Edit(strName, strHouse_name, houseNum, strStreet, strArea, strTown, strPostcode, strCountry);
    }
    public void Edit(String inWorkingHours, String inSortCode, String inManager){
        WorkingHours = inWorkingHours;
        Manager = inManager;
        if (inSortCode.length() == 8){
            SortCode = inSortCode;
        }
        else{
            //sort code too short/long
        }
    }
    public void Display(JTextArea src){
        
        src.append("Branch hours: "+ WorkingHours + "\nSort Code: " + SortCode + "\nManager: " + Manager); 
        Address.Display(src);
    }
    public void SaveToFile(boolean append){
        try{
            FileWriter writer;
            writer = new FileWriter(BranchFileName, append);
            String string = Manager + "\n" + SortCode + "\n" + WorkingHours;
            writer.write(string+System.getProperty("line.separator"));
            Address.SaveToFile(writer);
            writer.flush();
            writer.close();
            writer = null;
        }
        catch(IOException ioe){
            System.out.println("Could not save");
        }
    }
    public void LoadFromFile(){
        FileReader reader;
        try{
            reader = new FileReader("branches.txt");
            BufferedReader bin = new BufferedReader(reader);
            String Record,name, house_name, street,area,town,postcode,country;
            Integer house_number;
            Record = new String();
            Record=bin.readLine();
            Manager = Record;
            Record=bin.readLine();
            SortCode = Record;
            Record=bin.readLine();
            WorkingHours = Record;
            Record=bin.readLine();
            name = Record;
            Record=bin.readLine();
            house_name = Record;
            Record=bin.readLine();
            house_number = Integer.valueOf(Record);
            Record=bin.readLine();
            street = Record;
            Record=bin.readLine();
            area = Record;
            Record=bin.readLine();
            town = Record;
            Record=bin.readLine();
            postcode = Record;
            Record=bin.readLine();
            country = Record; 
            Record=bin.readLine();
            this.EditAddress(name, house_name, house_number, street, area, town, postcode, country);
        }
        catch(IOException ioe){
            System.out.println("Could not load");
        }
    }
}
