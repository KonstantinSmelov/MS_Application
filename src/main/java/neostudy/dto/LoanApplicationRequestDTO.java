package neostudy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class LoanApplicationRequestDTO {

    @ApiModelProperty(notes = "Сумма кредита", example = "50000")
    private BigDecimal amount;

    @ApiModelProperty(notes = "Срок кредита", example = "6")
    private Integer term;

    @ApiModelProperty(notes = "Имя", example = "Иван")
    private String firstName;

    @ApiModelProperty(notes = "Фамилия", example = "Иванов")
    private String lastName;

    @ApiModelProperty(notes = "Отчёство", example = "Иванович")
    private String middleName;

    @ApiModelProperty(notes = "Email", example = "ivanov@mail.ru")
    private String email;

    @ApiModelProperty(notes = "День рождения", example = "2000-05-15")
    private LocalDate birthdate;

    @ApiModelProperty(notes = "Серия паспорта", example = "1234")
    private String passportSeries;

    @ApiModelProperty(notes = "Номер паспорта", example = "123456")
    private String passportNumber;
}
