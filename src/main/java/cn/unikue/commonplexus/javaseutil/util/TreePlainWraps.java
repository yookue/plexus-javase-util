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
        // Build id -> struct map
        Map<Object, T> structMap = new HashMap<>();
        Map<Object, List<T>> parentChildrenMap = new HashMap<>();
        for (T struct : structs) {
            Object id = FieldUtilsWraps.readField(struct, idField, true);
            if (id == null) {
                continue;
            }
            structMap.put(id, struct);
            Object pid = FieldUtilsWraps.readField(struct, pidField, true);
            // Build parent-child relationships
            if (pid != null && structMap.containsKey(pid)) {
                parentChildrenMap.computeIfAbsent(pid, k -> new ArrayList<>()).add(struct);
            }
        }
        // Set children field and collect root nodes
        List<T> rootList = new ArrayList<>();
        for (T struct : structs) {
            Object id = FieldUtilsWraps.readField(struct, idField, true);
            Object pid = FieldUtilsWraps.readField(struct, pidField, true);
            List<T> children = FieldUtilsWraps.readFieldAs(struct, childrenField, true, List.class);
            if (children == null) {
                children = parentChildrenMap.getOrDefault(id, Collections.emptyList());
                FieldUtilsWraps.writeField(struct, childrenField, children, true);
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
                List<T> children = FieldUtilsWraps.readFieldAs(struct, childrenField, true, List.class);
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
            Object v1 = FieldUtilsWraps.readField(o1, field, true);
            Object v2 = FieldUtilsWraps.readField(o2, field, true);
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
}
