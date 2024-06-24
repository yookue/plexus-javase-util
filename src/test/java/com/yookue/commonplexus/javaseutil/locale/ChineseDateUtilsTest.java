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


import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import com.yookue.commonplexus.javaseutil.enumeration.ChineseNumberType;
import com.yookue.commonplexus.javaseutil.util.UtilDateWraps;
import com.yookue.commonplexus.javaseutil.util.StackTraceWraps;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.locale.ChineseDateUtils}
 *
 * @author David Hsing
 */
@Slf4j
class ChineseDateUtilsTest {
    @Test
    void cnAdFinanceDate() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseDateUtils.toDateString(UtilDateWraps.getSpecificDate(2021, 1, 10), true, ChineseNumberType.ZH_CN_FINANCE));
    }

    @Test
    void cnGeneralDate() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseDateUtils.toDateString(UtilDateWraps.getSpecificDate(-726, 3, 31), false, ChineseNumberType.ZH_CN_GENERAL));
    }

    @Test
    void twGeneralDate() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseDateUtils.toDateString(UtilDateWraps.getSpecificDate(-2022, 10, 20), false, ChineseNumberType.ZH_TW_GENERAL));
    }

    @Test
    void cnAdFinanceLocalDate() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseDateUtils.toDateString(LocalDate.of(2021, 1, 10), true, ChineseNumberType.ZH_CN_FINANCE));
    }

    @Test
    void cnGeneralLocaleDate() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseDateUtils.toDateString(LocalDate.of(819, 10, 31), false, ChineseNumberType.ZH_CN_GENERAL));
    }
}
