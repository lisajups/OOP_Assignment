package iteration2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author 2017554
 */
public class Product {
    private int ID = 1001;
    private String name = "Default Name";
    private double weight = 1;
    private String weightClass = "kg";
    private int stock = 10;
    private int minStock = 2;
    private int maxStock = 15;
    private LocalDate expiryDate;
    private LocalDate lastOrdered;
    private LocalDate dateToOrder;
    private Supplier deliveryCompany;
    private String type = "Food";
    private Boolean luxuryItem = false;
    private double priceVAT;
    private double priceNoVAT = 1.00;
    
    public Product(){
        deliveryCompany = new Supplier();
        priceVAT = this.calculateVAT(priceNoVAT);
        lastOrdered = LocalDate.now();
        expiryDate = LocalDate.now().plusDays(7);
        dateToOrder = lastOrdered.plusDays(4);
    }
    
    public Product(int newID, String newName, double newWeight, String newWeightClass, int newStock, int newMinStock, int newMaxStock, Supplier newDeliveryCompany, String newType, Boolean newLuxuryItem, double newPriceNoVAT){
        this.edit(newID, newName, newWeight, newWeightClass, newStock, newMinStock, newMaxStock, newDeliveryCompany, newType, newLuxuryItem, newPriceNoVAT);
        lastOrdered = LocalDate.now();
        expiryDate = LocalDate.now().plusDays(7);
        dateToOrder = lastOrdered.plusDays(4);
    }
    
    public String getImage(){
        //A product image is displayed next to the jTextArea that shows prod info
        //This gets the location of that image
        String filename = "Images/" + name + ID + ".jpg";
        filename = filename.replace(" ", "_");
        return filename;
    }
    
    public void edit(int newID, String newName, double newWeight, String newWeightClass, 
                     int newStock, int newMinStock, int newMaxStock, Supplier newDeliveryCompany, 
                     String newType, Boolean newLuxuryItem, double newPriceNoVAT){
        
        ID = newID;
        name = newName;
        weight = newWeight;
        weightClass = newWeightClass;
        stock = newStock;
        minStock = newMinStock;
        maxStock = newMaxStock;
        deliveryCompany = newDeliveryCompany;
        type = newType;
        luxuryItem = newLuxuryItem;
        priceNoVAT = newPriceNoVAT;
        
        priceVAT = this.calculateVAT(priceNoVAT);
    }
    
    public double calculateVAT(double price){
        if (luxuryItem){
            price = price*1.2;
        }
        return price;
    }
    
    public int getStock(){
        return stock;
    }
    public int getMaxStock(){
        return maxStock;
    }
    
    public void order(){
        lastOrdered = LocalDate.now();
    }
    public void reStock(int amount){
        if (stock+amount<=maxStock){
            stock = stock + amount;
            expiryDate = LocalDate.now().plusDays(7);
            dateToOrder = lastOrdered.plusDays(4);
            
        }
        else{
            System.out.println("Too much stock to order that much.");
        }
    }
    
    public void checkStock(JDialog warningMessageFrame){
        //Called when the program starts
        if (stock<=minStock){
            JOptionPane.showMessageDialog(warningMessageFrame, "WARNING: " + name + " is low on stock!");
        }
    }
    
    public void purchase(int amount){
        stock = stock-amount;
    }
    
