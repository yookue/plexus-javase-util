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
import cn.unikue.commonplexus.javaseutil.iterator.EnumerationIterator;


/**
 * Utilities for {@link java.util.Enumeration}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class EnumerationPlainWraps {
    /**
     * Checks whether the given {@code enumeration} contains the given item
     *
     * @param enumeration The source enumeration to check
     * @param comparison The target item to look for
     *
     * @return {@code true} if found, {@code false} otherwise
     */
    public static boolean contains(@Nullable Enumeration<?> enumeration, @Nullable Object comparison) {
        return enumeration != null && IteratorPlainWraps.contains(new EnumerationIterator<>(enumeration), comparison);
    }

    /**
     * Gets the first non-null element from the enumeration
     *
     * @param enumeration the enumeration to get from
     *
     * @return the first non-null element, or {@code null} if the enumeration is null or contains only null elements
     */
    @Nullable
    public static <E> E firstNonNull(@Nullable Enumeration<E> enumeration) {
        return (enumeration == null) ? null : IteratorPlainWraps.firstNonNull(new EnumerationIterator<>(enumeration));
    }

    /**
     * Gets the first non-empty element from the enumeration
     *
     * @param enumeration the enumeration to get from
     *
     * @return the first non-empty element, or {@code null} if the enumeration is null or contains only empty elements
     */
    @Nullable
    public static <E> E firstNonEmpty(@Nullable Enumeration<E> enumeration) {
        return (enumeration == null) ? null : IteratorPlainWraps.firstNonEmpty(new EnumerationIterator<>(enumeration));
    }

    /**
     * Performs the given action for each element in the enumeration
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element
     */
    public static <E> void forEach(@Nullable Enumeration<E> enumeration, @Nullable Consumer<? super E> action) {
        forEach(enumeration, action, null);
    }

    /**
     * Performs the given action for each element in the enumeration that passes the filter
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element
     * @param filter the predicate to test each element before performing the action; can be null to process all elements
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEach(@Nullable Enumeration<E> enumeration, @Nullable Consumer<? super E> action, @Nullable Predicate<? super E> filter) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return;
        }
        while (enumeration.hasMoreElements()) {
            E item = enumeration.nextElement();
            if (filter == null || filter.test(item)) {
                action.accept(item);
            }
        }
    }

    /**
     * Performs the given action for each element in the enumeration until it returns false
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element; returns {@code true} to continue, {@code false} to break
     */
    public static <E> void forEachBreakable(@Nullable Enumeration<E> enumeration, @Nullable Function<? super E, Boolean> action) {
        forEachBreakable(enumeration, action, null);
    }

    /**
     * Performs the given action for each element in the enumeration that passes the filter until it returns false
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element; returns {@code true} to continue, {@code false} to break
     * @param filter the predicate to test each element before performing the action; can be null to process all elements
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachBreakable(@Nullable Enumeration<E> enumeration, @Nullable Function<? super E, Boolean> action, @Nullable Predicate<? super E> filter) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return;
        }
        while (enumeration.hasMoreElements()) {
            E item = enumeration.nextElement();
            if ((filter == null || filter.test(item)) && BooleanUtils.isNotTrue(action.apply(item))) {
                break;
            }
        }
    }

    /**
     * Performs the given action for each element except the last one, then returns the last element
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element except the last
     *
     * @return the last element of the enumeration, or {@code null} if the enumeration is null or empty
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachTailing(@Nullable Enumeration<E> enumeration, @Nullable Consumer<? super E> action) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return null;
        }
        while (enumeration.hasMoreElements()) {
            E item = enumeration.nextElement();
            if (enumeration.hasMoreElements()) {
                action.accept(item);
            } else {
                return item;
            }
        }
        return null;
    }

    /**
     * Performs the given action for each element in the enumeration with index
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element with its index
     */
    public static <E> void forEachIndexing(@Nullable Enumeration<E> enumeration, @Nullable BiConsumer<Integer, ? super E> action) {
        forEachIndexing(enumeration, action, null);
    }

    /**
     * Performs the given action for each element in the enumeration with index that passes the filter
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element with its index
     * @param filter the predicate to test each element before performing the action; can be null to process all elements
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexing(@Nullable Enumeration<E> enumeration, @Nullable BiConsumer<Integer, ? super E> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return;
        }
        int index = 0;
        while (enumeration.hasMoreElements()) {
            E item = enumeration.nextElement();
            if (filter == null || filter.test(index, item)) {
                action.accept(index, item);
            }
            index++;
        }
    }

    /**
     * Performs the given action for each element in the enumeration with index until it returns false
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element with its index; returns {@code true} to continue, {@code false} to break
     */
    public static <E> void forEachIndexingBreakable(@Nullable Enumeration<E> enumeration, @Nullable BiFunction<Integer, ? super E, Boolean> action) {
        forEachIndexingBreakable(enumeration, action, null);
    }

    /**
     * Performs the given action for each element in the enumeration with index that passes the filter until it returns false
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element with its index; returns {@code true} to continue, {@code false} to break
     * @param filter the predicate to test each element before performing the action; can be null to process all elements
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexingBreakable(@Nullable Enumeration<E> enumeration, @Nullable BiFunction<Integer, ? super E, Boolean> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return;
        }
        int index = 0;
        while (enumeration.hasMoreElements()) {
            E item = enumeration.nextElement();
            if ((filter == null || filter.test(index, item)) && BooleanUtils.isNotTrue(action.apply(index, item))) {
                break;
            }
            index++;
        }
    }

    /**
     * Performs the given action for each element except the last one with index, then returns the last element
     *
     * @param enumeration the enumeration to iterate over
     * @param action the action to perform on each element except the last, with its index
     *
     * @return the last element of the enumeration, or {@code null} if the enumeration is null or empty
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachIndexingTailing(@Nullable Enumeration<E> enumeration, @Nullable BiConsumer<Integer, ? super E> action) {
        if (ObjectUtils.anyNull(enumeration, action)) {
            return null;
        }
        int index = 0;
        while (enumeration.hasMoreElements()) {
            E item = enumeration.nextElement();
            if (enumeration.hasMoreElements()) {
                action.accept(index, item);
            } else {
                return item;
            }
            index++;
        }
        return null;
    }

    /**
     * Returns the index-th value in the enumeration
     *
     * @param enumeration The source to get from
     * @param index The index to get
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
     * @param enumeration The enumeration to check
     * @param deepScan {@code true} means need to scan all the items; false means by the first nonnull item
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
            Object item = enumeration.nextElement();
            if (item == null) {
                continue;
            }
            if (!deepScan) {
                return item.getClass();
            }
            if (candidate == null) {
                candidate = item.getClass();
            } else {
                boolean assignable = false;
                if (ClassUtils.isAssignable(candidate, item.getClass())) {
                    candidate = item.getClass();
                    assignable = true;
                }
                if (!assignable) {
                    assignable = ClassUtils.isAssignable(item.getClass(), candidate);
                }
                if (!assignable) {
                    return null;
                }
            }
        }
        return candidate;
    }

    /**
     * Executes the action if the enumeration is empty
     *
     * @param enumeration the enumeration to check
     * @param action the action to execute if the enumeration is empty
     */
    public static void ifEmpty(@Nullable Enumeration<?> enumeration, @Nullable Runnable action) {
        if (isEmpty(enumeration) && action != null) {
            action.run();
        }
    }

    /**
     * Executes the absent action if the enumeration is empty, otherwise executes the present action with the enumeration
     *
     * @param enumeration the enumeration to check
     * @param absentAction the action to execute if the enumeration is empty
     * @param presentAction the action to execute with the enumeration if it is not empty
     */
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

    /**
     * Executes the action with the enumeration if it is not empty
     *
     * @param enumeration the enumeration to check
     * @param action the action to execute with the enumeration if it is not empty
     */
    public static <E, T extends Enumeration<E>> void ifNotEmpty(@Nullable T enumeration, @Nullable Consumer<? super T> action) {
        ifNotEmpty(enumeration, action, null);
    }

    /**
     * Executes the action with the enumeration if it is not empty and passes the filter
     *
     * @param enumeration the enumeration to check
     * @param action the action to execute with the enumeration if it is not empty
     * @param filter the predicate to test the enumeration before executing the action; can be null to always execute
     */
    public static <E, T extends Enumeration<E>> void ifNotEmpty(@Nullable T enumeration, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (isNotEmpty(enumeration) && action != null && (filter == null || filter.test(enumeration))) {
            action.accept(enumeration);
        }
    }

    /**
     * Executes the action if the enumeration is not empty
     *
     * @param enumeration the enumeration to check
     * @param action the action to execute if the enumeration is not empty
     */
    public static <E, T extends Enumeration<E>> void ifNotEmpty(@Nullable T enumeration, @Nullable Runnable action) {
        if (isNotEmpty(enumeration) && action != null) {
            action.run();
        }
    }

    /**
     * Executes the action with the enumeration if it contains exactly one element
     *
     * @param enumeration the enumeration to check
     * @param action the action to execute with the enumeration if it is a singleton
     */
    public static <T extends Enumeration<?>> void ifSingleton(@Nullable T enumeration, @Nullable Consumer<? super T> action) {
        ifSingletonOrElse(enumeration, action, null);
    }

    /**
     * Executes the action if the enumeration contains exactly one element
     *
     * @param enumeration the enumeration to check
     * @param action the action to execute if the enumeration is a singleton
     */
    public static <T extends Enumeration<?>> void ifSingleton(@Nullable T enumeration, @Nullable Runnable action) {
        ifSingletonOrElse(enumeration, action, null);
    }

    /**
     * Executes the present action with the enumeration if it contains exactly one element, otherwise executes the absent action
     *
     * @param enumeration the enumeration to check
     * @param presentAction the action to execute with the enumeration if it is a singleton
     * @param absentAction the action to execute with the enumeration if it is not a singleton
     */
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

    /**
     * Executes the present action if the enumeration contains exactly one element, otherwise executes the absent action
     *
     * @param enumeration the enumeration to check
     * @param presentAction the action to execute if the enumeration is a singleton
     * @param absentAction the action to execute if the enumeration is not a singleton
     */
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

    /**
     * Executes the action with the enumeration if it contains more than one element
     *
     * @param enumeration the enumeration to check
     * @param action the action to execute with the enumeration if it has multiple elements
     */
    public static <T extends Enumeration<?>> void ifMultitude(@Nullable T enumeration, @Nullable Consumer<? super T> action) {
        if (isMultitude(enumeration) && action != null) {
            action.accept(enumeration);
        }
    }

    /**
     * Executes the action if the enumeration contains more than one element
     *
     * @param enumeration the enumeration to check
     * @param action the action to execute if the enumeration has multiple elements
     */
    public static <T extends Enumeration<?>> void ifMultitude(@Nullable T enumeration, @Nullable Runnable action) {
        if (isMultitude(enumeration) && action != null) {
            action.run();
        }
    }

    /**
     * Checks if the enumeration is null or has no elements
     *
     * @param enumeration the enumeration to check
     *
     * @return {@code true} if the enumeration is null or empty, {@code false} otherwise
     */
    public static boolean isEmpty(@Nullable Enumeration<?> enumeration) {
        return enumeration == null || !enumeration.hasMoreElements();
    }

    /**
     * Checks if the enumeration is not null and has at least one element
     *
     * @param enumeration the enumeration to check
     *
     * @return {@code true} if the enumeration is not null and not empty, {@code false} otherwise
     */
    public static boolean isNotEmpty(@Nullable Enumeration<?> enumeration) {
        return !isEmpty(enumeration);
    }

    /**
     * Checks if the index is within the bounds of the enumeration
     *
     * @param enumeration the enumeration to check
     * @param index the index to validate
     *
     * @return {@code true} if the index is valid (0 &le; index &lt; size), {@code false} otherwise
     */
    public static boolean isIndexBound(@Nullable Enumeration<?> enumeration, int index) {
        return index >= 0 && index < size(enumeration);
    }

    /**
     * Checks if all given enumerations have the same size
     *
     * @param enumerations the enumerations to compare
     *
     * @return {@code true} if all enumerations have the same size, {@code false} otherwise or if less than 2 enumerations are provided
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameSize(@Nullable Enumeration<?>... enumerations) {
        if (ArrayUtils.getLength(enumerations) < 2) {
            return false;
        }
        int size = size(ArrayUtils.get(enumerations, 0));
        return Arrays.stream(enumerations).skip(1L).allMatch(item -> size(item) == size);
    }

    /**
     * Checks if the enumeration contains exactly one element
     *
     * @param enumeration the enumeration to check
     *
     * @return {@code true} if the enumeration has exactly one element, {@code false} otherwise
     */
    public static boolean isSingleton(@Nullable Enumeration<?> enumeration) {
        return size(enumeration) == 1;
    }

    /**
     * Checks if the enumeration contains more than one element
     *
     * @param enumeration the enumeration to check
     *
     * @return {@code true} if the enumeration has more than one element, {@code false} otherwise
     */
    public static boolean isMultitude(@Nullable Enumeration<?> enumeration) {
        return size(enumeration) > 1;
    }

    /**
     * Returns null if the enumeration is empty, otherwise returns the enumeration itself
     *
     * @param enumeration the enumeration to check
     *
     * @return {@code null} if the enumeration is empty, otherwise the enumeration
     */
    @Nullable
    public static <T extends Enumeration<?>> T nullIfEmpty(@Nullable T enumeration) {
        return isEmpty(enumeration) ? null : enumeration;
    }

    /**
     * Gets the number of elements in the enumeration
     *
     * <p>
     * Note: This method consumes the enumeration, so it can only be called once
     * </p>
     *
     * @param enumeration the enumeration to count
     *
     * @return the number of elements, or 0 if the enumeration is null
     */
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

    /**
     * Gets the maximum size among all given enumerations
     *
     * @param enumerations the enumerations to check
     *
     * @return the maximum size, or 0 if no enumerations are provided
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int maxSize(@Nullable Enumeration<?>... enumerations) {
        return ArrayUtils.isEmpty(enumerations) ? 0 : Arrays.stream(enumerations).mapToInt(EnumerationPlainWraps::size).max().orElse(0);
    }

    /**
     * Gets the sum of sizes of all given enumerations
     *
     * @param enumerations the enumerations to count
     *
     * @return the total number of elements across all enumerations, or 0 if no enumerations are provided
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int sumSize(@Nullable Enumeration<?>... enumerations) {
        return ArrayUtils.isEmpty(enumerations) ? 0 : Arrays.stream(enumerations).mapToInt(EnumerationPlainWraps::size).sum();
    }

    /**
     * Return an array containing all the items in the enumeration
     *
     * @param enumeration The enumeration to convert, with item that can not be primitive types
     *
     * @return an array containing all the items in the enumeration
     *
     * @see "org.springframework.util.CollectionUtils#toArray"
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <E> E[] toElementArray(@Nullable Enumeration<E> enumeration) {
        Class<E> clazz = (Class<E>) getComponentType(enumeration);
        return toElementArray(enumeration, clazz);
    }

    /**
     * Converts the enumeration to a typed array
     *
     * @param enumeration the enumeration to convert
     * @param elementType the type of elements in the array
     *
     * @return an array containing all elements from the enumeration, or {@code null} if either parameter is null
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Enumeration<E> enumeration, @Nullable Class<? super E> elementType) {
        return ObjectUtils.anyNull(enumeration, elementType) ? null : IteratorPlainWraps.toElementArray(new EnumerationIterator<>(enumeration), elementType);
    }

    /**
     * Converts the enumeration to a typed array using the provided target array
     *
     * @param enumeration the enumeration to convert
     * @param target the target array to store the elements
     *
     * @return the target array filled with elements from the enumeration, or {@code null} if either parameter is null or the result is empty
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Enumeration<E> enumeration, @Nullable E[] target) {
        if (ObjectUtils.anyNull(enumeration, target)) {
            return null;
        }
        List<E> result = toElementList(enumeration);
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(target);
    }

    /**
     * Converts the enumeration to a list
     *
     * @param enumeration the enumeration to convert
     *
     * @return a list containing all elements from the enumeration, or {@code null} if the enumeration is null or empty
     */
    @Nullable
    public static <E> List<E> toElementList(@Nullable Enumeration<E> enumeration) {
        if (enumeration == null) {
            return null;
        }
        List<E> result = Collections.list(enumeration);
        return result.isEmpty() ? null : result;
    }

    /**
     * Converts the enumeration to a set (preserving insertion order)
     *
     * @param enumeration the enumeration to convert
     *
     * @return a set containing all unique elements from the enumeration, or {@code null} if the enumeration is null or empty
     */
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

    /**
     * Converts the enumeration to an object array
     *
     * @param enumeration the enumeration to convert
     *
     * @return an object array containing all elements from the enumeration, or {@code null} if the enumeration is null or empty
     */
    @Nullable
    public static Object[] toObjectArray(@Nullable Enumeration<?> enumeration) {
        List<?> result = toObjectList(enumeration);
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

    /**
     * Converts the enumeration to a list of objects
     *
     * @param enumeration the enumeration to convert
     *
     * @return a list containing all elements from the enumeration, or {@code null} if the enumeration is null or empty
     */
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

    /**
     * Converts the string tokenizer to a list of strings
     *
     * @param tokenizer the string tokenizer to convert
     *
     * @return a list containing all tokens, or {@code null} if the tokenizer is null or has no tokens
     */
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
