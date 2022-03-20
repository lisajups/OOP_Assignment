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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author 2017554
 */
public class IAddressBank {
    private String name = "Address";
    private String house_name = "House Name";
    private Integer house_number = 1;
    private String street = "Street";
    private String area = "Area";
    private String town = "Town";
    private String postcode = "Postcode";
    private String country = "Country";
    public IAddressBank(){
        this.Edit(name, house_name, house_number, street, area, town, postcode, country);
    }
   
    public String GetName(){
        return name;    
    }
    public int getHouseNum(){
        return house_number;
    }

    public void Display(JTextArea src){
        src.append(this.ToString());
    }
    public void Edit(String strName, String strHouse_name, int houseNum, String strStreet, String strArea, String strTown, String strPostcode, String strCountry){
        if (houseNum<0){
            JOptionPane.showMessageDialog(null, "Error: House number less than 0");
        }
        else{
            house_number = houseNum; 
        }
        name = strName;
        town = strTown;
        street = strStreet;
        area = strArea;
        postcode = strPostcode;
        country = strCountry;
        house_name = strHouse_name;
        
    }
    public String ToString(){
        String string;
        string = "\nName: "+ name + "\nHouse name: " + house_name + "\nHouse number: " + house_number.toString() + "\nStreet: "+ street + "\nArea: " + area + "\nTown: " + town +"\nPostcode: " + postcode + "\nCountry: " + country;
        return string;
    }
    
    public void SaveToFile(FileWriter aWriter){
        try{
            String string;
            string = name + "\n" + house_name + "\n" + house_number.toString() + "\n"+ street + "\n" + area + "\n" + town +"\n" + postcode + "\n" + country;
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
                this.Edit(name, house_name, house_number, street, area, town, postcode, country);
                
            }
        }
        catch(IOException ioe){
            System.out.println("Could not load");
        }
    }
    
}