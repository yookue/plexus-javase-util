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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for trees
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class TreePlainWraps {
    /**
     * Returns a tree structure that only contains the root nodes list (unsorted)
     *
     * @param structs The flat list of structures
     * @param idField The name of the ID field
     * @param pidField The name of the parent ID field
     * @param childrenField The name of the children field
     * @param <T> The generic type of the structure
     *
     * @return a tree structure with root nodes, or null if input is invalid
     */
    @Nullable
    public static <T> List<T> sortChildrenTree(@Nullable Collection<T> structs, @Nullable String idField, @Nullable String pidField, @Nullable String childrenField) {
        return sortChildrenTree(structs, idField, pidField, childrenField, null, null, false);
    }

    /**
     * Returns a tree structure that only contains the root nodes list (sorted by field)
     *
     * @param structs The flat list of structures
     * @param idField The name of the ID field
     * @param pidField The name of the parent ID field
     * @param childrenField The name of the children field
     * @param sortField The name of the field to sort by
     * @param <T> The generic type of the structure
     *
     * @return a sorted tree structure with root nodes, or null if input is invalid
     */
    @Nullable
    public static <T> List<T> sortChildrenTree(@Nullable Collection<T> structs, @Nullable String idField, @Nullable String pidField, @Nullable String childrenField, @Nullable String sortField) {
        return sortChildrenTree(structs, idField, pidField, childrenField, sortField, null, true);
    }

    /**
     * Returns a tree structure that only contains the root nodes list, with optional sorting ability
     *
     * @param structs The flat list of structures
     * @param idField The name of the ID field
     * @param pidField The name of the parent ID field
     * @param childrenField The name of the children field
     * @param sortField The name of the field to sort by (can be null)
     * @param comparator A custom comparator for sorting (takes precedence over sortField, can be null)
     * @param recursive Whether to recursively sort child nodes
     * @param <T> The generic type of the structure
     *
     * @return a tree structure that only contains the root nodes list, with optional sorting ability
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> List<T> sortChildrenTree(@Nullable Collection<T> structs, @Nullable String idField, @Nullable String pidField, @Nullable String childrenField, @Nullable String sortField, @Nullable Comparator<T> comparator, boolean recursive) {
        if (CollectionPlainWraps.isEmpty(structs) || StringUtils.isAnyBlank(idField, pidField, childrenField)) {
            return null;
        }
        // First pass: build the complete id -> struct map
        Map<Object, T> structMap = new HashMap<>();
        for (T struct : structs) {
            Object id = readProperty(struct, idField);
            if (id != null) {
                structMap.put(id, struct);
            }
        }
        // Second pass: build parent-child relationships using the complete structMap
        Map<Object, List<T>> parentChildrenMap = new HashMap<>();
        for (T struct : structs) {
            Object pid = readProperty(struct, pidField);
            if (pid != null && structMap.containsKey(pid)) {
                parentChildrenMap.computeIfAbsent(pid, k -> new ArrayList<>()).add(struct);
            }
        }
        // Set children field and collect root nodes
        List<T> rootList = new ArrayList<>();
        for (T struct : structs) {
            Object id = readProperty(struct, idField);
            Object pid = readProperty(struct, pidField);
            List<T> children = (List<T>) readProperty(struct, childrenField);
            List<T> computedChildren = parentChildrenMap.get(id);
            if (children == null) {
                children = computedChildren != null ? computedChildren : Collections.emptyList();
                writeProperty(struct, childrenField, children);
            } else if (computedChildren != null) {
                // Merge computed children into pre-filled children (avoid duplicates)
                for (T child : computedChildren) {
                    if (!children.contains(child)) {
                        children.add(child);
                    }
                }
            }
            if (pid == null || !structMap.containsKey(pid)) {
                rootList.add(struct);
            }
        }
        // Sort root nodes
        Comparator<T> effectiveComparator = comparator != null ? comparator : createFieldComparator(sortField);
        rootList.sort(effectiveComparator);
        // Recursively sort child nodes if needed
        if (recursive) {
            for (T struct : structs) {
                List<T> children = (List<T>) readProperty(struct, childrenField);
                if (children != null && !children.isEmpty()) {
                    children.sort(effectiveComparator);
                }
            }
        }
        return rootList;
    }

    /**
     * Returns a comparator based on a field name
     *
     * @param field The name of the field to compare
     * @param <T> The generic type of the structure
     *
     * @return a comparator based on the field
     */
    @Nonnull
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> Comparator<T> createFieldComparator(@Nullable String field) {
        if (StringUtils.isBlank(field)) {
            return (o1, o2) -> 0;
        }
        return (o1, o2) -> {
            Object v1 = readProperty(o1, field);
            Object v2 = readProperty(o2, field);
            if (v1 == null && v2 == null) {
                return 0;
            }
            if (v1 == null) {
                return -1;
            }
            if (v2 == null) {
                return 1;
            }
            if (v1 instanceof Comparable<?> && v2 instanceof Comparable<?>) {
                return ((Comparable) v1).compareTo(v2);
            }
            return v1.toString().compareTo(v2.toString());
        };
    }

    /**
     * Reads a property value from a struct, supporting both {@link Map} and bean objects.
     */
    @Nullable
    @SuppressWarnings("rawtypes")
    private static Object readProperty(@Nullable Object struct, @Nullable String fieldName) {
        if (struct == null || StringUtils.isBlank(fieldName)) {
            return null;
        }
        if (struct instanceof Map) {
            return ((Map) struct).get(fieldName);
        }
        return FieldUtilsWraps.readField(struct, fieldName, true);
    }

    /**
     * Writes a property value to a struct, supporting both {@link Map} and bean objects.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void writeProperty(@Nullable Object struct, @Nullable String fieldName, @Nullable Object value) {
        if (struct == null || StringUtils.isBlank(fieldName)) {
            return;
        }
        if (struct instanceof Map) {
            ((Map) struct).put(fieldName, value);
            return;
        }
        FieldUtilsWraps.writeField(struct, fieldName, value, true);
    }
}
