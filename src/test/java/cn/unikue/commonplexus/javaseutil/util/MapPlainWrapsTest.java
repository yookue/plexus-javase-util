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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link cn.unikue.commonplexus.javaseutil.util.MapPlainWraps}
 *
 * @author David Hsing
 */
@Slf4j
@SuppressWarnings("LoggingSimilarMessage")
class MapPlainWrapsTest {
    @Test
    void forEachIndexingTailing() {
        String methodName = StackTraceWraps.getExecutingMethodName();
        Map<String, Integer> map = MapPlainWraps.newLinkedHashMapWithin("first", 1, "second", 2, "third", 3);
        Map.Entry<String, Integer> tail = MapPlainWraps.forEachIndexingTailing(map, (index, key, value) -> log.info("{}: index = {}, key = {}, value= {}", methodName, index, key, value));
        Assertions.assertTrue(tail != null && tail.getValue() == 3);
    }

    @Test
    void recompute() {
        String methodName = StackTraceWraps.getExecutingMethodName();
        Map<String, Integer> map = MapPlainWraps.newLinkedHashMapWithin("first", 1, "second", 2, "third", 3);
        MapPlainWraps.recompute(map, (key, value) -> value + 10);
        Integer value = map.get("first");
        log.info("{}: first value = {}", methodName, value);
        Assertions.assertEquals(11, (int) value);
    }

    @Test
    void reverseForEachIndexingHeading() {
        String methodName = StackTraceWraps.getExecutingMethodName();
        Map<String, Integer> map = MapPlainWraps.newLinkedHashMapWithin("first", 1, "second", 2, "third", 3);
        Map.Entry<String, Integer> head = MapPlainWraps.reverseForEachIndexingHeading(map, (index, key, value) -> log.info("{}: index = {}, key = {}, value= {}", methodName, index, key, value));
        Assertions.assertTrue(head != null && head.getValue() == 1);
    }

    @Test
    void sortChildrenTree() {
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "2", "pid", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "3", "pid", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "4", "pid", "2"));
        income.add(MapPlainWraps.newHashMapWithin("id", "5", "pid", "2"));
        List<Map<String, Object>> outcome = MapPlainWraps.sortChildrenTree(income, "id", "pid", "children");
        Assertions.assertTrue(outcome != null && outcome.size() == 1 && CollectionPlainWraps.size((Collection<?>) outcome.get(0).get("children")) == 2);
    }

    @Test
    void getObjectListAs() {
        String methodName = StackTraceWraps.getExecutingMethodName();

        // 1. Normal: map with a list of matched type
        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        Map<String, Object> map = MapPlainWraps.newHashMapWithin("ints", intList);
        List<Integer> result = MapPlainWraps.getObjectListAs(map, "ints", Integer.class);
        log.info("{}: normal result = {}", methodName, result);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(10, (int) result.get(0));

        // 2. Type mismatch: list contains String but expect Integer
        List<Object> mixedList = new ArrayList<>();
        mixedList.add("hello");
        Map<String, Object> map2 = MapPlainWraps.newHashMapWithin("items", mixedList);
        List<Integer> result2 = MapPlainWraps.getObjectListAs(map2, "items", Integer.class);
        log.info("{}: type-mismatch result = {}", methodName, result2);
        Assertions.assertNull(result2);

        // 3. Type mismatch with custom default value
        List<Integer> defaultList = new ArrayList<>();
        defaultList.add(-1);
        List<Integer> result3 = MapPlainWraps.getObjectListAs(map2, "items", Integer.class, defaultList);
        log.info("{}: custom default result = {}", methodName, result3);
        Assertions.assertSame(defaultList, result3);

        // 4. Empty list
        List<String> emptyList = new ArrayList<>();
        Map<String, Object> map4 = MapPlainWraps.newHashMapWithin("empty", emptyList);
        List<String> result4 = MapPlainWraps.getObjectListAs(map4, "empty", String.class);
        log.info("{}: empty list result = {}", methodName, result4);
        Assertions.assertNotNull(result4);
        Assertions.assertTrue(result4.isEmpty());

        // 5. Key not present
        List<Integer> result5 = MapPlainWraps.getObjectListAs(map, "nonexistent", Integer.class);
        log.info("{}: missing key result = {}", methodName, result5);
        Assertions.assertNull(result5);

        // 6. Null map
        List<Integer> result6 = MapPlainWraps.getObjectListAs(null, "any", Integer.class);
        Assertions.assertNull(result6);

        // 7. Null expectType
        List<Integer> result7 = MapPlainWraps.getObjectListAs(map, "ints", null);
        Assertions.assertNull(result7);

        // 8. Mixed null/non-null items of correct type
        List<Integer> mixedWithNulls = new ArrayList<>();
        mixedWithNulls.add(null);
        mixedWithNulls.add(42);
        Map<String, Object> map8 = MapPlainWraps.newHashMapWithin("mix", mixedWithNulls);
        List<Integer> result8 = MapPlainWraps.getObjectListAs(map8, "mix", Integer.class);
        log.info("{}: mixed with nulls result = {}", methodName, result8);
        Assertions.assertNotNull(result8);
        Assertions.assertEquals(2, result8.size());
        Assertions.assertNull(result8.get(0));
        Assertions.assertEquals(42, (int) result8.get(1));
    }
}
