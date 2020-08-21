package com.example.demo.service;

import org.junit.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;


class CheckinServiceTest {

    CheckinService checkinService = new CheckinService(null, null);

    @Test
    void periodCalculationTest() {
        // Asserting that we get what we expect to get.
        Assert.assertEquals(9,
                checkinService.periodCalculation(
                        LocalDate.of(2020, 8, 1),
                        LocalDate.of(2020, 8, 1)
                )
        );
    }

    @Test
    void listOfDatesTest() {
        List<LocalDate> listOfDates = checkinService.listOfDates(
                9,
                LocalDate.of(2020, 8, 1));
        System.out.println(listOfDates);

        List<LocalDate> verificationList = List.of(
                LocalDate.of(2020, 8, 1),
                LocalDate.of(2020, 8, 2),
                LocalDate.of(2020, 8, 3),
                LocalDate.of(2020, 8, 4),
                LocalDate.of(2020, 8, 5),
                LocalDate.of(2020, 8, 6),
                LocalDate.of(2020, 8, 7),
                LocalDate.of(2020, 8, 8),
                LocalDate.of(2020, 8, 9)

        );
        System.out.println(verificationList);

        Assert.assertEquals(verificationList.size(), listOfDates.size());
        Assert.assertEquals(verificationList, listOfDates);

    }
}