package ru.javawebinar.topjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    static Main patient;

    @BeforeAll
    static void init(){
        patient = new Main();
    }

    @Test
    void mainTest(){
        patient.main(null);
        Assertions.assertTrue(true);
    }

}