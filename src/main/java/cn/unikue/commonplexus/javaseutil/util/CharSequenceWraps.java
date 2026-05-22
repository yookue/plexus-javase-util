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


import java.util.Objects;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.lang.CharSequence}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.CharSequenceUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class CharSequenceWraps {
    /**
     * Return empty CharSequence if sequence equals comparison.
     *
     * @param sequence the CharSequence to check
     * @param comparison the CharSequence to compare with
     * @return empty CharSequence if equal, original sequence otherwise
     */
    public static CharSequence emptyIfEquals(@Nullable CharSequence sequence, @Nullable CharSequence comparison) {
        return StringUtils.equals(sequence, comparison) ? StringUtils.EMPTY : sequence;
    }

    /**
     * Return empty CharSequence if sequence equals comparison ignoring case.
     *
     * @param sequence the CharSequence to check
     * @param comparison the CharSequence to compare with
     * @return empty CharSequence if equal ignoring case, original sequence otherwise
     */
    public static CharSequence emptyIfEqualsIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence comparison) {
        return StringUtils.equalsIgnoreCase(sequence, comparison) ? StringUtils.EMPTY : sequence;
    }

    /**
     * Convert CharSequence to String, treating null as null.
     *
     * @param sequence the CharSequence to convert
     * @return the String representation, or null if sequence is null
     */
    public static String toStringIgnoreNull(@Nullable CharSequence sequence) {
        return toStringIgnoreNull(sequence, null);
    }

    /**
     * Convert CharSequence to String, treating null as specified value.
     *
     * @param sequence the CharSequence to convert
     * @param nullString the String to return if sequence is null
     * @return the String representation, or nullString if sequence is null
     */
    public static String toStringIgnoreNull(@Nullable CharSequence sequence, @Nullable String nullString) {
        return Objects.toString(sequence, nullString);
    }

    /**
     * Convert CharSequence to String, treating empty as null.
     *
     * @param sequence the CharSequence to convert
     * @return the String representation, or null if sequence is empty
     */
    public static String toStringIgnoreEmpty(@Nullable CharSequence sequence) {
        return toStringIgnoreEmpty(sequence, null);
    }

    /**
     * Convert CharSequence to String, treating empty as specified value.
     *
     * @param sequence the CharSequence to convert
     * @param emptyString the String to return if sequence is empty
     * @return the String representation, or emptyString if sequence is empty
     */
    public static String toStringIgnoreEmpty(@Nullable CharSequence sequence, @Nullable String emptyString) {
        return StringUtils.isEmpty(sequence) ? emptyString : sequence.toString();
    }

    /**
     * Convert CharSequence to String, treating blank as null.
     *
     * @param sequence the CharSequence to convert
     * @return the String representation, or null if sequence is blank
     */
    public static String toStringIgnoreBlank(@Nullable CharSequence sequence) {
        return toStringIgnoreBlank(sequence, null);
    }

    /**
     * Convert CharSequence to String, treating blank as specified value.
     *
     * @param sequence the CharSequence to convert
     * @param blankString the String to return if sequence is blank
     * @return the String representation, or blankString if sequence is blank
     */
    public static String toStringIgnoreBlank(@Nullable CharSequence sequence, @Nullable String blankString) {
        return StringUtils.isBlank(sequence) ? blankString : sequence.toString();
    }
}
