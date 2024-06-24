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


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.CharUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.CharUtils
 * @see org.apache.commons.lang3.CharSequenceUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class CharUtilsWraps {
    public static boolean equals(@Nullable Character character, @Nullable Character comparison) {
        return Objects.equals(character, comparison);
    }

    public static boolean equalsIgnoreCase(@Nullable Character character, @Nullable Character comparison) {
        if (character == comparison) {
            return true;
        }
        if (character == null || comparison == null) {
            return false;
        }
        return Character.toLowerCase(character) == comparison || Character.toUpperCase(character) == comparison;
    }

    public static boolean equalsAny(@Nullable Character character, @Nullable Character... comparisons) {
        return equalsAny(character, ArrayUtilsWraps.asList(comparisons));
    }

    public static boolean equalsAny(@Nullable Character character, @Nullable Collection<Character> comparisons) {
        return CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(element -> equals(character, element));
    }

    public static boolean equalsAnyIgnoreCase(@Nullable Character character, @Nullable Character... comparisons) {
        return equalsAnyIgnoreCase(character, ArrayUtilsWraps.asList(comparisons));
    }

    public static boolean equalsAnyIgnoreCase(@Nullable Character character, @Nullable Collection<Character> comparisons) {
        return CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(element -> equalsIgnoreCase(character, element));
    }

    /**
     * Returns an uppercase Character object on the given value
     * <pre>
     *     CharUtilsWraps.upperCase(0)  = 'A'
     *     CharUtilsWraps.upperCase(25) = 'Z'
     * </pre>
     *
     * @param value the value to convert, must between 0(inclusive) and 26(exclusive)
     *
     * @return an uppercase Character object on the given value
     */
    @Nullable
    public static Character upperCase(short value) {
        return (value < 0 || value > 25) ? null : (char) (value + 65);
    }

    /**
     * Returns a lowercase Character object on the given value
     * <pre>
     *     CharUtilsWraps.toLowerCharacter(0)  = 'a'
     *     CharUtilsWraps.toLowerCharacter(25) = 'z'
     * </pre>
     *
     * @param value the value to convert, must between 0(inclusive) and 26(exclusive)
     *
     * @return a lowercase Character object on the given value
     */
    @Nullable
    public static Character lowerCase(short value) {
        return (value < 0 || value > 25) ? null : (char) (value + 97);
    }

    /**
     * @see org.apache.commons.lang3.CharSequenceUtils#toCharArray
     */
    @Nullable
    public static String toString(@Nullable char... chars) {
        if (ArrayUtils.isEmpty(chars)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        ArrayUtilsWraps.forEach(chars, builder::append);
        return builder.toString();
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String[] toStringArray(@Nullable char... chars) {
        if (ArrayUtils.isEmpty(chars)) {
            return null;
        }
        String[] result = new String[ArrayUtils.getLength(chars)];
        ArrayUtilsWraps.forEachIndexing(chars, (index, element) -> result[index] = CharUtils.toString(chars[index]));
        return result;
    }

    @Nullable
    public static List<String> toStringList(@Nullable char... chars) {
        if (ArrayUtils.isEmpty(chars)) {
            return null;
        }
        List<String> result = new ArrayList<>(ArrayUtils.getLength(chars));
        ArrayUtilsWraps.forEach(chars, element -> result.add(CharUtils.toString(element)));
        return result;
    }

    @Nullable
    public static Set<String> toStringSet(@Nullable char... chars) {
        if (ArrayUtils.isEmpty(chars)) {
            return null;
        }
        Set<String> result = new LinkedHashSet<>(ArrayUtils.getLength(chars));
        ArrayUtilsWraps.forEach(chars, element -> result.add(CharUtils.toString(element)));
        return result;
    }
}
