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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.ArrayUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.ArrayUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ArrayUtilsWraps {
    public static final CharSequence[] EMPTY_CHAR_SEQUENCE_ARRAY = new CharSequence[0];

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable boolean[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).allMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable byte[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).allMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable char[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).allMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable double[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).allMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable float[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).allMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable int[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).allMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable long[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).allMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable short[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).allMatch(ArrayUtils::isEmpty);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean allEmpty(@Nullable E[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).allMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable boolean[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).anyMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable byte[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).anyMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable char[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).anyMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable double[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).anyMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable float[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).anyMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable int[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).anyMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable long[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).anyMatch(ArrayUtils::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable short[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).anyMatch(ArrayUtils::isEmpty);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean anyEmpty(@Nullable E[]... arrays) {
        return ArrayUtils.isEmpty(arrays) || Arrays.stream(arrays).anyMatch(ArrayUtils::isEmpty);
    }

    @Nullable
    @SafeVarargs
    public static <E> List<E> asList(@Nullable E... elements) {
        return asList(false, elements);
    }

    @Nullable
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> List<E> asList(boolean modifiable, @Nullable E... elements) {
        if (ArrayUtils.isEmpty(elements)) {
            return null;
        }
        List<E> result = Arrays.asList(elements);
        return modifiable ? new ArrayList<>(result) : Collections.unmodifiableList(result);
    }

    @Nullable
    @SafeVarargs
    public static <E> Set<E> asSet(@Nullable E... elements) {
        return asSet(false, elements);
    }

    @Nullable
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> Set<E> asSet(boolean modifiable, @Nullable E... elements) {
        if (ArrayUtils.isEmpty(elements)) {
            return null;
        }
        Set<E> result = new LinkedHashSet<>(Arrays.asList(elements));
        return modifiable ? result : Collections.unmodifiableSet(result);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <E> E[] castAs(@Nullable Object[] sources, @Nullable Class<E> expectedType) {
        if (ArrayUtils.isEmpty(sources) || expectedType == null || expectedType == Void.class) {
            return null;
        }
        E[] result = (E[]) Array.newInstance(expectedType, ArrayUtils.getLength(sources));
        forEachIndexing(sources, (index, source) -> result[index] = ObjectUtilsWraps.castAs(source, expectedType));
        return ArrayUtils.isEmpty(result) ? null : result;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean containsString(@Nullable CharSequence[] sequences, @Nullable CharSequence sequence) {
        return ArrayUtils.isNotEmpty(sequences) && StringUtils.isNotEmpty(sequence) && Arrays.stream(sequences).anyMatch(element -> StringUtils.contains(element, sequence));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean containsStringIgnoreCase(@Nullable CharSequence[] sequences, @Nullable CharSequence sequence) {
        return ArrayUtils.isNotEmpty(sequences) && StringUtils.isNotEmpty(sequence) && Arrays.stream(sequences).anyMatch(element -> StringUtils.containsIgnoreCase(element, sequence));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsString(@Nullable CharSequence[] sequences, @Nullable CharSequence sequence) {
        return ArrayUtils.isNotEmpty(sequences) && StringUtils.isNotEmpty(sequence) && Arrays.stream(sequences).anyMatch(element -> StringUtils.equals(element, sequence));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsStringIgnoreCase(@Nullable CharSequence[] sequences, @Nullable CharSequence sequence) {
        return ArrayUtils.isNotEmpty(sequences) && StringUtils.isNotEmpty(sequence) && Arrays.stream(sequences).anyMatch(element -> StringUtils.equalsIgnoreCase(element, sequence));
    }

    public static void forEach(@Nullable boolean[] array, @Nullable Consumer<Boolean> action) {
        forEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEach(@Nullable boolean[] array, @Nullable Consumer<Boolean> action, @Nullable Predicate<Boolean> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (boolean element : array) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void forEach(@Nullable byte[] array, @Nullable Consumer<Byte> action) {
        forEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEach(@Nullable byte[] array, @Nullable Consumer<Byte> action, @Nullable Predicate<Byte> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (byte element : array) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void forEach(@Nullable char[] array, @Nullable Consumer<Character> action) {
        forEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEach(@Nullable char[] array, @Nullable Consumer<Character> action, @Nullable Predicate<Character> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (char element : array) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void forEach(@Nullable double[] array, @Nullable Consumer<Double> action) {
        forEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEach(@Nullable double[] array, @Nullable Consumer<Double> action, @Nullable Predicate<Double> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (double element : array) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void forEach(@Nullable float[] array, @Nullable Consumer<Float> action) {
        forEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEach(@Nullable float[] array, @Nullable Consumer<Float> action, @Nullable Predicate<Float> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (float element : array) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void forEach(@Nullable int[] array, @Nullable Consumer<Integer> action) {
        forEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEach(@Nullable int[] array, @Nullable Consumer<Integer> action, @Nullable Predicate<Integer> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (int element : array) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void forEach(@Nullable long[] array, @Nullable Consumer<Long> action) {
        forEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEach(@Nullable long[] array, @Nullable Consumer<Long> action, @Nullable Predicate<Long> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (long element : array) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void forEach(@Nullable short[] array, @Nullable Consumer<Short> action) {
        forEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEach(@Nullable short[] array, @Nullable Consumer<Short> action, @Nullable Predicate<Short> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (short element : array) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static <E> void forEach(@Nullable E[] array, @Nullable Consumer<? super E> action) {
        forEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEach(@Nullable E[] array, @Nullable Consumer<? super E> action, @Nullable Predicate<? super E> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (E element : array) {
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void forEachBreakable(@Nullable boolean[] array, @Nullable Function<Boolean, Boolean> action) {
        forEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachBreakable(@Nullable boolean[] array, @Nullable Function<Boolean, Boolean> action, @Nullable Predicate<Boolean> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (boolean element : array) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static void forEachBreakable(@Nullable byte[] array, @Nullable Function<Byte, Boolean> action) {
        forEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachBreakable(@Nullable byte[] array, @Nullable Function<Byte, Boolean> action, @Nullable Predicate<Byte> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (byte element : array) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static void forEachBreakable(@Nullable char[] array, @Nullable Function<Character, Boolean> action) {
        forEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachBreakable(@Nullable char[] array, @Nullable Function<Character, Boolean> action, @Nullable Predicate<Character> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (char element : array) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static void forEachBreakable(@Nullable double[] array, @Nullable Function<Double, Boolean> action) {
        forEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachBreakable(@Nullable double[] array, @Nullable Function<Double, Boolean> action, @Nullable Predicate<Double> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (double element : array) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static void forEachBreakable(@Nullable float[] array, @Nullable Function<Float, Boolean> action) {
        forEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachBreakable(@Nullable float[] array, @Nullable Function<Float, Boolean> action, @Nullable Predicate<Float> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (float element : array) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static void forEachBreakable(@Nullable int[] array, @Nullable Function<Integer, Boolean> action) {
        forEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachBreakable(@Nullable int[] array, @Nullable Function<Integer, Boolean> action, @Nullable Predicate<Integer> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (int element : array) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static void forEachBreakable(@Nullable long[] array, @Nullable Function<Long, Boolean> action) {
        forEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachBreakable(@Nullable long[] array, @Nullable Function<Long, Boolean> action, @Nullable Predicate<Long> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (long element : array) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static void forEachBreakable(@Nullable short[] array, @Nullable Function<Short, Boolean> action) {
        forEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachBreakable(@Nullable short[] array, @Nullable Function<Short, Boolean> action, @Nullable Predicate<Short> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (short element : array) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static <E> void forEachBreakable(@Nullable E[] array, @Nullable Function<? super E, Boolean> action) {
        forEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachBreakable(@Nullable E[] array, @Nullable Function<? super E, Boolean> action, @Nullable Predicate<? super E> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        for (E element : array) {
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Boolean forEachTailing(@Nullable boolean[] array, @Nullable Consumer<Boolean> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Byte forEachTailing(@Nullable byte[] array, @Nullable Consumer<Byte> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Character forEachTailing(@Nullable char[] array, @Nullable Consumer<Character> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Double forEachTailing(@Nullable double[] array, @Nullable Consumer<Double> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Float forEachTailing(@Nullable float[] array, @Nullable Consumer<Float> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Integer forEachTailing(@Nullable int[] array, @Nullable Consumer<Integer> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Long forEachTailing(@Nullable long[] array, @Nullable Consumer<Long> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Short forEachTailing(@Nullable short[] array, @Nullable Consumer<Short> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachTailing(@Nullable E[] array, @Nullable Consumer<? super E> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(array[i]);
        }
        return array[length - 1];
    }

    public static void forEachIndexing(@Nullable boolean[] array, @Nullable BiConsumer<Integer, Boolean> action) {
        forEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexing(@Nullable boolean[] array, @Nullable BiConsumer<Integer, Boolean> action, @Nullable BiPredicate<Integer, Boolean> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (boolean element : array) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static void forEachIndexing(@Nullable byte[] array, @Nullable BiConsumer<Integer, Byte> action) {
        forEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexing(@Nullable byte[] array, @Nullable BiConsumer<Integer, Byte> action, @Nullable BiPredicate<Integer, Byte> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (byte element : array) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static void forEachIndexing(@Nullable char[] array, @Nullable BiConsumer<Integer, Character> action) {
        forEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexing(@Nullable char[] array, @Nullable BiConsumer<Integer, Character> action, @Nullable BiPredicate<Integer, Character> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (char element : array) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static void forEachIndexing(@Nullable double[] array, @Nullable BiConsumer<Integer, Double> action) {
        forEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexing(@Nullable double[] array, @Nullable BiConsumer<Integer, Double> action, @Nullable BiPredicate<Integer, Double> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (double element : array) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static void forEachIndexing(@Nullable float[] array, @Nullable BiConsumer<Integer, Float> action) {
        forEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexing(@Nullable float[] array, @Nullable BiConsumer<Integer, Float> action, @Nullable BiPredicate<Integer, Float> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (float element : array) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static void forEachIndexing(@Nullable int[] array, @Nullable BiConsumer<Integer, Integer> action) {
        forEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexing(@Nullable int[] array, @Nullable BiConsumer<Integer, Integer> action, @Nullable BiPredicate<Integer, Integer> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (int element : array) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static void forEachIndexing(@Nullable long[] array, @Nullable BiConsumer<Integer, Long> action) {
        forEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexing(@Nullable long[] array, @Nullable BiConsumer<Integer, Long> action, @Nullable BiPredicate<Integer, Long> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (long element : array) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static void forEachIndexing(@Nullable short[] array, @Nullable BiConsumer<Integer, Short> action) {
        forEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexing(@Nullable short[] array, @Nullable BiConsumer<Integer, Short> action, @Nullable BiPredicate<Integer, Short> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (short element : array) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static <E> void forEachIndexing(@Nullable E[] array, @Nullable BiConsumer<Integer, ? super E> action) {
        forEachIndexing(array, action, null);
    }

    /**
     * @see org.apache.commons.collections4.IteratorUtils#forEach
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexing(@Nullable E[] array, @Nullable BiConsumer<Integer, ? super E> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (E element : array) {
            if (filter == null || filter.test(index, element)) {
                action.accept(index, element);
            }
            index++;
        }
    }

    public static void forEachIndexingBreakable(@Nullable boolean[] array, @Nullable BiFunction<Integer, Boolean, Boolean> action) {
        forEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexingBreakable(@Nullable boolean[] array, @Nullable BiFunction<Integer, Boolean, Boolean> action, @Nullable BiPredicate<Integer, Boolean> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (boolean element : array) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    public static void forEachIndexingBreakable(@Nullable byte[] array, @Nullable BiFunction<Integer, Byte, Boolean> action) {
        forEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexingBreakable(@Nullable byte[] array, @Nullable BiFunction<Integer, Byte, Boolean> action, @Nullable BiPredicate<Integer, Byte> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (byte element : array) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    public static void forEachIndexingBreakable(@Nullable char[] array, @Nullable BiFunction<Integer, Character, Boolean> action) {
        forEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexingBreakable(@Nullable char[] array, @Nullable BiFunction<Integer, Character, Boolean> action, @Nullable BiPredicate<Integer, Character> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (char element : array) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    public static void forEachIndexingBreakable(@Nullable double[] array, @Nullable BiFunction<Integer, Double, Boolean> action) {
        forEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexingBreakable(@Nullable double[] array, @Nullable BiFunction<Integer, Double, Boolean> action, @Nullable BiPredicate<Integer, Double> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (double element : array) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    public static void forEachIndexingBreakable(@Nullable float[] array, @Nullable BiFunction<Integer, Float, Boolean> action) {
        forEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexingBreakable(@Nullable float[] array, @Nullable BiFunction<Integer, Float, Boolean> action, @Nullable BiPredicate<Integer, Float> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (float element : array) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    public static void forEachIndexingBreakable(@Nullable int[] array, @Nullable BiFunction<Integer, Integer, Boolean> action) {
        forEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexingBreakable(@Nullable int[] array, @Nullable BiFunction<Integer, Integer, Boolean> action, @Nullable BiPredicate<Integer, Integer> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (int element : array) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    public static void forEachIndexingBreakable(@Nullable long[] array, @Nullable BiFunction<Integer, Long, Boolean> action) {
        forEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexingBreakable(@Nullable long[] array, @Nullable BiFunction<Integer, Long, Boolean> action, @Nullable BiPredicate<Integer, Long> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (long element : array) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    public static void forEachIndexingBreakable(@Nullable short[] array, @Nullable BiFunction<Integer, Short, Boolean> action) {
        forEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachIndexingBreakable(@Nullable short[] array, @Nullable BiFunction<Integer, Short, Boolean> action, @Nullable BiPredicate<Integer, Short> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (short element : array) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    public static <E> void forEachIndexingBreakable(@Nullable E[] array, @Nullable BiFunction<Integer, ? super E, Boolean> action) {
        forEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void forEachIndexingBreakable(@Nullable E[] array, @Nullable BiFunction<Integer, ? super E, Boolean> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int index = 0;
        for (E element : array) {
            if ((filter == null || filter.test(index, element)) && BooleanUtils.isNotTrue(action.apply(index, element))) {
                break;
            }
            index++;
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Boolean forEachIndexingTailing(@Nullable boolean[] array, @Nullable BiConsumer<Integer, Boolean> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Byte forEachIndexingTailing(@Nullable byte[] array, @Nullable BiConsumer<Integer, Byte> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Character forEachIndexingTailing(@Nullable char[] array, @Nullable BiConsumer<Integer, Character> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Double forEachIndexingTailing(@Nullable double[] array, @Nullable BiConsumer<Integer, Double> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Float forEachIndexingTailing(@Nullable float[] array, @Nullable BiConsumer<Integer, Float> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Integer forEachIndexingTailing(@Nullable int[] array, @Nullable BiConsumer<Integer, Integer> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Long forEachIndexingTailing(@Nullable long[] array, @Nullable BiConsumer<Integer, Long> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Short forEachIndexingTailing(@Nullable short[] array, @Nullable BiConsumer<Integer, Short> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E forEachIndexingTailing(@Nullable E[] array, @Nullable BiConsumer<Integer, ? super E> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, array[i]);
        }
        return array[length - 1];
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Object array) {
        if (!ObjectUtilsWraps.isArray(array)) {
            return null;
        }
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            if (element != null) {
                return element.getClass();
            }
        }
        return null;
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Object[] array) {
        return (array == null) ? null : array.getClass().getComponentType();
    }

    @Nullable
    public static <E> E getFirst(@Nullable E[] array) {
        return ArrayUtils.isEmpty(array) ? null : ArrayUtils.get(array, 0);
    }

    @Nullable
    public static <E> E getFirst(@Nullable E[] array, @Nullable E defaultValue) {
        return ArrayUtils.isEmpty(array) ? null : ArrayUtils.get(array, 0, defaultValue);
    }

    @Nullable
    public static <E> E getLast(@Nullable E[] array) {
        return ArrayUtils.isEmpty(array) ? null : ArrayUtils.get(array, ArrayUtils.getLength(array) - 1);
    }

    @Nullable
    public static <E> E getLast(@Nullable E[] array, @Nullable E defaultValue) {
        return ArrayUtils.isEmpty(array) ? null : ArrayUtils.get(array, ArrayUtils.getLength(array) - 1, defaultValue);
    }

    public static int getLength(@Nullable Object array) {
        return ObjectUtilsWraps.isArray(array) ? Array.getLength(array) : 0;
    }

    @Nullable
    public static <E> int[] getMatchedIndexes(@Nullable E[] array, @Nullable Predicate<? super E> filter) {
        List<Integer> indexes = getMatchedIndexesToList(array, filter);
        if (indexes == null || indexes.isEmpty()) {
            return null;
        }
        return ArrayUtils.toPrimitive(indexes.toArray(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY));
    }

    @Nullable
    public static <E> List<Integer> getMatchedIndexesToList(@Nullable E[] array, @Nullable Predicate<? super E> filter) {
        if (ArrayUtils.isEmpty(array) || filter == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        forEachIndexing(array, (index, element) -> {
            if (filter.test(element)) {
                result.add(index);
            }
        });
        return result.isEmpty() ? null : result;
    }

    public static void ifEmpty(@Nullable boolean[] array, @Nullable Runnable action) {
        if (ArrayUtils.isEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifEmpty(@Nullable byte[] array, @Nullable Runnable action) {
        if (ArrayUtils.isEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifEmpty(@Nullable char[] array, @Nullable Runnable action) {
        if (ArrayUtils.isEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifEmpty(@Nullable double[] array, @Nullable Runnable action) {
        if (ArrayUtils.isEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifEmpty(@Nullable float[] array, @Nullable Runnable action) {
        if (ArrayUtils.isEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifEmpty(@Nullable int[] array, @Nullable Runnable action) {
        if (ArrayUtils.isEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifEmpty(@Nullable long[] array, @Nullable Runnable action) {
        if (ArrayUtils.isEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifEmpty(@Nullable short[] array, @Nullable Runnable action) {
        if (ArrayUtils.isEmpty(array) && action != null) {
            action.run();
        }
    }

    public static <E> void ifEmpty(@Nullable E[] array, @Nullable Runnable action) {
        if (ArrayUtils.isEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifEmptyOrElse(@Nullable boolean[] array, @Nullable Runnable absentAction, @Nullable Consumer<boolean[]> presentAction) {
        if (ArrayUtils.isEmpty(array)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(array);
            }
        }
    }

    public static void ifEmptyOrElse(@Nullable byte[] array, @Nullable Runnable absentAction, @Nullable Consumer<byte[]> presentAction) {
        if (ArrayUtils.isEmpty(array)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(array);
            }
        }
    }

    public static void ifEmptyOrElse(@Nullable char[] array, @Nullable Runnable absentAction, @Nullable Consumer<char[]> presentAction) {
        if (ArrayUtils.isEmpty(array)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(array);
            }
        }
    }

    public static void ifEmptyOrElse(@Nullable double[] array, @Nullable Runnable absentAction, @Nullable Consumer<double[]> presentAction) {
        if (ArrayUtils.isEmpty(array)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(array);
            }
        }
    }

    public static void ifEmptyOrElse(@Nullable float[] array, @Nullable Runnable absentAction, @Nullable Consumer<float[]> presentAction) {
        if (ArrayUtils.isEmpty(array)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(array);
            }
        }
    }

    public static void ifEmptyOrElse(@Nullable int[] array, @Nullable Runnable absentAction, @Nullable Consumer<int[]> presentAction) {
        if (ArrayUtils.isEmpty(array)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(array);
            }
        }
    }

    public static void ifEmptyOrElse(@Nullable long[] array, @Nullable Runnable absentAction, @Nullable Consumer<long[]> presentAction) {
        if (ArrayUtils.isEmpty(array)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(array);
            }
        }
    }

    public static void ifEmptyOrElse(@Nullable short[] array, @Nullable Runnable absentAction, @Nullable Consumer<short[]> presentAction) {
        if (ArrayUtils.isEmpty(array)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(array);
            }
        }
    }

    public static <E> void ifEmptyOrElse(@Nullable E[] array, @Nullable Runnable absentAction, @Nullable Consumer<E[]> presentAction) {
        if (ArrayUtils.isEmpty(array)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(array);
            }
        }
    }

    public static void ifNotEmpty(@Nullable boolean[] array, @Nullable Consumer<boolean[]> action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.accept(array);
        }
    }

    public static void ifNotEmpty(@Nullable byte[] array, @Nullable Consumer<byte[]> action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.accept(array);
        }
    }

    public static void ifNotEmpty(@Nullable char[] array, @Nullable Consumer<char[]> action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.accept(array);
        }
    }

    public static void ifNotEmpty(@Nullable double[] array, @Nullable Consumer<double[]> action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.accept(array);
        }
    }

    public static void ifNotEmpty(@Nullable float[] array, @Nullable Consumer<float[]> action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.accept(array);
        }
    }

    public static void ifNotEmpty(@Nullable int[] array, @Nullable Consumer<int[]> action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.accept(array);
        }
    }

    public static void ifNotEmpty(@Nullable long[] array, @Nullable Consumer<long[]> action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.accept(array);
        }
    }

    public static void ifNotEmpty(@Nullable short[] array, @Nullable Consumer<short[]> action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.accept(array);
        }
    }

    public static <E> void ifNotEmpty(@Nullable E[] array, @Nullable Consumer<E[]> action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.accept(array);
        }
    }

    public static void ifNotEmpty(@Nullable boolean[] array, @Nullable Runnable action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifNotEmpty(@Nullable byte[] array, @Nullable Runnable action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifNotEmpty(@Nullable char[] array, @Nullable Runnable action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifNotEmpty(@Nullable double[] array, @Nullable Runnable action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifNotEmpty(@Nullable float[] array, @Nullable Runnable action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifNotEmpty(@Nullable int[] array, @Nullable Runnable action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifNotEmpty(@Nullable long[] array, @Nullable Runnable action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.run();
        }
    }

    public static void ifNotEmpty(@Nullable short[] array, @Nullable Runnable action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.run();
        }
    }

    public static <E> void ifNotEmpty(@Nullable E[] array, @Nullable Runnable action) {
        if (ArrayUtils.isNotEmpty(array) && action != null) {
            action.run();
        }
    }

    public static boolean isDistinct(@Nullable boolean[] array) {
        return isDistinct(ArrayUtils.toObject(array), false);
    }

    public static boolean isDistinct(@Nullable byte[] array) {
        return isDistinct(ArrayUtils.toObject(array), false);
    }

    public static boolean isDistinct(@Nullable char[] array) {
        return isDistinct(ArrayUtils.toObject(array), false);
    }

    public static boolean isDistinct(@Nullable double[] array) {
        return isDistinct(ArrayUtils.toObject(array), false);
    }

    public static boolean isDistinct(@Nullable float[] array) {
        return isDistinct(ArrayUtils.toObject(array), false);
    }

    public static boolean isDistinct(@Nullable int[] array) {
        return isDistinct(ArrayUtils.toObject(array), false);
    }

    public static boolean isDistinct(@Nullable long[] array) {
        return isDistinct(ArrayUtils.toObject(array), false);
    }

    public static boolean isDistinct(@Nullable short[] array) {
        return isDistinct(ArrayUtils.toObject(array), false);
    }

    public static <E> boolean isDistinct(@Nullable E[] array) {
        return isDistinct(array, true);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean isDistinct(@Nullable E[] array, boolean ignoreNull) {
        if (ArrayUtils.isEmpty(array)) {
            return false;
        }
        if (ignoreNull) {
            List<E> list = Arrays.stream(array).filter(Objects::nonNull).collect(Collectors.toList());
            Set<E> set = new LinkedHashSet<>(list);
            return CollectionPlainWraps.isSameSize(list, set);
        } else {
            Set<E> set = new LinkedHashSet<>(Arrays.asList(array));
            return ArrayUtils.getLength(array) == CollectionPlainWraps.size(set);
        }
    }

    public static boolean isIndexBound(@Nullable boolean[] array, int index) {
        return index >= 0 && index < ArrayUtils.getLength(array);
    }

    public static boolean isIndexBound(@Nullable byte[] array, int index) {
        return index >= 0 && index < ArrayUtils.getLength(array);
    }

    public static boolean isIndexBound(@Nullable char[] array, int index) {
        return index >= 0 && index < ArrayUtils.getLength(array);
    }

    public static boolean isIndexBound(@Nullable double[] array, int index) {
        return index >= 0 && index < ArrayUtils.getLength(array);
    }

    public static boolean isIndexBound(@Nullable float[] array, int index) {
        return index >= 0 && index < ArrayUtils.getLength(array);
    }

    public static boolean isIndexBound(@Nullable int[] array, int index) {
        return index >= 0 && index < ArrayUtils.getLength(array);
    }

    public static boolean isIndexBound(@Nullable long[] array, int index) {
        return index >= 0 && index < ArrayUtils.getLength(array);
    }

    public static boolean isIndexBound(@Nullable short[] array, int index) {
        return index >= 0 && index < ArrayUtils.getLength(array);
    }

    /**
     * @see org.apache.commons.lang3.ArrayUtils#isArrayIndexValid
     */
    public static <E> boolean isIndexBound(@Nullable E[] array, int index) {
        return index >= 0 && index < ArrayUtils.getLength(array);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameLength(@Nullable boolean[]... arrays) {
        if (ArrayUtils.getLength(arrays) < 2) {
            return false;
        }
        int length = ArrayUtils.getLength(ArrayUtils.get(arrays, 0));
        return Arrays.stream(arrays).skip(1L).allMatch(element -> ArrayUtils.getLength(element) == length);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameLength(@Nullable byte[]... arrays) {
        if (ArrayUtils.getLength(arrays) < 2) {
            return false;
        }
        int length = ArrayUtils.getLength(ArrayUtils.get(arrays, 0));
        return Arrays.stream(arrays).skip(1L).allMatch(element -> ArrayUtils.getLength(element) == length);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameLength(@Nullable char[]... arrays) {
        if (ArrayUtils.getLength(arrays) < 2) {
            return false;
        }
        int length = ArrayUtils.getLength(ArrayUtils.get(arrays, 0));
        return Arrays.stream(arrays).skip(1L).allMatch(element -> ArrayUtils.getLength(element) == length);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameLength(@Nullable double[]... arrays) {
        if (ArrayUtils.getLength(arrays) < 2) {
            return false;
        }
        int length = ArrayUtils.getLength(ArrayUtils.get(arrays, 0));
        return Arrays.stream(arrays).skip(1L).allMatch(array -> ArrayUtils.getLength(array) == length);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameLength(@Nullable float[]... arrays) {
        if (ArrayUtils.getLength(arrays) < 2) {
            return false;
        }
        int length = ArrayUtils.getLength(ArrayUtils.get(arrays, 0));
        return Arrays.stream(arrays).skip(1L).allMatch(element -> ArrayUtils.getLength(element) == length);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameLength(@Nullable int[]... arrays) {
        if (ArrayUtils.getLength(arrays) < 2) {
            return false;
        }
        int length = ArrayUtils.getLength(ArrayUtils.get(arrays, 0));
        return Arrays.stream(arrays).skip(1L).allMatch(element -> ArrayUtils.getLength(element) == length);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameLength(@Nullable long[]... arrays) {
        if (ArrayUtils.getLength(arrays) < 2) {
            return false;
        }
        int length = ArrayUtils.getLength(ArrayUtils.get(arrays, 0));
        return Arrays.stream(arrays).skip(1L).allMatch(element -> ArrayUtils.getLength(element) == length);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameLength(@Nullable short[]... arrays) {
        if (ArrayUtils.getLength(arrays) < 2) {
            return false;
        }
        int length = ArrayUtils.getLength(ArrayUtils.get(arrays, 0));
        return Arrays.stream(arrays).skip(1L).allMatch(element -> ArrayUtils.getLength(element) == length);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> boolean isSameLength(@Nullable E[]... arrays) {
        if (ArrayUtils.getLength(arrays) < 2) {
            return false;
        }
        int length = ArrayUtils.getLength(ArrayUtils.get(arrays, 0));
        return Arrays.stream(arrays).skip(1L).allMatch(element -> ArrayUtils.getLength(element) == length);
    }

    public static boolean isSingleton(@Nullable boolean[] array) {
        return ArrayUtils.getLength(array) == 1;
    }

    public static boolean isSingleton(@Nullable byte[] array) {
        return ArrayUtils.getLength(array) == 1;
    }

    public static boolean isSingleton(@Nullable char[] array) {
        return ArrayUtils.getLength(array) == 1;
    }

    public static boolean isSingleton(@Nullable double[] array) {
        return ArrayUtils.getLength(array) == 1;
    }

    public static boolean isSingleton(@Nullable float[] array) {
        return ArrayUtils.getLength(array) == 1;
    }

    public static boolean isSingleton(@Nullable int[] array) {
        return ArrayUtils.getLength(array) == 1;
    }

    public static boolean isSingleton(@Nullable long[] array) {
        return ArrayUtils.getLength(array) == 1;
    }

    public static boolean isSingleton(@Nullable short[] array) {
        return ArrayUtils.getLength(array) == 1;
    }

    public static <E> boolean isSingleton(@Nullable E[] array) {
        return ArrayUtils.getLength(array) == 1;
    }

    public static boolean isMultitude(@Nullable boolean[] array) {
        return ArrayUtils.getLength(array) > 1;
    }

    public static boolean isMultitude(@Nullable byte[] array) {
        return ArrayUtils.getLength(array) > 1;
    }

    public static boolean isMultitude(@Nullable char[] array) {
        return ArrayUtils.getLength(array) > 1;
    }

    public static boolean isMultitude(@Nullable double[] array) {
        return ArrayUtils.getLength(array) > 1;
    }

    public static boolean isMultitude(@Nullable float[] array) {
        return ArrayUtils.getLength(array) > 1;
    }

    public static boolean isMultitude(@Nullable int[] array) {
        return ArrayUtils.getLength(array) > 1;
    }

    public static boolean isMultitude(@Nullable long[] array) {
        return ArrayUtils.getLength(array) > 1;
    }

    public static boolean isMultitude(@Nullable short[] array) {
        return ArrayUtils.getLength(array) > 1;
    }

    public static <E> boolean isMultitude(@Nullable E[] array) {
        return ArrayUtils.getLength(array) > 1;
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> int maxLength(@Nullable E[]... arrays) {
        return ArrayUtils.isEmpty(arrays) ? 0 : Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).max().orElse(0);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> int sumLength(@Nullable E[]... arrays) {
        return ArrayUtils.isEmpty(arrays) ? 0 : Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
    }

    /**
     * Returns a new array created with the specified component type of the source array and length
     *
     * @param array the source array
     * @param length the length of the new array
     *
     * @return a new array created with the specified component type of the source array and length
     *
     * @throws java.lang.IllegalArgumentException if the number of dimensions of the requested array instance exceed 255
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <E> E[] newInstance(@Nullable E[] array, int length) throws IllegalArgumentException {
        if (array == null || length < 0) {
            return null;
        }
        Class<?> clazz = array.getClass().getComponentType();
        if (clazz == null || clazz == Void.class) {
            return null;
        }
        return (E[]) Array.newInstance(clazz, length);
    }

    @Nullable
    public static <E> E[] newInstanceQuietly(@Nullable E[] array, int length) {
        try {
            return newInstance(array, length);
        } catch (Exception ignored) {
        }
        return null;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] removeIf(@Nullable E[] array, @Nullable Predicate<? super E> filter) {
        int[] indexes = getMatchedIndexes(array, filter);
        if (ArrayUtils.isEmpty(indexes)) {
            return array;
        }
        return ArrayUtils.removeAll(array, indexes);
    }

    public static void reverseForEach(@Nullable boolean[] array, @Nullable Consumer<Boolean> action) {
        reverseForEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEach(@Nullable boolean[] array, @Nullable Consumer<Boolean> action, @Nullable Predicate<Boolean> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(array[i])) {
                action.accept(array[i]);
            }
        }
    }

    public static void reverseForEach(@Nullable byte[] array, @Nullable Consumer<Byte> action) {
        reverseForEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEach(@Nullable byte[] array, @Nullable Consumer<Byte> action, @Nullable Predicate<Byte> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(array[i])) {
                action.accept(array[i]);
            }
        }
    }

    public static void reverseForEach(@Nullable char[] array, @Nullable Consumer<Character> action) {
        reverseForEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEach(@Nullable char[] array, @Nullable Consumer<Character> action, @Nullable Predicate<Character> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(array[i])) {
                action.accept(array[i]);
            }
        }
    }

    public static void reverseForEach(@Nullable double[] array, @Nullable Consumer<Double> action) {
        reverseForEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEach(@Nullable double[] array, @Nullable Consumer<Double> action, @Nullable Predicate<Double> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(array[i])) {
                action.accept(array[i]);
            }
        }
    }

    public static void reverseForEach(@Nullable float[] array, @Nullable Consumer<Float> action) {
        reverseForEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEach(@Nullable float[] array, @Nullable Consumer<Float> action, @Nullable Predicate<Float> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(array[i])) {
                action.accept(array[i]);
            }
        }
    }

    public static void reverseForEach(@Nullable int[] array, @Nullable Consumer<Integer> action) {
        reverseForEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEach(@Nullable int[] array, @Nullable Consumer<Integer> action, @Nullable Predicate<Integer> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(array[i])) {
                action.accept(array[i]);
            }
        }
    }

    public static void reverseForEach(@Nullable long[] array, @Nullable Consumer<Long> action) {
        reverseForEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEach(@Nullable long[] array, @Nullable Consumer<Long> action, @Nullable Predicate<Long> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(array[i])) {
                action.accept(array[i]);
            }
        }
    }

    public static void reverseForEach(@Nullable short[] array, @Nullable Consumer<Short> action) {
        reverseForEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEach(@Nullable short[] array, @Nullable Consumer<Short> action, @Nullable Predicate<Short> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(array[i])) {
                action.accept(array[i]);
            }
        }
    }

    public static <E> void reverseForEach(@Nullable E[] array, @Nullable Consumer<? super E> action) {
        reverseForEach(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void reverseForEach(@Nullable E[] array, @Nullable Consumer<? super E> action, @Nullable Predicate<? super E> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(array[i])) {
                action.accept(array[i]);
            }
        }
    }

    public static void reverseForEachBreakable(@Nullable boolean[] array, @Nullable Function<Boolean, Boolean> action) {
        reverseForEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachBreakable(@Nullable boolean[] array, @Nullable Function<Boolean, Boolean> action, @Nullable Predicate<Boolean> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(array[i])) && BooleanUtils.isNotTrue(action.apply(array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachBreakable(@Nullable byte[] array, @Nullable Function<Byte, Boolean> action) {
        reverseForEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachBreakable(@Nullable byte[] array, @Nullable Function<Byte, Boolean> action, @Nullable Predicate<Byte> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(array[i])) && BooleanUtils.isNotTrue(action.apply(array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachBreakable(@Nullable char[] array, @Nullable Function<Character, Boolean> action) {
        reverseForEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachBreakable(@Nullable char[] array, @Nullable Function<Character, Boolean> action, @Nullable Predicate<Character> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(array[i])) && BooleanUtils.isNotTrue(action.apply(array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachBreakable(@Nullable double[] array, @Nullable Function<Double, Boolean> action) {
        reverseForEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachBreakable(@Nullable double[] array, @Nullable Function<Double, Boolean> action, @Nullable Predicate<Double> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(array[i])) && BooleanUtils.isNotTrue(action.apply(array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachBreakable(@Nullable float[] array, @Nullable Function<Float, Boolean> action) {
        reverseForEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachBreakable(@Nullable float[] array, @Nullable Function<Float, Boolean> action, @Nullable Predicate<Float> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(array[i])) && BooleanUtils.isNotTrue(action.apply(array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachBreakable(@Nullable int[] array, @Nullable Function<Integer, Boolean> action) {
        reverseForEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachBreakable(@Nullable int[] array, @Nullable Function<Integer, Boolean> action, @Nullable Predicate<Integer> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(array[i])) && BooleanUtils.isNotTrue(action.apply(array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachBreakable(@Nullable long[] array, @Nullable Function<Long, Boolean> action) {
        reverseForEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachBreakable(@Nullable long[] array, @Nullable Function<Long, Boolean> action, @Nullable Predicate<Long> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(array[i])) && BooleanUtils.isNotTrue(action.apply(array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachBreakable(@Nullable short[] array, @Nullable Function<Short, Boolean> action) {
        reverseForEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachBreakable(@Nullable short[] array, @Nullable Function<Short, Boolean> action, @Nullable Predicate<Short> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(array[i])) && BooleanUtils.isNotTrue(action.apply(array[i]))) {
                break;
            }
        }
    }

    public static <E> void reverseForEachBreakable(@Nullable E[] array, @Nullable Function<? super E, Boolean> action) {
        reverseForEachBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void reverseForEachBreakable(@Nullable E[] array, @Nullable Function<? super E, Boolean> action, @Nullable Predicate<? super E> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(array[i])) && BooleanUtils.isNotTrue(action.apply(array[i]))) {
                break;
            }
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Boolean reverseForEachHeading(@Nullable boolean[] array, @Nullable Consumer<Boolean> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Byte reverseForEachHeading(@Nullable byte[] array, @Nullable Consumer<Byte> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Character reverseForEachHeading(@Nullable char[] array, @Nullable Consumer<Character> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Double reverseForEachHeading(@Nullable double[] array, @Nullable Consumer<Double> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Float reverseForEachHeading(@Nullable float[] array, @Nullable Consumer<Float> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Integer reverseForEachHeading(@Nullable int[] array, @Nullable Consumer<Integer> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Long reverseForEachHeading(@Nullable long[] array, @Nullable Consumer<Long> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Short reverseForEachHeading(@Nullable short[] array, @Nullable Consumer<Short> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E reverseForEachHeading(@Nullable E[] array, @Nullable Consumer<? super E> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(array[i]);
        }
        return array[0];
    }

    public static void reverseForEachIndexing(@Nullable boolean[] array, @Nullable BiConsumer<Integer, Boolean> action) {
        reverseForEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexing(@Nullable boolean[] array, @Nullable BiConsumer<Integer, Boolean> action, @Nullable BiPredicate<Integer, Boolean> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(i, array[i])) {
                action.accept(i, array[i]);
            }
        }
    }

    public static void reverseForEachIndexing(@Nullable byte[] array, @Nullable BiConsumer<Integer, Byte> action) {
        reverseForEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexing(@Nullable byte[] array, @Nullable BiConsumer<Integer, Byte> action, @Nullable BiPredicate<Integer, Byte> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(i, array[i])) {
                action.accept(i, array[i]);
            }
        }
    }

    public static void reverseForEachIndexing(@Nullable char[] array, @Nullable BiConsumer<Integer, Character> action) {
        reverseForEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexing(@Nullable char[] array, @Nullable BiConsumer<Integer, Character> action, @Nullable BiPredicate<Integer, Character> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(i, array[i])) {
                action.accept(i, array[i]);
            }
        }
    }

    public static void reverseForEachIndexing(@Nullable double[] array, @Nullable BiConsumer<Integer, Double> action) {
        reverseForEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexing(@Nullable double[] array, @Nullable BiConsumer<Integer, Double> action, @Nullable BiPredicate<Integer, Double> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(i, array[i])) {
                action.accept(i, array[i]);
            }
        }
    }

    public static void reverseForEachIndexing(@Nullable float[] array, @Nullable BiConsumer<Integer, Float> action) {
        reverseForEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexing(@Nullable float[] array, @Nullable BiConsumer<Integer, Float> action, @Nullable BiPredicate<Integer, Float> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(i, array[i])) {
                action.accept(i, array[i]);
            }
        }
    }

    public static void reverseForEachIndexing(@Nullable int[] array, @Nullable BiConsumer<Integer, Integer> action) {
        reverseForEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexing(@Nullable int[] array, @Nullable BiConsumer<Integer, Integer> action, @Nullable BiPredicate<Integer, Integer> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(i, array[i])) {
                action.accept(i, array[i]);
            }
        }
    }

    public static void reverseForEachIndexing(@Nullable long[] array, @Nullable BiConsumer<Integer, Long> action) {
        reverseForEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexing(@Nullable long[] array, @Nullable BiConsumer<Integer, Long> action, @Nullable BiPredicate<Integer, Long> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(i, array[i])) {
                action.accept(i, array[i]);
            }
        }
    }

    public static void reverseForEachIndexing(@Nullable short[] array, @Nullable BiConsumer<Integer, Short> action) {
        reverseForEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexing(@Nullable short[] array, @Nullable BiConsumer<Integer, Short> action, @Nullable BiPredicate<Integer, Short> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(i, array[i])) {
                action.accept(i, array[i]);
            }
        }
    }

    public static <E> void reverseForEachIndexing(@Nullable E[] array, @Nullable BiConsumer<Integer, ? super E> action) {
        reverseForEachIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void reverseForEachIndexing(@Nullable E[] array, @Nullable BiConsumer<Integer, ? super E> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if (filter == null || filter.test(i, array[i])) {
                action.accept(i, array[i]);
            }
        }
    }

    public static void reverseForEachIndexingBreakable(@Nullable boolean[] array, @Nullable BiFunction<Integer, Boolean, Boolean> action) {
        reverseForEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexingBreakable(@Nullable boolean[] array, @Nullable BiFunction<Integer, Boolean, Boolean> action, @Nullable BiPredicate<Integer, Boolean> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(i, array[i])) && BooleanUtils.isNotTrue(action.apply(i, array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachIndexingBreakable(@Nullable byte[] array, @Nullable BiFunction<Integer, Byte, Boolean> action) {
        reverseForEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexingBreakable(@Nullable byte[] array, @Nullable BiFunction<Integer, Byte, Boolean> action, @Nullable BiPredicate<Integer, Byte> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(i, array[i])) && BooleanUtils.isNotTrue(action.apply(i, array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachIndexingBreakable(@Nullable char[] array, @Nullable BiFunction<Integer, Character, Boolean> action) {
        reverseForEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexingBreakable(@Nullable char[] array, @Nullable BiFunction<Integer, Character, Boolean> action, @Nullable BiPredicate<Integer, Character> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(i, array[i])) && BooleanUtils.isNotTrue(action.apply(i, array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachIndexingBreakable(@Nullable double[] array, @Nullable BiFunction<Integer, Double, Boolean> action) {
        reverseForEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexingBreakable(@Nullable double[] array, @Nullable BiFunction<Integer, Double, Boolean> action, @Nullable BiPredicate<Integer, Double> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(i, array[i])) && BooleanUtils.isNotTrue(action.apply(i, array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachIndexingBreakable(@Nullable float[] array, @Nullable BiFunction<Integer, Float, Boolean> action) {
        reverseForEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexingBreakable(@Nullable float[] array, @Nullable BiFunction<Integer, Float, Boolean> action, @Nullable BiPredicate<Integer, Float> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(i, array[i])) && BooleanUtils.isNotTrue(action.apply(i, array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachIndexingBreakable(@Nullable int[] array, @Nullable BiFunction<Integer, Integer, Boolean> action) {
        reverseForEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexingBreakable(@Nullable int[] array, @Nullable BiFunction<Integer, Integer, Boolean> action, @Nullable BiPredicate<Integer, Integer> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(i, array[i])) && BooleanUtils.isNotTrue(action.apply(i, array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachIndexingBreakable(@Nullable long[] array, @Nullable BiFunction<Integer, Long, Boolean> action) {
        reverseForEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexingBreakable(@Nullable long[] array, @Nullable BiFunction<Integer, Long, Boolean> action, @Nullable BiPredicate<Integer, Long> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(i, array[i])) && BooleanUtils.isNotTrue(action.apply(i, array[i]))) {
                break;
            }
        }
    }

    public static void reverseForEachIndexingBreakable(@Nullable short[] array, @Nullable BiFunction<Integer, Short, Boolean> action) {
        reverseForEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachIndexingBreakable(@Nullable short[] array, @Nullable BiFunction<Integer, Short, Boolean> action, @Nullable BiPredicate<Integer, Short> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(i, array[i])) && BooleanUtils.isNotTrue(action.apply(i, array[i]))) {
                break;
            }
        }
    }

    public static <E> void reverseForEachIndexingBreakable(@Nullable E[] array, @Nullable BiFunction<Integer, ? super E, Boolean> action) {
        reverseForEachIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> void reverseForEachIndexingBreakable(@Nullable E[] array, @Nullable BiFunction<Integer, ? super E, Boolean> action, @Nullable BiPredicate<Integer, ? super E> filter) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            if ((filter == null || filter.test(i, array[i])) && BooleanUtils.isNotTrue(action.apply(i, array[i]))) {
                break;
            }
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Boolean reverseForEachIndexingHeading(@Nullable boolean[] array, @Nullable BiConsumer<Integer, Boolean> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(i, array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Byte reverseForEachIndexingHeading(@Nullable byte[] array, @Nullable BiConsumer<Integer, Byte> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(i, array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Character reverseForEachIndexingHeading(@Nullable char[] array, @Nullable BiConsumer<Integer, Character> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(i, array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Double reverseForEachIndexingHeading(@Nullable double[] array, @Nullable BiConsumer<Integer, Double> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(i, array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Float reverseForEachIndexingHeading(@Nullable float[] array, @Nullable BiConsumer<Integer, Float> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(i, array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Integer reverseForEachIndexingHeading(@Nullable int[] array, @Nullable BiConsumer<Integer, Integer> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(i, array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Long reverseForEachIndexingHeading(@Nullable long[] array, @Nullable BiConsumer<Integer, Long> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(i, array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Short reverseForEachIndexingHeading(@Nullable short[] array, @Nullable BiConsumer<Integer, Short> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(i, array[i]);
        }
        return array[0];
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E reverseForEachIndexingHeading(@Nullable E[] array, @Nullable BiConsumer<Integer, ? super E> action) {
        if (ArrayUtils.isEmpty(array) || action == null) {
            return null;
        }
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            action.accept(i, array[i]);
        }
        return array[0];
    }

    public static void forEachObject(@Nullable Object array, @Nullable Consumer<Object> action) {
        forEachObject(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachObject(@Nullable Object array, @Nullable Consumer<Object> action, @Nullable Predicate<Object> filter) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return;
        }
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void forEachObjectBreakable(@Nullable Object array, @Nullable Function<Object, Boolean> action) {
        forEachObjectBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachObjectBreakable(@Nullable Object array, @Nullable Function<Object, Boolean> action, @Nullable Predicate<Object> filter) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return;
        }
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Object forEachObjectTailing(@Nullable Object array, @Nullable Consumer<Object> action) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return null;
        }
        int length = Array.getLength(array);
        for (int i = 0; i < length - 1; i++) {
            action.accept(Array.get(array, i));
        }
        return (length == 0) ? null : Array.get(array, 0);
    }

    public static void forEachObjectIndexing(@Nullable Object array, @Nullable BiConsumer<Integer, Object> action) {
        forEachObjectIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachObjectIndexing(@Nullable Object array, @Nullable BiConsumer<Integer, Object> action, @Nullable BiPredicate<Integer, Object> filter) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return;
        }
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            if (filter == null || filter.test(i, element)) {
                action.accept(i, element);
            }
        }
    }

    public static void forEachObjectIndexingBreakable(@Nullable Object array, @Nullable BiFunction<Integer, Object, Boolean> action) {
        forEachObjectIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void forEachObjectIndexingBreakable(@Nullable Object array, @Nullable BiFunction<Integer, Object, Boolean> action, @Nullable BiPredicate<Integer, Object> filter) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return;
        }
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            if ((filter == null || filter.test(i, element)) && BooleanUtils.isNotTrue(action.apply(i, element))) {
                break;
            }
        }
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Object forEachObjectIndexingTailing(@Nullable Object array, @Nullable BiConsumer<Integer, Object> action) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return null;
        }
        int length = Array.getLength(array);
        for (int i = 0; i < length - 1; i++) {
            action.accept(i, Array.get(array, i));
        }
        return (length == 0) ? null : Array.get(array, 0);
    }

    public static void reverseForEachObject(@Nullable Object array, @Nullable Consumer<Object> action) {
        reverseForEachObject(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachObject(@Nullable Object array, @Nullable Consumer<Object> action, @Nullable Predicate<Object> filter) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return;
        }
        int length = Array.getLength(array);
        for (int i = length - 1; i >= 0; i--) {
            Object element = Array.get(array, i);
            if (filter == null || filter.test(element)) {
                action.accept(element);
            }
        }
    }

    public static void reverseForEachObjectBreakable(@Nullable Object array, @Nullable Function<Object, Boolean> action) {
        reverseForEachObjectBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachObjectBreakable(@Nullable Object array, @Nullable Function<Object, Boolean> action, @Nullable Predicate<Object> filter) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return;
        }
        int length = Array.getLength(array);
        for (int i = length - 1; i >= 0; i--) {
            Object element = Array.get(array, i);
            if ((filter == null || filter.test(element)) && BooleanUtils.isNotTrue(action.apply(element))) {
                break;
            }
        }
    }

    public static void reverseForEachObjectIndexing(@Nullable Object array, @Nullable BiConsumer<Integer, Object> action) {
        reverseForEachObjectIndexing(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachObjectIndexing(@Nullable Object array, @Nullable BiConsumer<Integer, Object> action, @Nullable BiPredicate<Integer, Object> filter) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return;
        }
        int length = Array.getLength(array);
        for (int i = length - 1; i >= 0; i--) {
            Object element = Array.get(array, i);
            if (filter == null || filter.test(i, element)) {
                action.accept(i, element);
            }
        }
    }

    public static void reverseForEachObjectIndexingBreakable(@Nullable Object array, @Nullable BiFunction<Integer, Object, Boolean> action) {
        reverseForEachObjectIndexingBreakable(array, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void reverseForEachObjectIndexingBreakable(@Nullable Object array, @Nullable BiFunction<Integer, Object, Boolean> action, @Nullable BiPredicate<Integer, Object> filter) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return;
        }
        int length = Array.getLength(array);
        for (int i = length - 1; i >= 0; i--) {
            Object element = Array.get(array, i);
            if ((filter == null || filter.test(i, element)) && BooleanUtils.isNotTrue(action.apply(i, element))) {
                break;
            }
        }
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <E> E[] singletonArray(@Nullable E element) {
        return (element == null) ? null : ArrayUtils.toArray(element);
    }

    @Nonnull
    public static Stream<Boolean> stream(@Nullable boolean[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : StreamPlainWraps.stream(ArrayUtils.toObject(array));
    }

    @Nonnull
    public static Stream<Byte> stream(@Nullable byte[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : StreamPlainWraps.stream(ArrayUtils.toObject(array));
    }

    @Nonnull
    public static Stream<Character> stream(@Nullable char[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : StreamPlainWraps.stream(ArrayUtils.toObject(array));
    }

    @Nonnull
    public static Stream<Double> stream(@Nullable double[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : StreamPlainWraps.stream(ArrayUtils.toObject(array));
    }

    @Nonnull
    public static Stream<Float> stream(@Nullable float[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : StreamPlainWraps.stream(ArrayUtils.toObject(array));
    }

    @Nonnull
    public static Stream<Integer> stream(@Nullable int[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : StreamPlainWraps.stream(ArrayUtils.toObject(array));
    }

    @Nonnull
    public static Stream<Long> stream(@Nullable long[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : StreamPlainWraps.stream(ArrayUtils.toObject(array));
    }

    @Nonnull
    public static Stream<Short> stream(@Nullable short[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : StreamPlainWraps.stream(ArrayUtils.toObject(array));
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> Stream<E> stream(@Nullable E[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : Arrays.stream(array);
    }

    @Nullable
    public static boolean[] subarray(@Nullable boolean[] array, int startIndexInclusive) {
        return isIndexBound(array, startIndexInclusive) ? ArrayUtils.subarray(array, startIndexInclusive, ArrayUtils.getLength(array)) : null;
    }

    @Nullable
    public static byte[] subarray(@Nullable byte[] array, int startIndexInclusive) {
        return isIndexBound(array, startIndexInclusive) ? ArrayUtils.subarray(array, startIndexInclusive, ArrayUtils.getLength(array)) : null;
    }

    @Nullable
    public static char[] subarray(@Nullable char[] array, int startIndexInclusive) {
        return isIndexBound(array, startIndexInclusive) ? ArrayUtils.subarray(array, startIndexInclusive, ArrayUtils.getLength(array)) : null;
    }

    @Nullable
    public static double[] subarray(@Nullable double[] array, int startIndexInclusive) {
        return isIndexBound(array, startIndexInclusive) ? ArrayUtils.subarray(array, startIndexInclusive, ArrayUtils.getLength(array)) : null;
    }

    @Nullable
    public static float[] subarray(@Nullable float[] array, int startIndexInclusive) {
        return isIndexBound(array, startIndexInclusive) ? ArrayUtils.subarray(array, startIndexInclusive, ArrayUtils.getLength(array)) : null;
    }

    @Nullable
    public static int[] subarray(@Nullable int[] array, int startIndexInclusive) {
        return isIndexBound(array, startIndexInclusive) ? ArrayUtils.subarray(array, startIndexInclusive, ArrayUtils.getLength(array)) : null;
    }

    @Nullable
    public static long[] subarray(@Nullable long[] array, int startIndexInclusive) {
        return isIndexBound(array, startIndexInclusive) ? ArrayUtils.subarray(array, startIndexInclusive, ArrayUtils.getLength(array)) : null;
    }

    @Nullable
    public static short[] subarray(@Nullable short[] array, int startIndexInclusive) {
        return isIndexBound(array, startIndexInclusive) ? ArrayUtils.subarray(array, startIndexInclusive, ArrayUtils.getLength(array)) : null;
    }

    @Nullable
    public static <E> E[] subarray(@Nullable E[] array, int startIndexInclusive) {
        return isIndexBound(array, startIndexInclusive) ? ArrayUtils.subarray(array, startIndexInclusive, ArrayUtils.getLength(array)) : null;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Object reverseForEachObjectIndexingHeading(@Nullable Object array, @Nullable BiFunction<Integer, Object, Boolean> action) {
        if (ObjectUtils.anyNull(array, action) || !array.getClass().isArray()) {
            return null;
        }
        int length = Array.getLength(array);
        for (int i = length - 1; i > 0; i--) {
            action.apply(i, Array.get(array, i));
        }
        return (length == 0) ? null : Array.get(array, 0);
    }

    /**
     * Returns an object array
     * <p>
     * Typically used in scenarios, building "args" parameter of "org.springframework.context.MessageSource"
     *
     * @param array the source object array
     *
     * @return an object array
     *
     * @see org.apache.commons.lang3.ArrayUtils#toArray
     */
    public static Object[] toArray(@Nullable Object... array) {
        return array;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean[] unionAll(@Nullable boolean[]... arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return null;
        }
        int length = Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
        if (length <= 0) {
            return null;
        }
        boolean[] result = new boolean[length];
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).flatMap(ArrayUtilsWraps::stream).forEach(element -> result[index.getAndIncrement()] = element);
        return result;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static byte[] unionAll(@Nullable byte[]... arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return null;
        }
        int length = Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
        if (length <= 0) {
            return null;
        }
        byte[] result = new byte[length];
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).flatMap(ArrayUtilsWraps::stream).forEach(element -> result[index.getAndIncrement()] = element);
        return result;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static char[] unionAll(@Nullable char[]... arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return null;
        }
        int length = Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
        if (length <= 0) {
            return null;
        }
        char[] result = new char[length];
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).flatMap(ArrayUtilsWraps::stream).forEach(element -> result[index.getAndIncrement()] = element);
        return result;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static double[] unionAll(@Nullable double[]... arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return null;
        }
        int length = Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
        if (length <= 0) {
            return null;
        }
        double[] result = new double[length];
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).flatMap(ArrayUtilsWraps::stream).forEach(element -> result[index.getAndIncrement()] = element);
        return result;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static float[] unionAll(@Nullable float[]... arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return null;
        }
        int length = Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
        if (length <= 0) {
            return null;
        }
        float[] result = new float[length];
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).flatMap(ArrayUtilsWraps::stream).forEach(element -> result[index.getAndIncrement()] = element);
        return result;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int[] unionAll(@Nullable int[]... arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return null;
        }
        int length = Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
        if (length <= 0) {
            return null;
        }
        int[] result = new int[length];
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).flatMap(ArrayUtilsWraps::stream).forEach(element -> result[index.getAndIncrement()] = element);
        return result;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long[] unionAll(@Nullable long[]... arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return null;
        }
        int length = Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
        if (length <= 0) {
            return null;
        }
        long[] result = new long[length];
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).flatMap(ArrayUtilsWraps::stream).forEach(element -> result[index.getAndIncrement()] = element);
        return result;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static short[] unionAll(@Nullable short[]... arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return null;
        }
        int length = Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
        if (length <= 0) {
            return null;
        }
        short[] result = new short[length];
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).flatMap(ArrayUtilsWraps::stream).forEach(element -> result[index.getAndIncrement()] = element);
        return result;
    }

    @Nullable
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <E> E[] unionAll(@Nullable E[]... arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return null;
        }
        int length = Arrays.stream(arrays).mapToInt(ArrayUtils::getLength).sum();
        if (length <= 0) {
            return null;
        }
        E[] result = newInstance(Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).findFirst().orElse(null), length);
        if (result == null) {
            return null;
        }
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arrays).filter(ArrayUtils::isNotEmpty).flatMap(Arrays::stream).forEach(element -> result[index.getAndIncrement()] = element);
        return result;
    }
}
