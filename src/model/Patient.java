package model;

public class Patient implements Comparable<Patient> {
    private String firstname;
    private String lastname;
    private int age;
    private String reason;
    private boolean consulted = false;

    public Patient(String firstname, String lastname, int age, String reason) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.reason = reason;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public int getAge(){
        return this.age;
    }

    public String getReason(){
        return this.reason;
    }

    public boolean hasbeenConsulted(){ return this.consulted; }

    public void setConsulted(){
        this.consulted = true;
    }

    @Override
    public String toString(){
        return "Firstname: " + this.firstname +" Lastname: " + this.lastname + " Age: "+ this.age + " reason: " + this.reason;
    }

    @Override
    public int compareTo(Patient patient) {
        return Integer.compare(this.getAge(), patient.getAge());
    }

}
