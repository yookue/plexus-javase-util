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


import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link cn.unikue.commonplexus.javaseutil.util.CollectionPlainWraps}
 *
 * @author David Hsing
 */
@Slf4j
class CollectionPlainWrapsTest {
    @Test
    void toElementArray() {
        List<String> items = CollectionPlainWraps.newArrayListWithin("a", "b", "c");    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$
        String[] result = CollectionPlainWraps.toElementArray(items);
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), StringUtilsWraps.joinWithCommaSpace(result));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.length);
    }

    @Test
    void containsAny() {
        String methodName = StackTraceWraps.getExecutingMethodName();

        // 1. Exact match: overlapping elements
        List<String> listA = CollectionPlainWraps.newArrayListWithin("foo", "bar", "baz");
        List<String> listB = CollectionPlainWraps.newArrayListWithin("bar", "qux");
        boolean result1 = CollectionPlainWraps.containsAny(listA, listB);
        log.info("{}: exact-overlap result = {}", methodName, result1);
        Assertions.assertTrue(result1);

        // 2. No overlap: "foo-bar" is NOT equal to "bar" (exact equality, not substring)
        List<String> listC = CollectionPlainWraps.newArrayListWithin("foo-bar", "hello");
        List<String> listD = CollectionPlainWraps.newArrayListWithin("bar", "world");
        boolean result2 = CollectionPlainWraps.containsAny(listC, listD);
        log.info("{}: no-overlap (substring vs exact) result = {}", methodName, result2);
        Assertions.assertFalse(result2);

        // 3. Empty comparison collection
        List<String> listE = CollectionPlainWraps.newArrayListWithin("a", "b");
        boolean result3 = CollectionPlainWraps.containsAny(listE, Collections.emptyList());
        log.info("{}: empty-comparison result = {}", methodName, result3);
        Assertions.assertFalse(result3);

        // 4. Empty target collection
        boolean result4 = CollectionPlainWraps.containsAny(Collections.emptyList(), listB);
        log.info("{}: empty-target result = {}", methodName, result4);
        Assertions.assertFalse(result4);

        // 5. Both empty
        boolean result5 = CollectionPlainWraps.containsAny(Collections.emptyList(), Collections.emptyList());
        log.info("{}: both-empty result = {}", methodName, result5);
        Assertions.assertFalse(result5);

        // 6. Null target
        boolean result6 = CollectionPlainWraps.containsAny(null, listB);
        log.info("{}: null-target result = {}", methodName, result6);
        Assertions.assertFalse(result6);

        // 7. Large target, small comparison (first branch of size optimization)
        List<String> listLarge = CollectionPlainWraps.newArrayListWithin("a", "b", "c", "d", "e", "f");
        List<String> listSmall = CollectionPlainWraps.newArrayListWithin("d");
        boolean result8 = CollectionPlainWraps.containsAny(listLarge, listSmall);
        log.info("{}: large-target-small-comparison result = {}", methodName, result8);
        Assertions.assertTrue(result8);

        // 8. Small target, large comparison (second branch of size optimization)
        List<String> listTarget = CollectionPlainWraps.newArrayListWithin("x", "y");
        List<String> listMany = CollectionPlainWraps.newArrayListWithin("a", "b", "x", "c", "d");
        boolean result9 = CollectionPlainWraps.containsAny(listTarget, listMany);
        log.info("{}: small-target-large-comparison result = {}", methodName, result9);
        Assertions.assertTrue(result9);
    }

    @Test
    void containsAnySubstring() {
        String methodName = StackTraceWraps.getExecutingMethodName();

        // 1. Normal: "foo-bar" contains substring "bar"
        List<String> collection = CollectionPlainWraps.newArrayListWithin("foo-bar", "hello", "world");
        List<String> sequences = CollectionPlainWraps.newArrayListWithin("bar", "xyz");
        boolean result1 = CollectionPlainWraps.containsAnySubstring(collection, sequences);
        log.info("{}: contains-substring result = {}", methodName, result1);
        Assertions.assertTrue(result1);

        // 2. No match: no element contains any of the sequences
        List<String> sequences2 = CollectionPlainWraps.newArrayListWithin("abc", "xyz");
        boolean result2 = CollectionPlainWraps.containsAnySubstring(collection, sequences2);
        log.info("{}: no-match result = {}", methodName, result2);
        Assertions.assertFalse(result2);

        // 3. Empty collection
        boolean result3 = CollectionPlainWraps.containsAnySubstring(Collections.emptyList(), sequences);
        log.info("{}: empty-collection result = {}", methodName, result3);
        Assertions.assertFalse(result3);

        // 4. Empty sequences
        boolean result4 = CollectionPlainWraps.containsAnySubstring(collection, Collections.emptyList());
        log.info("{}: empty-sequences result = {}", methodName, result4);
        Assertions.assertFalse(result4);

        // 5. Null collection
        boolean result5 = CollectionPlainWraps.containsAnySubstring(null, sequences);
        log.info("{}: null-collection result = {}", methodName, result5);
        Assertions.assertFalse(result5);

        // 6. Null sequences
        boolean result6 = CollectionPlainWraps.containsAnySubstring(collection, null);
        log.info("{}: null-sequences result = {}", methodName, result6);
        Assertions.assertFalse(result6);

        // 7. Exact match also works (substring = full string)
        List<String> sequences7 = CollectionPlainWraps.newArrayListWithin("hello", "nonexistent");
        boolean result7 = CollectionPlainWraps.containsAnySubstring(collection, sequences7);
        log.info("{}: exact-match result = {}", methodName, result7);
        Assertions.assertTrue(result7);

        // 8. Same element contains two different substrings (still any → true)
        List<String> sequences8 = CollectionPlainWraps.newArrayListWithin("foo", "ba");
        boolean result8 = CollectionPlainWraps.containsAnySubstring(collection, sequences8);
        log.info("{}: multiple-in-same-element result = {}", methodName, result8);
        Assertions.assertTrue(result8);
    }

    @Test
    void containsAllSubstring() {
        String methodName = StackTraceWraps.getExecutingMethodName();

        // 1. Normal: all sequences found as substrings somewhere
        List<String> collection = CollectionPlainWraps.newArrayListWithin("foo-bar", "hello-world", "hello-java");
        List<String> sequences = CollectionPlainWraps.newArrayListWithin("bar", "java");
        boolean result1 = CollectionPlainWraps.containsAllSubstring(collection, sequences);
        log.info("{}: all-found result = {}", methodName, result1);
        Assertions.assertTrue(result1);

        // 2. Partial: only some sequences found
        List<String> sequences2 = CollectionPlainWraps.newArrayListWithin("bar", "xyz");
        boolean result2 = CollectionPlainWraps.containsAllSubstring(collection, sequences2);
        log.info("{}: partial-found result = {}", methodName, result2);
        Assertions.assertFalse(result2);

        // 3. All sequences found in same element
        List<String> collection3 = CollectionPlainWraps.newArrayListWithin("aaa-bbb-ccc", "ddd");
        List<String> sequences3 = CollectionPlainWraps.newArrayListWithin("aaa", "bbb");
        boolean result3 = CollectionPlainWraps.containsAllSubstring(collection3, sequences3);
        log.info("{}: all-in-same-element result = {}", methodName, result3);
        Assertions.assertTrue(result3);

        // 4. Empty collection
        boolean result4 = CollectionPlainWraps.containsAllSubstring(Collections.emptyList(), sequences);
        log.info("{}: empty-collection result = {}", methodName, result4);
        Assertions.assertFalse(result4);

        // 5. Empty sequences
        boolean result5 = CollectionPlainWraps.containsAllSubstring(collection, Collections.emptyList());
        log.info("{}: empty-sequences result = {}", methodName, result5);
        Assertions.assertTrue(result5);

        // 6. Null collection
        boolean result6 = CollectionPlainWraps.containsAllSubstring(null, sequences);
        log.info("{}: null-collection result = {}", methodName, result6);
        Assertions.assertFalse(result6);

        // 7. Null sequences
        boolean result7 = CollectionPlainWraps.containsAllSubstring(collection, null);
        log.info("{}: null-sequences result = {}", methodName, result7);
        Assertions.assertFalse(result7);

        // 8. Single sequence matches exactly
        List<String> sequences8 = CollectionPlainWraps.newArrayListWithin("hello-world");
        boolean result8 = CollectionPlainWraps.containsAllSubstring(collection, sequences8);
        log.info("{}: single-exact-match result = {}", methodName, result8);
        Assertions.assertTrue(result8);
    }

    @Test
    void containsAnySubstringIgnoreCase() {
        String methodName = StackTraceWraps.getExecutingMethodName();

        // 1. Normal: case-insensitive match
        List<String> collection = CollectionPlainWraps.newArrayListWithin("Foo-Bar", "Hello-World");
        List<String> sequences = CollectionPlainWraps.newArrayListWithin("bar", "XYZ");
        boolean result1 = CollectionPlainWraps.containsAnySubstringIgnoreCase(collection, sequences);
        log.info("{}: ignore-case-match result = {}", methodName, result1);
        Assertions.assertTrue(result1);

        // 2. No match: even case-insensitive, the substring is not present
        List<String> sequences2 = CollectionPlainWraps.newArrayListWithin("xyz", "abc");
        boolean result2 = CollectionPlainWraps.containsAnySubstringIgnoreCase(collection, sequences2);
        log.info("{}: no-match result = {}", methodName, result2);
        Assertions.assertFalse(result2);

        // 3. Different case in collection and sequences both
        List<String> collection3 = CollectionPlainWraps.newArrayListWithin("UPPERCASE-TEXT", "lowercase-text");
        List<String> sequences3 = CollectionPlainWraps.newArrayListWithin("upper", "LOWER");
        boolean result3 = CollectionPlainWraps.containsAnySubstringIgnoreCase(collection3, sequences3);
        log.info("{}: mixed-case-match result = {}", methodName, result3);
        Assertions.assertTrue(result3);

        // 4. Empty collection
        boolean result4 = CollectionPlainWraps.containsAnySubstringIgnoreCase(Collections.emptyList(), sequences);
        log.info("{}: empty-collection result = {}", methodName, result4);
        Assertions.assertFalse(result4);

        // 5. Empty sequences
        boolean result5 = CollectionPlainWraps.containsAnySubstringIgnoreCase(collection, Collections.emptyList());
        log.info("{}: empty-sequences result = {}", methodName, result5);
        Assertions.assertFalse(result5);

        // 6. Null collection
        boolean result6 = CollectionPlainWraps.containsAnySubstringIgnoreCase(null, sequences);
        log.info("{}: null-collection result = {}", methodName, result6);
        Assertions.assertFalse(result6);

        // 7. Null sequences
        boolean result7 = CollectionPlainWraps.containsAnySubstringIgnoreCase(collection, null);
        log.info("{}: null-sequences result = {}", methodName, result7);
        Assertions.assertFalse(result7);
    }

    @Test
    void containsAllSubstringIgnoreCase() {
        String methodName = StackTraceWraps.getExecutingMethodName();

        // 1. Normal: all sequences found case-insensitively
        List<String> collection = CollectionPlainWraps.newArrayListWithin("Foo-Bar", "Hello-World", "Hello-Java");
        List<String> sequences = CollectionPlainWraps.newArrayListWithin("BAR", "java");
        boolean result1 = CollectionPlainWraps.containsAllSubstringIgnoreCase(collection, sequences);
        log.info("{}: all-found result = {}", methodName, result1);
        Assertions.assertTrue(result1);

        // 2. Partial: only some found case-insensitively
        List<String> sequences2 = CollectionPlainWraps.newArrayListWithin("BAR", "xyz");
        boolean result2 = CollectionPlainWraps.containsAllSubstringIgnoreCase(collection, sequences2);
        log.info("{}: partial-found result = {}", methodName, result2);
        Assertions.assertFalse(result2);

        // 3. Mixed case in both: all found in same element
        List<String> collection3 = CollectionPlainWraps.newArrayListWithin("AAA-bbb-CCC");
        List<String> sequences3 = CollectionPlainWraps.newArrayListWithin("aaa", "BBB", "ccc");
        boolean result3 = CollectionPlainWraps.containsAllSubstringIgnoreCase(collection3, sequences3);
        log.info("{}: all-mixed-case-in-one result = {}", methodName, result3);
        Assertions.assertTrue(result3);

        // 4. Empty collection
        boolean result4 = CollectionPlainWraps.containsAllSubstringIgnoreCase(Collections.emptyList(), sequences);
        log.info("{}: empty-collection result = {}", methodName, result4);
        Assertions.assertFalse(result4);

        // 5. Empty sequences
        boolean result5 = CollectionPlainWraps.containsAllSubstringIgnoreCase(collection, Collections.emptyList());
        log.info("{}: empty-sequences result = {}", methodName, result5);
        Assertions.assertTrue(result5);

        // 6. Null collection
        boolean result6 = CollectionPlainWraps.containsAllSubstringIgnoreCase(null, sequences);
        log.info("{}: null-collection result = {}", methodName, result6);
        Assertions.assertFalse(result6);

        // 7. Null sequences
        boolean result7 = CollectionPlainWraps.containsAllSubstringIgnoreCase(collection, null);
        log.info("{}: null-sequences result = {}", methodName, result7);
        Assertions.assertFalse(result7);

        // 8. Single sequence, exact match case-insensitively
        List<String> sequences8 = CollectionPlainWraps.newArrayListWithin("FOO-BAR");
        boolean result8 = CollectionPlainWraps.containsAllSubstringIgnoreCase(collection, sequences8);
        log.info("{}: single-exact-ignorecase result = {}", methodName, result8);
        Assertions.assertTrue(result8);
    }
}
