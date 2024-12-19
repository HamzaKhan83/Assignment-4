package SemesterProject;
import java.io.Serializable;

public class Crime implements Serializable{
   private String crimeName;
   
    
    public String getCrimeName() {
    return crimeName;
}

public void setCrimeName(String crimeName) {
    this.crimeName = crimeName;
}

    Crime(String crimeName){
         this.crimeName=crimeName;
    }


    @Override
    public String toString(){
        return " Crime: "+this.getCrimeName();
    }

    @Override
    public boolean equals(Object obj){
if(this==obj) return true;
if(!(obj instanceof Crime)) return false;
Crime c=(Crime)obj;
return this.crimeName.equals(c.crimeName);

    
}
}