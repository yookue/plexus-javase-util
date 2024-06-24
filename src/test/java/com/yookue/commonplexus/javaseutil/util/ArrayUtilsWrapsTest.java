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


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.util.ArrayUtilsWraps}
 *
 * @author David Hsing
 */
@Slf4j
class ArrayUtilsWrapsTest {
    @Test
    void booleanDistinct() {
        boolean result = ArrayUtilsWraps.isDistinct(new boolean[]{true, false});
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void booleanNotDistinct() {
        boolean result = ArrayUtilsWraps.isDistinct(new boolean[] {true, false, true, true});
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertFalse(result);
    }

    @Test
    void charDistinct() {
        boolean result = ArrayUtilsWraps.isDistinct(new char[] {'a', 'b'});
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void charNotDistinct() {
        boolean result = ArrayUtilsWraps.isDistinct(new char[] {'a', 'b', 'a', 'c'});
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertFalse(result);
    }
}
