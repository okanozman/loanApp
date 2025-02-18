package com.example.loan_api.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class LoanRequest {

    @NotNull
    private Long customerId;

    @NotNull
    @Min(0)
    private double amount;

    @NotNull
    @Min(0)
    private double interestRate;

    @NotNull
    @Min(1)
    private int installment;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getInstallment() {
        return installment;
    }

    public void setInstallment(int installment) {
        this.installment = installment;
    }
}