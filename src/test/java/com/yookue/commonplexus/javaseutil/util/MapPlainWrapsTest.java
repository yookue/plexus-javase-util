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


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.util.MapPlainWraps}
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
        Assertions.assertTrue(outcome != null && outcome.size() == 1);
    }
}
