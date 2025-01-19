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
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.util.Collection}
 *
 * @author David Hsing
 * @see java.util.Collections
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class CollectionPlainWraps {
    /**
     * Default load factor for {@link java.util.HashMap}/{@link java.util.LinkedHashMap} variants
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    @SafeVarargs
    public static <E> boolean addAll(@Nullable Collection<? super E> target, @Nullable E... sources) {
        return addAllIf(target, null, sources);
    }

    @SafeVarargs
    public static <E> boolean addAll(@Nullable Collection<E> target, @Nullable E[]... sources) {
        return addAllIf(target, null, sources);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAll(@Nullable Collection<E> target, @Nullable Collection<? extends E> source) {
        return ObjectUtils.allNotNull(target, source) && target.addAll(source);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAll(@Nullable Collection<E> target, @Nullable Iterable<? extends E> source) {
        if (ObjectUtils.anyNull(target, source)) {
            return false;
        }
        boolean changed = false;
        for (E element : source) {
            changed |= target.add(element);
        }
        return changed;
    }

    @SafeVarargs
    public static <E> boolean addAll(@Nullable Collection<E> target, @Nullable Iterable<? extends E>... sources) {
        return addAllIf(target, null, sources);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAll(@Nullable Collection<E> target, @Nullable Iterator<? extends E> source) {
        if (ObjectUtils.anyNull(target, source)) {
            return false;
        }
        boolean changed = false;
        while (source.hasNext()) {
            changed |= target.add(source.next());
        }
        return changed;
    }

    @SafeVarargs
    public static <E> boolean addAll(@Nullable Collection<E> target, @Nullable Iterator<? extends E>... sources) {
        return addAllIf(target, null, sources);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAll(@Nullable Collection<E> target, @Nullable Enumeration<? extends E> source) {
        if (ObjectUtils.anyNull(target, source)) {
            return false;
        }
        boolean changed = false;
        while (source.hasMoreElements()) {
            changed |= target.add(source.nextElement());
        }
        return changed;
    }

    @SafeVarargs
    public static <E> boolean addAll(@Nullable Collection<E> target, @Nullable Enumeration<? extends E>... sources) {
        return addAllIf(target, null, sources);
    }

    /**
     * Adds all elements to the target collection if matched the filter
     *
     * @param target the collection to add to
     * @param filter the filter to apply to the target collection and each element
     * @param sources the elements to add
     *
     * @return {@code true} if the collection was changed, {@code false} otherwise
     */
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAllIf(@Nullable Collection<? super E> target, @Nullable BiPredicate<Collection<? super E>, E> filter, @Nullable E... sources) {
        if (target == null || ArrayUtils.isEmpty(sources)) {
            return false;
        }
        boolean changed = false;
        for (E source : sources) {
            changed |= addIf(target, source, filter);
        }
        return changed;
    }

    /**
     * Adds all elements to the target collection if matched the filter
     *
     * @param target the target collection to merge the array into
     * @param filter the filter to apply to the target collection and each element
     * @param sources the element arrays to add
     *
     * @return {@code true} if the collection was changed, {@code false} otherwise
     */
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAllIf(@Nullable Collection<? super E> target, @Nullable BiPredicate<Collection<? super E>, E> filter, @Nullable E[]... sources) {
        if (target == null || ArrayUtils.isEmpty(sources)) {
            return false;
        }
        boolean changed = false;
        for (E[] source : sources) {
            changed |= addAllIf(target, filter, source);
        }
        return changed;
    }

    /**
     * Adds all elements to the target collection if matched the filter
     *
     * @param target the collection to add to
     * @param filter the filter to apply to the target collection and each element
     * @param source the element iterable to add
     *
     * @return {@code true} if the collection was changed, {@code false} otherwise
     */
    @SuppressWarnings({"unchecked", "DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAllIf(@Nullable Collection<E> target, @Nullable BiPredicate<Collection<? super E>, E> filter, @Nullable Iterable<? extends E> source) {
        if (ObjectUtils.anyNull(target, source)) {
            return false;
        }
        boolean changed = false;
        for (E element : source) {
            changed |= addIf(target, element, filter);
        }
        return changed;
    }

    /**
     * Adds all elements to the target collection if matched the filter
     *
     * @param target the collection to add to
     * @param filter the filter to apply to the target collection and each element
     * @param sources the element iterables to add
     *
     * @return {@code true} if the collection was changed, {@code false} otherwise
     */
    @SuppressWarnings({"unchecked", "DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAllIf(@Nullable Collection<E> target, @Nullable BiPredicate<Collection<? super E>, E> filter, @Nullable Iterable<? extends E>... sources) {
        if (target == null || ArrayUtils.isEmpty(sources)) {
            return false;
        }
        boolean changed = false;
        for (Iterable<? extends E> source : sources) {
            if (source == null) {
                continue;
            }
            for (E element : source) {
                changed |= addIf(target, element, filter);
            }
        }
        return changed;
    }

    /**
     * Adds all elements to the target collection if matched the filter
     *
     * @param target the collection to add to
     * @param filter the filter to apply to the target collection and each element
     * @param source the element iterator to add
     *
     * @return {@code true} if the collections was changed, {@code false} otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAllIf(@Nullable Collection<E> target, @Nullable BiPredicate<Collection<? super E>, E> filter, @Nullable Iterator<? extends E> source) {
        if (ObjectUtils.anyNull(target, source)) {
            return false;
        }
        boolean changed = false;
        while (source.hasNext()) {
            changed |= addIf(target, source.next(), filter);
        }
        return changed;
    }

    /**
     * Adds all elements to the target collection if matched the filter
     *
     * @param target the collection to add to
     * @param filter the filter to apply to the target collection and each element
     * @param sources the element iterators to add
     *
     * @return {@code true} if the collections was changed, {@code false} otherwise
     */
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAllIf(@Nullable Collection<E> target, @Nullable BiPredicate<Collection<? super E>, E> filter, @Nullable Iterator<? extends E>... sources) {
        if (target == null || ArrayUtils.isEmpty(sources)) {
            return false;
        }
        boolean changed = false;
        for (Iterator<? extends E> source : sources) {
            if (source == null) {
                continue;
            }
            while (source.hasNext()) {
                changed |= addIf(target, source.next(), filter);
            }
        }
        return changed;
    }

    /**
     * Adds all elements to the target collection if matched the filter
     *
     * @param target the collection to add to
     * @param filter the filter to apply to the target collection and each element
     * @param source the element enumeration to add
     *
     * @return {@code true} if the collections was changed, {@code false} otherwise
     */
    public static <E> boolean addAllIf(@Nullable Collection<E> target, @Nullable BiPredicate<Collection<? super E>, E> filter, Enumeration<? extends E> source) {
        if (ObjectUtils.anyNull(target, source)) {
            return false;
        }
        boolean changed = false;
        while (source.hasMoreElements()) {
            changed |= addIf(target, source.nextElement(), filter);
        }
        return changed;
    }

    /**
     * Adds all elements to the target collection if matched the filter
     *
     * @param target the collection to add to
     * @param filter the filter to apply to the target collection and each element
     * @param sources the element enumerations to add
     *
     * @return {@code true} if the collections was changed, {@code false} otherwise
     */
    @SafeVarargs
    public static <E> boolean addAllIf(@Nullable Collection<E> target, @Nullable BiPredicate<Collection<? super E>, E> filter, Enumeration<? extends E>... sources) {
        if (target == null || ArrayUtils.isEmpty(sources)) {
            return false;
        }
        boolean changed = false;
        for (Enumeration<? extends E> source : sources) {
            if (source == null) {
                continue;
            }
            while (source.hasMoreElements()) {
                changed |= addIf(target, source.nextElement(), filter);
            }
        }
        return changed;
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotNull(@Nullable Collection<? super E> target, @Nullable E... sources) {
        return addAllIf(target, (collection, element) -> element != null, sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable E[]... sources) {
        return addAllIf(target, (collection, element) -> element != null, sources);
    }

    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Iterable<? extends E> source) {
        return addAllIf(target, (collection, element) -> element != null, source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Iterable<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> element != null, sources);
    }

    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Iterator<? extends E> source) {
        return addAllIf(target, (collection, element) -> element != null, source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Iterator<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> element != null, sources);
    }

    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Enumeration<? extends E> source) {
        return addAllIf(target, (collection, element) -> element != null, source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Enumeration<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> element != null, sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<? super E> target, @Nullable E... sources) {
        return addAllIf(target, (collection, element) -> !contains(collection, element), sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable E[]... sources) {
        return addAllIf(target, (collection, element) -> !contains(collection, element), sources);
    }

    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Iterable<? extends E> source) {
        return addAllIf(target, (collection, element) -> !contains(collection, element), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Iterable<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> !contains(collection, element), sources);
    }

    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Iterator<? extends E> source) {
        return addAllIf(target, (collection, element) -> !contains(collection, element), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Iterator<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> !contains(collection, element), sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Enumeration<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> !contains(collection, element), sources);
    }

    @SafeVarargs
    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<? super E> target, @Nullable E... sources) {
        return addAllIf(target, (collection, element) -> StringUtils.isNotBlank(element), sources);
    }

    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Iterable<? extends E> source) {
        return addAllIf(target, (collection, element) -> StringUtils.isNotBlank(element), source);
    }

    @SafeVarargs
    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Iterable<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> StringUtils.isNotBlank(element), sources);
    }

    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Iterator<? extends E> source) {
        return addAllIf(target, (collection, element) -> StringUtils.isNotBlank(element), source);
    }

    @SafeVarargs
    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Iterator<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> StringUtils.isNotBlank(element), sources);
    }

    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Enumeration<? extends E> source) {
        return addAllIf(target, (collection, element) -> StringUtils.isNotBlank(element), source);
    }

    @SafeVarargs
    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Enumeration<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> StringUtils.isNotBlank(element), sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<? super E> target, @Nullable E... sources) {
        return addAllIf(target, (collection, element) -> ObjectUtils.isNotEmpty(element), sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable E[]... sources) {
        return addAllIf(target, (collection, element) -> ObjectUtils.isNotEmpty(element), sources);
    }

    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Iterable<? extends E> source) {
        return addAllIf(target, (collection, element) -> ObjectUtils.isNotEmpty(element), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Iterable<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> ObjectUtils.isNotEmpty(element), sources);
    }

    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Iterator<? extends E> source) {
        return addAllIf(target, (collection, element) -> ObjectUtils.isNotEmpty(element), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Iterator<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> ObjectUtils.isNotEmpty(element), sources);
    }

    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Enumeration<? extends E> source) {
        return addAllIf(target, (collection, element) -> ObjectUtils.isNotEmpty(element), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Enumeration<? extends E>... sources) {
        return addAllIf(target, (collection, element) -> ObjectUtils.isNotEmpty(element), sources);
    }

    public static <E> boolean addIf(@Nullable Collection<? super E> target, @Nullable E source, @Nullable BiPredicate<Collection<? super E>, E> filter) {
        return target != null && (filter == null || filter.test(target, source)) && target.add(source);
    }

    public static <E> boolean addIfNotNull(@Nullable Collection<? super E> target, @Nullable E source) {
        return addIf(target, source, (collection, element) -> element != null);
    }

    public static <E> boolean addIfNotContains(@Nullable Collection<? super E> target, @Nullable E source) {
        return addIf(target, source, (collection, element) -> !contains(target, element));
    }

    public static <E extends CharSequence> boolean addIfNotBlank(@Nullable Collection<? super E> target, @Nullable E source) {
        return addIf(target, source, (collection, element) -> StringUtils.isNotBlank(element));
    }

    public static <E> boolean addIfNotEmpty(@Nullable Collection<? super E> target, @Nullable E source) {
        return addIf(target, source, (collection, element) -> ObjectUtils.isNotEmpty(element));
    }

    public static boolean allNull(@Nullable Collection<?> collection) {
        return collection == null || collection.stream().allMatch(Objects::isNull);
    }

    public static boolean allNull(@Nullable Collection<?>... collections) {
        return collections == null || Arrays.stream(collections).allMatch(CollectionPlainWraps::allNull);
    }

    public static boolean allNotNull(@Nullable Collection<?> collection) {
        return collection != null && collection.stream().allMatch(Objects::nonNull);
    }

    public static boolean allNotNull(@Nullable Collection<?>... collections) {
        return collections != null && Arrays.stream(collections).allMatch(CollectionPlainWraps::allNotNull);
    }

    public static boolean allEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.stream().allMatch(ObjectUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable Collection<?>... collections) {
        return ArrayUtils.isEmpty(collections) || Arrays.stream(collections).allMatch(CollectionPlainWraps::allEmpty);
    }

    public static boolean allNotEmpty(@Nullable Collection<?> collection) {
        return collection != null && collection.stream().allMatch(ObjectUtils::isNotEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotEmpty(@Nullable Collection<?>... collections) {
        return ArrayUtils.isNotEmpty(collections) && Arrays.stream(collections).allMatch(CollectionPlainWraps::allNotEmpty);
    }

    public static boolean anyNull(@Nullable Collection<?> collection) {
        return collection == null || collection.stream().anyMatch(Objects::isNull);
    }

    public static boolean anyNull(@Nullable Collection<?>... collections) {
        return collections == null || Arrays.stream(collections).anyMatch(CollectionPlainWraps::anyNull);
    }

    public static boolean anyNotNull(@Nullable Collection<?> collection) {
        return collection != null && collection.stream().anyMatch(Objects::nonNull);
    }

    public static boolean anyNotNull(@Nullable Collection<?>... collections) {
        return collections != null && Arrays.stream(collections).anyMatch(CollectionPlainWraps::anyNotNull);
    }

    public static boolean anyEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.stream().anyMatch(ObjectUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable Collection<?>... collections) {
        return ArrayUtils.isEmpty(collections) || Arrays.stream(collections).anyMatch(CollectionPlainWraps::isEmpty);
    }

    public static boolean anyNotEmpty(@Nullable Collection<?> collection) {
        return collection != null && collection.stream().anyMatch(ObjectUtils::isNotEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotEmpty(@Nullable Collection<?>... collections) {
        return ArrayUtils.isNotEmpty(collections) && Arrays.stream(collections).anyMatch(CollectionPlainWraps::anyNotEmpty);
    }

    @Nullable
    public static <E> List<E> castToList(@Nullable Collection<?> sources, @Nullable Class<E> expectedType) {
        if (CollectionPlainWraps.isEmpty(sources) || expectedType == null || expectedType == Void.class) {
            return null;
        }
        return sources.stream().map(element -> ObjectUtilsWraps.castAs(element, expectedType)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Nullable
    public static <E> Set<E> castToSet(@Nullable Collection<?> sources, @Nullable Class<E> expectedType) {
        if (CollectionPlainWraps.isEmpty(sources) || expectedType == null || expectedType == Void.class) {
            return null;
        }
        return sources.stream().map(element -> ObjectUtilsWraps.castAs(element, expectedType)).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    /**
     * Returns {@code true} if the element is in collection
     *
     * @param collection the collection to check
     * @param element the element to look for
     *
     * @return {@code true} if the element is in collection
     */
    public static <E> boolean contains(@Nullable Collection<?> collection, @Nullable E element) {
        return collection != null && collection.contains(element);
    }

    @SafeVarargs
    public static <E> boolean containsAll(@Nullable Collection<? super E> target, @Nullable E... comparisons) {
        return containsAll(target, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Returns {@code true} if all elements of {@code comparison} is in the {@code target} collection
     *
     * @param target the target collection to check
     * @param comparison the elements to look for
     *
     * @return {@code true} if all elements of {@code comparison} is in the {@code target} collection
     */
    public static <E> boolean containsAll(@Nullable Collection<?> target, @Nullable Collection<?> comparison) {
        if (isEmpty(target) || size(target) < size(comparison)) {
            return false;
        }
        if (isEmpty(comparison)) {
            return true;
        }
        for (Object element : comparison) {
            if (!target.contains(element)) {
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static <E> boolean containsAny(@Nullable Collection<? super E> target, @Nullable E... comparisons) {
        return containsAny(target, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Returns {@code true} if any elements of {@code comparison} is in the {@code target} collection
     *
     * @param target the target collection to check
     * @param comparison the elements to look for
     *
     * @return {@code true} if any elements of {@code comparison} is in the {@code target} collection
     */
    public static <E> boolean containsAny(@Nullable Collection<?> target, @Nullable Collection<?> comparison) {
        if (isEmpty(target) || isEmpty(comparison)) {
            return false;
        }
        if (target.size() < comparison.size()) {
            for (Object element : target) {
                if (comparison.contains(element)) {
                    return true;
                }
            }
        } else {
            for (Object element : comparison) {
                if (target.contains(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsString(@Nullable Collection<? extends CharSequence> collection, @Nullable CharSequence sequence) {
        return isNotEmpty(collection) && collection.stream().anyMatch(element -> StringUtils.equals(element, sequence));
    }

    public static boolean containsStringIgnoreCase(@Nullable Collection<? extends CharSequence> collection, @Nullable CharSequence sequence) {
        return isNotEmpty(collection) && collection.stream().anyMatch(element -> StringUtils.equalsIgnoreCase(element, sequence));
    }

    public static boolean containsSubstring(@Nullable Collection<? extends CharSequence> collection, @Nullable CharSequence sequence) {
        return isNotEmpty(collection) && collection.stream().anyMatch(element -> StringUtils.contains(element, sequence));
    }

    public static boolean containsSubstringIgnoreCase(@Nullable Collection<? extends CharSequence> collection, @Nullable CharSequence sequence) {
        return isNotEmpty(collection) && collection.stream().anyMatch(element -> StringUtils.containsIgnoreCase(element, sequence));
    }

    public static <E> void forEach(@Nullable Collection<E> collection, @Nullable Consumer<? super E> action) {
        forEach(collection, action, null);
    }

    public static <E> void forEach(@Nullable Collection<E> collection, @Nullable Consumer<? super E> action, @Nullable Predicate<? super E> filter) {
        if (isEmpty(collection) || action == null) {
            return;
        }
        for (E element : collection) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static <E> void forEachBreakable(@Nullable Collection<E> collection, @Nullable Function<? super E, Boolean> action) {
        forEachBreakable(collection, action, null);
    }

    public static <E> void forEachBreakable(@Nullable Collection<E> collection, @Nullable Function<? super E, Boolean> action, @Nullable Predicate<? super E> filter) {
        if (isEmpty(collection) || action == null) {
            return;
        }
        for (E element : collection) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static <E> void forEachIndexing(@Nullable Collection<E> collection, @Nullable BiConsumer<Integer, ? super E> action) {
        forEachIndexing(collection, action, null);
    }

    public static <E> void forEachIndexing(@Nullable Collection<E> collection, @Nullable BiConsumer<Integer, ? super E> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (isEmpty(collection) || action == null) {
            return;
        }
        int index = 0;
        for (E element : collection) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static <E> void forEachIndexingBreakable(@Nullable Collection<E> collection, @Nullable BiFunction<Integer, ? super E, Boolean> action) {
        forEachIndexingBreakable(collection, action, null);
    }

    public static <E> void forEachIndexingBreakable(@Nullable Collection<E> collection, @Nullable BiFunction<Integer, ? super E, Boolean> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (isEmpty(collection) || action == null) {
            return;
        }
        int index = 0;
        for (E element : collection) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    @Nullable
    public static <E> E forEachIndexingTailing(@Nullable Collection<E> collection, @Nullable BiConsumer<Integer, ? super E> action) {
        if (isEmpty(collection) || action == null) {
            return null;
        }
        int index = 0, size = collection.size();
        for (E element : collection) {
            if (index < size - 1) {
                action.accept(index, element);
            } else {
                return element;
            }
            index++;
        }
        return null;
    }

    @Nullable
    public static <E> E forEachTailing(@Nullable Collection<E> collection, @Nullable Consumer<? super E> action) {
        if (isEmpty(collection) || action == null) {
            return null;
        }
        int index = 0, size = collection.size();
        for (E element : collection) {
            if (index < size - 1) {
                action.accept(element);
            } else {
                return element;
            }
            index++;
        }
        return null;
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Collection<?> collection) {
        return getComponentType(collection, false);
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Collection<?> collection, boolean deepScan) {
        return (collection == null) ? null : IteratorPlainWraps.getComponentType(collection.iterator(), deepScan);
    }

    public static <T extends Collection<?>> void ifEmpty(@Nullable T collection, @Nullable Consumer<? super T> action) {
        ifEmptyOrElse(collection, action, null);
    }

    public static <T extends Collection<?>> void ifEmpty(@Nullable T collection, @Nullable Runnable action) {
        ifEmptyOrElse(collection, action, null);
    }

    public static <T extends Collection<?>> void ifEmptyOrElse(@Nullable T collection, @Nullable Consumer<? super T> absentAction, @Nullable Consumer<? super T> presentAction) {
        if (isEmpty(collection)) {
            if (absentAction != null) {
                absentAction.accept(collection);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(collection);
            }
        }
    }

    public static <T extends Collection<?>> void ifEmptyOrElse(@Nullable T collection, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (isEmpty(collection)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static <T extends Collection<?>> void ifNotEmpty(@Nullable T collection, @Nullable Consumer<? super T> action) {
        if (isNotEmpty(collection) && action != null) {
            action.accept(collection);
        }
    }

    public static <T extends Collection<?>> void ifNotEmpty(@Nullable T collection, @Nullable Runnable action) {
        if (isNotEmpty(collection) && action != null) {
            action.run();
        }
    }

    public static <T extends Collection<?>> void ifSingleton(@Nullable T collection, @Nullable Consumer<? super T> action) {
        ifSingletonOrElse(collection, action, null);
    }

    public static <T extends Collection<?>> void ifSingleton(@Nullable T collection, @Nullable Runnable action) {
        ifSingletonOrElse(collection, action, null);
    }

    public static <T extends Collection<?>> void ifSingletonOrElse(@Nullable T collection, @Nullable Consumer<? super T> presentAction, @Nullable Consumer<? super T> absentAction) {
        if (isSingleton(collection)) {
            if (presentAction != null) {
                presentAction.accept(collection);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(collection);
            }
        }
    }

    public static <T extends Collection<?>> void ifSingletonOrElse(@Nullable T collection, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isSingleton(collection)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static <T extends Collection<?>> void ifMultitude(@Nullable T collection, @Nullable Consumer<? super T> action) {
        if (isMultitude(collection) && action != null) {
            action.accept(collection);
        }
    }

    public static <T extends Collection<?>> void ifMultitude(@Nullable T collection, @Nullable Runnable action) {
        if (isMultitude(collection) && action != null) {
            action.run();
        }
    }

    public static boolean isDistinct(@Nullable Collection<?> collection) {
        return isDistinct(collection, true);
    }

    public static boolean isDistinct(@Nullable Collection<?> collection, boolean ignoreNull) {
        if (isEmpty(collection)) {
            return false;
        }
        if (ignoreNull) {
            List<?> list = collection.stream().filter(Objects::nonNull).collect(Collectors.toList());
            Set<?> set = new LinkedHashSet<>(list);
            return isSameSize(list, set);
        } else {
            Set<?> set = new LinkedHashSet<>(collection);
            return isSameSize(collection, set);
        }
    }

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isIndexBound(@Nullable Collection<?> collection, int index) {
        return isIndexBound(collection, index, false);
    }

    /**
     * Returns true if the index object exists in the collection
     *
     * @param collection the source to check
     * @param index the index to detect
     * @param adding indicates whether trying to add an element to the source collection or not
     *
     * @return true if the index object exists in the collection
     *
     * @see ArrayUtils#isArrayIndexValid
     */
    public static boolean isIndexBound(@Nullable Collection<?> collection, int index, boolean adding) {
        return index >= 0 && index < (size(collection) + (adding ? 1 : 0));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameSize(@Nullable Collection<?>... collections) {
        if (ArrayUtils.getLength(collections) < 2) {
            return false;
        }
        int size = size(ArrayUtils.get(collections, 0));
        return Arrays.stream(collections).skip(1L).allMatch(element -> size(element) == size);
    }

    public static boolean isSingleton(@Nullable Collection<?> collection) {
        return size(collection) == 1;
    }

    public static boolean isMultitude(@Nullable Collection<?> collection) {
        return size(collection) > 1;
    }

    @Nonnull
    public static <E> ArrayList<E> newArrayListIfNull(@Nullable Collection<E> collection) {
        return (collection instanceof ArrayList<E> alias) ? alias : (collection == null ? new ArrayList<>() : new ArrayList<>(collection));
    }

    @Nonnull
    public static <E> ArrayList<E> newArrayListSizing(int size) {
        return (size < 0) ? new ArrayList<>() : new ArrayList<>((int) ((float) size / DEFAULT_LOAD_FACTOR));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> ArrayList<E> newArrayListWithin(@Nullable E... elements) {
        return newArrayListWithin(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> ArrayList<E> newArrayListWithin(boolean emptyAsNull, @Nullable E... elements) {
        return newArrayListWithinAll(emptyAsNull, ArrayUtilsWraps.asList(elements));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> ArrayList<E> newArrayListWithinAll(@Nullable Iterable<? extends E>... elements) {
        return newArrayListWithinAll(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> ArrayList<E> newArrayListWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... elements) {
        ArrayList<E> result = new ArrayList<>();
        addAll(result, elements);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListIfNull(@Nullable Collection<E> collection) {
        return (collection instanceof CopyOnWriteArrayList<E> alias) ? alias : (collection == null ? new CopyOnWriteArrayList<>() : new CopyOnWriteArrayList<>(collection));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListWithin(@Nullable E... elements) {
        return newCopyOnWriteArrayListWithin(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListWithin(boolean emptyAsNull, @Nullable E... elements) {
        return newCopyOnWriteArrayListWithinAll(emptyAsNull, ArrayUtilsWraps.asList(elements));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListWithinAll(@Nullable Iterable<? extends E>... elements) {
        return newCopyOnWriteArrayListWithinAll(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... elements) {
        CopyOnWriteArrayList<E> result = new CopyOnWriteArrayList<>();
        addAll(result, elements);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <E> HashSet<E> newHashSetIfNull(@Nullable Collection<E> collection) {
        return (collection instanceof HashSet<E> alias) ? alias : (collection == null ? new HashSet<>() : new HashSet<>(collection));
    }

    @Nonnull
    public static <E> HashSet<E> newHashSetSizing(int size) {
        return (size < 0) ? new HashSet<>() : new HashSet<>((int) ((float) size / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> HashSet<E> newHashSetWithin(@Nullable E... elements) {
        return newHashSetWithin(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> HashSet<E> newHashSetWithin(boolean emptyAsNull, @Nullable E... elements) {
        return newHashSetWithinAll(emptyAsNull, ArrayUtilsWraps.asList(elements));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> HashSet<E> newHashSetWithinAll(@Nullable Iterable<? extends E>... elements) {
        return newHashSetWithinAll(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> HashSet<E> newHashSetWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... elements) {
        HashSet<E> result = new HashSet<>();
        addAll(result, elements);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <E> LinkedHashSet<E> newLinkedHashSetIfNull(@Nullable Collection<E> collection) {
        return (collection instanceof LinkedHashSet<E> alias) ? alias : (collection == null ? new LinkedHashSet<>() : new LinkedHashSet<>(collection));
    }

    @Nonnull
    public static <E> LinkedHashSet<E> newLinkedHashSetSizing(int size) {
        return (size < 0) ? new LinkedHashSet<>() : new LinkedHashSet<>((int) ((float) size / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> LinkedHashSet<E> newLinkedHashSetWithin(@Nullable E... elements) {
        return newLinkedHashSetWithin(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> LinkedHashSet<E> newLinkedHashSetWithin(boolean emptyAsNull, @Nullable E... elements) {
        return newLinkedHashSetWithinAll(emptyAsNull, ArrayUtilsWraps.asList(elements));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> LinkedHashSet<E> newLinkedHashSetWithinAll(@Nullable Iterable<? extends E>... elements) {
        return newLinkedHashSetWithinAll(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> LinkedHashSet<E> newLinkedHashSetWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... elements) {
        LinkedHashSet<E> result = new LinkedHashSet<>();
        addAll(result, elements);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <E> LinkedList<E> newLinkedListIfNull(@Nullable Collection<E> collection) {
        return (collection instanceof LinkedList<E> alias) ? alias : (collection == null ? new LinkedList<>() : new LinkedList<>(collection));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> LinkedList<E> newLinkedListWithin(@Nullable E... elements) {
        return newLinkedListWithin(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> LinkedList<E> newLinkedListWithin(boolean emptyAsNull, @Nullable E... elements) {
        return newLinkedListWithinAll(emptyAsNull, ArrayUtilsWraps.asList(elements));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> LinkedList<E> newLinkedListWithinAll(@Nullable Iterable<? extends E>... elements) {
        return newLinkedListWithinAll(false, elements);
    }

    @Nullable
    @SafeVarargs
    public static <E> LinkedList<E> newLinkedListWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... elements) {
        LinkedList<E> result = new LinkedList<>();
        addAll(result, elements);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nullable
    public static <T extends Collection<?>> T nullIfEmpty(@Nullable T collection) {
        return isEmpty(collection) ? null : collection;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean removeIf(@Nullable Collection<E> collection, @Nullable Predicate<? super E> filter) {
        return ObjectUtils.allNotNull(collection, filter) && collection.removeIf(filter);
    }

    public static int size(@Nullable Collection<?> collection) {
        return (collection == null) ? 0 : collection.size();
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int maxSize(@Nullable Collection<?>... collections) {
        return ArrayUtils.isEmpty(collections) ? 0 : Arrays.stream(collections).mapToInt(CollectionPlainWraps::size).max().orElse(0);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int sumSize(@Nullable Collection<?>... collections) {
        return ArrayUtils.isEmpty(collections) ? 0 : Arrays.stream(collections).mapToInt(CollectionPlainWraps::size).sum();
    }

    /**
     * Return an array containing all the elements in the collection
     *
     * @param collection the collection to convert, with element that can not be primitive types
     *
     * @return an array containing all the elements in the collection
     *
     * @see org.apache.commons.lang3.ArrayUtils#addAll
     * @see "org.springframework.util.CollectionUtils#toArray"
     * @see "org.springframework.util.StringUtils#toStringArray"
     * @see "org.springframework.util.CollectionUtils#findCommonElementType"
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <E> E[] toElementArray(@Nullable Collection<E> collection) {
        Class<E> clazz = (Class<E>) getComponentType(collection);
        return toElementArray(collection, clazz);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] toElementArray(@Nullable Collection<E> collection, @Nullable Class<? super E> elementType) {
        return ObjectUtils.anyNull(collection, elementType) ? null : IteratorPlainWraps.toElementArray(collection.iterator(), elementType);
    }

    @Nullable
    public static <E> E[] toElementArray(@Nullable Collection<E> collection, @Nullable E[] target) {
        return (isEmpty(collection) || target == null) ? null : collection.toArray(target);
    }

    @Nullable
    public static <E> Enumeration<E> toElementEnumeration(@Nullable Collection<E> collection) {
        return isEmpty(collection) ? null : Collections.enumeration(collection);
    }

    @Nullable
    public static Object[] toObjectArray(@Nullable Collection<?> collection) {
        return (collection == null) ? null : collection.toArray();
    }

    @SafeVarargs
    public static <E> Collection<E> unmodifiableCollection(@Nullable E... elements) {
        return unmodifiableCollection(false, elements);
    }

    @SafeVarargs
    public static <E> Collection<E> unmodifiableCollection(boolean emptyAsNull, @Nullable E... elements) {
        return unmodifiableCollection(emptyAsNull, ArrayUtilsWraps.asList(elements));
    }

    @Nullable
    public static <E> Collection<E> unmodifiableCollection(@Nullable Collection<? extends E> collection) {
        return unmodifiableCollection(false, collection);
    }

    @Nullable
    public static <E> Collection<E> unmodifiableCollection(boolean emptyAsNull, @Nullable Collection<? extends E> collection) {
        return (collection == null || (emptyAsNull && collection.isEmpty())) ? null : Collections.unmodifiableCollection(collection);
    }

    @SafeVarargs
    public static <E> List<E> unmodifiableList(@Nullable E... elements) {
        return unmodifiableList(false, elements);
    }

    @SafeVarargs
    public static <E> List<E> unmodifiableList(boolean emptyAsNull, @Nullable E... elements) {
        return unmodifiableList(emptyAsNull, ArrayUtilsWraps.asList(elements));
    }

    @Nullable
    public static <E> List<E> unmodifiableList(@Nullable List<? extends E> list) {
        return unmodifiableList(false, list);
    }

    @Nullable
    public static <E> List<E> unmodifiableList(boolean emptyAsNull, @Nullable List<? extends E> list) {
        return (list == null || (emptyAsNull && list.isEmpty())) ? null : Collections.unmodifiableList(list);
    }

    @SafeVarargs
    public static <E> Set<E> unmodifiableSet(@Nullable E... elements) {
        return unmodifiableSet(false, elements);
    }

    @SafeVarargs
    public static <E> Set<E> unmodifiableSet(boolean emptyAsNull, @Nullable E... elements) {
        return unmodifiableSet(emptyAsNull, ArrayUtilsWraps.asSet(elements));
    }

    @Nullable
    public static <E> Set<E> unmodifiableSet(@Nullable Set<? extends E> set) {
        return unmodifiableSet(false, set);
    }

    @Nullable
    public static <E> Set<E> unmodifiableSet(boolean emptyAsNull, @Nullable Set<? extends E> set) {
        return (set == null || (emptyAsNull && set.isEmpty())) ? null : Collections.unmodifiableSet(set);
    }

    @Nullable
    public static <K, V> Map<K, V> unmodifiableMap(@Nullable Map<? extends K, ? extends V> map) {
        return unmodifiableMap(false, map);
    }

    @Nullable
    public static <K, V> Map<K, V> unmodifiableMap(boolean emptyAsNull, @Nullable Map<? extends K, ? extends V> map) {
        return (map == null || (emptyAsNull && map.isEmpty())) ? null : Collections.unmodifiableMap(map);
    }
}
