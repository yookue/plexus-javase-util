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


import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.util.PropertyPlainWraps}
 *
 * @author David Hsing
 */
@Slf4j
class PropertyPlainWrapsTest {
    @Test
    void newPropertiesNullKeyNullValue() {
        Properties properties = null;
        try {
            properties = PropertyPlainWraps.newPropertiesWithin(null, null);
        } catch (Exception ignored) {
        }
        boolean result = (properties == null);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void newPropertiesNullKeyNonnullValue() {
        Properties properties = null;
        try {
            properties = PropertyPlainWraps.newPropertiesWithin(null, String.valueOf(1));
        } catch (Exception ignored) {
        }
        boolean result = (properties == null);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void newPropertiesNonnullKeyNullValue() {
        Properties properties = null;
        try {
            properties = PropertyPlainWraps.newPropertiesWithin(String.valueOf(1), null);
        } catch (Exception ignored) {
        }
        boolean result = (properties == null);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }
}
