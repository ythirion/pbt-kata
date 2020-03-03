package com.bil.katas.pbt.bank;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class Account {
    private final double balance;
    private final boolean isOverdraftAuthorized;
    private final double maxWithdrawal;

    public Try<Account> withdraw(Withdraw command) {
        return Try.of(() -> {
            checkMaxWithdrawal(command);
            checkBalance(command);

            return new Account(balance - command.getAmount(),
                    this.isOverdraftAuthorized,
                    this.maxWithdrawal);
        });
    }

    private void checkMaxWithdrawal(Withdraw command) {
        if(command.getAmount() > maxWithdrawal) {
            throw new IllegalArgumentException("Amount exceeding your limit of " + maxWithdrawal);
        }
    }

    private void checkBalance(Withdraw command) {
        if(command.getAmount() > balance && !isOverdraftAuthorized()) {
            throw new IllegalArgumentException("Insufficient balance to withdraw : " + command.getAmount());
        }
    }
}

