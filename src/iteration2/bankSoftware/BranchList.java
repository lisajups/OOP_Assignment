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
public class BranchList {
    private ArrayList<Branch> branches;
    
    public BranchList(){
        branches = new ArrayList<Branch>();
        this.LoadListFromFile();
    }
    public void Add(Branch branch){
        branches.add(branch);
    }
    public void Remove(Branch branch){
        branches.remove(branch);
    }
    public void Display(JTextArea branchJTextArea){
        for (int i = 0; i<branches.size(); i++){
            
            branches.get(i).Display(branchJTextArea);
        }
    }
    public int size(){
        return this.size();
    }
    public Branch get(int i){
        return this.get(i);
    }
    public void LoadListFromFile(){
        FileReader reader;
        
        try{
            reader = new FileReader("branches.txt");
            BufferedReader bin = new BufferedReader(reader);
            String Record;
            Record = new String();
            String workinghours, sortcode, manager, name, house_name, street,area,town,postcode,country;
            Integer house_number;
            Branch branch;
            
            while((Record=bin.readLine()) !=null){
                branch = new Branch();
                manager = Record;
                Record=bin.readLine();
                sortcode = Record;
                Record=bin.readLine();
                workinghours = Record;    
                Record=bin.readLine();
                branch.Edit(workinghours, sortcode, manager);
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
                branch.EditAddress(name, house_name, house_number, street, area, town, postcode, country);
                this.Add(branch);
            }
        }
        catch(IOException ioe){
            System.out.println("Could not load");
        }
    }
}
