package com.example.wouterbauweraerts.instancio.demo;

import static com.example.wouterbauweraerts.instancio.demo.fixtures.NestedPojoFixtures.aNestedPojo;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.wouterbauweraerts.instancio.demo.fixtures.NestedPojoFixtures;

class NestedPojoTest {
    @Test
    void testEquals_twoRandomPojos() {
        assertThat(aNestedPojo().equals(aNestedPojo())).isFalse();
    }

    @Test
    void testEquals_compareWithSelf() {
        NestedPojo nestedPojo = aNestedPojo();
        assertThat(nestedPojo.equals(nestedPojo)).isTrue();
    }

    @Test
    void testEquals_compareTwoEqualObjects() {
        NestedPojo nestedPojo = aNestedPojo();
        NestedPojo copy = NestedPojoFixtures.fixtureBuilder()
                .withAPrimitiveInt(nestedPojo.aPrimitiveInt())
                .withADouble(nestedPojo.aDouble())
                .withABigInt(nestedPojo.aBigInt())
                .build();

        assertThat(nestedPojo).isEqualTo(copy);
    }
}