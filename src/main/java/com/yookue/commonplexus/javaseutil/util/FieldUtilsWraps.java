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


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.reflect.FieldUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.reflect.FieldUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class FieldUtilsWraps {
    public static void doWithDeclaredFields(@Nullable Class<?> clazz, @Nullable Consumer<Field> action) {
        doWithDeclaredFields(clazz, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void doWithDeclaredFields(@Nullable Class<?> clazz, @Nullable Consumer<Field> action, @Nullable Predicate<Field> filter) {
        if (ObjectUtils.anyNull(clazz, action)) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        if (ArrayUtils.isEmpty(fields)) {
            return;
        }
        for (Field field : fields) {
            if (filter == null || filter.test(field)) {
                action.accept(field);
            }
        }
    }

    public static void doWithNestedFields(@Nullable Class<?> clazz, @Nullable Consumer<Field> action) {
        doWithNestedFields(clazz, action, null);
    }

    public static void doWithNestedFields(@Nullable Class<?> clazz, @Nullable Consumer<Field> action, @Nullable Predicate<Field> filter) {
        doWithNestedFieldsUntil(clazz, null, action, filter);
    }

    public static void doWithNestedFieldsUntil(@Nullable Class<?> clazz, @Nullable Class<?> untilClass, @Nullable Consumer<Field> action) {
        doWithNestedFieldsUntil(clazz, untilClass, action, null);
    }

    /**
     * @see "org.springframework.util.ReflectionUtils#doWithFields"
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void doWithNestedFieldsUntil(@Nullable Class<?> clazz, @Nullable Class<?> untilClass, @Nullable Consumer<Field> action, @Nullable Predicate<Field> filter) {
        if (ObjectUtils.anyNull(clazz, action)) {
            return;
        }
        Class<?> loopClass = clazz;
        while (loopClass != null) {
            if (untilClass != null && untilClass == loopClass) {
                break;
            }
            Field[] fields = loopClass.getDeclaredFields();
            if (ArrayUtils.isNotEmpty(fields)) {
                for (Field field : fields) {
                    if (filter == null || filter.test(field)) {
                        action.accept(field);
                    }
                }
            }
            loopClass = loopClass.getSuperclass();
        }
    }

    @Nullable
    public static Field[] getDeclaredFields(@Nullable Class<?> clazz) {
        return (clazz == null) ? null : clazz.getDeclaredFields();
    }

    @Nullable
    public static Field[] getDeclaredFields(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<Field> fields = getDeclaredFieldsToList(clazz, filter);
        return CollectionPlainWraps.isEmpty(fields) ? null : fields.toArray(ArrayUtils.EMPTY_FIELD_ARRAY);
    }

    @Nullable
    public static List<Field> getDeclaredFieldsToList(@Nullable Class<?> clazz) {
        return getDeclaredFieldsToList(clazz, null);
    }

    @Nullable
    public static List<Field> getDeclaredFieldsToList(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<Field> result = new ArrayList<>();
        doWithDeclaredFields(clazz, result::add, filter);
        return CollectionPlainWraps.isEmpty(result) ? null : result;
    }

    @Nullable
    public static Set<Field> getDeclaredFieldsToSet(@Nullable Class<?> clazz) {
        return getDeclaredFieldsToSet(clazz, null);
    }

    @Nullable
    public static Set<Field> getDeclaredFieldsToSet(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<Field> fields = getDeclaredFieldsToList(clazz, filter);
        return CollectionPlainWraps.isEmpty(fields) ? null : new LinkedHashSet<>(fields);
    }

    @Nullable
    public static String[] getDeclaredFieldNames(@Nullable Class<?> clazz) {
        return getDeclaredFieldNames(clazz, null);
    }

    @Nullable
    public static String[] getDeclaredFieldNames(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<String> names = getDeclaredFieldNamesToList(clazz, filter);
        return CollectionPlainWraps.isEmpty(names) ? null : names.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    @Nullable
    public static List<String> getDeclaredFieldNamesToList(@Nullable Class<?> clazz) {
        return getDeclaredFieldNamesToList(clazz, null);
    }

    @Nullable
    public static List<String> getDeclaredFieldNamesToList(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<String> result = new ArrayList<>();
        doWithDeclaredFields(clazz, element -> result.add(element.getName()), filter);
        return CollectionPlainWraps.isEmpty(result) ? null : result;
    }

    @Nullable
    public static Set<String> getDeclaredFieldNamesToSet(@Nullable Class<?> clazz) {
        return getDeclaredFieldNamesToSet(clazz, null);
    }

    @Nullable
    public static Set<String> getDeclaredFieldNamesToSet(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<String> names = getDeclaredFieldNamesToList(clazz, filter);
        return CollectionPlainWraps.isEmpty(names) ? null : new LinkedHashSet<>(names);
    }

    @Nullable
    public static Field[] getNestedFields(@Nullable Class<?> clazz) {
        return getNestedFields(clazz, null);
    }

    @Nullable
    public static Field[] getNestedFields(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<Field> fields = getNestedFieldsToList(clazz, filter);
        return CollectionPlainWraps.isEmpty(fields) ? null : fields.toArray(ArrayUtils.EMPTY_FIELD_ARRAY);
    }

    @Nullable
    public static List<Field> getNestedFieldsToList(@Nullable Class<?> clazz) {
        return getNestedFieldsToList(clazz, null);
    }

    @Nullable
    public static List<Field> getNestedFieldsToList(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<Field> result = new ArrayList<>();
        doWithNestedFields(clazz, result::add, filter);
        return CollectionPlainWraps.isEmpty(result) ? null : result;
    }

    @Nullable
    public static Set<Field> getNestedFieldsToSet(@Nullable Class<?> clazz) {
        return getNestedFieldsToSet(clazz, null);
    }

    @Nullable
    public static Set<Field> getNestedFieldsToSet(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<Field> fields = getNestedFieldsToList(clazz, filter);
        return CollectionPlainWraps.isEmpty(fields) ? null : new LinkedHashSet<>(fields);
    }

    @Nullable
    public static String[] getNestedFieldNames(@Nullable Class<?> clazz) {
        return getNestedFieldNames(clazz, null);
    }

    @Nullable
    public static String[] getNestedFieldNames(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<String> names = getNestedFieldNamesToList(clazz, filter);
        return CollectionPlainWraps.isEmpty(names) ? null : names.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    @Nullable
    public static List<String> getNestedFieldNamesToList(@Nullable Class<?> clazz) {
        return getNestedFieldNamesToList(clazz, null);
    }

    @Nullable
    public static List<String> getNestedFieldNamesToList(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<String> result = new ArrayList<>();
        doWithNestedFields(clazz, element -> result.add(element.getName()), filter);
        return CollectionPlainWraps.isEmpty(result) ? null : result;
    }

    @Nullable
    public static Set<String> getNestedFieldNamesToSet(@Nullable Class<?> clazz) {
        return getNestedFieldNamesToSet(clazz, null);
    }

    @Nullable
    public static Set<String> getNestedFieldNamesToSet(@Nullable Class<?> clazz, @Nullable Predicate<Field> filter) {
        List<String> names = getNestedFieldNamesToList(clazz, filter);
        return CollectionPlainWraps.isEmpty(names) ? null : new LinkedHashSet<>(names);
    }

    @Nullable
    @SafeVarargs
    public static Field[] getFieldsWithAllAnnotations(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldsWithAllAnnotations(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static Field[] getFieldsWithAllAnnotations(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<Field> fields = getFieldsWithAllAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(fields) ? null : fields.toArray(ArrayUtils.EMPTY_FIELD_ARRAY);
    }

    @Nullable
    @SafeVarargs
    public static List<Field> getFieldsWithAllAnnotationsToList(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldsWithAllAnnotationsToList(clazz, ArrayUtilsWraps.asList(annotations));
    }

    /**
     * @see org.apache.commons.lang3.reflect.FieldUtils#getFieldsListWithAnnotation
     */
    @Nullable
    public static List<Field> getFieldsWithAllAnnotationsToList(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        if (clazz == null || CollectionPlainWraps.isEmpty(annotations)) {
            return null;
        }
        List<Field> fields = FieldUtils.getAllFieldsList(clazz);
        return CollectionPlainWraps.isEmpty(fields) ? null : fields.stream().filter(element -> AnnotationUtilsWraps.allPresent(element, annotations)).collect(Collectors.toList());
    }

    @Nullable
    @SafeVarargs
    public static Set<Field> getFieldsWithAllAnnotationsToSet(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldsWithAllAnnotationsToSet(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static Set<Field> getFieldsWithAllAnnotationsToSet(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<Field> fields = getFieldsWithAllAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(fields) ? null : new LinkedHashSet<>(fields);
    }

    @Nullable
    @SafeVarargs
    public static Field[] getFieldsWithAnyAnnotations(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldsWithAnyAnnotations(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static Field[] getFieldsWithAnyAnnotations(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<Field> fields = getFieldsWithAnyAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(fields) ? null : fields.toArray(ArrayUtils.EMPTY_FIELD_ARRAY);
    }

    @Nullable
    @SafeVarargs
    public static List<Field> getFieldsWithAnyAnnotationsToList(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldsWithAnyAnnotationsToList(clazz, ArrayUtilsWraps.asList(annotations));
    }

    /**
     * @see org.apache.commons.lang3.reflect.FieldUtils#getFieldsListWithAnnotation
     */
    @Nullable
    public static List<Field> getFieldsWithAnyAnnotationsToList(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        if (clazz == null || CollectionPlainWraps.isEmpty(annotations)) {
            return null;
        }
        List<Field> fields = FieldUtils.getAllFieldsList(clazz);
        return CollectionPlainWraps.isEmpty(fields) ? null : fields.stream().filter(element -> AnnotationUtilsWraps.anyPresent(element, annotations)).collect(Collectors.toList());
    }

    @Nullable
    @SafeVarargs
    public static Set<Field> getFieldsWithAnyAnnotationsToSet(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldsWithAnyAnnotationsToSet(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static Set<Field> getFieldsWithAnyAnnotationsToSet(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<Field> fields = getFieldsWithAnyAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(fields) ? null : new LinkedHashSet<>(fields);
    }

    @Nullable
    @SafeVarargs
    public static String[] getFieldNamesWithAllAnnotations(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldNamesWithAllAnnotations(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static String[] getFieldNamesWithAllAnnotations(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<String> names = getFieldNamesWithAllAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(names) ? null : names.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    @Nullable
    @SafeVarargs
    public static List<String> getFieldNamesWithAllAnnotationsToList(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldNamesWithAllAnnotationsToList(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static List<String> getFieldNamesWithAllAnnotationsToList(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<Field> fields = getFieldsWithAllAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(fields) ? null : fields.stream().map(Field::getName).collect(Collectors.toList());
    }

    @Nullable
    @SafeVarargs
    public static Set<String> getFieldNamesWithAllAnnotationsToSet(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldNamesWithAllAnnotationsToSet(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static Set<String> getFieldNamesWithAllAnnotationsToSet(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<String> names = getFieldNamesWithAllAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(names) ? null : new LinkedHashSet<>(names);
    }

    @Nullable
    @SafeVarargs
    public static String[] getFieldNamesWithAnyAnnotations(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldNamesWithAnyAnnotations(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static String[] getFieldNamesWithAnyAnnotations(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<String> names = getFieldNamesWithAnyAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(names) ? null : names.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    @Nullable
    @SafeVarargs
    public static List<String> getFieldNamesWithAnyAnnotationsToList(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldNamesWithAnyAnnotationsToList(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static List<String> getFieldNamesWithAnyAnnotationsToList(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<Field> fields = getFieldsWithAnyAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(fields) ? null : fields.stream().map(Field::getName).collect(Collectors.toList());
    }

    @Nullable
    @SafeVarargs
    public static Set<String> getFieldNamesWithAnyAnnotationsToSet(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation>... annotations) {
        return getFieldNamesWithAnyAnnotationsToSet(clazz, ArrayUtilsWraps.asList(annotations));
    }

    @Nullable
    public static Set<String> getFieldNamesWithAnyAnnotationsToSet(@Nullable Class<?> clazz, @Nullable Collection<Class<? extends Annotation>> annotations) {
        List<String> names = getFieldNamesWithAnyAnnotationsToList(clazz, annotations);
        return CollectionPlainWraps.isEmpty(names) ? null : new LinkedHashSet<>(names);
    }

    public static boolean isPublic(@Nullable Field field) {
        return field != null && Modifier.isPublic(field.getModifiers());
    }

    public static boolean isStatic(@Nullable Field field) {
        return field != null && Modifier.isStatic(field.getModifiers());
    }

    public static boolean isFinal(@Nullable Field field) {
        return field != null && Modifier.isFinal(field.getModifiers());
    }

    public static boolean isPublicStatic(@Nullable Field field) {
        return isPublic(field) && isStatic(field);
    }

    public static boolean isPublicStaticFinal(@Nullable Field field) {
        return isPublic(field) && isStatic(field) && isFinal(field);
    }

    /**
     * @see org.apache.commons.lang3.reflect.MemberUtils#setAccessibleWorkaround
     * @see "org.springframework.util.ReflectionUtils#makeAccessible"
     */
    @SuppressWarnings("JavadocReference")
    public static void makeAccessible(@Nullable Field field) {
        if (field == null) {
            return;
        }
        if (!field.canAccess(null) && (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers()))) {
            field.setAccessible(true);
        }
    }

    @Nullable
    public static Object readField(@Nullable Class<?> clazz, @Nullable String fieldName) {
        return readField(clazz, fieldName, false);
    }

    @Nullable
    public static Object readField(@Nullable Class<?> clazz, @Nullable String fieldName, boolean forceAccess) {
        if (clazz == null || StringUtils.isBlank(fieldName)) {
            return null;
        }
        try {
            return FieldUtils.readField(clazz, fieldName, forceAccess);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Object readField(@Nullable Field field, @Nullable Object target) {
        return readField(field, target, false);
    }

    /**
     * Returns the field value from a filed in the target
     *
     * @param field the field to read
     * @param target the object to call on, may be {@code null} for {@code static} fields
     * @param forceAccess whether to break scope restrictions using the {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} method
     *
     * @return the field value from a filed in the target
     */
    @Nullable
    public static Object readField(@Nullable Field field, @Nullable Object target, boolean forceAccess) {
        if (field == null) {
            return null;
        }
        try {
            return FieldUtils.readField(field, target, forceAccess);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Object readField(@Nullable Object target, @Nullable String fieldName) {
        return readField(target, fieldName, false);
    }

    @Nullable
    public static Object readField(@Nullable Object target, @Nullable String fieldName, boolean forceAccess) {
        if (target == null || StringUtils.isBlank(fieldName)) {
            return null;
        }
        try {
            return FieldUtils.readField(target, fieldName, forceAccess);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static <T> T readFieldAs(@Nullable Class<?> clazz, @Nullable String fieldName, @Nullable Class<T> expectedType) {
        return readFieldAs(clazz, fieldName, false, expectedType);
    }

    @Nullable
    public static <T> T readFieldAs(@Nullable Class<?> clazz, @Nullable String fieldName, boolean forceAccess, @Nullable Class<T> expectedType) {
        if (ObjectUtils.anyNull(clazz, expectedType) || StringUtils.isBlank(fieldName)) {
            return null;
        }
        return ObjectUtilsWraps.castAs(readField(clazz, fieldName, forceAccess), expectedType);
    }

    @Nullable
    public static <T> T readFieldAs(@Nullable Field field, @Nullable Object target, @Nullable Class<T> expectedType) {
        return readFieldAs(field, target, false, expectedType);
    }

    /**
     * Returns the field value from a filed in target
     *
     * @param field the field to read
     * @param target the object to call on, may be {@code null} for {@code static} fields
     * @param forceAccess whether to break scope restrictions using the {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} method
     * @param expectedType the expected class to check
     *
     * @return the field value from a filed in target
     */
    @Nullable
    public static <T> T readFieldAs(@Nullable Field field, @Nullable Object target, boolean forceAccess, @Nullable Class<T> expectedType) {
        if (ObjectUtils.anyNull(field, expectedType)) {
            return null;
        }
        return ObjectUtilsWraps.castAs(readField(field, target, forceAccess), expectedType);
    }

    @Nullable
    public static <T> T readFieldAs(@Nullable Object target, @Nullable String fieldName, @Nullable Class<T> expectedType) {
        return readFieldAs(target, fieldName, false, expectedType);
    }

    @Nullable
    public static <T> T readFieldAs(@Nullable Object target, @Nullable String fieldName, boolean forceAccess, @Nullable Class<T> expectedType) {
        if (ObjectUtils.anyNull(target, expectedType) || StringUtils.isBlank(fieldName)) {
            return null;
        }
        return ObjectUtilsWraps.castAs(readField(target, fieldName, forceAccess), expectedType);
    }

    @Nullable
    public static Object readDeclaredField(@Nullable Class<?> clazz, @Nullable String fieldName) {
        return readDeclaredField(clazz, fieldName, false);
    }

    @Nullable
    public static Object readDeclaredField(@Nullable Class<?> clazz, @Nullable String fieldName, boolean forceAccess) {
        if (clazz == null || StringUtils.isBlank(fieldName)) {
            return null;
        }
        try {
            return FieldUtils.readDeclaredField(clazz, fieldName, forceAccess);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Object readDeclaredField(@Nullable Object target, @Nullable String fieldName) {
        return readDeclaredField(target, fieldName, false);
    }

    @Nullable
    public static Object readDeclaredField(@Nullable Object target, @Nullable String fieldName, boolean forceAccess) {
        if (target == null || StringUtils.isBlank(fieldName)) {
            return null;
        }
        try {
            return FieldUtils.readDeclaredField(target, fieldName, forceAccess);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static <T> T readDeclaredFieldAs(@Nullable Class<?> clazz, @Nullable String fieldName, @Nullable Class<T> expectedType) {
        return readDeclaredFieldAs(clazz, fieldName, false, expectedType);
    }

    @Nullable
    public static <T> T readDeclaredFieldAs(@Nullable Class<?> clazz, @Nullable String fieldName, boolean forceAccess, @Nullable Class<T> expectedType) {
        if (ObjectUtils.anyNull(clazz, expectedType) || StringUtils.isBlank(fieldName)) {
            return null;
        }
        return ObjectUtilsWraps.castAs(readDeclaredField(clazz, fieldName, forceAccess), expectedType);
    }

    @Nullable
    public static <T> T readDeclaredFieldAs(@Nullable Object target, @Nullable String fieldName, @Nullable Class<T> expectedType) {
        return readDeclaredFieldAs(target, fieldName, false, expectedType);
    }

    @Nullable
    public static <T> T readDeclaredFieldAs(@Nullable Object target, @Nullable String fieldName, boolean forceAccess, @Nullable Class<T> expectedType) {
        if (ObjectUtils.anyNull(target, expectedType) || StringUtils.isBlank(fieldName)) {
            return null;
        }
        return ObjectUtilsWraps.castAs(readDeclaredField(target, fieldName, forceAccess), expectedType);
    }

    @Nullable
    public static Object readDeclaredStaticField(@Nullable Class<?> clazz, @Nullable String fieldName) {
        return readDeclaredStaticField(clazz, fieldName, false);
    }

    @Nullable
    public static Object readDeclaredStaticField(@Nullable Class<?> clazz, @Nullable String fieldName, boolean forceAccess) {
        if (clazz == null || StringUtils.isBlank(fieldName)) {
            return null;
        }
        try {
            return FieldUtils.readDeclaredStaticField(clazz, fieldName, forceAccess);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static <T> T readDeclaredStaticFieldAs(@Nullable Class<?> clazz, @Nullable String fieldName, @Nullable Class<T> expectedType) {
        return readDeclaredStaticFieldAs(clazz, fieldName, false, expectedType);
    }

    @Nullable
    public static <T> T readDeclaredStaticFieldAs(@Nullable Class<?> clazz, @Nullable String fieldName, boolean forceAccess, @Nullable Class<T> expectedType) {
        if (ObjectUtils.anyNull(clazz, expectedType) || StringUtils.isBlank(fieldName)) {
            return null;
        }
        return ObjectUtilsWraps.castAs(readDeclaredStaticField(clazz, fieldName, forceAccess), expectedType);
    }

    @Nullable
    public static Object readStaticField(@Nullable Class<?> clazz, @Nullable String fieldName) {
        return readStaticField(clazz, fieldName, false);
    }

    @Nullable
    public static Object readStaticField(@Nullable Class<?> clazz, @Nullable String fieldName, boolean forceAccess) {
        if (clazz == null || StringUtils.isBlank(fieldName)) {
            return null;
        }
        try {
            return FieldUtils.readStaticField(clazz, fieldName, forceAccess);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Object readStaticField(@Nullable Field field) {
        return readStaticField(field, false);
    }

    @Nullable
    public static Object readStaticField(@Nullable Field field, boolean forceAccess) {
        if (field == null) {
            return null;
        }
        try {
            return FieldUtils.readStaticField(field, forceAccess);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static <T> T readStaticFieldAs(@Nullable Class<?> clazz, @Nullable String fieldName, @Nullable Class<T> expectedType) {
        return readStaticFieldAs(clazz, fieldName, false, expectedType);
    }

    @Nullable
    public static <T> T readStaticFieldAs(@Nullable Class<?> clazz, @Nullable String fieldName, boolean forceAccess, @Nullable Class<T> expectedType) {
        if (ObjectUtils.anyNull(clazz, expectedType) || StringUtils.isBlank(fieldName)) {
            return null;
        }
        return ObjectUtilsWraps.castAs(readStaticField(clazz, fieldName, forceAccess), expectedType);
    }

    @Nullable
    public static <T> T readStaticFieldAs(@Nullable Field field, @Nullable Class<T> expectedType) {
        return readStaticFieldAs(field, false, expectedType);
    }

    @Nullable
    public static <T> T readStaticFieldAs(@Nullable Field field, boolean forceAccess, @Nullable Class<T> expectedType) {
        if (ObjectUtils.anyNull(field, expectedType)) {
            return null;
        }
        return ObjectUtilsWraps.castAs(readStaticField(field, forceAccess), expectedType);
    }

    public static void writeField(@Nullable Field field, @Nullable Object target, @Nullable Object value) {
        writeField(field, target, value, false);
    }

    /**
     * Write a value to a filed in target
     *
     * @param field the field to write
     * @param target the object to call on, may be {@code null} for {@code static} fields
     * @param value the value to set
     * @param forceAccess whether to break scope restrictions using the {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} method
     */
    public static void writeField(@Nullable Field field, @Nullable Object target, @Nullable Object value, boolean forceAccess) {
        if (field == null) {
            return;
        }
        try {
            FieldUtils.writeField(field, target, value, forceAccess);
        } catch (Exception ignored) {
        }
    }

    public static void writeField(@Nullable Object target, @Nullable String fieldName, @Nullable Object value) {
        writeField(target, fieldName, value, false);
    }

    /**
     * Write a value to a filed in target, superclasses will be considered
     *
     * @param target the object to reflect
     * @param fieldName the field name to obtain
     * @param value the value to set
     * @param forceAccess whether to break scope restrictions using the {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} method
     */
    public static void writeField(@Nullable Object target, @Nullable String fieldName, @Nullable Object value, boolean forceAccess) {
        if (target == null || StringUtils.isBlank(fieldName)) {
            return;
        }
        try {
            FieldUtils.writeField(target, fieldName, value, forceAccess);
        } catch (Exception ignored) {
        }
    }

    public static void writeStaticField(@Nullable Field field, @Nullable Object value) {
        writeStaticField(field, value, false);
    }

    /**
     * Write a value to a filed in target
     *
     * @param field the field to write
     * @param value the value to set
     * @param forceAccess whether to break scope restrictions using the {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} method
     */
    public static void writeStaticField(@Nullable Field field, @Nullable Object value, boolean forceAccess) {
        if (field == null) {
            return;
        }
        try {
            FieldUtils.writeStaticField(field, value, forceAccess);
        } catch (Exception ignored) {
        }
    }

    public static void writeStaticField(@Nullable Class<?> clazz, @Nullable String fieldName, @Nullable Object value) {
        writeStaticField(clazz, fieldName, value, false);
    }

    /**
     * Write a value to a filed in target, superclasses will be considered
     *
     * @param clazz on which {@link java.lang.Class} the field is to be found
     * @param fieldName the field name to obtain
     * @param value the value to set
     * @param forceAccess whether to break scope restrictions using the {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} method
     */
    public static void writeStaticField(@Nullable Class<?> clazz, @Nullable String fieldName, @Nullable Object value, boolean forceAccess) {
        if (clazz == null || StringUtils.isBlank(fieldName)) {
            return;
        }
        try {
            FieldUtils.writeStaticField(clazz, fieldName, value, forceAccess);
        } catch (Exception ignored) {
        }
    }
}
