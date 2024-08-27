package com.example.wouterbauweraerts.instancio.demo.fixtures;

import static org.instancio.Select.allDoubles;
import static org.instancio.Select.allInts;

import java.math.BigInteger;

import org.instancio.Instancio;
import org.instancio.Model;

import com.example.wouterbauweraerts.instancio.demo.NestedPojo;

public class NestedPojoFixtures {
    private static final Model<NestedPojo> NESTED_POJO_MODEL = Instancio.of(NestedPojo.class)
            .generate(allInts(), gen -> gen.ints().min(1))
            .generate(allDoubles(), gen -> gen.doubles().min(0.5))
            .toModel();

    public static NestedPojo aNestedPojo() {
        return Instancio.create(NESTED_POJO_MODEL);
    }

    public static FixtureBuilder fixtureBuilder() {
        return new FixtureBuilder();
    }

    public static class FixtureBuilder extends AbstractFixtureBuilder<NestedPojo,FixtureBuilder> {
        @Override
        public NestedPojo build() {
            return buildInternal(NESTED_POJO_MODEL);
        }

        @Override
        public FixtureBuilder self() {
            return this;
        }

        public FixtureBuilder withAPrimitiveInt(int value) {
            return setField(NestedPojo::aPrimitiveInt, value);
        }

        public FixtureBuilder withADouble(double value) {
            return setField(NestedPojo::aDouble, value);
        }

        public FixtureBuilder withABigInt(BigInteger value) {
            return setField(NestedPojo::aBigInt, value);
        }
    }
}
