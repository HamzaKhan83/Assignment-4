package SemesterProject;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int age;
    private String residence;
    private String cnic;

 

    Person(String name,int age,String residence,String cnic){
        this.name=name;
        this.age=age;
        this.residence=residence;
        this.cnic=cnic;
    }
    public String getCnic() {
        return cnic;
    }
    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
    public String getResidence() {
        return residence;
    }
    public void setResidence(String residence) {
        this.residence = residence;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
Person(){}
    @Override
    public boolean equals(Object obj){
        if(this==obj)  return true;
        if(!(obj instanceof Person))return false;
        Person p=(Person)obj;
        return this.getName().equals(p.getName()) && this.getAge()==(p.age);
    }

}