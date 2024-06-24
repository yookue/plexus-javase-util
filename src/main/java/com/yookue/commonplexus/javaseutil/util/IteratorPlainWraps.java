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


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link java.util.Iterator}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class IteratorPlainWraps {
    /**
     * Checks whether the given {@code iterator} contains the given element
     *
     * @param iterator the source iterator to check
     * @param comparison the target element to look for
     *
     * @return {@code true} if found, {@code false} otherwise
     */
    public static boolean contains(@Nullable Iterator<?> iterator, @Nullable Object comparison) {
        if (iterator == null) {
            return false;
        }
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (ObjectUtilsWraps.equals(element, comparison)) {
                return true;
            }
        }
        return false;
    }

    public static <E> void forEach(@Nullable Iterator<E> iterator, @Nullable Consumer<? super E> action) {
        forEach(iterator, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEach(@Nullable Iterator<E> iterator, @Nullable Consumer<? super E> action, @Nullable Predicate<? super E> filter) {
        if (ObjectUtils.anyNull(iterator, action)) {
            return;
        }
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static <E> void forEachBreakable(@Nullable Iterator<E> iterator, @Nullable Function<? super E, Boolean> action) {
        forEachBreakable(iterator, action, null);
    }

    /**
     * Perform an action for filtered elements in the {@code iterator} until the {@code action} return false
     *
     * @param iterator the source iterator to lookup
     * @param action the action to be performed. when return {@code true}, means continue next element; otherwise means break loop
     * @param filter the filter to choose elements
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachBreakable(@Nullable Iterator<E> iterator, @Nullable Function<? super E, Boolean> action, @Nullable Predicate<? super E> filter) {
        if (ObjectUtils.anyNull(iterator, action)) {
            return;
        }
        while (iterator.hasNext()) {
            E element = iterator.next();
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachTailing(@Nullable Iterator<E> iterator, @Nullable Consumer<? super E> action) {
        if (ObjectUtils.anyNull(iterator, action)) {
            return null;
        }
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (iterator.hasNext()) {
                action.accept(element);
            } else {
                return element;
            }
        }
        return null;
    }

    public static <E> void forEachIndexing(@Nullable Iterator<E> iterator, @Nullable BiConsumer<Integer, ? super E> action) {
        forEachIndexing(iterator, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexing(@Nullable Iterator<E> iterator, @Nullable BiConsumer<Integer, ? super E> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ObjectUtils.anyNull(iterator, action)) {
            return;
        }
        int index = 0;
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static <E> void forEachIndexingBreakable(@Nullable Iterator<E> iterator, @Nullable BiFunction<Integer, ? super E, Boolean> action) {
        forEachIndexingBreakable(iterator, action, null);
    }

    /**
     * Perform an action with index for filtered elements in the {@code iterator} until the {@code action} return false
     *
     * @param iterator the iterator to get a value from
     * @param action the action to be performed. when return {@code true}, means continue next element; otherwise means break loop
     * @param filter the filter to choose elements
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexingBreakable(@Nullable Iterator<E> iterator, @Nullable BiFunction<Integer, ? super E, Boolean> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ObjectUtils.anyNull(iterator, action)) {
            return;
        }
        int index = 0;
        while (iterator.hasNext()) {
            E element = iterator.next();
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachIndexingTailing(@Nullable Iterator<E> iterator, @Nullable BiConsumer<Integer, ? super E> action) {
        if (ObjectUtils.anyNull(iterator, action)) {
            return null;
        }
        int index = 0;
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (iterator.hasNext()) {
                action.accept(index, element);
            } else {
                return element;
            }
            index++;
        }
        return null;
    }

    /**
     * Returns the index-th value in the iterator
     *
     * @param iterator the source to get from
     * @param index the index to get
     *
     * @return the index-th value in the iterator
     */
    @Nullable
    public static <E> E get(@Nullable Iterator<E> iterator, int index) {
        if (iterator == null || index < 0) {
            return null;
        }
        int cursor = 0;
        while (iterator.hasNext()) {
            E result = iterator.next();
            if (cursor == index) {
                return result;
            }
            cursor++;
        }
        return null;
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Iterator<?> iterator) {
        return getComponentType(iterator, false);
    }

    /**
     * Returns the component type of superclass in the iterator
     *
     * @param iterator the iterator to check
     * @param deepScan true means need to scan all the elements; false means by the first nonnull element
     *
     * @see "org.springframework.util.CollectionUtils#findCommonElementType"
     */
    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static Class<?> getComponentType(@Nullable Iterator<?> iterator, boolean deepScan) {
        if (iterator == null) {
            return null;
        }
        Class<?> candidate = null;
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (element == null) {
                continue;
            }
            if (!deepScan) {
                return element.getClass();
            }
            if (candidate == null) {
                candidate = element.getClass();
            } else {
                boolean assignable = false;
                if (ClassUtils.isAssignable(candidate, element.getClass())) {
                    candidate = element.getClass();
                    assignable = true;
                }
                if (!assignable) {
                    assignable = ClassUtils.isAssignable(element.getClass(), candidate);
                }
                if (!assignable) {
                    return null;
                }
            }
        }
        return candidate;
    }

    public static void ifEmpty(@Nullable Iterator<?> iterator, @Nullable Runnable action) {
        if (isEmpty(iterator) && action != null) {
            action.run();
        }
    }

    public static <E, T extends Iterator<E>> void ifEmptyOrElse(@Nullable T iterator, @Nullable Runnable absentAction, @Nullable Consumer<? super T> presentAction) {
        if (isEmpty(iterator)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(iterator);
            }
        }
    }

    public static <E, T extends Iterator<E>> void ifNotEmpty(@Nullable T iterator, @Nullable Consumer<? super T> action) {
        ifNotEmpty(iterator, action, null);
    }

    public static <E, T extends Iterator<E>> void ifNotEmpty(@Nullable T iterator, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (isNotEmpty(iterator) && action != null && (filter == null || filter.test(iterator))) {
            action.accept(iterator);
        }
    }

    public static <E, T extends Iterator<E>> void ifNotEmpty(@Nullable T iterator, @Nullable Runnable action) {
        if (isNotEmpty(iterator) && action != null) {
            action.run();
        }
    }

    public static <T extends Iterator<?>> void ifSingleton(@Nullable T iterator, @Nullable Consumer<? super T> action) {
        ifSingletonOrElse(iterator, action, null);
    }

    public static <T extends Iterator<?>> void ifSingleton(@Nullable T iterator, @Nullable Runnable action) {
        ifSingletonOrElse(iterator, action, null);
    }

    public static <T extends Iterator<?>> void ifSingletonOrElse(@Nullable T iterator, @Nullable Consumer<? super T> presentAction, @Nullable Consumer<? super T> absentAction) {
        if (isSingleton(iterator)) {
            if (presentAction != null) {
                presentAction.accept(iterator);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(iterator);
            }
        }
    }

    public static <T extends Iterator<?>> void ifSingletonOrElse(@Nullable T iterator, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isSingleton(iterator)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static <T extends Iterator<?>> void ifMultitude(@Nullable T iterator, @Nullable Consumer<? super T> action) {
        if (isMultitude(iterator) && action != null) {
            action.accept(iterator);
        }
    }

    public static <T extends Iterator<?>> void ifMultitude(@Nullable T iterator, @Nullable Runnable action) {
        if (isMultitude(iterator) && action != null) {
            action.run();
        }
    }

    public static boolean isEmpty(@Nullable Iterator<?> iterator) {
        return iterator == null || !iterator.hasNext();
    }

    public static boolean isNotEmpty(@Nullable Iterator<?> iterator) {
        return !isEmpty(iterator);
    }

    public static boolean isIndexBound(@Nullable Iterator<?> iterator, int index) {
        return index >= 0 && index < size(iterator);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameSize(@Nullable Iterator<?>... iterators) {
        if (ArrayUtils.getLength(iterators) < 2) {
            return false;
        }
        int size = size(ArrayUtils.get(iterators, 0));
        return Arrays.stream(iterators).skip(1L).allMatch(element -> size(element) == size);
    }

    public static boolean isSingleton(@Nullable Iterator<?> iterator) {
        return size(iterator) == 1;
    }

    public static boolean isMultitude(@Nullable Iterator<?> iterator) {
        return size(iterator) > 1;
    }

    @Nullable
    public static <T extends Iterator<?>> T nullIfEmpty(@Nullable T iterator) {
        return isEmpty(iterator) ? null : iterator;
    }

    /**
     * @see java.util.Collection#removeIf
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean removeIf(@Nullable Iterator<E> iterator, @Nullable Predicate<? super E> filter) {
        if (ObjectUtils.anyNull(iterator, filter)) {
            return false;
        }
        boolean removed = false;
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean removeIfIndexing(@Nullable Iterator<E> iterator, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ObjectUtils.anyNull(iterator, filter)) {
            return false;
        }
        boolean removed = false;
        int index = 0;
        while (iterator.hasNext()) {
            if (filter.test(index, iterator.next())) {
                iterator.remove();
                removed = true;
            }
            index++;
        }
        return removed;
    }

    public static int size(@Nullable Iterator<?> iterator) {
        if (iterator == null) {
            return 0;
        }
        int result = 0;
        while (iterator.hasNext()) {
            iterator.next();
            result++;
        }
        return result;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int maxSize(@Nullable Iterator<?>... iterators) {
        return ArrayUtils.isEmpty(iterators) ? 0 : Arrays.stream(iterators).mapToInt(IteratorPlainWraps::size).max().orElse(0);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int sumSize(@Nullable Iterator<?>... iterators) {
        return ArrayUtils.isEmpty(iterators) ? 0 : Arrays.stream(iterators).mapToInt(IteratorPlainWraps::size).sum();
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <E> E[] toElementArray(@Nullable Iterator<E> iterator) {
        Class<E> clazz = (Class<E>) getComponentType(iterator);
        return toElementArray(iterator, clazz);
    }

    @Nullable
    @SuppressWarnings({"unchecked", "SuspiciousToArrayCall"})
    public static <E> E[] toElementArray(@Nullable Iterator<E> iterator, @Nullable Class<? super E> elementType) {
        if (ObjectUtils.anyNull(iterator, elementType)) {
            return null;
        }
        List<E> result = toElementList(iterator);
        if (CollectionPlainWraps.isEmpty(result)) {
            return null;
        }
        if (elementType == Boolean.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_BOOLEAN_OBJECT_ARRAY);
        } else if (elementType == Byte.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY);
        } else if (elementType == Character.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_CHARACTER_OBJECT_ARRAY);
        } else if (elementType == Class.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_CLASS_ARRAY);
        } else if (elementType == Double.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY);
        } else if (elementType == Field.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_FIELD_ARRAY);
        } else if (elementType == Float.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY);
        } else if (elementType == Integer.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY);
        } else if (elementType == Long.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_LONG_OBJECT_ARRAY);
        } else if (elementType == Method.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_METHOD_ARRAY);
        } else if (elementType == Object.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_OBJECT_ARRAY);
        } else if (elementType == Short.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_SHORT_OBJECT_ARRAY);
        } else if (elementType == String.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        } else if (elementType == Throwable.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_THROWABLE_ARRAY);
        } else if (elementType == Type.class) {
            return (E[]) result.toArray(ArrayUtils.EMPTY_TYPE_ARRAY);
        } else {
            E[] array = (E[]) Array.newInstance(elementType, result.size());
            return result.toArray(array);
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Iterator<E> iterator, @Nullable E[] target) {
        if (ObjectUtils.anyNull(iterator, target)) {
            return null;
        }
        List<E> result = toElementList(iterator);
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(target);
    }

    @Nullable
    public static <E> Enumeration<E> toElementEnumeration(@Nullable Iterator<E> iterator) {
        return CollectionPlainWraps.toElementEnumeration(toElementList(iterator));
    }

    @Nullable
    public static <E> List<E> toElementList(@Nullable Iterator<E> iterator) {
        if (iterator == null) {
            return null;
        }
        List<E> result = new ArrayList<>();
        forEach(iterator, result::add);
        return result.isEmpty() ? null : result;
    }

    @Nullable
    public static <E> Set<E> toElementSet(@Nullable Iterator<E> iterator) {
        if (iterator == null) {
            return null;
        }
        Set<E> result = new LinkedHashSet<>();
        forEach(iterator, result::add);
        return result.isEmpty() ? null : result;
    }

    @Nullable
    public static Object[] toObjectArray(@Nullable Iterator<?> iterator) {
        List<?> result = toObjectList(iterator);
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

    @Nullable
    public static List<?> toObjectList(@Nullable Iterator<?> iterator) {
        if (iterator == null) {
            return null;
        }
        List<Object> result = new ArrayList<>();
        forEach(iterator, result::add);
        return result.isEmpty() ? null : result;
    }
}
