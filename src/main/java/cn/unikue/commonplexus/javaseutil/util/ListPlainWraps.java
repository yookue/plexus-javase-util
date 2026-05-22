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
    /**
     * Adds all items to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the items to add
     */
    @SafeVarargs
    public static <E> void addAll(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, null, items);
    }

    /**
     * Adds all items from the iterable to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterable containing items to add
     */
    public static <E> void addAll(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, null, items);
    }

    /**
     * Adds all items from the iterator to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterator containing items to add
     */
    public static <E> void addAll(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, null, items);
    }

    /**
     * Adds all items from the enumeration to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the enumeration containing items to add
     */
    public static <E> void addAll(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, null, items);
    }

    /**
     * Adds all items to the target list at the specified index if they pass the filter
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param filter the predicate to test each item before adding; can be null to add all items
     * @param items the items to potentially add
     */
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

    /**
     * Adds all items from the iterable to the target list at the specified index if they pass the filter
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param filter the predicate to test each item before adding; can be null to add all items
     * @param items the iterable containing items to potentially add
     */
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

    /**
     * Adds all items from the iterator to the target list at the specified index if they pass the filter
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param filter the predicate to test each item before adding; can be null to add all items
     * @param items the iterator containing items to potentially add
     */
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

    /**
     * Adds all items from the enumeration to the target list at the specified index if they pass the filter
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param filter the predicate to test each item before adding; can be null to add all items
     * @param items the enumeration containing items to potentially add
     */
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

    /**
     * Adds all non-null items to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the items to potentially add
     */
    @SafeVarargs
    public static <E> void addAllIfNotNull(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, (list, item) -> item != null, items);
    }

    /**
     * Adds all non-null items from the iterable to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterable containing items to potentially add
     */
    public static <E> void addAllIfNotNull(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, (list, item) -> item != null, items);
    }

    /**
     * Adds all non-null items from the iterator to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterator containing items to potentially add
     */
    public static <E> void addAllIfNotNull(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, (list, item) -> item != null, items);
    }

    /**
     * Adds all non-null items from the enumeration to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the enumeration containing items to potentially add
     */
    public static <E> void addAllIfNotNull(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, (list, item) -> item != null, items);
    }

    /**
     * Adds all items that are not already contained in the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the items to potentially add
     */
    @SafeVarargs
    public static <E> void addAllIfNotContains(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, (list, item) -> !CollectionPlainWraps.contains(list, item), items);
    }

    /**
     * Adds all items from the iterable that are not already contained in the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterable containing items to potentially add
     */
    public static <E> void addAllIfNotContains(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, (list, item) -> !CollectionPlainWraps.contains(list, item), items);
    }

    /**
     * Adds all items from the iterator that are not already contained in the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterator containing items to potentially add
     */
    public static <E> void addAllIfNotContains(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, (list, item) -> !CollectionPlainWraps.contains(list, item), items);
    }

    /**
     * Adds all items from the enumeration that are not already contained in the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the enumeration containing items to potentially add
     */
    public static <E> void addAllIfNotContains(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, (list, item) -> !CollectionPlainWraps.contains(list, item), items);
    }

    /**
     * Adds all non-blank CharSequence items to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the CharSequence items to potentially add
     */
    @SafeVarargs
    public static <E extends CharSequence> void addAllIfNotBlank(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, (list, item) -> StringUtils.isNotBlank(item), items);
    }

    /**
     * Adds all non-blank CharSequence items from the iterable to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterable containing CharSequence items to potentially add
     */
    public static <E extends CharSequence> void addAllIfNotBlank(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, (list, item) -> StringUtils.isNotBlank(item), items);
    }

    /**
     * Adds all non-blank CharSequence items from the iterator to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterator containing CharSequence items to potentially add
     */
    public static <E extends CharSequence> void addAllIfNotBlank(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, (list, item) -> StringUtils.isNotBlank(item), items);
    }

    /**
     * Adds all non-blank CharSequence items from the enumeration to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the enumeration containing CharSequence items to potentially add
     */
    public static <E extends CharSequence> void addAllIfNotBlank(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, (list, item) -> StringUtils.isNotBlank(item), items);
    }

    /**
     * Adds all non-empty items to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the items to potentially add
     */
    @SafeVarargs
    public static <E> void addAllIfNotEmpty(@Nullable List<? super E> target, int index, @Nullable E... items) {
        addAllIf(target, index, (list, item) -> ObjectUtils.isNotEmpty(item), items);
    }

    /**
     * Adds all non-empty items from the iterable to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterable containing items to potentially add
     */
    public static <E> void addAllIfNotEmpty(@Nullable List<E> target, int index, @Nullable Iterable<? extends E> items) {
        addAllIf(target, index, (list, item) -> ObjectUtils.isNotEmpty(item), items);
    }

    /**
     * Adds all non-empty items from the iterator to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the iterator containing items to potentially add
     */
    public static <E> void addAllIfNotEmpty(@Nullable List<E> target, int index, @Nullable Iterator<? extends E> items) {
        addAllIf(target, index, (list, item) -> ObjectUtils.isNotEmpty(item), items);
    }

    /**
     * Adds all non-empty items from the enumeration to the target list at the specified index
     *
     * @param target the target list to add items to
     * @param index the index at which to insert the first element
     * @param items the enumeration containing items to potentially add
     */
    public static <E> void addAllIfNotEmpty(@Nullable List<E> target, int index, @Nullable Enumeration<? extends E> items) {
        addAllIf(target, index, (list, item) -> ObjectUtils.isNotEmpty(item), items);
    }

    /**
     * Gets the element at the specified index in the list
     *
     * @param list the list to get from
     * @param index the index of the element to get
     *
     * @return the element at the specified index, or {@code null} if the list is null or index is out of bounds
     */
    public static <E> E get(@Nullable List<E> list, int index) {
        return get(list, index, null);
    }

    /**
     * Gets the element at the specified index in the list, or returns the default value if not found
     *
     * @param list the list to get from
     * @param index the index of the element to get
     * @param defaultValue the default value to return if the element is null or not found
     *
     * @return the element at the specified index, or the default value if the list is null, index is out of bounds, or element is null
     */
    public static <E> E get(@Nullable List<E> list, int index, E defaultValue) {
        return (list == null || index < 0 || index > list.size() - 1) ? defaultValue : ObjectUtils.defaultIfNull(list.get(index), defaultValue);
    }

    /**
     * Gets the first element in the list
     *
     * @param list the list to get from
     *
     * @return the first element, or {@code null} if the list is null or empty
     */
    public static <E> E getFirst(@Nullable List<E> list) {
        return getFirst(list, null);
    }

    /**
     * Gets the first element in the list, or returns the default value if not found
     *
     * @param list the list to get from
     * @param defaultValue the default value to return if the element is null or not found
     *
     * @return the first element, or the default value if the list is null, empty, or element is null
     */
    public static <E> E getFirst(@Nullable List<E> list, E defaultValue) {
        return get(list, 0, defaultValue);
    }

    /**
     * Gets the last element in the list
     *
     * @param list the list to get from
     *
     * @return the last element, or {@code null} if the list is null or empty
     */
    public static <E> E getLast(@Nullable List<E> list) {
        return getLast(list, null);
    }

    /**
     * Gets the last element in the list, or returns the default value if not found
     *
     * @param list the list to get from
     * @param defaultValue the default value to return if the element is null or not found
     *
     * @return the last element, or the default value if the list is null, empty, or element is null
     */
    public static <E> E getLast(@Nullable List<E> list, E defaultValue) {
        return get(list, CollectionPlainWraps.size(list) - 1, defaultValue);
    }

    /**
     * Creates a list iterator over the elements in the list
     *
     * @param list the list to create iterator from
     *
     * @return a list iterator, or {@code null} if the list is null
     */
    public static <E> ListIterator<E> listIterator(@Nullable List<E> list) {
        return listIterator(list, 0);
    }

    /**
     * Creates a list iterator over the elements in the list starting at the specified index
     *
     * @param list the list to create iterator from
     * @param index the index of the first element to be returned by the iterator
     *
     * @return a list iterator, or {@code null} if the list is null or index is out of bounds
     */
    public static <E> ListIterator<E> listIterator(@Nullable List<E> list, int index) {
        return (list == null || index < 0 || index > list.size()) ? null : list.listIterator(index);
    }

    /**
     * Creates a list iterator positioned at the end of the list
     *
     * @param list the list to create iterator from
     *
     * @return a list iterator positioned at the end, or {@code null} if the list is null
     */
    public static <E> ListIterator<E> listIteratorTailing(@Nullable List<E> list) {
        return listIterator(list, CollectionPlainWraps.size(list));    // Attention: NOT size - 1
    }

    /**
     * Checks if the list is null or empty
     *
     * @param list the list to check
     *
     * @return {@code true} if the list is null or empty, {@code false} otherwise
     */
    public static boolean isEmpty(@Nullable List<?> list) {
        return (list == null || list.isEmpty());
    }

    /**
     * Checks if the list is not null and not empty
     *
     * @param list the list to check
     *
     * @return {@code true} if the list is not null and not empty, {@code false} otherwise
     */
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

    /**
     * Iterates over the list in reverse order and performs the given action for each element
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element
     */
    public static <E, T extends List<E>> void reverseForEach(@Nullable T list, @Nullable Consumer<? super E> action) {
        reverseForEach(list, action, null);
    }

    /**
     * Iterates over the list in reverse order and performs the given action for each element that passes the filter
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element
     * @param filter the predicate to test each element before performing the action; can be null to process all elements
     */
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

    /**
     * Iterates over the list in reverse order and performs the given action for each element until it returns false
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element; returns {@code true} to continue, {@code false} to break
     */
    public static <E, T extends List<E>> void reverseForEachBreakable(@Nullable T list, @Nullable Function<? super E, Boolean> action) {
        reverseForEachBreakable(list, action, null);
    }

    /**
     * Iterates over the list in reverse order and performs the given action for each element that passes the filter until it returns false
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element; returns {@code true} to continue, {@code false} to break
     * @param filter the predicate to test each element before performing the action; can be null to process all elements
     */
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

    /**
     * Iterates over the list in reverse order (excluding the first element) and performs the given action, then returns the first element
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element except the first
     *
     * @return the first element of the list, or {@code null} if the list is empty
     */
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

    /**
     * Iterates over the list in reverse order with index and performs the given action for each element
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element with its index
     */
    public static <E, T extends List<E>> void reverseForEachIndexing(@Nullable T list, @Nullable BiConsumer<Integer, ? super E> action) {
        reverseForEachIndexing(list, action, null);
    }

    /**
     * Iterates over the list in reverse order with index and performs the given action for each element that passes the filter
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element with its index
     * @param filter the predicate to test each element before performing the action; can be null to process all elements
     */
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

    /**
     * Iterates over the list in reverse order with index and performs the given action for each element until it returns false
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element with its index; returns {@code true} to continue, {@code false} to break
     */
    public static <E, T extends List<E>> void reverseForEachIndexingBreakable(@Nullable T list, @Nullable BiFunction<Integer, ? super E, Boolean> action) {
        reverseForEachIndexingBreakable(list, action, null);
    }

    /**
     * Iterates over the list in reverse order with index and performs the given action for each element that passes the filter until it returns false
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element with its index; returns {@code true} to continue, {@code false} to break
     * @param filter the predicate to test each element before performing the action; can be null to process all elements
     */
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

    /**
     * Iterates over the list in reverse order with index (excluding the first element) and performs the given action, then returns the first element
     *
     * @param list the list to iterate over
     * @param action the action to perform on each element except the first, with its index
     *
     * @return the first element of the list, or {@code null} if the list is empty
     */
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
