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


import java.util.Collection;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.SystemUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.SystemUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class SystemUtilsWraps {
    /**
     * Checks whether all given environment variables exist
     *
     * @param variables The variable names to check
     *
     * @return {@code true} if all variables exist, {@code false} otherwise
     */
    public static boolean existsAllVariables(@Nullable CharSequence... variables) {
        return existsAllVariables(ArrayUtilsWraps.asList(variables));
    }

    /**
     * Checks whether all environment variables in the collection exist
     *
     * @param variables The collection of variable names to check
     *
     * @return {@code true} if all variables exist and collection is not empty, {@code false} otherwise
     */
    public static boolean existsAllVariables(@Nullable Collection<? extends CharSequence> variables) {
        return CollectionPlainWraps.isNotEmpty(variables) && variables.stream().allMatch(SystemUtilsWraps::existsVariable);
    }

    /**
     * Checks whether any of the given environment variables exists
     *
     * @param variables The variable names to check
     *
     * @return {@code true} if any variable exists, {@code false} otherwise
     */
    public static boolean existsAnyVariables(@Nullable CharSequence... variables) {
        return existsAnyVariables(ArrayUtilsWraps.asList(variables));
    }

    /**
     * Checks whether any environment variable in the collection exists
     *
     * @param variables The collection of variable names to check
     *
     * @return {@code true} if any variable exists and collection is not empty, {@code false} otherwise
     */
    public static boolean existsAnyVariables(@Nullable Collection<? extends CharSequence> variables) {
        return CollectionPlainWraps.isNotEmpty(variables) && variables.stream().anyMatch(SystemUtilsWraps::existsVariable);
    }

    /**
     * Checks whether an environment variable with the given name exists
     *
     * @param name The variable name to check
     *
     * @return {@code true} if the variable exists, {@code false} otherwise
     */
    public static boolean existsVariable(@Nullable CharSequence name) {
        return getVariable(name) != null;
    }

    /**
     * Returns the value of the environment variable with the given name
     *
     * @param name The variable name to look up
     *
     * @return the variable value, or null if name is blank or variable doesn't exist
     */
    @Nullable
    public static String getVariable(@Nullable CharSequence name) {
        return StringUtils.isBlank(name) ? null : SystemUtils.getEnvironmentVariable(CharSequenceWraps.toStringIgnoreNull(name), null);
    }
}
