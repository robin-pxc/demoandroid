package com.example.robinpan.demoandroid.CommonUtils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testRegularExpressionMatcher() {
        // Define the regular expression that only can contain numbers in a string.
        String regex = "^[0-9]*$";
        String expectedStr = "1234567890";
        String unexpectedStr = "1234567890aS";

        assertTrue(StringUtils.RegexMatcher.isMatch(expectedStr, regex));
        assertFalse(StringUtils.RegexMatcher.isMatch(unexpectedStr, regex));
    }
}