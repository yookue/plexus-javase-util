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
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import com.yookue.commonplexus.javaseutil.iterator.EnumerationIterator;


/**
 * Utilities for {@link java.util.Enumeration}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class EnumerationPlainWraps {
    /**
     * Checks whether the given {@code enumeration} contains the given element
     *
     * @param enumeration the source enumeration to check
     * @param comparison the target element to look for
     *
     * @return {@code true} if found, {@code false} otherwise
     */
    public static boolean contains(@Nullable Enumeration<?> enumeration, @Nullable Object comparison) {
        return enumeration != null && IteratorPlainWraps.contains(new EnumerationIterator<>(enumeration), comparison);
    }

    public static <E> void forEach(@Nullable Enumeration<E> enumeration, @Nullable Consumer<? super E> action) {
        forEach(enumeration, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEach(@Nullable Enumeration<E> enumeration, @Nullable Consumer<? super E> action, @Nullable Predicate<? super E> filter) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return;
        }
        while (enumeration.hasMoreElements()) {
            E element = enumeration.nextElement();
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static <E> void forEachBreakable(@Nullable Enumeration<E> enumeration, @Nullable Function<? super E, Boolean> action) {
        forEachBreakable(enumeration, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachBreakable(@Nullable Enumeration<E> enumeration, @Nullable Function<? super E, Boolean> action, @Nullable Predicate<? super E> filter) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return;
        }
        while (enumeration.hasMoreElements()) {
            E element = enumeration.nextElement();
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachTailing(@Nullable Enumeration<E> enumeration, @Nullable Consumer<? super E> action) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return null;
        }
        while (enumeration.hasMoreElements()) {
            E element = enumeration.nextElement();
            if (enumeration.hasMoreElements()) {
                action.accept(element);
            } else {
                return element;
            }
        }
        return null;
    }

    public static <E> void forEachIndexing(@Nullable Enumeration<E> enumeration, @Nullable BiConsumer<Integer, ? super E> action) {
        forEachIndexing(enumeration, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexing(@Nullable Enumeration<E> enumeration, @Nullable BiConsumer<Integer, ? super E> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return;
        }
        int index = 0;
        while (enumeration.hasMoreElements()) {
            E element = enumeration.nextElement();
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static <E> void forEachIndexingBreakable(@Nullable Enumeration<E> enumeration, @Nullable BiFunction<Integer, ? super E, Boolean> action) {
        forEachIndexingBreakable(enumeration, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexingBreakable(@Nullable Enumeration<E> enumeration, @Nullable BiFunction<Integer, ? super E, Boolean> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return;
        }
        int index = 0;
        while (enumeration.hasMoreElements()) {
            E element = enumeration.nextElement();
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachIndexingTailing(@Nullable Enumeration<E> enumeration, @Nullable BiConsumer<Integer, ? super E> action) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return null;
        }
        int index = 0;
        while (enumeration.hasMoreElements()) {
            E element = enumeration.nextElement();
            if (enumeration.hasMoreElements()) {
                action.accept(index, element);
            } else {
                return element;
            }
            index++;
        }
        return null;
    }

    /**
     * Returns the index-th value in the enumeration
     *
     * @param enumeration the source to get from
     * @param index the index to get
     *
     * @return the index-th value in the enumeration
     */
    @Nullable
    public static <E> E get(@Nullable Enumeration<E> enumeration, int index) {
        if (enumeration == null || index < 0) {
            return null;
        }
        int cursor = 0;
        while (enumeration.hasMoreElements()) {
            E result = enumeration.nextElement();
            if (cursor == index) {
                return result;
            }
            cursor++;
        }
        return null;
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Enumeration<?> enumeration) {
        return getComponentType(enumeration, false);
    }

    /**
     * Returns the component type of superclass in the enumeration
     *
     * @param enumeration the enumeration to check
     * @param deepScan true means need to scan all the elements; false means by the first nonnull element
     *
     * @see "org.springframework.util.CollectionUtils#findCommonElementType"
     */
    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static Class<?> getComponentType(@Nullable Enumeration<?> enumeration, boolean deepScan) {
        if (enumeration == null) {
            return null;
        }
        Class<?> candidate = null;
        while (enumeration.hasMoreElements()) {
            Object element = enumeration.nextElement();
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

    public static void ifEmpty(@Nullable Enumeration<?> enumeration, @Nullable Runnable action) {
        if (isEmpty(enumeration) && action != null) {
            action.run();
        }
    }

    public static <E, T extends Enumeration<E>> void ifEmptyOrElse(@Nullable T enumeration, @Nullable Runnable absentAction, @Nullable Consumer<? super T> presentAction) {
        if (isEmpty(enumeration)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(enumeration);
            }
        }
    }

    public static <E, T extends Enumeration<E>> void ifNotEmpty(@Nullable T enumeration, @Nullable Consumer<? super T> action) {
        ifNotEmpty(enumeration, action, null);
    }

    public static <E, T extends Enumeration<E>> void ifNotEmpty(@Nullable T enumeration, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (isNotEmpty(enumeration) && action != null && (filter == null || filter.test(enumeration))) {
            action.accept(enumeration);
        }
    }

    public static <E, T extends Enumeration<E>> void ifNotEmpty(@Nullable T enumeration, @Nullable Runnable action) {
        if (isNotEmpty(enumeration) && action != null) {
            action.run();
        }
    }

    public static <T extends Enumeration<?>> void ifSingleton(@Nullable T enumeration, @Nullable Consumer<? super T> action) {
        ifSingletonOrElse(enumeration, action, null);
    }

    public static <T extends Enumeration<?>> void ifSingleton(@Nullable T enumeration, @Nullable Runnable action) {
        ifSingletonOrElse(enumeration, action, null);
    }

    public static <T extends Enumeration<?>> void ifSingletonOrElse(@Nullable T enumeration, @Nullable Consumer<? super T> presentAction, @Nullable Consumer<? super T> absentAction) {
        if (isSingleton(enumeration)) {
            if (presentAction != null) {
                presentAction.accept(enumeration);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(enumeration);
            }
        }
    }

    public static <T extends Enumeration<?>> void ifSingletonOrElse(@Nullable T enumeration, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isSingleton(enumeration)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static <T extends Enumeration<?>> void ifMultitude(@Nullable T enumeration, @Nullable Consumer<? super T> action) {
        if (isMultitude(enumeration) && action != null) {
            action.accept(enumeration);
        }
    }

    public static <T extends Enumeration<?>> void ifMultitude(@Nullable T enumeration, @Nullable Runnable action) {
        if (isMultitude(enumeration) && action != null) {
            action.run();
        }
    }

    public static boolean isEmpty(@Nullable Enumeration<?> enumeration) {
        return enumeration == null || !enumeration.hasMoreElements();
    }

    public static boolean isNotEmpty(@Nullable Enumeration<?> enumeration) {
        return !isEmpty(enumeration);
    }

    public static boolean isIndexBound(@Nullable Enumeration<?> enumeration, int index) {
        return index >= 0 && index < size(enumeration);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameSize(@Nullable Enumeration<?>... enumerations) {
        if (ArrayUtils.getLength(enumerations) < 2) {
            return false;
        }
        int size = size(ArrayUtils.get(enumerations, 0));
        return Arrays.stream(enumerations).skip(1L).allMatch(element -> size(element) == size);
    }

    public static boolean isSingleton(@Nullable Enumeration<?> enumeration) {
        return size(enumeration) == 1;
    }

    public static boolean isMultitude(@Nullable Enumeration<?> enumeration) {
        return size(enumeration) > 1;
    }

    @Nullable
    public static <T extends Enumeration<?>> T nullIfEmpty(@Nullable T enumeration) {
        return isEmpty(enumeration) ? null : enumeration;
    }

    public static int size(@Nullable Enumeration<?> enumeration) {
        if (enumeration == null) {
            return 0;
        }
        int result = 0;
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement();
            result++;
        }
        return result;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int maxSize(@Nullable Enumeration<?>... enumerations) {
        return ArrayUtils.isEmpty(enumerations) ? 0 : Arrays.stream(enumerations).mapToInt(EnumerationPlainWraps::size).max().orElse(0);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int sumSize(@Nullable Enumeration<?>... enumerations) {
        return ArrayUtils.isEmpty(enumerations) ? 0 : Arrays.stream(enumerations).mapToInt(EnumerationPlainWraps::size).sum();
    }

    /**
     * Return an array containing all the elements in the enumeration
     *
     * @param enumeration the enumeration to convert, with element that can not be primitive types
     *
     * @return an array containing all the elements in the enumeration
     *
     * @see "org.springframework.util.CollectionUtils#toArray"
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <E> E[] toElementArray(@Nullable Enumeration<E> enumeration) {
        Class<E> clazz = (Class<E>) getComponentType(enumeration);
        return toElementArray(enumeration, clazz);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Enumeration<E> enumeration, @Nullable Class<? super E> elementType) {
        return ObjectUtils.anyNull(enumeration, elementType) ? null : IteratorPlainWraps.toElementArray(new EnumerationIterator<>(enumeration), elementType);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Enumeration<E> enumeration, @Nullable E[] target) {
        if (ObjectUtils.anyNull(enumeration, target)) {
            return null;
        }
        List<E> result = toElementList(enumeration);
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(target);
    }

    @Nullable
    public static <E> List<E> toElementList(@Nullable Enumeration<E> enumeration) {
        if (enumeration == null) {
            return null;
        }
        List<E> result = Collections.list(enumeration);
        return result.isEmpty() ? null : result;
    }

    @Nullable
    public static <E> Set<E> toElementSet(@Nullable Enumeration<E> enumeration) {
        if (enumeration == null) {
            return null;
        }
        Set<E> result = new LinkedHashSet<>();
        while (enumeration.hasMoreElements()) {
            result.add(enumeration.nextElement());
        }
        return result.isEmpty() ? null : result;
    }

    @Nullable
    public static Object[] toObjectArray(@Nullable Enumeration<?> enumeration) {
        List<?> result = toObjectList(enumeration);
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

    @Nullable
    public static List<?> toObjectList(@Nullable Enumeration<?> enumeration) {
        if (enumeration == null) {
            return null;
        }
        List<Object> result = new ArrayList<>();
        while (enumeration.hasMoreElements()) {
            result.add(enumeration.nextElement());
        }
        return result.isEmpty() ? null : result;
    }

    @Nullable
    public static List<String> toStringList(@Nullable StringTokenizer tokenizer) {
        if (tokenizer == null) {
            return null;
        }
        List<String> result = new ArrayList<>(tokenizer.countTokens());
        while (tokenizer.hasMoreTokens()) {
            result.add(tokenizer.nextToken());
        }
        return result.isEmpty() ? null : result;
    }
}
