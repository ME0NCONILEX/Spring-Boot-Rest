package se.me0nly.mysbrestlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.me0nly.mysbrestlibrary.dto.LoanDto;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;
import se.me0nly.mysbrestlibrary.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    LoanService loanService;

    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> findAll(){
        if (loanService.findAll().isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok(loanService.findAll());
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDto> findById(@PathVariable("loanId") Integer loanId) throws DataNotFoundException {
        if (loanId == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(loanService.findById(loanId));
    }

    @PostMapping("/")
    public ResponseEntity<LoanDto> create (@RequestBody LoanDto dto){
        if (dto==null){
            if (dto.getLoanId() !=0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    return ResponseEntity.status(HttpStatus.CREATED).body(loanService.create(dto));
    }

    @PutMapping("/")
    public ResponseEntity<LoanDto> update (@RequestBody LoanDto dto) throws DataNotFoundException {
        if (dto.getLoanId()==0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(loanService.update(dto));
    }



}
