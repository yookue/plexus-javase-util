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
 * @reference "http://tutorials.jenkov.com/java-reflection/generics.html"
 * @reference "https://xebia.com/blog/acessing-generic-types-at-runtime-in-java/"
 * @reference "https://blog.csdn.net/changsa65/article/details/78790881"
 * @see org.apache.commons.lang3.reflect.TypeUtils
 * @see "sun.reflect.generics.reflectiveObjects.TypeVariableImpl"
 * @see "org.springframework.util.TypeUtils"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public abstract class TypeUtilsWraps {
    @Nullable
    public static Type getGenericParameterType(@Nullable Class<?> clazz) {
        return getGenericParameterType(clazz, 0);
    }

    @Nullable
    public static Type getGenericParameterType(@Nullable Class<?> clazz, int index) {
        if (clazz == null || index < 0 || !(clazz.getGenericSuperclass() instanceof ParameterizedType alias)) {
            return null;
        }
        return ArrayUtils.get(alias.getActualTypeArguments(), index);
    }

    @Nullable
    public static Type[] getGenericParameterTypes(@Nullable Class<?> clazz) {
        if (clazz == null || !(clazz.getGenericSuperclass() instanceof ParameterizedType alias)) {
            return null;
        }
        return alias.getActualTypeArguments();
    }

    @Nullable
    public static Class<?> getGenericParameterClass(@Nullable Class<?> clazz) {
        return getGenericParameterClass(clazz, 0);
    }

    @Nullable
    public static Class<?> getGenericParameterClass(@Nullable Class<?> clazz, int index) {
        Type result = getGenericParameterType(clazz, index);
        return (result instanceof Class<?> alias) ? alias : null;
    }

    @Nullable
    public static <T> T getGenericParameterClassAs(@Nullable Class<?> clazz, @Nullable Class<T> expectedType) {
        return getGenericParameterClassAs(clazz, 0, expectedType);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> T getGenericParameterClassAs(@Nullable Class<?> clazz, int index, @Nullable Class<T> expectedType) {
        if (ObjectUtils.anyNull(clazz, expectedType) || index < 0) {
            return null;
        }
        Class<?> result = getGenericParameterClass(clazz, index);
        return ClassUtils.isAssignable(result, expectedType) ? (T) result : null;
    }

    public boolean isGenericParameterizedWrapper(@Nullable Class<?> wrapper, @Nullable Type... parameters) {
        if (wrapper == null || ArrayUtils.isEmpty(parameters)) {
            return false;
        }
        Type[] actualTypes = getGenericParameterTypes(wrapper);
        return ArrayUtils.isNotEmpty(actualTypes) && Arrays.equals(actualTypes, parameters);
    }

    public static boolean isPrimitive(@Nullable Type type) {
        return (type instanceof Class<?> alias) && alias.isPrimitive();
    }

    public static boolean isNotPrimitive(@Nullable Type type) {
        return !isPrimitive(type);
    }

    public static boolean isPrimitiveArray(@Nullable Type type) {
        return (type instanceof Class<?> alias) && alias.isArray() && alias.getComponentType().isPrimitive();
    }
}
