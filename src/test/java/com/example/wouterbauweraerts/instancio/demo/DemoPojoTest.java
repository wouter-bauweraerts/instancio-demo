package com.example.wouterbauweraerts.instancio.demo;

import static com.example.wouterbauweraerts.instancio.demo.fixtures.DemoPojoFixtures.aDemoPojo;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.wouterbauweraerts.instancio.demo.fixtures.DemoPojoFixtures;

class DemoPojoTest {
    @Test
    void allFieldsCanBeNull() {
        DemoPojo instance = DemoPojoFixtures.fixtureBuilder()
                .ignoreAnInt()
                .ignoreAString()
                .ignoreATimestamp()
                .ignoreANestedPojo()
                .build();

        assertThat(instance).isNotNull()
                .returns(null, DemoPojo::anInt)
                .returns(null, DemoPojo::aString)
                .returns(null, DemoPojo::aTimestamp)
                .returns(null, DemoPojo::aNestedPojo);
    }

    @Test
    void allFieldsArePopulatedByDefault() {
        assertThat(aDemoPojo()).satisfies(pojo -> {
            assertThat(pojo.anInt()).isNotNull();
            assertThat(pojo.aString()).isNotNull();
            assertThat(pojo.aTimestamp()).isNotNull();
            assertThat(pojo.anInt()).isNotNull();

            assertThat(pojo.aNestedPojo()).satisfies(
                    nestedPojo -> {
                        assertThat(nestedPojo.aPrimitiveInt()).isPositive();
                        assertThat(nestedPojo.aDouble()).isGreaterThanOrEqualTo(0.5);
                        assertThat(nestedPojo.aBigInt()).isNotNull();
                    }
            );
        });
    }

    @Test
    void definedModelIsRespectedInGeneration() {
        assertThat(aDemoPojo()).satisfies(
                pojo -> {
                    assertThat(pojo.anInt()).isBetween(2000, 20000);
                    assertThat(pojo.aTimestamp()).isAfter(LocalDateTime.now());
                }
        );
    }

    @Test
    void weCanIgnoreRangesDefinedInModelWithFixtureBuilder() {
        DemoPojo demoPojo = DemoPojoFixtures.fixtureBuilder()
                .withAnInt(-1)
                .build();

        assertThat(demoPojo.anInt()).isEqualTo(-1);
    }
}