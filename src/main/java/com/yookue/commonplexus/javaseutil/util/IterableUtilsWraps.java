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


import java.util.List;
import javax.annotation.Nullable;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link org.apache.commons.collections4.IterableUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.collections4.IterableUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class IterableUtilsWraps {
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Iterable<? extends E> iterable, @Nullable Class<E> elementType) {
        return ObjectUtils.anyNull(iterable, elementType) ? null : IteratorUtils.toArray(iterable.iterator(), elementType);
    }

    @Nullable
    public static <E> List<E> toElementList(@Nullable Iterable<? extends E> iterable) {
        return (iterable == null) ? null : IteratorUtils.toList(iterable.iterator());
    }

    @Nullable
    public static Object[] toObjectArray(@Nullable Iterable<?> iterable) {
        return (iterable == null) ? null : IteratorUtils.toArray(iterable.iterator());
    }

    @Nullable
    public static List<?> toObjectList(@Nullable Iterable<?> iterable) {
        return (iterable == null) ? null : IteratorUtils.toList(iterable.iterator());
    }
}
