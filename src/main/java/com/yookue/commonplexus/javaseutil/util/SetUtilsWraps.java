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


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.SetUtils;


/**
 * Utilities for {@link org.apache.commons.collections4.SetUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.collections4.SetUtils
 * @see org.apache.commons.collections4.CollectionUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class SetUtilsWraps {
    @Nullable
    @SafeVarargs
    public static <E> Set<E> intersectionAll(@Nullable Set<E>... sets) {
        return intersectionAll(ArrayUtilsWraps.asList(sets));
    }

    @Nullable
    public static <E> Set<E> intersectionAll(@Nullable Collection<Set<E>> sets) {
        if (CollectionUtils.isEmpty(sets) || sets.stream().anyMatch(CollectionUtils::isEmpty)) {
            return null;
        }
        Set<E> first = IterableUtils.get(sets, 0);
        if (CollectionUtils.size(sets) == 1) {
            return first;
        }
        Set<E> result = first;
        int index = 0;
        for (Set<E> set : sets) {
            if (index > 0) {
                result = SetUtils.intersection(result, set);
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
    public static <E> Set<E> unionAll(@Nullable Set<E>... sets) {
        return unionAll(ArrayUtilsWraps.asList(sets));
    }

    /**
     * @see org.apache.commons.collections4.SetUtils#union
     */
    @Nullable
    public static <E> Set<E> unionAll(@Nullable Collection<Set<E>> sets) {
        if (CollectionUtils.isEmpty(sets)) {
            return null;
        }
        Set<E> result = new HashSet<>();
        for (Set<E> set : sets) {
            if (CollectionUtils.isEmpty(set)) {
                continue;
            }
            result = SetUtils.union(result, set).toSet();
        }
        return CollectionUtils.isEmpty(result) ? null : result;
    }
}
