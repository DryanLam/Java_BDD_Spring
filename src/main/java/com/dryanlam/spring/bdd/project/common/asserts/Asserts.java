package com.dryanlam.spring.bdd.project.common.asserts;

import com.dryanlam.spring.bdd.project.common.exception.ProjectException;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Asserts {

    public enum AssertionMessage {
        EQUAL_ERROR_MESSAGE("\n*** ERROR: ''{0}'' Equal Assertion. \nExpected: ''{1}''. \nActual: " + "''{2}''."),
        NOT_EQUAL_ERROR_MESSAGE("\n*** ERROR: ''{0}'' Not Equal Assertion. \nExpected: ''{1}''. \nActual: " + "''{2}''."),
        LIST_EQUAL_ERROR_MESSAGE("\n*** ERROR: ''{0}'' List Equal Assertion.\nExpected: ''{1}''.\nGot     : ''{2}''.\nMissing elements: ''{3}''.\nExtra elements  : ''{4}''"),
        LIST_CONTAIN_ERROR_MESSAGE("\n*** ERROR: ''{0}'' List Contain Assertion.\nExpected:\n {1}.\nGot:\n {2}.\nMissing elements: {3}"),
        LIST_NOT_CONTAIN_ERROR_MESSAGE("*** ERROR: ''{0}'' List Not Contain Assertion.\nExpected:\n {1}.\nGot:\n {2}.\nCommon elements: {3}"),
        LIST_NOT_SORTED("*** ERROR: ''{0}'' List Not Sorted Assertion.\nExpected order: {1}.\nGot:\n {2}.");

        private String value;

        AssertionMessage(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private Asserts() {
    }

    public static void assertEquals(Object first, Object second, String... assertType) {
        if (!Objects.equals(first, second)) {
            throw new ProjectException(MessageFormat.format(
                    AssertionMessage.EQUAL_ERROR_MESSAGE.getValue(),
                    assertType.length > 0 ? assertType[0] : "", first, second));
        }
    }

    public static void assertNotEquals(Object first, Object second, String... assertType) {
        if (Objects.equals(first, second)) {
            throw new ProjectException(MessageFormat.format(
                    AssertionMessage.NOT_EQUAL_ERROR_MESSAGE.getValue(),
                    assertType.length > 0 ? assertType[0] : "", first, second));
        }
    }

    public static void assertEquals(List<Object> first, List<Object> second, Comparator comparator, String... assertType) {
        Collections.sort(first, comparator);
        Collections.sort(second, comparator);
        assertEqualsInOrder(first, second, assertType);
    }

    private static void assertEqualsInOrder(List<Object> first, List<Object> second, String... assertType) {
        if (!first.equals(second)) {
            List<Object> missing = new ArrayList<>(second);
            missing.removeAll(first);
            List<Object> extra = new ArrayList<>(first);
            extra.removeAll(second);

            throw new ProjectException(MessageFormat.format(
                    AssertionMessage.LIST_EQUAL_ERROR_MESSAGE.getValue(),
                    assertType.length > 0 ? assertType[0] : "", first, second, missing, extra));
        }
    }

    public static void assertContainsInOrder(List<Object> first, List<Object> second, String... assertType) {
        List<Object> commonList = Arrays.asList(first.stream().filter(second::contains).toArray(Object[]::new));
        assertEqualsInOrder(commonList, second, assertType);
    }

    public static void assertContains(List<Object> first, List<Object> second, String... assertType) {
        if (!first.containsAll(second)) {
            List<Object> missing = new ArrayList<>(second);
            missing.removeAll(first);
            throw new ProjectException(MessageFormat.format(
                    AssertionMessage.LIST_CONTAIN_ERROR_MESSAGE.getValue(),
                    assertType.length > 0 ? assertType[0] : "", first, second, missing));
        }
    }

    public static void assertNotContains(List<Object> first, List<Object> second, String... assertType) {
        List<Object> commonList = first.stream().filter(second::contains).collect(Collectors.toList());
        if (!(commonList.isEmpty())) {
            throw new ProjectException(MessageFormat.format(
                    AssertionMessage.LIST_NOT_CONTAIN_ERROR_MESSAGE.getValue(),
                    assertType.length > 0 ? assertType[0] : "", first, second, commonList));
        }
    }
}
