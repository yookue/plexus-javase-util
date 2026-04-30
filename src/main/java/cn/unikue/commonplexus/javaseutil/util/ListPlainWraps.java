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


import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.util.List}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ListPlainWraps {
    @SafeVarargs
    public static <E> void addAll(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, null, items);
    }

    public static <E> void addAll(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, null, items);
    }

    public static <E> void addAll(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, null, items);
    }

    public static <E> void addAll(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, null, items);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void addAllIf(@Nullable List<? super E> target, int index, @Nullable BiPredicate<List<? super E>, E> filter, @Nullable E... items) {
        if (target == null || ArrayUtils.isEmpty(items)) {
            return;
        }
        int offset = index;
        for (E item : items) {
            if (filter == null || filter.test(target, item)) {
                target.add(offset++, item);
            }
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void addAllIf(@Nullable List<E> target, int index, @Nullable BiPredicate<List<? super E>, E> filter, @Nullable Iterable<? extends E> items) {
        if (ObjectUtils.anyNull(target, items) || !CollectionPlainWraps.isIndexBound(target, index, true)) {
            return;
        }
        int offset = index;
        for (E item : items) {
            if (filter == null || filter.test(target, item)) {
                target.add(offset++, item);
            }
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void addAllIf(@Nullable List<E> target, int index, @Nullable BiPredicate<List<? super E>, E> filter, @Nullable Iterator<? extends E> items) {
        if (ObjectUtils.anyNull(target, items) || !CollectionPlainWraps.isIndexBound(target, index, true)) {
            return;
        }
        int offset = index;
        while (items.hasNext()) {
            E item = items.next();
            if (filter == null || filter.test(target, item)) {
                target.add(offset++, item);
            }
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void addAllIf(@Nullable List<E> target, int index, @Nullable BiPredicate<List<? super E>, E> filter, @Nullable Enumeration<? extends E> items) {
        if (ObjectUtils.anyNull(target, items) || !CollectionPlainWraps.isIndexBound(target, index, true)) {
            return;
        }
        int offset = index;
        while (items.hasMoreElements()) {
            E item = items.nextElement();
            if (filter == null || filter.test(target, item)) {
                target.add(offset++, item);
            }
        }
    }

    @SafeVarargs
    public static <E> void addAllIfNotNull(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, (list, item) -> item != null, items);
    }

    public static <E> void addAllIfNotNull(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, (list, item) -> item != null, items);
    }

    public static <E> void addAllIfNotNull(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, (list, item) -> item != null, items);
    }

    public static <E> void addAllIfNotNull(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, (list, item) -> item != null, items);
    }

    @SafeVarargs
    public static <E> void addAllIfNotContains(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, (list, item) -> !CollectionPlainWraps.contains(list, item), items);
    }

    public static <E> void addAllIfNotContains(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, (list, item) -> !CollectionPlainWraps.contains(list, item), items);
    }

    public static <E> void addAllIfNotContains(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, (list, item) -> !CollectionPlainWraps.contains(list, item), items);
    }

    public static <E> void addAllIfNotContains(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, (list, item) -> !CollectionPlainWraps.contains(list, item), items);
    }

    @SafeVarargs
    public static <E extends CharSequence> void addAllIfNotBlank(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, (list, item) -> StringUtils.isNotBlank(item), items);
    }

    public static <E extends CharSequence> void addAllIfNotBlank(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, (list, item) -> StringUtils.isNotBlank(item), items);
    }

    public static <E extends CharSequence> void addAllIfNotBlank(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, (list, item) -> StringUtils.isNotBlank(item), items);
    }

    public static <E extends CharSequence> void addAllIfNotBlank(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, (list, item) -> StringUtils.isNotBlank(item), items);
    }

    @SafeVarargs
    public static <E> void addAllIfNotEmpty(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, (list, item) -> ObjectUtils.isNotEmpty(item), items);
    }

    public static <E> void addAllIfNotEmpty(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, (list, item) -> ObjectUtils.isNotEmpty(item), items);
    }

    public static <E> void addAllIfNotEmpty(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, (list, item) -> ObjectUtils.isNotEmpty(item), items);
    }

    public static <E> void addAllIfNotEmpty(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, (list, item) -> ObjectUtils.isNotEmpty(item), items);
    }

    public static <E> E get(@Nullable List<E> list, int index) {
        return get(list, index, null);
    }

    public static <E> E get(@Nullable List<E> list, int index, E defaultValue) {
        return (list == null || index < 0 || index > list.size() - 1) ? defaultValue : ObjectUtils.getIfNull(list.get(index), defaultValue);
    }

    public static <E> E getFirst(@Nullable List<E> list) {
        return getFirst(list, null);
    }

    public static <E> E getFirst(@Nullable List<E> list, E defaultValue) {
        return get(list, 0, defaultValue);
    }

    public static <E> E getLast(@Nullable List<E> list) {
        return getLast(list, null);
    }

    public static <E> E getLast(@Nullable List<E> list, E defaultValue) {
        return get(list, CollectionPlainWraps.size(list) - 1, defaultValue);
    }

    public static <E> ListIterator<E> listIterator(@Nullable List<E> list) {
        return listIterator(list, 0);
    }

    public static <E> ListIterator<E> listIterator(@Nullable List<E> list, int index) {
        return (list == null || index < 0 || index > list.size()) ? null : list.listIterator(index);
    }

    public static <E> ListIterator<E> listIteratorTailing(@Nullable List<E> list) {
        return listIterator(list, CollectionPlainWraps.size(list));    // Attention: NOT size - 1
    }

    public static boolean isEmpty(@Nullable List<?> list) {
        return (list == null || list.isEmpty());
    }

    public static boolean isNotEmpty(@Nullable List<?> list) {
        return !isEmpty(list);
    }

    /**
     * Reverse the order of the given list
     *
     * @param list The target list to reverse
     *
     * @see org.apache.commons.lang3.ArrayUtils#reverse(Object[])
     */
    public static <E, T extends List<E>> void reverse(@Nullable T list) {
        reverse(list, 0, CollectionPlainWraps.size(list));
    }

    /**
     * Reverse the order of the given list in the given range
     *
     * @param list The target list to reverse
     * @param startIndexInclusive The starting index
     * @param endIndexExclusive The ending index, exclusive
     *
     * @see org.apache.commons.lang3.ArrayUtils#reverse(Object[], int, int)
     */
    public static <E, T extends List<E>> void reverse(@Nullable T list, int startIndexInclusive, int endIndexExclusive) {
        if (CollectionPlainWraps.isEmpty(list) || startIndexInclusive >= endIndexExclusive) {
            return;
        }
        int start = Math.max(startIndexInclusive, 0);
        int end = Math.min(CollectionPlainWraps.size(list), endIndexExclusive) - 1;
        while (end > start) {
            E item = list.get(end);
            list.set(end, list.get(start));
            list.set(start, item);
            end--;
            start++;
        }
    }

    public static <E, T extends List<E>> void reverseForEach(@Nullable T list, @Nullable Consumer<? super E> action) {
        reverseForEach(list, action, null);
    }

    public static <E, T extends List<E>> void reverseForEach(@Nullable T list, @Nullable Consumer<? super E> action, @Nullable Predicate<? super E> filter) {
        if (CollectionPlainWraps.isEmpty(list) || action == null) {
            return;
        }
        int size = list.size();
        for (int i = size - 1; i >= 0; i--) {
            E item = list.get(i);
            if (filter == null || filter.test(item)) {
                action.accept(item);
            }
        }
    }

    public static <E, T extends List<E>> void reverseForEachBreakable(@Nullable T list, @Nullable Function<? super E, Boolean> action) {
        reverseForEachBreakable(list, action, null);
    }

    public static <E, T extends List<E>> void reverseForEachBreakable(@Nullable T list, @Nullable Function<? super E, Boolean> action, @Nullable Predicate<? super E> filter) {
        if (CollectionPlainWraps.isEmpty(list) || action == null) {
            return;
        }
        int size = list.size();
        for (int i = size - 1; i >= 0; i--) {
            E item = list.get(i);
            if ((filter == null || filter.test(item)) && BooleanUtils.isNotTrue(action.apply(item))) {
                break;
            }
        }
    }

    @Nullable
    public static <E, T extends List<E>> E reverseForEachHeading(@Nullable T list, @Nullable Consumer<? super E> action) {
        if (CollectionPlainWraps.isEmpty(list) || action == null) {
            return null;
        }
        int size = list.size();
        for (int i = size - 1; i > 0; i--) {
            action.accept(list.get(i));
        }
        return list.get(0);
    }

    public static <E, T extends List<E>> void reverseForEachIndexing(@Nullable T list, @Nullable BiConsumer<Integer, ? super E> action) {
        reverseForEachIndexing(list, action, null);
    }

    public static <E, T extends List<E>> void reverseForEachIndexing(@Nullable T list, @Nullable BiConsumer<Integer, ? super E> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (CollectionPlainWraps.isEmpty(list) || action == null) {
            return;
        }
        int size = list.size();
        for (int i = size - 1; i >= 0; i--) {
            E item = list.get(i);
            if (filter == null || filter.test(i, item)) {
                action.accept(i, item);
            }
        }
    }

    public static <E, T extends List<E>> void reverseForEachIndexingBreakable(@Nullable T list, @Nullable BiFunction<Integer, ? super E, Boolean> action) {
        reverseForEachIndexingBreakable(list, action, null);
    }

    public static <E, T extends List<E>> void reverseForEachIndexingBreakable(@Nullable T list, @Nullable BiFunction<Integer, ? super E, Boolean> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (CollectionPlainWraps.isEmpty(list) || action == null) {
            return;
        }
        int size = list.size();
        for (int i = size - 1; i >= 0; i--) {
            E item = list.get(i);
            if ((filter == null || filter.test(i, item)) && BooleanUtils.isNotTrue(action.apply(i, item))) {
                break;
            }
        }
    }

    @Nullable
    public static <E, T extends List<E>> E reverseForEachIndexingHeading(@Nullable T list, @Nullable BiConsumer<Integer, ? super E> action) {
        if (CollectionPlainWraps.isEmpty(list) || action == null) {
            return null;
        }
        int size = list.size();
        for (int i = size - 1; i > 0; i--) {
            action.accept(i, list.get(i));
        }
        return list.get(0);
    }
}
