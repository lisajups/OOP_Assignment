package iteration2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author 2017554
 */
public class Order {
    private int productID = 1;
    private int productQuantity = 10;
    private Double subtotal = 8.0;
    private Double deliveryCharge = 1.50;
    private Double total = 9.50;
    private LocalDate deliveryDate;
    private Supplier deliveryCompany;
    private Product product;
    
    public Order(){
        deliveryDate = LocalDate.now().plusDays(2);
        deliveryCompany = new Supplier();
    }
    public void setProduct(Product inProduct){
        product = inProduct;
        productID = product.getID();
    }
    public int getProdID(){
        return productID;
    }
    public int getQuantity(){
        return productQuantity;
    }
    public double customerSubtotal(int newProductQuantity){
        return (newProductQuantity*product.getPrice());
    }
    public Product getProduct(){
        return product;
    }
    public void placeOrder(int newProductQuantity, Product orderedProduct){
        productID = orderedProduct.getID();
        productQuantity = newProductQuantity;
        subtotal = orderedProduct.getPrice() * productQuantity;
        total = subtotal+1.5; //£1.50 default delivery fee
        deliveryCompany = orderedProduct.getCompany();
    }
    public boolean checkDate(JDialog warningMessageFrame){
        //Called when loading Orders from file upon opening program
        if (LocalDate.now().compareTo(deliveryDate)>=0){
            JOptionPane.showMessageDialog(warningMessageFrame, "Your order from " + deliveryCompany.getName()+" has arrived");
            return true;
        }
        else{
            return false;
        }
    }
    
    public void SaveToFile(){
        //Saves all information to a dedicated Orders .txt file
        FileWriter aWriter;
        try{
            aWriter = new FileWriter("orderlist.txt", true);
            String string;
            string = ("\n" + productID +"\n" + productQuantity + "\n" + subtotal + "\n" + deliveryCharge + "\n" + total + "\n" + (deliveryDate.format(DateTimeFormatter.ISO_DATE)));
            aWriter.write(string+System.getProperty("line.separator"));
            aWriter.write("##"+System.getProperty("line.separator"));
            this.deliveryCompany.SaveToFile(aWriter);
            aWriter.flush();
            aWriter.close();
            aWriter = null;
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }
    void LoadFromFile(BufferedReader bin) {
        //Loads order info from the Orders .txt file.
        String Record;
        
        try{
            Record = new String();
            Record=bin.readLine();
            productID = Integer.valueOf(Record);
            Record = bin.readLine();
            productQuantity = Integer.valueOf(Record);
            Record = bin.readLine();
            subtotal = Double.valueOf(Record);
            Record = bin.readLine();
            deliveryCharge = Double.valueOf(Record);
            Record = bin.readLine();
            total = Double.valueOf(Record);
            Record = bin.readLine();
            deliveryDate = LocalDate.parse(Record);
            Record = bin.readLine();
            this.deliveryCompany.LoadFromFile(bin);
            
            
        }
        catch(IOException ioe){
            System.out.println("Could not load (Supplier)");
        }
    }
}
