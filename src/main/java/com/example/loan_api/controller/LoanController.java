package com.example.loan_api.controller;
import com.example.loan_api.dto.LoanRequest;
import com.example.loan_api.model.Installment;
import com.example.loan_api.model.Loan;
import com.example.loan_api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody LoanRequest loanRequest) {
        Loan loan = loanService.createLoan(loanRequest);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Loan>> listLoans(@PathVariable Long customerId) {
        List<Loan> loans = loanService.listLoans(customerId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/installments/{loanId}")
    public ResponseEntity <List<Installment>> listInstallments(@PathVariable Long loanId) {
        List<Installment> installments = loanService.listInstallments(loanId);
        return ResponseEntity.ok(installments);
    }

    @PostMapping("/pay/{loanId}")
    public ResponseEntity<String> payLoan(@PathVariable Long loanId, @RequestParam double amount) {
        String result = loanService.payLoan(loanId, amount);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/health")
    public String test() {
        return "Healty";
    }
}