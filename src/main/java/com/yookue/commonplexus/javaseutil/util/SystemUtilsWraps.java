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


import java.util.Collection;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.SystemUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.SystemUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class SystemUtilsWraps {
    public static boolean existsAllVariables(@Nullable CharSequence... variables) {
        return existsAllVariables(ArrayUtilsWraps.asList(variables));
    }

    public static boolean existsAllVariables(@Nullable Collection<? extends CharSequence> variables) {
        return CollectionPlainWraps.isNotEmpty(variables) && variables.stream().allMatch(SystemUtilsWraps::existsVariable);
    }

    public static boolean existsAnyVariables(@Nullable CharSequence... variables) {
        return existsAnyVariables(ArrayUtilsWraps.asList(variables));
    }

    public static boolean existsAnyVariables(@Nullable Collection<? extends CharSequence> variables) {
        return CollectionPlainWraps.isNotEmpty(variables) && variables.stream().anyMatch(SystemUtilsWraps::existsVariable);
    }

    public static boolean existsVariable(@Nullable CharSequence name) {
        return getVariable(name) != null;
    }

    @Nullable
    public static String getVariable(@Nullable CharSequence name) {
        return StringUtils.isBlank(name) ? null : SystemUtils.getEnvironmentVariable(CharSequenceWraps.toStringIgnoreNull(name), null);
    }
}
