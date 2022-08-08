package neostudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ScoringExceptionHandIer {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> notValidException(MethodArgumentNotValidException e) {

        List<String> errorsList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> ("\n" + error.getDefaultMessage()))
                .collect(Collectors.toList());
        errorsList.add("\n");

        return new ResponseEntity<>(errorsList.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PreScoringException.class)
    public ResponseEntity<String> preScoringInvalid(PreScoringException e) {
        return new ResponseEntity<>(e.getErrorList().toString(), HttpStatus.CONFLICT);
    }
}
