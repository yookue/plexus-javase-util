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

package com.yookue.commonplexus.javaseutil.support;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * Fluent capable of {@link java.util.Optional}
 * <p>
 * Similar to use fluent methods of {@link java.util.Optional#map}
 *
 * @author David Hsing
 * @reference "https://blog.csdn.net/code_shadow/article/details/109039751"
 * @see java.util.Optional
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings({"unused", "WeakerAccess", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public class FluentOptional<T> {
    private static final FluentOptional<?> EMPTY = new FluentOptional<>();
    private T value = null;

    private FluentOptional(@Nullable T value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    public static <T> FluentOptional<T> empty() {
        return (FluentOptional<T>) EMPTY;
    }

    @Nonnull
    public static <T> FluentOptional<T> of(@Nonnull T value) {
        return new FluentOptional<>(value);
    }

    @Nonnull
    public static <T> FluentOptional<T> ofNullable(@Nullable T value) {
        return (value == null) ? empty() : of(value);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public <R> FluentOptional<R> ofFluent(@Nullable Function<? super T, ? extends R> mapper) {
        return ObjectUtils.anyNull(mapper, value) ? empty() : ofNullable(mapper.apply(value));
    }

    public FluentOptional<T> filter(@Nonnull Predicate<? super T> predicate) {
        return (value == null) ? this : (predicate.test(value) ? this : empty());
    }

    @Nullable
    public T get() {
        return value;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public boolean isNotEmpty() {
        return value != null;
    }

    public boolean isPresent() {
        return value != null;
    }

    public boolean isNotPresent() {
        return value == null;
    }

    public void ifPresent(@Nonnull Consumer<? super T> action) {
        if (value != null) {
            action.accept(value);
        }
    }

    public void ifNotPresent(@Nonnull Runnable action) {
        if (value == null) {
            action.run();
        }
    }

    public void ifPresentOrElse(@Nonnull Consumer<? super T> presentAction, @Nonnull Runnable emptyAction) {
        if (value != null) {
            presentAction.accept(value);
        } else {
            emptyAction.run();
        }
    }

    public <U> FluentOptional<U> map(@Nonnull Function<? super T, ? extends U> mapper) {
        return (value == null) ? empty() : ofNullable(mapper.apply(value));
    }

    @SuppressWarnings("unchecked")
    public <U> FluentOptional<U> flatMap(@Nonnull Function<? super T, ? extends FluentOptional<? extends U>> mapper) {
        if (value == null) {
            return empty();
        }
        FluentOptional<U> result = (FluentOptional<U>) mapper.apply(value);
        return (result == null) ? empty() : result;
    }

    @SuppressWarnings("unchecked")
    public FluentOptional<T> or(@Nonnull Supplier<? extends FluentOptional<? extends T>> supplier) {
        if (value != null) {
            return this;
        }
        FluentOptional<T> result = (FluentOptional<T>) supplier.get();
        return (result == null) ? empty() : result;
    }

    @Nullable
    public T orElse(@Nullable T other) {
        return value != null ? value : other;
    }

    public T orElseGet(@Nonnull Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    public T orElseThrow() {
        if (value == null) {
            throw new NoSuchElementException("None value present");
        }
        return value;
    }

    public <X extends Throwable> T orElseThrow(@Nonnull Supplier<? extends X> supplier) throws X {
        if (value != null) {
            return value;
        }
        throw supplier.get();
    }

    public Stream<T> stream() {
        return (value == null) ? Stream.empty() : Stream.of(value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
