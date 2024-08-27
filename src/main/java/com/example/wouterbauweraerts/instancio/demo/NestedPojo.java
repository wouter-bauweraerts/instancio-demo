package com.example.wouterbauweraerts.instancio.demo;

import java.math.BigInteger;

public record NestedPojo(
        int aPrimitiveInt,
        double aDouble,
        BigInteger aBigInt
) {
}
