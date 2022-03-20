
package iteration2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JTextArea;

/**
 *
 * @author 2017554
 */
public class ProductList {
    private ArrayList<Product> products;
    
    public ProductList(){
        products = new ArrayList<Product>();
        this.LoadListFromFile();
    }
    public ArrayList<Product> getList(){
        return products;
    } 
    public void Add(Product product){
        products.add(product);
    }
    public void Remove(Product product){
        //Remove by Product Object
        products.remove(product);
    }
    public void Remove(int ID){
        //Remove by ID
        for (int i = 0; i<products.size(); i++){
            if(products.get(i).getID() == ID){
                products.remove(i);
            }
        }
    }
    public Product find(Product inInfo){
        Product foundProduct = new Product();
        for (int i = 0; i<products.size(); i++){
            if(products.get(i).getID() == (inInfo.getID())){
                foundProduct = products.get(i);
            }
        }
        return foundProduct;
    }
    public Product find(Product inInfo, JTextArea text){
        //Finds product & displays it if found, if not found displays error message
        boolean Found = false;
        Product foundProduct = new Product();
        for (int i = 0; i<products.size(); i++){
            if(products.get(i).getName().equals(inInfo.getName())){
                foundProduct = products.get(i);
                Found = true;
            }
            if(products.get(i).getID() == (inInfo.getID())){
                foundProduct = products.get(i);
                Found = true;
            }
        }
        
        if (!Found){
           text.selectAll();
           text.replaceSelection("");
           text.append("Product not found.\n");
           return inInfo; 
        }
        else{
            foundProduct.display(text);
            return foundProduct;
        }
    }
    public boolean isProduct(Product inInfo){
        boolean Found = false;
        for (int i = 0; i<products.size(); i++){
            if((products.get(i).getID() == inInfo.getID())){
                Found = true;
            }
        }
        return Found;
    }
    public void Display(JTextArea clientJTextArea){
        for (int i = 0; i<products.size(); i++){
            products.get(i).display(clientJTextArea);
        }
    }
    public int size(){
        return products.size();
    }
    public Product get(int i){
        return products.get(i);
    }
    public void checkStock(JDialog warningMessageFrame){
        for (int i = 0; i<products.size(); i++){
            products.get(i).checkStock(warningMessageFrame);
        }
    }
    public void LoadListFromFile(){
        FileReader reader;
        try{
            reader = new FileReader("productlist.txt");
            BufferedReader bin = new BufferedReader(reader);
            String Record;
            Record = new String();
            
            
            while((Record=bin.readLine()) !=null){
                Product product = new Product();
                product.LoadFromFile(bin);
                this.Add(product);
                

            }
        }
        catch(IOException ioe){
            System.out.println("Could not load Products");
        }
    }
    public void SaveToFile(){
        FileWriter aWriter;
        try{
            aWriter = new FileWriter("productlist.txt", false);
            aWriter.write(System.getProperty("line.separator"));
        }
        catch (IOException ioe){
            System.out.println("Could not clear file");
        }
        for (int i = 0; i<products.size(); i++){
            products.get(i).SaveToFile(true);
        }
    }
}
