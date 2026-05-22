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


import jakarta.annotation.Nullable;
import org.apache.commons.lang3.exception.ExceptionUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.exception.ExceptionUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.exception.ExceptionUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ExceptionUtilsWraps {
    /**
     * Gets the root cause of the throwable by traversing the exception chain
     *
     * @param throwable the throwable to get the root cause from
     *
     * @return the root cause, or {@code null} if the throwable is null
     *
     * @see "org.springframework.core.NestedExceptionUtils#getMostSpecificCause"
     */
    public static Throwable getRootCause(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getRootCause(throwable);
    }

    /**
     * Gets the root cause of the throwable as an Exception
     *
     * @param throwable the throwable to get the root cause from
     *
     * @return the root cause as an Exception, or {@code null} if the throwable is null or the root cause is not an Exception
     */
    public static Exception getRootCauseAsException(@Nullable Throwable throwable) {
        return getRootCauseAsException(throwable, null);
    }

    /**
     * Gets the root cause of the throwable as an Exception, or returns the default value if not applicable
     *
     * @param throwable the throwable to get the root cause from
     * @param defaultValue the default value to return if the root cause is not an Exception
     *
     * @return the root cause as an Exception, or the default value if the throwable is null or the root cause is not an Exception
     *
     * @see org.apache.commons.lang3.exception.ExceptionUtils#getRootCause
     */
    public static Exception getRootCauseAsException(@Nullable Throwable throwable, @Nullable Exception defaultValue) {
        Throwable cause = ExceptionUtils.getRootCause(throwable);
        return (cause instanceof Exception alias) ? alias: defaultValue;
    }

    /**
     * Gets the message from the root cause of the throwable
     *
     * @param throwable the throwable to get the root cause message from
     *
     * @return the root cause message, or {@code null} if the throwable is null
     */
    public static String getRootCauseMessage(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getRootCauseMessage(throwable);
    }

    /**
     * Gets the stack trace string from the root cause of the throwable
     *
     * @param throwable the throwable to get the root cause stack trace from
     *
     * @return the root cause stack trace as a string, or {@code null} if the throwable is null
     */
    public static String getRootCauseStackTrace(@Nullable Throwable throwable) {
        return getStackTrace(getRootCause(throwable));
    }

    /**
     * Gets the stack trace lines from the root cause of the throwable
     *
     * @param throwable the throwable to get the root cause stack traces from
     *
     * @return an array of stack trace lines from the root cause, or {@code null} if the throwable is null
     */
    public static String[] getRootCauseStackTraces(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getRootCauseStackTrace(throwable);
    }

    /**
     * Gets the stack frames from the root cause of the throwable
     *
     * @param throwable the throwable to get the root cause stack frames from
     *
     * @return an array of stack frames from the root cause, or {@code null} if the throwable is null
     */
    public static String[] getRootCauseStackFrames(@Nullable Throwable throwable) {
        return getStackFrames(getRootCause(throwable));
    }

    /**
     * Gets the stack frames from the throwable
     *
     * @param throwable the throwable to get the stack frames from
     *
     * @return an array of stack frames, or {@code null} if the throwable is null
     */
    public static String[] getStackFrames(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getStackFrames(throwable);
    }

    /**
     * Gets the stack trace string from the throwable
     *
     * @param throwable the throwable to get the stack trace from
     *
     * @return the stack trace as a string, or {@code null} if the throwable is null
     */
    public static String getStackTrace(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getStackTrace(throwable);
    }
}
