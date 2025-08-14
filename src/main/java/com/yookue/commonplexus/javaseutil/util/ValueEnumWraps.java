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
import java.util.Objects;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.support.KeyValueEnum;
import com.yookue.commonplexus.javaseutil.support.ValueEnum;


/**
 * Utilities for {@link com.yookue.commonplexus.javaseutil.support.ValueEnum}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ValueEnumWraps extends EnumPlainWraps {
    public static <K, E extends Enum<E> & KeyValueEnum<K, ?>> boolean containsKey(@Nullable Class<E> enumClazz, @Nullable K key) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> Objects.equals(item.getKey(), key));
    }

    public static <K extends CharSequence, E extends Enum<E> & KeyValueEnum<K, ?>> boolean containsKeyIgnoreCase(@Nullable Class<E> enumClazz, @Nullable K key) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> StringUtils.equalsIgnoreCase(item.getKey(), key));
    }

    public static <V, E extends Enum<E> & ValueEnum<V>> boolean containsValue(@Nullable Class<E> enumClazz, @Nullable V value) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> Objects.equals(item.getValue(), value));
    }

    public static <V extends CharSequence, E extends Enum<E> & ValueEnum<V>> boolean containsValueIgnoreCase(@Nullable Class<E> enumClazz, @Nullable V value) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> StringUtils.equalsIgnoreCase(item.getValue(), value));
    }

    @Nullable
    public static <K, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKey(@Nullable Class<E> enumClazz, @Nullable K key) {
        return ofKey(enumClazz, key, null);
    }

    @Nullable
    public static <K, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKey(@Nullable Class<E> enumClazz, @Nullable K key, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> Objects.equals(item.getKey(), key)).findFirst().orElse(defaultValue);
    }

    @Nullable
    public static <K extends CharSequence, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKeyIgnoreCase(@Nullable Class<E> enumClazz, @Nullable K key) {
        return ofKeyIgnoreCase(enumClazz, key, null);
    }

    @Nullable
    public static <K extends CharSequence, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKeyIgnoreCase(@Nullable Class<E> enumClazz, @Nullable K key, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> StringUtils.equalsIgnoreCase(item.getKey(), key)).findFirst().orElse(defaultValue);
    }

    @Nullable
    public static <V, E extends Enum<E> & ValueEnum<V>> E ofValue(@Nullable Class<E> enumClazz, @Nullable V value) {
        return ofValue(enumClazz, value, null);
    }

    @Nullable
    public static <V, E extends Enum<E> & ValueEnum<V>> E ofValue(@Nullable Class<E> enumClazz, @Nullable V value, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> Objects.equals(item.getValue(), value)).findFirst().orElse(defaultValue);
    }

    @Nullable
    public static <V extends CharSequence, E extends Enum<E> & ValueEnum<V>> E ofValueIgnoreCase(@Nullable Class<E> enumClazz, @Nullable V value) {
        return ofValueIgnoreCase(enumClazz, value, null);
    }

    @Nullable
    public static <V extends CharSequence, E extends Enum<E> & ValueEnum<V>> E ofValueIgnoreCase(@Nullable Class<E> enumClazz, @Nullable V value, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> StringUtils.equalsIgnoreCase(item.getValue(), value)).findFirst().orElse(defaultValue);
    }

    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> K getKeyByValue(@Nullable Class<E> enumClazz, @Nullable V value) {
        return getKeyByValue(enumClazz, value, null);
    }

    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> K getKeyByValue(@Nullable Class<E> enumClazz, @Nullable V value, @Nullable K defaultValue) {
        KeyValueEnum<K, V> result = ofValue(enumClazz, value);
        return (result == null) ? defaultValue : result.getKey();
    }

    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> V getValueByKey(@Nullable Class<E> enumClazz, @Nullable K key) {
        return getValueByKey(enumClazz, key, null);
    }

    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> V getValueByKey(@Nullable Class<E> enumClazz, @Nullable K key, @Nullable V defaultValue) {
        KeyValueEnum<K, V> result = ofKey(enumClazz, key);
        return (result == null) ? defaultValue : result.getValue();
    }
}
