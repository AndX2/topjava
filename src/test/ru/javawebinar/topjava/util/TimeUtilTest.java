package ru.javawebinar.topjava.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilTest {

    @Test
    void isBetweenTest(){
        Assertions.assertEquals(TimeUtil.isBetween(LocalDateTime.of(2015, Month.MAY, 30, 20, 0).toLocalTime(),
                LocalDateTime.of(2015, Month.MAY, 30, 19, 59).toLocalTime(),
                LocalDateTime.of(2015, Month.MAY, 30, 20, 1).toLocalTime()),
                true);
    }


}