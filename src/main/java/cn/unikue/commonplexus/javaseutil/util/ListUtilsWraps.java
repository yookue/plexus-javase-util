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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jakarta.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.ListUtils;


/**
 * Utilities for {@link org.apache.commons.collections4.ListUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.collections4.ListUtils
 * @see org.apache.commons.collections4.CollectionUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ListUtilsWraps {
    /**
     * Computes the intersection of all given lists
     *
     * @param lists the lists to compute intersection for
     *
     * @return a list containing elements present in all input lists, or {@code null} if any list is empty or null
     */
    @Nullable
    @SafeVarargs
    public static <E> List<E> intersectionAll(@Nullable List<E>... lists) {
        return intersectionAll(ArrayUtilsWraps.asList(lists));
    }

    /**
     * Computes the intersection of all lists in the collection
     *
     * @param lists the collection of lists to compute intersection for
     *
     * @return a list containing elements present in all input lists, or {@code null} if the collection is empty, any list is empty, or the result is empty
     */
    @Nullable
    public static <E> List<E> intersectionAll(@Nullable Collection<List<E>> lists) {
        if (CollectionUtils.isEmpty(lists) || lists.stream().anyMatch(CollectionUtils::isEmpty)) {
            return null;
        }
        List<E> first = IterableUtils.get(lists, 0);
        if (CollectionUtils.size(lists) == 1) {
            return first;
        }
        List<E> result = first;
        int index = 0;
        for (List<E> list : lists) {
            if (index > 0) {
                result = ListUtils.intersection(result, list);
                if (CollectionUtils.isEmpty(result)) {
                    return null;
                }
            }
            index++;
        }
        return CollectionUtils.isEmpty(result) ? null : result;
    }

    /**
     * Computes the union of all given collections
     *
     * @param collections the collections to compute union for
     *
     * @return a list containing all elements from all input collections, or {@code null} if the input is null or empty
     */
    @Nullable
    @SafeVarargs
    public static <E> List<E> unionAll(@Nullable Collection<? extends E>... collections) {
        return unionAll(ArrayUtilsWraps.asList(collections));
    }

    /**
     * Computes the union of all collections in the collection
     *
     * @param collections the collection of collections to compute union for
     *
     * @return a list containing all elements from all input collections, or {@code null} if the collection is empty or the total size is zero
     *
     * @see org.apache.commons.collections4.ListUtils#union
     */
    @Nullable
    public static <E> List<E> unionAll(@Nullable Collection<Collection<? extends E>> collections) {
        if (CollectionUtils.isEmpty(collections)) {
            return null;
        }
        int total = collections.stream().mapToInt(CollectionUtils::size).sum();
        if (total <= 0) {
            return null;
        }
        List<E> result = new ArrayList<>(total);
        collections.stream().filter(CollectionUtils::isNotEmpty).forEach(result::addAll);
        return result;
    }
}
