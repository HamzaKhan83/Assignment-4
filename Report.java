package SemesterProject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.*;
public class Report implements Serializable{
    private Crime crime;
    private Person applicant;
    private String reportTime;
    private static final long serialVersionUID = 7124384323945947224L;

    private String caseId;
   // private static int nextId;
   File reportFile=new File("C:\\\\Users\\\\Hp\\\\Desktop\\\\java\\\\Reports.txt");


   Report (String crimeName,String applicantName,int applicantAge,String applicantResidence, String applicantCnic){
    crime=new Crime(crimeName);
    applicant=new Person(applicantName, applicantAge, applicantResidence,applicantCnic);

    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    reportTime=LocalDateTime.now().format(formatter);
    this.caseId=setId();
   }
   public String getReportTime() {
    return reportTime;
}


public void setReportTime(String reportTime) {
    this.reportTime = reportTime;
}


public String getCaseId() {
    return caseId;
}



public String setId() {
    ArrayList<Report> reports = loadReportsFromFile();
    if (reports == null) {
        reports = new ArrayList<>();
    }
    int nextId = reports.size() + 1;
    String generatedId = String.format("FIR-%03d", nextId);
    return generatedId;
}




    public Crime getCrime() {
        return crime;
    }
    public void setCrime(Crime crime) {
        this.crime = crime;
    }
    public Person getApplicant() {
        return applicant;
    }
    public void setApplicant(Person applicant) {
        this.applicant = applicant;
    }

    @Override
    public String toString(){
        return "Applicant Information \n"+" Applicant Name: "+this.getApplicant().getName()+" Age: "+this.getApplicant().getAge()+" Cnic: "+this.getApplicant().getCnic()+" Residence: "+getApplicant().getResidence()
       + " \n Crime: "+this.getCrime().getCrimeName()+" ReportId: "+this.getCaseId()+" Report Time: "+this.getReportTime()
        ;
    }
@Override
public boolean equals(Object obj){
   if(this==obj) return true;
   if(!(obj instanceof Report)) return false;
   Report r=(Report)obj;
   return this.getCaseId().equals(r.getCaseId());
}


    public ArrayList<Report> loadReportsFromFile(){
        ArrayList<Report> reportsFromFile=new ArrayList<>();
        try{
            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(reportFile));
            reportsFromFile=(ArrayList<Report>)ois.readObject();
            ois.close();
        }catch(IOException | ClassNotFoundException e){
          System.out.println(e.getMessage()+" this error is in loadReport from filr");
        }
        return reportsFromFile;
    }

    public String getApplicantName(){
        return applicant.getName();
    }

    public int getApplicantAge(){
        return applicant.getAge();
    }
    public String getApplicantCnic(){
        return applicant.getCnic();
    }
    public String getApplicantResidence(){
        return applicant.getResidence();
    }

}
