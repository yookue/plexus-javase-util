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

package com.yookue.commonplexus.javaseutil.locale;


import org.junit.jupiter.api.Test;
import com.yookue.commonplexus.javaseutil.enumeration.ChineseNumberType;
import com.yookue.commonplexus.javaseutil.util.StackTraceWraps;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.locale.ChineseNumberUtils}
 *
 * @author David Hsing
 */
@Slf4j
class ChineseNumberUtilsTest {
    @Test
    void cnGeneralDouble() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseNumberUtils.toNumberString(1903210120007.65123D, 2, ChineseNumberType.ZH_CN_GENERAL));
    }

    @Test
    void cnFinanceDouble() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseNumberUtils.toNumberString(1903210120007.65123D, 2, ChineseNumberType.ZH_CN_FINANCE));
    }

    @Test
    void twGeneralFloat() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseNumberUtils.toNumberString(1809857.65234F, 3, ChineseNumberType.ZH_TW_GENERAL));
    }

    @Test
    void cnFinanceInteger() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseNumberUtils.toNumberString(256, 3, ChineseNumberType.ZH_CN_FINANCE));
    }

    @Test
    void cnFinanceString() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseNumberUtils.toNumberString("002.0126760", 3, ChineseNumberType.ZH_CN_FINANCE));
    }

    @Test
    void twFinanceString() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseNumberUtils.toNumberString("018.008", 3, ChineseNumberType.ZH_TW_FINANCE));
    }
}
