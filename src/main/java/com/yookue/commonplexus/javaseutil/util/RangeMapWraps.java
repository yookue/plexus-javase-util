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
import jakarta.annotation.Nullable;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;


/**
 * Utilities for {@link com.google.common.collect.RangeMap}
 *
 * @author David Hsing
 *
 * @see com.google.common.collect.RangeMap
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class RangeMapWraps {
    @SafeVarargs
    @SuppressWarnings("NullableProblems")
    public static <T extends Comparable<T>> boolean anyOverlap(@Nullable Range<T>... ranges) {
        return anyOverlap(ArrayUtilsWraps.asList(ranges));
    }

    @SuppressWarnings("NullableProblems")
    public static <T extends Comparable<T>> boolean anyOverlap(@Nullable Collection<Range<T>> ranges) {
        if (ranges == null || ranges.size() < 2) {
            return false;
        }
        RangeMap<T, Boolean> rangeMap = TreeRangeMap.create();
        for (Range<T> range : ranges) {
            if (range == null) {
                continue;
            }
            if (!rangeMap.subRangeMap(range).asMapOfRanges().isEmpty()) {
                return true;
            }
            rangeMap.put(range, true);
        }
        return false;
    }
}
