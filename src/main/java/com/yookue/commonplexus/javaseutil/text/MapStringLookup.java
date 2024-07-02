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

package com.yookue.commonplexus.javaseutil.text;


import java.util.Map;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.lookup.StringLookup;


/**
 * Lookup for {@link org.apache.commons.text.lookup.StringLookup}
 *
 * @author David Hsing
 * @see org.apache.commons.text.StringSubstitutor#StringSubstitutor(org.apache.commons.text.lookup.StringLookup)
 * @see "org.apache.commons.text.StrLookup.MapStrLookup"
 */
@SuppressWarnings("unused")
public class MapStringLookup<V> implements StringLookup {
    private final Map<String, V> map;
    private final boolean nullKeyAsPlaceholder;
    private final boolean nullValueAsPlaceholder;
    private final String placeholder;

    public MapStringLookup(@Nullable Map<String, V> map) {
        this(map, false, true, StringUtils.EMPTY);
    }

    public MapStringLookup(@Nullable Map<String, V> map, boolean nullKeyAsPlaceholder, boolean nullValueAsPlaceholder) {
        this(map, nullKeyAsPlaceholder, nullValueAsPlaceholder, StringUtils.EMPTY);
    }

    public MapStringLookup(@Nullable Map<String, V> map, boolean nullKeyAsPlaceholder, boolean nullValueAsPlaceholder, @Nullable String placeholder) {
        this.map = map;
        this.nullKeyAsPlaceholder = nullKeyAsPlaceholder;
        this.nullValueAsPlaceholder = nullValueAsPlaceholder;
        this.placeholder = placeholder;
    }

    @Override
    public String lookup(@Nullable String key) {
        if (map == null) {
            return null;
        }
        if (!map.containsKey(key)) {
            return nullKeyAsPlaceholder ? placeholder : null;
        }
        Object value = map.get(key);
        if (value == null) {
            return nullValueAsPlaceholder ? placeholder : null;
        }
        return value.toString();
    }
}
