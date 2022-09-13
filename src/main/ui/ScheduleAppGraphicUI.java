package ui;

import exceptions.NotADayException;
import model.Doctor;
import model.DoctorSchedule;
import model.Patient;
import model.PatientAppointments;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// Graphical UI representation of Doctor Schedule Appointment
public class ScheduleAppGraphicUI extends JFrame {
    private DoctorSchedule schedule;
    private PatientAppointments pa = new PatientAppointments();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private final String saveLocation = "./data/gui-saved.json";

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private final Container container;

    private final JTextArea textArea = new JTextArea();
    private final JFrame printScheduleFrame = new JFrame();
    private JFrame makeScheduleFrame = new JFrame();
    private JFrame selectTimeFrame = new JFrame();
    private JFrame enterNameFrame = new JFrame();
    private JFrame appointmentSuccessFrame = new JFrame();

    JButton printSchedule = new JButton("Display Schedule");
    JButton save = new JButton("Save current appointments");
    JButton load = new JButton("Load previous appointments");
    JButton makeAppointments = new JButton("Make an appointment");
    JButton displayCurrentAppointments = new JButton("Display Current Appointments.");
    JButton displayImage = new JButton("Display Image");

    private JButton monday;
    private JButton tuesday;
    private JButton wednesday;
    private JButton thursday;
    private JButton friday;
    private JButton morning;
    private JButton afternoon;
    private JButton submitName;

    private JTextField askForNameField;

    // EFFECTS: GUI for doctor appointments
    public ScheduleAppGraphicUI() {
        super("Doctor Appointment");
        container = getContentPane();
        frameSetUp();
        generateTextArea();
    }

