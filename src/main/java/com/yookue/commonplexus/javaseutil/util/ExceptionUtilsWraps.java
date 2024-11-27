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


import jakarta.annotation.Nullable;
import org.apache.commons.lang3.exception.ExceptionUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.exception.ExceptionUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.exception.ExceptionUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ExceptionUtilsWraps {
    public static Throwable getRootCause(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getRootCause(throwable);
    }

    public static Exception getRootCauseAsException(@Nullable Throwable throwable) {
        return getRootCauseAsException(throwable, null);
    }

    /**
     * @see org.apache.commons.lang3.exception.ExceptionUtils#getRootCause
     */
    public static Exception getRootCauseAsException(@Nullable Throwable throwable, @Nullable Exception defaultValue) {
        Throwable cause = ExceptionUtils.getRootCause(throwable);
        return (cause instanceof Exception alias) ? alias: defaultValue;
    }

    public static String getRootCauseMessage(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getRootCauseMessage(throwable);
    }

    public static String getRootCauseStackTrace(@Nullable Throwable throwable) {
        return getStackTrace(getRootCause(throwable));
    }

    public static String[] getRootCauseStackTraces(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getRootCauseStackTrace(throwable);
    }

    public static String[] getRootCauseStackFrames(@Nullable Throwable throwable) {
        return getStackFrames(getRootCause(throwable));
    }

    public static String[] getStackFrames(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getStackFrames(throwable);
    }

    public static String getStackTrace(@Nullable Throwable throwable) {
        return (throwable == null) ? null : ExceptionUtils.getStackTrace(throwable);
    }
}
