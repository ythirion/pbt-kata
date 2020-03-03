package com.bil.katas.pbt.generators;

import com.bil.katas.pbt.bank.Account;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class AccountGenerator extends Generator<Account> {
    public AccountGenerator() {
        super(Account.class);
    }

    @Override
    public Account generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        return new Account(sourceOfRandomness.nextDouble(),
                sourceOfRandomness.nextBoolean(),
                sourceOfRandomness.nextDouble());
    }
}

