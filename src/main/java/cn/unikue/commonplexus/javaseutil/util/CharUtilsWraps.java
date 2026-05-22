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


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.CharUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.CharUtils
 * @see org.apache.commons.lang3.CharSequenceUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class CharUtilsWraps {
    /**
     * Check if two Character objects are equal.
     *
     * @param character the first character
     * @param comparison the character to compare with
     * @return true if both characters are equal, false otherwise
     */
    public static boolean equals(@Nullable Character character, @Nullable Character comparison) {
        return Objects.equals(character, comparison);
    }

    /**
     * Check if two Character objects are equal ignoring case.
     *
     * @param character the first character
     * @param comparison the character to compare with
     * @return true if both characters are equal ignoring case, false otherwise
     */
    public static boolean equalsIgnoreCase(@Nullable Character character, @Nullable Character comparison) {
        if (character == comparison) {
            return true;
        }
        if (character == null || comparison == null) {
            return false;
        }
        return Character.toLowerCase(character) == comparison || Character.toUpperCase(character) == comparison;
    }

    /**
     * Check if the character equals any of the comparison characters.
     *
     * @param character the character to check
     * @param comparisons the characters to compare with
     * @return true if character equals any comparison, false otherwise
     */
    public static boolean equalsAny(@Nullable Character character, @Nullable Character... comparisons) {
        return equalsAny(character, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Check if the character equals any character in the collection.
     *
     * @param character the character to check
     * @param comparisons the collection of characters to compare with
     * @return true if character equals any in collection, false otherwise
     */
    public static boolean equalsAny(@Nullable Character character, @Nullable Collection<Character> comparisons) {
        return CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(item -> equals(character, item));
    }

    /**
     * Check if the character equals any of the comparison characters ignoring case.
     *
     * @param character the character to check
     * @param comparisons the characters to compare with
     * @return true if character equals any comparison ignoring case, false otherwise
     */
    public static boolean equalsAnyIgnoreCase(@Nullable Character character, @Nullable Character... comparisons) {
        return equalsAnyIgnoreCase(character, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Check if the character equals any character in the collection ignoring case.
     *
     * @param character the character to check
     * @param comparisons the collection of characters to compare with
     * @return true if character equals any in collection ignoring case, false otherwise
     */
    public static boolean equalsAnyIgnoreCase(@Nullable Character character, @Nullable Collection<Character> comparisons) {
        return CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(item -> equalsIgnoreCase(character, item));
    }

    /**
     * Convert char array to String.
     *
     * @param chars the char array to convert
     * @return the String representation, or null if array is empty
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

    /**
     * Convert char array to String array (each char becomes a String).
     *
     * @param chars the char array to convert
     * @return the String array, or null if input is empty
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String[] toStringArray(@Nullable char... chars) {
        if (ArrayUtils.isEmpty(chars)) {
            return null;
        }
        String[] result = new String[ArrayUtils.getLength(chars)];
        ArrayUtilsWraps.forEachIndexing(chars, (index, item) -> result[index] = CharUtils.toString(chars[index]));
        return result;
    }

    /**
     * Convert char array to List of Strings (each char becomes a String).
     *
     * @param chars the char array to convert
     * @return the List of Strings, or null if input is empty
     */
    @Nullable
    public static List<String> toStringList(@Nullable char... chars) {
        if (ArrayUtils.isEmpty(chars)) {
            return null;
        }
        List<String> result = new ArrayList<>(ArrayUtils.getLength(chars));
        ArrayUtilsWraps.forEach(chars, item -> result.add(CharUtils.toString(item)));
        return result;
    }

    /**
     * Convert char array to Set of Strings (each char becomes a String, duplicates removed).
     *
     * @param chars the char array to convert
     * @return the Set of Strings, or null if input is empty
     */
    @Nullable
    public static Set<String> toStringSet(@Nullable char... chars) {
        if (ArrayUtils.isEmpty(chars)) {
            return null;
        }
        Set<String> result = new LinkedHashSet<>(ArrayUtils.getLength(chars));
        ArrayUtilsWraps.forEach(chars, item -> result.add(CharUtils.toString(item)));
        return result;
    }
}
