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


import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import jakarta.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;


/**
 * Utilities for {@link org.apache.commons.collections4.MapUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.collections4.MapUtils
 * @see "com.google.common.collect.Maps"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class MapUtilsWraps {
    public static <K> boolean containsKey(@Nullable Map<? super K, ?> map, @Nullable K key) {
        return MapUtils.isNotEmpty(map) && map.containsKey(key);
    }

    public static <V> boolean containsValue(@Nullable Map<?, ? super V> map, @Nullable V value) {
        return MapUtils.isNotEmpty(map) && map.containsValue(value);
    }

    public static <K, V> boolean containsKeyValue(@Nullable Map<K, V> map, @Nullable K key, @Nullable V value) {
        return MapUtils.isNotEmpty(map) && map.containsKey(key) && Objects.equals(map.get(key), value);
    }

    @SafeVarargs
    public static <K> boolean containsAllKeys(@Nullable Map<? super K, ?> map, @Nullable K... keys) {
        return containsAllKeys(map, ArrayUtilsWraps.asList(keys));
    }

    public static <K> boolean containsAllKeys(@Nullable Map<? super K, ?> map, @Nullable Collection<K> keys) {
        return MapUtils.isNotEmpty(map) && CollectionUtils.isNotEmpty(keys) && CollectionUtils.containsAll(map.keySet(), keys);
    }

    @SafeVarargs
    public static <K> boolean containsAnyKeys(@Nullable Map<? super K, ?> map, @Nullable K... keys) {
        return containsAnyKeys(map, ArrayUtilsWraps.asList(keys));
    }

    public static <K> boolean containsAnyKeys(@Nullable Map<? super K, ?> map, @Nullable Collection<K> keys) {
        return MapUtils.isNotEmpty(map) && CollectionUtils.isNotEmpty(keys) && CollectionUtils.containsAny(map.keySet(), keys);
    }

    @SafeVarargs
    public static <V> boolean containsAllValues(@Nullable Map<?, ? super V> map, @Nullable V... values) {
        return containsAllValues(map, ArrayUtilsWraps.asList(values));
    }

    public static <V> boolean containsAllValues(@Nullable Map<?, ? super V> map, @Nullable Collection<V> values) {
        return MapUtils.isNotEmpty(map) && CollectionUtils.isNotEmpty(values) && CollectionUtils.containsAll(map.values(), values);
    }

    @SafeVarargs
    public static <V> boolean containsAnyValues(@Nullable Map<?, ? super V> map, @Nullable V... values) {
        return containsAnyValues(map, ArrayUtilsWraps.asList(values));
    }

    public static <V> boolean containsAnyValues(@Nullable Map<?, ? super V> map, @Nullable Collection<V> values) {
        return MapUtils.isNotEmpty(map) && CollectionUtils.isNotEmpty(values) && CollectionUtils.containsAny(map.values(), values);
    }
}
