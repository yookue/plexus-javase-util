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


import java.util.Map;
import org.junit.jupiter.api.Test;
import com.yookue.commonplexus.javaseutil.text.MapStringLookup;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link StringSubstitutorWraps}
 *
 * @author David Hsing
 */
@Slf4j
class StringSubstituteWrapsTest {
    @Test
    void replaceDollarCurlyBrackets1() {
        String template = "The quick brown ${dog} jumped over the lazy ${fox}";
        Map<String, String> mappings = MapPlainWraps.newLinkedHashMapWithin("dog", "dog", "fox", "fox");
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), StringSubstitutorWraps.replaceDollarCurlyBrackets(template, mappings));
    }

    @Test
    void replaceDollarCurlyBrackets2() {
        String template = "The quick brown ${dog} jumped over the lazy ${fox}";
        Map<String, String> mappings = MapPlainWraps.newLinkedHashMapWithin("dog", "dog", "fox", null);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), StringSubstitutorWraps.replaceDollarCurlyBrackets(template, mappings, new MapStringLookup<>(mappings)));
    }

    @Test
    void replaceDollarCurlyBrackets3() {
        String template = "The quick brown ${dog} jumped over the lazy ${fox}";
        Map<String, String> mappings = MapPlainWraps.newLinkedHashMapWithin("quick", "quick");
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), StringSubstitutorWraps.replaceDollarCurlyBrackets(template, mappings, new MapStringLookup<>(mappings, true, true)));
    }
}
