package exceptions;

public class NotADayException extends Exception {
    String message;

    public NotADayException(String msg) {
        message = msg;
    }

    public String toString() {
        return message;
    }
}
