package com.bil.katas.pbt.generators;

import com.bil.katas.pbt.bank.Withdraw;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.Date;

public class WithdrawGenerator extends Generator<Withdraw> {

    public WithdrawGenerator() {
        super(Withdraw.class);
    }

    @Override
    public Withdraw generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        return new Withdraw(sourceOfRandomness.nextDouble(), new Date());
    }
}
