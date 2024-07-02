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


import java.nio.charset.Charset;
import java.util.Collection;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.nio.charset.Charset}
 *
 * @author David Hsing
 * @see java.nio.charset.Charset
 * @see org.apache.commons.io.Charsets
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class CharsetPlainWraps {
    public static boolean allSupported(@Nullable CharSequence... charsets) {
        return allSupported(ArrayUtilsWraps.asList(charsets));
    }

    public static boolean allSupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isNotEmpty(charsets) && charsets.stream().allMatch(CharsetPlainWraps::isSupported);
    }

    public static boolean anySupported(@Nullable CharSequence... charsets) {
        return anySupported(ArrayUtilsWraps.asList(charsets));
    }

    public static boolean anySupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isNotEmpty(charsets) && charsets.stream().anyMatch(CharsetPlainWraps::isSupported);
    }

    public static boolean allNotSupported(@Nullable CharSequence... charsets) {
        return allNotSupported(ArrayUtilsWraps.asList(charsets));
    }

    public static boolean allNotSupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isEmpty(charsets) || charsets.stream().allMatch(CharsetPlainWraps::isNotSupported);
    }

    public static boolean anyNotSupported(@Nullable CharSequence... charsets) {
        return anyNotSupported(ArrayUtilsWraps.asList(charsets));
    }

    public static boolean anyNotSupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isEmpty(charsets) || charsets.stream().anyMatch(CharsetPlainWraps::isNotSupported);
    }

    @Nonnull
    public static Charset defaultCharset(@Nullable Charset charset) {
        return (charset != null) ? charset : Charset.defaultCharset();
    }

    @Nonnull
    public static String defaultCharsetName(@Nullable Charset charset) {
        return (charset != null) ? charset.name() : Charset.defaultCharset().name();
    }

    @Nonnull
    public static String defaultCharsetName(@Nullable CharSequence charset) {
        return StringUtils.isBlank(charset) ? Charset.defaultCharset().name() : charset.toString();
    }

    public static boolean isSupported(@Nullable CharSequence charset) {
        return StringUtils.isNotEmpty(charset) && Charset.isSupported(charset.toString());
    }

    public static boolean isNotSupported(@Nullable CharSequence charset) {
        return !isSupported(charset);
    }

    @Nullable
    public static Charset firstSupported(@Nullable CharSequence... charsets) {
        return firstSupported(ArrayUtilsWraps.asList(charsets));
    }

    @Nullable
    public static Charset firstSupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isEmpty(charsets) ? null : charsets.stream().filter(CharsetPlainWraps::isSupported).findFirst().map(CharsetPlainWraps::forName).orElse(null);
    }

    @Nullable
    public static Charset forName(@Nullable CharSequence charset) {
        if (StringUtils.isBlank(charset)) {
            return null;
        }
        try {
            return Charset.forName(charset.toString());
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Charset toCharset(@Nullable CharSequence charset) {
        return toCharset(charset, false);
    }

    @Nullable
    public static Charset toCharset(@Nullable CharSequence charset, boolean useDefault) {
        if (StringUtils.isBlank(charset)) {
            return useDefault ? Charset.defaultCharset() : null;
        }
        try {
            return Charsets.toCharset(charset.toString());
        } catch (Exception ignored) {
        }
        return useDefault ? Charset.defaultCharset() : null;
    }
}
