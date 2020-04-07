package model;

public class Doctor {
    private String firstname;
    private String lastname;
    private int age;
    private int idNumber;
    private int minutesWorkedToday = 0;
    private int nrOfPatientsToday = 0;
    private int totalAmountBilled = 0;
    private boolean isShiftOver = false;
    private Patient currentPatient = null;

    public Doctor(String firstname, String lastname, int age, int idNumber){
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.idNumber = idNumber;
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

    public int getIdNumber(){
        return this.idNumber;
    }

    public int getMinutesWorkedToday(){
        return this.minutesWorkedToday;
    }

    public int getNrOfPatientsToday(){
        return this.nrOfPatientsToday;
    }

    public int getTotalAmountBilled(){
        return this.totalAmountBilled;
    }

    public boolean getShiftStatus(){return this.isShiftOver;}

    public Patient getPatient(){return this.currentPatient;}

    public void addMinutesWorkedToday(int minutes){
        this.minutesWorkedToday += minutes;
    }

    public void addToTotalAmountBilled(int money){
        this.totalAmountBilled += money;
    }

    public void increaseNrPatients(){
        this.nrOfPatientsToday ++;
    }

    public void setShiftStatus(boolean isShiftOver) { this.isShiftOver = isShiftOver;}

    public void setPatient(Patient patient) { this.currentPatient = patient;}

    @Override
    public String toString(){
        return "Firstname: " + this.firstname +" Lastname: " + this.lastname + " Age: "+ this.age + " idNumber: " + this.idNumber;
    }

}

