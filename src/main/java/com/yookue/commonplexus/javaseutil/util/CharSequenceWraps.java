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


import java.util.Objects;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.lang.CharSequence}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.CharSequenceUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class CharSequenceWraps {
    public static CharSequence emptyIfEquals(@Nullable CharSequence sequence, @Nullable CharSequence comparison) {
        return StringUtils.equals(sequence, comparison) ? StringUtils.EMPTY : sequence;
    }

    public static CharSequence emptyIfEqualsIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence comparison) {
        return StringUtils.equalsIgnoreCase(sequence, comparison) ? StringUtils.EMPTY : sequence;
    }

    public static String toStringIgnoreNull(@Nullable CharSequence sequence) {
        return toStringIgnoreNull(sequence, null);
    }

    public static String toStringIgnoreNull(@Nullable CharSequence sequence, @Nullable String nullString) {
        return Objects.toString(sequence, nullString);
    }

    public static String toStringIgnoreEmpty(@Nullable CharSequence sequence) {
        return toStringIgnoreEmpty(sequence, null);
    }

    public static String toStringIgnoreEmpty(@Nullable CharSequence sequence, @Nullable String emptyString) {
        return StringUtils.isEmpty(sequence) ? emptyString : sequence.toString();
    }

    public static String toStringIgnoreBlank(@Nullable CharSequence sequence) {
        return toStringIgnoreBlank(sequence, null);
    }

    public static String toStringIgnoreBlank(@Nullable CharSequence sequence, @Nullable String blankString) {
        return StringUtils.isBlank(sequence) ? blankString : sequence.toString();
    }
}
