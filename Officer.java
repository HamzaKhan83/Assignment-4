package SemesterProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.*;

public class Officer extends Person  {
    ArrayList<Report> assignedCases;
   private String officerId;
   File officerFile =new File("C:\\\\Users\\\\Hp\\\\Desktop\\\\java\\\\OfficerData.txt");



//private static int nextId=1;

    Officer(String name,int age,String residence,String cnic){
        super(name, age, residence,cnic);
        assignedCases=new ArrayList<>();
        this.officerId=setId();
       
    }
    public String getOfficerId() {
        return officerId;
    }
    

    
    public String setId() {

        ArrayList<Officer> officers=loadOfficerFromFile();
        for(Officer o:officers){
       if(this.getCnic().equals(o.getCnic())){
        this.officerId=o.getOfficerId();
       }

        }
        int size=officers.size();
        String generatedId=String.format("Off-%03d",++size);
        return generatedId;
    }


    public ArrayList<Officer> loadOfficerFromFile(){
ArrayList<Officer> officerFromFile=new ArrayList<>();
try{
    if(officerFile.exists()){
        ObjectInputStream ois =new ObjectInputStream(new FileInputStream(officerFile));
        officerFromFile=(ArrayList<Officer>)ois.readObject();
    }
}catch(IOException | ClassNotFoundException e){
    System.out.println(e.getMessage()+" error is in load officer from file");
}

return officerFromFile;


}

@Override
public boolean equals(Object obj){
if(obj==this) return true;
if(!(obj instanceof Officer)) return false;

Officer o=(Officer)obj;
return this.getCnic().equals(o.getCnic());
}

@Override
public String toString(){

  return " Name: "+this.getName()+" ID: "+this.getOfficerId()+" Cnic: "+this.getCnic()+" Residence: "+this.getResidence();
}
              
}
