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


import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.util.JsoupParserWraps}
 *
 * @author David Hsing
 */
@Slf4j
class JsoupParserWrapsTest {
    @Test
    void relaxedFlashSafelist() {
        Safelist result = JsoupParserWraps.relaxedFlashSafelist();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertNotNull(result);
    }

    @Test
    void relaxedHtml5Safelist() {
        Safelist result = JsoupParserWraps.relaxedHtml5Safelist();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertNotNull(result);
    }
}
