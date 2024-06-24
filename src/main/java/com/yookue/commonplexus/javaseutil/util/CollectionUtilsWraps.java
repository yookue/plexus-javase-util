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


import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;


/**
 * Utilities for {@link org.apache.commons.collections4.CollectionUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.collections4.CollectionUtils
 * @see org.apache.commons.collections4.IterableUtils
 * @see org.apache.commons.collections4.ListUtils
 * @see org.apache.commons.collections4.MapUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class CollectionUtilsWraps {
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean containsAll(@Nullable Collection<?> collection, @Nullable E... elements) {
        return CollectionUtils.isNotEmpty(collection) && ArrayUtils.isNotEmpty(elements) && CollectionUtils.containsAll(collection, ArrayUtilsWraps.asList(elements));
    }

    @Nullable
    @SafeVarargs
    public static <E> Collection<E> intersectionAll(@Nullable Iterable<E>... iterables) {
        return intersectionAll(ArrayUtilsWraps.asList(iterables));
    }

    /**
     * @see org.apache.commons.collections4.CollectionUtils#intersection
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> Collection<E> intersectionAll(@Nullable Collection<Iterable<E>> iterables) {
        if (CollectionUtils.isEmpty(iterables) || iterables.stream().anyMatch(IterableUtils::isEmpty)) {
            return null;
        }
        Iterable<E> first = IterableUtils.get(iterables, 0);
        if (CollectionUtils.size(iterables) == 1) {
            return IterableUtils.toList(first);
        }
        Collection<E> result = null;
        int index = 0;
        for (Iterable<E> iterable : iterables) {
            if (index > 0) {
                result = (index == 1) ? CollectionUtils.intersection(first, iterable) : CollectionUtils.intersection(result, iterable);
                if (CollectionUtils.isEmpty(result)) {
                    return null;
                }
            }
            index++;
        }
        return CollectionUtils.isEmpty(result) ? null : result;
    }

    @Nullable
    @SafeVarargs
    public static <E> Collection<E> unionAll(@Nullable Iterable<E>... iterables) {
        return unionAll(ArrayUtilsWraps.asList(iterables));
    }

    /**
     * @see org.apache.commons.collections4.CollectionUtils#union
     */
    @Nullable
    public static <E> Collection<E> unionAll(@Nullable Collection<Iterable<E>> iterables) {
        if (CollectionUtils.isEmpty(iterables)) {
            return null;
        }
        Collection<E> result = new ArrayList<>();
        for (Iterable<E> iterable : iterables) {
            if (IterableUtils.isEmpty(iterable)) {
                continue;
            }
            result = CollectionUtils.union(result, iterable);
        }
        return CollectionUtils.isEmpty(result) ? null : result;
    }
}
