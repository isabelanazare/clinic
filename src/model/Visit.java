package model;

import service.DoctorService;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;


public class Visit extends Thread {
    private final DoctorService doctorService;
    private PriorityBlockingQueue<Patient> patients;
    private float start;

    public Visit(DoctorService doctorService, PriorityBlockingQueue<Patient> patients, float start) {
        this.doctorService = doctorService;
        this.patients = patients;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            int duration;
            int price;
            while (this.patients.size() > 0) {
                //get patient
                Patient patient = this.patients.poll();

                switch (patient.getReason()) {
                    case "Consultation":
                        duration = 30;
                        price = 50;
                        break;
                    case "Prescription":
                        duration = 20;
                        price = 20;
                        break;
                    case "Treatment":
                        duration = 40;
                        price = 35;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + patient.getReason());
                }
                //check time
                float middle = System.nanoTime() / 1000000;
                if (((middle - this.start) / 1000) >= 7.2) {
                    //assuming the work starts at 7 AM, after 12 hours (equivalent to 7.2 seconds in this program) the clinic should close
                    this.interrupt();
                    return;
                }

                Doctor doctor;
                synchronized (doctorService) {
                    //get an available doctor
                    doctor = doctorService.getAvailableDoctor();
                    if (doctor == null) {
                        return;
                    }
                    int total = doctor.getMinutesWorkedToday() + duration;
                    //if the doctor's total minutes worked would surpass the limit, end shift and get another doctor
                    while (total > 420) {
                        doctor.setShiftStatus(true);
                        doctor = doctorService.getAvailableDoctor();
                        total = doctor.getMinutesWorkedToday() + duration;
                    }
                }
                doctor.setPatient(patient);
                //wait according to consultation duration
                Thread.sleep(duration * 10);
                doctor.addMinutesWorkedToday(duration);
                doctor.addToTotalAmountBilled(price);
                doctor.increaseNrPatients();
                patient.setConsulted();
                doctor.setPatient(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
