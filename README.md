# clinic
A clinic has 4 consulting rooms and several doctors. The clinic functions between 7 AM and 19 PM and has each consulting room assigned to a doctor. When the doctor’s shift is over, another doctor will take over the consulting room.

A doctor can be identified by first name, last name, age and identification number. Each doctor can consult a single patient at a time and he can work for maximum 7 hours a day. 
A patient can be identified by first name, last name, age and the reason why he came to the doctor. The reason can be: consultation, treatment or prescription.
In order to provide better healthcare, the clinic has a categorizing system for patients using the age as a differentiating factor, in order to determine the consultation order: 
-	Children: 0-1 years
-	Pupil: 1-7 years
-	Student: 7-18 years
-	Adult: > 18 years
According to the consultation’s reason, each patient will stay in the consulting room for a predefined period of time and he will pay a predefined fee:
-	Consultation: 30 minutes, 50 RON
-	Prescription: 20 minutes, 20 RON
-	Treatment: 40 minutes, 35 RON

Requirements
a) Generate a list of doctors, respecting the following requirements:
-	The list must contain at least 8 doctors.
-	Each doctor will be generated following the rules:
o	First Name:  3 characters; 
o	Last Name: 2 characters.
o	Age: between 30 and 65.
o	Identification number: 4 numbers, must be unique in the list

b) Generate a list of patients, respecting the following requirements:
-	The list must contain 100 patients.
-	Each patient will be generated following the rules:
o	First Name: 5 characters; 
o	Last Name: 4 characters.
o	Age: between 0 and 85.
o	Reason: consultation, treatment or prescriptions.
-	The list must contain at least one patient from each age category and at least one patient for each consultation reason.

c) Print the list of doctors and the list of patients on the console.
(extra / + 5 points) Print a summary of all patients based on their age group.
E.g.
Children (0-1): 25 patients
Pupil (1-7): 35 patients
Student (7-18): 20 patients
Adults (>18): 20 patients

d) Store the list of patients from b) in a file on disk. 

e) Store the list of doctors from a) in a file on disk.

f) Provide an implementation able to simulate the system described above, respecting the following requirements:

-	Use the patient list from b)
-	Use the doctor list from a)
-	At the end print a summary of the doctors, the number of patients consulted and the total amount billed.

E.g.
A, B – 1234: 18 patients, 390 minutes, 250 RON
X, Y – 3331: 22 patients, 460 minutes, 310 RON
M, N – 1233: 0 patients, 0 minutes, 0 RON

-	At the end print the list of patients which were not consulted (if any).

E.g.
A, B, 20 years, prescription
C, D, 14 years, treatment
E, F, 9 years, consultation
