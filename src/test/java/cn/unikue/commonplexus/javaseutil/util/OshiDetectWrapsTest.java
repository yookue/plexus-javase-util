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

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link cn.unikue.commonplexus.javaseutil.util.OshiDetectWraps}
 *
 * @author David Hsing
 */
@Slf4j
class OshiDetectWrapsTest {
    @Test
    void getCpuSerials() {
        List<String> result = OshiDetectWraps.getCpuSerials();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), StringUtilsWraps.joinWithCommaSpace(result));
        Assertions.assertTrue(CollectionPlainWraps.isNotEmpty(result));
    }

    @Test
    void getBaseboardSerials() {
        List<String> result = OshiDetectWraps.getBaseboardSerials();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), StringUtilsWraps.joinWithCommaSpace(result));
        Assertions.assertTrue(CollectionPlainWraps.isNotEmpty(result));
    }

    @Test
    void getMacAddresses() {
        List<String> result = OshiDetectWraps.getMacAddresses();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), StringUtilsWraps.joinWithCommaSpace(result));
        Assertions.assertTrue(CollectionPlainWraps.isNotEmpty(result));
    }
}
