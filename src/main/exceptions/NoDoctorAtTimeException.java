package exceptions;

public class NoDoctorAtTimeException extends Exception {
    String message;

    public NoDoctorAtTimeException(String str) {
        message = str;
    }

    public String toString() {
        return message;
    }
}
