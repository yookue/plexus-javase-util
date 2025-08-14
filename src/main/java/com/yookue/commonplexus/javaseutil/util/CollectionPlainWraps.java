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
 *
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
        for (E item : source) {
            changed |= target.add(item);
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
     * Adds all items to the target collection if matched the filter
     *
     * @param target The collection to add to
     * @param filter The filter to apply to the target collection and each item
     * @param sources The items to add
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
     * Adds all items to the target collection if matched the filter
     *
     * @param target The target collection to merge the array into
     * @param filter The filter to apply to the target collection and each item
     * @param sources The item arrays to add
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
     * Adds all items to the target collection if matched the filter
     *
     * @param target The collection to add to
     * @param filter The filter to apply to the target collection and each item
     * @param source The item iterable to add
     *
     * @return {@code true} if the collection was changed, {@code false} otherwise
     */
    @SuppressWarnings({"unchecked", "DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean addAllIf(@Nullable Collection<E> target, @Nullable BiPredicate<Collection<? super E>, E> filter, @Nullable Iterable<? extends E> source) {
        if (ObjectUtils.anyNull(target, source)) {
            return false;
        }
        boolean changed = false;
        for (E item : source) {
            changed |= addIf(target, item, filter);
        }
        return changed;
    }

    /**
     * Adds all items to the target collection if matched the filter
     *
     * @param target The collection to add to
     * @param filter The filter to apply to the target collection and each item
     * @param sources The item iterables to add
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
            for (E item : source) {
                changed |= addIf(target, item, filter);
            }
        }
        return changed;
    }

    /**
     * Adds all items to the target collection if matched the filter
     *
     * @param target The collection to add to
     * @param filter The filter to apply to the target collection and each item
     * @param source The item iterator to add
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
     * Adds all items to the target collection if matched the filter
     *
     * @param target The collection to add to
     * @param filter The filter to apply to the target collection and each item
     * @param sources The item iterators to add
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
     * Adds all items to the target collection if matched the filter
     *
     * @param target The collection to add to
     * @param filter The filter to apply to the target collection and each item
     * @param source The item enumeration to add
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
     * Adds all items to the target collection if matched the filter
     *
     * @param target The collection to add to
     * @param filter The filter to apply to the target collection and each item
     * @param sources The item enumerations to add
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
        return addAllIf(target, (collection, item) -> item != null, sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable E[]... sources) {
        return addAllIf(target, (collection, item) -> item != null, sources);
    }

    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Iterable<? extends E> source) {
        return addAllIf(target, (collection, item) -> item != null, source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Iterable<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> item != null, sources);
    }

    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Iterator<? extends E> source) {
        return addAllIf(target, (collection, item) -> item != null, source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Iterator<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> item != null, sources);
    }

    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Enumeration<? extends E> source) {
        return addAllIf(target, (collection, item) -> item != null, source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotNull(@Nullable Collection<E> target, @Nullable Enumeration<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> item != null, sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<? super E> target, @Nullable E... sources) {
        return addAllIf(target, (collection, item) -> !contains(collection, item), sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable E[]... sources) {
        return addAllIf(target, (collection, item) -> !contains(collection, item), sources);
    }

    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Iterable<? extends E> source) {
        return addAllIf(target, (collection, item) -> !contains(collection, item), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Iterable<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> !contains(collection, item), sources);
    }

    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Iterator<? extends E> source) {
        return addAllIf(target, (collection, item) -> !contains(collection, item), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Iterator<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> !contains(collection, item), sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotContains(@Nullable Collection<E> target, @Nullable Enumeration<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> !contains(collection, item), sources);
    }

    @SafeVarargs
    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<? super E> target, @Nullable E... sources) {
        return addAllIf(target, (collection, item) -> StringUtils.isNotBlank(item), sources);
    }

    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Iterable<? extends E> source) {
        return addAllIf(target, (collection, item) -> StringUtils.isNotBlank(item), source);
    }

    @SafeVarargs
    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Iterable<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> StringUtils.isNotBlank(item), sources);
    }

    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Iterator<? extends E> source) {
        return addAllIf(target, (collection, item) -> StringUtils.isNotBlank(item), source);
    }

    @SafeVarargs
    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Iterator<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> StringUtils.isNotBlank(item), sources);
    }

    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Enumeration<? extends E> source) {
        return addAllIf(target, (collection, item) -> StringUtils.isNotBlank(item), source);
    }

    @SafeVarargs
    public static <E extends CharSequence> boolean addAllIfNotBlank(@Nullable Collection<E> target, @Nullable Enumeration<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> StringUtils.isNotBlank(item), sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<? super E> target, @Nullable E... sources) {
        return addAllIf(target, (collection, item) -> ObjectUtils.isNotEmpty(item), sources);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable E[]... sources) {
        return addAllIf(target, (collection, item) -> ObjectUtils.isNotEmpty(item), sources);
    }

    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Iterable<? extends E> source) {
        return addAllIf(target, (collection, item) -> ObjectUtils.isNotEmpty(item), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Iterable<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> ObjectUtils.isNotEmpty(item), sources);
    }

    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Iterator<? extends E> source) {
        return addAllIf(target, (collection, item) -> ObjectUtils.isNotEmpty(item), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Iterator<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> ObjectUtils.isNotEmpty(item), sources);
    }

    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Enumeration<? extends E> source) {
        return addAllIf(target, (collection, item) -> ObjectUtils.isNotEmpty(item), source);
    }

    @SafeVarargs
    public static <E> boolean addAllIfNotEmpty(@Nullable Collection<E> target, @Nullable Enumeration<? extends E>... sources) {
        return addAllIf(target, (collection, item) -> ObjectUtils.isNotEmpty(item), sources);
    }

    public static <E> boolean addIf(@Nullable Collection<? super E> target, @Nullable E source, @Nullable BiPredicate<Collection<? super E>, E> filter) {
        return target != null && (filter == null || filter.test(target, source)) && target.add(source);
    }

    public static <E> boolean addIfNotNull(@Nullable Collection<? super E> target, @Nullable E source) {
        return addIf(target, source, (collection, item) -> item != null);
    }

    public static <E> boolean addIfNotContains(@Nullable Collection<? super E> target, @Nullable E source) {
        return addIf(target, source, (collection, item) -> !contains(target, item));
    }

    public static <E extends CharSequence> boolean addIfNotBlank(@Nullable Collection<? super E> target, @Nullable E source) {
        return addIf(target, source, (collection, item) -> StringUtils.isNotBlank(item));
    }

    public static <E> boolean addIfNotEmpty(@Nullable Collection<? super E> target, @Nullable E source) {
        return addIf(target, source, (collection, item) -> ObjectUtils.isNotEmpty(item));
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
    public static <E> List<E> castToList(@Nullable Collection<?> sources, @Nullable Class<E> expectType) {
        if (CollectionPlainWraps.isEmpty(sources) || expectType == null || expectType == Void.class) {
            return null;
        }
        return sources.stream().map(item -> ObjectUtilsWraps.castAs(item, expectType)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Nullable
    public static <E> Set<E> castToSet(@Nullable Collection<?> sources, @Nullable Class<E> expectType) {
        if (CollectionPlainWraps.isEmpty(sources) || expectType == null || expectType == Void.class) {
            return null;
        }
        return sources.stream().map(item -> ObjectUtilsWraps.castAs(item, expectType)).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    /**
     * Returns {@code true} if the item is in collection
     *
     * @param collection The collection to check
     * @param item The item to look for
     *
     * @return {@code true} if the item is in collection
     */
    public static <E> boolean contains(@Nullable Collection<?> collection, @Nullable E item) {
        return collection != null && collection.contains(item);
    }

    @SafeVarargs
    public static <E> boolean containsAll(@Nullable Collection<? super E> target, @Nullable E... comparisons) {
        return containsAll(target, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Returns {@code true} if all items of {@code comparison} is in the {@code target} collection
     *
     * @param target The target collection to check
     * @param comparison The items to look for
     *
     * @return {@code true} if all items of {@code comparison} is in the {@code target} collection
     */
    public static <E> boolean containsAll(@Nullable Collection<?> target, @Nullable Collection<?> comparison) {
        if (isEmpty(target) || size(target) < size(comparison)) {
            return false;
        }
        if (isEmpty(comparison)) {
            return true;
        }
        for (Object item : comparison) {
            if (!target.contains(item)) {
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
     * Returns {@code true} if any items of {@code comparison} is in the {@code target} collection
     *
     * @param target The target collection to check
     * @param comparison The items to look for
     *
     * @return {@code true} if any items of {@code comparison} is in the {@code target} collection
     */
    public static <E> boolean containsAny(@Nullable Collection<?> target, @Nullable Collection<?> comparison) {
        if (isEmpty(target) || isEmpty(comparison)) {
            return false;
        }
        if (target.size() < comparison.size()) {
            for (Object item : target) {
                if (comparison.contains(item)) {
                    return true;
                }
            }
        } else {
            for (Object item : comparison) {
                if (target.contains(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsString(@Nullable Collection<? extends CharSequence> collection, @Nullable CharSequence sequence) {
        return isNotEmpty(collection) && collection.stream().anyMatch(item -> StringUtils.equals(item, sequence));
    }

    public static boolean containsStringIgnoreCase(@Nullable Collection<? extends CharSequence> collection, @Nullable CharSequence sequence) {
        return isNotEmpty(collection) && collection.stream().anyMatch(item -> StringUtils.equalsIgnoreCase(item, sequence));
    }

    public static boolean containsSubstring(@Nullable Collection<? extends CharSequence> collection, @Nullable CharSequence sequence) {
        return isNotEmpty(collection) && collection.stream().anyMatch(item -> StringUtils.contains(item, sequence));
    }

    public static boolean containsSubstringIgnoreCase(@Nullable Collection<? extends CharSequence> collection, @Nullable CharSequence sequence) {
        return isNotEmpty(collection) && collection.stream().anyMatch(item -> StringUtils.containsIgnoreCase(item, sequence));
    }

    public static <E> void forEach(@Nullable Collection<E> collection, @Nullable Consumer<? super E> action) {
        forEach(collection, action, null);
    }

    public static <E> void forEach(@Nullable Collection<E> collection, @Nullable Consumer<? super E> action, @Nullable Predicate<? super E> filter) {
        if (isEmpty(collection) || action == null) {
            return;
        }
        for (E item : collection) {
            if (filter == null || filter.test(item)) {
                action.accept(item);
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
        for (E item : collection) {
            if ((filter == null || filter.test(item)) && BooleanUtils.isNotTrue(action.apply(item))) {
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
        for (E item : collection) {
            if (filter == null || filter.test(index, item)) {
                action.accept(index, item);
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
        for (E item : collection) {
            if ((filter == null || filter.test(index, item)) && BooleanUtils.isNotTrue(action.apply(index, item))) {
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
        for (E item : collection) {
            if (index < size - 1) {
                action.accept(index, item);
            } else {
                return item;
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
        for (E item : collection) {
            if (index < size - 1) {
                action.accept(item);
            } else {
                return item;
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
     * @param collection The source to check
     * @param index The index to detect
     * @param adding indicates whether trying to add an item to the source collection or not
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
        return Arrays.stream(collections).skip(1L).allMatch(item -> size(item) == size);
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
    public static <E> ArrayList<E> newArrayListWithin(@Nullable Collection<E> collection) {
        return (collection == null) ? new ArrayList<>() : new ArrayList<>(collection);
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> ArrayList<E> newArrayListWithin(@Nullable E... items) {
        return newArrayListWithin(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> ArrayList<E> newArrayListWithin(boolean emptyAsNull, @Nullable E... items) {
        return newArrayListWithinAll(emptyAsNull, ArrayUtilsWraps.asList(items));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> ArrayList<E> newArrayListWithinAll(@Nullable Iterable<? extends E>... items) {
        return newArrayListWithinAll(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> ArrayList<E> newArrayListWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... items) {
        ArrayList<E> result = new ArrayList<>();
        addAll(result, items);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListIfNull(@Nullable Collection<E> collection) {
        return (collection instanceof CopyOnWriteArrayList<E> alias) ? alias : (collection == null ? new CopyOnWriteArrayList<>() : new CopyOnWriteArrayList<>(collection));
    }

    @Nonnull
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListWithin(@Nullable Collection<E> collection) {
        return (collection == null) ? new CopyOnWriteArrayList<>() : new CopyOnWriteArrayList<>(collection);
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListWithin(@Nullable E... items) {
        return newCopyOnWriteArrayListWithin(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListWithin(boolean emptyAsNull, @Nullable E... items) {
        return newCopyOnWriteArrayListWithinAll(emptyAsNull, ArrayUtilsWraps.asList(items));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListWithinAll(@Nullable Iterable<? extends E>... items) {
        return newCopyOnWriteArrayListWithinAll(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayListWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... items) {
        CopyOnWriteArrayList<E> result = new CopyOnWriteArrayList<>();
        addAll(result, items);
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
    public static <E> HashSet<E> newHashSetWithin(@Nullable Collection<E> collection) {
        return (collection == null) ? new HashSet<>() : new HashSet<>(collection);
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> HashSet<E> newHashSetWithin(@Nullable E... items) {
        return newHashSetWithin(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> HashSet<E> newHashSetWithin(boolean emptyAsNull, @Nullable E... items) {
        return newHashSetWithinAll(emptyAsNull, ArrayUtilsWraps.asList(items));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> HashSet<E> newHashSetWithinAll(@Nullable Iterable<? extends E>... items) {
        return newHashSetWithinAll(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> HashSet<E> newHashSetWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... items) {
        HashSet<E> result = new HashSet<>();
        addAll(result, items);
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
    public static <E> LinkedHashSet<E> newLinkedHashSetWithin(@Nullable Collection<E> collection) {
        return (collection == null) ? new LinkedHashSet<>() : new LinkedHashSet<>(collection);
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> LinkedHashSet<E> newLinkedHashSetWithin(@Nullable E... items) {
        return newLinkedHashSetWithin(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> LinkedHashSet<E> newLinkedHashSetWithin(boolean emptyAsNull, @Nullable E... items) {
        return newLinkedHashSetWithinAll(emptyAsNull, ArrayUtilsWraps.asList(items));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> LinkedHashSet<E> newLinkedHashSetWithinAll(@Nullable Iterable<? extends E>... items) {
        return newLinkedHashSetWithinAll(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> LinkedHashSet<E> newLinkedHashSetWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... items) {
        LinkedHashSet<E> result = new LinkedHashSet<>();
        addAll(result, items);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <E> LinkedList<E> newLinkedListIfNull(@Nullable Collection<E> collection) {
        return (collection instanceof LinkedList<E> alias) ? alias : (collection == null ? new LinkedList<>() : new LinkedList<>(collection));
    }

    @Nonnull
    public static <E> LinkedList<E> newLinkedListWithin(@Nullable Collection<E> collection) {
        return (collection == null) ? new LinkedList<>() : new LinkedList<>(collection);
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> LinkedList<E> newLinkedListWithin(@Nullable E... items) {
        return newLinkedListWithin(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> LinkedList<E> newLinkedListWithin(boolean emptyAsNull, @Nullable E... items) {
        return newLinkedListWithinAll(emptyAsNull, ArrayUtilsWraps.asList(items));
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> LinkedList<E> newLinkedListWithinAll(@Nullable Iterable<? extends E>... items) {
        return newLinkedListWithinAll(false, items);
    }

    @Nullable
    @SafeVarargs
    public static <E> LinkedList<E> newLinkedListWithinAll(boolean emptyAsNull, @Nullable Iterable<? extends E>... items) {
        LinkedList<E> result = new LinkedList<>();
        addAll(result, items);
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
     * Return an array containing all the items in the collection
     *
     * @param collection The collection to convert, with item that can not be primitive types
     *
     * @return an array containing all the items in the collection
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
    public static <E> Collection<E> unmodifiableCollection(@Nullable E... items) {
        return unmodifiableCollection(false, items);
    }

    @SafeVarargs
    public static <E> Collection<E> unmodifiableCollection(boolean emptyAsNull, @Nullable E... items) {
        return unmodifiableCollection(emptyAsNull, ArrayUtilsWraps.asList(items));
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
    public static <E> List<E> unmodifiableList(@Nullable E... items) {
        return unmodifiableList(false, items);
    }

    @SafeVarargs
    public static <E> List<E> unmodifiableList(boolean emptyAsNull, @Nullable E... items) {
        return unmodifiableList(emptyAsNull, ArrayUtilsWraps.asList(items));
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
    public static <E> Set<E> unmodifiableSet(@Nullable E... items) {
        return unmodifiableSet(false, items);
    }

    @SafeVarargs
    public static <E> Set<E> unmodifiableSet(boolean emptyAsNull, @Nullable E... items) {
        return unmodifiableSet(emptyAsNull, ArrayUtilsWraps.asSet(items));
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
