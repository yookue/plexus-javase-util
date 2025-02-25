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


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import com.yookue.commonplexus.javaseutil.exception.UnsupportedClassException;


/**
 * Utilities for {@link org.apache.commons.lang3.ObjectUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.ObjectUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ObjectUtilsWraps {
    public static boolean allNull(@Nullable Object... values) {
        return allNull(ArrayUtilsWraps.asList(values));
    }

    public static boolean allNull(@Nullable Collection<Object> values) {
        return values == null || values.stream().allMatch(Objects::isNull);
    }

    public static boolean allEmpty(@Nullable Object... values) {
        return allEmpty(ArrayUtilsWraps.asList(values));
    }

    public static boolean allEmpty(@Nullable Collection<Object> values) {
        return CollectionPlainWraps.isEmpty(values) || values.stream().allMatch(ObjectUtils::isEmpty);
    }

    public static boolean allNotNull(@Nullable Object... values) {
        return allNotNull(ArrayUtilsWraps.asList(values));
    }

    public static boolean allNotNull(@Nullable Collection<Object> values) {
        return values != null && values.stream().allMatch(Objects::nonNull);
    }

    public static boolean allNotEmpty(@Nullable Object... values) {
        return allNotEmpty(ArrayUtilsWraps.asList(values));
    }

    public static boolean allNotEmpty(@Nullable Collection<Object> values) {
        return CollectionPlainWraps.isNotEmpty(values) && values.stream().noneMatch(ObjectUtils::isEmpty);
    }

    public static boolean anyNull(@Nullable Object... values) {
        return anyNull(ArrayUtilsWraps.asList(values));
    }

    public static boolean anyNull(@Nullable Collection<Object> values) {
        return values == null || values.stream().anyMatch(Objects::isNull);
    }

    public static boolean anyEmpty(@Nullable Object... values) {
        return anyEmpty(ArrayUtilsWraps.asList(values));
    }

    public static boolean anyEmpty(@Nullable Collection<Object> values) {
        return CollectionPlainWraps.isEmpty(values) || values.stream().anyMatch(ObjectUtils::isEmpty);
    }

    public static boolean anyNotNull(@Nullable Object... values) {
        return anyNotNull(ArrayUtilsWraps.asList(values));
    }

    public static boolean anyNotNull(@Nullable Collection<Object> values) {
        return values != null && values.stream().anyMatch(Objects::nonNull);
    }

    public static boolean anyNotEmpty(@Nullable Object... values) {
        return anyNotEmpty(ArrayUtilsWraps.asList(values));
    }

    public static boolean anyNotEmpty(@Nullable Collection<Object> values) {
        return CollectionPlainWraps.isNotEmpty(values) && values.stream().anyMatch(ObjectUtils::isNotEmpty);
    }

    public static <T> T castAs(@Nullable Object source, @Nullable Class<T> expectedType) {
        return castAs(source, expectedType, null);
    }

    /**
     * Returns a cast instance of the expected class, if the {@code source} object is instanceof the expected class
     *
     * @param source an object that maybe instanceof the expected class
     * @param expectedType a parent/child class to check
     * @param defaultValue the default value to return if expected value is null
     *
     * @return a cast instance of the expected class, if the {@code source} object is instanceof the expected class
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T> T castAs(@Nullable Object source, @Nullable Class<T> expectedType, @Nullable T defaultValue) {
        return (ObjectUtils.anyNull(source, expectedType) || !expectedType.isInstance(source)) ? defaultValue : expectedType.cast(source);
    }

    @Nullable
    public static Object[] castAsArray(@Nullable Object source) {
        return castAsArray(source, false);
    }

    /**
     * Convert the given object (which may be a primitive array) to an object array (if necessary of primitive wrapper objects)
     *
     * @param source the (potentially primitive) array
     * @param transform indicates whether to detect the source object type and try to convert it
     *
     * @return the corresponding object array
     *
     * @see "org.springframework.util.ObjectUtils#toObjectArray"
     */
    @Nullable
    public static Object[] castAsArray(@Nullable Object source, boolean transform) {
        if (source == null) {
            return null;
        }
        if (source instanceof Object[] alias) {
            return alias;
        }
        if (isArray(source)) {
            Class<?> clazz = ArrayUtilsWraps.getComponentType(source);
            if (clazz == null) {
                return null;
            }
            int length = ArrayUtilsWraps.getLength(source);
            Object[] result = (Object[]) Array.newInstance(clazz, length);
            ArrayUtilsWraps.forEachObjectIndexing(source, (index, element) -> result[index] = element);
            return result;
        }
        if (!transform) {
            return null;
        }
        if (source instanceof Iterable<?> alias) {
            return IterablePlainWraps.toObjectArray(alias);
        } else if (source instanceof Iterator<?> alias) {
            return IteratorPlainWraps.toObjectArray(alias);
        } else if (source instanceof Enumeration<?> alias) {
            return EnumerationPlainWraps.toObjectArray(alias);
        }
        return null;
    }

    public static <E> Collection<E> castAsCollection(@Nullable Object source, @Nullable Class<E> componentType) {
        return castAsCollection(source, componentType, null);
    }

    @SuppressWarnings("unchecked")
    public static <E> Collection<E> castAsCollection(@Nullable Object source, @Nullable Class<E> componentType, @Nullable Collection<E> defaultValue) {
        return castAsIterable(source, Collection.class, componentType, defaultValue);
    }

    @Nullable
    public static List<?> castAsList(@Nullable Object source) {
        return castAsList(source, false);
    }

    @Nullable
    public static List<?> castAsList(@Nullable Object source, boolean transform) {
        if (source == null) {
            return null;
        }
        if (source instanceof List<?> alias) {
            return alias;
        }
        if (!transform) {
            return null;
        }
        if (isArray(source)) {
            return ArrayUtilsWraps.asList(true, castAsArray(source, true));
        }
        if (source instanceof Iterable<?> alias) {
            return IterablePlainWraps.toObjectList(alias);
        } else if (source instanceof Iterator<?> alias) {
            return IteratorPlainWraps.toObjectList(alias);
        } else if (source instanceof Enumeration<?> alias) {
            return EnumerationPlainWraps.toObjectList(alias);
        }
        return null;
    }

    public static <E> List<E> castAsList(@Nullable Object source, @Nullable Class<E> componentType) {
        return castAsList(source, componentType, null);
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> castAsList(@Nullable Object source, @Nullable Class<E> componentType, @Nullable List<E> defaultValue) {
        return castAsIterable(source, List.class, componentType, defaultValue);
    }

    public static <T extends Iterable<E>, E> T castAsIterable(@Nullable Object source, @Nullable Class<T> expectedType, @Nullable Class<E> componentType) {
        return castAsIterable(source, expectedType, componentType, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Iterable<E>, E> T castAsIterable(@Nullable Object source, @Nullable Class<T> expectedType, @Nullable Class<E> componentType, @Nullable T defaultValue) {
        if (ObjectUtils.anyNull(source, expectedType, componentType) || !expectedType.isInstance(source)) {
            return defaultValue;
        }
        T result = expectedType.cast(source);
        Class<?> clazz = IterablePlainWraps.getComponentType(result);
        if (clazz == null || !componentType.isAssignableFrom(clazz)) {
            return defaultValue;
        }
        return result;
    }

    public static <T extends Iterator<E>, E> T castAsIterator(@Nullable Object source, @Nullable Class<T> expectedType, @Nullable Class<E> componentType) {
        return castAsIterator(source, expectedType, componentType, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Iterator<E>, E> T castAsIterator(@Nullable Object source, @Nullable Class<T> expectedType, @Nullable Class<E> componentType, @Nullable T defaultValue) {
        if (ObjectUtils.anyNull(source, expectedType, componentType) || !expectedType.isInstance(source)) {
            return defaultValue;
        }
        T result = expectedType.cast(source);
        Class<?> clazz = IteratorPlainWraps.getComponentType(result);
        if (clazz == null || !componentType.isAssignableFrom(clazz)) {
            return defaultValue;
        }
        return result;
    }

    public static <E> Set<E> castAsSet(@Nullable Object source, @Nullable Class<E> componentType) {
        return castAsSet(source, componentType, null);
    }

    @SuppressWarnings("unchecked")
    public static <E> Set<E> castAsSet(@Nullable Object source, @Nullable Class<E> componentType, @Nullable Set<E> defaultValue) {
        return castAsIterable(source, Set.class, componentType, defaultValue);
    }

    /**
     * @see org.apache.commons.lang3.ObjectUtils#defaultIfNull
     */
    public static <T> T defaultIfEquals(@Nullable T target, @Nullable T comparison, @Nullable T defaultValue) {
        return Objects.equals(target, comparison) ? defaultValue : target;
    }

    /**
     * Determines if the given objects are equal
     * <p>
     * Returning {@code true} if both are {@code null}, {@code false} if only one is {@code null}
     *
     * @param target first Object to compare
     * @param comparison second Object to compare
     *
     * @return {@code true} if the given objects are equal
     *
     * @see java.util.Objects#equals(Object a, Object b)
     * @see "org.springframework.util.ObjectUtils#nullSafeEquals"
     */
    public static boolean equals(@Nullable Object target, @Nullable Object comparison) {
        if (target == comparison) {
            return true;
        }
        if (target == null || comparison == null) {
            return false;
        }
        if (target.equals(comparison)) {
            return true;
        }
        if (target.getClass().isArray() && comparison.getClass().isArray()) {
            if (target instanceof Object[] targetAlias && comparison instanceof Object[] comparisonAlias) {
                return Arrays.equals(targetAlias, comparisonAlias);
            }
            if (target instanceof boolean[] targetAlias && comparison instanceof boolean[] comparisonAlias) {
                return Arrays.equals(targetAlias, comparisonAlias);
            }
            if (target instanceof byte[] targetAlias && comparison instanceof byte[] comparisonAlias) {
                return Arrays.equals(targetAlias, comparisonAlias);
            }
            if (target instanceof char[] targetAlias && comparison instanceof char[] comparisonAlias) {
                return Arrays.equals(targetAlias, comparisonAlias);
            }
            if (target instanceof double[] targetAlias && comparison instanceof double[] comparisonAlias) {
                return Arrays.equals(targetAlias, comparisonAlias);
            }
            if (target instanceof float[] targetAlias && comparison instanceof float[] comparisonAlias) {
                return Arrays.equals(targetAlias, comparisonAlias);
            }
            if (target instanceof int[] targetAlias && comparison instanceof int[] comparisonAlias) {
                return Arrays.equals(targetAlias, comparisonAlias);
            }
            if (target instanceof long[] targetAlias && comparison instanceof long[] comparisonAlias) {
                return Arrays.equals(targetAlias, comparisonAlias);
            }
            if (target instanceof short[] targetAlias && comparison instanceof short[] comparisonAlias) {
                return Arrays.equals(targetAlias, comparisonAlias);
            }
        }
        return false;
    }

    @SafeVarargs
    public static <T> boolean equalsAny(@Nullable T target, @Nullable T... comparisons) {
        return equalsAny(target, ArrayUtilsWraps.asList(comparisons));
    }

    public static <T> boolean equalsAny(@Nullable T target, @Nullable Collection<T> comparisons) {
        return ObjectUtils.allNull(target, comparisons) || CollectionPlainWraps.contains(comparisons, target);
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Object source) {
        return getComponentType(source, false);
    }

    @Nullable
    public static Class<?> getComponentType(@Nullable Object source, boolean deepScan) {
        if (source == null) {
            return null;
        }
        if (source instanceof Object[] alias) {
            return ArrayUtilsWraps.getComponentType(alias);
        } else if (source instanceof Iterable<?> alias) {
            return IterablePlainWraps.getComponentType(alias, deepScan);
        } else if (source instanceof Iterator<?> alias) {
            return IteratorPlainWraps.getComponentType(alias, deepScan);
        } else if (source instanceof Enumeration<?> alias) {
            return EnumerationPlainWraps.getComponentType(alias, deepScan);
        }
        if (isArray(source)) {
            return ArrayUtilsWraps.getComponentType(source);
        }
        return null;
    }

    public static <T> void ifEmpty(@Nullable T source, @Nullable Consumer<? super T> action) {
        ifEmptyOrElse(source, action, null);
    }

    public static <T> void ifEmpty(@Nullable T source, @Nullable Runnable action) {
        ifEmptyOrElse(source, action, null);
    }

    public static <T> void ifEmptyOrElse(@Nullable T source, @Nullable Consumer<? super T> absentAction, @Nullable Consumer<? super T> presentAction) {
        if (ObjectUtils.isEmpty(source)) {
            if (absentAction != null) {
                absentAction.accept(source);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(source);
            }
        }
    }

    public static <T> void ifEmptyOrElse(@Nullable T source, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (ObjectUtils.isEmpty(source)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static void ifNull(@Nullable Object source, @Nullable Runnable action) {
        if (source == null && action != null) {
            action.run();
        }
    }

    public static <T> void ifNullOrElse(@Nullable T source, @Nullable Runnable absentAction, @Nullable Consumer<? super T> presentAction) {
        if (source == null) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(source);
            }
        }
    }

    public static <T> void ifNullOrElse(@Nullable T source, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (source == null) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static <T> void ifNotEmpty(@Nullable T source, @Nullable Consumer<? super T> action) {
        ifNotEmpty(source, action, null);
    }

    public static <T> void ifNotEmpty(@Nullable T source, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (ObjectUtils.isNotEmpty(source) && action != null && (filter == null || filter.test(source))) {
            action.accept(source);
        }
    }

    public static <T> void ifNotEmpty(@Nullable T source, @Nullable Runnable action) {
        if (ObjectUtils.isNotEmpty(source) && action != null) {
            action.run();
        }
    }

    public static <T> void ifNotNull(@Nullable T source, @Nullable Consumer<? super T> action) {
        ifNotNull(source, action, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T> void ifNotNull(@Nullable T source, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (ObjectUtils.allNotNull(source, action) && (filter == null || filter.test(source))) {
            action.accept(source);
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T> void ifNotNull(@Nullable T source, @Nullable Runnable action) {
        if (ObjectUtils.allNotNull(source, action)) {
            action.run();
        }
    }

    /**
     * Returns whether the given object is an array or not
     *
     * @param object the source object to check
     *
     * @return whether the given object is an array or not
     *
     * @see "org.springframework.util.ObjectUtils#isArray"
     */
    public static boolean isArray(@Nullable Object object) {
        return object != null && object.getClass().isArray();
    }

    /**
     * Returns whether the given object is an enum or not
     *
     * @param object the source object to check
     *
     * @return whether the given object is an enum or not
     */
    public static boolean isEnum(@Nullable Object object) {
        return object != null && object.getClass().isEnum();
    }

    /**
     * Returns whether the given object is a record
     *
     * @param object the source object to check
     *
     * @return whether the given object is a record or not
     */
    public static boolean isRecord(@Nullable Object object) {
        return object != null && object.getClass().isRecord();
    }

    public static boolean isPrimitive(@Nullable Object object) {
        return object != null && object.getClass().isPrimitive();
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSameSize(@Nullable Object... objects) {
        if (ArrayUtils.getLength(objects) < 2) {
            return false;
        }
        int size = size(ArrayUtils.get(objects, 0));
        return Arrays.stream(objects).skip(1L).allMatch(element -> size(element) == size);
    }

    /**
     * Returns the size of the specified object
     * <p>
     * This method can handle objects as follows
     * </p>
     * <ul>
     * <li>Collection - the collection size
     * <li>Map - the map size
     * <li>Array - the array size
     * <li>Iterator - the number of elements remaining in the iterator
     * <li>Enumeration - the number of elements remaining in the enumeration
     * </ul>
     *
     * @param object the object to get the size of, may be null
     *
     * @return the size of the specified object, or 0 if the object is {@code null}
     *
     * @throws com.yookue.commonplexus.javaseutil.exception.UnsupportedClassException thrown if object is not recognized
     */
    public static int size(@Nullable Object object) {
        if (object == null) {
            return 0;
        }
        if (object instanceof Map<?, ?> alias) {
            return alias.size();
        } else if (object instanceof Collection<?> alias) {
            return alias.size();
        } else if (object instanceof Iterable<?> alias) {
            return IterablePlainWraps.size(alias);
        } else if (object instanceof Object[] alias) {
            return alias.length;
        } else if (object instanceof Iterator<?> alias) {
            return IteratorPlainWraps.size(alias);
        } else if (object instanceof Enumeration<?> alias) {
            return EnumerationPlainWraps.size(alias);
        } else if (isArray(object)) {
            return Array.getLength(object);
        }
        throw new UnsupportedClassException("Unsupported object class type: " + object.getClass().getName());
    }

    public static int maxSize(@Nullable Object... objects) {
        return maxSize(ArrayUtilsWraps.asList(objects));
    }

    public static int maxSize(@Nullable Collection<Object> objects) {
        return CollectionPlainWraps.isEmpty(objects) ? 0 : objects.stream().mapToInt(ObjectUtilsWraps::size).max().orElse(0);
    }

    public static int sumSize(@Nullable Object... objects) {
        return sumSize(ArrayUtilsWraps.asList(objects));
    }

    public static int sumSize(@Nullable Collection<Object> objects) {
        return CollectionPlainWraps.isEmpty(objects) ? 0 : objects.stream().mapToInt(ObjectUtilsWraps::size).sum();
    }

    public static String toString(@Nullable Object source) {
        return toString(source, null);
    }

    /**
     * Returns the string representation of the given object
     *
     * @param source the given source object
     * @param nullString the default string value if {@code source} is null
     *
     * @return the string representation of the given object
     *
     * @see java.util.Objects#toString(java.lang.Object, java.lang.String)
     * @see "org.springframework.util.ObjectUtils#nullSafeToString(java.lang.Object)"
     */
    public static String toString(@Nullable Object source, @Nullable String nullString) {
        if (source == null) {
            return nullString;
        }
        if (source instanceof String alias) {
            return alias;
        }
        if (source instanceof CharSequence alias) {
            return alias.toString();
        }
        if (isArray(source)) {
            if (source instanceof Object[] alias) {
                return Arrays.toString(alias);
            }
            if (source instanceof boolean[] alias) {
                return Arrays.toString(alias);
            }
            if (source instanceof byte[] alias) {
                return Arrays.toString(alias);
            }
            if (source instanceof char[] alias) {
                return Arrays.toString(alias);
            }
            if (source instanceof double[] alias) {
                return Arrays.toString(alias);
            }
            if (source instanceof float[] alias) {
                return Arrays.toString(alias);
            }
            if (source instanceof int[] alias) {
                return Arrays.toString(alias);
            }
            if (source instanceof long[] alias) {
                return Arrays.toString(alias);
            }
            if (source instanceof short[] alias) {
                return Arrays.toString(alias);
            }
        }
        return Objects.toString(source, nullString);
    }
}
