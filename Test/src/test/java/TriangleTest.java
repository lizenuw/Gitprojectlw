import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
/***
 *
 * @author Lizenuw
 * @date 2023年3月15日-20:15
 * @description 三角形测试用例
 */
class TriangleTest {
    @Test
    @DisplayName("输入错误")
    void parameters_error_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(0, 1, 2);
        assertEquals("输入错误", type);
    }
    @Test
    @DisplayName("非三角形")
    void test01() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(1, 2, 5);
        assertEquals("非三角形", type);
    }
    @Test
    @DisplayName("一般三角形")
    void test02() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(3, 4, 6);
        assertEquals("一般三角形", type);
    }
    @Test
    @DisplayName("等腰三角形")
    void test03() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(3, 5, 5);
        assertEquals("等腰三角形", type);
    }
    @Test
    @DisplayName("等边三角形")
    void test04() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(2, 2, 2);
        assertEquals("等边三角形", type);
    }
}