    public String getName(){
        return name;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setName(String nName){
        name = nName;
    }
    
    public void setID(int nID){
        ID = nID;
    }
    
    public Supplier getCompany(){
        return deliveryCompany;
    }
    public Double getPrice(){
        return priceVAT;
    }
    public LocalDate getLastDate(){
        return lastOrdered;
    }
//    public String toString(){
//        //Owner can see this information in a jTextArea, customers don't need this much info
//        String rtrnString;
//        
//        
//        rtrnString = ("ID: " + ID +"\nName: " + name + "\nWeight: " + weight + "\nWeight Class: " 
//                    + weightClass + "\nStock: " + stock + "\nMin Stock: " + minStock + "\nMax Stock: " 
//                    + maxStock + "\nType: " + type + "\nLuxury Item: " + luxuryItem + "\nPrice without VAT: " 
//                    + priceNoVAT + "\nPrice with VAT: " + priceVAT + "\nDate last ordered: " 
//                    + (lastOrdered.format(DateTimeFormatter.ISO_DATE)) + "\nDate to order: " 
//                    + (dateToOrder.format(DateTimeFormatter.ISO_DATE)) + "\nExpiry date: " 
//                    + (expiryDate.format(DateTimeFormatter.ISO_DATE)));
//        return rtrnString;
//    }
    
    public void display(JTextArea textArea){
        textArea.selectAll();
        textArea.replaceSelection("");
        textArea.append("ID: " + ID +"\nName: " + name + "\nWeight: " + weight + weightClass + 
                        "\nStock: " + stock + "\nMin Stock: " + minStock + "\nMax Stock: " + 
                        maxStock + "\nSupplier: " + deliveryCompany.getName() + "\nType: " + 
                        type + "\nLuxury Item: " + luxuryItem + "\nPrice without VAT: £" + 
                        priceNoVAT + "\nPrice with VAT: £" + priceVAT + "\nExpiry date: " +
                        (expiryDate.format(DateTimeFormatter.ISO_DATE)));
    }
    
    public String listString(){
        //This formats the display for use in a jList. it needs HTML  (?)
        return ("<html>ID: " + ID +"<br>Name: " + name + "<br>Weight: " + weight + weightClass + 
                        "<br>Stock: " + stock + "<br>Min Stock: " + minStock + "<br>Max Stock: " + 
                        maxStock + "<br>Supplier: " + deliveryCompany.getName() + "<br>Type: " + 
                        type + "<br>Luxury Item: " + luxuryItem + "<br>Price without VAT: £" + 
                        priceNoVAT + "<br>Price with VAT: £" + priceVAT + "<br>Expiry date: " +
                        (expiryDate.format(DateTimeFormatter.ISO_DATE)) + "<br></span></html>");
    }
    public String wishlistString(){
        //Also for displaying info in a List
        return ("<html>ID: " + ID +"<br>Name: " + name + "<br>Weight: " + weight + weightClass + 
                        "<br>Price with VAT: £" + priceVAT + "<br>Expiry date: " +
                        (expiryDate.format(DateTimeFormatter.ISO_DATE)) + "<br></span></html>");
    }
    public String basketString(int Quantity){
        //Also for displaying info in a List
        return ("<html>ID: " + ID +"<br>Name: " + name + "<br>Weight: " + weight + weightClass + 
                        "<br>Price with VAT: £" + priceVAT + "<br>Expiry date: " +
                        (expiryDate.format(DateTimeFormatter.ISO_DATE)) + "<br>Quanitity: " + 
                        Quantity + "<br>Subtotal: £" + priceVAT*Quantity + "<br></span></html>");
    }
    
    
    public void SaveToFile(boolean append){
        FileWriter aWriter;
        try{
            aWriter = new FileWriter("productlist.txt", append);
            String string;
            string = ("\n" + ID +"\n" + name + "\n" + weight + "\n" + weightClass + "\n" + stock + "\n" + minStock + "\n" 
                    + maxStock + "\n" + type + "\n" + luxuryItem + "\n" + priceNoVAT + "\n" + priceVAT + "\n" 
                    + (lastOrdered.format(DateTimeFormatter.ISO_DATE)) + "\n" + (dateToOrder.format(DateTimeFormatter.ISO_DATE)) 
                    + "\n" + (expiryDate.format(DateTimeFormatter.ISO_DATE)));
            aWriter.write(string+System.getProperty("line.separator"));
            aWriter.write("##"+System.getProperty("line.separator"));
            this.getCompany().SaveToFile(aWriter);
            aWriter.flush();
            aWriter.close();
            aWriter = null;
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
    
    public void LoadFromFile(BufferedReader bin){
        
        try{
            String Record;
            Record = new String();
            Record=bin.readLine();
            ID = Integer.valueOf(Record);
            Record=bin.readLine();
            name = Record;
            Record=bin.readLine();
            weight = Double.valueOf(Record); 
            Record=bin.readLine();
            weightClass = Record; 
            Record=bin.readLine();
            stock = Integer.valueOf(Record); 
            Record=bin.readLine();
            minStock = Integer.valueOf(Record); 
            Record=bin.readLine();
            maxStock = Integer.valueOf(Record);
            Record=bin.readLine();
            type = Record;
            Record=bin.readLine();
            if ("false".equals(Record)){
                luxuryItem = false;
            }
            else{
                luxuryItem = true;
            }
            Record=bin.readLine();
            priceNoVAT = Double.valueOf(Record);
            Record=bin.readLine();
            priceVAT = Double.valueOf(Record);
            Record=bin.readLine();
            lastOrdered = LocalDate.parse(Record);
            Record=bin.readLine();
            dateToOrder = LocalDate.parse(Record);
            Record=bin.readLine();
            expiryDate = LocalDate.parse(Record);
            Record=bin.readLine();
            this.getCompany().LoadFromFile(bin);
            
        }
        catch(IOException ioe){
            System.out.println("Could not load (Product)");
        }
    }
}
