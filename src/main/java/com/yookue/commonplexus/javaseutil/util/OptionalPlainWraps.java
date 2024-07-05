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
    @Nullable
    public static <T, R> R getIfPresent(@Nullable Optional<T> source, @Nullable Function<T, R> action) {
        return getIfPresent(source, action, null);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T, R> R getIfPresent(@Nullable Optional<T> source, @Nullable Function<T, R> action, @Nullable Predicate<? super T> filter) {
        if (ObjectUtils.anyNull(source, action)) {
            return null;
        }
        Optional<T> filtered = (filter == null) ? source : source.filter(filter);
        return filtered.map(t -> action.apply(t)).orElse(null);
    }

    public static <T> void ifPresent(@Nullable Optional<T> source, @Nullable Consumer<? super T> action) {
        ifPresent(source, action, null);
    }

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

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T> void ifPresent(@Nullable Optional<T> source, @Nullable Runnable action) {
        if (ObjectUtils.allNotNull(source, action) && source.isPresent()) {
            action.run();
        }
    }

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
     * @param source the source object
     *
     * @return either the value held within the {@code Optional}, {@code null}
     *
     * @see "org.springframework.util.ObjectUtils#unwrapOptional"
     */
    @Nullable
    @SuppressWarnings("OptionalAssignedToNull")
    public static Object unwrap(@Nullable Optional<?> source) {
        Object result = (source == null || source.isEmpty()) ? null : source.get();
        return (result instanceof Optional) ? unwrap((Optional<?>) result) : result;
    }

    @Nullable
    public static <T> T unwrapAs(@Nullable Optional<?> source, @Nullable Class<T> expectedType) {
        return ObjectUtils.anyNull(source, expectedType) ? null : ObjectUtilsWraps.castAs(unwrap(source), expectedType);
    }
}
