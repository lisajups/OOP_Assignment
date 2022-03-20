
package iteration2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author 2017554
 */
public class Supplier {
    private String name = "Default Name";
    private IAddress address;
    private String phoneNo = "12341234";
    
    public Supplier(){
        address = new IAddress();
    }
    
    public void edit(String newName, String newPhoneNo, IAddress newAddress){
        name = newName;
        phoneNo = newPhoneNo;
        address = newAddress;
    }
    
    public IAddress getAddress(){
        return address;
    }
    
    public String getName(){
        return name;
    }
    
    public void display(JTextArea src){
        src.selectAll();
        src.replaceSelection("");
        src.append("Name: " + name + "\nPhone number: " + phoneNo + "\nAddress: " + address.ToString());
    }
    public void setName(String in){
        name = in;
    }
    
    public void SaveToFile(FileWriter aWriter){
        try{
            String string;
            string = name + "\n" + phoneNo;
            aWriter.write(string+System.getProperty("line.separator"));
            aWriter.write("##"+System.getProperty("line.separator"));
            this.getAddress().SaveToFile(aWriter);
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
    
    public void SaveToFile(){
        FileWriter aWriter;
        try{
            aWriter = new FileWriter("supplierlist.txt", true);
            String string;
            string = "\n" + name + "\n" + phoneNo;
            aWriter.write(string+System.getProperty("line.separator"));
            aWriter.write("##"+System.getProperty("line.separator"));
            this.getAddress().SaveToFile(aWriter);
            aWriter.flush();
            aWriter.close();
            aWriter = null;
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
    
    void LoadFromFile(BufferedReader bin) {
        String Record;
        
        try{
            Record = new String();
            Record=bin.readLine();
            name = Record;
            Record = bin.readLine();
            phoneNo = Record;
                
            this.getAddress().LoadFromFile(bin);
            
        }
        catch(IOException ioe){
            System.out.println("Could not load (Supplier)");
        }
    }
}
