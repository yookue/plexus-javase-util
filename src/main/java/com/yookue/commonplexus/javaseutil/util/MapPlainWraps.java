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


import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.function.TriFunction;
import org.apache.commons.lang3.math.Fraction;
import com.yookue.commonplexus.javaseutil.constant.AssertMessageConst;
import com.yookue.commonplexus.javaseutil.constant.JavaKeywordConst;
import com.yookue.commonplexus.javaseutil.function.TriConsumer;
import com.yookue.commonplexus.javaseutil.function.TriPredicate;


/**
 * Utilities for {@link java.util.Map}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class MapPlainWraps {
    /**
     * Default load factor for {@link java.util.HashMap}/{@link java.util.LinkedHashMap} variants
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allEmpty(@Nullable Map<?, ?>... maps) {
        return ArrayUtils.isEmpty(maps) || Arrays.stream(maps).allMatch(MapPlainWraps::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotEmpty(@Nullable Map<?, ?>... maps) {
        return ArrayUtils.isNotEmpty(maps) && Arrays.stream(maps).noneMatch(MapPlainWraps::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyEmpty(@Nullable Map<?, ?>... maps) {
        return ArrayUtils.isNotEmpty(maps) && Arrays.stream(maps).anyMatch(MapPlainWraps::isEmpty);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotEmpty(@Nullable Map<?, ?>... maps) {
        return ArrayUtils.isNotEmpty(maps) && Arrays.stream(maps).anyMatch(MapPlainWraps::isNotEmpty);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> V compute(@Nullable Map<K, V> map, @Nullable K key, @Nullable BiFunction<? super K, ? super V, ? extends V> action) {
        return ObjectUtils.anyNull(map, action) ? null : map.compute(key, action);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> V computeIfAbsent(@Nullable Map<K, V> map, @Nullable K key, @Nullable Function<? super K, ? extends V> action) {
        return ObjectUtils.anyNull(map, action) ? null : map.computeIfAbsent(key, action);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> V computeIfEquals(@Nullable Map<K, V> map, @Nullable K key, @Nullable V comparison, @Nullable BiFunction<? super K, ? super V, ? extends V> action) {
        return (ObjectUtils.anyNull(map, action) || !Objects.equals(map.get(key), comparison)) ? null : map.compute(key, action);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V extends CharSequence> V computeIfEqualsIgnoreCase(@Nullable Map<K, V> map, @Nullable K key, @Nullable CharSequence comparison, @Nullable BiFunction<? super K, ? super V, ? extends V> action) {
        return (ObjectUtils.anyNull(map, action) || !StringUtils.equalsIgnoreCase(map.get(key), comparison)) ? null : map.compute(key, action);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> V computeIfPresent(@Nullable Map<K, V> map, @Nullable K key, @Nullable BiFunction<? super K, ? super V, ? extends V> action) {
        return ObjectUtils.anyNull(map, action) ? null : map.computeIfPresent(key, action);
    }

    public static <K> boolean containsKey(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return isNotEmpty(map) && map.containsKey(key);
    }

    public static <K extends CharSequence> boolean containsKeyIgnoreCase(@Nullable Map<K, ?> map, @Nullable K key) {
        return isNotEmpty(map) && (map.containsKey(key) || map.keySet().stream().anyMatch(element -> StringUtils.equalsIgnoreCase(element, key)));
    }

    public static <V> boolean containsValue(@Nullable Map<?, ? super V> map, @Nullable V value) {
        return isNotEmpty(map) && map.containsValue(value);
    }

    public static <V extends CharSequence> boolean containsValueIgnoreCase(@Nullable Map<?, V> map, @Nullable V value) {
        return isNotEmpty(map) && (map.containsValue(value) || map.values().stream().anyMatch(element -> StringUtils.equalsIgnoreCase(element, value)));
    }

    public static <K, V> boolean containsKeyValue(@Nullable Map<K, V> map, @Nullable K key, @Nullable V value) {
        return isNotEmpty(map) && map.containsKey(key) && Objects.equals(map.get(key), value);
    }

    public static <K extends CharSequence, V extends CharSequence> boolean containsKeyIgnoreCaseValueIgnoreCase(@Nullable Map<K, V> map, @Nullable K key, @Nullable V value) {
        return isNotEmpty(map) && map.entrySet().stream().anyMatch(entry -> StringUtils.equalsIgnoreCase(entry.getKey(), key) && StringUtils.equalsIgnoreCase(entry.getValue(), value));
    }

    @SafeVarargs
    public static <K> boolean containsAllKeys(@Nullable Map<? super K, ?> map, @Nullable K... keys) {
        return containsAllKeys(map, ArrayUtilsWraps.asList(keys));
    }

    public static <K> boolean containsAllKeys(@Nullable Map<? super K, ?> map, @Nullable Collection<K> keys) {
        return isNotEmpty(map) && CollectionPlainWraps.containsAll(map.keySet(), keys);
    }

    @SafeVarargs
    public static <K> boolean containsAnyKeys(@Nullable Map<? super K, ?> map, @Nullable K... keys) {
        return containsAnyKeys(map, ArrayUtilsWraps.asList(keys));
    }

    public static <K> boolean containsAnyKeys(@Nullable Map<? super K, ?> map, @Nullable Collection<K> keys) {
        return isNotEmpty(map) && CollectionPlainWraps.containsAny(map.keySet(), keys);
    }

    @SafeVarargs
    public static <V> boolean containsAllValues(@Nullable Map<?, ? super V> map, @Nullable V... values) {
        return containsAllValues(map, ArrayUtilsWraps.asList(values));
    }

    public static <V> boolean containsAllValues(@Nullable Map<?, ? super V> map, @Nullable Collection<V> values) {
        return isNotEmpty(map) && CollectionPlainWraps.containsAll(map.values(), values);
    }

    @SafeVarargs
    public static <V> boolean containsAnyValues(@Nullable Map<?, ? super V> map, @Nullable V... values) {
        return containsAnyValues(map, ArrayUtilsWraps.asList(values));
    }

    public static <V> boolean containsAnyValues(@Nullable Map<?, ? super V> map, @Nullable Collection<V> values) {
        return isNotEmpty(map) && CollectionPlainWraps.containsAny(map.values(), values);
    }

    @Nullable
    public static <K, V> Map<K, V> emptyAsNull(@Nullable Map<K, V> map) {
        return isEmpty(map) ? null : map;
    }

    @Nullable
    @SafeVarargs
    public static <K, V> V firstNonNullValue(@Nullable Map<? super K, V> map, @Nullable K... keys) {
        return firstNonNullValue(map, ArrayUtilsWraps.asList(keys));
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> V firstNonNullValue(@Nullable Map<? super K, V> map, @Nullable Collection<K> keys) {
        return ObjectUtilsWraps.anyEmpty(map, keys) ? null : keys.stream().map(map::get).filter(Objects::nonNull).findFirst().orElse(null);
    }

    @Nullable
    @SafeVarargs
    public static <K, V extends CharSequence> V firstNonBlankValue(@Nullable Map<? super K, V> map, @Nullable K... keys) {
        return firstNonBlankValue(map, ArrayUtilsWraps.asList(keys));
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V extends CharSequence> V firstNonBlankValue(@Nullable Map<? super K, V> map, @Nullable Collection<K> keys) {
        return ObjectUtilsWraps.anyEmpty(map, keys) ? null : keys.stream().map(map::get).filter(StringUtils::isNotBlank).findFirst().orElse(null);
    }

    @Nullable
    @SafeVarargs
    public static <K, V extends CharSequence> V firstNonEmptyValue(@Nullable Map<? super K, V> map, @Nullable K... keys) {
        return firstNonEmptyValue(map, ArrayUtilsWraps.asList(keys));
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V extends CharSequence> V firstNonEmptyValue(@Nullable Map<? super K, V> map, @Nullable Collection<K> keys) {
        return ObjectUtilsWraps.anyEmpty(map, keys) ? null : keys.stream().map(map::get).filter(StringUtils::isNotEmpty).findFirst().orElse(null);
    }

    public static <K, V> void forEach(@Nullable Map<K, V> map, @Nullable BiConsumer<? super K, ? super V> action) {
        forEach(map, action, null);
    }

    public static <K, V> void forEach(@Nullable Map<K, V> map, @Nullable BiConsumer<? super K, ? super V> action, @Nullable BiPredicate<? super K, ? super V> filter) {
        if (isEmpty(map) || action == null) {
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (filter == null || filter.test(entry.getKey(), entry.getValue())) {
                action.accept(entry.getKey(), entry.getValue());
            }
        }
    }

    public static <K, V> void forEachBreakable(@Nullable Map<K, V> map, @Nullable BiFunction<? super K, ? super V, Boolean> action) {
        forEachBreakable(map, action, null);
    }

    public static <K, V> void forEachBreakable(@Nullable Map<K, V> map, @Nullable BiFunction<? super K, ? super V, Boolean> action, @Nullable BiPredicate<? super K, ? super V> filter) {
        if (isEmpty(map) || action == null) {
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if ((filter == null || filter.test(entry.getKey(), entry.getValue())) && BooleanUtils.isNotTrue(action.apply(entry.getKey(), entry.getValue()))) {
                break;
            }
        }
    }

    public static <K, V> void forEachIndexing(@Nullable Map<K, V> map, @Nullable TriConsumer<Integer, ? super K, ? super V> action) {
        forEachIndexing(map, action, null);
    }

    public static <K, V> void forEachIndexing(@Nullable Map<K, V> map, @Nullable TriConsumer<Integer, ? super K, ? super V> action, @Nullable TriPredicate<Integer, ? super K, ? super V> filter) {
        if (isEmpty(map) || action == null) {
            return;
        }
        int index = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (filter == null || filter.test(index, entry.getKey(), entry.getValue())) {
                action.accept(index, entry.getKey(), entry.getValue());
            }
            index++;
        }
    }

    public static <K, V> void forEachIndexingBreakable(@Nullable Map<K, V> map, @Nullable TriFunction<Integer, ? super K, ? super V, Boolean> action) {
        forEachIndexingBreakable(map, action, null);
    }

    public static <K, V> void forEachIndexingBreakable(@Nullable Map<K, V> map, @Nullable TriFunction<Integer, ? super K, ? super V, Boolean> action, @Nullable TriPredicate<Integer, ? super K, ? super V> filter) {
        if (isEmpty(map) || action == null) {
            return;
        }
        int index = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if ((filter == null || filter.test(index, entry.getKey(), entry.getValue())) && BooleanUtils.isNotTrue(action.apply(index, entry.getKey(), entry.getValue()))) {
                break;
            }
            index++;
        }
    }

    @Nullable
    public static <K, V> Map.Entry<K, V> forEachIndexingTailing(@Nullable Map<K, V> map, @Nullable TriConsumer<Integer, ? super K, ? super V> action) {
        if (isEmpty(map) || action == null) {
            return null;
        }
        int index = 0, size = map.size();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (index < size - 1) {
                action.accept(index, entry.getKey(), entry.getValue());
            } else {
                return entry;
            }
            index++;
        }
        return null;
    }

    @Nullable
    public static <K, V> Map.Entry<K, V> forEachTailing(@Nullable Map<K, V> map, @Nullable BiConsumer<? super K, ? super V> action) {
        if (isEmpty(map) || action == null) {
            return null;
        }
        int index = 0, size = map.size();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (index < size - 1) {
                action.accept(entry.getKey(), entry.getValue());
            } else {
                return entry;
            }
            index++;
        }
        return null;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K> K getKeyIfSingleton(@Nullable Map<K, ?> map) {
        if (isSingleton(map)) {
            for (Map.Entry<K, ?> entry : map.entrySet()) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Nullable
    public static <K> Enumeration<K> getKeysToEnumeration(@Nullable Map<K, ?> map) {
        return isEmpty(map) ? null : Collections.enumeration(map.keySet());
    }

    @Nullable
    public static <V> Enumeration<V> getValuesToEnumeration(@Nullable Map<?, V> map) {
        return isEmpty(map) ? null : Collections.enumeration(map.values());
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <V> V getValueIfSingleton(@Nullable Map<?, V> map) {
        if (isSingleton(map)) {
            for (Map.Entry<?, V> entry : map.entrySet()) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static <K> Boolean getBoolean(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getBoolean(map, key, null);
    }

    public static <K> Boolean getBoolean(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Boolean defaultValue) {
        return getObjectAs(map, key, Boolean.class, defaultValue);
    }

    public static <K> Byte getByte(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getByte(map, key, null);
    }

    public static <K> Byte getByte(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Byte defaultValue) {
        return getObjectAs(map, key, Byte.class, defaultValue);
    }

    public static <K> Character getCharacter(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getCharacter(map, key, null);
    }

    public static <K> Character getCharacter(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Character defaultValue) {
        return getObjectAs(map, key, Character.class, defaultValue);
    }

    public static <K> Double getDouble(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getDouble(map, key, null);
    }

    public static <K> Double getDouble(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Double defaultValue) {
        return getObjectAs(map, key, Double.class, defaultValue);
    }

    public static <K> Float getFloat(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getFloat(map, key, null);
    }

    public static <K> Float getFloat(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Float defaultValue) {
        return getObjectAs(map, key, Float.class, defaultValue);
    }

    public static <K> Integer getInteger(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getInteger(map, key, null);
    }

    public static <K> Integer getInteger(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Integer defaultValue) {
        return getObjectAs(map, key, Integer.class, defaultValue);
    }

    public static <K> Long getLong(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getLong(map, key, null);
    }

    public static <K> Long getLong(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Long defaultValue) {
        return getObjectAs(map, key, Long.class, defaultValue);
    }

    public static <K> Short getShort(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getShort(map, key, null);
    }

    public static <K> Short getShort(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Short defaultValue) {
        return getObjectAs(map, key, Short.class, defaultValue);
    }

    public static <K> BigDecimal getBigDecimal(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getBigDecimal(map, key, null);
    }

    public static <K> BigDecimal getBigDecimal(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable BigDecimal defaultValue) {
        return getObjectAs(map, key, BigDecimal.class, defaultValue);
    }

    public static <K> BigInteger getBigInteger(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getBigInteger(map, key, null);
    }

    public static <K> BigInteger getBigInteger(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable BigInteger defaultValue) {
        return getObjectAs(map, key, BigInteger.class, defaultValue);
    }

    public static <K> Fraction getFraction(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getFraction(map, key, null);
    }

    public static <K> Fraction getFraction(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Fraction defaultValue) {
        return getObjectAs(map, key, Fraction.class, defaultValue);
    }

    public static <K> String getString(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getString(map, key, null);
    }

    public static <K> String getString(@Nullable Map<? super K, ?> map, @Nullable K key, char defaultValue) {
        return getObjectAs(map, key, String.class, CharUtils.toString(defaultValue));
    }

    public static <K> String getString(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable String defaultValue) {
        return getObjectAs(map, key, String.class, defaultValue);
    }

    @Nullable
    public static <K, V> V getObject(@Nullable Map<? super K, V> map, @Nullable K key) {
        return isEmpty(map) ? null : map.get(key);
    }

    public static <K, V> V getObject(@Nullable Map<? super K, V> map, @Nullable K key, @Nullable V defaultValue) {
        V result = getObject(map, key);
        return (result == null) ? defaultValue : result;
    }

    @Nullable
    public static <K extends CharSequence, V> V getObjectIgnoreCase(@Nullable Map<K, V> map, @Nullable K key) {
        if (isEmpty(map)) {
            return null;
        }
        V result = map.get(key);
        if (result != null) {
            return result;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (StringUtils.equalsIgnoreCase(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static <K extends CharSequence, V> V getObjectIgnoreCase(@Nullable Map<K, V> map, @Nullable K key, @Nullable V defaultValue) {
        V result = getObjectIgnoreCase(map, key);
        return (result == null) ? defaultValue : result;
    }

    public static <K, T> T getObjectAs(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Class<T> expectedType) {
        return getObjectAs(map, key, expectedType, null);
    }

    public static <K, T> T getObjectAs(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Class<T> expectedType, @Nullable T defaultValue) {
        if (isEmpty(map) || expectedType == null) {
            return defaultValue;
        }
        T result = ObjectUtilsWraps.castAs(map.get(key), expectedType);
        return (result == null) ? defaultValue : result;
    }

    @Nullable
    public static <K> Object[] getObjectArray(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getObjectArray(map, key, null);
    }

    @Nullable
    public static <K> Object[] getObjectArray(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Object[] defaultValue) {
        if (isEmpty(map)) {
            return defaultValue;
        }
        Object[] result = ObjectUtilsWraps.toObjectArray(map.get(key));
        return (result == null) ? defaultValue : result;
    }

    @Nullable
    public static <T, K> T[] getObjectArrayAs(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Class<T> expectedType) {
        return getObjectArrayAs(map, key, expectedType, null);
    }

    @Nullable
    public static <T, K> T[] getObjectArrayAs(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Class<T> expectedType, @Nullable T[] defaultValue) {
        if (isEmpty(map) || expectedType == null) {
            return defaultValue;
        }
        T[] result = ArrayUtilsWraps.castAs(getObjectArray(map, key), expectedType);
        return (result == null) ? defaultValue : result;
    }

    @Nullable
    public static <K> List<?> getObjectList(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getObjectList(map, key, null);
    }

    @Nullable
    public static <K> List<?> getObjectList(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable List<Object> defaultValue) {
        if (isEmpty(map)) {
            return defaultValue;
        }
        List<?> result = ObjectUtilsWraps.toObjectList(map.get(key));
        return (result == null) ? defaultValue : result;
    }

    public static <K> LocalDate getLocalDate(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getLocalDate(map, key, null);
    }

    @Nullable
    public static <K> LocalDate getLocalDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable LocalDate defaultValue) {
        if (isEmpty(map)) {
            return null;
        }
        LocalDate result = null;
        Object value = map.get(key);
        if (value instanceof LocalDate alias) {
            result = alias;
        } else if (value instanceof String alias) {
            result = LocalDateWraps.parseDateGuessing(alias);
        }
        return (result == null) ? defaultValue : result;
    }

    public static <K> LocalDateTime getLocalDateTime(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getLocalDateTime(map, key, null);
    }

    @Nullable
    public static <K> LocalDateTime getLocalDateTime(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable LocalDateTime defaultValue) {
        if (isEmpty(map)) {
            return null;
        }
        LocalDateTime result = null;
        Object value = map.get(key);
        if (value instanceof LocalDateTime alias) {
            result = alias;
        } else if (value instanceof String alias) {
            result = LocalDateWraps.parseDateTimeGuessing(alias);
        }
        return (result == null) ? defaultValue : result;
    }

    public static <K> LocalTime getLocalTime(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getLocalTime(map, key, null);
    }

    @Nullable
    public static <K> LocalTime getLocalTime(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable LocalTime defaultValue) {
        if (isEmpty(map)) {
            return null;
        }
        LocalTime result = null;
        Object value = map.get(key);
        if (value instanceof LocalTime alias) {
            result = alias;
        } else if (value instanceof String alias) {
            result = LocalDateWraps.parseTimeGuessing(alias);
        }
        return (result == null) ? defaultValue : result;
    }

    public static <K> java.sql.Date getSqlDate(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getSqlDate(map, key, null, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static <K> java.sql.Date getSqlDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable String... formats) {
        return getSqlDate(map, key, ArrayUtilsWraps.asList(formats));
    }

    public static <K> java.sql.Date getSqlDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Collection<String> formats) {
        return getSqlDate(map, key, null, formats);
    }

    public static <K> java.sql.Date getSqlDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable java.sql.Date defaultValue) {
        return getSqlDate(map, key, defaultValue, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static <K> java.sql.Date getSqlDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable java.sql.Date defaultValue, @Nullable String... formats) {
        return getSqlDate(map, key, defaultValue, ArrayUtilsWraps.asList(formats));
    }

    public static <K> java.sql.Date getSqlDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable java.sql.Date defaultValue, @Nullable Collection<String> formats) {
        java.util.Date utilDate = getUtilDate(map, key, formats);
        return (utilDate == null) ? defaultValue : SqlDateWraps.castFromUtilDate(utilDate);
    }

    public static <K> java.util.Date getUtilDate(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return getUtilDate(map, key, null, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static <K> java.util.Date getUtilDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable String... formats) {
        return getUtilDate(map, key, ArrayUtilsWraps.asList(formats));
    }

    public static <K> java.util.Date getUtilDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable Collection<String> formats) {
        return getUtilDate(map, key, null, formats);
    }

    public static <K> java.util.Date getUtilDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable java.util.Date defaultValue) {
        return getUtilDate(map, key, defaultValue, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static <K> java.util.Date getUtilDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable java.util.Date defaultValue, @Nullable String... formats) {
        return getUtilDate(map, key, defaultValue, ArrayUtilsWraps.asList(formats));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K> java.util.Date getUtilDate(@Nullable Map<? super K, ?> map, @Nullable K key, @Nullable java.util.Date defaultValue, @Nullable Collection<String> formats) {
        java.util.Date result = null;
        Object value = map.get(key);
        if (value instanceof java.util.Date alias) {
            result = alias;
        } else if (value instanceof String alias) {
            if (CollectionPlainWraps.isEmpty(formats)) {
                result = ObjectUtils.defaultIfNull(UtilDateWraps.parseDateTimeGuessing(alias), UtilDateWraps.parseDateGuessing((String) value));
            } else {
                result = UtilDateWraps.parseDateTimeWithFormats((String) value, formats);
            }
        }
        return (result == null) ? defaultValue : result;
    }

    @Nullable
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K> Set<K> getUniqueKeySet(@Nullable Map<? extends K, ?>... maps) {
        if (ArrayUtils.isEmpty(maps)) {
            return null;
        }
        Set<K> result = new LinkedHashSet<>();
        Arrays.stream(maps).filter(ObjectUtils::isNotEmpty).forEach(map -> CollectionPlainWraps.addAll(result, map.keySet()));
        return result.isEmpty() ? null : result;
    }

    public static <T extends Map<?, ?>> void ifEmpty(@Nullable T map, @Nullable Consumer<? super T> action) {
        ifEmptyOrElse(map, action, null);
    }

    public static <T extends Map<?, ?>> void ifEmpty(@Nullable T map, @Nullable Runnable action) {
        ifEmptyOrElse(map, action, null);
    }

    public static <T extends Map<?, ?>> void ifEmptyOrElse(@Nullable T map, @Nullable Consumer<? super T> absentAction, @Nullable Consumer<? super T> presentAction) {
        if (isEmpty(map)) {
            if (absentAction != null) {
                absentAction.accept(map);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(map);
            }
        }
    }

    public static <T extends Map<?, ?>> void ifEmptyOrElse(@Nullable T map, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (isEmpty(map)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static <T extends Map<?, ?>> void ifNotEmpty(@Nullable T map, @Nullable Consumer<? super T> action) {
        if (isNotEmpty(map) && action != null) {
            action.accept(map);
        }
    }

    public static <T extends Map<?, ?>> void ifNotEmpty(@Nullable T map, @Nullable Runnable action) {
        if (isNotEmpty(map) && action != null) {
            action.run();
        }
    }

    public static <T extends Map<?, ?>> void ifSingleton(@Nullable T map, @Nullable Consumer<? super T> action) {
        ifSingletonOrElse(map, action, null);
    }

    public static <T extends Map<?, ?>> void ifSingleton(@Nullable T map, @Nullable Runnable action) {
        ifSingletonOrElse(map, action, null);
    }

    public static <T extends Map<?, ?>> void ifSingletonOrElse(@Nullable T map, @Nullable Consumer<? super T> presentAction, @Nullable Consumer<? super T> absentAction) {
        if (isSingleton(map)) {
            if (presentAction != null) {
                presentAction.accept(map);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(map);
            }
        }
    }

    public static <T extends Map<?, ?>> void ifSingletonOrElse(@Nullable T map, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isSingleton(map)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static <T extends Map<?, ?>> void ifMultitude(@Nullable T map, @Nullable Consumer<? super T> action) {
        if (isMultitude(map) && action != null) {
            action.accept(map);
        }
    }

    public static <T extends Map<?, ?>> void ifMultitude(@Nullable T map, @Nullable Runnable action) {
        if (isMultitude(map) && action != null) {
            action.run();
        }
    }

    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameSize(@Nullable Map<?, ?>... maps) {
        if (ArrayUtils.getLength(maps) < 2) {
            return false;
        }
        int size = size(ArrayUtils.get(maps, 0));
        return Arrays.stream(maps).skip(1L).allMatch(element -> size(element) == size);
    }

    public static boolean isSingleton(@Nullable Map<?, ?> map) {
        return size(map) == 1;
    }

    public static boolean isMultitude(@Nullable Map<?, ?> map) {
        return size(map) > 1;
    }

    public static <K> boolean isValueEmpty(@Nullable Map<K, ?> map, @Nullable K key) {
        return isEmpty(map) || ObjectUtils.isEmpty(map.get(key));
    }

    public static <K, V extends CharSequence> boolean isValueBlank(@Nullable Map<K, V> map, @Nullable K key) {
        return isEmpty(map) || StringUtils.isBlank(map.get(key));
    }

    public static int maxSize(@Nullable Map<?, ?>... maps) {
        return maxSize(ArrayUtilsWraps.asList(maps));
    }

    public static int maxSize(@Nullable Collection<Map<?, ?>> maps) {
        return CollectionPlainWraps.isEmpty(maps) ? 0 : maps.stream().mapToInt(MapPlainWraps::size).max().orElse(0);
    }

    @Nonnull
    public static <K, V> HashMap<K, V> newHashMapIfNull(@Nullable Map<K, V> map) {
        return (map instanceof HashMap<K, V> alias) ? alias : (map == null ? new HashMap<>() : new HashMap<>(map));
    }

    /**
     * @see "org.springframework.util.CollectionUtils#newHashMap"
     * @see "com.google.common.collect.Maps#newHashMapWithExpectedSize"
     */
    @Nonnull
    public static <K, V> HashMap<K, V> newHashMapSizing(int size) {
        return (size < 0) ? new HashMap<>() : new HashMap<>((int) ((float) size / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    @Nonnull
    public static <K, V> HashMap<K, V> newHashMapWithin(@Nullable K key, @Nullable V value) {
        HashMap<K, V> map = new HashMap<>(1);
        map.put(key, value);
        return map;
    }

    @Nonnull
    public static <K, V> HashMap<K, V> newHashMapWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2) {
        HashMap<K, V> map = new HashMap<>(2);
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }

    @Nonnull
    public static <K, V> HashMap<K, V> newHashMapWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2, @Nullable K key3, @Nullable V value3) {
        HashMap<K, V> map = new HashMap<>(3);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        return map;
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> HashMap<K, V> newHashMapWithinFlat(@Nullable Map<? extends K, ? extends V>... maps) {
        return newHashMapWithinFlat(false, maps);
    }

    @Nullable
    @SafeVarargs
    public static <K, V> HashMap<K, V> newHashMapWithinFlat(boolean emptyAsNull, @Nullable Map<? extends K, ? extends V>... maps) {
        return newHashMapWithinFlat(emptyAsNull, ArrayUtilsWraps.asList(maps));
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> HashMap<K, V> newHashMapWithinFlat(@Nullable Collection<Map<? extends K, ? extends V>> maps) {
        return newHashMapWithinFlat(false, maps);
    }

    @Nullable
    public static <K, V> HashMap<K, V> newHashMapWithinFlat(boolean emptyAsNull, @Nullable Collection<Map<? extends K, ? extends V>> maps) {
        HashMap<K, V> result = new HashMap<>();
        putAll(result, maps);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapIfNull(@Nullable Map<K, V> map) {
        return (map instanceof ConcurrentHashMap<K, V> alias) ? alias : (map == null ? new ConcurrentHashMap<>() : new ConcurrentHashMap<>(map));
    }

    @Nonnull
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapSizing(int size) {
        return (size < 0) ? new ConcurrentHashMap<>() : new ConcurrentHashMap<>((int) ((float) size / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapWithin(@Nullable K key, @Nullable V value) {
        ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>(1);
        if (ObjectUtils.allNotNull(key, value)) {
            map.put(key, value);
        }
        return map;
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2) {
        ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>(2);
        if (ObjectUtils.allNotNull(key1, value1)) {
            map.put(key1, value1);
        }
        if (ObjectUtils.allNotNull(key2, value2)) {
            map.put(key2, value2);
        }
        return map;
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression", "DuplicatedCode"})
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2, @Nullable K key3, @Nullable V value3) {
        ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>(3);
        if (ObjectUtils.allNotNull(key1, value1)) {
            map.put(key1, value1);
        }
        if (ObjectUtils.allNotNull(key2, value2)) {
            map.put(key2, value2);
        }
        if (ObjectUtils.allNotNull(key3, value3)) {
            map.put(key3, value3);
        }
        return map;
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapWithinFlat(@Nullable Map<? extends K, ? extends V>... maps) {
        return newConcurrentHashMapWithinFlat(false, maps);
    }

    @Nullable
    @SafeVarargs
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapWithinFlat(boolean emptyAsNull, @Nullable Map<? extends K, ? extends V>... maps) {
        return newConcurrentHashMapWithinFlat(emptyAsNull, ArrayUtilsWraps.asList(maps));
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapWithinFlat(@Nullable Collection<Map<? extends K, ? extends V>> maps) {
        return newConcurrentHashMapWithinFlat(false, maps);
    }

    @Nullable
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapWithinFlat(boolean emptyAsNull, @Nullable Collection<Map<? extends K, ? extends V>> maps) {
        ConcurrentHashMap<K, V> result = new ConcurrentHashMap<>();
        putAll(result, maps);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapIfNull(@Nullable Map<K, V> map) {
        return (map instanceof LinkedHashMap<K, V> alias) ? alias : (map == null ? new LinkedHashMap<>() : new LinkedHashMap<>(map));
    }

    /**
     * @see "org.springframework.util.CollectionUtils#newLinkedHashMap"
     * @see "com.google.common.collect.Maps#newLinkedHashMapWithExpectedSize"
     */
    @Nonnull
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapSizing(int size) {
        return (size < 0) ? new LinkedHashMap<>() : new LinkedHashMap<>((int) ((float) size / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    @Nonnull
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithin(@Nullable K key, @Nullable V value) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>(1);
        map.put(key, value);
        return map;
    }

    @Nonnull
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>(2);
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }

    @Nonnull
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2, @Nullable K key3, @Nullable V value3) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>(3);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        return map;
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithinFlat(@Nullable Map<? extends K, ? extends V>... maps) {
        return newLinkedHashMapWithinFlat(false, maps);
    }

    @Nullable
    @SafeVarargs
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithinFlat(boolean emptyAsNull, @Nullable Map<? extends K, ? extends V>... maps) {
        return newLinkedHashMapWithinFlat(emptyAsNull, ArrayUtilsWraps.asList(maps));
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithinFlat(@Nullable Collection<Map<? extends K, ? extends V>> maps) {
        return newLinkedHashMapWithinFlat(false, maps);
    }

    @Nullable
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithinFlat(boolean emptyAsNull, @Nullable Collection<Map<? extends K, ? extends V>> maps) {
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        putAll(result, maps);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMapIfNull(@Nullable Map<K, V> map) {
        return (map instanceof TreeMap<K, V> alias) ? alias : (map == null ? new TreeMap<>() : new TreeMap<>(map));
    }

    /**
     * @see "com.google.common.collect.Maps#newTreeMap()"
     */
    @Nonnull
    public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMapWithin(@Nullable K key, @Nullable V value) {
        TreeMap<K, V> map = new TreeMap<>();
        if (key != null) {
            map.put(key, value);
        }
        return map;
    }

    @Nonnull
    public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMapWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2) {
        TreeMap<K, V> map = new TreeMap<>();
        if (key1 != null) {
            map.put(key1, value1);
        }
        if (key2 != null) {
            map.put(key2, value2);
        }
        return map;
    }

    @Nonnull
    public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMapWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2, @Nullable K key3, @Nullable V value3) {
        TreeMap<K, V> map = new TreeMap<>();
        if (key1 != null) {
            map.put(key1, value1);
        }
        if (key2 != null) {
            map.put(key2, value2);
        }
        if (key3 != null) {
            map.put(key3, value3);
        }
        return map;
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMapWithinFlat(@Nullable Map<? extends K, ? extends V>... maps) {
        return newTreeMapWithinFlat(false, maps);
    }

    @Nullable
    @SafeVarargs
    public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMapWithinFlat(boolean emptyAsNull, @Nullable Map<? extends K, ? extends V>... maps) {
        return newTreeMapWithinFlat(emptyAsNull, ArrayUtilsWraps.asList(maps));
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMapWithinFlat(@Nullable Collection<Map<? extends K, ? extends V>> maps) {
        return newTreeMapWithinFlat(false, maps);
    }

    @Nullable
    public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMapWithinFlat(boolean emptyAsNull, @Nullable Collection<Map<? extends K, ? extends V>> maps) {
        TreeMap<K, V> result = new TreeMap<>();
        putAll(result, maps);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nonnull
    public static <K, V> Hashtable<K, V> newHashtableIfNull(@Nullable Map<K, V> map) {
        return (map instanceof Hashtable<K, V> alias) ? alias : (map == null ? new Hashtable<>() : new Hashtable<>(map));
    }

    @Nonnull
    public static <K, V> Hashtable<K, V> newHashtableSizing(int size) {
        return (size < 0) ? new Hashtable<>() : new Hashtable<>((int) ((float) size / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    @Nonnull
    public static <K, V> Hashtable<K, V> newHashTableWithin(@Nullable K key, @Nullable V value) {
        Hashtable<K, V> table = new Hashtable<>(1);
        if (ObjectUtils.allNotNull(key, value)) {
            table.put(key, value);
        }
        return table;
    }

    @Nonnull
    public static <K, V> Hashtable<K, V> newHashTableWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2) {
        Hashtable<K, V> table = new Hashtable<>(2);
        if (ObjectUtils.allNotNull(key1, value1)) {
            table.put(key1, value1);
        }
        if (ObjectUtils.allNotNull(key2, value2)) {
            table.put(key2, value2);
        }
        return table;
    }

    @Nonnull
    @SuppressWarnings("DuplicatedCode")
    public static <K, V> Hashtable<K, V> newHashTableWithin(@Nullable K key1, @Nullable V value1, @Nullable K key2, @Nullable V value2, @Nullable K key3, @Nullable V value3) {
        Hashtable<K, V> table = new Hashtable<>(3);
        if (ObjectUtils.allNotNull(key1, value1)) {
            table.put(key1, value1);
        }
        if (ObjectUtils.allNotNull(key2, value2)) {
            table.put(key2, value2);
        }
        if (ObjectUtils.allNotNull(key3, value3)) {
            table.put(key3, value3);
        }
        return table;
    }

    @Nonnull
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> Hashtable<K, V> newHashtableWithinFlat(@Nullable Map<? extends K, ? extends V>... maps) {
        return newHashtableWithinFlat(false, maps);
    }

    @Nullable
    @SafeVarargs
    public static <K, V> Hashtable<K, V> newHashtableWithinFlat(boolean emptyAsNull, @Nullable Map<? extends K, ? extends V>... maps) {
        return newHashtableWithinFlat(emptyAsNull, ArrayUtilsWraps.asList(maps));
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> Hashtable<K, V> newHashtableWithinFlat(@Nullable Collection<Map<? extends K, ? extends V>> maps) {
        return newHashtableWithinFlat(false, maps);
    }

    @Nullable
    public static <K, V> Hashtable<K, V> newHashtableWithinFlat(boolean emptyAsNull, @Nullable Collection<Map<? extends K, ? extends V>> maps) {
        Hashtable<K, V> result = new Hashtable<>();
        putAll(result, maps);
        return (emptyAsNull && isEmpty(result)) ? null : result;
    }

    @Nullable
    public static <T extends Map<?, ?>> T nullIfEmpty(@Nullable T map) {
        return isEmpty(map) ? null : map;
    }

    public static <K, V> void putAll(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            target.putAll(source);
        }
    }

    @SafeVarargs
    public static <K, V> void putAll(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V>... sources) {
        putAll(target, ArrayUtilsWraps.asList(sources));
    }

    public static <K, V> void putAll(@Nullable Map<K, V> target, @Nullable Collection<Map<? extends K, ? extends V>> sources) {
        if (target != null && CollectionPlainWraps.isNotEmpty(sources)) {
            sources.stream().filter(MapPlainWraps::isNotEmpty).forEach(Failable.asConsumer(target::putAll));
        }
    }

    public static <K extends CharSequence, V extends CharSequence> void putAllIfAllNotBlank(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            source.forEach(Failable.asBiConsumer((key, value) -> putIfAllNotBlank(target, key, value)));
        }
    }

    public static <K, V> void putAllIfAllNotEmpty(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            source.forEach(Failable.asBiConsumer((key, value) -> putIfAllNotEmpty(target, key, value)));
        }
    }

    public static <K, V> void putAllIfAllNotNull(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            source.forEach(Failable.asBiConsumer((key, value) -> putIfAllNotNull(target, key, value)));
        }
    }

    public static <K, V> void putAllIfKeyNotNull(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            source.forEach(Failable.asBiConsumer((key, value) -> putIfKeyNotNull(target, key, value)));
        }
    }

    public static <K extends CharSequence, V> void putAllIfKeyNotBlank(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            source.forEach(Failable.asBiConsumer((key, value) -> putIfKeyNotBlank(target, key, value)));
        }
    }

    public static <K, V> void putAllIfKeyNotEmpty(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            source.forEach(Failable.asBiConsumer((key, value) -> putIfKeyNotEmpty(target, key, value)));
        }
    }

    public static <K, V> void putAllIfValueNotNull(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            source.forEach(Failable.asBiConsumer((key, value) -> putIfValueNotNull(target, key, value)));
        }
    }

    public static <K, V extends CharSequence> void putAllIfValueNotBlank(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            source.forEach(Failable.asBiConsumer((key, value) -> putIfValueNotBlank(target, key, value)));
        }
    }

    public static <K, V> void putAllIfValueNotEmpty(@Nullable Map<K, V> target, @Nullable Map<? extends K, ? extends V> source) {
        if (target != null && isNotEmpty(source)) {
            source.forEach(Failable.asBiConsumer((key, value) -> putIfValueNotEmpty(target, key, value)));
        }
    }

    @Nullable
    public static <K extends CharSequence, V extends CharSequence> V putIfAllNotBlank(@Nullable Map<K, V> map, @Nullable K key, @Nullable V value) {
        return (map == null || StringUtils.isAnyBlank(key, value)) ? null : map.put(key, value);
    }

    @Nullable
    public static <K, V> V putIfAllNotEmpty(@Nullable Map<K, V> map, @Nullable K key, @Nullable V value) {
        return (map == null || ObjectUtilsWraps.anyEmpty(key, value)) ? null : map.put(key, value);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> V putIfAllNotNull(@Nullable Map<K, V> target, @Nullable K key, @Nullable V value) {
        return ObjectUtils.anyNull(target, key, value) ? null : target.put(key, value);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> V putIfKeyNotNull(@Nullable Map<K, V> target, @Nullable K key, @Nullable V value) {
        return ObjectUtils.anyNull(target, key) ? null : target.put(key, value);
    }

    @Nullable
    public static <K extends CharSequence, V> V putIfKeyNotBlank(@Nullable Map<K, V> target, @Nullable K key, @Nullable V value) {
        return (target == null || StringUtils.isBlank(key)) ? null : target.put(key, value);
    }

    @Nullable
    public static <K, V> V putIfKeyNotEmpty(@Nullable Map<K, V> target, @Nullable K key, @Nullable V value) {
        return (target == null || ObjectUtils.isEmpty(key)) ? null : target.put(key, value);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K, V> V putIfValueNotNull(@Nullable Map<K, V> target, @Nullable K key, @Nullable V value) {
        return ObjectUtils.anyNull(target, value) ? null : target.put(key, value);
    }

    @Nullable
    public static <K, V extends CharSequence> V putIfValueNotBlank(@Nullable Map<K, V> target, @Nullable K key, @Nullable V value) {
        return (target == null || StringUtils.isBlank(value)) ? null : target.put(key, value);
    }

    @Nullable
    public static <K, V> V putIfValueNotEmpty(@Nullable Map<K, V> target, @Nullable K key, @Nullable V value) {
        return (target == null || ObjectUtils.isEmpty(value)) ? null : target.put(key, value);
    }

    public static <K, V> void recompute(@Nullable Map<K, V> map, @Nullable BiFunction<? super K, ? super V, V> action) {
        recompute(map, action, null);
    }

    public static <K, V> void recompute(@Nullable Map<K, V> map, @Nullable BiFunction<? super K, ? super V, V> action, @Nullable BiPredicate<? super K, ? super V> filter) {
        if (isEmpty(map) || action == null) {
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (filter == null || filter.test(entry.getKey(), entry.getValue())) {
                map.put(entry.getKey(), action.apply(entry.getKey(), entry.getValue()));
            }
        }
    }

    @Nullable
    public static <K, V> V removeByKey(@Nullable Map<K, V> map, @Nullable K key) {
        return isEmpty(map) ? null : map.remove(key);
    }

    @SafeVarargs
    public static <K> void removeByKeys(@Nullable Map<? super K, ?> map, @Nullable K... keys) {
        removeByKeys(map, ArrayUtilsWraps.asList(keys));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <K> void removeByKeys(@Nullable Map<? super K, ?> map, @Nullable Collection<K> keys) {
        if (ObjectUtilsWraps.allNotEmpty(map, keys)) {
            keys.forEach(map::remove);
        }
    }

    @Nullable
    public static <K, V> K removeByValue(@Nullable Map<K, V> map, @Nullable V value) {
        if (isEmpty(map)) {
            return null;
        }
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            if (entry != null && Objects.equals(entry.getValue(), value)) {
                K result = entry.getKey();
                iterator.remove();
                return result;
            }
        }
        return null;
    }

    @SafeVarargs
    public static <V> void removeByValues(@Nullable Map<?, ? super V> map, @Nullable V... values) {
        removeByValues(map, ArrayUtilsWraps.asList(values));
    }

    public static <V> void removeByValues(@Nullable Map<?, ? super V> map, @Nullable Collection<V> values) {
        if (ObjectUtilsWraps.allNotEmpty(map, values)) {
            removeIfValue(map, value -> CollectionPlainWraps.contains(values, value));
        }
    }

    @Nullable
    public static Object removeClassKey(@Nullable Map<String, ?> map) {
        return isEmpty(map) ? null : map.remove(JavaKeywordConst.CLASS);
    }

    public static <K, V> void removeEmptyValue(@Nullable Map<K, V> map) {
        removeIfValue(map, ObjectUtils::isEmpty);
    }

    /**
     * @see java.util.Collection#removeIf
     */
    public static <K, V> boolean removeIf(@Nullable Map<K, V> map, @Nullable BiPredicate<? super K, ? super V> filter) {
        if (isEmpty(map) || filter == null) {
            return false;
        }
        boolean removed = false;
        Iterator<? extends Map.Entry<K, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            if (entry != null && filter.test(entry.getKey(), entry.getValue())) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    public static <K> boolean removeIfKey(@Nullable Map<K, ?> map, @Nullable Predicate<? super K> filter) {
        if (isEmpty(map) || filter == null) {
            return false;
        }
        boolean removed = false;
        Iterator<? extends Map.Entry<K, ?>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, ?> entry = iterator.next();
            if (entry != null && filter.test(entry.getKey())) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    public static <V> boolean removeIfValue(@Nullable Map<?, V> map, @Nullable Predicate<? super V> filter) {
        if (isEmpty(map) || filter == null) {
            return false;
        }
        boolean removed = false;
        Iterator<? extends Map.Entry<?, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<?, V> entry = iterator.next();
            if (entry != null && filter.test(entry.getValue())) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    @Nullable
    public static <K, V> V renameKey(@Nullable Map<K, V> map, @Nullable K oldKey, @Nullable K newKey) {
        if (map == null) {
            return null;
        }
        V result = map.remove(oldKey);
        map.put(newKey, result);
        return result;
    }

    public static <K, V> void reverseForEach(@Nullable Map<K, V> map, @Nullable BiConsumer<? super K, ? super V> action) {
        reverseForEach(map, action, null);
    }

    public static <K, V> void reverseForEach(@Nullable Map<K, V> map, @Nullable BiConsumer<? super K, ? super V> action, @Nullable BiPredicate<? super K, ? super V> filter) {
        if (isEmpty(map) || action == null) {
            return;
        }
        ListIterator<Map.Entry<K, V>> iterator = ListPlainWraps.listIteratorTailing(new ArrayList<>(map.entrySet()));
        Validate.notNull(iterator, AssertMessageConst.NOT_NULL);
        while (iterator.hasPrevious()) {
            Map.Entry<K, V> entry = iterator.previous();
            if (filter == null || filter.test(entry.getKey(), entry.getValue())) {
                action.accept(entry.getKey(), entry.getValue());
            }
        }
    }

    public static <K, V> void reverseForEachBreakable(@Nullable Map<K, V> map, @Nullable BiFunction<? super K, ? super V, Boolean> action) {
        reverseForEachBreakable(map, action, null);
    }

    public static <K, V> void reverseForEachBreakable(@Nullable Map<K, V> map, @Nullable BiFunction<? super K, ? super V, Boolean> action, @Nullable BiPredicate<? super K, ? super V> filter) {
        if (isEmpty(map) || action == null) {
            return;
        }
        ListIterator<Map.Entry<K, V>> iterator = ListPlainWraps.listIteratorTailing(new ArrayList<>(map.entrySet()));
        Validate.notNull(iterator, AssertMessageConst.NOT_NULL);
        while (iterator.hasPrevious()) {
            Map.Entry<K, V> entry = iterator.previous();
            if ((filter == null || filter.test(entry.getKey(), entry.getValue())) && BooleanUtils.isNotTrue(action.apply(entry.getKey(), entry.getValue()))) {
                break;
            }
        }
    }

    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static <K, V> Map.Entry<K, V> reverseForEachHeading(@Nullable Map<K, V> map, @Nullable BiConsumer<? super K, ? super V> action) {
        if (isEmpty(map) || action == null) {
            return null;
        }
        ListIterator<Map.Entry<K, V>> iterator = ListPlainWraps.listIteratorTailing(new ArrayList<>(map.entrySet()));
        Validate.notNull(iterator, AssertMessageConst.NOT_NULL);
        int index = map.size() - 1;
        while (iterator.hasPrevious()) {
            Map.Entry<K, V> entry = iterator.previous();
            if (index > 0) {
                action.accept(entry.getKey(), entry.getValue());
            } else {
                return entry;
            }
            index--;
        }
        return null;
    }

    public static <K, V> void reverseForEachIndexing(@Nullable Map<K, V> map, @Nullable TriConsumer<Integer, ? super K, ? super V> action) {
        reverseForEachIndexing(map, action, null);
    }

    @SuppressWarnings("DuplicatedCode")
    public static <K, V> void reverseForEachIndexing(@Nullable Map<K, V> map, @Nullable TriConsumer<Integer, ? super K, ? super V> action, @Nullable TriPredicate<Integer, ? super K, ? super V> filter) {
        if (isEmpty(map) || action == null) {
            return;
        }
        ListIterator<Map.Entry<K, V>> iterator = ListPlainWraps.listIteratorTailing(new ArrayList<>(map.entrySet()));
        Validate.notNull(iterator, AssertMessageConst.NOT_NULL);
        int index = map.size() - 1;
        while (iterator.hasPrevious()) {
            Map.Entry<K, V> entry = iterator.previous();
            if (filter == null || filter.test(index, entry.getKey(), entry.getValue())) {
                action.accept(index, entry.getKey(), entry.getValue());
            }
            index--;
        }
    }

    public static <K, V> void reverseForEachIndexingBreakable(@Nullable Map<K, V> map, @Nullable TriFunction<Integer, ? super K, ? super V, Boolean> action) {
        reverseForEachIndexingBreakable(map, action, null);
    }

    @SuppressWarnings("DuplicatedCode")
    public static <K, V> void reverseForEachIndexingBreakable(@Nullable Map<K, V> map, @Nullable TriFunction<Integer, ? super K, ? super V, Boolean> action, @Nullable TriPredicate<Integer, ? super K, ? super V> filter) {
        if (isEmpty(map) || action == null) {
            return;
        }
        ListIterator<Map.Entry<K, V>> iterator = ListPlainWraps.listIteratorTailing(new ArrayList<>(map.entrySet()));
        Validate.notNull(iterator, AssertMessageConst.NOT_NULL);
        int index = map.size() - 1;
        while (iterator.hasPrevious()) {
            Map.Entry<K, V> entry = iterator.previous();
            if ((filter == null || filter.test(index, entry.getKey(), entry.getValue())) && BooleanUtils.isNotTrue(action.apply(index, entry.getKey(), entry.getValue()))) {
                break;
            }
            index--;
        }
    }

    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static <K, V> Map.Entry<K, V> reverseForEachIndexingHeading(@Nullable Map<K, V> map, @Nullable TriConsumer<Integer, ? super K, ? super V> action) {
        if (isEmpty(map) || action == null) {
            return null;
        }
        ListIterator<Map.Entry<K, V>> iterator = ListPlainWraps.listIteratorTailing(new ArrayList<>(map.entrySet()));
        Validate.notNull(iterator, AssertMessageConst.NOT_NULL);
        int index = map.size() - 1;
        while (iterator.hasPrevious()) {
            Map.Entry<K, V> entry = iterator.previous();
            if (index > 0) {
                action.accept(index, entry.getKey(), entry.getValue());
            } else {
                return entry;
            }
            index--;
        }
        return null;
    }

    public static int size(@Nullable Map<?, ?> map) {
        return (map == null) ? 0 : map.size();
    }

    /**
     * Returns the sorted map which converts the plain list to tree list, joining with the children prop
     *
     * @param maps the source maps to inspect
     * @param idKey the id key to identify each record, such as "id"
     * @param pidKey the parent key to identify the parent record of current, such as "pid"
     * @param childrenKey the children key to organize children records, such as "children"
     *
     * @return the sorted map which converts the plain list to tree list, joining with the children prop
     */
    @Nullable
    public static List<Map<String, Object>> sortChildrenTree(@Nullable Collection<Map<String, Object>> maps, @Nullable String idKey, @Nullable String pidKey, @Nullable String childrenKey) {
        return sortChildrenTree(maps, idKey, pidKey, childrenKey, (Comparator<Map<String, Object>>) null);
    }

    /**
     * Returns the sorted map which converts the plain list to tree list, joining with the children prop
     *
     * @param maps the source maps to inspect
     * @param idKey the id key to identify each record, such as "id"
     * @param pidKey the parent key to identify the parent record of current, such as "pid"
     * @param childrenKey the children key to organize children records, such as "children"
     * @param orderKey the order key to sort records, such as "order", note the value must implement {@link java.lang.Comparable}
     *
     * @return the sorted map which converts the plain list to tree list, joining with the children prop
     */
    @Nullable
    public static List<Map<String, Object>> sortChildrenTree(@Nullable Collection<Map<String, Object>> maps, @Nullable String idKey, @Nullable String pidKey, @Nullable String childrenKey, @Nullable String orderKey) {
        return sortChildrenTree(maps, idKey, pidKey, childrenKey, (m1, m2) -> ObjectUtils.compare(MapPlainWraps.getObjectAs(m1, orderKey, Comparable.class), MapPlainWraps.getObjectAs(m2, orderKey, Comparable.class), true));
    }

    /**
     * Returns the sorted map which converts the plain list to tree list, joining with the children prop
     *
     * @param maps the source maps to inspect
     * @param idKey the id key to identify each record, such as "id"
     * @param pidKey the parent key to identify the parent record of current, such as "pid"
     * @param childrenKey the children key to organize children records, such as "children"
     * @param comparator comparator to sort records
     *
     * @return the sorted map which converts the plain list to tree list, joining with the children prop
     */
    @Nullable
    public static List<Map<String, Object>> sortChildrenTree(@Nullable Collection<Map<String, Object>> maps, @Nullable String idKey, @Nullable String pidKey, @Nullable String childrenKey, @Nullable Comparator<Map<String, Object>> comparator) {
        if (CollectionPlainWraps.isEmpty(maps) || StringUtils.isAnyBlank(idKey, pidKey, childrenKey)) {
            return null;
        }
        Map<String, Map<String, Object>> indexes = new HashMap<>(maps.size());
        for (Map<String, Object> map : maps) {
            String idValue = getString(map, idKey);
            if (StringUtils.isNotBlank(idValue)) {
                Map<String, Object> alias = new LinkedHashMap<>(map);
                alias.remove(childrenKey);
                indexes.put(idValue, alias);
            }
        }
        if (isEmpty(indexes)) {
            return null;
        }
        List<Map<String, Object>> roots = new ArrayList<>(maps.size());
        for (Map<String, Object> map : maps) {
            String pidValue = getString(map, pidKey);
            if (StringUtils.isBlank(pidValue)) {
                roots.add(map);
            } else {
                Map<String, Object> parent = indexes.get(pidValue);
                if (parent != null) {
                    Object children = getObject(parent, childrenKey);
                    if (children == null) {
                        parent.put(childrenKey, CollectionPlainWraps.newArrayListWithin(map));
                    } else if (children instanceof Collection<?> alias) {
                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> clone = new ArrayList<>((Collection<? extends Map<String, Object>>) alias);
                        clone.add(map);
                        if (comparator != null) {
                            clone.sort(comparator);
                        }
                        parent.put(childrenKey, clone);
                    }
                }
            }
        }
        return roots.isEmpty() ? null : roots;
    }

    public static int sumSize(@Nullable Map<?, ?>... maps) {
        return sumSize(ArrayUtilsWraps.asList(maps));
    }

    public static int sumSize(@Nullable Collection<Map<?, ?>> maps) {
        return CollectionPlainWraps.isEmpty(maps) ? 0 : maps.stream().mapToInt(MapPlainWraps::size).sum();
    }
}
