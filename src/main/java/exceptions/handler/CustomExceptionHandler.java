package exceptions.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView missingCountryId(RuntimeException exception) {
        ModelAndView modelAndView = new ModelAndView("hotel_list");
        modelAndView.addObject("error_message", exception.getMessage());
        return modelAndView;
    }

}
