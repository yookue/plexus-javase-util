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


/**
 * Utilities for {@link java.lang.Enum}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class EnumPlainWraps {
    public static <E extends Enum<E>> boolean containsName(@Nullable Class<? extends E> enumClass, @Nullable String name) {
        return enumClass != null && ArrayUtils.isNotEmpty(enumClass.getEnumConstants()) && Arrays.stream(enumClass.getEnumConstants()).anyMatch(element -> Objects.equals(element.name(), name));
    }

    public static <E extends Enum<E>> boolean containsNameIgnoreCase(@Nullable Class<? extends E> enumClass, @Nullable String name) {
        return enumClass != null && ArrayUtils.isNotEmpty(enumClass.getEnumConstants()) && Arrays.stream(enumClass.getEnumConstants()).anyMatch(element -> StringUtils.equalsIgnoreCase(element.name(), name));
    }

    @Nullable
    public static <E extends Enum<E>> E ofName(@Nullable Class<? extends E> enumClass, @Nullable String name) {
        return (enumClass == null || ArrayUtils.isEmpty(enumClass.getEnumConstants())) ? null : Arrays.stream(enumClass.getEnumConstants()).filter(element -> Objects.equals(element.name(), name)).findFirst().orElse(null);
    }

    @Nullable
    public static <E extends Enum<E>> E ofNameIgnoreCase(@Nullable Class<? extends E> enumClass, @Nullable String name) {
        return (enumClass == null || ArrayUtils.isEmpty(enumClass.getEnumConstants())) ? null : Arrays.stream(enumClass.getEnumConstants()).filter(element -> StringUtils.equalsIgnoreCase(element.name(), name)).findFirst().orElse(null);
    }
}
