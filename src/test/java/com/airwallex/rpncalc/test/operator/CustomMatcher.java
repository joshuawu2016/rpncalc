package com.airwallex.rpncalc.test.operator;

import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CustomMatcher extends TypeSafeMatcher<ExecutionException> {
    ErrorCode foundErrorCode;
    ErrorCode expectedErrorCode;

    private CustomMatcher(ErrorCode expectedErrorCode) {
        this.expectedErrorCode = expectedErrorCode;
    }

    public static CustomMatcher hasCode(ErrorCode item) {
        return new CustomMatcher(item);
    }

    @Override
    protected boolean matchesSafely(ExecutionException item) {
        this.foundErrorCode = item.getErrorCode();
        return foundErrorCode == expectedErrorCode;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(foundErrorCode).appendText(" was not found instead of ").appendValue(expectedErrorCode);
    }
}
