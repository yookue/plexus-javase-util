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


import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link cn.unikue.commonplexus.javaseutil.util.Exp4jMathWraps}
 *
 * @author David Hsing
 */
@Slf4j
class Exp4jMathWrapsTest {
    @Test
    void evalMathExpress() {
        Map<String, Object> variables = Map.of("x", 3);
        Assertions.assertEquals(6.0, Exp4jMathWraps.evalMathExpress("x*2", variables));
        Assertions.assertEquals(61.0, Exp4jMathWraps.evalMathExpress("floor(61.2)"));
        Assertions.assertEquals(62.0, Exp4jMathWraps.evalMathExpress("ceil(61.2)"));
        Assertions.assertEquals(3.0, Exp4jMathWraps.evalMathExpress("abs(-3)"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Exp4jMathWraps.evalMathExpress("y*2", variables));
    }

    @Test
    void testMathExpress() {
        Map<String, Object> variables = Map.of("x", 3);
        boolean result = Exp4jMathWraps.testMathExpress("x*2", variables);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }
}
