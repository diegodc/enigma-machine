package rest.config;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import enigma.core.machine.Enigma;
import enigma.service.EnigmaServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({EnigmaServiceException.class, Enigma.InvalidSetting.class, IllegalArgumentException.class})
    public final ResponseEntity<ErrorMessage> handleEnigmaServiceExceptions(RuntimeException e) {

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler({MismatchedInputException.class, HttpMessageNotReadableException.class})
    public final ResponseEntity<ErrorMessage> handleInvalidRequest(RuntimeException e) {

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(new ErrorMessage("Invalid Request"));
    }

    public static class ErrorMessage {

        private final String message;

        ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }

}