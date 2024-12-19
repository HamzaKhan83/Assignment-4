package SemesterProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Police {

    File criminalFile ;
    File executiveFile;
    File reportFile;
    File officerFile;
    
    ArrayList<Executive> executiveList;
    ArrayList<Criminal> criminalList;
    ArrayList<Report> registeredReports;
    ArrayList<Officer> officerList;

    Police(){
        executiveList=new ArrayList<>();
        criminalList = new ArrayList<>();
        registeredReports=new ArrayList<>();
        officerList=new ArrayList<>();
  criminalFile= new File("C:\\Users\\Hp\\Desktop\\java\\CriminalData.txt");
  executiveFile=  new File("C:\\Users\\Hp\\Desktop\\java\\ExecutiveData.txt");
  reportFile=new File("C:\\Users\\Hp\\Desktop\\java\\Reports.txt");
  officerFile=new File("C:\\Users\\Hp\\Desktop\\java\\OfficerData.txt");
    }


     //                                    Operations for criminal Handling
    //
    // File path for storing criminal data
   

    // Add a criminal to the list and save to file
    public void addCriminals(String name, int age, String residence,String cnic, String crime) {
        Criminal criminal = new Criminal(name, age, residence, cnic,crime);
        ArrayList<Criminal> criminalList = loadCriminalArrayFromFile();
        if (criminalList == null) {
            criminalList = new ArrayList<>();
        }
        if (!criminalList.contains(criminal)) {
     
            criminalList.add(criminal);
            writeCrimeToFile(criminalList);
          //  System.out.println("Criminal added");
        } else {
            System.out.println("Criminal already present");
    }
}
    
    
   // String filePath="C:\\Users\\Hp\\Desktop\\java\\CriminalData.txt";

    // Write the list of criminals to the file
    public void writeCrimeToFile(ArrayList<Criminal> criminals) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(criminalFile))) {
            oos.writeObject(criminals);
            oos.flush();
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());
        }
    }
    
    

    // Load the list of criminals from the file
    public ArrayList<Criminal> loadCriminalArrayFromFile() {
        ArrayList<Criminal> criminalListFromFile = new ArrayList<>();
        try {
            if (criminalFile.exists()) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(criminalFile))) {
                    criminalListFromFile = (ArrayList<Criminal>) objectInputStream.readObject();
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error while reading from file: " + e.getMessage());
        }
        return criminalListFromFile;
    }

    // display crimianl list
    
    public void displayCriminal(){
        ArrayList<Criminal> criminals=new ArrayList<>();
        criminals=loadCriminalArrayFromFile();
        for(Criminal c:criminals){
            System.out.println(c);
        }
    }
                       // Searching criminals for a specific crime

    public ArrayList<Criminal> searchCriminalForCrime(String crime){

        ArrayList <Criminal> cr=new ArrayList<>();
        cr=loadCriminalArrayFromFile();
        System.out.println("Searching suspects for "+crime);
        ArrayList<Criminal> criminalForACrime=new ArrayList<>();
        for(Criminal c:cr){
            if(c.getCrimeDone().getCrimeName().equalsIgnoreCase(crime)){
                criminalForACrime.add(c);
            }
        }
        return criminalForACrime;
    }




    //                              Operations for Executive handling
    //


    public void addExecutive(String name,int age,String residence,String cnic,String rank,String password){
        Executive executive=new Executive(name, age, residence, cnic, rank, password);

        executiveList=loadExecutiveFromFile();
        if(executiveList==null){
            executiveList=new ArrayList<>();
        }
        if (!executiveList.contains(executive)){
        executiveList.add(executive);
        writeExecutiveToFile(executiveList);
        }

    }


    public void writeExecutiveToFile(ArrayList<Executive> executiveList){

try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(executiveFile))){
    objectOutputStream.writeObject(executiveList);
    objectOutputStream.flush();
}

catch(IOException e){
    System.out.println(e.getMessage()+"This error is from writing executive to file");
}

    }

    public ArrayList<Executive> loadExecutiveFromFile(){
              
        ArrayList<Executive> executiveListFromFile=new ArrayList<>();
        

try{
    if(executiveFile.exists()){
       ObjectInputStream ois=new ObjectInputStream(new FileInputStream(executiveFile));
       executiveListFromFile=(ArrayList<Executive>)ois.readObject();
       ois.close();}
}
catch(IOException | ClassNotFoundException e){
    System.out.println(e.getMessage()+" this would be the error in load executive method");
}
        
return executiveListFromFile;
    }


    public void displayExecutiveList(){
        ArrayList<Executive> executives=new ArrayList<>();
        executives=loadExecutiveFromFile();
        for(Executive e:executives){
            System.out.println(e);
        }
    }

    //                Report Methods
    //

    public void addReport(String crimeName, String applicantName, int applicantAge, String applicantResidence, String applicantCnic) {
        Report report = new Report(crimeName, applicantName, applicantAge, applicantResidence, applicantCnic);
        registeredReports = loadReportsFromFile();
        if (registeredReports == null) {
            registeredReports = new ArrayList<>();
        }
        if (!registeredReports.contains(report)) {
            registeredReports.add(report);
            writeReportToFile(registeredReports);
        } else {
            System.out.println("Report already exists");
        }
    }
    




    public void writeReportToFile(ArrayList<Report> reports) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(reportFile))) {
            oos.writeObject(reports);
        } catch (IOException e) {
            System.out.println(e.getMessage() + " this error is in write report to file");
        }
    }
    
    

    public ArrayList<Report> loadReportsFromFile() {
        ArrayList<Report> reportsFromFile = new ArrayList<>();
        try {
            if (reportFile.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(reportFile))) {
                    reportsFromFile = (ArrayList<Report>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage() + " this error is in loadReport from file");
        }
        return reportsFromFile;
    }
    
    // to stop report doubling include  a factor in report of giving time and check time and cnic if same dont add in report list

public void displayReports(){

    ArrayList<Report> reports=loadReportsFromFile();
    System.out.println("Registered Reports");
    for(Report r:reports){
        System.out.println(r);
    }
}

//            Methods for Officers
//

public void addOfficer(String name, int age, String residence, String cnic) {
    Officer officer = new Officer(name, age, residence, cnic);
    officerList = loadOfficerFromFile();
    if (officerList == null) {
        officerList = new ArrayList<>();
    }
    if (!officerList.contains(officer)) {
        officerList.add(officer);
        writeOfficerTOFile(officerList);
    } else {
        System.out.println("Officer is already present");
    }
}





public void writeOfficerTOFile(ArrayList<Officer> officers) {
    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(officerFile))) {
        objectOutputStream.writeObject(officers);
    } catch (IOException e) {
        System.out.println(e.getMessage() + " this error is in write officer to file");
    }
}

public ArrayList<Officer> loadOfficerFromFile() {
    ArrayList<Officer> officerFromFile = new ArrayList<>();
    try {
        if (officerFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(officerFile))) {
                officerFromFile = (ArrayList<Officer>) ois.readObject();
            }
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println(e.getMessage() + " error is in load officer from file");
    }
    return officerFromFile;
}

public void displayOfficer(){

    ArrayList<Officer> officers=loadOfficerFromFile();
    System.out.println("Officer List");
    for(Officer o:officers){
        System.out.println(o);

    }
}

public boolean loginAsExecutive(String cnic, String password) {
    boolean executiveFound = false;
    ArrayList<Executive> executives = loadExecutiveFromFile();
    for (Executive e : executives) {
        if (e.getCnic().equals(cnic) && e.getExecutivePassword().equals(password)) {
            executiveFound = true;
            break;
        }
    }
    return executiveFound;
}

}
