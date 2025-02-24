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
    public static <K, E extends Enum<E> & KeyValueEnum<K, ?>> boolean containsKey(@Nullable Class<? extends E> enumClass, @Nullable K key) {
        return enumClass != null && ArrayUtils.isNotEmpty(enumClass.getEnumConstants()) && Arrays.stream(enumClass.getEnumConstants()).anyMatch(element -> Objects.equals(element.getKey(), key));
    }

    public static <K extends CharSequence, E extends Enum<E> & KeyValueEnum<K, ?>> boolean containsKeyIgnoreCase(@Nullable Class<? extends E> enumClass, @Nullable K key) {
        return enumClass != null && ArrayUtils.isNotEmpty(enumClass.getEnumConstants()) && Arrays.stream(enumClass.getEnumConstants()).anyMatch(element -> StringUtils.equalsIgnoreCase(element.getKey(), key));
    }

    public static <V, E extends Enum<E> & ValueEnum<V>> boolean containsValue(@Nullable Class<? extends E> enumClass, @Nullable V value) {
        return enumClass != null && ArrayUtils.isNotEmpty(enumClass.getEnumConstants()) && Arrays.stream(enumClass.getEnumConstants()).anyMatch(element -> Objects.equals(element.getValue(), value));
    }

    public static <V extends CharSequence, E extends Enum<E> & ValueEnum<V>> boolean containsValueIgnoreCase(@Nullable Class<? extends E> enumClass, @Nullable V value) {
        return enumClass != null && ArrayUtils.isNotEmpty(enumClass.getEnumConstants()) && Arrays.stream(enumClass.getEnumConstants()).anyMatch(element -> StringUtils.equalsIgnoreCase(element.getValue(), value));
    }

    @Nullable
    public static <K, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKey(@Nullable Class<? extends E> enumClass, @Nullable K key) {
        return (enumClass == null || ArrayUtils.isEmpty(enumClass.getEnumConstants())) ? null : Arrays.stream(enumClass.getEnumConstants()).filter(element -> Objects.equals(element.getKey(), key)).findFirst().orElse(null);
    }

    @Nullable
    public static <K extends CharSequence, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKeyIgnoreCase(@Nullable Class<? extends E> enumClass, @Nullable K key) {
        return (enumClass == null || ArrayUtils.isEmpty(enumClass.getEnumConstants())) ? null : Arrays.stream(enumClass.getEnumConstants()).filter(element -> StringUtils.equalsIgnoreCase(element.getKey(), key)).findFirst().orElse(null);
    }

    @Nullable
    public static <V, E extends Enum<E> & ValueEnum<V>> E ofValue(@Nullable Class<? extends E> enumClass, @Nullable V value) {
        return (enumClass == null || ArrayUtils.isEmpty(enumClass.getEnumConstants())) ? null : Arrays.stream(enumClass.getEnumConstants()).filter(element -> Objects.equals(element.getValue(), value)).findFirst().orElse(null);
    }

    @Nullable
    public static <V extends CharSequence, E extends Enum<E> & ValueEnum<V>> E ofValueIgnoreCase(@Nullable Class<? extends E> enumClass, @Nullable V value) {
        return (enumClass == null || ArrayUtils.isEmpty(enumClass.getEnumConstants())) ? null : Arrays.stream(enumClass.getEnumConstants()).filter(element -> StringUtils.equalsIgnoreCase(element.getValue(), value)).findFirst().orElse(null);
    }

    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> K getKeyByValue(@Nullable Class<? extends E> enumClass, @Nullable V value) {
        KeyValueEnum<K, V> result = ofValue(enumClass, value);
        return (result == null) ? null : result.getKey();
    }

    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> V getValueByKey(@Nullable Class<? extends E> enumClass, @Nullable K key) {
        KeyValueEnum<K, V> result = ofKey(enumClass, key);
        return (result == null) ? null : result.getValue();
    }
}
