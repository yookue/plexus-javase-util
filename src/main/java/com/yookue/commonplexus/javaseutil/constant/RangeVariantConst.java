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

package com.yookue.commonplexus.javaseutil.constant;


import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.math.NumberUtils;


/**
 * Constants for range variants
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.Range
 */
@SuppressWarnings("unused")
public abstract class RangeVariantConst {
    public static final Range<Long> LONG_INTEGER = Range.between(NumberUtils.LONG_INT_MIN_VALUE, NumberUtils.LONG_INT_MAX_VALUE);
    public static final Range<Character> LOWER_ALPHABETIC = Range.between('a', 'z');
    public static final Range<Character> UPPER_ALPHABETIC = Range.between('A', 'Z');
}
