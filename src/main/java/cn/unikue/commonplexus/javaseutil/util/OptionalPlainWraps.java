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


import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link java.util.Optional}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "OptionalUsedAsFieldOrParameterType"})
public abstract class OptionalPlainWraps {
    /**
     * Returns the result of applying the action to the value if present, or null otherwise
     *
     * @param source The optional to check
     * @param action The function to apply to the value if present
     *
     * @return the result of the action, or null if optional is empty or null
     */
    @Nullable
    public static <T, R> R getIfPresent(@Nullable Optional<T> source, @Nullable Function<T, R> action) {
        return getIfPresent(source, action, null);
    }

    /**
     * Returns the result of applying the action to the filtered value if present, or null otherwise
     *
     * @param source The optional to check
     * @param action The function to apply to the value if present
     * @param filter The predicate to filter the value, or null to skip filtering
     *
     * @return the result of the action, or null if optional is empty, null, or doesn't pass filter
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T, R> R getIfPresent(@Nullable Optional<T> source, @Nullable Function<T, R> action, @Nullable Predicate<? super T> filter) {
        if (ObjectUtils.anyNull(source, action)) {
            return null;
        }
        Optional<T> filtered = (filter == null) ? source : source.filter(filter);
        return filtered.map(action).orElse(null);
    }

    /**
     * Executes the action with the value if present
     *
     * @param source The optional to check
     * @param action The consumer to execute with the value if present
     */
    public static <T> void ifPresent(@Nullable Optional<T> source, @Nullable Consumer<? super T> action) {
        ifPresent(source, action, null);
    }

    /**
     * Executes the action with the filtered value if present
     *
     * @param source The optional to check
     * @param action The consumer to execute with the value if present
     * @param filter The predicate to filter the value, or null to skip filtering
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T> void ifPresent(@Nullable Optional<T> source, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (ObjectUtils.anyNull(source, action)) {
            return;
        }
        if (filter != null) {
            source.filter(filter).ifPresent(action);
        } else {
            source.ifPresent(action);
        }
    }

    /**
     * Executes the action if a value is present
     *
     * @param source The optional to check
     * @param action The runnable to execute if value is present
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T> void ifPresent(@Nullable Optional<T> source, @Nullable Runnable action) {
        if (ObjectUtils.allNotNull(source, action) && source.isPresent()) {
            action.run();
        }
    }

    /**
     * Executes presentAction if value is present, otherwise executes absentAction
     *
     * @param source The optional to check
     * @param presentAction The consumer to execute with the value if present
     * @param absentAction The runnable to execute if value is absent
     */
    public static <T> void ifPresentOrElse(@Nullable Optional<T> source, @Nullable Consumer<? super T> presentAction, @Nullable Runnable absentAction) {
        if (source != null && source.isPresent()) {
            if (presentAction != null) {
                presentAction.accept(source.get());
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    /**
     * Executes presentAction if value is present, otherwise executes absentAction
     *
     * @param source The optional to check
     * @param presentAction The runnable to execute if value is present
     * @param absentAction The runnable to execute if value is absent
     */
    public static <T> void ifPresentOrElse(@Nullable Optional<T> source, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (source != null && source.isPresent()) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    /**
     * Executes the action if the optional is empty or null
     *
     * @param source The optional to check
     * @param action The runnable to execute if optional is empty or null
     */
    @SuppressWarnings("OptionalAssignedToNull")
    public static void ifNotPresent(@Nullable Optional<?> source, @Nullable Runnable action) {
        if ((source == null || source.isEmpty()) && action != null) {
            action.run();
        }
    }

    /**
     * Unwrap the given object which is potentially a {@link java.util.Optional}
     * Differ from {@code org.springframework.util.ObjectUtils#unwrapOptional}, this return object recursively rather than throw a {@link java.lang.IllegalArgumentException}
     *
     * @param source The source object
     *
     * @return either the value held within the {@code Optional}, {@code null}
     *
     * @see "org.springframework.util.ObjectUtils#unwrapOptional"
     */
    @Nullable
    @SuppressWarnings("OptionalAssignedToNull")
    public static Object unwrap(@Nullable Optional<?> source) {
        Object result = (source == null || source.isEmpty()) ? null : source.get();
        return (result instanceof Optional<?> alias) ? unwrap(alias) : result;
    }

    /**
     * Unwraps the optional and returns the value cast to the expected type
     *
     * @param source The optional to unwrap
     * @param expectType The expected type to cast to
     *
     * @return the unwrapped value cast to the expected type, or null if optional is empty or null
     */
    @Nullable
    public static <T> T unwrapAs(@Nullable Optional<?> source, @Nullable Class<T> expectType) {
        return ObjectUtils.anyNull(source, expectType) ? null : ObjectUtilsWraps.castAs(unwrap(source), expectType);
    }
}
