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


import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;


/**
 * Utilities for transforming {@link java.util.UUID}
 *
 * @author David Hsing
 * @see "org.apache.logging.log4j.core.util.UuidUtil"
 * @see "org.springframework.core.convert.support.StringToUUIDConverter"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class UuidUtilsWraps {
    @Nullable
    public static UUID fromString(@Nullable String uuid) {
        if (StringUtils.isBlank(uuid)) {
            return null;
        }
        try {
            return UUID.fromString(uuid);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toHyphenUuid(@Nullable String uuid) {
        if (StringUtils.length(uuid) != 32) {
            return uuid;
        }
        return String.format("%s-%s-%s-%s-%s", uuid.substring(0, 8), uuid.substring(8, 12), uuid.substring(12, 16), uuid.substring(16, 20), uuid.substring(20));    // $NON-NLS-1$
    }

    /**
     * Convert plain uuids to hyphen uuids
     *
     * @param uuids the source plain uuids
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

    @Nullable
    public static String toPlainUuid(@Nullable String uuid) {
        return (StringUtils.length(uuid) == 36) ? StringUtils.remove(uuid, CharVariantConst.HYPHEN) : uuid;
    }

    /**
     * Convert hyphen uuids to plain uuids
     *
     * @param uuids the source hyphen uuids
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
