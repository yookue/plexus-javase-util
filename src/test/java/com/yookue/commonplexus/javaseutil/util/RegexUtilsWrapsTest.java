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


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.yookue.commonplexus.javaseutil.constant.RegexVariantConst;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.util.RegexUtilsWraps}
 *
 * @author David Hsing
 */
@Slf4j
class RegexUtilsWrapsTest {
    @Test
    void isApplicationJson() {
        boolean result = RegexUtilsWraps.findIgnoreCase("application/problem+json;charset=UTF-8", RegexVariantConst.APPLICATION_JSON);    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void isApplicationXml() {
        boolean result = RegexUtilsWraps.findIgnoreCase("application/problem+xml;charset=UTF-8", RegexVariantConst.APPLICATION_XML);    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void isWordHyphen() {
        boolean result = RegexUtilsWraps.isWordHyphen("12345ABC-DEF");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void reserveNumeric() {
        String text = RegexUtilsWraps.reserveNumeric("ABC_123_456-DEF");    // $NON-NLS-1$
        boolean result = StringUtils.equals(text, "123456");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void reserveNumericHyphen() {
        String text = RegexUtilsWraps.reserveNumericHyphen("ABC_123_456-DEF");    // $NON-NLS-1$
        boolean result = StringUtils.equals(text, "123456-");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void reserveWord() {
        String text = RegexUtilsWraps.reserveWord("ABC#_123@_456-DEF");    // $NON-NLS-1$
        boolean result = StringUtils.equals(text, "ABC_123_456DEF");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void reserveWordHyphen() {
        String text = RegexUtilsWraps.reserveWordHyphen("ABC#_123@_456-DEF");    // $NON-NLS-1$
        boolean result = StringUtils.equals(text, "ABC_123_456-DEF");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }
}
