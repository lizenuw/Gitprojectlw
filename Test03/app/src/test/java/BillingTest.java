import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingTest {
    private final Billing billing = new Billing();

    @Test
    @DisplayName("无时制转换，通话时长小于等于20分钟")
    void test1() {
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 10, 15, 0);
        assertEquals(0.75, billing.calculateCost(startTime, endTime));
    }

    @Test
    @DisplayName("无时制转换，通话时长大于20分钟")
    void test2() {
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 10, 30, 0);
        assertEquals(2.00, billing.calculateCost(startTime, endTime));
    }

    @Test
    @DisplayName("春夏时制转换，通话时长小于等于20分钟")
    void test3() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 26, 1, 50, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 26, 3, 5, 0);
        assertEquals(0.75, billing.calculateCost(startTime, endTime));
    }

    @Test
    @DisplayName("春夏时制转换，通话时长大于20分钟")
    void test4() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 26, 1, 50, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 26, 3, 20, 0);
        assertEquals(2.00, billing.calculateCost(startTime, endTime));
    }

    @Test
    @DisplayName("夏秋时制转换，通话时长小于等于20分钟")
    void test5() {
        LocalDateTime startTime = LocalDateTime.of(2023, 11, 5, 2, 50, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 11, 5, 2, 5, 0);
        assertEquals(0.75, billing.calculateCost(startTime, endTime));
    }

    @Test
    @DisplayName("夏秋时制转换，通话时长大于20分钟")
    void test6() {
        LocalDateTime startTime = LocalDateTime.of(2023, 11, 5, 2, 50, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 11, 5, 2, 20, 0);
        assertEquals(2.00, billing.calculateCost(startTime, endTime));
    }
}
