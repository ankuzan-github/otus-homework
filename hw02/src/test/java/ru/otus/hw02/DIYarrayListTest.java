package ru.otus.hw02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DIYarrayListTest {

    private final List<String> testData = Arrays.asList(
            "jsakdjsalkd",
            "askdjasoidj",
            "012321304",
            "fjdhbfdijlsnvdjfbn",
            "djsnfijbnjfncvn",
            "djsnfijbnjfncvn",
            "djsnfijbnjfncvn",
            "djsnfijbnjfncvn",
            "djsnfijbnjfncvn",
            "djsnfijbnjfncvn",
            "3248934987234",
            "02394",
            "02394",
            "02394",
            "sdjncjnzxkcnjsahdhis",
            "apsdokjasodjk",
            "popjoij",
            "a",
            "0",
            "z",
            "!@#$%^&*()`~}{][|'",
            "zzzzzz"
    );

    @Test
    void createList() {
        var list = new DIYarrayList<String>();
        assertEquals(0, list.size());
    }

    @Test
    void addAllTest() {
        var list = new DIYarrayList<String>();
        Collections.addAll(list, testData.toArray(new String[0]));
        testData.forEach((el) -> assertTrue(list.contains(el)));
    }

    @Test
    void copyTest() {
        var list = new DIYarrayList<String>();
        for (int i = 0; i <= testData.size(); i++)
            list.add(String.valueOf(i));

        Collections.copy(list, testData);
        for (int i = 0; i < testData.size(); i++) {
            assertTrue(testData.contains(list.get(i)));
        }
    }

    @Test
    void sortTest() {
        var list = new DIYarrayList<String>();
        Collections.addAll(list, testData.toArray(new String[0]));
        list.sort(String::compareTo);

        assertThat(list).isSortedAccordingTo(String::compareTo);
    }

}
