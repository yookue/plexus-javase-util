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


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;


/**
 * Utilities for {@link java.lang.StackTraceElement}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.exception.ExceptionUtils
 * @see "org.codehaus.groovy.runtime.StackTraceUtils"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class StackTraceWraps {
    @Nonnull
    public static String getExecutingClassName() {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return trace.getClassName();
    }

    @Nonnull
    public static String getExecutingClassName(boolean shortName) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
    }

    @Nonnull
    public static String getExecutingClassName(boolean shortName, boolean lineNumber) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return lineNumber ? StringUtils.join(className, CharVariantConst.COLON, trace.getLineNumber()) : className;
    }

    @Nonnull
    public static String getExecutingClassNameAndAppend(@Nullable String suffix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return StringUtils.join(trace.getClassName(), suffix);
    }

    @Nonnull
    public static String getExecutingClassNameAndAppend(boolean shortName, @Nullable String suffix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(className, suffix);
    }

    @Nonnull
    public static String getExecutingClassNameAndPrepend(@Nullable String prefix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return StringUtils.join(prefix, trace.getClassName());
    }

    @Nonnull
    public static String getExecutingClassNameAndPrepend(boolean shortName, @Nullable String prefix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(prefix, className);
    }

    @Nonnull
    public static String getExecutingClassMethodName() {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return StringUtils.join(trace.getClassName(), CharVariantConst.DOT, trace.getMethodName());
    }

    @Nonnull
    public static String getExecutingClassMethodName(boolean shortName) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(className, CharVariantConst.DOT, trace.getMethodName());
    }

    @Nonnull
    public static String getExecutingClassMethodNameAndAppend(@Nullable String suffix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return StringUtils.join(trace.getClassName(), CharVariantConst.DOT, trace.getMethodName(), suffix);
    }

    @Nonnull
    public static String getExecutingClassMethodNameAndAppend(boolean shortName, @Nullable String suffix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(className, CharVariantConst.DOT, trace.getMethodName(), suffix);
    }

    @Nonnull
    public static String getExecutingClassMethodNameAndPrepend(@Nullable String prefix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return StringUtils.join(prefix, trace.getClassName(), CharVariantConst.DOT, trace.getMethodName());
    }

    @Nonnull
    public static String getExecutingClassMethodNameAndPrepend(boolean shortName, @Nullable String prefix) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        String className = shortName ? StringUtils.substringAfterLast(trace.getClassName(), CharVariantConst.DOT) : trace.getClassName();
        return StringUtils.join(prefix, className, CharVariantConst.DOT, trace.getMethodName());
    }

    @Nonnull
    public static String getExecutingMethodName() {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return trace.getMethodName();
    }

    @Nullable
    public static String getExecutingFileName() {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return trace.getFileName();
    }

    @Nullable
    public static String getExecutingFileName(boolean lineNumber) {
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), 2);
        Validate.notNull(trace);
        return lineNumber ? StringUtils.join(trace.getFileName(), CharVariantConst.COLON, trace.getLineNumber()) : trace.getFileName();
    }

    /**
     * Returns a {@link java.lang.StackTraceElement} object with the specified index
     *
     * @param index the index from the invoking method, unnecessary to consider the {@link StackTraceWraps} class
     *
     * @return a {@link java.lang.StackTraceElement} object with the specified index
     *
     * @throws SecurityException if a security manager exists and its <tt>checkPermission</tt> method doesn't allow getting the stack trace of thread
     */
    @Nullable
    public static StackTraceElement getStackTraceElement(int index) {
        return (index < 0) ? null : ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
    }

    @Nullable
    public static String getTracingClassMethodName(int index) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : StringUtils.join(trace.getClassName(), CharVariantConst.DOT, trace.getMethodName());
    }

    @Nullable
    @SuppressWarnings("DuplicatedCode")
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

    @Nullable
    public static String getTracingClassMethodNameAndAppend(int index, @Nullable String suffix) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : StringUtils.join(trace.getClassName(), CharVariantConst.DOT, trace.getMethodName(), suffix);
    }

    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static String getTracingClassMethodNameAndAppend(int index, boolean shortName, @Nullable String suffix) {
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

    @Nullable
    public static String getTracingClassMethodNameAndPrepend(int index, @Nullable String prefix) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : StringUtils.join(prefix, trace.getClassName(), CharVariantConst.DOT, trace.getMethodName());
    }

    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static String getTracingClassMethodNameAndPrepend(int index, boolean shortName, @Nullable String prefix) {
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

    @Nullable
    public static String getTracingClassName(int index) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : trace.getClassName();
    }

    @Nullable
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

    @Nullable
    @SuppressWarnings("DuplicatedCode")
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

    @Nullable
    public static String getTracingMethodName(int index) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : trace.getMethodName();
    }

    @Nullable
    public static String getTracingFileName(int index) {
        if (index < 0) {
            return null;
        }
        StackTraceElement trace = ArrayUtils.get(Thread.currentThread().getStackTrace(), index);
        return (trace == null) ? null : trace.getFileName();
    }

    @Nullable
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
