package com.swe266group12.bankapp.model;

public class Deposit {
    private String deposit;
    private String withdraw;

    public Deposit() {}

    public Deposit(String deposit, String withdraw) {
        this.deposit = deposit;
        this.withdraw = withdraw;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(String withdraw) {
        this.withdraw = withdraw;
    }
}
