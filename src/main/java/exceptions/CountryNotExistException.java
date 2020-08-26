package exceptions;

public class CountryNotExistException extends RuntimeException {
    public CountryNotExistException(String message) {
        super(message);
    }
}
