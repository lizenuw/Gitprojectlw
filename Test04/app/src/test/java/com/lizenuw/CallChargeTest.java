package com.lizenuw;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallChargeTest {

    @DisplayName(value = "电话账单测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/电话账单测试用例.csv", numLinesToSkip = 1)
    void test(String startTime, String endTime, double expected) {
        ZoneId zoneId = ZoneId.of("America/New_York");
        CallCharge callCharge = new CallCharge(zoneId, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
        assertEquals(expected, callCharge.calculateFee(), 0.001);
    }
}
