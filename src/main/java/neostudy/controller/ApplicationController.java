package neostudy.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neostudy.dto.LoanApplicationRequestDTO;
import neostudy.dto.LoanOfferDTO;
import neostudy.exception.PreScoringException;
import neostudy.service.LoanOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final LoanOfferService loanOfferService;

    @ApiOperation(value = "Запрос кредитных предложений")
    @PostMapping
    public ResponseEntity<List<LoanOfferDTO>> getLoanOffers(@ApiParam(value = "Предварительные данные для расчётов") @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) throws PreScoringException {
        log.debug("/application -> получено LoanApplicationRequestDTO {}", loanApplicationRequestDTO);

        return ResponseEntity.ok(loanOfferService.gettingLoanOfferDTOList(loanApplicationRequestDTO));
    }

    @ApiOperation(value = "Выбор подходящего предложения")
    @PutMapping("/offer")
    public ResponseEntity<?> confirmOffer(@ApiParam(value = "Выбраное подходящее предложение") @RequestBody LoanOfferDTO loanOfferDTO) {
        log.debug("/application/offer -> получено LoanOfferDTO {}", loanOfferDTO);
        loanOfferService.confirmOffer(loanOfferDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
