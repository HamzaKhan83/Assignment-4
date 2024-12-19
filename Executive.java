package SemesterProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Executive extends Person {
    private String executiveId;
    private String rank;
    private String executivePassword;
    private static final long serialVersionUID = 3265662807052325554L;

    
    ArrayList<Report> reportsFiled;

   // public static int nextId = 1;
    ArrayList<Officer> officerList;
    File executiveFile=new File("C:\\\\Users\\\\Hp\\\\Desktop\\\\java\\\\ExecutiveData.txt");
  

    // Constructor
    Executive(String name, int age, String residence,String cnic, String rank, String password) {
        super(name, age, residence,cnic);
        this.executiveId= setId();
        this.rank = rank;
        this.executivePassword = password;
        officerList = new ArrayList<>();
        
        reportsFiled = new ArrayList<>();
    }

/* 
    // Set the ID for the executive (e.g., EXE-001)
    public String setId() {

        String generatedId = String.format("EXE-%03d", nextId++);
        return generatedId;
    }

*/



public String setId(){
           
    ArrayList<Executive> executives=new ArrayList<>();
    executives=loadExecutiveFromFile();
   
    for(Executive c: executives){
        if(this.getCnic().equals(c.getCnic())){
            this.executiveId=c.executiveId;
            break;
        }
    }
   
        int size=executives.size();


        String generatedId=String.format("EXE-%03d",++size );
         return generatedId;
  

   //return null;
 }







    // Getter and setter methods
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getExecutivePassword() {
        return executivePassword;
    }

    public void setExecutivePassword(String executivePassword) {
        this.executivePassword = executivePassword;
    }

    public String getId() {
        return executiveId;
    }

public boolean equalsCnic(String cnic){
    boolean foundCnic=false;
    
if(this.getCnic().equals(cnic)){
    foundCnic=true;
    return  foundCnic;
}

    return foundCnic;
}

public boolean equalsPassword(String password){
boolean passwordFound=false;
if(this.getExecutivePassword().equals(password)){
    passwordFound=true;
    return passwordFound;
}



return passwordFound;
}

  

    // Equals method for comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Executive)) return false;
        Executive e = (Executive) obj;
        return this.getCnic().equals(e.getCnic()) && this.getExecutivePassword().equals(e.getExecutivePassword());
    }

    // ToString method for displaying the executive details
    @Override
    public String toString() {
        return "Name: " + this.getName() + " ID: " + this.getId() + " CNIC: "+ this.getCnic()+ " Age:"+ this.getAge() + " Residence: "
                + this.getResidence() + " Rank: " + this.getRank() + " Password: " + this.getExecutivePassword();
    }

   /* public void clearFile(String path){

        try{
            FileOutputStream fos=new FileOutputStream(path);
            fos.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
     

    }*/ 

    public ArrayList<Executive> loadExecutiveFromFile(){
              
        ArrayList<Executive> executiveListFromFile=new ArrayList<>();
        if(executiveFile.exists()){

try{

       ObjectInputStream ois=new ObjectInputStream(new FileInputStream(executiveFile));
       executiveListFromFile=(ArrayList<Executive>)ois.readObject();
}
catch(IOException | ClassNotFoundException e){
    System.out.println(e.getMessage()+" this would be the error in load executive method");
}
        }
return executiveListFromFile;
    }

 
}