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


import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import jakarta.annotation.Nullable;
import org.apache.commons.collections4.EnumerationUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link org.apache.commons.collections4.EnumerationUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.collections4.EnumerationUtils
 * @see org.apache.commons.collections4.IteratorUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class EnumerationUtilsWraps {
    @Nullable
    public static Class<?> getComponentType(@Nullable Enumeration<?> enumeration) {
        return getComponentType(enumeration, false);
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Enumeration<?> enumeration, boolean deepScan) {
        return (enumeration == null) ? null : IteratorPlainWraps.getComponentType(new EnumerationIterator<>(enumeration), deepScan);
    }

    public static <E> boolean removeIf(@Nullable Enumeration<E> enumeration, @Nullable Predicate<? super E> filter) {
        return ObjectUtils.allNotNull(enumeration, filter) && IteratorPlainWraps.removeIf(new EnumerationIterator<>(enumeration), filter);
    }

    public static <E> boolean removeIfIndexing(@Nullable Enumeration<E> enumeration, @Nullable BiPredicate<Integer, ? super E> filter) {
        return ObjectUtils.allNotNull(enumeration, filter) && IteratorPlainWraps.removeIfIndexing(new EnumerationIterator<>(enumeration), filter);
    }

    public static int size(@Nullable Enumeration<?> enumeration) {
        return (enumeration == null) ? 0 : IteratorUtils.size(new EnumerationIterator<>(enumeration));
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Enumeration<? extends E> enumeration, @Nullable Class<E> elementType) {
        return ObjectUtils.anyNull(enumeration, elementType) ? null : IteratorUtils.toArray(new EnumerationIterator<>(enumeration), elementType);
    }

    @Nullable
    public static <E> List<E> toElementList(@Nullable Enumeration<? extends E> enumeration) {
        return (enumeration == null) ? null : EnumerationUtils.toList(enumeration);
    }

    @Nullable
    public static <E> Set<E> toElementSet(@Nullable Enumeration<? extends E> enumeration) {
        return (enumeration == null) ? null : IteratorPlainWraps.toElementSet(new EnumerationIterator<>(enumeration));
    }

    @Nullable
    public static Object[] toObjectArray(@Nullable Enumeration<?> enumeration) {
        return (enumeration == null) ? null : IteratorUtils.toArray(new EnumerationIterator<>(enumeration));
    }

    @Nullable
    public static List<?> toObjectList(@Nullable Enumeration<?> enumeration) {
        return (enumeration == null) ? null : IteratorUtils.toList(new EnumerationIterator<>(enumeration));
    }

    @Nullable
    public static List<String> toStringList(@Nullable StringTokenizer tokenizer) {
        return (tokenizer == null) ? null : EnumerationUtils.toList(tokenizer);
    }
}
