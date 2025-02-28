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

package com.yookue.commonplexus.javaseutil.function;


import java.util.Objects;


/**
 * Represents an operation that accepts three input arguments and return no result
 *
 * @param <T> the type of the first argument to the operation
 * @param <U> the type of the second argument to the operation
 * @param <V> the type of the third argument to the operation
 *
 * @author David Hsing
 * @see java.util.function.BiConsumer
 * @see org.apache.commons.lang3.function.TriFunction
 */
@FunctionalInterface
@SuppressWarnings("unused")
public interface TriConsumer<T, U, V> {
    /**
     * Perform this operation on the given arguments
     *
     * @param t the first input argument
     * @param u the second input argument
     * @param v the third input argument
     */
    void accept(T t, U u, V v);

    /**
     * Returns a composed {@code TriConsumer} that perform in sequence, this operation followed by the {@code after} operation
     * If performing either operation throws an exception, it is relayed to the caller of the composed operation
     * If performing this operation throws an exception, the {@code after} operation will not be performed
     *
     * @param after the operation to perform after this operation
     *
     * @return a composed {@code TriConsumer} that perform in sequence, this operation followed by the {@code after} operation
     *
     * @throws NullPointerException if the {@code after} operation is null
     */
    default TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> {
            accept(t, u, v);
            after.accept(t, u, v);
        };
    }
}
