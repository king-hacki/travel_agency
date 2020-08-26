package exceptions;

import lombok.Getter;

@Getter
public class BadDateException extends RuntimeException {
    public BadDateException(String message) {
        super(message);
    }
}
