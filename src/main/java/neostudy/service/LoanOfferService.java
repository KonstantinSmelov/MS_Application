package neostudy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import neostudy.dto.LoanApplicationRequestDTO;
import neostudy.dto.LoanOfferDTO;
import neostudy.exception.PreScoringException;
import neostudy.feignclient.DealClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoanOfferService {

    private final DealClient dealClient;
    private final Scoring scoring;

    public List<LoanOfferDTO> gettingLoanOfferDTOList(LoanApplicationRequestDTO loanApplicationRequestDTO) throws PreScoringException {
        scoring.checkForPreScoringErrors(loanApplicationRequestDTO);
        List<LoanOfferDTO> loanOfferDTOList = dealClient.getLoanOfferListFromDeal(loanApplicationRequestDTO);
        log.debug("creatingLoanOfferDTOList(): Получен loanOfferDTOList из Deal: {}", loanOfferDTOList);

        return loanOfferDTOList;
    }

    public void confirmOffer(LoanOfferDTO loanOfferDTO) {
        dealClient.confirmLoanOfferToDeal(loanOfferDTO);
        log.debug("confirmOffer(): Получен loanOfferDTO из Deal: {}", loanOfferDTO);
    }
}
