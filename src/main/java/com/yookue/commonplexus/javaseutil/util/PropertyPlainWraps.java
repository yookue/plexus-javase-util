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


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link java.util.Properties}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class PropertyPlainWraps {
    @Nullable
    public static Properties fromMap(@Nullable Map<?, ?> map) {
        if (MapPlainWraps.isEmpty(map)) {
            return null;
        }
        Properties result = new Properties();
        result.putAll(map);
        return result;
    }

    @Nonnull
    public static Properties newPropertiesWithin(@Nullable Object key, @Nullable Object value) {
        Properties result = new Properties();
        if (ObjectUtils.allNotNull(key, value)) {
            result.put(key, value);
        }
        return result;
    }

    @Nonnull
    public static Properties newPropertiesWithin(@Nullable Object key1, @Nullable Object value1, @Nullable Object key2, @Nullable Object value2) {
        Properties result = new Properties();
        if (ObjectUtils.allNotNull(key1, value1)) {
            result.put(key1, value1);
        }
        if (ObjectUtils.allNotNull(key2, value2)) {
            result.put(key2, value2);
        }
        return result;
    }

    @Nonnull
    public static Properties newPropertiesWithin(@Nullable Object key1, @Nullable Object value1, @Nullable Object key2, @Nullable Object value2, @Nullable Object key3, @Nullable Object value3) {
        Properties result = new Properties();
        if (ObjectUtils.allNotNull(key1, value1)) {
            result.put(key1, value1);
        }
        if (ObjectUtils.allNotNull(key2, value2)) {
            result.put(key2, value2);
        }
        if (ObjectUtils.allNotNull(key3, value3)) {
            result.put(key3, value3);
        }
        return result;
    }

    /**
     * Returns a new {@code Map<String, Object>} object that converted from the given properties
     *
     * @param properties the properties to be converted
     *
     * @return a new {@code Map<String, Object>} object that converted from the given properties
     *
     * @see "org.springframework.util.CollectionUtils#mergePropertiesIntoMap"
     */
    @Nullable
    public static Map<String, Object> toStringObjectMap(@Nullable Properties properties) {
        if (MapPlainWraps.isEmpty(properties)) {
            return null;
        }
        Map<String, Object> result = new LinkedHashMap<>(properties.size());
        properties.forEach((key, value) -> result.put(Objects.toString(key), value));
        return result;
    }

    /**
     * Returns a new {@code Map<String, String>} object that converted from the given properties
     *
     * @param properties the properties to be converted
     *
     * @return a new {@code Map<String, String>} object that converted from the given properties
     *
     * @see "org.springframework.util.CollectionUtils#mergePropertiesIntoMap"
     */
    @Nullable
    public static Map<String, String> toStringStringMap(@Nullable Properties properties) {
        if (MapPlainWraps.isEmpty(properties)) {
            return null;
        }
        Map<String, String> result = new LinkedHashMap<>(properties.size());
        properties.forEach((key, value) -> result.put(Objects.toString(key), Objects.toString(value, null)));
        return result;
    }
}
