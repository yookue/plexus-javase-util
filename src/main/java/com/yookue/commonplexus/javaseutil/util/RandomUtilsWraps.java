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


import javax.annotation.Nullable;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.RandomUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.RandomUtils
 * @see org.apache.commons.lang3.RandomStringUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class RandomUtilsWraps {
    public static char randomUpperChar() {
        return randomUpperChar('A', 'Z');
    }

    public static char randomUpperChar(char startInclusive, char endInclusive) {
        if (startInclusive > 'Z' || endInclusive < 'A' || startInclusive > endInclusive) {
            return 0;
        }
        return (char) RandomUtils.nextInt(startInclusive, (int) endInclusive + 1);
    }

    public static char randomLowerChar() {
        return randomLowerChar('a', 'z');
    }

    public static char randomLowerChar(char startInclusive, char endInclusive) {
        if (startInclusive > 'z' || endInclusive < 'a' || startInclusive > endInclusive) {
            return 0;
        }
        return (char) RandomUtils.nextInt(startInclusive, (int) endInclusive + 1);
    }

    @Nullable
    public static String randomUpperAlphabetic(int count) {
        return randomUpperAlphabetic(count, 'A', 'Z');
    }

    @Nullable
    public static String randomUpperAlphabetic(int count, char startInclusive, char endInclusive) {
        if (count <= 0 || startInclusive > 'Z' || endInclusive < 'A' || startInclusive > endInclusive) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (char i = startInclusive; i <= endInclusive; i++) {
            builder.append(i);
        }
        return RandomStringUtils.random(count, builder.toString());
    }

    @Nullable
    public static String randomLowerAlphabetic(int count) {
        return randomLowerAlphabetic(count, 'a', 'z');
    }

    @Nullable
    public static String randomLowerAlphabetic(int count, char startInclusive, char endInclusive) {
        if (count <= 0 || startInclusive > 'z' || endInclusive < 'a' || startInclusive > endInclusive) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (char i = startInclusive; i <= endInclusive; i++) {
            builder.append(i);
        }
        return RandomStringUtils.random(count, builder.toString());
    }

    @Nullable
    public static String randomCapitalizeAlphabetic(int count) {
        return randomCapitalizeAlphabetic(count, 'a', 'z');
    }

    @Nullable
    public static String randomCapitalizeAlphabetic(int count, char startInclusive, char endInclusive) {
        if (count <= 0) {
            return null;
        }
        // Ensure the set of characters is 'a' to 'z'
        char start = (startInclusive >= 65 && startInclusive <= 90) ? (char) ((int) startInclusive + 32) : startInclusive;
        char end = (endInclusive >= 65 && endInclusive <= 90) ? (char) ((int) endInclusive + 32) : endInclusive;
        if (start > 'z' || end < 'a' || start > end) {
            return null;
        }
        return StringUtils.capitalize(randomLowerAlphabetic(count, start, end));
    }
}
