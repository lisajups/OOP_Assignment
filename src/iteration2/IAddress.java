package iteration2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author 2017554
 */
public class IAddress {
    private String house_name = "House Name";
    private Integer house_number = 1;
    private String street = "Street";
    private String town = "Town";
    private String postcode = "Postcode";
    private String country = "Country";
    
    public IAddress(){
        this.Edit(house_name, house_number, street, town, postcode, country);
    }
   
    public int getHouseNum(){
        return house_number;
    }

    public void Display(JTextArea src){
        //Displays all info in a jTextArea in a readable format
        src.append(this.ToString());
    }
    public void Edit(String strHouse_name, int houseNum, String strStreet, String strTown, String strPostcode, String strCountry){
        if (houseNum<0){
            JOptionPane.showMessageDialog(null, "Error: House number less than 0");
        }
        else{
            house_number = houseNum; 
        }
        town = strTown;
        street = strStreet;
        postcode = strPostcode;
        country = strCountry;
        house_name = strHouse_name;
        
    }
    public String ToString(){
        //Info about address in readable form
        String string;
        string = "\nHouse name: " + house_name + "\nHouse number: " + house_number.toString() + "\nStreet: "+ street + "\nTown: " + town +"\nPostcode: " + postcode + "\nCountry: " + country;
        return string;
    }

    public void SaveToFile(FileWriter aWriter){
        //Saves all information to a .txt file
        try{
            String string;
            string = house_name + "\n" + house_number.toString() + "\n"+ street + "\n" + town +"\n" + postcode + "\n" + country;
            aWriter.write(string+System.getProperty("line.separator"));
            aWriter.write("##"+System.getProperty("line.separator"));
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
    public void LoadFromFile(BufferedReader bin){
        //Loads information about address from .txt file
        String Record;
        
        try{
            Record = new String();
            Record=bin.readLine();
            Record=bin.readLine();
            house_name = Record;
            Record=bin.readLine();
            house_number = Integer.valueOf(Record);
            Record=bin.readLine();
            street = Record;
            Record=bin.readLine();
            town = Record;
            Record=bin.readLine();
            postcode = Record;
            Record=bin.readLine();
            country = Record; 
            this.Edit(house_name, house_number, street, town, postcode, country);
            Record=bin.readLine();
                
            
        }
        catch(IOException ioe){
            System.out.println("Could not load (Address)");
        }
    }
    
}