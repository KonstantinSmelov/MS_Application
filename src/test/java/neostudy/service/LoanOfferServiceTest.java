package neostudy.service;

import neostudy.dto.LoanApplicationRequestDTO;
import neostudy.dto.LoanOfferDTO;
import neostudy.exception.PreScoringException;
import neostudy.feignclient.DealClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class LoanOfferServiceTest {

    @InjectMocks
    private LoanOfferService loanOfferService;

    @Mock
    private DealClient dealClient;

    @Mock
    private PreScoring preScoring;

    @Test
    void gettingLoanOfferDTOList() throws PreScoringException {

        List<LoanOfferDTO> loanOfferDTOList = List.of(LoanOfferDTO.builder().term(18).build());

        when(dealClient.getLoanOfferListFromDeal(any(LoanApplicationRequestDTO.class))).thenReturn(loanOfferDTOList);

        assertEquals(18, loanOfferService.gettingLoanOfferDTOList(LoanApplicationRequestDTO.builder().build()).get(0).getTerm());
    }

    @Test
    void confirmOffer() {

        loanOfferService.confirmOffer(LoanOfferDTO.builder().build());

        verify(dealClient, times(1)).confirmLoanOfferToDeal(any(LoanOfferDTO.class));
    }
}