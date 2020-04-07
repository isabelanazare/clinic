package repository;

import model.Patient;

import java.io.*;
import java.util.stream.Collectors;

public class PatientFileRepository extends FILERepository<Patient> {

    public PatientFileRepository(String filename) {
        super(filename);
        loadFromFile();
    }

    public void loadFromFile() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            buffer.lines().collect(Collectors.toList()).forEach(line -> {
                String[] result = line.split(",");
                Patient patient = new Patient(result[0], result[1], Integer.parseInt(result[2]), result[3]);
                try {
                    super.save(patient);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeOne(Patient patient) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))) {
            bufferedWriter.write(patient.getFirstname() + "," + patient.getLastname() + "," + patient.getAge() +
                    ","+ patient.getReason() + "\n");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAll() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, false))) {
            super.entities.listIterator().forEachRemaining(patient -> {
                try {
                    bufferedWriter.write(patient.getFirstname() + "," + patient.getLastname() + "," + patient.getAge() +
                            ","+ patient.getReason() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
