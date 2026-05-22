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


import jakarta.annotation.Nullable;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.RandomUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.RandomUtils
 * @see org.apache.commons.lang3.RandomStringUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class RandomUtilsWraps {
    /**
     * Returns a random string whose length is the number of characters specified
     *
     * <p>
     * Characters will be chosen from the set of Latin alphabetic characters (a-z, A-Z)
     * </p>
     *
     * @param count The length of random string to create
     *
     * @return a random string whose length is the number of characters specified
     */
    @Nullable
    public static String randomAlphabetic(int count) {
        return (count <= 0) ? null : RandomStringUtils.secure().nextAlphabetic(count);
    }

    /**
     * Returns a random string whose length is between the inclusive minimum and the exclusive maximum
     *
     * <p>
     * Characters will be chosen from the set of Latin alphabetic characters (a-z, A-Z)
     * </p>
     *
     * @param minLengthInclusive The inclusive minimum length of the string to generate
     * @param maxLengthExclusive The exclusive maximum length of the string to generate
     *
     * @return a random string whose length is between the inclusive minimum and the exclusive maximum
     */
    @Nullable
    public static String randomAlphabetic(int minLengthInclusive, int maxLengthExclusive) {
        return (minLengthInclusive < 0 || minLengthInclusive > maxLengthExclusive) ? null : RandomStringUtils.secure().nextAlphabetic(minLengthInclusive, maxLengthExclusive);
    }

    /**
     * Returns a random string whose length is the number of characters specified
     *
     * <p>
     * Characters will be chosen from the set of Latin alphabetic characters (a-z, A-Z) and the digits 0-9
     * </p>
     *
     * @param count The length of random string to create
     *
     * @return a random string whose length is the number of characters specified
     */
    /**
     * Returns a random string whose length is the number of characters specified
     *
     * <p>
     * Characters will be chosen from the set of Latin alphabetic characters (a-z, A-Z) and the digits 0-9
     * </p>
     *
     * @param count The length of random string to create
     *
     * @return a random alphanumeric string whose length is the number of characters specified, or {@code null} if count is less than or equal to 0
     */
    @Nullable
    public static String randomAlphanumeric(final int count) {
        return (count <= 0) ? null : RandomStringUtils.secure().nextAlphanumeric(count);
    }

    /**
     * Returns a random string whose length is between the inclusive minimum and the exclusive maximum
     *
     * <p>
     * Characters will be chosen from the set of Latin alphabetic characters (a-z, A-Z) and the digits 0-9
     * </p>
     *
     * @param minLengthInclusive The inclusive minimum length of the string to generate
     * @param maxLengthExclusive The exclusive maximum length of the string to generate
     *
     * @return a random string whose length is between the inclusive minimum and the exclusive maximum
     */
    @Nullable
    public static String randomAlphanumeric(final int minLengthInclusive, final int maxLengthExclusive) {
        return (minLengthInclusive < 0 || minLengthInclusive > maxLengthExclusive) ? null : RandomStringUtils.secure().nextAlphanumeric(minLengthInclusive, maxLengthExclusive);
    }

    /**
     * Returns a random character from the set of upper case and lower case letters
     *
     * @return a random character (either upper case or lower case)
     */
    public static char randomChar() {
        return RandomUtils.secure().randomBoolean() ? randomCharUpper() : randomCharLower();
    }

    /**
     * Returns a random lower case character (a-z)
     *
     * @return a random lower case character
     */
    public static char randomCharLower() {
        return (char) RandomUtils.secure().randomInt(97, 123);
    }

    /**
     * Returns a random upper case character (A-Z)
     *
     * @return a random upper case character
     */
    public static char randomCharUpper() {
        return (char) RandomUtils.secure().randomInt(65, 91);
    }

    /**
     * Returns a random string whose length is the number of characters specified
     *
     * <p>
     * Characters will be chosen from the set of numeric characters.
     * </p>
     *
     * @param count The length of random string to create
     *
     * @return a random string whose length is the number of characters specified
     */
    @Nullable
    public static String randomNumeric(int count) {
        return (count <= 0) ? null : RandomStringUtils.secure().nextNumeric(count);
    }

    /**
     * Returns a random string whose length is between the inclusive minimum and the exclusive maximum
     *
     * <p>
     * Characters will be chosen from the set of \p{Digit} characters.
     * </p>
     *
     * @param minLengthInclusive The inclusive minimum length of the string to generate
     * @param maxLengthExclusive The exclusive maximum length of the string to generate
     *
     * @return a random string whose length is between the inclusive minimum and the exclusive maximum
     */
    @Nullable
    public static String randomNumeric(int minLengthInclusive, int maxLengthExclusive) {
        return (minLengthInclusive < 0 || minLengthInclusive > maxLengthExclusive) ? null : RandomStringUtils.secure().nextNumeric(minLengthInclusive, maxLengthExclusive);
    }
}
