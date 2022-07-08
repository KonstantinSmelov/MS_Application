package neostudy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PreScoringException extends Exception {

    private List<String> errorList;

}
