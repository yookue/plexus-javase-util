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


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link cn.unikue.commonplexus.javaseutil.util.FilenamePlainWraps}
 *
 * @author David Hsing
 */
@Slf4j
class FilenamePlainWrapsTest {
    @Test
    void appendSlash() {
        String text = FilenamePlainWraps.appendSlash("C:/Windows/System32");    // $NON-NLS-1$
        boolean result = StringUtils.equals(text, "C:/Windows/System32/");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void removeEndSlashes() {
        String text = FilenamePlainWraps.removeEndSlashes("C:\\Windows\\System32\\");    // $NON-NLS-1$
        boolean result = StringUtils.equals(text, "C:\\Windows\\System32");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(result);
    }

    @Test
    void removeExtensionDot() {
        String result = FilenamePlainWraps.removeExtensionDot("..tmp");    // $NON-NLS-1$
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(StringUtils.equals(result, "tmp"));    // $NON-NLS-1$
    }
}
