package com.bil.katas.pbt;

import com.bil.katas.pbt.bank.Account;
import com.bil.katas.pbt.bank.Withdraw;
import com.bil.katas.pbt.generators.AccountGenerator;
import com.bil.katas.pbt.generators.WithdrawGenerator;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.control.Try;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
import static org.junit.Assume.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class Account_properties {
    @Property
    public void balance_should_be_decremented_of_the_amount(
            @From(AccountGenerator.class) Account account,
            @From(WithdrawGenerator.class) Withdraw withdraw) {

        withEnoughMoney(account, withdraw);
        withoutReachingMaxWithdrawal(account, withdraw);

        Try<Account> debitedAccount = account.withdraw(withdraw);

        Assert.assertEquals(
                account.getBalance() - withdraw.getAmount(),
                debitedAccount.get().getBalance(), 0);
    }

    @Property
    public void balance_should_be_decremented_as_well_when_insufficient_balance_but_overdraft_authorized(
            @From(AccountGenerator.class) Account account,
            @From(WithdrawGenerator.class) Withdraw withdraw) {

        withInsufficientBalance(account, withdraw);
        withoutReachingMaxWithdrawal(account, withdraw);
        withOverdraftAuthorized(account);

        Try<Account> debitedAccount = account.withdraw(withdraw);

        Assert.assertEquals(
                account.getBalance() - withdraw.getAmount(),
                debitedAccount.get().getBalance(), 0);
    }

    @Property
    public void withdraw_should_not_be_allowed_when_withdraw_over_maxWithdrawal_requested(
            @From(AccountGenerator.class) Account account,
            @From(WithdrawGenerator.class) Withdraw withdraw) {

        assumeThat(account.getMaxWithdrawal(), lessThan(withdraw.getAmount()));

        Throwable failure = account.withdraw(withdraw).getCause();

        Assert.assertTrue(failure instanceof IllegalArgumentException);
        Assert.assertTrue(failure.getMessage().startsWith("Amount exceeding your limit of"));
    }

    @Property
    public void withdraw_should_not_be_allowed_when_no_money_and_no_overdraft(
            @From(AccountGenerator.class) Account account,
            @From(WithdrawGenerator.class) Withdraw withdraw) {

        withInsufficientBalance(account, withdraw);
        withoutReachingMaxWithdrawal(account, withdraw);
        withoutOverdraftAuthorized(account);

        Throwable failure = account.withdraw(withdraw).getCause();

        Assert.assertTrue(failure instanceof IllegalArgumentException);
        Assert.assertTrue(failure.getMessage().startsWith("Insufficient balance to withdraw"));
    }

    private void withoutOverdraftAuthorized(Account account) {
        assumeThat(account.isOverdraftAuthorized(), is(false));
    }

    private void withEnoughMoney(Account account, Withdraw withdraw) {
        assumeThat(account.getBalance(), greaterThan(withdraw.getAmount()));
    }

    private void withoutReachingMaxWithdrawal(Account account, Withdraw withdraw) {
        assumeThat(account.getMaxWithdrawal(), greaterThan(withdraw.getAmount()));
    }

    private void withInsufficientBalance(Account account, Withdraw withdraw) {
        assumeThat(account.getBalance(), lessThan(withdraw.getAmount()));
    }

    private void withOverdraftAuthorized(Account account) {
        assumeThat(account.isOverdraftAuthorized(), is(true));
    }
}