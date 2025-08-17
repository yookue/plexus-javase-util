/*
 * Copyright (c) 2016 Yookue Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yookue.commonplexus.javaseutil.util;


import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.BooleanUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.BooleanUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class BooleanUtilsWraps {
    private static final String ONE = "1";    // $NON-NLS-1$
    private static final String ZERO = "0";    // $NON-NLS-1$
    private static final String LOWER_T = "t";    // $NON-NLS-1$
    private static final String LOWER_F = "f";    // $NON-NLS-1$
    private static final String LOWER_Y = "y";    // $NON-NLS-1$
    private static final String LOWER_N = "n";    // $NON-NLS-1$
    private static final String UPPER_T = "T";    // $NON-NLS-1$
    private static final String UPPER_F = "F";    // $NON-NLS-1$
    private static final String UPPER_Y = "Y";    // $NON-NLS-1$
    private static final String UPPER_N = "N";    // $NON-NLS-1$

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allTrue(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtils::isTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allTrue(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allTrue(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allTrue(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotTrue(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtils::isNotTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotTrue(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotTrue(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotTrue(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allFalse(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtils::isFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allFalse(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allFalse(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allFalse(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotFalse(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtils::isNotFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotFalse(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotFalse(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotFalse(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyTrue(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtils::isTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyTrue(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyTrue(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyTrue(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotTrue(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtils::isNotTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotTrue(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotTrue(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotTrue(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotTrue);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyFalse(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtils::isFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyFalse(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyFalse(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyFalse(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotFalse(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtils::isNotFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotFalse(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotFalse(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotFalse);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotFalse(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotFalse);
    }

    public static void ifTrue(@Nullable Boolean value, @Nullable Consumer<Boolean> action) {
        if (BooleanUtils.isTrue(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifTrue(@Nullable Boolean value, @Nullable Runnable action) {
        if (BooleanUtils.isTrue(value) && action != null) {
            action.run();
        }
    }

    public static void ifTrue(@Nullable String value, @Nullable Consumer<String> action) {
        if (isTrue(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifTrue(@Nullable String value, @Nullable Runnable action) {
        if (isTrue(value) && action != null) {
            action.run();
        }
    }

    public static void ifTrue(@Nullable Integer value, @Nullable Consumer<Integer> action) {
        if (isTrue(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifTrue(@Nullable Integer value, @Nullable Runnable action) {
        if (isTrue(value) && action != null) {
            action.run();
        }
    }

    public static void ifTrue(@Nullable Object value, @Nullable Consumer<Object> action) {
        if (isTrue(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifTrue(@Nullable Object value, @Nullable Runnable action) {
        if (isTrue(value) && action != null) {
            action.run();
        }
    }

    public static void ifTrueOrElse(@Nullable Boolean value, @Nullable Consumer<Boolean> presentAction, @Nullable Consumer<Boolean> absentAction) {
        if (BooleanUtils.isTrue(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    public static void ifTrueOrElse(@Nullable Boolean value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (BooleanUtils.isTrue(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static void ifTrueOrElse(@Nullable String value, @Nullable Consumer<String> presentAction, @Nullable Consumer<String> absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    public static void ifTrueOrElse(@Nullable String value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static void ifTrueOrElse(@Nullable Integer value, @Nullable Consumer<Integer> presentAction, @Nullable Consumer<Integer> absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    public static void ifTrueOrElse(@Nullable Integer value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static void ifTrueOrElse(@Nullable Object value, @Nullable Consumer<Object> presentAction, @Nullable Consumer<Object> absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    public static void ifTrueOrElse(@Nullable Object value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static void ifFalse(@Nullable Boolean value, @Nullable Consumer<Boolean> action) {
        if (BooleanUtils.isFalse(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifFalse(@Nullable Boolean value, @Nullable Runnable action) {
        if (BooleanUtils.isFalse(value) && action != null) {
            action.run();
        }
    }

    public static void ifFalse(@Nullable String value, @Nullable Consumer<String> action) {
        if (isFalse(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifFalse(@Nullable String value, @Nullable Runnable action) {
        if (isFalse(value) && action != null) {
            action.run();
        }
    }

    public static void ifFalse(@Nullable Integer value, @Nullable Consumer<Integer> action) {
        if (isFalse(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifFalse(@Nullable Integer value, @Nullable Runnable action) {
        if (isFalse(value) && action != null) {
            action.run();
        }
    }

    public static void ifFalse(@Nullable Object value, @Nullable Consumer<Object> action) {
        if (isFalse(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifFalse(@Nullable Object value, @Nullable Runnable action) {
        if (isFalse(value) && action != null) {
            action.run();
        }
    }

    public static void ifFalseOrElse(@Nullable Boolean value, @Nullable Consumer<Boolean> absentAction, @Nullable Consumer<Boolean> presentAction) {
        if (BooleanUtils.isFalse(value)) {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        }
    }

    public static void ifFalseOrElse(@Nullable Boolean value, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (BooleanUtils.isFalse(value)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static void ifFalseOrElse(@Nullable String value, @Nullable Consumer<String> absentAction, @Nullable Consumer<String> presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        }
    }

    public static void ifFalseOrElse(@Nullable String value, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static void ifFalseOrElse(@Nullable Integer value, @Nullable Consumer<Integer> absentAction, @Nullable Consumer<Integer> presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        }
    }

    public static void ifFalseOrElse(@Nullable Integer value, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static void ifFalseOrElse(@Nullable Object value, @Nullable Consumer<Object> absentAction, @Nullable Consumer<Object> presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        }
    }

    public static void ifFalseOrElse(@Nullable Object value, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static void ifNotTrue(@Nullable Boolean value, @Nullable Consumer<Boolean> action) {
        if (BooleanUtils.isNotTrue(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifNotTrue(@Nullable Boolean value, @Nullable Runnable action) {
        if (BooleanUtils.isNotTrue(value) && action != null) {
            action.run();
        }
    }

    public static void ifNotTrue(@Nullable String value, @Nullable Consumer<String> action) {
        if (isNotTrue(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifNotTrue(@Nullable String value, @Nullable Runnable action) {
        if (isNotTrue(value) && action != null) {
            action.run();
        }
    }

    public static void ifNotTrue(@Nullable Integer value, @Nullable Consumer<Integer> action) {
        if (isNotTrue(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifNotTrue(@Nullable Integer value, @Nullable Runnable action) {
        if (isNotTrue(value) && action != null) {
            action.run();
        }
    }

    public static void ifNotTrue(@Nullable Object value, @Nullable Consumer<Object> action) {
        if (isNotTrue(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifNotTrue(@Nullable Object value, @Nullable Runnable action) {
        if (isNotTrue(value) && action != null) {
            action.run();
        }
    }

    public static void ifNotFalse(@Nullable Boolean value, @Nullable Consumer<Boolean> action) {
        if (BooleanUtils.isNotFalse(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifNotFalse(@Nullable Boolean value, @Nullable Runnable action) {
        if (BooleanUtils.isNotFalse(value) && action != null) {
            action.run();
        }
    }

    public static void ifNotFalse(@Nullable String value, @Nullable Consumer<String> action) {
        if (isNotFalse(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifNotFalse(@Nullable String value, @Nullable Runnable action) {
        if (isNotFalse(value) && action != null) {
            action.run();
        }
    }

    public static void ifNotFalse(@Nullable Integer value, @Nullable Consumer<Integer> action) {
        if (isNotFalse(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifNotFalse(@Nullable Integer value, @Nullable Runnable action) {
        if (isNotFalse(value) && action != null) {
            action.run();
        }
    }

    public static void ifNotFalse(@Nullable Object value, @Nullable Consumer<Object> action) {
        if (isNotFalse(value) && action != null) {
            action.accept(value);
        }
    }

    public static void ifNotFalse(@Nullable Object value, @Nullable Runnable action) {
        if (isNotFalse(value) && action != null) {
            action.run();
        }
    }

    public static boolean isTrue(@Nullable String value) {
        return BooleanUtils.isTrue(BooleanUtils.toBooleanObject(value));
    }

    public static boolean isTrue(@Nullable Integer value) {
        return BooleanUtils.isTrue(BooleanUtils.toBooleanObject(value));
    }

    public static boolean isTrue(@Nullable Object value) {
        if (value == null) {
            return false;
        } else if (value instanceof Boolean alias) {
            return BooleanUtils.isTrue(alias);
        } else if (value instanceof String alias) {
            return isTrue(alias);
        } else if (value instanceof Integer alias) {
            return isTrue(alias);
        }
        return isTrue(Objects.toString(value));
    }

    public static boolean isFalse(@Nullable String value) {
        return BooleanUtils.isFalse(BooleanUtils.toBooleanObject(value));
    }

    public static boolean isFalse(@Nullable Integer value) {
        return BooleanUtils.isFalse(BooleanUtils.toBooleanObject(value));
    }

    public static boolean isFalse(@Nullable Object value) {
        if (value == null) {
            return false;
        } else if (value instanceof Boolean alias) {
            return BooleanUtils.isFalse(alias);
        } else if (value instanceof String alias) {
            return isFalse(alias);
        } else if (value instanceof Integer alias) {
            return isFalse(alias);
        }
        return isFalse(Objects.toString(value));
    }

    public static boolean isNotTrue(@Nullable String value) {
        return BooleanUtils.isNotTrue(BooleanUtils.toBooleanObject(value));
    }

    public static boolean isNotTrue(@Nullable Integer value) {
        return BooleanUtils.isNotTrue(BooleanUtils.toBooleanObject(value));
    }

    public static boolean isNotTrue(@Nullable Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof Boolean alias) {
            return BooleanUtils.isNotTrue(alias);
        } else if (value instanceof String alias) {
            return isNotTrue(alias);
        } else if (value instanceof Integer alias) {
            return isNotTrue(alias);
        }
        return isNotTrue(Objects.toString(value));
    }

    public static boolean isNotFalse(@Nullable String value) {
        return BooleanUtils.isNotFalse(BooleanUtils.toBooleanObject(value));
    }

    public static boolean isNotFalse(@Nullable Integer value) {
        return BooleanUtils.isNotFalse(BooleanUtils.toBooleanObject(value));
    }

    public static boolean isNotFalse(@Nullable Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof Boolean alias) {
            return BooleanUtils.isNotFalse(alias);
        } else if (value instanceof String alias) {
            return isNotFalse(alias);
        } else if (value instanceof Integer alias) {
            return isNotFalse(alias);
        }
        return isNotFalse(Objects.toString(value));
    }

    public static Boolean toBooleanObject(@Nullable String value, @Nullable String truthy, @Nullable String falsy) {
        return toBooleanObject(value, truthy, falsy, null);
    }

    @Nullable
    public static Boolean toBooleanObject(@Nullable String value, @Nullable String truthy, @Nullable String falsy, @Nullable String nil) {
        try {
            return BooleanUtils.toBooleanObject(value, truthy, falsy, nil);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Boolean toBooleanObjectIgnoreCase(@Nullable String value, @Nullable String truthy, @Nullable String falsy) {
        return toBooleanObjectIgnoreCase(value, truthy, falsy, null);
    }

    /**
     * Returns a Boolean object that converted from the {@code value} String
     *
     * @param value The String to check
     * @param truthy The String to match for true
     * @param falsy The String to match for false
     * @param nil The String to match for null
     *
     * @return a Boolean object that converted from the {@code value} String
     *
     * @throws java.lang.IllegalArgumentException if the String doesn't match
     * @see org.apache.commons.lang3.BooleanUtils#toBooleanObject(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public static Boolean toBooleanObjectIgnoreCase(@Nullable String value, @Nullable String truthy, @Nullable String falsy, @Nullable String nil) {
        if (value == null) {
            if (truthy == null) {
                return Boolean.TRUE;
            }
            if (falsy == null) {
                return Boolean.FALSE;
            }
            if (nil == null) {
                return null;
            }
        } else if (value.equalsIgnoreCase(truthy)) {
            return Boolean.TRUE;
        } else if (value.equalsIgnoreCase(falsy)) {
            return Boolean.FALSE;
        } else if (value.equalsIgnoreCase(nil)) {
            return null;
        }
        throw new IllegalArgumentException("The String did not match any specified value, even case insensitive");    // $NON-NLS-1$
    }

    @Nullable
    public static Boolean toBooleanObjectIgnoreCaseQuietly(@Nullable String value, @Nullable String truthy, @Nullable String falsy) {
        return toBooleanObjectIgnoreCaseQuietly(value, truthy, falsy, null);
    }

    @Nullable
    public static Boolean toBooleanObjectIgnoreCaseQuietly(@Nullable String value, @Nullable String truthy, @Nullable String falsy, @Nullable String nil) {
        try {
            return toBooleanObjectIgnoreCase(value, truthy, falsy, nil);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static String toString(boolean value, char truthy, char falsy) {
        return BooleanUtils.toString(value, CharUtils.toString(truthy), CharUtils.toString(falsy));
    }

    public static String toString(@Nullable Boolean value, char truthy, char falsy) {
        return toString(value, truthy, falsy, null);
    }

    public static String toString(@Nullable Boolean value, char truthy, char falsy, char nil) {
        return BooleanUtils.toString(value, CharUtils.toString(truthy), CharUtils.toString(falsy), CharUtils.toString(nil));
    }

    public static String toString(@Nullable Boolean value, char truthy, char falsy, String nil) {
        return BooleanUtils.toString(value, CharUtils.toString(truthy), CharUtils.toString(falsy), nil);
    }

    public static String toString10(boolean value) {
        return BooleanUtils.toString(value, ONE, ZERO);
    }

    public static String toString10(@Nullable Boolean value) {
        return BooleanUtils.toString(value, ONE, ZERO, null);
    }

    public static String toStringOnOff(boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringOnOff(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    public static String toStringOnOff(@Nullable Boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringOnOff(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    public static String toStringTrueFalse(boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringTrueFalse(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    public static String toStringTrueFalse(@Nullable Boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringTrueFalse(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    public static String toStringYesNo(boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringYesNo(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    public static String toStringYesNo(@Nullable Boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringYesNo(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    public static String toStringTF(boolean value) {
        return toStringTF(value, false);
    }

    public static String toStringTF(boolean value, boolean uppercase) {
        return BooleanUtils.toString(value, (uppercase ? UPPER_T : LOWER_T), (uppercase ? UPPER_F : LOWER_F));
    }

    public static String toStringTF(@Nullable Boolean value) {
        return toStringTF(value, false);
    }

    public static String toStringTF(@Nullable Boolean value, boolean uppercase) {
        return BooleanUtils.toString(value, (uppercase ? UPPER_T : LOWER_T), (uppercase ? UPPER_F : LOWER_F), null);
    }

    public static String toStringYN(boolean value) {
        return toStringYN(value, false);
    }

    public static String toStringYN(boolean value, boolean uppercase) {
        return BooleanUtils.toString(value, (uppercase ? UPPER_Y : LOWER_Y), (uppercase ? UPPER_N : LOWER_N));
    }

    public static String toStringYN(@Nullable Boolean value) {
        return toStringYN(value, false);
    }

    public static String toStringYN(@Nullable Boolean value, boolean uppercase) {
        return BooleanUtils.toString(value, (uppercase ? UPPER_Y : LOWER_Y), (uppercase ? UPPER_N : LOWER_N), null);
    }
}
