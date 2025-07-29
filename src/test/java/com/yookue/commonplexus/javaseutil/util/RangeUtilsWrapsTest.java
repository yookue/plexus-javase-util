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


import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.util.RangeUtilsWraps}
 *
 * @author David Hsing
 */
@Slf4j
class RangeUtilsWrapsTest {
    @Test
    void anyOverlap() {
        Range<Integer> range1 = Range.of(1, 5);
        Range<Integer> range2 = Range.of(4, 8);
        Range<Integer> range3 = Range.of(7, 10);
        Range<Integer> range4 = Range.of(12, 15);
        boolean result = RangeUtilsWraps.anyOverlap(range1, range2, range3, range4);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }
}
