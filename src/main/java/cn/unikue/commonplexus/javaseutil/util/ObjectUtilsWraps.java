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
import cn.unikue.commonplexus.javaseutil.exception.UnsupportedClassException;


/**
 * Utilities for {@link org.apache.commons.lang3.ObjectUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.ObjectUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ObjectUtilsWraps {
    /**
     * Checks whether all given objects are null
     *
     * @param values The objects to check
     *
     * @return {@code true} if all objects are null, {@code false} otherwise
     */
    public static boolean allNull(@Nullable Object... values) {
        return allNull(ArrayUtilsWraps.asList(values));
    }

    /**
     * Checks whether all objects in the collection are null
     *
     * @param values The collection of objects to check
     *
     * @return {@code true} if all objects are null or the collection is null, {@code false} otherwise
     */
    public static boolean allNull(@Nullable Collection<Object> values) {
        return values == null || values.stream().allMatch(Objects::isNull);
    }

    /**
     * Checks whether all given objects are empty
     *
     * @param values The objects to check
     *
     * @return {@code true} if all objects are empty, {@code false} otherwise
     */
    public static boolean allEmpty(@Nullable Object... values) {
        return allEmpty(ArrayUtilsWraps.asList(values));
    }

    /**
     * Checks whether all objects in the collection are empty
     *
     * @param values The collection of objects to check
     *
     * @return {@code true} if the collection is empty or all objects are empty, {@code false} otherwise
     */
    public static boolean allEmpty(@Nullable Collection<Object> values) {
        return CollectionPlainWraps.isEmpty(values) || values.stream().allMatch(ObjectUtils::isEmpty);
    }

    /**
     * Checks whether all given objects are not null
     *
     * @param values The objects to check
     *
     * @return {@code true} if all objects are not null, {@code false} otherwise
     */
    public static boolean allNotNull(@Nullable Object... values) {
        return allNotNull(ArrayUtilsWraps.asList(values));
    }

    /**
     * Checks whether all objects in the collection are not null
     *
     * @param values The collection of objects to check
     *
     * @return {@code true} if the collection is not null and all objects are not null, {@code false} otherwise
     */
    public static boolean allNotNull(@Nullable Collection<Object> values) {
        return values != null && values.stream().allMatch(Objects::nonNull);
    }

    /**
     * Checks whether all given objects are not empty
     *
     * @param values The objects to check
     *
     * @return {@code true} if all objects are not empty, {@code false} otherwise
     */
    public static boolean allNotEmpty(@Nullable Object... values) {
        return allNotEmpty(ArrayUtilsWraps.asList(values));
    }

    /**
     * Checks whether all objects in the collection are not empty
     *
     * @param values The collection of objects to check
     *
     * @return {@code true} if the collection is not empty and no object is empty, {@code false} otherwise
     */
    public static boolean allNotEmpty(@Nullable Collection<Object> values) {
        return CollectionPlainWraps.isNotEmpty(values) && values.stream().noneMatch(ObjectUtils::isEmpty);
    }

    /**
     * Checks whether any of the given objects is null
     *
     * @param values The objects to check
     *
     * @return {@code true} if any object is null or the array is null, {@code false} otherwise
     */
    public static boolean anyNull(@Nullable Object... values) {
        return anyNull(ArrayUtilsWraps.asList(values));
    }

    /**
     * Checks whether any object in the collection is null
     *
     * @param values The collection of objects to check
     *
     * @return {@code true} if the collection is null or any object is null, {@code false} otherwise
     */
    public static boolean anyNull(@Nullable Collection<Object> values) {
        return values == null || values.stream().anyMatch(Objects::isNull);
    }

    /**
     * Checks whether any of the given objects is empty
     *
     * @param values The objects to check
     *
     * @return {@code true} if any object is empty or the array is null, {@code false} otherwise
     */
    public static boolean anyEmpty(@Nullable Object... values) {
        return anyEmpty(ArrayUtilsWraps.asList(values));
    }

    /**
     * Checks whether any object in the collection is empty
     *
     * @param values The collection of objects to check
     *
     * @return {@code true} if the collection is empty or any object is empty, {@code false} otherwise
     */
    public static boolean anyEmpty(@Nullable Collection<Object> values) {
        return CollectionPlainWraps.isEmpty(values) || values.stream().anyMatch(ObjectUtils::isEmpty);
    }

    /**
     * Checks whether any of the given objects is not null
     *
     * @param values The objects to check
     *
     * @return {@code true} if any object is not null, {@code false} otherwise
     */
    public static boolean anyNotNull(@Nullable Object... values) {
        return anyNotNull(ArrayUtilsWraps.asList(values));
    }

    /**
     * Checks whether any object in the collection is not null
     *
     * @param values The collection of objects to check
     *
     * @return {@code true} if the collection is not null and any object is not null, {@code false} otherwise
     */
    public static boolean anyNotNull(@Nullable Collection<Object> values) {
        return values != null && values.stream().anyMatch(Objects::nonNull);
    }

    /**
     * Checks whether any of the given objects is not empty
     *
     * @param values The objects to check
     *
     * @return {@code true} if any object is not empty, {@code false} otherwise
     */
    public static boolean anyNotEmpty(@Nullable Object... values) {
        return anyNotEmpty(ArrayUtilsWraps.asList(values));
    }

    /**
     * Checks whether any object in the collection is not empty
     *
     * @param values The collection of objects to check
     *
     * @return {@code true} if the collection is not empty and any object is not empty, {@code false} otherwise
     */
    public static boolean anyNotEmpty(@Nullable Collection<Object> values) {
        return CollectionPlainWraps.isNotEmpty(values) && values.stream().anyMatch(ObjectUtils::isNotEmpty);
    }

    /**
     * Returns a cast instance of the expected class, or null if type mismatch
     *
     * @param source The object to cast
     * @param expectType The expected type to cast to
     *
     * @return a cast instance of the expected class, or null if type mismatch
     */
    public static <T> T castAs(@Nullable Object source, @Nullable Class<T> expectType) {
        return castAs(source, expectType, null);
    }

    /**
     * Returns a cast instance of the expected class, if the {@code source} object is instanceof the expected class
     *
     * @param source The object that maybe instanceof the expected class
     * @param expectType The parent/child class to check
     * @param defaultValue The default value to return if expected value is null
     *
     * @return a cast instance of the expected class, if the {@code source} object is instanceof the expected class
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T> T castAs(@Nullable Object source, @Nullable Class<T> expectType, @Nullable T defaultValue) {
        return (ObjectUtils.anyNull(source, expectType) || !expectType.isInstance(source)) ? defaultValue : expectType.cast(source);
    }

    /**
     * Returns a cast String instance, or null if source is not a String
     *
     * @param source The object to cast
     *
     * @return a cast String instance, or null if source is not a String
     */
    public static String castAsString(@Nullable Object source) {
        return castAsString(source, null);
    }

    /**
     * Returns a cast String instance, or defaultValue if source is not a String
     *
     * @param source The object to cast
     * @param defaultValue The default value to return if source is not a String
     *
     * @return a cast String instance, or defaultValue if source is not a String
     */
    public static String castAsString(@Nullable Object source, @Nullable String defaultValue) {
        return castAs(source, String.class, defaultValue);
    }

    /**
     * Returns a cast Integer instance, or null if source is not an Integer
     *
     * @param source The object to cast
     *
     * @return a cast Integer instance, or null if source is not an Integer
     */
    public static Integer castAsInteger(@Nullable Object source) {
        return castAsInteger(source, null);
    }

    /**
     * Returns a cast Integer instance, or defaultValue if source is not an Integer
     *
     * @param source The object to cast
     * @param defaultValue The default value to return if source is not an Integer
     *
     * @return a cast Integer instance, or defaultValue if source is not an Integer
     */
    public static Integer castAsInteger(@Nullable Object source, @Nullable Integer defaultValue) {
        return castAs(source, Integer.class, defaultValue);
    }

    /**
     * Returns a cast Boolean instance, or null if source is not a Boolean
     *
     * @param source The object to cast
     *
     * @return a cast Boolean instance, or null if source is not a Boolean
     */
    public static Boolean castAsBoolean(@Nullable Object source) {
        return castAsBoolean(source, null);
    }

    /**
     * Returns a cast Boolean instance, or defaultValue if source is not a Boolean
     *
     * @param source The object to cast
     * @param defaultValue The default value to return if source is not a Boolean
     *
     * @return a cast Boolean instance, or defaultValue if source is not a Boolean
     */
    public static Boolean castAsBoolean(@Nullable Object source, @Nullable Boolean defaultValue) {
        return castAs(source, Boolean.class, defaultValue);
    }

    /**
     * Returns a cast Object array, or null if source is not an array
     *
     * @param source The object to cast
     *
     * @return a cast Object array, or null if source is not an array
     */
    @Nullable
    public static Object[] castAsArray(@Nullable Object source) {
        return castAsArray(source, false);
    }

    /**
     * Convert the given object (which may be a primitive array) to an object array (if necessary of primitive wrapper objects)
     *
     * @param source The (potentially primitive) array
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
            ArrayUtilsWraps.forEachObjectIndexing(source, (index, item) -> result[index] = item);
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

    /**
     * Returns a cast Collection instance with the specified component type, or null if type mismatch
     *
     * @param source The object to cast
     * @param componentType The expected component type of the collection
     *
     * @return a cast Collection instance, or null if type mismatch
     */
    public static <E> Collection<E> castAsCollection(@Nullable Object source, @Nullable Class<E> componentType) {
        return castAsCollection(source, componentType, null);
    }

    /**
     * Returns a cast Collection instance with the specified component type, or defaultValue if type mismatch
     *
     * @param source The object to cast
     * @param componentType The expected component type of the collection
     * @param defaultValue The default value to return if type mismatch
     *
     * @return a cast Collection instance, or defaultValue if type mismatch
     */
    @SuppressWarnings("unchecked")
    public static <E> Collection<E> castAsCollection(@Nullable Object source, @Nullable Class<E> componentType, @Nullable Collection<E> defaultValue) {
        return castAsIterable(source, Collection.class, componentType, defaultValue);
    }

    /**
     * Returns a cast List instance, or null if source is not a List
     *
     * @param source The object to check
     *
     * @return a cast List instance, or null if source is not a List
     */
    @Nullable
    public static List<?> castAsList(@Nullable Object source) {
        return castAsList(source, false);
    }

    /**
     * Returns a cast List instance by converting the source object if transform is true
     *
     * @param source The object to cast or convert
     * @param transform indicates whether to detect the source object type and try to convert it
     *
     * @return a cast List instance, or null if conversion is not possible
     */
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

    /**
     * Returns a cast List instance with the specified component type, or null if type mismatch
     *
     * @param source The object to cast
     * @param componentType The expected component type of the list
     *
     * @return a cast List instance, or null if type mismatch
     */
    public static <E> List<E> castAsList(@Nullable Object source, @Nullable Class<E> componentType) {
        return castAsList(source, componentType, null);
    }

    /**
     * Returns a cast List instance with the specified component type, or defaultValue if type mismatch
     *
     * @param source The object to cast
     * @param componentType The expected component type of the list
     * @param defaultValue The default value to return if type mismatch
     *
     * @return a cast List instance, or defaultValue if type mismatch
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> castAsList(@Nullable Object source, @Nullable Class<E> componentType, @Nullable List<E> defaultValue) {
        return castAsIterable(source, List.class, componentType, defaultValue);
    }

    /**
     * Returns a cast instance of the expected map, if the {@code source} object is instanceof {@link Map}
     *
     * @param source The object that maybe instanceof {@link Map}
     * @param keyType The expected key type to check
     * @param valueType The expected value type to check
     * @param defaultValue The default value to return if expected value is null or type mismatch
     *
     * @return a cast instance of the expected map, if the {@code source} object is instanceof {@link Map} and types match
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression", "unchecked"})
    public static <K, V> Map<K, V> castAsMap(@Nullable Object source, @Nullable Class<K> keyType, @Nullable Class<V> valueType, @Nullable Map<K, V> defaultValue) {
        if (ObjectUtils.anyNull(source, keyType, valueType) || !(source instanceof Map<?, ?> map)) {
            return defaultValue;
        }
        boolean typeMatch = map.entrySet().stream().allMatch(entry -> keyType.isInstance(entry.getKey()) && valueType.isInstance(entry.getValue()));
        return typeMatch ? (Map<K, V>) map : defaultValue;
    }

    /**
     * Returns a cast Iterable instance with the specified component type, or null if type mismatch
     *
     * @param source The object to cast
     * @param expectType The expected iterable type to cast to
     * @param componentType The expected component type of the iterable
     *
     * @return a cast Iterable instance, or null if type mismatch
     */
    public static <T extends Iterable<E>, E> T castAsIterable(@Nullable Object source, @Nullable Class<T> expectType, @Nullable Class<E> componentType) {
        return castAsIterable(source, expectType, componentType, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Iterable<E>, E> T castAsIterable(@Nullable Object source, @Nullable Class<T> expectType, @Nullable Class<E> componentType, @Nullable T defaultValue) {
        if (ObjectUtils.anyNull(source, expectType, componentType) || !expectType.isInstance(source)) {
            return defaultValue;
        }
        T result = expectType.cast(source);
        // For empty iterables, skip component type check since there are no elements to verify
        if (IterablePlainWraps.isNotEmpty(result)) {
            Class<?> clazz = IterablePlainWraps.getComponentType(result);
            if (clazz == null || !componentType.isAssignableFrom(clazz)) {
                return defaultValue;
            }
        }
        return result;
    }

    /**
     * Returns a cast Iterator instance with the specified component type, or null if type mismatch
     *
     * @param source The object to cast
     * @param expectType The expected iterator type to cast to
     * @param componentType The expected component type of the iterator
     *
     * @return a cast Iterator instance, or null if type mismatch
     */
    public static <T extends Iterator<E>, E> T castAsIterator(@Nullable Object source, @Nullable Class<T> expectType, @Nullable Class<E> componentType) {
        return castAsIterator(source, expectType, componentType, null);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Iterator<E>, E> T castAsIterator(@Nullable Object source, @Nullable Class<T> expectType, @Nullable Class<E> componentType, @Nullable T defaultValue) {
        if (ObjectUtils.anyNull(source, expectType, componentType) || !expectType.isInstance(source)) {
            return defaultValue;
        }
        T result = expectType.cast(source);
        // For empty iterators, skip component type check since there are no elements to verify
        if (IteratorPlainWraps.isNotEmpty(result)) {
            Class<?> clazz = IteratorPlainWraps.getComponentType(result);
            if (clazz == null || !componentType.isAssignableFrom(clazz)) {
                return defaultValue;
            }
        }
        return result;
    }

    /**
     * Returns a cast Set instance with the specified component type, or null if type mismatch
     *
     * @param source The object to cast
     * @param componentType The expected component type of the set
     *
     * @return a cast Set instance, or null if type mismatch
     */
    public static <E> Set<E> castAsSet(@Nullable Object source, @Nullable Class<E> componentType) {
        return castAsSet(source, componentType, null);
    }

    /**
     * Returns a cast Set instance with the specified component type, or defaultValue if type mismatch
     *
     * @param source The object to cast
     * @param componentType The expected component type of the set
     * @param defaultValue The default value to return if type mismatch
     *
     * @return a cast Set instance, or defaultValue if type mismatch
     */
    @SuppressWarnings("unchecked")
    public static <E> Set<E> castAsSet(@Nullable Object source, @Nullable Class<E> componentType, @Nullable Set<E> defaultValue) {
        return castAsIterable(source, Set.class, componentType, defaultValue);
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

    /**
     * Checks whether the target equals any of the comparison objects
     *
     * @param target The object to compare
     * @param comparisons The objects to compare against
     *
     * @return {@code true} if target equals any comparison, {@code false} otherwise
     */
    @SafeVarargs
    public static <T> boolean equalsAny(@Nullable T target, @Nullable T... comparisons) {
        return equalsAny(target, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Checks whether the target equals any object in the collection
     *
     * @param target The object to compare
     * @param comparisons The collection of objects to compare against
     *
     * @return {@code true} if target equals any comparison or both are null, {@code false} otherwise
     */
    public static <T> boolean equalsAny(@Nullable T target, @Nullable Collection<T> comparisons) {
        return ObjectUtils.allNull(target, comparisons) || CollectionPlainWraps.contains(comparisons, target);
    }

    /**
     * Returns the component type of the source object (array, iterable, iterator, or enumeration)
     *
     * @param source The object to check
     *
     * @return the component type, or null if not applicable
     */
    @Nullable
    public static Class<?> getComponentType(@Nullable Object source) {
        return getComponentType(source, false);
    }

    /**
     * Returns the component type of the source object with optional deep scanning
     *
     * @param source The object to check
     * @param deepScan true to scan all items for common type, false to use first non-null item
     *
     * @return the component type, or null if not applicable
     */
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

    /**
     * Returns the defaultValue if target equals comparison, otherwise returns target
     *
     * @param target The target object
     * @param comparison The object to compare against
     * @param defaultValue The value to return if target equals comparison
     *
     * @return defaultValue if target equals comparison, otherwise target
     */
    public static <T> T defaultIfEquals(@Nullable T target, @Nullable T comparison, @Nullable T defaultValue) {
        return Objects.equals(target, comparison) ? defaultValue : target;
    }

    /**
     * Executes the action if the source is empty
     *
     * @param source The object to check
     * @param action The action to execute if source is empty
     */
    public static <T> void ifEmpty(@Nullable T source, @Nullable Consumer<? super T> action) {
        ifEmptyOrElse(source, action, null);
    }

    /**
     * Executes the action if the source is empty
     *
     * @param source The object to check
     * @param action The action to execute if source is empty
     */
    public static <T> void ifEmpty(@Nullable T source, @Nullable Runnable action) {
        ifEmptyOrElse(source, action, null);
    }

    /**
     * Executes absentAction if source is empty, otherwise executes presentAction
     *
     * @param source The object to check
     * @param absentAction The action to execute if source is empty
     * @param presentAction The action to execute if source is not empty
     */
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

    /**
     * Executes absentAction if source is empty, otherwise executes presentAction
     *
     * @param source The object to check
     * @param absentAction The action to execute if source is empty
     * @param presentAction The action to execute if source is not empty
     */
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

    /**
     * Executes the action if the source is null
     *
     * @param source The object to check
     * @param action The action to execute if source is null
     */
    public static void ifNull(@Nullable Object source, @Nullable Runnable action) {
        if (source == null && action != null) {
            action.run();
        }
    }

    /**
     * Executes absentAction if source is null, otherwise executes presentAction with the source
     *
     * @param source The object to check
     * @param absentAction The action to execute if source is null
     * @param presentAction The action to execute if source is not null
     */
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

    /**
     * Executes absentAction if source is null, otherwise executes presentAction
     *
     * @param source The object to check
     * @param absentAction The action to execute if source is null
     * @param presentAction The action to execute if source is not null
     */
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

    /**
     * Executes the action if the source is not empty
     *
     * @param source The object to check
     * @param action The action to execute if source is not empty
     */
    public static <T> void ifNotEmpty(@Nullable T source, @Nullable Consumer<? super T> action) {
        ifNotEmpty(source, action, null);
    }

    /**
     * Executes the action if the source is not empty and passes the filter
     *
     * @param source The object to check
     * @param action The action to execute if source is not empty
     * @param filter The filter to apply, or null to skip filtering
     */
    public static <T> void ifNotEmpty(@Nullable T source, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (ObjectUtils.isNotEmpty(source) && action != null && (filter == null || filter.test(source))) {
            action.accept(source);
        }
    }

    /**
     * Executes the action if the source is not empty
     *
     * @param source The object to check
     * @param action The action to execute if source is not empty
     */
    public static <T> void ifNotEmpty(@Nullable T source, @Nullable Runnable action) {
        if (ObjectUtils.isNotEmpty(source) && action != null) {
            action.run();
        }
    }

    /**
     * Executes the action if the source is not null
     *
     * @param source The object to check
     * @param action The action to execute if source is not null
     */
    public static <T> void ifNotNull(@Nullable T source, @Nullable Consumer<? super T> action) {
        ifNotNull(source, action, null);
    }

    /**
     * Executes the action if the source is not null and passes the filter
     *
     * @param source The object to check
     * @param action The action to execute if source is not null
     * @param filter The filter to apply, or null to skip filtering
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T> void ifNotNull(@Nullable T source, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (ObjectUtils.allNotNull(source, action) && (filter == null || filter.test(source))) {
            action.accept(source);
        }
    }

    /**
     * Executes the action if the source is not null
     *
     * @param source The object to check
     * @param action The action to execute if source is not null
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T> void ifNotNull(@Nullable T source, @Nullable Runnable action) {
        if (ObjectUtils.allNotNull(source, action)) {
            action.run();
        }
    }

    /**
     * Returns whether the given object is an array or not
     *
     * @param object The source object to check
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
     * @param object The source object to check
     *
     * @return whether the given object is an enum or not
     */
    public static boolean isEnum(@Nullable Object object) {
        return object != null && object.getClass().isEnum();
    }

    /**
     * Returns whether the given object is a record
     *
     * @param object The source object to check
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
        return Arrays.stream(objects).skip(1L).allMatch(item -> size(item) == size);
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
     * @param object The object to get the size of, may be null
     *
     * @return the size of the specified object, or 0 if the object is {@code null}
     *
     * @throws cn.unikue.commonplexus.javaseutil.exception.UnsupportedClassException thrown if object is not recognized
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

    /**
     * Returns the maximum size among all given objects
     *
     * @param objects The objects to check
     *
     * @return the maximum size, or 0 if no objects provided
     */
    public static int maxSize(@Nullable Object... objects) {
        return maxSize(ArrayUtilsWraps.asList(objects));
    }

    /**
     * Returns the maximum size among all objects in the collection
     *
     * @param objects The collection of objects to check
     *
     * @return the maximum size, or 0 if the collection is empty
     */
    public static int maxSize(@Nullable Collection<Object> objects) {
        return CollectionPlainWraps.isEmpty(objects) ? 0 : objects.stream().mapToInt(ObjectUtilsWraps::size).max().orElse(0);
    }

    /**
     * Returns the sum of sizes of all given objects
     *
     * @param objects The objects to check
     *
     * @return the sum of all sizes, or 0 if no objects provided
     */
    public static int sumSize(@Nullable Object... objects) {
        return sumSize(ArrayUtilsWraps.asList(objects));
    }

    /**
     * Returns the sum of sizes of all objects in the collection
     *
     * @param objects The collection of objects to check
     *
     * @return the sum of all sizes, or 0 if the collection is empty
     */
    public static int sumSize(@Nullable Collection<Object> objects) {
        return CollectionPlainWraps.isEmpty(objects) ? 0 : objects.stream().mapToInt(ObjectUtilsWraps::size).sum();
    }

    /**
     * Returns the string representation of the source object, or null if source is null
     *
     * @param source The object to convert to string
     *
     * @return the string representation, or null if source is null
     */
    public static String toString(@Nullable Object source) {
        return toString(source, null);
    }

    /**
     * Returns the string representation of the given object
     *
     * @param source The given source object
     * @param nullString The default string value if {@code source} is null
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
