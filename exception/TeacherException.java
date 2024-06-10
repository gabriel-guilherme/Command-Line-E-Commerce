package exception;

public class TeacherException extends Exception {
    public TeacherException() {
        super();
    }

    public TeacherException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeacherException(String message) {
        super(message);
    }
}