    /*
     * EFFECTS: Sets up the frame for GUI
     */
    public void frameSetUp() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        generateButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /*
     * EFFECTS: generate buttons displayed for user interaction
     */
    public void generateButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0,1));
        container.add(buttonArea, BorderLayout.SOUTH);
        buttonArea.add(printSchedule);
        buttonArea.add(save);
        buttonArea.add(load);
        buttonArea.add(makeAppointments);
        buttonArea.add(displayCurrentAppointments);
        buttonArea.add(displayImage);
        buttonArea.setVisible(true);
        addSeeScheduleAndMakeAppointmentsEvents();
        addSaveAndLoadClickEvents();
    }

    /*
     * EFFECTS: sets up add image frame to display image
     */
    private void addImage() {
        JFrame imageFrame = new JFrame();
        imageFrame.setResizable(false);
        imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        imageFrame.setVisible(true);
        imageFrame.setSize(WIDTH, HEIGHT);
        ImageIcon image = new ImageIcon("./data/tobs.jpg");
        JLabel imageLabel = new JLabel(image);
        imageFrame.add(imageLabel);
    }

    /*
     * EFFECTS: generates text area to display text; not editable by user
     */
    public void generateTextArea() {
        textArea.setEditable(false);
        container.add(textArea, BorderLayout.CENTER);
    }

    /*
     * EFFECTS: adds click events to existing buttons;
     *          displays schedule when printSchedule button is clicked;
     *          makes new appointments when makeAppointments button is clicked;
     */
    private void addSeeScheduleAndMakeAppointmentsEvents() {
        printSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTableForDisplayAppointments();
            }
        });

        makeAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeScheduleFrame();
                appointmentSuccessFrame.dispose();
            }
        });

        displayImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addImage();
            }
        });
    }

    /*
     * EFFECTS: adds click events to buttons;
     *          saves current appointments when save button is clicked;
     *          loads previous appointments whe load button is clicked;
     */
    private void addSaveAndLoadClickEvents() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePatientAppointments();
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPatientAppointments();
            }
        });
        displayCurrentAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAppointments();
            }
        });
    }

    /*
     * MODIFIES: displayAppointmentFrame
     * EFFECTS: makes
     */
    private void displayAppointments() {
        JFrame displayAppointmentsFrame = new JFrame();
        displayAppointmentsFrame.setSize(WIDTH, HEIGHT);
        displayAppointmentsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayAppointmentsFrame.setVisible(true);
        displayAppointmentsFrame.setResizable(false);
        String[] columnNames = {"Day", "Morning", "Afternoon"};
        Object[][] appointmentTable = {
                {"        ", "Morning (9:00 - 12:00)", "Afternoon (3:00 - 6:00)"},
                {"Monday", pa.mondayMorningPatient.size(), pa.mondayAfternoonPatient.size()},
                {"Tuesday", pa.tuesdayMorningPatient.size(), pa.tuesdayAfternoonPatient.size()},
                {"Wednesday", pa.wednesdayMorningPatient.size(), pa.wednesdayAfternoonPatient.size()},
                {"Thursday", pa.thursdayMorningPatient.size(), pa.thursdayAfternoonPatient.size()},
                {"Friday", pa.fridayMorningPatient.size(), pa.fridayAfternoonPatient.size()},
        };
        JTable patientAppointmentTable = new JTable(appointmentTable, columnNames);
        displayAppointmentsFrame.add(patientAppointmentTable, BorderLayout.CENTER);

    }

    /*
     * MODIFIES: printScheduleFrame
     * EFFECTS: make changes to set up a new JFrame in another window printing out the
     *          doctor schedule
     */
    public void makeTableForDisplayAppointments() {
        printScheduleFrame.setSize(WIDTH, HEIGHT);
        printScheduleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        printScheduleFrame.setResizable(false);
        DoctorSchedule docSchedule = new DoctorSchedule();
        String[] columnNames = {"Day", "Morning", "Afternoon"};
        try {
            String[][] schedule2DString = makeSchedule();
            JTable doctorScheduleTable = new JTable(schedule2DString, columnNames);
            printScheduleFrame.add(doctorScheduleTable, BorderLayout.CENTER);
            printScheduleFrame.setVisible(true);
        } catch (NotADayException e) {
            System.out.println("Not a day entered!");
        }
    }

    /*
     * EFFECTS: Makes doctor schedule
     */
    public String[][] makeSchedule() throws NotADayException {
        try {
            addDoctorsForSampleSchedule();
        } catch (NotADayException e) {
            throw new NotADayException(e.toString());
        }
        ArrayList<Doctor> monday = schedule.getMonday();
        ArrayList<Doctor> tuesday = schedule.getTuesday();
        ArrayList<Doctor> wednesday = schedule.getWednesday();
        ArrayList<Doctor> thursday = schedule.getThursday();
        ArrayList<Doctor> friday = schedule.getFriday();
        String[][] doctorSchedule = {
                {"", "Morning (9:00 - 12:00)", "Afternoon (3:00 - 6:00)"},
                {"Monday", (monday.get(0)).getDoctorName(), (monday.get(1).getDoctorName())},
                {"Tuesday", (tuesday.get(0)).getDoctorName(), (tuesday.get(1).getDoctorName())},
                {"Wednesday", (wednesday.get(0)).getDoctorName(), (wednesday.get(1).getDoctorName())},
                {"Thursday", (thursday.get(0)).getDoctorName(), (thursday.get(1).getDoctorName())},
                {"Friday", (friday.get(0)).getDoctorName(), (friday.get(1).getDoctorName())},
        };
        return doctorSchedule;
    }

    /*
     * EFFECTS: adds doctors to sample schedule
     */
    public void addDoctorsForSampleSchedule() throws NotADayException {
        schedule = new DoctorSchedule();
        Doctor georgeMorning = new Doctor("George", "morning");
        Doctor bradAfternoon = new Doctor("Brad", "afternoon");
        Doctor georgeAfternoon = new Doctor("George", "afternoon");
        Doctor bradMorning = new Doctor("Brad", "morning");
        try {
            schedule.addDoctor(georgeMorning, georgeAfternoon, "monday");
            schedule.addDoctor(bradMorning, georgeAfternoon, "tuesday");
            schedule.addDoctor(bradMorning, bradAfternoon, "wednesday");
            schedule.addDoctor(georgeMorning, bradAfternoon, "thursday");
            schedule.addDoctor(bradMorning, georgeAfternoon, "friday");
        } catch (NotADayException e) {
            throw new NotADayException(e.toString());
        }
    }

    /*
     * EFFECTS: saves patient appointment
     * method referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     */
    private void savePatientAppointments() {
        jsonWriter = new JsonWriter(saveLocation);
        try {
            jsonWriter.open();
            jsonWriter.write(pa);
            jsonWriter.close();
            textArea.append("Successfully saved to " + saveLocation + "\n");
        } catch (FileNotFoundException e) {
            textArea.append("Unable to write file to destination.\n");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads patient appointments from file
     * method referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     */
    private void loadPatientAppointments() {
        jsonReader = new JsonReader(saveLocation);
        try {
            pa = jsonReader.read();
            textArea.append("Successfully loaded patient appointments from " + saveLocation + "\n");
        } catch (Exception e) {
            textArea.append("Unable to read from file " + saveLocation + "\n");
        }
    }

    /*
     * EFFECTS: generates JFrame to select which day for user's appointment
     */
    private void makeScheduleFrame() {
        makeScheduleFrame = new JFrame();
        makeScheduleFrame.setSize(WIDTH, HEIGHT);
        makeScheduleFrame.setResizable(false);
        makeScheduleFrame.setVisible(true);
        makeScheduleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextArea makeAppointmentTA = new JTextArea();
        makeAppointmentTA.setEditable(false);
        makeScheduleFrame.add(makeAppointmentTA, BorderLayout.CENTER);
        makeAppointmentTA.append("Please choose a Day \n");
        makeAppointmentTA.append("Close window to quit");
        makeButtonsWeekday();
    }

    /*
     * MODIFIES: makeScheduleFrame
     * EFFECTS: makes buttons for weekdays
     */
    private void makeButtonsWeekday() {
        JPanel buttonAreaWeekday = new JPanel();
        makeScheduleFrame.add(buttonAreaWeekday, BorderLayout.SOUTH);
        buttonAreaWeekday.setLayout(new GridLayout(0,1));
        monday = new JButton("Monday");
        tuesday = new JButton("Tuesday");
        wednesday = new JButton("Wednesday");
        thursday = new JButton("Thursday");
        friday = new JButton("Friday");
        buttonAreaWeekday.add(monday);
        buttonAreaWeekday.add(tuesday);
        buttonAreaWeekday.add(wednesday);
        buttonAreaWeekday.add(thursday);
        buttonAreaWeekday.add(friday);
        addButtonEventForMonday();
        addButtonEventForTuesday();
        addButtonEventForWednesday();
        addButtonEventForThursday();
        addButtonEventForFriday();
    }

    /*
     * REQUIRES: day must be a weekday
     * MODIFIES: selectTimeFrame
     * EFFECTS: generates JFrame for time selection for user's appointment
     */
    private void selectTimeHandler(String day) {
        selectTimeFrame = new JFrame();
        selectTimeFrame.setSize(WIDTH, HEIGHT);
        selectTimeFrame.setResizable(false);
        selectTimeFrame.setVisible(true);
        selectTimeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextArea selectTimeTA = new JTextArea();
        selectTimeTA.setEditable(false);
        selectTimeFrame.add(selectTimeTA, BorderLayout.CENTER);
        selectTimeTA.append("Please choose a Time \n");
        selectTimeTA.append("Close window to quit");
        makeButtonMorningAndAfternoon(day);
    }

    /*
     * REQUIRES: day must be a weekday
     * MODIFIES: morning, afternoon
     * EFFECTS: creates buttons for morning and afternoon
     */
    private void makeButtonMorningAndAfternoon(String day) {
        JPanel buttonAreaTime = new JPanel();
        selectTimeFrame.add(buttonAreaTime, BorderLayout.SOUTH);
        buttonAreaTime.setLayout(new GridLayout(0,1));
        morning = new JButton("Morning [9:00 - 12:00]");
        afternoon = new JButton("Afternoon [3:00 - 6:00]");
        buttonAreaTime.add(morning);
        buttonAreaTime.add(afternoon);
        addButtonEventForMorning(day);
        addButtonEventForAfternoon(day);
    }


    /*
     * EFFECTS: adds action listeners to monday, calls selectTimeHandler with monday
     */
    private void addButtonEventForMonday() {
        monday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeScheduleFrame.dispose();
                selectTimeHandler("monday");
            }
        });
    }

    /*
     * EFFECTS: adds action listeners to tuesday, calls selectTimeHandler with tuesday
     */
    private void addButtonEventForTuesday() {
        tuesday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeScheduleFrame.dispose();
                selectTimeHandler("tuesday");
            }
        });
    }

    /*
     * EFFECTS: adds action listeners to wednesday, calls selectTimeHandler with wednesday
     */
    private void addButtonEventForWednesday() {
        wednesday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeScheduleFrame.dispose();
                selectTimeHandler("wednesday");
            }
        });
    }

    /*
     * EFFECTS: adds action listeners to thursday, calls selectTimeHandler with thursday
     */
    private void addButtonEventForThursday() {
        thursday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeScheduleFrame.dispose();
                selectTimeHandler("thursday");
            }
        });
    }

    /*
     * EFFECTS: adds action listeners to friday, calls selectTimeHandler with friday
     */
    private void addButtonEventForFriday() {
        friday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeScheduleFrame.dispose();
                selectTimeHandler("friday");
            }
        });
    }

    /*
     * REQUIRES: day must be one of weekdays
     * EFFECTS: adds action listener to morning, passes down day chosen
     */
    private void addButtonEventForMorning(String day) {
        morning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectTimeFrame.dispose();
                askForNameFrame(day, "morning");
            }
        });
    }

    /*
     * EFFECTS: adds action listener to afternoon
     */
    private void addButtonEventForAfternoon(String day) {
        afternoon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectTimeFrame.dispose();
                askForNameFrame(day, "afternoon");
            }
        });
    }

    /*
     * REQUIRES: day must be one of weekday, time must be one of morning or afternoon
     * MODIFIES: enterNameFrame
     * EFFECTS: Asks for the name of user for appointment;
     *          initializes a new frame including button and text area and label;
     */
    private void askForNameFrame(String day, String time) {
        enterNameFrame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please enter your name: ");
        askForNameField = new JTextField(30);
        submitName = new JButton("Submit");

        enterNameFrame.setSize(WIDTH,200);
        enterNameFrame.setVisible(true);
        enterNameFrame.setResizable(false);
        enterNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setLayout(new GridLayout(0,1));
        panel.add(label);
        panel.add(askForNameField);
        panel.add(submitName);
        enterNameFrame.add(panel);
        submitNameButtonHandler(day, time);
    }

    /*
     * REQUIRES: day must be one of weekday, time must be one of morning or afternoon
     * EFFECTS: calls addToPA when submitName button is clicked, disposes enterNameFrame and extracts text field
     *          texts as string
     */
    private void submitNameButtonHandler(String day, String time) {
        submitName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = askForNameField.getText();
                addToPA(day, time, name);
                enterNameFrame.dispose();
            }
        });
    }

    /*
     * REQUIRES: day must be one of weekday, time must be one of morning or afternoon
     * MODIFIES: p1, pa
     * EFFECTS: adds patient to patient appointments, calls appointmentSuccess method
     */
    private void addToPA(String day, String time, String name) {
        Patient p = new Patient(name);
        pa.addPatientToSchedule(p, day, time);
        appointmentSuccess(p);
    }

    /*
     * MODIFIES: appointmentSuccessFrame
     * EFFECTS: generates JFrame including text of details regarding appointment
     */
    private void appointmentSuccess(Patient p) {
        appointmentSuccessFrame = new JFrame();
        JTextArea success = new JTextArea();
        success.setEditable(false);
        success.append("Thank you for your appointment " + p.getName() + ".\n");
        success.append("You are appointed to: " + p.getAppointDay() + " " + p.getAppointTime() + ".\n");
        success.append("Your appointment number is: " + p.getAppointNum() + ".\n");
        textArea.setFont(textArea.getFont().deriveFont(20f));
        appointmentSuccessFrame.setSize(500,300);
        appointmentSuccessFrame.setResizable(false);
        appointmentSuccessFrame.setVisible(true);
        appointmentSuccessFrame.add(success);
        appointmentSuccessFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
