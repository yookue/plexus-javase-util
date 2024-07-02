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
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link java.lang.Iterable}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class IterablePlainWraps {
    /**
     * Checks whether the given {@code iterable} contains the given element
     *
     * @param iterable the source iterable to check
     * @param comparison the target element to look for
     *
     * @return {@code true} if found, {@code false} otherwise
     */
    public static boolean contains(@Nullable Iterable<?> iterable, @Nullable Object comparison) {
        return iterable != null && IteratorPlainWraps.contains(iterable.iterator(), comparison);
    }

    @Nullable
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E extends Iterable<?>> E firstEmpty(@Nullable E... iterables) {
        return ArrayUtils.isEmpty(iterables) ? null : Arrays.stream(iterables).filter(IterablePlainWraps::isEmpty).findFirst().orElse(null);
    }

    @Nullable
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E extends Iterable<?>> E firstNonEmpty(@Nullable E... iterables) {
        return ArrayUtils.isEmpty(iterables) ? null : Arrays.stream(iterables).filter(IterablePlainWraps::isNotEmpty).findFirst().orElse(null);
    }

    public static <E> void forEach(@Nullable Iterable<E> iterable, @Nullable Consumer<? super E> action) {
        forEach(iterable, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEach(@Nullable Iterable<E> iterable, @Nullable Consumer<? super E> action, @Nullable Predicate<? super E> filter) {
        if (ObjectUtils.allNotNull(iterable, action)) {
            IteratorPlainWraps.forEach(iterable.iterator(), action, filter);
        }
    }

    public static <E> void forEachBreakable(@Nullable Iterable<E> iterable, @Nullable Function<? super E, Boolean> action) {
        forEachBreakable(iterable, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachBreakable(@Nullable Iterable<E> iterable, @Nullable Function<? super E, Boolean> action, @Nullable Predicate<? super E> filter) {
        if (ObjectUtils.allNotNull(iterable, action)) {
            IteratorPlainWraps.forEachBreakable(iterable.iterator(), action, filter);
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachTailing(@Nullable Iterable<E> iterable, @Nullable Consumer<? super E> action) {
        return ObjectUtils.anyNull(iterable, action) ? null : IteratorPlainWraps.forEachTailing(iterable.iterator(), action);
    }

    public static <E> void forEachIndexing(@Nullable Iterable<E> iterable, @Nullable BiConsumer<Integer, ? super E> action) {
        forEachIndexing(iterable, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexing(@Nullable Iterable<E> iterable, @Nullable BiConsumer<Integer, ? super E> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ObjectUtils.allNotNull(iterable, action)) {
            IteratorPlainWraps.forEachIndexing(iterable.iterator(), action, filter);
        }
    }

    public static <E> void forEachIndexingBreakable(@Nullable Iterable<E> iterable, @Nullable BiFunction<Integer, ? super E, Boolean> action) {
        forEachIndexingBreakable(iterable, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexingBreakable(@Nullable Iterable<E> iterable, @Nullable BiFunction<Integer, ? super E, Boolean> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ObjectUtils.allNotNull(iterable, action)) {
            IteratorPlainWraps.forEachIndexingBreakable(iterable.iterator(), action, filter);
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachIndexingTailing(@Nullable Iterable<E> iterable, @Nullable BiConsumer<Integer, ? super E> action) {
        return ObjectUtils.anyNull(iterable, action) ? null : IteratorPlainWraps.forEachIndexingTailing(iterable.iterator(), action);
    }

    /**
     * Returns the index-th value in the iterable
     *
     * @param iterable the source to get from
     * @param index the index to get
     *
     * @return the index-th value in the iterable
     */
    @Nullable
    public static <E> E get(@Nullable Iterable<E> iterable, int index) {
        if (iterable == null || index < 0) {
            return null;
        }
        if (iterable instanceof List) {
            return ListPlainWraps.get((List<E>) iterable, index);
        }
        return IteratorPlainWraps.get(iterable.iterator(), index);
    }

    /**
     * Returns the first element in the given iterable
     *
     * @param iterable the source iterable
     *
     * @return the first element in the given iterable
     */
    @Nullable
    public static <E> E getFirst(@Nullable Iterable<E> iterable) {
        if (iterable == null) {
            return null;
        }
        if (iterable instanceof List) {
            return ((List<E>) iterable).get(0);
        } else if (iterable instanceof SortedSet) {
            return ((SortedSet<E>) iterable).first();
        }
        Iterator<E> iterator = iterable.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    /**
     * Returns the last element in the given iterable
     *
     * @param iterable the source iterable
     *
     * @return the last element in the given iterable
     *
     * @see "org.springframework.util.CollectionUtils#lastElement"
     */
    @Nullable
    public static <E> E getLast(@Nullable Iterable<E> iterable) {
        if (iterable == null) {
            return null;
        }
        if (iterable instanceof List) {
            return ListPlainWraps.getLast((List<E>) iterable);
        } else if (iterable instanceof SortedSet) {
            return ((SortedSet<E>) iterable).last();
        }
        // Full iteration necessary...
        Iterator<E> iterator = iterable.iterator();
        E result = null;
        while (iterator.hasNext()) {
            result = iterator.next();
        }
        return result;
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Iterable<?> iterable) {
        return getComponentType(iterable, false);
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Iterable<?> iterable, boolean deepScan) {
        return (iterable == null) ? null : IteratorPlainWraps.getComponentType(iterable.iterator(), deepScan);
    }

    public static <T extends Iterable<?>> void ifSingleton(@Nullable T iterable, @Nullable Consumer<? super T> action) {
        ifSingletonOrElse(iterable, action, null);
    }

    public static <T extends Iterable<?>> void ifSingleton(@Nullable T iterable, @Nullable Runnable action) {
        ifSingletonOrElse(iterable, action, null);
    }

    public static <T extends Iterable<?>> void ifSingletonOrElse(@Nullable T iterable, @Nullable Consumer<? super T> presentAction, @Nullable Consumer<? super T> absentAction) {
        if (isSingleton(iterable)) {
            if (presentAction != null) {
                presentAction.accept(iterable);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(iterable);
            }
        }
    }

    public static <T extends Iterable<?>> void ifSingletonOrElse(@Nullable T iterable, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isSingleton(iterable)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static <T extends Iterable<?>> void ifMultitude(@Nullable T iterable, @Nullable Consumer<? super T> action) {
        if (isMultitude(iterable) && action != null) {
            action.accept(iterable);
        }
    }

    public static <T extends Iterable<?>> void ifMultitude(@Nullable T iterable, @Nullable Runnable action) {
        if (isMultitude(iterable) && action != null) {
            action.run();
        }
    }

    public static void ifEmpty(@Nullable Iterable<?> iterable, @Nullable Runnable action) {
        if (isEmpty(iterable) && action != null) {
            action.run();
        }
    }

    public static <E, T extends Iterable<E>> void ifEmptyOrElse(@Nullable T iterable, @Nullable Runnable absentAction, @Nullable Consumer<? super T> presentAction) {
        if (isEmpty(iterable)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(iterable);
            }
        }
    }

    public static <E, T extends Iterable<E>> void ifNotEmpty(@Nullable T iterable, @Nullable Consumer<? super T> action) {
        ifNotEmpty(iterable, action, null);
    }

    public static <E, T extends Iterable<E>> void ifNotEmpty(@Nullable T iterable, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (isNotEmpty(iterable) && action != null && (filter == null || filter.test(iterable))) {
            action.accept(iterable);
        }
    }

    public static <E, T extends Iterable<E>> void ifNotEmpty(@Nullable T iterable, @Nullable Runnable action) {
        if (isNotEmpty(iterable) && action != null) {
            action.run();
        }
    }

    public static boolean isEmpty(@Nullable Iterable<?> iterable) {
        if (iterable == null) {
            return true;
        }
        if (iterable instanceof Collection<?>) {
            return ((Collection<?>) iterable).isEmpty();
        }
        return IteratorPlainWraps.isEmpty(iterable.iterator());
    }

    public static boolean isNotEmpty(@Nullable Iterable<?> iterable) {
        return !isEmpty(iterable);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameSize(@Nullable Iterable<?>... iterables) {
        if (ArrayUtils.getLength(iterables) < 2) {
            return false;
        }
        int size = size(ArrayUtils.get(iterables, 0));
        return Arrays.stream(iterables).skip(1L).allMatch(element -> size(element) == size);
    }

    public static boolean isSingleton(@Nullable Iterable<?> iterable) {
        return size(iterable) == 1;
    }

    public static boolean isMultitude(@Nullable Iterable<?> iterable) {
        return size(iterable) > 1;
    }

    public static boolean isIndexBound(@Nullable Iterable<?> iterable, int index) {
        return isIndexBound(iterable, index, false);
    }

    /**
     * Returns true if the index object exists in the iterable
     *
     * @param iterable the source to check
     * @param index the index to detect
     * @param adding indicates whether trying to add an element to the source iterable or not
     *
     * @return true if the index object exists in the iterable
     *
     * @see ArrayUtils#isArrayIndexValid
     */
    public static boolean isIndexBound(@Nullable Iterable<?> iterable, int index, boolean adding) {
        return index >= 0 && index < (size(iterable) + (adding ? 1 : 0));
    }

    @Nullable
    public static <T extends Iterable<?>> T nullIfEmpty(@Nullable T iterable) {
        return isEmpty(iterable) ? null : iterable;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean removeIf(@Nullable Iterable<E> iterable, @Nullable Predicate<? super E> filter) {
        return ObjectUtils.allNotNull(iterable, filter) && IteratorPlainWraps.removeIf(iterable.iterator(), filter);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean removeIfIndexing(@Nullable Iterable<E> iterable, @Nullable BiPredicate<Integer, ? super E> filter) {
        return ObjectUtils.allNotNull(iterable, filter) && IteratorPlainWraps.removeIfIndexing(iterable.iterator(), filter);
    }

    public static int size(@Nullable Iterable<?> iterable) {
        if (iterable == null) {
            return 0;
        }
        if (iterable instanceof Collection<?>) {
            return ((Collection<?>) iterable).size();
        }
        return IteratorPlainWraps.size(iterable.iterator());
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int maxSize(@Nullable Iterable<?>... iterables) {
        return ArrayUtils.isEmpty(iterables) ? 0 : Arrays.stream(iterables).mapToInt(IterablePlainWraps::size).max().orElse(0);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int sumSize(@Nullable Iterable<?>... iterables) {
        return ArrayUtils.isEmpty(iterables) ? 0 : Arrays.stream(iterables).mapToInt(IterablePlainWraps::size).sum();
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <E> E[] toElementArray(@Nullable Iterable<E> iterable) {
        Class<E> clazz = (Class<E>) getComponentType(iterable);
        return toElementArray(iterable, clazz);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Iterable<E> iterable, @Nullable Class<? super E> elementType) {
        return ObjectUtils.anyNull(iterable, elementType) ? null : IteratorPlainWraps.toElementArray(iterable.iterator(), elementType);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Iterable<E> iterable, @Nullable E[] target) {
        return ObjectUtils.anyNull(iterable, target) ? null : IteratorPlainWraps.toElementArray(iterable.iterator(), target);
    }

    @Nullable
    public static <E> Enumeration<E> toElementEnumeration(@Nullable Iterable<E> iterable) {
        return (iterable == null) ? null : IteratorPlainWraps.toElementEnumeration(iterable.iterator());
    }

    @Nullable
    public static <E> List<E> toElementList(@Nullable Iterable<E> iterable) {
        return (iterable == null) ? null : IteratorPlainWraps.toElementList(iterable.iterator());
    }

    @Nullable
    public static <E> Set<E> toElementSet(@Nullable Iterable<E> iterable) {
        return (iterable == null) ? null : IteratorPlainWraps.toElementSet(iterable.iterator());
    }

    @Nullable
    public static Object[] toObjectArray(@Nullable Iterable<?> iterable) {
        return (iterable == null) ? null : IteratorPlainWraps.toObjectArray(iterable.iterator());
    }

    @Nullable
    public static List<?> toObjectList(@Nullable Iterable<?> iterable) {
        return (iterable == null) ? null : IteratorPlainWraps.toObjectList(iterable.iterator());
    }
}
