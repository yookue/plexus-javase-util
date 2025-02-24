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
import com.yookue.commonplexus.javaseutil.enumeration.BootstrapColorType;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.util.ValueEnumWraps}
 *
 * @author David Hsing
 */
@Slf4j
class ValueEnumWrapsTest {
    @Test
    void containsValue() {
        boolean result = ValueEnumWraps.containsValue(BootstrapColorType.class, "primary");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void containsValueIgnoreCase() {
        boolean result = ValueEnumWraps.containsValueIgnoreCase(BootstrapColorType.class, "PRIMARY");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void notContainsValue() {
        boolean result = ValueEnumWraps.containsValue(BootstrapColorType.class, "NOT_EXISTS");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertFalse(result);
    }

    @Test
    void ofValue() {
        String methodName = StackTraceWraps.getExecutingMethodName();
        BootstrapColorType type1 = ValueEnumWraps.ofValue(BootstrapColorType.class, "primary");    // $NON-NLS-1$
        log.info("{}: {}", methodName, type1 != null);
        Assertions.assertNotNull(type1);
        BootstrapColorType type2 = ValueEnumWraps.ofValueIgnoreCase(BootstrapColorType.class, "NOT_EXISTS");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), type2 == null);
        Assertions.assertNull(type2);
    }

    @Test
    void ofValueIgnoreCase() {
        BootstrapColorType result = ValueEnumWraps.ofValueIgnoreCase(BootstrapColorType.class, "PRIMARY");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result != null);
        Assertions.assertNotNull(result);
    }
}
