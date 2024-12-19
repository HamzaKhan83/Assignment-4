package SemesterProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Criminal extends Person {

    private Crime crimeDone;
    private static int nextId=1;
    private String criminalId;
    private static final long serialVersionUID=1L;
    File file = new File("C:\\Users\\Hp\\Desktop\\java\\CriminalData.txt");




    Criminal(String name,int age, String residence,String cnic,String crime){
   
        super(name, age, residence,cnic);
        this.crimeDone=new Crime(crime);
        this.criminalId=setCriminalId();
    }


    public String setCriminalId(){
           
        ArrayList<Criminal> criminals=new ArrayList<>();
        criminals=loadArrayFromFile();
       
        for(Criminal c: criminals){
            if(this.getCnic().equals(c.getCnic())){
              // this.criminalId=c.criminalId;
                break;
            }
        }
       
            int size=criminals.size();



            String generatedId=String.format("CRX-%03d",++size );
             return generatedId;
             
        

       //return null;
     }

    public Crime getCrimeDone() {
        return crimeDone;
    }

    public void setCrimeDone(Crime crimeDone) {
        this.crimeDone = crimeDone;
    }

  

 public int getNextId(){
    return nextId;
 }

 


 public String getCriminalId() {
    return criminalId;
}


@Override
public String toString(){
    return " Name:"+this.getName()+" ID: "+this.getCriminalId()+" Age: "+this.getAge()+" Residence: "+this.getResidence()+" CNIC: "+this.getCnic()+" Crime: "+this.getCrimeDone().getCrimeName();
}

@Override
public boolean equals(Object obj){
if(this==obj) return true;
if(!( obj instanceof Criminal)) return false;
Criminal c=(Criminal)obj;
//this.getCriminalId().equals(c.getCriminalId()) ||
return  this.getCnic().equals(c.getCnic());
}

  public ArrayList<Criminal> loadArrayFromFile() {
        ArrayList<Criminal> criminalListFromFile = new ArrayList<>();
        try {
            if (file.exists()) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                    criminalListFromFile = (ArrayList<Criminal>) objectInputStream.readObject();
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error while reading from file: " + e.getMessage());
        }
        return criminalListFromFile;
    }
    
}
