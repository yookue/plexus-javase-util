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


import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.constant.CharVariantConst;
import cn.unikue.commonplexus.javaseutil.constant.RegexVariantConst;


/**
 * Utilities for transforming {@link java.util.UUID}
 *
 * @author David Hsing
 *
 * @see "org.apache.logging.log4j.core.util.UuidUtil"
 * @see "org.springframework.core.convert.support.StringToUUIDConverter"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class UuidUtilsWraps {
    /**
     * Check if the string is a valid hyphenated UUID format (8-4-4-4-12).
     *
     * @param uuid the string to check
     * @return true if the string is a valid hyphenated UUID, false otherwise
     */
    public static boolean isHyphenUuid(@Nullable String uuid) {
        return uuid != null && uuid.length() == 36 && uuid.matches(RegexVariantConst.HYPHEN_UUID);
    }

    /**
     * Check if the string is a valid plain UUID format (32 hex characters without hyphens).
     *
     * @param uuid the string to check
     * @return true if the string is a valid plain UUID, false otherwise
     */
    public static boolean isPlainUuid(@Nullable String uuid) {
        return uuid != null && uuid.length() == 32 && uuid.matches(RegexVariantConst.PLAIN_UUID);
    }

    /**
     * Convert string to UUID object.
     *
     * @param uuid the string to convert
     * @return the UUID object, or null if string is blank or invalid
     */
    @Nullable
    public static UUID ofString(@Nullable String uuid) {
        if (StringUtils.isBlank(uuid)) {
            return null;
        }
        try {
            return UUID.fromString(uuid);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Convert plain UUID string to hyphenated format.
     *
     * @param uuid the plain UUID string (32 characters) or already hyphenated UUID
     * @return the hyphenated UUID string, or original if not plain UUID
     */
    public static String toHyphenUuid(@Nullable String uuid) {
        return !isPlainUuid(uuid) ? uuid : String.format("%s-%s-%s-%s-%s", uuid.substring(0, 8), uuid.substring(8, 12), uuid.substring(12, 16), uuid.substring(16, 20), uuid.substring(20));    // $NON-NLS-1$
    }

    /**
     * Convert plain UUIDs to hyphenated format in-place.
     *
     * @param uuids the collection of plain UUID strings to convert
     * @throws java.lang.UnsupportedOperationException if the <tt>clear</tt> operation is not supported by the source collection
     */
    public static void toHyphenUuid(@Nullable Collection<String> uuids) {
        if (CollectionPlainWraps.isEmpty(uuids)) {
            return;
        }
        String[] array = uuids.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        uuids.clear();
        Arrays.stream(array).map(UuidUtilsWraps::toHyphenUuid).forEach(uuids::add);
    }

    /**
     * Convert hyphenated UUID string to plain format (remove hyphens).
     *
     * @param uuid the hyphenated UUID string (36 characters) or already plain UUID
     * @return the plain UUID string, or original if not hyphenated UUID
     */
    @Nullable
    public static String toPlainUuid(@Nullable String uuid) {
        return !isHyphenUuid(uuid) ? uuid : StringUtils.remove(uuid, CharVariantConst.HYPHEN);
    }

    /**
     * Convert hyphenated UUIDs to plain format in-place.
     *
     * @param uuids the collection of hyphenated UUID strings to convert
     * @throws java.lang.UnsupportedOperationException if the <tt>clear</tt> operation is not supported by the source collection
     */
    public static void toPlainUuid(@Nullable Collection<String> uuids) {
        if (CollectionPlainWraps.isEmpty(uuids)) {
            return;
        }
        String[] array = uuids.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        uuids.clear();
        Arrays.stream(array).map(UuidUtilsWraps::toPlainUuid).forEach(uuids::add);
    }
}
