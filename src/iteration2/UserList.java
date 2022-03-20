/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author abuba
 */
public class UserList {
    private ArrayList<User> users;
    
    public UserList(){
        users = new ArrayList<User>();
        this.LoadFromFile();
    }
    public User getUser(String inName, String inPassword){
        User user = new User();
        for (int i = 0; i<users.size(); i++){
            if (users.get(i).getUsername().equals(inName)&&users.get(i).getPassword().equals(inPassword)){
                user = users.get(i);
            }
        }
        return user;
    }
    public void Remove(User user){
        for (int i=0; i<users.size(); i++){
            if (user.getUsername().equals(users.get(i).getUsername())){
                users.remove(users.get(i));
            }
            
        }
        
    }
    public void Add(User user){
        users.add(user);
    }
    public void LoadFromFile(){
        String Record, name, password, role;
        int wishlistLength;
        User user;
        FileReader reader;
        try{
            reader = new FileReader("login.txt");
            BufferedReader bin = new BufferedReader(reader);
            Record = new String();
            while((Record=bin.readLine()) !=null){
                user = new User();
                name = Record;
                Record=bin.readLine();
                
                password = Record;
                Record=bin.readLine();
                role = Record;    
                Record=bin.readLine();
                wishlistLength = Integer.valueOf(Record);
                user.edit(name, password, role, wishlistLength);
                user.LoadWishlist(bin);
                users.add(user);
                
                Record=bin.readLine();
            }
            bin.close();
            bin = null;
        }
        catch (IOException ioe){
            System.out.println("Can't load users");
        }
    }
    public void SaveToFile(){
        FileWriter aWriter;
        try{
            aWriter = new FileWriter("login.txt", false);
        }
        catch (IOException ioe){
            System.out.println("Could not clear file");
        }
        for (int i = 0; i<users.size(); i++){
            users.get(i).SaveToFile(true);
        }
    }
}

