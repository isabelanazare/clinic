package repository;

import model.Doctor;
import java.io.*;
import java.util.stream.Collectors;

public class DoctorFileRepository extends FILERepository<Doctor>{

    public DoctorFileRepository(String filename) {
        super(filename);
        loadFromFile();
    }

    protected void loadFromFile() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            buffer.lines().collect(Collectors.toList()).forEach(line -> {
                String[] result = line.split(",");
                Doctor doctor = new Doctor(result[0], result[1], Integer.parseInt(result[2]), Integer.parseInt(result[3]));
                super.save(doctor);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeOne(Doctor doctor) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))) {
            bufferedWriter.write(doctor.getFirstname() + "," + doctor.getLastname() + "," + doctor.getAge() +
                    ","+ doctor.getIdNumber() + "\n");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void writeAll() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, false))) {
            super.entities.listIterator().forEachRemaining(doctor -> {
                try {
                    bufferedWriter.write(doctor.getFirstname() + "," + doctor.getLastname() + "," + doctor.getAge() +
                            ","+ doctor.getIdNumber() + "\n");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
