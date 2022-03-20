
package iteration2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lisaj
 */
public class OrderList {
    //This class is to allow you to add/remove orders from the orders .txt file.
    //Orders are automatically removed if the delivery date has passed.
    private ArrayList<Order> orders;
    
    public OrderList(){
        orders = new ArrayList<Order>();
        this.LoadListFromFile();
    }
    public ArrayList<Order> getList(){
        return orders;
    } 
    public void Add(Order order){
        orders.add(order);
    }
    public void Remove(Order order){
        orders.remove(order);
    }
    
        
    
    public void LoadListFromFile(){
        FileReader reader;
        try{
            reader = new FileReader("orderlist.txt");
            BufferedReader bin = new BufferedReader(reader);
            String Record;
            Record = new String();
            
            
            while((Record=bin.readLine()) !=null){
                Order order = new Order();
                order.LoadFromFile(bin);
                this.Add(order);
                
            }
        }
        catch(IOException ioe){
            System.out.println("Could not load Orders");
        }
    }
    public void SaveToFile(){
        FileWriter aWriter;
        try{
            aWriter = new FileWriter("orderlist.txt", false);//Do not append
            aWriter.write(System.getProperty("line.separator"));//Clear file
        }
        catch (IOException ioe){
            System.out.println("Could not clear file");
        }
        for (int i = 0; i<orders.size(); i++){//Save only the up-to-date orders to the list
            orders.get(i).SaveToFile();
        }
    }
}
