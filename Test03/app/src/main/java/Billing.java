import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;

public class Billing {
    private static final double RATE_20_MINUTES_OR_LESS = 0.05; // 不超过20分钟的费率（每分钟）
    private static final double FLAT_RATE = 1.00; // 超过20分钟的固定费用
    private static final double RATE_GREATER_THAN_20_MINUTES = 0.10; // 超过20分钟的费率（每分钟）
    private static final double SECONDS_PER_MINUTE = 60.0; // 每分钟的秒数
    private static final LocalTime DST_START_TIME = LocalTime.of(2, 0, 0);
    private static final LocalTime DST_END_TIME = LocalTime.of(2, 59, 59);
    private static final int MAX_CALL_DURATION = 20;
    private static final int DST_START_MONTH = 3;
    private static final int DST_END_MONTH = 11;

    // 计算通话费用
    public double calculateCost(LocalDateTime startTime, LocalDateTime endTime) {
        double minutes = getDurationInMinutes(startTime, endTime);
        return calculateCost(minutes);
    }

    // 获取两个时间点之间的通话时长（分钟）
    private double getDurationInMinutes(LocalDateTime startTime, LocalDateTime endTime) {
        // 夏令时开始时间
        final LocalDateTime dstStart = startTime.withMonth(DST_START_MONTH)
                .with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY)).with(DST_START_TIME);
        // 夏令时结束时间
        final LocalDateTime dstEnd = startTime.withMonth(DST_END_MONTH)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY)).with(DST_END_TIME);

        if (startTime.isBefore(dstStart) && endTime.isAfter(dstStart)) {
            endTime = endTime.minusHours(1);
        }
        if (startTime.isBefore(dstEnd) && (endTime.isAfter(dstEnd) || endTime.isBefore(startTime))) {
            endTime = endTime.plusHours(1);
        }
        return Math.ceil((endTime.toEpochSecond(ZoneOffset.UTC)
                - startTime.toEpochSecond(ZoneOffset.UTC)) / SECONDS_PER_MINUTE);
    }

    // 根据通话时长计算通话费用
    private double calculateCost(final double minutes) {
        return (minutes <= MAX_CALL_DURATION) ? Math.ceil(minutes) * RATE_20_MINUTES_OR_LESS
                : FLAT_RATE + (Math.ceil(minutes) - MAX_CALL_DURATION) * RATE_GREATER_THAN_20_MINUTES;
    }
}
