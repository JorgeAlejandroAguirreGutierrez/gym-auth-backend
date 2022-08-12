package co.com.bancolombia.model.azuretoken.exception;

public class AzureErrorException extends Exception {

    public AzureErrorException(String errorMessage) {
        super(errorMessage);
    }
}
