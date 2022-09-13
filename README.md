#Doctor's appointment scheduling program

## A program that automatically generates appointment schedule

## *User Stories* list (Phase 1):
- As a user, I want to be able to find the name of available doctor(s) at given weekday
- As a user, I want to be able to see the number of total available appointments at given weekday
- As a user, I want to be able to view the doctor's schedule
- As a user, I want to be able to add a patient to schedule

## *User Stories* list (Phase 2):
- As a user, I want to be able to save patient's appointment to file
- As a user, I want to be able to load patient's appointments from file
- As a user, I want to be able to choose a file name to save as
- As a user, I want to be able to choose a file name to load from
- As a user, I want to be able to the number of appointments made on different day and time

## Phase 4 Task 2:
- I implemented the first option, test and design a class in model that is robust. I designed the DoctorSchedule class 
in the model package to be robust through making 2 methods: addDoctor and findDoctorAtTime to throw exceptions when day
entered in addDoctor method is not a weekday and when findDoctorAtTime cannot find doctor at given time. I've also
included 2 custom exceptions in the exceptions package to handle the exceptions thrown from the two methods. Lastly, I
made changes to DoctorScheduleTest to check that exceptions are properly handled. Changed methods in 
ScheduleAppGraphicUI that used the two methods to catch exceptions thrown.

## Phase 4 Task 3:
- If I had more time to work on this project, I would work on cleaning up the graphical UI package by separating it 
into smaller classes in order to make the code easier to read and comprehend. I would also try to deal with the amount
of coupling I had in my model packages as I have found that if I wanted to change something in any of the classes, I 
would have to go through all the other classes in model and UI packages. Lastly, I would want to try and make either an
interface or abstract class and/or enum for weekdays and time instead of initiating a number of lists in DoctorSchedule
and PatientAppointments classes. 

My application will generate a clinic's appointment schedule according to user input of both doctor and patients. The
application will generate a schedule according to user input with given name of the doctor(s) available times and week 
days. After the above information has been given, a schedule with the doctor's working times according to week days
will be generated. Then, the schedule will be available for patients to make their appointments. 

This application can be operated by both the doctor at the clinic and the patients. To initiate a new schedule, input
from the doctor(s) are needed in order to generate an empty schedule for the patients to observe and make appointments
with the help of it. When a patient makes a appointment, the program will then check if that time period is available,
if it is, then a number will be given to the patient with given time to come to clinic, if not, then the patient will
be asked to choose another time. For example, in the time period of 8:00 to 11:30, doctor can choose the number
of patients that can be scheduled in this time period such as 14 patients with 15 minutes for every single patient. 
Then, if the patient receives a number 7, the program will tell him that he/she is scheduled at 9:45 to 10:00.

This project is of interest to me because I have always found making appointments through phone calls to be inefficient
and time-consuming. In recent years, some clinics and hospitals has started using online scheduling application, and it
has shown that it made making appointments much easier and efficient. However, some application used were either hard
to use and/or have really complicated interface. Thus, I aim to make a program that is easy to use with clear user 
interface that allows the user to make appointments through a few simple clicks.