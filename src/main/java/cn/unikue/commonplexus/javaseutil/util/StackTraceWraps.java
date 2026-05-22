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


import java.util.Objects;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.constant.CharVariantConst;


/**
 * Utilities for {@link java.lang.StackTraceElement}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.exception.ExceptionUtils
 * @see "org.codehaus.groovy.runtime.StackTraceUtils"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class StackTraceWraps {
    /**
     * Returns the fully qualified name of the executing class
     *
     * @return the executing class name
     */
    @Nonnull
    public static String getExecutingClassName() {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return trace.getClassName();
    }

    /**
     * Returns the class name of the executing method with optional short name format
     *
     * @param shortName {@code true} to return simple class name, {@code false} for fully qualified name
     *
     * @return the executing class name
     */
    @Nonnull
    public static String getExecutingClassName(boolean shortName) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
    }

    /**
     * Returns the class name with optional line number information
     *
     * @param shortName {@code true} to return simple class name
     * @param lineNumber {@code true} to append line number
     *
     * @return the class name with optional line number (e.g., "ClassName:42")
     */
    @Nonnull
    public static String getExecutingClassName(boolean shortName, boolean lineNumber) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return lineNumber ? StringUtils.join(className, CharVariantConst.COLON, trace.getLineNumber()) : className;
    }

    /**
     * Returns the executing class name with a suffix appended
     *
     * @param suffix The suffix to append
     *
     * @return the class name with suffix
     */
    @Nonnull
    public static String getExecutingClassNameAppending(@Nullable String suffix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return StringUtils.join(trace.getClassName(), suffix);
    }

    /**
     * Returns the executing class name with optional short format and suffix
     *
     * @param shortName {@code true} to return simple class name
     * @param suffix The suffix to append
     *
     * @return the class name with suffix
     */
    @Nonnull
    public static String getExecutingClassNameAppending(boolean shortName, @Nullable String suffix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(className, suffix);
    }

    /**
     * Returns the executing class name with a prefix prepended
     *
     * @param prefix The prefix to prepend
     *
     * @return the class name with prefix
     */
    @Nonnull
    public static String getExecutingClassNamePrepending(@Nullable String prefix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return StringUtils.join(prefix, trace.getClassName());
    }

    /**
     * Returns the executing class name with optional short format and prefix
     *
     * @param shortName {@code true} to return simple class name
     * @param prefix The prefix to prepend
     *
     * @return the class name with prefix
     */
    @Nonnull
    public static String getExecutingClassNamePrepending(boolean shortName, @Nullable String prefix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(prefix, className);
    }

    /**
     * Returns the fully qualified class name and method name of the executing method
     *
     * @return the class name and method name (e.g., "com.example.ClassName.methodName")
     */
    @Nonnull
    public static String getExecutingClassMethodName() {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return StringUtils.join(trace.getClassName(), CharVariantConst.DOT, trace.getMethodName());
    }

    /**
     * Returns the class name and method name with optional short format
     *
     * @param shortName {@code true} to return simple class name
     *
     * @return the class name and method name
     */
    @Nonnull
    public static String getExecutingClassMethodName(boolean shortName) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(className, CharVariantConst.DOT, trace.getMethodName());
    }

    /**
     * Returns the class name and method name with a suffix appended
     *
     * @param suffix The suffix to append
     *
     * @return the class name, method name and suffix
     */
    @Nonnull
    public static String getExecutingClassMethodNameAppending(@Nullable String suffix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return StringUtils.join(trace.getClassName(), CharVariantConst.DOT, trace.getMethodName(), suffix);
    }

    /**
     * Returns the class name and method name with optional short format and suffix
     *
     * @param shortName {@code true} to return simple class name
     * @param suffix The suffix to append
     *
     * @return the class name, method name and suffix
     */
    @Nonnull
    public static String getExecutingClassMethodNameAppending(boolean shortName, @Nullable String suffix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(className, CharVariantConst.DOT, trace.getMethodName(), suffix);
    }

    /**
     * Returns the class name and method name with a prefix prepended
     *
     * @param prefix The prefix to prepend
     *
     * @return the prefix, class name and method name
     */
    @Nonnull
    public static String getExecutingClassMethodNamePrepending(@Nullable String prefix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return StringUtils.join(prefix, trace.getClassName(), CharVariantConst.DOT, trace.getMethodName());
    }

    /**
     * Returns the class name and method name with optional short format and prefix
     *
     * @param shortName {@code true} to return simple class name
     * @param prefix The prefix to prepend
     *
     * @return the prefix, class name and method name
     */
    @Nonnull
    public static String getExecutingClassMethodNamePrepending(boolean shortName, @Nullable String prefix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(prefix, className, CharVariantConst.DOT, trace.getMethodName());
    }

    /**
     * Returns the name of the executing method
     *
     * @return the method name
     */
    @Nonnull
    public static String getExecutingMethodName() {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return trace.getMethodName();
    }

    /**
     * Returns the file name where the executing method is located
     *
     * @return the file name, or null if not available
     */
    @Nullable
    public static String getExecutingFileName() {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return trace.getFileName();
    }

    /**
     * Returns the file name with optional line number
     *
     * @param lineNumber {@code true} to append line number
     *
     * @return the file name with optional line number (e.g., "File.java:42")
     */
    @Nullable
    public static String getExecutingFileName(boolean lineNumber) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Objects.requireNonNull(trace);
        return lineNumber ? StringUtils.join(trace.getFileName(), CharVariantConst.COLON, trace.getLineNumber()) : trace.getFileName();
    }

    /**
     * Returns a {@link java.lang.StackTraceElement} object with the specified index
     *
     * @param index The index from the invoking method, unnecessary to consider the {@link StackTraceWraps} class
     *
     * @return a {@link java.lang.StackTraceElement} object with the specified index
     *
     * @throws SecurityException if a security manager exists and its <tt>checkPermission</tt> method doesn't allow getting the stack trace of thread
     */
    @Nullable
    public static StackTraceElement getStackTraceElement(int index) {
        return (index < 0) ? null : ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
    }

    /**
     * Returns the class name and method name at the specified stack trace index
     *
     * @param index The stack trace element index (0-based from Thread.getStackTrace())
     *
     * @return the class name and method name, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings("ConstantValue")
    public static String getTracingClassMethodName(int index) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : StringUtils.join(trace.getClassName(), CharVariantConst.DOT, trace.getMethodName());
    }

    /**
     * Returns the class name and method name at the specified index with optional short format
     *
     * @param index The stack trace element index
     * @param shortName {@code true} to return simple class name
     *
     * @return the class name and method name, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings({"ConstantValue", "DuplicatedCode"})
    public static String getTracingClassMethodName(int index, boolean shortName) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        if (trace == null) {
            return null;
        }
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(className, CharVariantConst.DOT, trace.getMethodName());
    }

    /**
     * Returns the class name and method name at the specified index with suffix
     *
     * @param index The stack trace element index
     * @param suffix The suffix to append
     *
     * @return the class name, method name and suffix, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings("ConstantValue")
    public static String getTracingClassMethodNameAppending(int index, @Nullable String suffix) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : StringUtils.join(trace.getClassName(), CharVariantConst.DOT, trace.getMethodName(), suffix);
    }

    /**
     * Returns the class name and method name at the specified index with short format and suffix
     *
     * @param index The stack trace element index
     * @param shortName {@code true} to return simple class name
     * @param suffix The suffix to append
     *
     * @return the class name, method name and suffix, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings({"ConstantValue", "DuplicatedCode"})
    public static String getTracingClassMethodNameAppending(int index, boolean shortName, @Nullable String suffix) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        if (trace == null) {
            return null;
        }
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(className, CharVariantConst.DOT, trace.getMethodName(), suffix);
    }

    /**
     * Returns the class name and method name at the specified index with prefix
     *
     * @param index The stack trace element index
     * @param prefix The prefix to prepend
     *
     * @return the prefix, class name and method name, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings("ConstantValue")
    public static String getTracingClassMethodNamePrepending(int index, @Nullable String prefix) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : StringUtils.join(prefix, trace.getClassName(), CharVariantConst.DOT, trace.getMethodName());
    }

    /**
     * Returns the class name and method name at the specified index with short format and prefix
     *
     * @param index The stack trace element index
     * @param shortName {@code true} to return simple class name
     * @param prefix The prefix to prepend
     *
     * @return the prefix, class name and method name, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings({"ConstantValue", "DuplicatedCode"})
    public static String getTracingClassMethodNamePrepending(int index, boolean shortName, @Nullable String prefix) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        if (trace == null) {
            return null;
        }
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(prefix, className, CharVariantConst.DOT, trace.getMethodName());
    }

    /**
     * Returns the class name at the specified stack trace index
     *
     * @param index The stack trace element index
     *
     * @return the class name, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings("ConstantValue")
    public static String getTracingClassName(int index) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : trace.getClassName();
    }

    /**
     * Returns the class name at the specified index with optional short format
     *
     * @param index The stack trace element index
     * @param shortName {@code true} to return simple class name
     *
     * @return the class name, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings("ConstantValue")
    public static String getTracingClassName(int index, boolean shortName) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        if (trace == null) {
            return null;
        }
        return shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
    }

    /**
     * Returns the class name at the specified index with optional line number
     *
     * @param index The stack trace element index
     * @param shortName {@code true} to return simple class name
     * @param lineNumber {@code true} to append line number
     *
     * @return the class name with optional line number, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings({"ConstantValue", "DuplicatedCode"})
    public static String getTracingClassName(int index, boolean shortName, boolean lineNumber) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        if (trace == null) {
            return null;
        }
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return lineNumber ? StringUtils.join(className, CharVariantConst.COLON, trace.getLineNumber()) : className;
    }

    /**
     * Returns the method name at the specified stack trace index
     *
     * @param index The stack trace element index
     *
     * @return the method name, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings("ConstantValue")
    public static String getTracingMethodName(int index) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : trace.getMethodName();
    }

    /**
     * Returns the file name at the specified stack trace index
     *
     * @param index The stack trace element index
     *
     * @return the file name, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings("ConstantValue")
    public static String getTracingFileName(int index) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : trace.getFileName();
    }

    /**
     * Returns the file name at the specified index with optional line number
     *
     * @param index The stack trace element index
     * @param lineNumber {@code true} to append line number
     *
     * @return the file name with optional line number, or null if index is invalid
     */
    @Nullable
    @SuppressWarnings("ConstantValue")
    public static String getTracingFileName(int index, boolean lineNumber) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        if (trace == null) {
            return null;
        }
        return lineNumber ? StringUtils.join(trace.getFileName(), CharVariantConst.COLON, trace.getLineNumber()) : trace.getFileName();
    }
}
