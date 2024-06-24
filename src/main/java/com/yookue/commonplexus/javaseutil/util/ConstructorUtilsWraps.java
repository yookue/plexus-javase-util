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


import java.lang.reflect.Constructor;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.reflect.ConstructorUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.reflect.ConstructorUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ConstructorUtilsWraps {
    /**
     * Returns a new instance of the given class
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types by assignment compatibility
     *
     * @param <T> the required type to be constructed
     * @param clazz the class to be constructed, not {@code null}
     *
     * @return a new instance of the given class
     */
    public static <T> T invokeConstructor(@Nullable Class<T> clazz) {
        return invokeConstructor(clazz, null, null);
    }

    /**
     * Returns a new instance of the given class with the arguments
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types by assignment compatibility
     *
     * @param <T> the required type to be constructed
     * @param clazz the class to be constructed, not {@code null}
     * @param args the array of arguments, may be {@code null}
     *
     * @return a new instance of the given class with the arguments
     */
    public static <T> T invokeConstructor(@Nullable Class<T> clazz, @Nullable Object... args) {
        return invokeConstructor(clazz, args, ClassUtils.toClass(args));
    }

    /**
     * Returns a new instance of the given class with the arguments of the specified parameter types
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types by assignment compatibility
     *
     * @param <T> the required type to be constructed
     * @param clazz the class to be constructed, not {@code null}
     * @param args the array of arguments, may be {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return a new instance of the given class with the arguments of the specified parameter types
     */
    public static <T> T invokeConstructor(@Nullable Class<T> clazz, @Nullable Object[] args, @Nullable Class<?>[] paramTypes) {
        if (clazz == null) {
            return null;
        }
        try {
            return ConstructorUtils.invokeConstructor(clazz, args, paramTypes);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns a new instance of the given class name
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types by assignment compatibility
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     *
     * @return a new instance of the given class name
     */
    public static <T> T invokeConstructor(@Nullable String className, @Nullable Class<T> expectedType) {
        return invokeConstructor(className, null, expectedType, null, null);
    }

    /**
     * Returns a new instance of the given class name with the arguments
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types by assignment compatibility
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     * @param args the array of arguments, may be {@code null}
     *
     * @return a new instance of the given class name with the arguments
     */
    public static <T> T invokeConstructor(@Nullable String className, @Nullable Class<T> expectedType, @Nullable Object... args) {
        return invokeConstructor(className, null, expectedType, args);
    }

    /**
     * Returns a new instance of the given class name with the arguments
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types by assignment compatibility
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param classLoader the class loader to be used, may be {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     * @param args the array of arguments, may be {@code null}
     *
     * @return a new instance of the given class name with the arguments
     */
    public static <T> T invokeConstructor(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Class<T> expectedType, @Nullable Object... args) {
        return invokeConstructor(className, classLoader, expectedType, args, ClassUtils.toClass(args));
    }

    /**
     * Returns a new instance of the given class with the arguments of the specified parameter types
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types by assignment compatibility
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param args the array of arguments, may be {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return a new instance of the given class with the arguments of the specified parameter types
     */
    public static <T> T invokeConstructor(@Nullable String className, @Nullable Class<T> expectedType, @Nullable Object[] args, @Nullable Class<?>[] paramTypes) {
        return invokeConstructor(className, null, expectedType, args, paramTypes);
    }

    /**
     * Returns a new instance of the given class with the arguments of the specified parameter types
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types by assignment compatibility
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param classLoader the class loader to be used, may be {@code null}
     * @param args the array of arguments, may be {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return a new instance of the given class with the arguments of the specified parameter types
     */
    @Nullable
    public static <T> T invokeConstructor(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Class<T> expectedType, @Nullable Object[] args, @Nullable Class<?>[] paramTypes) {
        if (StringUtils.isBlank(className) || expectedType == null) {
            return null;
        }
        Class<?> clazz = ClassUtilsWraps.forNameQuietly(className, classLoader);
        if (clazz == null) {
            return null;
        }
        try {
            Object instance = ConstructorUtils.invokeConstructor(clazz, args, paramTypes);
            return ObjectUtilsWraps.castAs(instance, expectedType);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns a new instance of the given class with the arguments
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types exactly
     *
     * @param <T> the required type to be constructed
     * @param clazz the class to be constructed, not {@code null}
     * @param args the array of arguments, may be {@code null}
     *
     * @return a new instance of the given class with the arguments
     */
    public static <T> T invokeExactConstructor(@Nullable Class<T> clazz, @Nullable Object... args) {
        return invokeExactConstructor(clazz, args, ClassUtils.toClass(args));
    }

    /**
     * Returns a new instance of the given class
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types exactly
     *
     * @param <T> the required type to be constructed
     * @param clazz the class to be constructed, not {@code null}
     *
     * @return a new instance of the given class
     */
    public static <T> T invokeExactConstructor(@Nullable Class<T> clazz) {
        return invokeExactConstructor(clazz, null, null);
    }

    /**
     * Returns a new instance of the given class with the arguments of the specified parameter types
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types exactly
     *
     * @param <T> the required type to be constructed
     * @param clazz the class to be constructed, not {@code null}
     * @param args the array of arguments, may be {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return a new instance of the given class with the arguments of the specified parameter types
     */
    public static <T> T invokeExactConstructor(@Nullable Class<T> clazz, @Nullable Object[] args, @Nullable Class<?>[] paramTypes) {
        if (clazz == null) {
            return null;
        }
        try {
            return ConstructorUtils.invokeExactConstructor(clazz, args, paramTypes);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns a new instance of the given class name
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types exactly
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     *
     * @return a new instance of the given class name
     */
    public static <T> T invokeExactConstructor(@Nullable String className, @Nullable Class<T> expectedType) {
        return invokeExactConstructor(className, null, expectedType, null, null);
    }

    /**
     * Returns a new instance of the given class name with the arguments
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types exactly
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     * @param args the array of arguments, may be {@code null}
     *
     * @return a new instance of the given class name with the arguments
     */
    public static <T> T invokeExactConstructor(@Nullable String className, @Nullable Class<T> expectedType, @Nullable Object... args) {
        return invokeExactConstructor(className, null, expectedType, args);
    }

    /**
     * Returns a new instance of the given class name with the arguments
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types exactly
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param classLoader the class loader to be used, may be {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     * @param args the array of arguments, may be {@code null}
     *
     * @return a new instance of the given class name with the arguments
     */
    public static <T> T invokeExactConstructor(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Class<T> expectedType, @Nullable Object... args) {
        return invokeExactConstructor(className, classLoader, expectedType, args, ClassUtils.toClass(args));
    }

    /**
     * Returns a new instance of the given class with the arguments of the specified parameter types
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types exactly
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param args the array of arguments, may be {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return a new instance of the given class with the arguments of the specified parameter types
     */
    public static <T> T invokeExactConstructor(@Nullable String className, @Nullable Class<T> expectedType, @Nullable Object[] args, @Nullable Class<?>[] paramTypes) {
        return invokeExactConstructor(className, null, expectedType, args, paramTypes);
    }

    /**
     * Returns a new instance of the given class with the arguments of the specified parameter types
     *
     * <p>
     * This locates and calls a constructor
     * <p>
     * The constructor signature must match the argument types exactly
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param classLoader the class loader to be used, may be {@code null}
     * @param args the array of arguments, may be {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return a new instance of the given class with the arguments of the specified parameter types
     */
    @Nullable
    public static <T> T invokeExactConstructor(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Class<T> expectedType, @Nullable Object[] args, @Nullable Class<?>[] paramTypes) {
        if (StringUtils.isBlank(className) || expectedType == null) {
            return null;
        }
        Class<?> clazz = ClassUtilsWraps.forNameQuietly(className, classLoader);
        if (clazz == null) {
            return null;
        }
        try {
            Object instance = ConstructorUtils.invokeExactConstructor(clazz, args, paramTypes);
            return ObjectUtilsWraps.castAs(instance, expectedType);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns the constructor of the given class with the specified parameter types, checking accessibility
     *
     * <p>
     * This finds the constructor and ensures that it is accessible
     * <p>
     * The constructor signature must match the parameter types exactly
     *
     * @param <T> the required type to be constructed
     * @param clazz the class to find a constructor for, not {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return the constructor of the given class with the specified parameter types, checking accessibility
     */
    public static <T> Constructor<T> getAccessibleConstructor(@Nullable Class<T> clazz, @Nullable Class<?>... paramTypes) {
        if (clazz == null) {
            return null;
        }
        try {
            return ConstructorUtils.getAccessibleConstructor(clazz, paramTypes);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns the constructor of the given class with the specified parameter types, checking accessibility
     *
     * <p>
     * This finds the constructor and ensures that it is accessible
     * <p>
     * The constructor signature must match the parameter types exactly
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return the constructor of the given class with the specified parameter types, checking accessibility
     */
    public static <T> Constructor<T> getAccessibleConstructor(@Nullable String className, @Nullable Class<T> expectedType, @Nullable Class<?>... paramTypes) {
        return getAccessibleConstructor(className, null, expectedType, paramTypes);
    }

    /**
     * Returns the constructor of the given class with the specified parameter types, checking accessibility
     *
     * <p>
     * This finds the constructor and ensures that it is accessible
     * <p>
     * The constructor signature must match the parameter types exactly
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param classLoader the class loader to be used, may be {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return the constructor of the given class with the specified parameter types, checking accessibility
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> Constructor<T> getAccessibleConstructor(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Class<T> expectedType, @Nullable Class<?>... paramTypes) {
        if (StringUtils.isBlank(className) || expectedType == null) {
            return null;
        }
        Class<?> clazz = ClassUtilsWraps.forNameQuietly(className, classLoader);
        if (clazz == null) {
            return null;
        }
        try {
            Constructor<?> result = ConstructorUtils.getAccessibleConstructor(clazz, paramTypes);
            if (result != null && ClassUtils.isAssignable(result.getDeclaringClass(), expectedType)) {
                return (Constructor<T>) result;
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns the constructor of the given class with compatible parameter types, checking accessibility
     *
     * <p>
     * This finds the constructor and ensures that it is accessible
     * <p>
     * This checks all the constructor and finds one with compatible parameters<br/>
     * This requires that every parameter is assignable from the given parameter types<br/>
     * This is a more flexible search than the normal exact matching algorithm
     *
     * @param <T> the required type to be constructed
     * @param clazz the class to find a constructor for, not {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return the constructor of the given class with compatible parameter types, checking accessibility
     */
    public static <T> Constructor<T> getMatchingAccessibleConstructor(@Nullable Class<T> clazz, @Nullable Class<?>... paramTypes) {
        if (clazz == null) {
            return null;
        }
        try {
            return ConstructorUtils.getMatchingAccessibleConstructor(clazz, paramTypes);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns the constructor of the given class with compatible parameter types, checking accessibility
     *
     * <p>
     * This finds the constructor and ensures that it is accessible
     * <p>
     * This checks all the constructor and finds one with compatible parameters<br/>
     * This requires that every parameter is assignable from the given parameter types<br/>
     * This is a more flexible search than the normal exact matching algorithm
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return the constructor of the given class with compatible parameter types, checking accessibility
     */
    public static <T> Constructor<T> getMatchingAccessibleConstructor(@Nullable String className, @Nullable Class<T> expectedType, @Nullable Class<?>... paramTypes) {
        return getMatchingAccessibleConstructor(className, null, expectedType, paramTypes);
    }

    /**
     * Returns the constructor of the given class with compatible parameter types, checking accessibility
     *
     * <p>
     * This finds the constructor and ensures that it is accessible
     * <p>
     * This checks all the constructor and finds one with compatible parameters<br/>
     * This requires that every parameter is assignable from the given parameter types<br/>
     * This is a more flexible search than the normal exact matching algorithm
     *
     * @param <T> the required type to be constructed
     * @param className the class name to be constructed, not {@code null}
     * @param classLoader the class loader to be used, may be {@code null}
     * @param expectedType the expected type to be converted, not {@code null}
     * @param paramTypes the array of parameter types, may be {@code null}
     *
     * @return the constructor of the given class with compatible parameter types, checking accessibility
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> Constructor<T> getMatchingAccessibleConstructor(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Class<T> expectedType, @Nullable Class<?>... paramTypes) {
        if (StringUtils.isBlank(className) || expectedType == null) {
            return null;
        }
        Class<?> clazz = ClassUtilsWraps.forNameQuietly(className, classLoader);
        if (clazz == null) {
            return null;
        }
        try {
            Constructor<?> result = ConstructorUtils.getMatchingAccessibleConstructor(clazz, paramTypes);
            if (result != null && ClassUtils.isAssignable(result.getDeclaringClass(), expectedType)) {
                return (Constructor<T>) result;
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public static <T> T newInstance(@Nullable Constructor<T> constructor) {
        return newInstance(constructor, ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

    /**
     * Returns a new instance of the constructor's declaring class
     *
     * @param constructor the given constructor method
     * @param args array of objects to be passed as arguments to the constructor call
     *
     * @return a new instance of the constructor's declaring class
     */
    public static <T> T newInstance(@Nullable Constructor<T> constructor, @Nullable Object... args) {
        if (constructor == null) {
            return null;
        }
        try {
            return constructor.newInstance(args != null ? args : ArrayUtils.EMPTY_OBJECT_ARRAY);
        } catch (Exception ignored) {
        }
        return null;
    }
}
