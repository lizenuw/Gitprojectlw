package com.lizenuw;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;

public class CallCharge {
    private final ZoneId zoneId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private static final double RATE_20_MINUTES_OR_LESS = 0.05;
    private static final double FLAT_RATE = 1.00;
    private static final double RATE_GREATER_THAN_20_MINUTES = 0.10;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MAX_CALL_DURATION = 20;

    public CallCharge(final ZoneId zoneId, final LocalDateTime startTime, final LocalDateTime endTime) {
        this.zoneId = zoneId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long calculateTimeSpan() {
        Duration duration = Duration.between(startTime, endTime);
        long seconds = duration.getSeconds();
        long minutes = seconds / SECONDS_PER_MINUTE + (seconds % SECONDS_PER_MINUTE == 0 ? 0 : 1);

        ZonedDateTime startZdt = ZonedDateTime.of(startTime, zoneId);
        ZonedDateTime endZdt = ZonedDateTime.of(endTime, zoneId);
        ZoneRules zoneRules = zoneId.getRules();

        if (!zoneRules.isDaylightSavings(startZdt.toInstant()) && zoneRules.isDaylightSavings(endZdt.toInstant())) {
            return minutes - MINUTES_PER_HOUR;
        }
        if (zoneRules.isDaylightSavings(startZdt.toInstant()) && !zoneRules.isDaylightSavings(endZdt.toInstant())) {
            return minutes + MINUTES_PER_HOUR;
        }
        return minutes;
    }

    public double calculateFee() {
        long minutes = calculateTimeSpan();
        return minutes <= MAX_CALL_DURATION ? minutes * RATE_20_MINUTES_OR_LESS
                : FLAT_RATE + (minutes - MAX_CALL_DURATION) * RATE_GREATER_THAN_20_MINUTES;
    }
}
