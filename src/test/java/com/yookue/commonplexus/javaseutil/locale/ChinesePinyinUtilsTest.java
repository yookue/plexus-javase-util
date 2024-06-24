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
import com.yookue.commonplexus.javaseutil.util.StackTraceWraps;
import net.sourceforge.pinyin4j.ChineseHelper;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.locale.ChinesePinyinUtils}
 *
 * @author David Hsing
 */
@Slf4j
class ChinesePinyinUtilsTest {
    @Test
    void firstSyllables() throws Exception {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChinesePinyinUtils.getFirstSyllables("你好"));    // $NON-NLS-1$
    }

    @Test
    void toTraditional() {
        log.info("{} = {}", StackTraceWraps.getExecutingMethodName(), ChineseHelper.convertToTraditionalChinese("欢迎"));    // $NON-NLS-1$
    }
}
