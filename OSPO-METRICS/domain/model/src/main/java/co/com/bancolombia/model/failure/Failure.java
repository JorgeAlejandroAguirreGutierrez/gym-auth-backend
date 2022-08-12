package co.com.bancolombia.model.failure;

public class Failure extends Exception {
    public Failure(String errorMessage) {
        super(errorMessage);
    }
}
