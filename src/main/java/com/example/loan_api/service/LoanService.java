package com.example.loan_api.service;


import com.example.loan_api.dto.LoanRequest;
import com.example.loan_api.model.Customer;
import com.example.loan_api.model.Installment;
import com.example.loan_api.model.Loan;
import com.example.loan_api.repository.CustomerRepository;
import com.example.loan_api.repository.InstallmentRepository;
import com.example.loan_api.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private InstallmentRepository installmentRepository;

    public Loan createLoan(LoanRequest loanRequest) {
        Customer customer = customerRepository.findById(loanRequest.getCustomerId()).orElseThrow();
        if (customer.getCreditLimit() < loanRequest.getAmount()) {
            throw new IllegalArgumentException("Customer does not have enough credit limit.");
        }
        if (loanRequest.getInstallment() != 6 && loanRequest.getInstallment() != 9 && loanRequest.getInstallment() != 12 && loanRequest.getInstallment()!= 24) {
            throw new IllegalArgumentException("Invalid number of installments.");
        }
        if (loanRequest.getInterestRate() < 0.1 || loanRequest.getInterestRate() > 0.5) {
            throw new IllegalArgumentException("Interest rate must be between 0.1 and 0.5.");
        }

        Loan loan = new Loan();
        loan.setAmount(loanRequest.getAmount());
        loan.setInterestRate(loanRequest.getInterestRate());
        loan.setInstallment(loanRequest.getInstallment());
        loan.setCustomer(customer);
        loan.setPaid(false);
        loanRepository.save(loan);

        double totalAmount = loanRequest.getAmount() * (1 + loanRequest.getInterestRate());
        double installmentAmount = totalAmount / loanRequest.getInstallment();

        for (int i = 0; i < loanRequest.getInstallment(); i++) {
            Installment installmentEntity = new Installment();
            installmentEntity.setAmount(installmentAmount);
            installmentEntity.setDueDate(LocalDate.now().plusMonths(i + 1).withDayOfMonth(1));
            installmentEntity.setLoan(loan);
            installmentEntity.setPaid(false);
            installmentRepository.save(installmentEntity);
        }

        return loan;
    }

    public List<Loan> listLoans(Long customerId) {
        return loanRepository.findByCustomerId(customerId);
    }

    public List<Installment> listInstallments(Long loanId) {
        return installmentRepository.findByLoanId(loanId);
    }

    public String payLoan(Long loanId, double amount) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        List<Installment> installments = installmentRepository.findByLoanId(loanId);
        double totalPaid = 0;
        int installmentsPaid = 0;

        for (Installment installment : installments) {
            if (!installment.isPaid() && totalPaid + installment.getAmount() <= amount) {
                totalPaid += installment.getAmount();
                installment.setPaid(true);
                installmentsPaid++;
                installmentRepository.save(installment);
            }
        }

        if (installmentsPaid > 0) {
            loan.setPaid(installments.stream().allMatch(Installment::isPaid));
            loanRepository.save(loan);
        }

        return String.format("Paid %d installments, total amount spent: %.2f, loan paid completely: %b",
                installmentsPaid, totalPaid, loan.isPaid());
    }
}