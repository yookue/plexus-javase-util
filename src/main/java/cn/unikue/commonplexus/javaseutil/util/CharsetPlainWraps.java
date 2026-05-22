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
 *
 * @see java.nio.charset.Charset
 * @see org.apache.commons.io.Charsets
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class CharsetPlainWraps {
    /**
     * Check if all charset names are supported.
     *
     * @param charsets the charset names to check
     * @return true if all charsets are supported, false otherwise
     */
    public static boolean allSupported(@Nullable CharSequence... charsets) {
        return allSupported(ArrayUtilsWraps.asList(charsets));
    }

    /**
     * Check if all charset names in collection are supported.
     *
     * @param charsets the collection of charset names to check
     * @return true if all charsets are supported, false otherwise
     */
    public static boolean allSupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isNotEmpty(charsets) && charsets.stream().allMatch(CharsetPlainWraps::isSupported);
    }

    /**
     * Check if any charset name is supported.
     *
     * @param charsets the charset names to check
     * @return true if any charset is supported, false otherwise
     */
    public static boolean anySupported(@Nullable CharSequence... charsets) {
        return anySupported(ArrayUtilsWraps.asList(charsets));
    }

    /**
     * Check if any charset name in collection is supported.
     *
     * @param charsets the collection of charset names to check
     * @return true if any charset is supported, false otherwise
     */
    public static boolean anySupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isNotEmpty(charsets) && charsets.stream().anyMatch(CharsetPlainWraps::isSupported);
    }

    /**
     * Check if all charset names are not supported.
     *
     * @param charsets the charset names to check
     * @return true if all charsets are not supported, false otherwise
     */
    public static boolean allNotSupported(@Nullable CharSequence... charsets) {
        return allNotSupported(ArrayUtilsWraps.asList(charsets));
    }

    /**
     * Check if all charset names in collection are not supported.
     *
     * @param charsets the collection of charset names to check
     * @return true if all charsets are not supported, false otherwise
     */
    public static boolean allNotSupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isEmpty(charsets) || charsets.stream().allMatch(CharsetPlainWraps::isNotSupported);
    }

    /**
     * Check if any charset name is not supported.
     *
     * @param charsets the charset names to check
     * @return true if any charset is not supported, false otherwise
     */
    public static boolean anyNotSupported(@Nullable CharSequence... charsets) {
        return anyNotSupported(ArrayUtilsWraps.asList(charsets));
    }

    /**
     * Check if any charset name in collection is not supported.
     *
     * @param charsets the collection of charset names to check
     * @return true if any charset is not supported, false otherwise
     */
    public static boolean anyNotSupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isEmpty(charsets) || charsets.stream().anyMatch(CharsetPlainWraps::isNotSupported);
    }

    /**
     * Get the default charset, using provided charset if not null.
     *
     * @param charset the charset to use, or null to use default
     * @return the provided charset or system default charset
     */
    @Nonnull
    public static Charset defaultCharset(@Nullable Charset charset) {
        return (charset != null) ? charset : Charset.defaultCharset();
    }

    /**
     * Get the default charset name, using provided charset name if not null.
     *
     * @param charset the charset to use, or null to use default
     * @return the charset name or system default charset name
     */
    @Nonnull
    public static String defaultCharsetName(@Nullable Charset charset) {
        return (charset != null) ? charset.name() : Charset.defaultCharset().name();
    }

    /**
     * Get the default charset name, using provided CharSequence if not blank.
     *
     * @param charset the charset name as CharSequence, or blank to use default
     * @return the charset name or system default charset name
     */
    @Nonnull
    public static String defaultCharsetName(@Nullable CharSequence charset) {
        return StringUtils.isBlank(charset) ? Charset.defaultCharset().name() : charset.toString();
    }

    /**
     * Check if the charset name is supported by the JVM.
     *
     * @param charset the charset name to check
     * @return true if the charset is supported, false otherwise
     */
    public static boolean isSupported(@Nullable CharSequence charset) {
        return StringUtils.isNotEmpty(charset) && Charset.isSupported(charset.toString());
    }

    /**
     * Check if the charset name is not supported by the JVM.
     *
     * @param charset the charset name to check
     * @return true if the charset is not supported, false otherwise
     */
    public static boolean isNotSupported(@Nullable CharSequence charset) {
        return !isSupported(charset);
    }

    /**
     * Get the first supported charset from the array.
     *
     * @param charsets the charset names to check
     * @return the first supported Charset, or null if none found
     */
    @Nullable
    public static Charset firstSupported(@Nullable CharSequence... charsets) {
        return firstSupported(ArrayUtilsWraps.asList(charsets));
    }

    /**
     * Get the first supported charset from the collection.
     *
     * @param charsets the collection of charset names to check
     * @return the first supported Charset, or null if none found
     */
    @Nullable
    public static Charset firstSupported(@Nullable Collection<? extends CharSequence> charsets) {
        return CollectionPlainWraps.isEmpty(charsets) ? null : charsets.stream().filter(CharsetPlainWraps::isSupported).findFirst().map(CharsetPlainWraps::forName).orElse(null);
    }

    /**
     * Create a Charset object from the charset name.
     *
     * @param charset the charset name
     * @return the Charset object, or null if invalid or unsupported
     */
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

    /**
     * Convert CharSequence to Charset object.
     *
     * @param charset the charset name as CharSequence
     * @return the Charset object, or null if invalid
     */
    @Nullable
    public static Charset toCharset(@Nullable CharSequence charset) {
        return toCharset(charset, false);
    }

    /**
     * Convert CharSequence to Charset object with fallback to default.
     *
     * @param charset the charset name as CharSequence
     * @param useDefault whether to use default charset if conversion fails
     * @return the Charset object, or default charset if useDefault is true
     */
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
