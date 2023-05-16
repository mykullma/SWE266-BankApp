package com.swe266group12.bankapp.model;

public class Deposit {
    private Long deposit;
    private Long withdraw;

    public Deposit() {}

    public Deposit(Long deposit, Long withdraw) {
        this.deposit = deposit;
        this.withdraw = withdraw;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Long withdraw) {
        this.withdraw = withdraw;
    }
}
