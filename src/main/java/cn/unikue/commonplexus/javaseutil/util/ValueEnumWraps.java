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


import java.util.Arrays;
import java.util.Objects;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.enumeration.KeyValueEnum;
import cn.unikue.commonplexus.javaseutil.enumeration.ValueEnum;


/**
 * Utilities for {@link cn.unikue.commonplexus.javaseutil.enumeration.ValueEnum}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ValueEnumWraps extends EnumPlainWraps {
    /**
     * Check if enum contains the specified key (case-sensitive).
     *
     * @param enumClazz the enum class to check
     * @param key the key to search for
     * @return true if the enum contains the key, false otherwise
     */
    public static <K, E extends Enum<E> & KeyValueEnum<K, ?>> boolean containsKey(@Nullable Class<E> enumClazz, @Nullable K key) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> Objects.equals(item.getKey(), key));
    }

    /**
     * Check if enum contains the specified key (case-insensitive).
     *
     * @param enumClazz the enum class to check
     * @param key the key to search for
     * @return true if the enum contains the key ignoring case, false otherwise
     */
    public static <K extends CharSequence, E extends Enum<E> & KeyValueEnum<K, ?>> boolean containsKeyIgnoreCase(@Nullable Class<E> enumClazz, @Nullable K key) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> StringUtils.equalsIgnoreCase(item.getKey(), key));
    }

    /**
     * Check if enum contains the specified value (case-sensitive).
     *
     * @param enumClazz the enum class to check
     * @param value the value to search for
     * @return true if the enum contains the value, false otherwise
     */
    public static <V, E extends Enum<E> & ValueEnum<V>> boolean containsValue(@Nullable Class<E> enumClazz, @Nullable V value) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> Objects.equals(item.getValue(), value));
    }

    /**
     * Check if enum contains the specified value (case-insensitive).
     *
     * @param enumClazz the enum class to check
     * @param value the value to search for
     * @return true if the enum contains the value ignoring case, false otherwise
     */
    public static <V extends CharSequence, E extends Enum<E> & ValueEnum<V>> boolean containsValueIgnoreCase(@Nullable Class<E> enumClazz, @Nullable V value) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> StringUtils.equalsIgnoreCase(item.getValue(), value));
    }

    /**
     * Get enum constant by key (case-sensitive), returning null if not found.
     *
     * @param enumClazz the enum class to search
     * @param key the key to match
     * @return the matching enum constant, or null if not found
     */
    @Nullable
    public static <K, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKey(@Nullable Class<E> enumClazz, @Nullable K key) {
        return ofKey(enumClazz, key, null);
    }

    /**
     * Get enum constant by key (case-sensitive), returning default value if not found.
     *
     * @param enumClazz the enum class to search
     * @param key the key to match
     * @param defaultValue the default value to return if not found
     * @return the matching enum constant, or defaultValue if not found
     */
    @Nullable
    public static <K, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKey(@Nullable Class<E> enumClazz, @Nullable K key, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> Objects.equals(item.getKey(), key)).findFirst().orElse(defaultValue);
    }

    /**
     * Get enum constant by key (case-insensitive), returning null if not found.
     *
     * @param enumClazz the enum class to search
     * @param key the key to match
     * @return the matching enum constant, or null if not found
     */
    @Nullable
    public static <K extends CharSequence, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKeyIgnoreCase(@Nullable Class<E> enumClazz, @Nullable K key) {
        return ofKeyIgnoreCase(enumClazz, key, null);
    }

    /**
     * Get enum constant by key (case-insensitive), returning default value if not found.
     *
     * @param enumClazz the enum class to search
     * @param key the key to match
     * @param defaultValue the default value to return if not found
     * @return the matching enum constant, or defaultValue if not found
     */
    @Nullable
    public static <K extends CharSequence, E extends Enum<E> & KeyValueEnum<K, ?>> E ofKeyIgnoreCase(@Nullable Class<E> enumClazz, @Nullable K key, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> StringUtils.equalsIgnoreCase(item.getKey(), key)).findFirst().orElse(defaultValue);
    }

    /**
     * Get enum constant by value (case-sensitive), returning null if not found.
     *
     * @param enumClazz the enum class to search
     * @param value the value to match
     * @return the matching enum constant, or null if not found
     */
    @Nullable
    public static <V, E extends Enum<E> & ValueEnum<V>> E ofValue(@Nullable Class<E> enumClazz, @Nullable V value) {
        return ofValue(enumClazz, value, null);
    }

    /**
     * Get enum constant by value (case-sensitive), returning default value if not found.
     *
     * @param enumClazz the enum class to search
     * @param value the value to match
     * @param defaultValue the default value to return if not found
     * @return the matching enum constant, or defaultValue if not found
     */
    @Nullable
    public static <V, E extends Enum<E> & ValueEnum<V>> E ofValue(@Nullable Class<E> enumClazz, @Nullable V value, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> Objects.equals(item.getValue(), value)).findFirst().orElse(defaultValue);
    }

    /**
     * Get enum constant by value (case-insensitive), returning null if not found.
     *
     * @param enumClazz the enum class to search
     * @param value the value to match
     * @return the matching enum constant, or null if not found
     */
    @Nullable
    public static <V extends CharSequence, E extends Enum<E> & ValueEnum<V>> E ofValueIgnoreCase(@Nullable Class<E> enumClazz, @Nullable V value) {
        return ofValueIgnoreCase(enumClazz, value, null);
    }

    /**
     * Get enum constant by value (case-insensitive), returning default value if not found.
     *
     * @param enumClazz the enum class to search
     * @param value the value to match
     * @param defaultValue the default value to return if not found
     * @return the matching enum constant, or defaultValue if not found
     */
    @Nullable
    public static <V extends CharSequence, E extends Enum<E> & ValueEnum<V>> E ofValueIgnoreCase(@Nullable Class<E> enumClazz, @Nullable V value, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> StringUtils.equalsIgnoreCase(item.getValue(), value)).findFirst().orElse(defaultValue);
    }

    /**
     * Get the key associated with the specified value, returning null if not found.
     *
     * @param enumClazz the enum class to search
     * @param value the value to look up
     * @return the key associated with the value, or null if not found
     */
    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> K getKeyByValue(@Nullable Class<E> enumClazz, @Nullable V value) {
        return getKeyByValue(enumClazz, value, null);
    }

    /**
     * Get the key associated with the specified value, returning default value if not found.
     *
     * @param enumClazz the enum class to search
     * @param value the value to look up
     * @param defaultValue the default value to return if not found
     * @return the key associated with the value, or defaultValue if not found
     */
    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> K getKeyByValue(@Nullable Class<E> enumClazz, @Nullable V value, @Nullable K defaultValue) {
        KeyValueEnum<K, V> result = ofValue(enumClazz, value);
        return (result == null) ? defaultValue : result.getKey();
    }

    /**
     * Get the value associated with the specified key, returning null if not found.
     *
     * @param enumClazz the enum class to search
     * @param key the key to look up
     * @return the value associated with the key, or null if not found
     */
    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> V getValueByKey(@Nullable Class<E> enumClazz, @Nullable K key) {
        return getValueByKey(enumClazz, key, null);
    }

    /**
     * Get the value associated with the specified key, returning default value if not found.
     *
     * @param enumClazz the enum class to search
     * @param key the key to look up
     * @param defaultValue the default value to return if not found
     * @return the value associated with the key, or defaultValue if not found
     */
    @Nullable
    public static <K, V, E extends Enum<E> & KeyValueEnum<K, V>> V getValueByKey(@Nullable Class<E> enumClazz, @Nullable K key, @Nullable V defaultValue) {
        KeyValueEnum<K, V> result = ofKey(enumClazz, key);
        return (result == null) ? defaultValue : result.getValue();
    }
}
