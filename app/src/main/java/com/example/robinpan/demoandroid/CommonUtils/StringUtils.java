package com.example.robinpan.demoandroid.CommonUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static class RegexMatcher {
        // For username: length: 5 - 16, letters, numbers and '_', start with letter, with length 5-16.
        public static final String USERNAME_REGEX = "^[a-zA-Z0-9_-]{5,16}$";
        // For password: length: 6 - 20, letters, numbers, but No special characters
        public static final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";

        public static boolean isMatch(String str, String regex) {
            Pattern mPattern = Pattern.compile(regex);
            Matcher mMatcher = mPattern.matcher(str);
            return mMatcher.matches();
        }
    }
}
