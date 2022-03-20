package iteration2.bankSoftware;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2017554
 */
public class CustomerList{
    private ArrayList<Customer> clients;
    
    public CustomerList(){
        clients = new ArrayList<Customer>();
        this.LoadListFromFile();
    }
    public void Add(Customer customer){
        clients.add(customer);
    }
    public void Remove(Customer customer){
        clients.remove(customer);
    }
    public void Remove(String surname, String firstName){
        for (int i = 0; i<clients.size(); i++){
            if(clients.get(i).getSurname().equals(surname) && clients.get(i).getFirstName().equals(firstName)){
                clients.remove(i);
            }
        }
    }
    public Customer find(Customer inInfo, JTextArea text){
        boolean Found = false;
        Customer foundCustomer = new Customer();
        for (int i = 0; i<clients.size(); i++){
            if(clients.get(i).getSurname().equals(inInfo.getSurname()) && (clients.get(i).getFirstName().equals(inInfo.getFirstName())) && (clients.get(i).getDOB().equals(inInfo.getDOB()))){
                Found = true;
                foundCustomer = clients.get(i);
            }
        }
        if (!Found){
           text.append("Customer not found.\n");
           return inInfo; 
        }
        else{
            text.append("Customer found.\n");
            foundCustomer.Display(text);
            return foundCustomer;
        }
    }
    public boolean isCustomer(Customer inInfo){
        boolean Found = false;
        for (int i = 0; i<clients.size(); i++){
            if(clients.get(i).getSurname().equals(inInfo.getSurname()) && (clients.get(i).getFirstName().equals(inInfo.getFirstName())) && (clients.get(i).getDOB().equals(inInfo.getDOB()))){
                Found = true;
            }
        }
        return Found;
    }
    public void Display(JTextArea clientJTextArea){
        for (int i = 0; i<clients.size(); i++){
            clients.get(i).Display(clientJTextArea);
            /*clients.get(i).homeaddress.Display(clientJTextArea);*/
        }
    }
    public int size(){
        return this.size();
    }
    public Customer get(int i){
        return this.get(i);
    }
    public boolean custAccountExists(String inSortCode, int inAccountNum){
        boolean found = false;
        for (int i = 0; i<clients.size(); i++){
            found = clients.get(i).hasAccount(inSortCode, inAccountNum);
        }
        return found;
    }
    public Account findCustAccount(String inSortCode, int inAccountNum){
        boolean found = false;
        Account account = null;
        for (int i = 0; i<clients.size(); i++){
            found = clients.get(i).hasAccount(inSortCode, inAccountNum);
            if (found){
                account = clients.get(i).getAccount(inSortCode, inAccountNum);
                return account;
            }
        }
        return account;        
    }
    
    public void LoadListFromFile(){
        FileReader reader;
        try{
            reader = new FileReader("customers.txt");
            BufferedReader bin = new BufferedReader(reader);
            String Record;
            Record = new String();
            String firstname, surname, DOB, name, house_name, street,area,town,postcode,country,SortCode,NameOfBank,accountType;
            Integer house_number, AccountNo, numAccounts;
            Double Balance, Rate, AccountSpecificVal;
            Customer customer;
            
            while((Record=bin.readLine()) !=null){
                customer = new Customer();
                firstname = Record;
                Record=bin.readLine();
                surname = Record;
                Record=bin.readLine();
                DOB = Record;    
                Record=bin.readLine();
                numAccounts = Integer.valueOf(Record);
                Record=bin.readLine();
                customer.Edit(firstname, surname, DOB, numAccounts);
                name = Record;
                Record=bin.readLine();
                house_name = Record;
                Record=bin.readLine();
                house_number = Integer.valueOf(Record);
                Record=bin.readLine();
                street = Record;
                Record=bin.readLine();
                area = Record;
                Record=bin.readLine();
                town = Record;
                Record=bin.readLine();
                postcode = Record;
                Record=bin.readLine();
                country = Record; 
                Record=bin.readLine();
                customer.EditAddress(name, house_name, house_number, street, area, town, postcode, country);
                customer.LoadAccounts(bin);
                
                
                this.Add(customer);
            }
        }
        catch(IOException ioe){
            System.out.println("Could not load");
        }
    }
}
