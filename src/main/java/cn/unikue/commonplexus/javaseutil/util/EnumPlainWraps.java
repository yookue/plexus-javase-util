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


/**
 * Utilities for {@link java.lang.Enum}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class EnumPlainWraps {
    /**
     * Checks if the enum class contains an enum constant with the specified name
     *
     * @param enumClazz the enum class to check
     * @param name the name to look for
     *
     * @return {@code true} if the enum class contains a constant with the specified name, {@code false} otherwise
     */
    public static <E extends Enum<E>> boolean containsName(@Nullable Class<E> enumClazz, @Nullable String name) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> Objects.equals(item.name(), name));
    }

    /**
     * Checks if the enum class contains an enum constant with the specified name (case-insensitive)
     *
     * @param enumClazz the enum class to check
     * @param name the name to look for (case-insensitive)
     *
     * @return {@code true} if the enum class contains a constant with the specified name, {@code false} otherwise
     */
    public static <E extends Enum<E>> boolean containsNameIgnoreCase(@Nullable Class<E> enumClazz, @Nullable String name) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> StringUtils.equalsIgnoreCase(item.name(), name));
    }

    /**
     * Gets the enum constant with the specified name
     *
     * @param enumClazz the enum class to search in
     * @param name the name of the enum constant to find
     *
     * @return the enum constant with the specified name, or {@code null} if not found
     */
    @Nullable
    public static <E extends Enum<E>> E ofName(@Nullable Class<E> enumClazz, @Nullable String name) {
        return ofName(enumClazz, name, null);
    }

    /**
     * Gets the enum constant with the specified name, or returns the default value if not found
     *
     * @param enumClazz the enum class to search in
     * @param name the name of the enum constant to find
     * @param defaultValue the default value to return if the enum constant is not found
     *
     * @return the enum constant with the specified name, or the default value if not found
     */
    @Nullable
    public static <E extends Enum<E>> E ofName(@Nullable Class<E> enumClazz, @Nullable String name, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> Objects.equals(item.name(), name)).findFirst().orElse(defaultValue);
    }

    /**
     * Gets the enum constant with the specified name (case-insensitive)
     *
     * @param enumClazz the enum class to search in
     * @param name the name of the enum constant to find (case-insensitive)
     *
     * @return the enum constant with the specified name, or {@code null} if not found
     */
    @Nullable
    public static <E extends Enum<E>> E ofNameIgnoreCase(@Nullable Class<E> enumClazz, @Nullable String name) {
        return ofNameIgnoreCase(enumClazz, name, null);
    }

    /**
     * Gets the enum constant with the specified name (case-insensitive), or returns the default value if not found
     *
     * @param enumClazz the enum class to search in
     * @param name the name of the enum constant to find (case-insensitive)
     * @param defaultValue the default value to return if the enum constant is not found
     *
     * @return the enum constant with the specified name, or the default value if not found
     */
    @Nullable
    public static <E extends Enum<E>> E ofNameIgnoreCase(@Nullable Class<E> enumClazz, @Nullable String name, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> StringUtils.equalsIgnoreCase(item.name(), name)).findFirst().orElse(defaultValue);
    }
}
