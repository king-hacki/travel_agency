package exceptions;

public class MissingCountryIdException extends  RuntimeException{
    public MissingCountryIdException(String message) {
        super(message);
    }
}
