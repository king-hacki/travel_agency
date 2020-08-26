package exceptions;

public class HotelNotExistException extends RuntimeException {
    public HotelNotExistException(String message) {
        super(message);
    }
}
