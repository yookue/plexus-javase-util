/*
 * Copyright (c) 2016 Unikue Ltd. All rights reserved.
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

package cn.unikue.commonplexus.javaseutil.util;


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;


/**
 * Utilities for assertion messages
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.Validate
 * @see "org.springframework.util.Assert"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class AssertMessageWraps {
    private static final String HAS_LENGTH = "The argument '%s' must has length.";    // $NON-NLS-1$
    private static final String HAS_TEXT = "The argument '%s' must has text.";    // $NON-NLS-1$
    private static final String IS_INSTANCE_OF = "The argument '%s' must be an instance of '%s'.";    // $NON-NLS-1$
    private static final String IS_ASSIGNABLE = "The argument '%s' must be assignable from '%s'.";    // $NON-NLS-1$
    private static final String IS_NULL = "The argument '%s' must be null.";    // $NON-NLS-1$
    private static final String IS_TRUE = "The expression '%s' must be true.";    // $NON-NLS-1$
    private static final String NOT_CONTAINS = "The argument '%s' must not contains '%s'.";    // $NON-NLS-1$
    private static final String NOT_EMPTY = "The argument '%s' must not be empty.";    // $NON-NLS-1$
    private static final String NOT_EQUALS = "The argument '%s' must not equals to '%s'.";    // $NON-NLS-1$
    private static final String NOT_NULL = "The argument '%s' must not be null.";    // $NON-NLS-1$
    private static final String NOT_NULL_ELEMENTS = "The argument '%s' must not contains any null elements.";    // $NON-NLS-1$
    private static final String STATE = "The state invariant '%s' must be true.";    // $NON-NLS-1$

    private static final String CONTAINS = "The argument '%s' must contains '%s'.";    // $NON-NLS-1$
    private static final String EMPTY = "The argument '%s' must be empty.";    // $NON-NLS-1$
    private static final String EQUALS = "The argument '%s' must equals '%s'.";    // $NON-NLS-1$
    private static final String EXISTS = "The argument '%s' must be exists.";    // $NON-NLS-1$
    private static final String NOT_EXISTS = "The argument '%s' must not be exists.";    // $NON-NLS-1$

    private static final String IS_YEAR = "The argument '%s' must be a year.";    // $NON-NLS-1$
    private static final String IS_MONTH = "The argument '%s' must be a month.";    // $NON-NLS-1$
    private static final String IS_DAY = "The argument '%s' must be a day.";    // $NON-NLS-1$
    private static final String IS_DIRECTORY = "The argument '%s' must be a directory.";    // $NON-NLS-1$
    private static final String IS_FILE = "The argument '%s' must be a file.";    // $NON-NLS-1$
    private static final String IS_POSITIVE = "The argument '%s' must be positive.";    // $NON-NLS-1$
    private static final String IS_NEGATIVE = "The argument '%s' must be negative.";    // $NON-NLS-1$
    private static final String IS_EXECUTABLE = "The argument '%s' must be executable.";    // $NON-NLS-1$
    private static final String IS_READABLE = "The argument '%s' must be readable.";    // $NON-NLS-1$
    private static final String IS_WRITABLE = "The argument '%s' must be writable.";    // $NON-NLS-1$
    private static final String NOT_POSITIVE = "The argument '%s' must not be positive.";    // $NON-NLS-1$
    private static final String NOT_NEGATIVE = "The argument '%s' must not be negative.";    // $NON-NLS-1$

    @Nonnull
    public static String hasLength(@Nullable String argument) {
        return String.format(HAS_LENGTH, argument);
    }

    /**
     * Generate assertion message for text with content requirement.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String hasText(@Nullable String argument) {
        return String.format(HAS_TEXT, argument);
    }

    /**
     * Generate assertion message for instance type check.
     *
     * @param argument the argument name
     * @param superclass the expected superclass name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isInstanceOf(@Nullable String argument, @Nullable String superclass) {
        return String.format(IS_INSTANCE_OF, argument, superclass);
    }

    /**
     * Generate assertion message for type assignability check.
     *
     * @param argument the argument name
     * @param subclass the expected subclass name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isAssignable(@Nullable String argument, @Nullable String subclass) {
        return String.format(IS_ASSIGNABLE, argument, subclass);
    }

    /**
     * Generate assertion message for null check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isNull(@Nullable String argument) {
        return String.format(IS_NULL, argument);
    }

    /**
     * Generate assertion message for boolean expression check.
     *
     * @param expression the expression description
     * @return formatted assertion message
     */
    @Nonnull
    public static String isTrue(@Nullable String expression) {
        return String.format(IS_TRUE, expression);
    }

    /**
     * Generate assertion message for not-contains check.
     *
     * @param argument the argument name
     * @param comparison the value that should not be contained
     * @return formatted assertion message
     */
    @Nonnull
    public static String notContains(@Nullable String argument, @Nullable String comparison) {
        return String.format(NOT_CONTAINS, argument, comparison);
    }

    /**
     * Generate assertion message for not-empty check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String notEmpty(@Nullable String argument) {
        return String.format(NOT_EMPTY, argument);
    }

    /**
     * Generate assertion message for inequality check.
     *
     * @param argument the argument name
     * @param comparison the value to compare against
     * @return formatted assertion message
     */
    @Nonnull
    public static String notEquals(@Nullable String argument, @Nullable String comparison) {
        return String.format(NOT_EQUALS, argument, comparison);
    }

    /**
     * Generate assertion message for not-null check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String notNull(@Nullable String argument) {
        return String.format(NOT_NULL, argument);
    }

    /**
     * Generate assertion message for no-null-elements check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String notNullElements(@Nullable String argument) {
        return String.format(NOT_NULL_ELEMENTS, argument);
    }

    /**
     * Generate assertion message for state invariant check.
     *
     * @param expression the state expression description
     * @return formatted assertion message
     */
    @Nonnull
    public static String state(@Nullable String expression) {
        return String.format(STATE, expression);
    }

    /**
     * Generate assertion message for contains check.
     *
     * @param argument the argument name
     * @param comparison the value that should be contained
     * @return formatted assertion message
     */
    @Nonnull
    public static String contains(@Nullable String argument, @Nullable String comparison) {
        return String.format(CONTAINS, argument, comparison);
    }

    /**
     * Generate assertion message for empty check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String empty(@Nullable String argument) {
        return String.format(EMPTY, argument);
    }

    /**
     * Generate assertion message for equality check.
     *
     * @param argument the argument name
     * @param comparison the value to compare against
     * @return formatted assertion message
     */
    @Nonnull
    public static String equals(@Nullable String argument, @Nullable String comparison) {
        return String.format(EQUALS, argument, comparison);
    }

    /**
     * Generate assertion message for existence check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String exists(@Nullable String argument) {
        return String.format(EXISTS, argument);
    }

    /**
     * Generate assertion message for non-existence check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String notExists(@Nullable String argument) {
        return String.format(NOT_EXISTS, argument);
    }

    /**
     * Generate assertion message for year validation.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isYear(@Nullable String argument) {
        return String.format(IS_YEAR, argument);
    }

    /**
     * Generate assertion message for month validation.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isMonth(@Nullable String argument) {
        return String.format(IS_MONTH, argument);
    }

    /**
     * Generate assertion message for day validation.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isDay(@Nullable String argument) {
        return String.format(IS_DAY, argument);
    }

    /**
     * Generate assertion message for directory check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isDirectory(@Nullable String argument) {
        return String.format(IS_DIRECTORY, argument);
    }

    /**
     * Generate assertion message for file check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isFile(@Nullable String argument) {
        return String.format(IS_FILE, argument);
    }

    /**
     * Generate assertion message for positive number check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isPositive(@Nullable String argument) {
        return String.format(IS_POSITIVE, argument);
    }

    /**
     * Generate assertion message for negative number check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isNegative(@Nullable String argument) {
        return String.format(IS_NEGATIVE, argument);
    }

    /**
     * Generate assertion message for executable permission check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isExecutable(@Nullable String argument) {
        return String.format(IS_EXECUTABLE, argument);
    }

    /**
     * Generate assertion message for readable permission check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isReadable(@Nullable String argument) {
        return String.format(IS_READABLE, argument);
    }

    /**
     * Generate assertion message for writable permission check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String isWritable(@Nullable String argument) {
        return String.format(IS_WRITABLE, argument);
    }

    /**
     * Generate assertion message for non-positive number check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String notPositive(@Nullable String argument) {
        return String.format(NOT_POSITIVE, argument);
    }

    /**
     * Generate assertion message for non-negative number check.
     *
     * @param argument the argument name
     * @return formatted assertion message
     */
    @Nonnull
    public static String notNegative(@Nullable String argument) {
        return String.format(NOT_NEGATIVE, argument);
    }
}
