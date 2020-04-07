import model.*;
import repository.DoctorFileRepository;
import repository.PatientFileRepository;
import service.DoctorService;
import service.PatientService;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        DoctorFileRepository doctorRepository = new DoctorFileRepository("doctors.txt");
        PatientFileRepository patientRepository = new PatientFileRepository("patients.txt");

        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);

        //uncomment this section to generate more doctors or patients

        //  doctorService.generateDoctors(8);
        //   doctorRepository.writeAll();
        //   patientService.generatePatients(100);
        //   patientRepository.writeAll();

        PriorityBlockingQueue<Patient> priorityQueue = new PriorityBlockingQueue<>(patientService.getPatients());

        List<Visit> visits = new ArrayList<>();

        float start = System.nanoTime() / 1000000;

        for (int i = 0; i < 4; i++) {
            Visit visit = new Visit(doctorService, priorityQueue, start);
            visits.add(visit);
        }

        for (Visit v : visits) {
            v.start();
        }

        for (int i = 0; i < 4; i++) {
            visits.get(i).join();
        }

        float end = System.nanoTime() / 1000000;
        System.out.println("\n End of work day after " + (end - start) / 1000 + " seconds");
        System.out.println();
        System.out.println("Doctors");
        System.out.println();
        for (ListIterator<Doctor> it = doctorService.getAll(); it.hasNext(); ) {
            Doctor x = it.next();
            System.out.println(x.toString());
        }
        System.out.println();
        System.out.println("Patients");
        System.out.println();

        for (ListIterator<Patient> it = patientService.getAll(); it.hasNext(); ) {
            Patient x = it.next();
            System.out.println(x.toString());
        }
        System.out.println();
        System.out.println("Summary of all patients based on their age group");
        Map<String, Float> summary = patientService.getPatientsBasedOnAgeGroup();

        System.out.println("Children (0-1): " + new java.text.DecimalFormat("#").format(summary.getOrDefault("children", 0.0f)) + " patients");
        System.out.println("Pupil (1-7): " + new java.text.DecimalFormat("#").format(summary.getOrDefault("pupil", 0.0f)) + " patients");
        System.out.println("Student (7-18): " + new java.text.DecimalFormat("#").format(summary.getOrDefault("students", 0.0f)) + " patients");
        System.out.println("Adults (>18): " + new java.text.DecimalFormat("#").format(summary.getOrDefault("adults", 0.0f)) + " patients");
        System.out.println();
        System.out.println("Summary of the doctors, the number of patients consulted and " +
                "the total amount billed");
        for (ListIterator<Doctor> it = doctorService.getAll(); it.hasNext(); ) {
            Doctor x = it.next();
            System.out.println(x.getFirstname() + ", " + x.getLastname() + " - " + x.getIdNumber() + ": " + x.getNrOfPatientsToday() +
                    " patients, " + x.getMinutesWorkedToday() + " minutes, " + x.getTotalAmountBilled() + " RON");
        }
        System.out.println();
        System.out.println("Total patients consulted today: " + patientService.getPatients().stream().filter(Patient::hasbeenConsulted).count());
        System.out.println();
        System.out.println("Patients who were not consulted");
        List<Patient> patientsNotConsulted = patientService.getPatients().stream().filter(p -> !p.hasbeenConsulted()).collect(Collectors.toList());
        System.out.println("nr: " + patientsNotConsulted.size());
        if (patientsNotConsulted.size() > 0) {
            for (Patient p : patientsNotConsulted) {
                System.out.println(p.getFirstname() + ", " + p.getLastname() + ", " + p.getAge() + " years, " + p.getReason());
            }
        }

    }
}
