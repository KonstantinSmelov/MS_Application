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
    @Min(value = 10000L, message = "Кредит не может быть меньше 10000")
    private BigDecimal amount;

    @ApiModelProperty(notes = "Срок кредита", example = "6")
    @Min(value = 6, message = "Срок кредита не менее 6 месяцев")
    private Integer term;

    @ApiModelProperty(notes = "Имя", example = "Иван")
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9]{2,30}", message = "Имя не должно быть короче 2 и длиннее 30 символов и должно состоять из букв и цифр")
    private String firstName;

    @ApiModelProperty(notes = "Фамилия", example = "Иванов")
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9]{2,30}", message = "Фамилия не должна быть короче 2 и длиннее 30 символов и должнасостоять из букв и цифр")
    private String lastName;

    @ApiModelProperty(notes = "Отчёство", example = "Иванович")
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9]{2,30}|", message = "Отчества или нет, или оно не должно быть короче 2 и длинее 30 символов и должно состоять из букв и цифр")
    private String middleName;

    @ApiModelProperty(notes = "Email", example = "ivanov@mail.ru")
    @Email(message = "Некорректная почта")
    private String email;

    @ApiModelProperty(notes = "День рождения", example = "2000-05-15")
    @Past(message = "Некорректная дата рождения")
    private LocalDate birthdate;

    @ApiModelProperty(notes = "Серия паспорта", example = "1234")
    @Pattern(regexp = "\\d{4}", message = "Серия паспорта должна содержать 4 цифры")
    private String passportSeries;

    @ApiModelProperty(notes = "Номер паспорта", example = "123456")
    @Pattern(regexp = "\\d{6}", message = "Номер паспорта должен содержать 6 цифр")
    private String passportNumber;
}
