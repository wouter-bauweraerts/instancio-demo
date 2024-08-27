package com.example.wouterbauweraerts.instancio.demo;

import java.time.LocalDateTime;

public record DemoPojo(
        Integer anInt,
        String sString,
        LocalDateTime aTimestamp,
        NestedPojo aNestedPojo
) {
}
