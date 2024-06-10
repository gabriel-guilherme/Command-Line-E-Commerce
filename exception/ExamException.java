package exception;

public class ExamException extends Exception {
    public ExamException() {
        super();
    }

    public ExamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExamException(String message) {
        super(message);
    }
}