package iteration2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 2017554
 */
public class User {
    private String Name;
    private String Role = "Bank Employee";
    private String password;
    private String FileName = "login.txt";
    private int wishlistLength = 0;
    private ArrayList<String> wishlist;
    
    public User(){
        wishlist = new ArrayList<String>();
    }
    public void edit(String inName, String inPassword, String inRole, int inWLL){
        Name = inName;
        Role = inRole;
        password = inPassword;
        wishlistLength = inWLL;
    }
    public Boolean usernameUnique(String newName){
        Name = newName;
        boolean isUnique = true;
        String Record;
        FileReader reader;
        try{
            reader = new FileReader(FileName);
            BufferedReader bin = new BufferedReader(reader);
            Record = new String();
            while((Record=bin.readLine()) !=null){
                if (newName.contentEquals(Record)){
                    isUnique = false;
                }
            }
            bin.close();
            bin = null;
        }
        catch (IOException ioe){
            isUnique = false;
        }
        return isUnique;
    }
    public Boolean isRegistered(String newName, String newPassword, String role){
        Name = newName;
        boolean isRegistered;
        FileWriter writer;
        try{
            writer = new FileWriter(FileName, true);
            writer.write(Name+System.getProperty("line.separator"));
            writer.write(newPassword+System.getProperty("line.separator"));
            writer.write(role+System.getProperty("line.separator"));
            writer.write("0"+System.getProperty("line.separator"));
            writer.write("##"+System.getProperty("line.separator"));
            isRegistered = true;
            writer.flush();
            writer.close();
            writer = null;
        }
        catch (IOException ioe){
            isRegistered = false;
        }
        return isRegistered;
    }
    public Boolean isUser(String newName, String newPassword){
        Name = newName;
        boolean isFound = false;
        String Record;
        FileReader reader;
        try{
            reader = new FileReader(FileName);
            BufferedReader bin = new BufferedReader(reader);
            Record = new String();
            while((Record=bin.readLine()) !=null){
                if (newName.contentEquals(Record)){
                    Record = bin.readLine();
                    if (newPassword.contentEquals(Record)){
                       isFound = true; 
                       password = newPassword;
                       
                    }
                }
            }
            bin.close();
            bin = null;
        }
        catch (IOException ioe){
            isFound = false;
        }
        return isFound;
    }
    public String getRole(String newName, String newPassword){
        Name = newName;
        String getRole = "";
        String Record;
        FileReader reader;
        try{
            reader = new FileReader(FileName);
            BufferedReader bin = new BufferedReader(reader);
            Record = new String();
            while((Record=bin.readLine()) !=null){
                if (newName.contentEquals(Record)){
                    Record = bin.readLine();
                    if (newPassword.contentEquals(Record)){
                        Record = bin.readLine();
                        getRole = Record;
                        Role = getRole;
                    }
                }
            }
            bin.close();
            bin = null;
        }
        catch (IOException ioe){
            getRole = "Unavailable";
        }
        return getRole;
        
    }

    public void updateWishlist(ArrayList<String> newWishlist){
        wishlist = newWishlist;
    }
    public void LoadWishlist(BufferedReader bin){
        String Record, wishlistItem;
        Record = new String();
        try{
            for(int i = 0; i<wishlistLength; i++){
                Record=bin.readLine();
                Record=bin.readLine();
                wishlistItem = Record;
                Record=bin.readLine();
                wishlistItem = wishlistItem +"\n" + Record;
                wishlist.add(wishlistItem);
            }
        }
        catch(IOException ioe){
            System.out.println("Can't load wishlist");
        }
    }
    public String getUsername(){
        return Name;
    }
    public String getPassword(){
        return password;
    }
    public ArrayList<String> getWishlist(){
        return wishlist;
    }
    public void SaveToFile(boolean append){
        FileWriter aWriter;
        try{
            aWriter = new FileWriter("login.txt", append);
            String string;
            string = (Name +"\n" + password + "\n" + Role + "\n" + wishlistLength);
            aWriter.write(string+System.getProperty("line.separator"));
            aWriter.write("##"+System.getProperty("line.separator"));
            
            for (int i=0; i<wishlist.size(); i++){ 
                
                string = (wishlist.get(i));
                
                aWriter.write(string+System.getProperty("line.separator"));
                aWriter.write("##"+System.getProperty("line.separator"));
            }
            aWriter.flush();
            aWriter.close();
            aWriter = null;
        }
        catch (IOException ioe){
            System.out.println("Could not save");
        }
    }

    public void setWishlistLength(int i){
        wishlistLength = i;
    }
}
