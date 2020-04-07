package service;


import model.Patient;
import repository.FILERepository;

import java.util.*;

public class PatientService {
    private FILERepository<Patient> patientRepository;

    public PatientService(FILERepository<Patient> patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return this.patientRepository.getEntities();
    }

    public ListIterator<Patient> getAll() {
        return this.patientRepository.findAll();
    }

    public Patient generatePatient(Object... parameters) {
        Random r = new Random();
        String reason = null;
        int age = 0;
        //if age or reason given as parameter, use parameter instead of generating random value
        if (parameters.length == 1) {
            if (parameters[0] instanceof Integer) {
                age = (int) parameters[0];
            } else {
                age = r.nextInt(86);
            }
            if (parameters[0] instanceof String) {
                reason = (String) parameters[0];
            } else {
                String[] reasons = {"Consultation", "Prescription", "Treatment"};
                reason = reasons[r.nextInt(3)];
            }
        }
        else{
            age = r.nextInt(86);
            String[] reasons = {"Consultation", "Prescription", "Treatment"};
            reason = reasons[r.nextInt(3)];
        }

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String firstname = "";
        String lastname = "";
        r = new Random();
        for (int i = 0; i < 5; i++) {
            firstname = firstname.concat(String.valueOf(alphabet.charAt(r.nextInt(alphabet.length()))));
        }
        for (int i = 0; i < 4; i++) {
            lastname = lastname.concat(String.valueOf(alphabet.charAt(r.nextInt(alphabet.length()))));
        }
        return new Patient(firstname, lastname, age, reason);
    }

    public void generatePatients(int nrOfPatients) {
        //generate a random patient for each age category
        Random r = new Random();
        this.patientRepository.save(generatePatient(r.nextInt(1)));
        this.patientRepository.save(generatePatient(r.nextInt(6) + 1));
        this.patientRepository.save(generatePatient(r.nextInt(11) + 1));
        this.patientRepository.save(generatePatient(r.nextInt(110 - 18) + 18));
        //generate a random patient for each possible reason
        this.patientRepository.save(generatePatient("Consultation"));
        this.patientRepository.save(generatePatient("Prescription"));
        this.patientRepository.save(generatePatient("Treatment"));

        //generate the rest (93 patients)
        nrOfPatients -= 7;
        while (nrOfPatients != 0) {
            this.patientRepository.save(generatePatient());
            nrOfPatients--;
        }
    }

    public Map<String, Float> getPatientsBasedOnAgeGroup() {
        Map<String, Float> summary = new HashMap<>();
        summary.put("children", (float) patientRepository.getEntities().stream().filter(p -> p.getAge() == 0 || p.getAge() == 1).count());
        summary.put("pupil", (float) patientRepository.getEntities().stream().filter(p -> p.getAge() > 1 && p.getAge() <= 7).count());
        summary.put("students", (float) patientRepository.getEntities().stream().filter(p -> p.getAge() > 7 && p.getAge() <= 18).count());
        summary.put("adults", (float) patientRepository.getEntities().stream().filter(p -> p.getAge() > 18).count());
        return summary;
    }
}