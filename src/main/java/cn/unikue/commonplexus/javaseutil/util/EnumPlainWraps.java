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


/**
 * Utilities for {@link java.lang.Enum}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class EnumPlainWraps {
    public static <E extends Enum<E>> boolean containsName(@Nullable Class<E> enumClazz, @Nullable String name) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> Objects.equals(item.name(), name));
    }

    public static <E extends Enum<E>> boolean containsNameIgnoreCase(@Nullable Class<E> enumClazz, @Nullable String name) {
        return enumClazz != null && ArrayUtils.isNotEmpty(enumClazz.getEnumConstants()) && Arrays.stream(enumClazz.getEnumConstants()).anyMatch(item -> StringUtilsWraps.equalsIgnoreCase(item.name(), name));
    }

    @Nullable
    public static <E extends Enum<E>> E ofName(@Nullable Class<E> enumClazz, @Nullable String name) {
        return ofName(enumClazz, name, null);
    }

    @Nullable
    public static <E extends Enum<E>> E ofName(@Nullable Class<E> enumClazz, @Nullable String name, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> Objects.equals(item.name(), name)).findFirst().orElse(defaultValue);
    }

    @Nullable
    public static <E extends Enum<E>> E ofNameIgnoreCase(@Nullable Class<E> enumClazz, @Nullable String name) {
        return ofNameIgnoreCase(enumClazz, name, null);
    }

    @Nullable
    public static <E extends Enum<E>> E ofNameIgnoreCase(@Nullable Class<E> enumClazz, @Nullable String name, @Nullable E defaultValue) {
        return (enumClazz == null || ArrayUtils.isEmpty(enumClazz.getEnumConstants())) ? defaultValue : Arrays.stream(enumClazz.getEnumConstants()).filter(item -> StringUtilsWraps.equalsIgnoreCase(item.name(), name)).findFirst().orElse(defaultValue);
    }
}
