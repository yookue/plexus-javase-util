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


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.reflect.TypeUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.reflect.TypeUtils
 * @see "sun.reflect.generics.reflectiveObjects.TypeVariableImpl"
 * @see "org.springframework.util.TypeUtils"
 *
 * @reference "http://tutorials.jenkov.com/java-reflection/generics.html"
 * @reference "https://xebia.com/blog/acessing-generic-types-at-runtime-in-java/"
 * @reference "https://blog.csdn.net/changsa65/article/details/78790881"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public abstract class TypeUtilsWraps {
    /**
     * Returns the first generic type parameter of the superclass
     *
     * @param clazz The class to inspect, may be null
     *
     * @return the first generic type parameter, or null if not available
     */
    @Nullable
    public static Type getGenericParameterType(@Nullable Class<?> clazz) {
        return getGenericParameterType(clazz, 0);
    }

    /**
     * Returns the generic type parameter at the specified index of the superclass
     *
     * @param clazz The class to inspect, may be null
     * @param index The index of the type parameter (0-based)
     *
     * @return the generic type parameter at the specified index, or null if not available
     */
    @Nullable
    public static Type getGenericParameterType(@Nullable Class<?> clazz, int index) {
        if (clazz == null || index < 0 || !(clazz.getGenericSuperclass() instanceof ParameterizedType alias)) {
            return null;
        }
        return ArrayUtils.get(alias.getActualTypeArguments(), index);
    }

    /**
     * Returns all generic type parameters of the superclass
     *
     * @param clazz The class to inspect, may be null
     *
     * @return an array of generic type parameters, or null if not available
     */
    @Nullable
    public static Type[] getGenericParameterTypes(@Nullable Class<?> clazz) {
        if (clazz == null || !(clazz.getGenericSuperclass() instanceof ParameterizedType alias)) {
            return null;
        }
        return alias.getActualTypeArguments();
    }

    /**
     * Returns the first generic type parameter as a Class object
     *
     * @param clazz The class to inspect, may be null
     *
     * @return the first generic type parameter as Class, or null if not available or not a Class
     */
    @Nullable
    public static Class<?> getGenericParameterClass(@Nullable Class<?> clazz) {
        return getGenericParameterClass(clazz, 0);
    }

    /**
     * Returns the generic type parameter at the specified index as a Class object
     *
     * @param clazz The class to inspect, may be null
     * @param index The index of the type parameter (0-based)
     *
     * @return the generic type parameter as Class, or null if not available or not a Class
     */
    @Nullable
    public static Class<?> getGenericParameterClass(@Nullable Class<?> clazz, int index) {
        Type result = getGenericParameterType(clazz, index);
        return (result instanceof Class<?> alias) ? alias : null;
    }

    /**
     * Returns the first generic type parameter as a Class object cast to the expected type
     *
     * @param clazz The class to inspect, may be null
     * @param expectType The expected type to cast to, may be null
     * @param <T> The generic type parameter
     *
     * @return the generic type parameter cast to the expected type, or null if not compatible
     */
    @Nullable
    public static <T> T getGenericParameterClassAs(@Nullable Class<?> clazz, @Nullable Class<T> expectType) {
        return getGenericParameterClassAs(clazz, 0, expectType);
    }

    /**
     * Returns the generic type parameter at the specified index as a Class object cast to the expected type
     *
     * @param clazz The class to inspect, may be null
     * @param index The index of the type parameter (0-based)
     * @param expectType The expected type to cast to, may be null
     * @param <T> The generic type parameter
     *
     * @return the generic type parameter cast to the expected type, or null if not compatible
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> T getGenericParameterClassAs(@Nullable Class<?> clazz, int index, @Nullable Class<T> expectType) {
        if (ObjectUtils.anyNull(clazz, expectType) || index < 0) {
            return null;
        }
        Class<?> result = getGenericParameterClass(clazz, index);
        return ClassUtils.isAssignable(result, expectType) ? (T) result : null;
    }

    /**
     * Checks whether the wrapper class has the specified generic type parameters
     *
     * @param wrapper The wrapper class to check, may be null
     * @param parameters The expected type parameters
     *
     * @return {@code true} if the wrapper has matching generic type parameters, {@code false} otherwise
     */
    public boolean isGenericParameterizedWrapper(@Nullable Class<?> wrapper, @Nullable Type... parameters) {
        if (wrapper == null || ArrayUtils.isEmpty(parameters)) {
            return false;
        }
        Type[] actualTypes = getGenericParameterTypes(wrapper);
        return ArrayUtils.isNotEmpty(actualTypes) && Arrays.equals(actualTypes, parameters);
    }

    /**
     * Checks whether the given type is a primitive type
     *
     * @param type The type to check, may be null
     *
     * @return {@code true} if the type is primitive, {@code false} otherwise
     */
    public static boolean isPrimitive(@Nullable Type type) {
        return (type instanceof Class<?> alias) && alias.isPrimitive();
    }

    /**
     * Checks whether the given type is not a primitive type
     *
     * @param type The type to check, may be null
     *
     * @return {@code true} if the type is not primitive, {@code false} otherwise
     */
    public static boolean isNotPrimitive(@Nullable Type type) {
        return !isPrimitive(type);
    }

    /**
     * Checks whether the given type is a primitive array
     *
     * @param type The type to check, may be null
     *
     * @return {@code true} if the type is a primitive array, {@code false} otherwise
     */
    public static boolean isPrimitiveArray(@Nullable Type type) {
        return (type instanceof Class<?> alias) && alias.isArray() && alias.getComponentType().isPrimitive();
    }
}
