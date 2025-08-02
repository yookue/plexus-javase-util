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


import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.util.LatexMathWraps}
 *
 * @author David Hsing
 */
@Slf4j
class LatexMathWrapsTest {
    @Test
    void parseLatexToMath() {
        String result = LatexMathWraps.parseLatexToMath("x\\cdot2");
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertEquals("x*2", result);
    }

    @Test
    void evalLatexExpress() {
        Map<String, String> variables = Map.of("x", "3");
        Double result = LatexMathWraps.evalLatexExpress("x\\cdot2", variables);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertEquals(6.0, result);
    }

    @Test
    void evalMathExpress() {
        Map<String, String> variables = Map.of("x", "3");
        Double result = LatexMathWraps.evalMathExpress("x*2", variables);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertEquals(6.0, result);
    }

    @Test
    void testLatexExpress() {
        Map<String, String> variables = Map.of("x", "3");
        boolean result = LatexMathWraps.testLatexExpress("x\\cdot2", variables);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void testMathExpress() {
        Map<String, String> variables = Map.of("x", "3");
        boolean result = LatexMathWraps.testMathExpress("x*2", variables);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }
}
