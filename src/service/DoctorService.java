package service;

import model.Doctor;
import repository.FILERepository;

import java.util.ListIterator;
import java.util.Optional;
import java.util.Random;

public class DoctorService {
    private FILERepository<Doctor> doctorRepository;

    public DoctorService(FILERepository<Doctor> doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public ListIterator<Doctor> getAll() {
        return this.doctorRepository.findAll();
    }

    public Doctor getAvailableDoctor() {
        Optional<Doctor> doctor = doctorRepository.getEntities().stream().
                filter(d -> d.getMinutesWorkedToday() < 420 && !d.getShiftStatus() && d.getPatient() == null).findFirst();
        if (doctor.isEmpty()) {
            return null;
        }
        return doctor.get();
    }

    public void generateDoctors(int nrOfDoctors) {
        while (nrOfDoctors != 0) {
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            Random r = new Random();
            String firstname = "";
            String lastname = "";
            int age;
            int id;
            age = r.nextInt(65 + 1 - 30) + 30;
            id = r.nextInt(9999 + 1 - 1000) + 1000;

            if (isIdNumberUnique(id)) {
                id = r.nextInt(9999 + 1 - 1000) + 1000;
                while (isIdNumberUnique(id)) {
                    id = r.nextInt(9999 + 1 - 1000) + 1000;
                }
            }

            for (int i = 0; i < 3; i++) {
                firstname = firstname.concat(String.valueOf(alphabet.charAt(r.nextInt(alphabet.length()))));
            }

            lastname = lastname.concat(String.valueOf(alphabet.charAt(r.nextInt(alphabet.length()))));
            lastname = lastname.concat(String.valueOf(alphabet.charAt(r.nextInt(alphabet.length()))));

            Doctor doctor = new Doctor(firstname, lastname, age, id);
            this.doctorRepository.save(doctor);
            nrOfDoctors--;
        }
    }

    public boolean isIdNumberUnique(int id) {
        Optional<Doctor> matchingObject = doctorRepository.getEntities().stream().
                filter(p -> p.getIdNumber() == id).findFirst();
        return !matchingObject.isEmpty();
    }

}