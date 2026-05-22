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


import java.util.List;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.Range;


/**
 * Utilities for {@link org.apache.commons.lang3.Range}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.Range
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class RangeUtilsWraps {
    /**
     * Checks if any of the given ranges overlap with each other
     *
     * @param ranges the ranges to check for overlaps
     *
     * @return {@code true} if any two ranges overlap, {@code false} otherwise
     */
    @SafeVarargs
    public static <T extends Comparable<T>> boolean anyOverlap(@Nullable Range<T>... ranges) {
        return anyOverlap(ArrayUtilsWraps.asList(ranges));
    }

    /**
     * Checks if any of the given ranges in the list overlap with each other
     *
     * @param ranges the list of ranges to check for overlaps
     *
     * @return {@code true} if any two ranges overlap, {@code false} otherwise or if the list is null or has less than 2 elements
     */
    public static <T extends Comparable<T>> boolean anyOverlap(@Nullable List<Range<T>> ranges) {
        if (ranges == null || ranges.size() < 2) {
            return false;
        }
        for (int i = 0; i < ranges.size() - 1; i++) {
            for (int j = i + 1; j < ranges.size(); j++) {
                Range<T> iRange = ranges.get(i), jRange = ranges.get(j);
                if (iRange != null && jRange != null && iRange.isOverlappedBy(jRange)) {
                    return true;
                }
            }
        }
        return false;
    }
}
