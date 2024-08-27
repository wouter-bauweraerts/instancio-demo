package com.example.wouterbauweraerts.instancio.demo.fixtures;

import static org.instancio.Select.all;
import static org.instancio.Select.allInts;
import static org.instancio.Select.field;

import java.time.LocalDateTime;

import org.instancio.Instancio;
import org.instancio.Model;

import com.example.wouterbauweraerts.instancio.demo.DemoPojo;
import com.example.wouterbauweraerts.instancio.demo.NestedPojo;

public class DemoPojoFixtures {
    private static final Model<DemoPojo> DEMO_POJO_MODEL = Instancio.of(DemoPojo.class)
            .generate(allInts(), gen -> gen.ints().min(2000).max(20000))
            .generate(all(LocalDateTime.class), gen -> gen.temporal().localDateTime().future())
            .supply(field(DemoPojo::aNestedPojo), NestedPojoFixtures::aNestedPojo)
            .toModel();

    public static DemoPojo aDemoPojo() {
        return Instancio.create(DEMO_POJO_MODEL);
    }

    public static FixtureBuilder fixtureBuilder() {
        return new FixtureBuilder();
    }

    public static class FixtureBuilder extends AbstractFixtureBuilder<DemoPojo, FixtureBuilder> {
        @Override
        public DemoPojo build() {
            return buildInternal(DEMO_POJO_MODEL);
        }

        @Override
        public FixtureBuilder self() {
            return this;
        }

        public FixtureBuilder withAnInt(Integer value) {
            return setField(DemoPojo::anInt, value);
        }

        public FixtureBuilder ignoreAnInt() {
            return withAnInt(null);
        }

        public FixtureBuilder withAString(String value) {
            return setField(DemoPojo::aString, value);
        }
        public FixtureBuilder ignoreAString() {
            return withAString(null);
        }

        public FixtureBuilder withATimestamp(LocalDateTime value) {
            return setField(DemoPojo::aTimestamp, value);
        }
        public FixtureBuilder ignoreATimestamp() {
            return withATimestamp(null);
        }

        public FixtureBuilder withANestedPojo(NestedPojo value) {
            return setField(DemoPojo::aNestedPojo, value);
        }
        public FixtureBuilder ignoreANestedPojo() {
            return withANestedPojo(null);
        }
    }
}
