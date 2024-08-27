package com.example.wouterbauweraerts.instancio.demo;

import java.time.LocalDateTime;

public record DemoPojo(
        Integer anInt,
        String aString,
        LocalDateTime aTimestamp,
        NestedPojo aNestedPojo
) {
}
