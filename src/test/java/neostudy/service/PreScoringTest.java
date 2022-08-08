package neostudy.service;

import lombok.RequiredArgsConstructor;
import neostudy.dto.LoanApplicationRequestDTO;
import neostudy.exception.PreScoringException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PreScoringTest {

    @InjectMocks
    private PreScoring preScoring;

    @Test
    void checkForPreScoringErrors() {

        LoanApplicationRequestDTO loanApplicationRequestDTO = LoanApplicationRequestDTO.builder()
                .amount(BigDecimal.valueOf(1000))
                .term(5)
                .passportSeries("1234")
                .passportNumber("123456")
                .firstName("Иван")
                .middleName("Иванович")
                .lastName("Иванов")
                .birthdate(LocalDate.of(2000, 5, 5))
                .email("mail@mail.com")
                .build();

        assertThrows(PreScoringException.class, () -> preScoring.checkForPreScoringErrors(loanApplicationRequestDTO));
    }
}