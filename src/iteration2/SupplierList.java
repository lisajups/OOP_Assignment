
package iteration2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author lisaj
 */
public class SupplierList {
    private ArrayList<Supplier> suppliers;
    
    public SupplierList(){
        suppliers = new ArrayList<Supplier>();
        this.LoadListFromFile();
    }
    
    public ArrayList<Supplier> getList(){
        return suppliers;
    }
    
    public void Add(Supplier supplier){
        suppliers.add(supplier);
    }
    
    public void Remove(Supplier supplier){
        suppliers.remove(supplier);
    }
    
    public int size(){
        return suppliers.size();
    }
    public boolean isSupplier(Supplier inInfo){
        boolean Found = false;
        for (int i = 0; i<suppliers.size(); i++){
            if((suppliers.get(i).getName() == inInfo.getName())){
                Found = true;
            }
        }
        return Found;
    }
    public Supplier get(int i){
        return suppliers.get(i);
    }
    public Supplier find(Supplier inInfo, JTextArea text){
        //Finds supplier & displays it if found, if not found displays error message
        boolean Found = false;
        Supplier foundSupplier = new Supplier();
        for (int i = 0; i<suppliers.size(); i++){
            if(suppliers.get(i).getName().equals(inInfo.getName())){
                foundSupplier = suppliers.get(i);
                Found = true;
            }
        }
        
        if (!Found){
           text.append("Supplier not found.\n");
           return inInfo; 
        }
        else{
            foundSupplier.display(text);
            return foundSupplier;
        }
    }
    public void LoadListFromFile(){
        FileReader reader;
        try{
            reader = new FileReader("supplierlist.txt");
            BufferedReader bin = new BufferedReader(reader);
            String Record;
            Record = new String();
            
            
            while((Record=bin.readLine()) !=null){
                Supplier supplier = new Supplier();
                supplier.LoadFromFile(bin);
                this.Add(supplier);
                
            }
        }
        catch(IOException ioe){
            System.out.println("Could not load Suppliers");
        }
    }
    public void SaveToFile(){
        FileWriter aWriter;
        try{
            aWriter = new FileWriter("supplierlist.txt", false);
            aWriter.write(System.getProperty("line.separator"));
        }
        catch (IOException ioe){
            System.out.println("Could not clear file");
        }
        for (int i = 0; i<suppliers.size(); i++){
            suppliers.get(i).SaveToFile();
        }
    }
}
