import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {
    @Test
    void test1(){
        LinkedList<String> list = new LinkedList<>();

        list.remove(null);
        assertEquals(0, list.size());
    }

    @Test
    void test2(){
        LinkedList<String> list = new LinkedList<>();

        list.remove("a");
        assertEquals(0, list.size());
    }

    @Test
    void test3(){
        LinkedList<String> list = new LinkedList<>();
        list.add(null);
        list.add(null);

        list.remove(null);
        assertEquals(1, list.size());
    }

    @Test
    void test4(){
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");

        list.remove("a");
        assertEquals(1, list.size());
    }

    @Test
    void test5(){
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");

        list.remove(null);
        assertEquals(2, list.size());
    }

    @Test
    void test6(){
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");

        list.remove("c");
        assertEquals(2, list.size());
    }

}
