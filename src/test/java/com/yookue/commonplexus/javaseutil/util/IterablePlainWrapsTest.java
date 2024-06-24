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


import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link com.yookue.commonplexus.javaseutil.util.IterablePlainWraps}
 *
 * @author David Hsing
 */
@Slf4j
class IterablePlainWrapsTest {
    @Test
    void forEachIndexingTailing() {
        String methodName = StackTraceWraps.getExecutingMethodName();
        List<Integer> list = CollectionPlainWraps.newArrayListWithin(1, 2, 3);
        Integer tail = IterablePlainWraps.forEachIndexingTailing(list, (index, element) -> log.info("{}: index = {}, value= {}", methodName, index, element));
        Assertions.assertTrue(tail != null && tail == 3);
    }

    @Test
    void reverseForEachIndexingHeading() {
        String methodName = StackTraceWraps.getExecutingMethodName();
        List<Integer> list = CollectionPlainWraps.newArrayListWithin(1, 2, 3);
        Integer head = ListPlainWraps.reverseForEachIndexingHeading(list, (index, element) -> log.info("{}: index = {}, value= {}", methodName, index, element));
        Assertions.assertTrue(head != null && head == 1);
    }
}
