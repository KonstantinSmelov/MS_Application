package neostudy.service;

import lombok.extern.slf4j.Slf4j;
import neostudy.dto.LoanApplicationRequestDTO;
import neostudy.exception.PreScoringException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class PreScoring {

    public void checkForPreScoringErrors(LoanApplicationRequestDTO loanApplicationRequestDTO) throws PreScoringException {

        List<String> errorsList = new ArrayList<>();
        Pattern nameSurnamePattern = Pattern.compile("^\\S[a-zA-Zа-яА-Я0-9 ]{2,30}\\S$");
        Pattern middlenamePattern = Pattern.compile("^(\\S[a-zA-Zа-яА-Я0-9 ]{2,30}\\S|)$");
        Pattern emailPattern = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Pattern passportSeriesPattern = Pattern.compile("\\d{4}");
        Pattern passportNumberPattern = Pattern.compile("\\d{6}");
        Matcher matcher;


        matcher = nameSurnamePattern.matcher(loanApplicationRequestDTO.getFirstName());
        if (!matcher.find()) {
            errorsList.add("\nИмя не должно быть короче 2 и длиннее 30 символов и должно состоять из букв и цифр");
            log.info("checkForPreScoringErrors():  добавление в errorsList: {}", errorsList.get(errorsList.size() - 1));
        }

        matcher = nameSurnamePattern.matcher(loanApplicationRequestDTO.getLastName());
        if (!matcher.find()) {
            errorsList.add("\nФамилия не должна быть короче 2 и длиннее 30 символов и должна состоять из букв и цифр");
            log.info("checkForPreScoringErrors():  добавление в errorsList: {}", errorsList.get(errorsList.size() - 1));
        }

        matcher = middlenamePattern.matcher(loanApplicationRequestDTO.getMiddleName());
        if (!matcher.find()) {
            errorsList.add("\nОтчества или нет, или оно не должно быть короче 2 и длинее 30 символов и должно состоять из букв и цифр");
            log.info("checkForPreScoringErrors():  добавление в errorsList: {}", errorsList.get(errorsList.size() - 1));
        }

        if (0 >= loanApplicationRequestDTO.getAmount().compareTo(BigDecimal.valueOf(10000))) {
            errorsList.add("\nКредит не может быть меньше 10000");
            log.info("checkForPreScoringErrors():  добавление в errorsList: {}", errorsList.get(errorsList.size() - 1));
        }

        if (loanApplicationRequestDTO.getTerm() < 6) {
            errorsList.add("\nСрок кредита не менее 6 месяцев");
            log.info("checkForPreScoringErrors():  добавление в errorsList: {}", errorsList.get(errorsList.size() - 1));
        }

        matcher = passportNumberPattern.matcher(loanApplicationRequestDTO.getPassportNumber());
        if (!matcher.find()) {
            errorsList.add("\nНомер паспорта должен содержать 6 цифр");
            log.info("checkForPreScoringErrors():  добавление в errorsList: {}", errorsList.get(errorsList.size() - 1));
        }

        matcher = passportSeriesPattern.matcher(loanApplicationRequestDTO.getPassportSeries());
        if (!matcher.find()) {
            errorsList.add("\nСерия паспорта должена содержать 4 цифры");
            log.info("checkForPreScoringErrors():  добавление в errorsList: {}", errorsList.get(errorsList.size() - 1));
        }

        matcher = emailPattern.matcher(loanApplicationRequestDTO.getEmail());
        if (!matcher.find()) {
            errorsList.add("\nНекорректная почта");
            log.info("checkForPreScoringErrors():  добавление в errorsList: {}", errorsList.get(errorsList.size() - 1));
        }

        if (0 <= loanApplicationRequestDTO.getBirthdate().plus(18, ChronoUnit.YEARS).compareTo(LocalDate.now())) {
            errorsList.add("\nВозраст меньше 18 лет");
            log.info("checkForPreScoringErrors():  добавление в errorsList: {}", errorsList.get(errorsList.size() - 1));
        }

        if (!errorsList.isEmpty()) {
            log.info("checkForPreScoringErrors(): итоговый errorsList: {}", errorsList);
            throw new PreScoringException(errorsList);
        }
    }
}
