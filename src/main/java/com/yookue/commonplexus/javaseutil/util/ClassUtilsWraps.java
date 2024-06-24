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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;


/**
 * Utilities for {@link org.apache.commons.lang3.ClassUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.ClassUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ClassUtilsWraps {
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allInstance(@Nullable Class<?> superclass, @Nullable Object... objects) {
        return superclass != null && ArrayUtils.isNotEmpty(objects) && Arrays.stream(objects).allMatch(superclass::isInstance);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allInstanceOf(@Nullable Object object, @Nullable Class<?>... superclasses) {
        return object != null && ArrayUtils.isNotEmpty(superclasses) && Arrays.stream(superclasses).allMatch(element -> element != null && element.isInstance(object));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyInstance(@Nullable Class<?> superclass, @Nullable Object... objects) {
        return superclass != null && ArrayUtils.isNotEmpty(objects) && Arrays.stream(objects).filter(Objects::nonNull).anyMatch(superclass::isInstance);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyInstanceOf(@Nullable Object object, @Nullable Class<?>... superclasses) {
        return object != null && ArrayUtils.isNotEmpty(superclasses) && Arrays.stream(superclasses).filter(Objects::nonNull).anyMatch(element -> element.isInstance(object));
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#equals
     */
    public static boolean equalsClass(@Nullable Object one, @Nullable Object two) {
        if (one == two) {
            return true;
        }
        if (one == null || two == null) {
            return false;
        }
        return one.getClass() == two.getClass();
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsAnyClasses(@Nullable Class<?> target, @Nullable Class<?>... comparisons) {
        return target != null && ArrayUtils.isNotEmpty(comparisons) && Arrays.stream(comparisons).filter(Objects::nonNull).anyMatch(comparison -> comparison == target);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsAnyClasses(@Nullable Object target, @Nullable Object... comparisons) {
        return target != null && ArrayUtils.isNotEmpty(comparisons) && Arrays.stream(comparisons).filter(Objects::nonNull).anyMatch(comparison -> comparison.getClass() == target.getClass());
    }

    @Nullable
    public static Class<?> forName(@Nullable String className) throws ClassNotFoundException {
        return forName(className, null);
    }

    @Nullable
    public static Class<?> forName(@Nullable String className, @Nullable ClassLoader classLoader) throws ClassNotFoundException {
        return StringUtils.isBlank(className) ? null : ClassUtils.getClass(classLoader, className);
    }

    @Nullable
    public static <T> Class<? extends T> forNameAs(@Nullable String className, @Nullable Class<T> expectedType) throws ClassNotFoundException {
        return forNameAs(className, null, expectedType);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> Class<T> forNameAs(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Class<T> expectedType) throws ClassNotFoundException {
        if (StringUtils.isBlank(className) || expectedType == null) {
            return null;
        }
        Class<?> result = ClassUtils.getClass(classLoader, className);
        return ClassUtils.isAssignable(result, expectedType) ? (Class<T>) result : null;
    }

    @Nullable
    public static Class<?> forNameQuietly(@Nullable String className) {
        return forNameQuietly(className, null);
    }

    @Nullable
    public static Class<?> forNameQuietly(@Nullable String className, @Nullable ClassLoader classLoader) {
        try {
            return forName(className, classLoader);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static <T> Class<? extends T> forNameAsQuietly(@Nullable String className, @Nullable Class<T> expectedType) {
        return forNameAsQuietly(className, null, expectedType);
    }

    @Nullable
    public static <T> Class<T> forNameAsQuietly(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Class<T> expectedType) {
        try {
            return forNameAs(className, classLoader, expectedType);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Class<?> getObjectClass(@Nullable Object object) {
        return (object == null) ? null : object.getClass();
    }

    /**
     * @see org.apache.commons.lang3.ClassUtils#toClass
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Class<?>[] getObjectClasses(@Nullable Object... objects) {
        if (ArrayUtils.isEmpty(objects)) {
            return null;
        }
        int length = ArrayUtils.getLength(objects);
        Class<?>[] result = new Class<?>[length];
        for (int i = 0; i < length; i++) {
            result[i] = getObjectClass(objects[i]);
        }
        return result;
    }

    @Nullable
    public static String getPackageCanonicalPath(@Nullable Class<?> clazz) {
        return getPackageCanonicalPath(clazz, 0);
    }

    /**
     * Returns the canonical path of the package
     *
     * @param clazz the class to get the path for, may be {@code null}
     * @param subtracts the group count (separated by dot) to be subtracted from right side
     *
     * @return the canonical path of the package
     */
    @Nullable
    public static String getPackageCanonicalPath(@Nullable Class<?> clazz, int subtracts) {
        if (clazz == null) {
            return null;
        }
        String name = ClassUtils.getPackageCanonicalName(clazz);
        if (StringUtils.isNotBlank(name)) {
            if (subtracts <= 0) {
                return RegExUtils.replaceAll(name, "\\.", "/");    // $NON-NLS-1$ // $NON-NLS-2$
            } else {
                String[] packages = StringUtils.split(name, CharVariantConst.DOT);
                String[] subtraction = ArrayUtils.subarray(packages, 0, ArrayUtils.getLength(packages) - subtracts);
                return ArrayUtils.isEmpty(subtraction) ? null : StringUtils.join(subtraction, CharVariantConst.SLASH);
            }
        }
        return null;
    }

    @Nullable
    public static String getPackageCanonicalPath(@Nullable Object source) {
        return getPackageCanonicalPath(source, 0);
    }

    /**
     * Returns the canonical path of the package
     *
     * @param source the object to get the path for, may be {@code null}
     * @param subtracts the group count (separated by dot) to be subtracted from right side
     *
     * @return the canonical path of the package
     */
    @Nullable
    public static String getPackageCanonicalPath(@Nullable Object source, int subtracts) {
        return (source == null) ? null : getPackageCanonicalPath(source.getClass(), subtracts);
    }

    @Nullable
    public static String getShortCanonicalName(@Nullable Object source) {
        return ClassUtils.getShortCanonicalName(source, null);
    }

    /**
     * @see org.apache.commons.lang3.ClassUtils#getAllSuperclasses
     */
    @Nullable
    public static List<Class<?>> getSuperclassesUntil(@Nullable Class<?> clazz, @Nullable Class<?> superclass) {
        if (clazz == null) {
            return null;
        }
        List<Class<?>> result = new ArrayList<>();
        Class<?> parent = clazz.getSuperclass();
        while (parent != null && !Objects.equals(parent, superclass)) {
            result.add(parent);
            parent = parent.getSuperclass();
        }
        return result.isEmpty() ? null : result;
    }

    @Nullable
    public static List<Class<?>> getSuperclassesUntilObject(@Nullable Class<?> clazz) {
        return getSuperclassesUntil(clazz, Object.class);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean hasAnnotation(@Nullable Class<?> clazz, @Nullable Class<? extends Annotation> annotation) {
        return ObjectUtils.allNotNull(clazz, annotation) && clazz.getAnnotation(annotation) != null;
    }

    public static void ifPresent(@Nullable String className, @Nullable Consumer<String> action) {
        ifPresent(className, null, action);
    }

    public static void ifPresent(@Nullable String className, @Nullable Runnable action) {
        ifPresent(className, null, action);
    }

    public static void ifPresent(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Consumer<String> action) {
        ifPresentOrElse(className, classLoader, action, null);
    }

    public static void ifPresent(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Runnable action) {
        ifPresentOrElse(className, classLoader, action, null);
    }

    public static void ifPresentOrElse(@Nullable String className, @Nullable Consumer<String> presentAction, @Nullable Consumer<String> absentAction) {
        ifPresentOrElse(className, null, presentAction, absentAction);
    }

    public static void ifPresentOrElse(@Nullable String className, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        ifPresentOrElse(className, null, presentAction, absentAction);
    }

    public static void ifPresentOrElse(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Consumer<String> presentAction, @Nullable Consumer<String> absentAction) {
        if (isPresent(className, classLoader)) {
            if (presentAction != null) {
                presentAction.accept(className);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(className);
            }
        }
    }

    public static void ifPresentOrElse(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isPresent(className, classLoader)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static void ifNotPresent(@Nullable String className, @Nullable Consumer<String> action) {
        ifNotPresent(className, null, action);
    }

    public static void ifNotPresent(@Nullable String className, @Nullable Runnable action) {
        ifNotPresent(className, null, action);
    }

    public static void ifNotPresent(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Consumer<String> action) {
        if (isNotPresent(className, classLoader) && action != null) {
            action.accept(className);
        }
    }

    public static void ifNotPresent(@Nullable String className, @Nullable ClassLoader classLoader, @Nullable Runnable action) {
        if (isNotPresent(className, classLoader) && action != null) {
            action.run();
        }
    }

    public static boolean isAssignable(@Nullable Class<?> superclass, @Nullable Class<?> subclass) {
        return ClassUtils.isAssignable(subclass, superclass);
    }

    public static boolean isAssignable(@Nullable String superclassName, @Nullable String subclassName) {
        return isAssignable(superclassName, subclassName, null);
    }

    public static boolean isAssignable(@Nullable String superclassName, @Nullable String subclassName, @Nullable ClassLoader classLoader) {
        return StringUtils.isNoneBlank(superclassName, subclassName) && isAssignable(forNameQuietly(superclassName, classLoader), forNameQuietly(subclassName, classLoader));
    }

    public static boolean isAssignableValue(@Nullable Class<?> superclass, @Nullable Object value) {
        return isAssignableValue(superclass, value, true);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isAssignableValue(@Nullable Class<?> superclass, @Nullable Object value, boolean autoboxing) {
        return superclass == null || ClassUtils.isAssignable(value.getClass(), superclass, autoboxing);
    }

    public static boolean isAssignableValue(@Nullable String superclassName, @Nullable Object value) {
        return isAssignableValue(superclassName, value, true, null);
    }

    public static boolean isAssignableValue(@Nullable String superclassName, @Nullable Object value, boolean autoboxing) {
        return isAssignableValue(superclassName, value, autoboxing, null);
    }

    public static boolean isAssignableValue(@Nullable String superclassName, @Nullable Object value, boolean autoboxing, @Nullable ClassLoader classLoader) {
        return StringUtils.isNotBlank(superclassName) && ClassUtils.isAssignable(getObjectClass(value), forNameQuietly(superclassName, classLoader), autoboxing);
    }

    /**
     * @see org.apache.commons.lang3.ClassUtils#isAssignable(java.lang.Class[], java.lang.Class[], boolean)
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isAssignableValue(@Nullable Class<?>[] superclasses, @Nullable Object[] subObjects) {
        if (ArrayUtils.isEmpty(superclasses) && ArrayUtils.isEmpty(subObjects)) {
            return true;
        }
        if (!ArrayUtils.isSameLength(superclasses, subObjects)) {
            return false;
        }
        for (int i = 0; i < superclasses.length; i++) {
            if (!isAssignableValue(superclasses[i], subObjects[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPresent(@Nullable String className) {
        return isPresent(className, null);
    }

    public static boolean isPresent(@Nullable String className, @Nullable ClassLoader classLoader) {
        if (StringUtils.isBlank(className)) {
            return false;
        }
        try {
            ClassUtils.getClass(classLoader, className);
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public static boolean isNotPresent(@Nullable String className) {
        return !isPresent(className);
    }

    public static boolean isNotPresent(@Nullable String className, @Nullable ClassLoader classLoader) {
        return !isPresent(className, classLoader);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isSamePackage(@Nullable Class<?> clazz1, @Nullable Class<?> clazz2) {
        if (ObjectUtils.anyNull(clazz1, clazz2) || clazz1.getClassLoader() != clazz2.getClassLoader()) {
            return false;
        }
        return StringUtils.equals(ClassUtils.getPackageName(clazz1), ClassUtils.getPackageName(clazz2));
    }
}
