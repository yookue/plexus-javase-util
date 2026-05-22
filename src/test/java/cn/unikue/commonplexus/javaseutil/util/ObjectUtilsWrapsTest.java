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
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link cn.unikue.commonplexus.javaseutil.util.ObjectUtilsWraps}
 *
 * @author David Hsing
 */
@Slf4j
class ObjectUtilsWrapsTest {
    @Test
    void castAsString() {
        Object source = "this is a test";
        String result = ObjectUtilsWraps.castAs(source, String.class);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertTrue(StringUtils.isNoneBlank(result));
    }

    @Test
    void castAsInteger() {
        Object source = "this is a test";
        Integer result = ObjectUtilsWraps.castAs(source, Integer.class);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertNull(result);
    }

    @Test
    void castAsListString() {
        Object source = CollectionPlainWraps.newArrayListWithin("this", "is", "a", "test");
        List<String> result = ObjectUtilsWraps.castAsList(source, String.class);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), StringUtilsWraps.joinWithCommaSpace(result));
        Assertions.assertNotNull(result);
    }

    @Test
    void castAsListInteger() {
        Object source = CollectionPlainWraps.newArrayListWithin("this", "is", "a", "test");
        List<Integer> result = ObjectUtilsWraps.castAsList(source, Integer.class);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), StringUtilsWraps.joinWithCommaSpace(result));
        Assertions.assertNull(result);
    }

    @Test
    void castAsMapValid() {
        Object source = MapPlainWraps.newHashMapWithin("key1", "value1", "key2", "value2");
        Map<String, String> result = ObjectUtilsWraps.castAsMap(source, String.class, String.class, null);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("value1", result.get("key1"));
        Assertions.assertEquals("value2", result.get("key2"));
    }

    @Test
    void castAsMapInvalid() {
        Object source = MapPlainWraps.newHashMapWithin("key1", "value1", "key2", "value2");
        Map<Integer, String> result = ObjectUtilsWraps.castAsMap(source, Integer.class, String.class, null);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), result);
        Assertions.assertNull(result);
    }
}
