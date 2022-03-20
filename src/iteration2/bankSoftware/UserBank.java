package iteration2.bankSoftware;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2017554
 */
public class UserBank {
    private String Name;
    private String Role = "Bank Employee";
    private String password;
    private String FileName = "login_bank.txt";
    
    public UserBank(){
        //
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
}
