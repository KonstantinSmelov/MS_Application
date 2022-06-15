package neostudy.feignclient;

import neostudy.dto.LoanApplicationRequestDTO;
import neostudy.dto.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="deal-service-client", url = "${URL.toDeal}")
public interface DealClient {

    @PostMapping("/deal/application")
    List<LoanOfferDTO> getLoanOfferListFromDeal(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO);

    @PutMapping("/deal/offer")
    ResponseEntity<?> confirmLoanOfferToDeal(@RequestBody LoanOfferDTO loanOfferDTO);
}
