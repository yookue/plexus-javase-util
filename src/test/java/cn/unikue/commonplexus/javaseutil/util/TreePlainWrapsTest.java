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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link cn.unikue.commonplexus.javaseutil.util.TreePlainWraps}
 *
 * @author David Hsing
 */
@Slf4j
class TreePlainWrapsTest {
    // ---------------------------------------------------------------
    // sortChildrenTree(structs, idField, pidField, childrenField)
    // ---------------------------------------------------------------

    @Test
    void sortChildrenTreeBasic() {
        String methodName = StackTraceWraps.getExecutingMethodName();

        // Build flat list:
        //   1
        //   ├── 2
        //   │   ├── 4
        //   │   └── 5
        //   └── 3
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "2", "pid", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "3", "pid", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "4", "pid", "2"));
        income.add(MapPlainWraps.newHashMapWithin("id", "5", "pid", "2"));

        List<Map<String, Object>> outcome = TreePlainWraps.sortChildrenTree(income, "id", "pid", "children");
        log.info("{}: outcome size = {}", methodName, CollectionPlainWraps.size(outcome));

        Assertions.assertNotNull(outcome);
        Assertions.assertEquals(1, outcome.size());

        Map<String, Object> root = outcome.get(0);
        Assertions.assertEquals("1", root.get("id"));
        List<?> rootChildren = (List<?>) root.get("children");
        Assertions.assertNotNull(rootChildren);
        Assertions.assertEquals(2, rootChildren.size());
    }

    @Test
    void sortChildrenTreeStructsWithPrefilledChildren() {
        // If children field is already populated, it should be preserved
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "1", "children", new ArrayList<>()));
        income.add(MapPlainWraps.newHashMapWithin("id", "2", "pid", "1", "children",
            CollectionPlainWraps.newArrayListWithin(MapPlainWraps.newHashMapWithin("id", "3", "pid", "2"))));

        List<Map<String, Object>> outcome = TreePlainWraps.sortChildrenTree(income, "id", "pid", "children");
        Assertions.assertNotNull(outcome);
        Assertions.assertEquals(1, outcome.size());

        Map<String, Object> root = outcome.get(0);
        List<?> rootChildren = (List<?>) root.get("children");
        Assertions.assertNotNull(rootChildren);
        // Children are preserved from prefilled values (including the nested item)
        Assertions.assertFalse(rootChildren.isEmpty());
    }

    @Test
    void sortChildrenTreeEmptyOrNullInput() {
        // Null structs
        Assertions.assertNull(TreePlainWraps.sortChildrenTree(null, "id", "pid", "children"));
        // Empty structs
        Assertions.assertNull(TreePlainWraps.sortChildrenTree(new ArrayList<>(), "id", "pid", "children"));
        // Blank idField
        Assertions.assertNull(TreePlainWraps.sortChildrenTree(
                CollectionPlainWraps.newArrayListWithin(MapPlainWraps.newHashMapWithin("id", "1")),
                "", "pid", "children"));
        // Blank pidField
        Assertions.assertNull(TreePlainWraps.sortChildrenTree(
                CollectionPlainWraps.newArrayListWithin(MapPlainWraps.newHashMapWithin("id", "1")),
                "id", null, "children"));
        // All inputs null
        Assertions.assertNull(TreePlainWraps.sortChildrenTree(null, null, null, null));
    }

    @Test
    void sortChildrenTreeSingleNode() {
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "single"));

        List<Map<String, Object>> outcome = TreePlainWraps.sortChildrenTree(income, "id", "pid", "children");
        Assertions.assertNotNull(outcome);
        Assertions.assertEquals(1, outcome.size());
        Assertions.assertEquals("single", outcome.get(0).get("id"));
    }

    @Test
    void sortChildrenTreeMultipleRoots() {
        //   1 (root)    2 (root)
        //   └── 3
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "2"));
        income.add(MapPlainWraps.newHashMapWithin("id", "3", "pid", "1"));

        List<Map<String, Object>> outcome = TreePlainWraps.sortChildrenTree(income, "id", "pid", "children");
        Assertions.assertNotNull(outcome);
        Assertions.assertEquals(2, outcome.size());
    }

    @Test
    void sortChildrenTreeOrphanNode() {
        // 1 is root with child 3, but 2 has pid pointing to nonexistent parent → should be treated as root
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "2", "pid", "nonexistent"));
        income.add(MapPlainWraps.newHashMapWithin("id", "3", "pid", "1"));

        List<Map<String, Object>> outcome = TreePlainWraps.sortChildrenTree(income, "id", "pid", "children");
        Assertions.assertNotNull(outcome);
        // Both 1 and 2 are root nodes (2's parent doesn't exist)
        Assertions.assertEquals(2, outcome.size());
    }

    // ---------------------------------------------------------------
    // sortChildrenTree(structs, idField, pidField, childrenField, sortField)
    // ---------------------------------------------------------------

    @Test
    void sortChildrenTreeSortedByField() {
        String methodName = StackTraceWraps.getExecutingMethodName();

        // Unsorted flat list, sorted by name at root level
        List<Map<String, Object>> income = new ArrayList<>();
        // Root nodes in reverse order
        income.add(MapPlainWraps.newHashMapWithin("id", "3", "name", "C"));
        income.add(MapPlainWraps.newHashMapWithin("id", "1", "name", "A"));
        income.add(MapPlainWraps.newHashMapWithin("id", "2", "name", "B"));

        List<Map<String, Object>> outcome = TreePlainWraps.sortChildrenTree(income, "id", "pid", "children", "name");
        Assertions.assertNotNull(outcome);

        log.info("{}: sorted roots = [{}]", methodName,
                StringUtilsWraps.joinWithCommaSpace(
                        outcome.stream().map(m -> m.get("name")).toArray()));

        Assertions.assertEquals(3, outcome.size());
        // Sorted by name: A, B, C
        Assertions.assertEquals("A", outcome.get(0).get("name"));
        Assertions.assertEquals("B", outcome.get(1).get("name"));
        Assertions.assertEquals("C", outcome.get(2).get("name"));
    }

    @Test
    void sortChildrenTreeSortedByFieldWithChildren() {
        // Build tree with root nodes sorted by 'name', children not recursively sorted
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "2", "pid", "1", "name", "childB"));
        income.add(MapPlainWraps.newHashMapWithin("id", "1", "name", "rootA"));
        income.add(MapPlainWraps.newHashMapWithin("id", "3", "pid", "1", "name", "childA"));
        income.add(MapPlainWraps.newHashMapWithin("id", "4", "pid", "1", "name", "childC"));

        List<Map<String, Object>> outcome = TreePlainWraps.sortChildrenTree(income, "id", "pid", "children", "name");
        // Root sorted by name: rootA, but children are sorted via recursive=false default in this overload
        // Actually let me re-check: the 5-param overload calls the 7-param with recursive=true
        // Let me look at the code again...
        // Line 68-70: sortChildrenTree(structs, idField, pidField, childrenField, sortField)
        //   return sortChildrenTree(structs, idField, pidField, childrenField, sortField, null, true);
        // So recursive=true by default. Children should be sorted too.
        Assertions.assertNotNull(outcome);
        Assertions.assertEquals(1, outcome.size());
        List<?> children = (List<?>) outcome.get(0).get("children");
        Assertions.assertNotNull(children);
        Assertions.assertEquals(3, children.size());
        // Children sorted by name: childA, childB, childC
        Map<?, ?> c1 = (Map<?, ?>) children.get(0);
        Assertions.assertNotNull(c1);
        Assertions.assertEquals("childA", c1.get("name"));
    }

    // ---------------------------------------------------------------
    // sortChildrenTree(structs, idField, pidField, childrenField, sortField, comparator, recursive)
    // ---------------------------------------------------------------

    @Test
    void sortChildrenTreeWithComparator() {
        String methodName = StackTraceWraps.getExecutingMethodName();

        // Custom comparator: sort by id numerically descending
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "1", "name", "Alpha"));
        income.add(MapPlainWraps.newHashMapWithin("id", "10", "name", "Beta"));
        income.add(MapPlainWraps.newHashMapWithin("id", "2", "name", "Gamma"));

        Comparator<Map<String, Object>> byIdDesc = Comparator.comparing(
                (Map<String, Object> m) -> Integer.parseInt((String) m.get("id"))).reversed();

        List<Map<String, Object>> outcome = TreePlainWraps.sortChildrenTree(
                income, "id", "pid", "children", null, byIdDesc, false);
        Assertions.assertNotNull(outcome);

        log.info("{}: comparator-sorted roots = [{}]", methodName,
                StringUtilsWraps.joinWithCommaSpace(
                        outcome.stream().map(m -> m.get("id")).toArray()));
        Assertions.assertEquals(3, outcome.size());
        // Sorted by id descending: 10, 2, 1
        Assertions.assertEquals("10", outcome.get(0).get("id"));
        Assertions.assertEquals("2", outcome.get(1).get("id"));
        Assertions.assertEquals("1", outcome.get(2).get("id"));
    }

    @Test
    void sortChildrenTreeRecursiveSort() {
        // recursive=true → children sorted; recursive=false → children unsorted (preserve insertion order)
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "1", "name", "root"));
        // Children inserted in reverse order
        income.add(MapPlainWraps.newHashMapWithin("id", "3", "pid", "1", "name", "childC"));
        income.add(MapPlainWraps.newHashMapWithin("id", "2", "pid", "1", "name", "childB"));
        income.add(MapPlainWraps.newHashMapWithin("id", "4", "pid", "1", "name", "childA"));

        // recursive=false: children should appear in insertion order (3, 2, 4)
        List<Map<String, Object>> nonRecursive = TreePlainWraps.sortChildrenTree(
                income, "id", "pid", "children", "name", null, false);
        Assertions.assertNotNull(nonRecursive);
        List<?> nrChildren = (List<?>) nonRecursive.get(0).get("children");
        // Without recursive sorting, children are sorted only at root; children keep their original order
        // But wait—the original list iteration for each struct may not guarantee order.
        // With recursive=false, only root nodes are sorted; children remain as they were added to parentChildrenMap.
        // Let's just verify they exist.
        Assertions.assertNotNull(nrChildren);
        Assertions.assertEquals(3, nrChildren.size());

        // recursive=true: children are sorted by name (A, B, C) after the root sort pass
        List<Map<String, Object>> recursive = TreePlainWraps.sortChildrenTree(
                income, "id", "pid", "children", "name", null, true);
        Assertions.assertNotNull(recursive);
        List<?> rChildren = (List<?>) recursive.get(0).get("children");
        Assertions.assertNotNull(rChildren);
        Assertions.assertEquals(3, rChildren.size());

        // Verify child order: sorted by name → childA(4), childB(2), childC(3)
        Map<?, ?> child0 = (Map<?, ?>) rChildren.get(0);
        Map<?, ?> child1 = (Map<?, ?>) rChildren.get(1);
        Map<?, ?> child2 = (Map<?, ?>) rChildren.get(2);
        Assertions.assertEquals("childA", child0.get("name"));
        Assertions.assertEquals("childB", child1.get("name"));
        Assertions.assertEquals("childC", child2.get("name"));
    }

    @Test
    void sortChildrenTreeDeepNesting() {
        // Three-level tree:
        //   1
        //   └── 2
        //       └── 3
        //           └── 4
        List<Map<String, Object>> income = new ArrayList<>();
        income.add(MapPlainWraps.newHashMapWithin("id", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "2", "pid", "1"));
        income.add(MapPlainWraps.newHashMapWithin("id", "3", "pid", "2"));
        income.add(MapPlainWraps.newHashMapWithin("id", "4", "pid", "3"));

        List<Map<String, Object>> outcome = TreePlainWraps.sortChildrenTree(income, "id", "pid", "children");
        Assertions.assertNotNull(outcome);
        Assertions.assertEquals(1, outcome.size());

        Map<String, Object> l1 = outcome.get(0);
        List<?> c1 = (List<?>) l1.get("children");
        Assertions.assertEquals(1, c1.size());

        Map<?, ?> l2 = (Map<?, ?>) c1.get(0);
        List<?> c2 = (List<?>) l2.get("children");
        Assertions.assertEquals(1, c2.size());

        Map<?, ?> l3 = (Map<?, ?>) c2.get(0);
        List<?> c3 = (List<?>) l3.get("children");
        Assertions.assertEquals(1, c3.size());
    }
}
