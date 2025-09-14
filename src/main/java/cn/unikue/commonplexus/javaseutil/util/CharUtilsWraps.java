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
        return CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(item -> equals(character, item));
    }

    public static boolean equalsAnyIgnoreCase(@Nullable Character character, @Nullable Character... comparisons) {
        return equalsAnyIgnoreCase(character, ArrayUtilsWraps.asList(comparisons));
    }

    public static boolean equalsAnyIgnoreCase(@Nullable Character character, @Nullable Collection<Character> comparisons) {
        return CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(item -> equalsIgnoreCase(character, item));
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
        ArrayUtilsWraps.forEachIndexing(chars, (index, item) -> result[index] = CharUtils.toString(chars[index]));
        return result;
    }

    @Nullable
    public static List<String> toStringList(@Nullable char... chars) {
        if (ArrayUtils.isEmpty(chars)) {
            return null;
        }
        List<String> result = new ArrayList<>(ArrayUtils.getLength(chars));
        ArrayUtilsWraps.forEach(chars, item -> result.add(CharUtils.toString(item)));
        return result;
    }

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
